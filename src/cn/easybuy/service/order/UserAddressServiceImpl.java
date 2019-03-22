package cn.easybuy.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.easybuy.dao.order.UserAddressDao;
import cn.easybuy.dao.order.UserAddressDaoImpl;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.Product;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.utils.DataSourceUtil;

public class UserAddressServiceImpl implements UserAddressService {

	@Override
	public List<UserAddress> queryUserAddressList(Integer userId)
			throws Exception {
		Connection connection = null;
		List<UserAddress> userAddresslList = null;
		try {
			connection = DataSourceUtil.openConnection();
			UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
			userAddresslList = userAddressDao.queryUserAddressList(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return userAddresslList;
	}

	@Override
	public Integer addUserAddress(Integer id, String newAddress,
			String newRemark) {
		Connection connection = null;
		Integer userAddressId = null;

		try {
			connection = DataSourceUtil.openConnection();
			UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(id);
			userAddress.setAddress(newAddress);
			userAddress.setRemark(newRemark);
			userAddressId = userAddressDao.saveUserAddress(userAddress);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userAddressId;

	}

	@Override
	public UserAddress getUserAddressById(Integer addressId) {
		Connection connection = null;
		UserAddress userAddress = null;
		try {
			connection = DataSourceUtil.openConnection();
			UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
			userAddress = userAddressDao.getUserAddressById(addressId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return userAddress;
	}
}
