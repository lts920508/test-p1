package cn.easybuy.utils;

import java.util.ArrayList;
import java.util.List;

import cn.easybuy.entity.Product;

public class ShoppingCart {
	private List<ShoppingCartItem> items=new ArrayList<ShoppingCartItem>();
	private double sum;

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public ReturnResult addItem(Product product, Integer quantity) {
		boolean flag=false;
		ReturnResult result = new ReturnResult();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getId().equals(product.getId())) {
				if (items.get(i).getQuantity() + quantity > product.getStock()) {
					return result.returnFail("商品数量不足");
				}else{
					items.get(i).setQuantity(items.get(i).getQuantity()+quantity);
					items.get(i).setCost(items.get(i).getProduct().getPrice()*quantity+items.get(i).getCost());
					flag=true;
				}
			}
		}
		if (!flag) {
			items.add(new ShoppingCartItem(product, quantity));
		}
		return result.returnSuccess();
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	public void modifyQuantity(int index,Integer quantity) {
		items.get(index).setQuantity(quantity);
	}
	
	public float getTotalCost(){
		float sum=0;
		for (ShoppingCartItem item:items) {
			sum=sum+item.getCost();
		}
		return sum;
	}
	
}
