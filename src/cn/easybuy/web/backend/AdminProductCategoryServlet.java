package cn.easybuy.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.params.ProductParams;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.Constants;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.web.AbstractServlet;
@WebServlet(urlPatterns={"/admin/productCategory"},name="adminProductCategory")
public class AdminProductCategoryServlet extends AbstractServlet {
	private ProductCategory productCategory;
	private ProductCategoryService productCategoryService;
	private ProductService productService;
	@Override
	public void init() throws ServletException {
		productCategory=new ProductCategory();
		productCategoryService=new ProductCategoryServiceImpl();
		productService=new ProductServiceImpl();
	}
	
	public String index(HttpServletRequest request, HttpServletResponse response)
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
		ProductCategoryParam params=new ProductCategoryParam();
		int total=productCategoryService.queryproductCategoryCount(params);
		pager=new Pager(total, rowPerPage, currentPage);
		params.openPage((pager.getCurrentPage()-1)*pager.getRowPerPage(), pager.getRowPerPage());
		pager.setUrl("/admin/productCategory?action=index");
		List<ProductCategory> productCategoryList=productCategoryService.queryProductCategoryList(params);
		request.setAttribute("productCategoryList", productCategoryList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 4);
		return "/backend/productCategory/productCategoryList";
	}

	public ReturnResult deleteProductCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		ProductCategoryParam productCategoryParam=new ProductCategoryParam();
		productCategoryParam.setParentId(Integer.parseInt(id));
		int count=productCategoryService.queryproductCategoryCount(productCategoryParam);
		if (count>0) {
			return result.returnFail("存在子分类，不能删除");
		}
		ProductParams productParams=new ProductParams();
		productParams.setCategorId(Integer.parseInt(id));
		count=productService.queryProductCount(productParams);
		if(count>0){
			return result.returnFail("分类下存在商品，不能删除");
		}
		productCategoryService.deleteById(Integer.parseInt(id));
		result.returnSuccess();
		return result;
	}
	
	
	public String toAddProductCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ProductCategory> productCategoryList1=null;
		ProductCategoryParam params=new ProductCategoryParam();
		params.setType(1);
		params.setPage(false);
		productCategoryList1=productCategoryService.queryProductCategoryList(params);
		request.setAttribute("productCategoryList1", productCategoryList1);
		return "/backend/productCategory/toAddProductCategory";
	}
	
	
	public ReturnResult queryProductCategoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		String parentId=request.getParameter("parentId");
		List<ProductCategory> 	pcList=null;
		ProductCategoryParam params=new ProductCategoryParam();
		params.setParentId(EmptyUtils.isEmpty(parentId)?0:Integer.parseInt(parentId));
		params.setPage(false);
		pcList=productCategoryService.queryProductCategoryList(params);
		result.setStatus(Constants.ReturnResult.SUCCESS);
		result.setData(pcList);
		return result;
	}
	
	
	public ReturnResult addProductCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		Integer parentId=0;
		String type=request.getParameter("type");
		String productCategoryLevel1=request.getParameter("productCategoryLevel1");
		String productCategoryLevel2=request.getParameter("productCategoryLevel2");
		String name=request.getParameter("name");
		if ("1".equals(type)) {
			parentId=0;
		}else if ("2".equals(type)) {
			parentId=Integer.parseInt(productCategoryLevel1);
		}else {
			parentId=Integer.parseInt(productCategoryLevel2);
		}
		ProductCategory productCategory=new ProductCategory();
		productCategory.setName(name);
		productCategory.setType(Integer.parseInt(type));
		productCategory.setParentId(parentId);
		productCategoryService.addProductCategory(productCategory);
		result.returnSuccess();
		return result;
	}
	
	public String toUpdateProductCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		ProductCategory pc=productCategoryService.getById(Integer.parseInt(id));
		List<ProductCategory> pcList1=new ArrayList<ProductCategory>();
		List<ProductCategory> pcList2=new ArrayList<ProductCategory>();
		if (pc.getType()>=2) {
			pcList1=productCategoryService.queryAllProductCategory(null);
		}if (pc.getType()==3) {
			ProductCategoryParam params=new ProductCategoryParam();
			params.setType(2);
			params.setPage(false);
			pcList2=productCategoryService.queryProductCategoryList(params);
			ProductCategory parentProductCategory=productCategoryService.getById(pc.getParentId());
			request.setAttribute("parentProductCategory", parentProductCategory);
		}
		request.setAttribute("pc", pc);
		request.setAttribute("productCategoryList1", pcList1);
		request.setAttribute("productCategoryList2", pcList2);
		return "/backend/productCategory/toAddProductCategory";
	}
	
	public ReturnResult modifyProductCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			ReturnResult result=new ReturnResult();
			Integer parentId=0;
			String id=request.getParameter("id");
			String productCategoryLevel1=request.getParameter("productCategoryLevel1");
			String productCategoryLevel2=request.getParameter("productCategoryLevel2");
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			if ("1".equals(type)) {
				parentId=0;
			}else if ("2".equals(type)) {
				parentId=Integer.parseInt(productCategoryLevel1);
			}else {
				parentId=Integer.parseInt(productCategoryLevel2);
			}
			ProductCategory productCategory=new ProductCategory();
			productCategory.setId(Integer.parseInt(id));
			productCategory.setName(name);
			productCategory.setParentId(parentId);
			productCategory.setType(Integer.parseInt(type));
			productCategoryService.modifyProductCategory(productCategory);
			result.returnSuccess();
			return result;
	}
	
	
	@Override
	public Class getServletClass() {
		return AdminProductCategoryServlet.class;
	}

}
