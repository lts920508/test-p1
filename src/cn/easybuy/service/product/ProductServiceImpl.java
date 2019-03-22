package cn.easybuy.service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.Product;
import cn.easybuy.params.ProductParams;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.EmptyUtils;

public class ProductServiceImpl implements ProductService {

	private Connection connection;
	private ProductDao pDao;
	
	@Override
	public List<Product> queryProductList(ProductParams params) {
		List<Product> pList=new ArrayList<Product>();
		try {
			connection=DataSourceUtil.openConnection();
			pDao=new ProductDaoImpl(connection);
			pList=pDao.queryProductList(params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			DataSourceUtil.closeConnection(connection);
		}
		return pList;
	}

	@Override
	public int queryProductCount(ProductParams params) {
		Integer num=0;
		try {
			connection=DataSourceUtil.openConnection();
			pDao=new ProductDaoImpl(connection);
			num=pDao.queryProductCount(params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally{
			DataSourceUtil.closeConnection(connection);
		}
		return num;
	}
	
	public Product findById(String id) {
		Connection connection=null;
		Product product=null;
		try {
			connection=DataSourceUtil.openConnection();
			ProductDao productDao=new ProductDaoImpl(connection);
			product=productDao.getProductById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DataSourceUtil.closeConnection(connection);
		}
		return product;
	}

	@Override
	public void deleteById(Integer id) {
		ProductDao productDao=null;
		try {
			connection=DataSourceUtil.openConnection();
			productDao=new ProductDaoImpl(connection);
			productDao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}

	@Override
	public void addProduct(Product product) {
		try {
			connection=DataSourceUtil.openConnection();
			ProductDao productDao=new ProductDaoImpl(connection);
			productDao.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}

	@Override
	public void modifyProduct(Product product) {
		try {
			connection=DataSourceUtil.openConnection();
			ProductDao productDao=new ProductDaoImpl(connection);
			productDao.modifyProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}

	@Override
	public void saveOrUpdate(Product product) {
		try {
			connection=DataSourceUtil.openConnection();
			ProductDao productDao=new ProductDaoImpl(connection);
			ProductParams params=new ProductParams();
			if (EmptyUtils.isEmpty(product.getId())) {
				productDao.addProduct(product);
			}else {
				productDao.modifyProduct(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}

}
