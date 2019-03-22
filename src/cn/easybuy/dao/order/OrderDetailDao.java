package cn.easybuy.dao.order;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.OrderDetail;
import cn.easybuy.params.OrderDetailParam;

public interface OrderDetailDao {
	public void saveOrderDetail(OrderDetail detail,int orderId) throws SQLException;

	public List<OrderDetail> queryOrderDetailList(OrderDetailParam param) throws Exception;
}
