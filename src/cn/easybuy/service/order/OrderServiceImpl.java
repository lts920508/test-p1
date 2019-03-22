package cn.easybuy.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.easybuy.dao.order.OrderDao;
import cn.easybuy.dao.order.OrderDaoImpl;
import cn.easybuy.dao.order.OrderDetailDao;
import cn.easybuy.dao.order.OrderDetailDaoImpl;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.User;
import cn.easybuy.params.OrderDetailParam;
import cn.easybuy.params.OrderParams;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ShoppingCart;
import cn.easybuy.utils.ShoppingCartItem;
import cn.easybuy.utils.StringUtils;

public class OrderServiceImpl implements OrderService {

	private Connection connection;
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private ProductDao productDao;
	@Override
	public Order payShoppingCart(User user, String address, ShoppingCart cart) {
		Order order=new Order();
		orderDao=new OrderDaoImpl(connection);
		try {
			connection=DataSourceUtil.openConnection();
			orderDao=new OrderDaoImpl(connection);
			orderDetailDao =new OrderDetailDaoImpl(connection);
			productDao =new ProductDaoImpl(connection);
			order.setUserId(user.getId());
			order.setLoginName(user.getLoginName());
			order.setUserAddress(address);
			order.setCost(cart.getTotalCost());
			order.setCreateTime(new Date());
			order.setSerialNumber(StringUtils.randomUUID());
			orderDao.saveOrder(order);
			for (ShoppingCartItem item:cart.getItems()) {
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setOrderId(order.getId());
				orderDetail.setCost(item.getCost());
				orderDetail.setProduct(item.getProduct());
				orderDetail.setQuantity(item.getQuantity());	
				orderDetailDao.saveOrderDetail(orderDetail, order.getId());
				productDao.updateStock(item.getProduct().getId(), (item.getProduct().getStock()-item.getQuantity()));
			}
		} catch (SQLException e) {
			order=null;
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return order;
	}
	@Override
	public Integer getOrderRowCount(OrderParams params) {
		OrderDao orderDao=null;
		int rtn=0;
		try {
			connection=DataSourceUtil.openConnection();
			orderDao=new OrderDaoImpl(connection);
			rtn=orderDao.queryOrderCount(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		return rtn;
	}
	@Override
	public List<Order> queryOrderList(Integer userId, Pager pager) {
		List<Order> orderlList=new ArrayList<Order>();
		Connection connection=null;
		try {
			connection=DataSourceUtil.openConnection();
			orderDao=new OrderDaoImpl(connection);
			OrderParams params=new OrderParams();
			params.openPage((pager.getCurrentPage()-1)*pager.getRowPerPage(), pager.getRowPerPage());
			params.setUserId(userId);
			params.setSort(" createTime desc ");
			orderlList=orderDao.queryOrderList(params);
			for (int i = 0; i < orderlList.size(); i++) {
				Order order=orderlList.get(i);
				OrderDetailParam orderDetailParam=new OrderDetailParam();
				orderDetailParam.setOrderId(order.getId());
				order.setOrderDetailList(queryOrderDetailList(orderDetailParam));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		
		return orderlList;
	}
	
	public List<OrderDetail> queryOrderDetailList(OrderDetailParam param) {
		List<OrderDetail> rtn=null;
		
		try {
			connection=DataSourceUtil.openConnection();
			orderDetailDao=new OrderDetailDaoImpl(connection);
			rtn=orderDetailDao.queryOrderDetailList(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DataSourceUtil.closeConnection(connection);
			}
		
		return rtn;
	}

}
