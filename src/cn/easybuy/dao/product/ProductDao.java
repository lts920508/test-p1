package cn.easybuy.dao.product;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.Product;
import cn.easybuy.params.ProductParams;

public interface ProductDao {
	/**
	 * 根据条件查询商品列表
	 * @param params
	 * @return
	 */
	public List<Product> queryProductList(ProductParams params)  throws SQLException;
	/**
	 * 查询商品数量
	 * @param params
	 * @return
	 */
	public int queryProductCount(ProductParams params)  throws SQLException;
	public Product getProductById(Integer id) throws SQLException;
	public void updateStock(Integer id,Integer quantity) throws SQLException;
	public void deleteById(Integer id) throws SQLException;
	public void addProduct(Product product) throws SQLException;
	public void modifyProduct(Product product) throws SQLException;
}
