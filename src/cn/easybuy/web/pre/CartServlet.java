package cn.easybuy.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.Product;
import cn.easybuy.entity.User;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.service.order.CartService;
import cn.easybuy.service.order.CartServiceImpl;
import cn.easybuy.service.order.OrderService;
import cn.easybuy.service.order.OrderServiceImpl;
import cn.easybuy.service.order.UserAddressService;
import cn.easybuy.service.order.UserAddressServiceImpl;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.Constants;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.ProductCategoryVo;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.ShoppingCart;
import cn.easybuy.utils.ShoppingCartItem;
import cn.easybuy.web.AbstractServlet;
@WebServlet(urlPatterns={"/Cart"},name="Cart")
public class CartServlet extends AbstractServlet {

	private ProductService productService;
	private ProductCategoryService productCategoryService;
	private CartService cartService;
	private OrderService orderService;
	private UserAddressService userAddressService;
	@Override
	public void init() throws ServletException {
		productService=new ProductServiceImpl();
		productCategoryService=new ProductCategoryServiceImpl();
		cartService=new CartServiceImpl();
		orderService=new OrderServiceImpl();
		userAddressService=new UserAddressServiceImpl();
	}
	
	@Override
	public Class getServletClass() {
		return CartServlet.class;
	}
	 
	
	
	
	public ReturnResult add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("entityId");
		String quantityStr=request.getParameter("quantity");
		Integer quantity=1;
		if (EmptyUtils.isNotEmpty(quantityStr)) {
			quantity=Integer.parseInt(quantityStr);
		}
		Product product=productService.findById(id);
		if (product.getStock()<quantity) {
			return result.returnFail("商品数量不足");
		}
		ShoppingCart cart=getCartFromSession(request);
		result=cart.addItem(product, quantity);
		if (result.getStatus()==Constants.ReturnResult.SUCCESS) {
			cart.setSum(EmptyUtils.isEmpty(cart.getSum())?0.0:cart.getSum()+(product.getPrice()*quantity*1.0));
		}
		return result;
	}

	private ShoppingCart getCartFromSession(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		ShoppingCart cart=(ShoppingCart)session.getAttribute("cart");
		if(EmptyUtils.isEmpty(cart)){
			cart=new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	

	private User getUserFromSession(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("loginUser");
		if(EmptyUtils.isEmpty(user)){
			user=new User();
			session.setAttribute("user", user);
		}
		return user;
	}
	
	
	public String refreshCart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/common/pre/searchBar";
	}
	
	public String toSettlement(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<ProductCategoryVo> pcList=productCategoryService.queryAllProductCategory();
		request.setAttribute("pcList", pcList);
		return "/pre/settlement/toSettlement";
	}
	
	public String toSettlement1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/pre/settlement/settlement1";
	}
	
	public String settlement2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		User user=getUserFromSession(request);
		List<UserAddress> userAddressList=userAddressService.queryUserAddressList(user.getId());
		request.setAttribute("userAddressList", userAddressList);
		return "/pre/settlement/settlement2";
	}
	
	public Object settlement3(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ShoppingCart cart=getCartFromSession(request);
		ReturnResult result=checkCart(request);
		if (result.getStatus()==Constants.ReturnResult.FAIL) {
			return result;
		}
		User user=getUserFromSession(request);
		String addressId=request.getParameter("addressId");
		String newAddress=request.getParameter("newAddress");
		String newRemark=request.getParameter("newRemark");
		UserAddress userAddress=new UserAddress();
		if ("-1".equals(addressId)) {
			userAddress.setAddress(newAddress);
			userAddress.setRemark(newRemark);
			userAddressService.addUserAddress(user.getId(),newAddress,newRemark);
		}else{
			userAddress=userAddressService.getUserAddressById(Integer.parseInt(addressId));
		}
		Order order=orderService.payShoppingCart(user, userAddress.getAddress(), cart);
		request.setAttribute("order", order);
		clearCart(request, response);
		return "/pre/settlement/settlement3";
	}
	
	public ReturnResult clearCart(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ReturnResult result=new ReturnResult();
		request.getSession().removeAttribute("cart");
		result.returnSuccess(null);
		return result;
	}
	
	public ReturnResult checkCart(HttpServletRequest request)throws Exception{
		ReturnResult result=new ReturnResult();
		HttpSession session=request.getSession();
		ShoppingCart cart=getCartFromSession(request);
		for (ShoppingCartItem item:cart.getItems()) {
			Product product=productService.findById(item.getProduct().getId()+"");
			if (product.getStock()<item.getQuantity()) {
				return result.returnFail(product.getName()+"商品数量不足");
			}
		}
		return result;
	}
	
	public ReturnResult modifyCart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnResult result=new ReturnResult();
		HttpSession session=request.getSession();
		String id=request.getParameter("entityId");
		String quantityStr=request.getParameter("quantity");
		if (EmptyUtils.isEmpty("id") || EmptyUtils.isEmpty(quantityStr)) {
			return result.returnFail("参数不能为空");
		}
		Integer quantity=Integer.parseInt(quantityStr);
		ShoppingCart cart=getCartFromSession(request);
		Product product=productService.findById(id);
		if (quantity>product.getStock()) {
			return result.returnFail("商品数量不足");
		}
		cart=cartService.modifyShoppingCart(id, quantity, cart);
		session.setAttribute("cart", cart);
		return result.returnSuccess();
	}
}
