package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.easybuy.dao.BaseDaoImpl;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.Product;
import cn.easybuy.params.OrderDetailParam;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.EmptyUtils;

public class OrderDetailDaoImpl extends BaseDaoImpl implements OrderDetailDao {

	public OrderDetailDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public void saveOrderDetail(OrderDetail detail, int orderId)
			throws SQLException {
		Integer id=0;
		String sql="insert into easybuy_order_detail(orderId,productId,quantity,cost) values(?,?,?,?)";
		try {
			Object[] params=new Object[]{detail.getOrderId(),detail.getProduct().getId(),detail.getQuantity(),detail.getCost()};
			id=this.executeInsert(sql, params);
			detail.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		this.closeResource();
		}
	}

	@Override
	public List<OrderDetail> queryOrderDetailList(OrderDetailParam param) throws Exception {
		List<OrderDetail> orderDetaillList=null;
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer("select id,orderId,productId,quantity,cost from easybuy_order_detail where 1=1 ");
		if (EmptyUtils.isNotEmpty(param.getOrderId())) {
			sql.append(" and orderId=? ");
			paramsList.add(param.getOrderId());
		}
		if(EmptyUtils.isNotEmpty(param.getSort())){
			sql.append(" order by "+param.getSort()+" ");
		}
		ResultSet rs=this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			orderDetaillList=new ArrayList<OrderDetail>();
			while (rs.next()) {
				OrderDetail orderDetail=this.tableToClass(rs);
				orderDetaillList.add(orderDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(rs);
			this.closeResource();
		}
		return orderDetaillList;
	}

	private OrderDetail tableToClass(ResultSet rs) throws SQLException {
		OrderDetail orderDetail=new OrderDetail();
		Product product=new Product();
		ProductService productService=new ProductServiceImpl();
		orderDetail.setId(rs.getInt("id"));
		orderDetail.setOrderId(rs.getInt("orderId"));
		int productId=(rs.getInt("productId"));
		product=productService.findById(productId+"");
		orderDetail.setProduct(product);
		orderDetail.setQuantity(rs.getInt("quantity"));
		orderDetail.setCost(rs.getFloat("cost"));
		return orderDetail;
	}
	
}
