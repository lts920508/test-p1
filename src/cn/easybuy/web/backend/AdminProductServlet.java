package cn.easybuy.web.backend;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.easybuy.entity.Product;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.params.ProductParams;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.StringUtils;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/product" }, name = "adminProduct")
public class AdminProductServlet extends AbstractServlet {
	private static final String TMP_DIR_PATH = "c:\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "files";
	private File destinationDir;
	private ProductService productService;
	private ProductCategoryService productCategoryService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}
		String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		destinationDir.mkdirs();
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ "is not a directory");
		}
		productService = new ProductServiceImpl();
		productCategoryService = new ProductCategoryServiceImpl();
	}

	public String index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Pager pager = null;
		String currentPageStr = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 8 : Integer
				.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer
				.parseInt(currentPageStr);
		if (EmptyUtils.isNotEmpty(pager)) {
			if (currentPage < 1) {
				currentPage = 1;
			}
			if (EmptyUtils.isNotEmpty(pager.getPageCount())) {
				if (currentPage > pager.getPageCount()) {
					currentPage = pager.getPageCount();
				}
			}
		}
		ProductParams params = new ProductParams();
		int total = productService.queryProductCount(params);
		pager = new Pager(total, rowPerPage, currentPage);
		params.openPage((pager.getCurrentPage() - 1) * pager.getRowPerPage(),
				pager.getRowPerPage());
		pager.setUrl("/admin/product?action=index");
		List<Product> productList = productService.queryProductList(params);
		request.setAttribute("productList", productList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 5);
		return "/backend/product/productList";
	}

	public ReturnResult deleteById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		String id = request.getParameter("id");
		productService.deleteById(Integer.parseInt(id));
		result.returnSuccess();
		return result;
	}

	public String toAddProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("menu", 6);
		ProductCategoryParam params = new ProductCategoryParam();
		params.setType(1);
		List<ProductCategory> productCategoryList1 = productCategoryService
				.queryProductCategoryList(params);
		request.setAttribute("productCategoryList1", productCategoryList1);
		return "/backend/product/toAddProduct";
	}

	public void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		Product product = null;
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setSizeThreshold(1 * 1024 * 1024);
		fileItemFactory.setRepository(tmpDir);
		String fileName = null;
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		uploadHandler.setHeaderEncoding("utf-8");
		try {
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					params.put(item.getFieldName(), item.getString("utf-8"));
				} else {
					if (item.getSize() > 0) {
						fileName = StringUtils.randomUUID()
								+ item.getName().substring(
										item.getName().lastIndexOf("."));
						File file = new File(destinationDir, fileName);
						item.write(file);
					}
				}
			}
			product = copyToProduct(params);
			if (EmptyUtils.isNotEmpty(fileName)) {
				product.setFileName(fileName);
			}
			productService.saveOrUpdate(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()
				+ "/admin/product?action=index");
	}

	private Product copyToProduct(Map<String, String> params) {
		Product product = new Product();
		String id = params.get("id");
		String name = params.get("name");
		String description = params.get("description");
		String price = params.get("price");
		String stock = params.get("stock");
		String categoryLevel1Id = params.get("categoryLevel1Id");
		String categoryLevel2Id = params.get("categoryLevel2Id");
		String categoryLevel3Id = params.get("categoryLevel3Id");
		product.setName(name);
		product.setDescription(description);
		product.setPrice(Float.valueOf(price));
		product.setStock(Integer.parseInt(stock));
		product.setCategoryLevel1Id(EmptyUtils.isNotEmpty(categoryLevel1Id) ? Integer
				.parseInt(categoryLevel1Id) : 0);
		product.setCategoryLevel2Id(EmptyUtils.isNotEmpty(categoryLevel2Id) ? Integer
				.parseInt(categoryLevel2Id) : 0);
		product.setCategoryLevel3Id(EmptyUtils.isNotEmpty(categoryLevel3Id) ? Integer
				.parseInt(categoryLevel3Id) : 0);
		product.setId(EmptyUtils.isNotEmpty(id) ? Integer.parseInt(id) : null);
		product.setFileName(params.get("fileName"));
		product.setIsDelete(0);
		return product;
	}

	public String toUpdateProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Product product = productService.findById(id);
		request.setAttribute("menu", 6);
		if (EmptyUtils.isNotEmpty(id)) {
			request.setAttribute("menu", 5);
		}
		ProductCategoryParam params = new ProductCategoryParam();
		params.setType(1);
		List<ProductCategory> productCategoryList1 = productCategoryService
				.queryProductCategoryList(params);
		params.setType(2);
		List<ProductCategory> productCategoryList2 = productCategoryService
				.queryProductCategoryList(params);
		params.setType(3);
		List<ProductCategory> productCategoryList3 = productCategoryService
				.queryProductCategoryList(params);
		request.setAttribute("productCategoryList1", productCategoryList1);
		request.setAttribute("productCategoryList2", productCategoryList2);
		request.setAttribute("productCategoryList3", productCategoryList3);
		request.setAttribute("product", product);
		return "/backend/product/toAddProduct";
	}

	@Override
	public Class getServletClass() {
		return AdminProductServlet.class;
	}

}
