package cn.easybuy.utils;

import java.io.Serializable;

import cn.easybuy.entity.Product;

public class ShoppingCartItem implements Serializable {
	private Product product;
	private int quantity;
	private float cost;

	public ShoppingCartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.cost=this.cost+product.getPrice()*quantity;
	}
	
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
