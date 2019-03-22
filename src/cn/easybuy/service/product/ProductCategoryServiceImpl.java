package cn.easybuy.service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.easybuy.dao.product.ProductCategoryDao;
import cn.easybuy.dao.product.ProductCategoryDaoImpl;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.ProductCategoryVo;

public class ProductCategoryServiceImpl implements ProductCategoryService {
	private Connection connection;
	private ProductCategoryDao pcDao;

	@Override
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		List<ProductCategory> pcList = new ArrayList<ProductCategory>();
		try {
			connection = DataSourceUtil.openConnection();
			pcDao = new ProductCategoryDaoImpl(connection);
			if (null == parentId || "".equals(parentId)) {
				parentId = "0";
			}
			pcList = pcDao.queryAllProductCategory(parentId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return pcList;
	}

	@Override
	public List<ProductCategoryVo> queryAllProductCategory() {
		//查寻一级分类VO列表
		List<ProductCategoryVo> pc1VoList=new ArrayList<ProductCategoryVo>();
		//查询一级分类
		List<ProductCategory> pcList=queryAllProductCategory(null);
		//查询二级分类
		for (ProductCategory productCategory1:pcList) {
			ProductCategoryVo pc1Vo=new ProductCategoryVo();
			pc1Vo.setProductCategory(productCategory1);
			//查询二级分裂的Vo列表
			List<ProductCategoryVo> pc2VoList=new ArrayList<ProductCategoryVo>();
			List<ProductCategory> pc2List=queryAllProductCategory(productCategory1.getId().toString());
			for (ProductCategory productCategory2:pc2List) {
				ProductCategoryVo pc2Vo=new ProductCategoryVo();
				pc2Vo.setProductCategory(productCategory2);
				List<ProductCategoryVo> pc3VoList=new ArrayList<ProductCategoryVo>();
				List<ProductCategory> pc3List=queryAllProductCategory(productCategory2.getId().toString());
				for (ProductCategory productCategory3:pc3List) {
					ProductCategoryVo pc3Vo=new ProductCategoryVo();
					pc3Vo.setProductCategory(productCategory3);
					pc3VoList.add(pc3Vo);
				}
				pc2Vo.setProductCategoryVoList(pc3VoList);
				pc2VoList.add(pc2Vo);
			}
			pc1Vo.setProductCategoryVoList(pc2VoList);
			pc1VoList.add(pc1Vo);
		}
		return pc1VoList;
	}

	@Override
	public List<ProductCategory> queryProductCategoryList(
			ProductCategoryParam param) {
		List<ProductCategory> rtn=null;
		try {
			connection=DataSourceUtil.openConnection();
			ProductCategoryDao productCategoryDao=new ProductCategoryDaoImpl(connection);
			rtn=productCategoryDao.queryProductCategoryList(param);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		
		return rtn;
	}

	@Override
	public int queryproductCategoryCount(ProductCategoryParam params) {
		int count=0;
		ProductCategoryDao productCategoryDao=null;
		try {
			connection=DataSourceUtil.openConnection();
			productCategoryDao=new ProductCategoryDaoImpl(connection);
			count=productCategoryDao.queryproductCategoryCount(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		return count;
	}

	@Override
	public void deleteById(Integer id) {
		ProductCategoryDao productCategoryDao=null;
		try {
			connection=DataSourceUtil.openConnection();
			productCategoryDao=new ProductCategoryDaoImpl(connection);
			productCategoryDao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}

	@Override
	public void addProductCategory(ProductCategory productCategory) {
		try {
			connection=DataSourceUtil.openConnection();
			ProductCategoryDao productCategoryDao=new ProductCategoryDaoImpl(connection);
			productCategoryDao.save(productCategory);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}
	
	public ProductCategory getById(Integer id){
		ProductCategory productCategory=null;
		try {
			connection=DataSourceUtil.openConnection();
			ProductCategoryDao productCategoryDao=new ProductCategoryDaoImpl(connection);
			productCategory = productCategoryDao.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		return productCategory;
	}

	@Override
	public void modifyProductCategory(ProductCategory productCategory) {
		try {
			connection=DataSourceUtil.openConnection();
			ProductCategoryDao productCategoryDao=new ProductCategoryDaoImpl(connection);
			productCategoryDao.modifyProductCategory(productCategory);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}
	
}
