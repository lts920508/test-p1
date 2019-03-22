package cn.easybuy.service.product;

import java.util.List;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.utils.ProductCategoryVo;

/**
 * 根据parentid查询所有商品分类的信息
 * @author 0
 * @params parentId
 * @return list
 */
public interface ProductCategoryService {
	public List<ProductCategory> queryAllProductCategory(String parentId);
	/**
	 * 查询所有商品分类的信息
	 * @author 0
	 * @params parentId
	 * @return list
	 */
	public List<ProductCategoryVo> queryAllProductCategory();
	public List<ProductCategory> queryProductCategoryList(ProductCategoryParam param);
	public int queryproductCategoryCount(ProductCategoryParam params);
	public void deleteById(Integer id);
	public void addProductCategory(ProductCategory productCategory);
	public ProductCategory getById(Integer id);
	public void modifyProductCategory(ProductCategory productCategory);
}
