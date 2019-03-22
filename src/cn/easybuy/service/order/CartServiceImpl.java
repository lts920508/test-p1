package cn.easybuy.service.order;

import cn.easybuy.utils.ShoppingCart;
import cn.easybuy.utils.ShoppingCartItem;

public class CartServiceImpl implements CartService {

	@Override
	public ShoppingCart modifyShoppingCart(String productId, Integer quantity,
			ShoppingCart cart) throws Exception {
		for (ShoppingCartItem item:cart.getItems()) {
			if (item.getProduct().getId().toString().equals(productId)) {
				if (quantity==0||quantity<0) {
					cart.getItems().remove(item);
					break;
				}else {
					item.setQuantity(quantity);
				}
			}
		}
		cart=calculate(cart);
		return cart;
	}

	@Override
	public ShoppingCart calculate(ShoppingCart cart) throws Exception {
		double sum=0.0;
		for (ShoppingCartItem item:cart.getItems()) {
			sum+=item.getQuantity()*item.getProduct().getPrice();
			item.setCost(item.getQuantity()*item.getProduct().getPrice());
		}
		cart.setSum(sum);
		return cart;
	}

}
