package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDaoImpl;
import cn.easybuy.entity.Product;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.utils.EmptyUtils;



public class UserAddressDaoImpl extends BaseDaoImpl implements UserAddressDao {

	
	private UserAddress tableToClass(ResultSet rs) throws Exception {
		UserAddress userAddress=new UserAddress();
		userAddress.setId(rs.getInt("id"));
		userAddress.setUserId(rs.getInt("userId"));
		userAddress.setAddress(rs.getString("address"));
		userAddress.setCreateTime(rs.getDate("createTime"));
		userAddress.setIsDefault(rs.getInt("isDefault"));
		userAddress.setRemark(rs.getString("remark"));
		return userAddress;
	}
	
	public UserAddressDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<UserAddress> queryUserAddressList(Integer userId) {
		List<Object> paramsList=new ArrayList<Object>();
		List<UserAddress> userAddressList=new ArrayList<UserAddress>();
		StringBuffer sql=new StringBuffer("select id,userId,address,createTime,isDefault,remark from easybuy_user_address where 1=1 ");
		if (EmptyUtils.isEmpty(userId)) {
			sql.append(" and userId=? ");
			paramsList.add(userId);
		}
		ResultSet rs=this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			while(rs.next()){
				UserAddress userAddress=this.tableToClass(rs);
				userAddressList.add(userAddress);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(rs);
			this.closeResource();
		}
		return userAddressList;
	}

	@Override
	public Integer saveUserAddress(UserAddress userAddress) {
		return null;
	}

	@Override
	public UserAddress getUserAddressById(Integer addressId) {
		String sql = "select id,userId,address,createTime,isDefault,remark from easybuy_user_address where id=?";
		ResultSet resultSet = null;
		UserAddress userAddress = null;
		try {
			Object params[] = new Object[] { addressId };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				userAddress = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(resultSet);
			this.closeResource();
			return userAddress;
		}
	}
	
	
	
}
