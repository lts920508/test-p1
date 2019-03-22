package cn.easybuy.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.News;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.service.news.NewsService;
import cn.easybuy.service.news.NewsServiceImpl;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.utils.ProductCategoryVo;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/Home" }, name = "Home")
public class HomeServlet extends AbstractServlet {

	private ProductCategoryService pcService;
	private NewsService newsService;
	public void init() throws ServletException{
		pcService=new ProductCategoryServiceImpl();
		newsService=new NewsServiceImpl();
	}
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		doPost(request, response);
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		pcService = new ProductCategoryServiceImpl();
//		List<ProductCategory> pcList = pcService.queryAllProductCategory("0");
//		request.setAttribute("pcList", pcList);
//		request.getRequestDispatcher("/pre/index.jsp").forward(request,
//				response);
//	}
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ProductCategoryVo> pcList = pcService.queryAllProductCategory();
		List<News> newsList=newsService.queryAllNews();
		request.setAttribute("pcList", pcList);
		request.setAttribute("newsList", newsList);
		return "/pre/index";
	}
	
	public String index2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String testsString=request.getParameter("testString");
		//logger.info(testsString);
		return "/pre/index";
	}
	@Override
	public Class getServletClass() {
		return HomeServlet.class;
	}
}
