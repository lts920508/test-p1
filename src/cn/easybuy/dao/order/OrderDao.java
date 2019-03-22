package cn.easybuy.dao.order;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.Order;
import cn.easybuy.params.OrderParams;

public interface OrderDao {
	public void saveOrder(Order order);	
	public List<Order> queryOrderList(OrderParams params) throws SQLException;
	public Integer queryOrderCount(OrderParams params);
}
