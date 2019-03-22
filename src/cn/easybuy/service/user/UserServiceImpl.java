package cn.easybuy.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import cn.easybuy.dao.order.OrderDao;
import cn.easybuy.dao.order.OrderDaoImpl;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.entity.User;
import cn.easybuy.params.UserParams;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class UserServiceImpl implements UserService {
	private Connection connection;
	private UserDao uDao;

	@Override
	public User getUserByLoginName(String loginName) {
		User user =null;
		try {
			connection=DataSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			user=uDao.getUserByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DataSourceUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean save(User user) {
		boolean flag=true;
		try {
			connection=DataSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			int count=uDao.save(user);
			flag=count>0;
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		return flag;
	}

	@Override
	public List<User> getAllUser(Pager pager) {
		List<User> userList =null;
		try {
			connection=DataSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			UserParams params=new UserParams();
			params.openPage((pager.getCurrentPage()-1)*pager.getRowPerPage(), pager.getRowPerPage());
			userList=uDao.getAllUser(params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DataSourceUtil.closeConnection(connection);
		}
		return userList;
	}

	@Override
	public int getUserRowCount(UserParams params) {
		int rtn=0;
		try {
			connection=DataSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			rtn=uDao.queryUserCount(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		return rtn;
	}

	@Override
	public void deleteUser(int id) {
		try {
			connection=DataSourceUtil.openConnection();
			UserDao userDao=new UserDaoImpl(connection);
			userDao.deleteUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
	}

	@Override
	public User getUserById(int id) {
		User user=null;
		try {
			connection=DataSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			user=uDao.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean modifyUser(User user) {
		boolean flag=true;
		try {
			connection=DataSourceUtil.openConnection();
			uDao=new UserDaoImpl(connection);
			int count=uDao.modifyUser(user);
			flag=count>0;
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeConnection(connection);
		}
		return flag;
	}

}
