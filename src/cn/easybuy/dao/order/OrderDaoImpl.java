package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.easybuy.dao.BaseDaoImpl;
import cn.easybuy.entity.Order;
import cn.easybuy.entity.User;
import cn.easybuy.params.OrderParams;
import cn.easybuy.utils.EmptyUtils;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

	public OrderDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public void saveOrder(Order order) {
		Integer id = 0;
		String sql = "insert into easybuy_order(userId,loginName,userAddress,createTime,cost,serialNumber) values(?,?,?,?,?,?)";
		Object[] params = new Object[] { order.getUserId(),
				order.getLoginName(), order.getUserAddress(),
		order.getCreateTime(), order.getCost(), order.getSerialNumber() };
		try {
			id = this.executeInsert(sql, params);
			order.setId(new Integer(id).intValue());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
	}

	@Override
	public List<Order> queryOrderList(OrderParams params) throws SQLException {
		List<Object> paramsList=new ArrayList<Object>();
		List<Order> orderlList=new ArrayList<Order>();
		StringBuffer sql=new StringBuffer("select id,userId,loginName,userAddress,createTime,cost,serialNumber from easybuy_order where 1=1 ");
		if (EmptyUtils.isNotEmpty(params.getUserId())) {
			sql.append(" and userId=? ");
			paramsList.add(params.getUserId());
		}
		if (EmptyUtils.isNotEmpty(params.getSort())) {
			sql.append(" order by "+params.getSort()+" ");
		}
		if(params.isPage()){
			sql.append(" limit "+params.getStartIndex()+","+params.getPageSize());
		}
		ResultSet rs=this.executeQuery(sql.toString(), paramsList.toArray());
			Order order=null;
				try {
					while(rs.wasNull()){
						return orderlList;
					}
					while (rs.next()) {
					order = this.tableToClass(rs);
					orderlList.add(order);
					}
				} catch (Exception e) {
				}finally{
					this.closeResource(rs);
					this.closeResource();
				}
		return orderlList;
	}

	private Order tableToClass(ResultSet rs) throws SQLException {
		Order order=new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("userId"));
		order.setLoginName(rs.getString("loginName"));
		order.setUserAddress(rs.getString("userAddress"));
		order.setCreateTime(rs.getDate("createTime"));
		order.setCost(rs.getFloat("cost"));
		order.setSerialNumber(rs.getString("serialNumber"));
		
		return order;
	}

	@Override
	public Integer queryOrderCount(OrderParams params) {
		List<Object> paramsList=new ArrayList<Object>();
		Integer count=0;
		StringBuffer sql=new StringBuffer("select count(id) count from easybuy_order where 1=1 ");
		if (EmptyUtils.isNotEmpty(params.getUserId())) {
			sql.append(" and userId=?");
			paramsList.add(params.getUserId());
		}
		ResultSet rs=this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			while (rs.next()) {
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeResource(rs);
			this.closeResource();
		}
		return count;
	}

}
