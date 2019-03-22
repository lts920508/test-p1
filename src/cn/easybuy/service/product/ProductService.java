package cn.easybuy.service.product;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.Product;
import cn.easybuy.params.ProductParams;

public interface ProductService {
	/**
	 * 根据条件查询商品列表
	 * @param params
	 * @return
	 */
	public List<Product> queryProductList(ProductParams params);
	/**
	 * 查询商品数量
	 * @param params
	 * @return
	 */
	public int queryProductCount(ProductParams params);
	public Product findById(String id);
	public void deleteById(Integer id);
	public void addProduct(Product product);
	public void modifyProduct(Product product);
	public void saveOrUpdate(Product product);
}
