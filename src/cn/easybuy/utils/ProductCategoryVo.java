package cn.easybuy.utils;

import java.util.List;

import cn.easybuy.entity.ProductCategory;

public class ProductCategoryVo {
	private ProductCategory productCategory;
	private List<ProductCategoryVo> productCategoryVoList;

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductCategoryVo> getProductCategoryVoList() {
		return productCategoryVoList;
	}

	public void setProductCategoryVoList(
			List<ProductCategoryVo> productCategoryVoList) {
		this.productCategoryVoList = productCategoryVoList;
	}

}
