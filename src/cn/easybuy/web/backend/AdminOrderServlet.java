package cn.easybuy.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.params.OrderDetailParam;
import cn.easybuy.params.OrderParams;
import cn.easybuy.service.order.OrderService;
import cn.easybuy.service.order.OrderServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.web.AbstractServlet;
@WebServlet(urlPatterns={"/admin/order"},name="order")
public class AdminOrderServlet extends AbstractServlet {
	private OrderService orderService;
	@Override
	public void init() throws ServletException {
		orderService=new OrderServiceImpl();
	}
	
	
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String currentPageStr=request.getParameter("currentPage");
		String pageSize=request.getParameter("pageSize");
		int rowPerPage=EmptyUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
		int currentPage=EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		OrderParams params=new OrderParams();
		if (EmptyUtils.isNotEmpty(userId)) {
			params.setUserId(Integer.parseInt(userId));
		}
		int total=orderService.getOrderRowCount(params);
		Pager pager=new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/admin/order?action=index&userId="+userId);
		List<Order> orderList=orderService.queryOrderList(Integer.parseInt(userId), pager);
		request.setAttribute("orderList", orderList);
		request.setAttribute("userId", Integer.parseInt(userId));
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 1);
		return "/backend/order/orderList";
	}
	
	public String queryOrderDetail(HttpServletRequest request,HttpServletResponse response){
		String orderId=request.getParameter("orderId");
		OrderDetailParam params=new OrderDetailParam();
		params.setOrderId(Integer.parseInt(orderId));
		int menu=Integer.parseInt(request.getParameter("menu"));
		List<OrderDetail> orderDetaillList=orderService.queryOrderDetailList(params);
		request.setAttribute("orderDetaillList", orderDetaillList);
		request.setAttribute("menu", menu);
		return "/backend/order/orderDetailList";
	}

	public String queryAllOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPageStr=request.getParameter("currentPage");
		String pageSize=request.getParameter("pageSize");
		int rowPerPage=EmptyUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
		int currentPage=EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		OrderParams params=new OrderParams();
		int total=orderService.getOrderRowCount(params);
		Pager pager=new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/admin/order?action=queryAllOrder");
		List<Order> orderList=orderService.queryOrderList(null, pager);
		request.setAttribute("allOrderList", orderList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 9);
		return "/backend/order/allOrderList";
	}
	
	@Override
	public Class getServletClass() {
		return AdminOrderServlet.class;
	}

}
