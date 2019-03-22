package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.User;
import cn.easybuy.params.OrderDetailParam;
import cn.easybuy.params.OrderParams;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ShoppingCart;

public interface OrderService {
	public Order payShoppingCart(User user,String address,ShoppingCart cart);
	public Integer getOrderRowCount(OrderParams params);
	public List<Order> queryOrderList(Integer userId,Pager pager);
	public List<OrderDetail> queryOrderDetailList(OrderDetailParam param);
}
