package cn.easybuy.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.easybuy.entity.Product;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.entity.User;
import cn.easybuy.params.ProductParams;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ProductCategoryVo;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.SecurityUtils;
import cn.easybuy.web.AbstractServlet;
@WebServlet(urlPatterns={"/Product"},name="Product")
public class ProductServlet extends AbstractServlet {
	private ProductService productService;
	private ProductCategoryService productCategoryService;
	List<Product> productlList;
	Pager pager;
	public String queryProductList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId=request.getParameter("categoryId");
		Integer cInteger=null;
		if (EmptyUtils.isNotEmpty(categoryId)) {
			cInteger=Integer.parseInt(categoryId);
		}
		String keyWord=request.getParameter("keyWord");
		String currentPageStr=request.getParameter("currentPage");
		String pageSizeStr=request.getParameter("pageSize");
		int currentPage=EmptyUtils.isNotEmpty(currentPageStr)?Integer.parseInt(currentPageStr):1;
		if (EmptyUtils.isNotEmpty(pager)) {
			if (currentPage<1) {
				currentPage=1;
			}
			if (EmptyUtils.isNotEmpty(pager.getPageCount())) {
				if (currentPage>pager.getPageCount()) {
					currentPage=pager.getPageCount();
				}
			}
		}
		int rowPerPage=EmptyUtils.isNotEmpty(pageSizeStr)?Integer.parseInt(pageSizeStr):4;
		ProductParams params=new ProductParams();
		params.setCategorId(EmptyUtils.isNotEmpty(categoryId)?Integer.parseInt(categoryId):null);
		params.setKeyWords(keyWord);
		params.openPage((currentPage-1)*rowPerPage, rowPerPage);
		int total=productService.queryProductCount(params);
		pager=new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/Product?action=queryProductList&categoryId="+(EmptyUtils.isNotEmpty(categoryId)?categoryId:null)+"&keyWord="+(EmptyUtils.isNotEmpty(keyWord)?keyWord:null));
		List<ProductCategoryVo> productCategoryVoList=productCategoryService.queryAllProductCategory();
		List<Product> pList=productService.queryProductList(params);
		request.setAttribute("categoryId",cInteger);
		request.setAttribute("pList",pList );
		request.setAttribute("pager", pager);
		request.setAttribute("total",total );
		request.setAttribute("keyWord",keyWord );
		request.setAttribute("pcList",productCategoryVoList );
		return "/pre/product/productList";
	}
	@Override
	public Class getServletClass() {
		return ProductServlet.class;
	}
	
	public String queryProductDetail(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		Product product=productService.findById(id);
		productlList.add(0, product);
		List<ProductCategoryVo> productCategoryVolList=productCategoryService.queryAllProductCategory();
		request.setAttribute("product", product);
		request.setAttribute("pcList", productCategoryVolList);
		HttpSession session=request.getSession();
		session.setAttribute("historyProductList", productlList);
		return "/pre/product/productDetail";
	}
	
	
	public void init() throws ServletException{
		productService=new ProductServiceImpl();
		productCategoryService=new ProductCategoryServiceImpl();
		productlList=new LinkedList<Product>();
	}

	
	public ReturnResult search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 参数获取
		String keyWord=request.getParameter("keyWord");
		String currentPageStr=request.getParameter("currentPage");
		String pageSizeStr=request.getParameter("pageSize");
		int currentPage=EmptyUtils.isNotEmpty(currentPageStr)?Integer.parseInt(currentPageStr):1;
		if (EmptyUtils.isNotEmpty(pager)) {
			if (currentPage<1) {
				currentPage=1;
			}
			if (EmptyUtils.isNotEmpty(pager.getPageCount())) {
				if (currentPage>pager.getPageCount()) {
					currentPage=pager.getPageCount();
				}
			}
		}
		int rowPerPage=EmptyUtils.isNotEmpty(pageSizeStr)?Integer.parseInt(pageSizeStr):4;
		ProductParams params=new ProductParams();
		params.setKeyWords(keyWord);
		params.openPage((currentPage-1)*rowPerPage, rowPerPage);
		int total=productService.queryProductCount(params);
		pager=new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/Product?action=queryProductList&categoryId="+"&keyWord="+(EmptyUtils.isNotEmpty(keyWord)?keyWord:null));
		List<ProductCategoryVo> productCategoryVoList=productCategoryService.queryAllProductCategory();
		List<Product> pList=productService.queryProductList(params);
		if (EmptyUtils.isEmpty(pList)) {
			result.returnFail("没有商品");
			return result;
		}
		result.returnSuccess();
		return result;
	}
	
	
	
}
