package cn.easybuy.dao.product;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
/**
 * 查询所有商品分类的信息
 * @author 0
 * @params parentId
 * @return list
 */
public interface ProductCategoryDao {
	public List<ProductCategory> queryAllProductCategory(String parentId);
	
	public List<ProductCategory> queryProductCategoryList(ProductCategoryParam param);

	public int queryproductCategoryCount(ProductCategoryParam params);
	
	public void deleteById(Integer id) throws SQLException;
	
	public Integer save(ProductCategory productCategory);
	
	public ProductCategory getById(Integer id);

	public void modifyProductCategory(ProductCategory productCategory);
}
