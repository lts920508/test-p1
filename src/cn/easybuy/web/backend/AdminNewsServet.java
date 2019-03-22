package cn.easybuy.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.News;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.NewsParams;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.service.news.NewsService;
import cn.easybuy.service.news.NewsServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.web.AbstractServlet;
@WebServlet(urlPatterns={"/admin/news"},name="adminNews")
public class AdminNewsServet extends AbstractServlet {

	private NewsService newsService;
	private ProductService productService;
	
	@Override
	public void init() throws ServletException {
		newsService=new NewsServiceImpl();
		productService=new ProductServiceImpl();
		
	}
	
	public String queryNewsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Pager pager=null;
		String currentPageStr=request.getParameter("currentPage");
		String pageSize=request.getParameter("pageSize");
		int rowPerPage=EmptyUtils.isEmpty(pageSize)?8:Integer.parseInt(pageSize);
		int currentPage=EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
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
		int total=newsService.queryNewsCount(new NewsParams());
		pager=new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/admin/news?action=queryNewsList");
		List<News> newsList=newsService.queryNewsList(pager);
		request.setAttribute("newsList", newsList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 7);
		return "/backend/news/newsList";
	}

	
	public String toAddNewNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/backend/news/toNewNews";
	}
	
	
	public String newsDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		News news=newsService.getNewsById(Integer.parseInt(id));
		request.setAttribute("news", news);
		request.setAttribute("menu", 7);
		return "/backend/news/newsDetail";
	}
	
	public ReturnResult addNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		String title=request.getParameter("title");
		String content=request.getParameter("newsContent");
		News news=new News();
		news.setTitle(title);
		news.setContent(content);
		String date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		news.setCreateTime(date);
		newsService.addNews(news);
		result.returnSuccess();
		return result;
	}
	
	public ReturnResult deleteNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("id");
		newsService.deleteNews(Integer.parseInt(id));
		result.returnSuccess();
		return result;
	}
	
	
	
	@Override
	public Class getServletClass() {
		return AdminNewsServet.class;
	}

}
