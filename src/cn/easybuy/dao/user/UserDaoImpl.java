package cn.easybuy.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDaoImpl;
import cn.easybuy.entity.User;
import cn.easybuy.params.UserParams;
import cn.easybuy.utils.EmptyUtils;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	
	public UserDaoImpl(Connection connection) {
		super(connection);
	}
	
	public User tableToClass(ResultSet rs) throws Exception{
		User user =new User();
		user.setLoginName(rs.getString("loginName"));
		user.setUserName(rs.getString("userName"));
		user.setPassword(rs.getString("password"));
		user.setSex(rs.getInt("sex"));
		user.setIdentityCode(rs.getString("identityCode"));
		user.setEmail(rs.getString("email"));
		user.setMobile(rs.getString("mobile"));
		user.setType(rs.getInt("type"));
		user.setId(rs.getInt("id"));
		return user;
	}
	@Override
	public User getUserByLoginName(String loginName) throws Exception {
		StringBuffer sql=new StringBuffer("select * from easybuy_user where 1=1");
		List<Object> params= new ArrayList<Object>();
		if (!EmptyUtils.isEmpty(loginName)) {
			sql.append(" and loginName=?");
			params.add(loginName);
		}
		ResultSet rs=executeQuery(sql.toString(), params.toArray());
		User user =null;
		while(rs.next()){
			user=tableToClass(rs);
		}
		return user;
	}

	@Override
	public int save(User user) {
		Integer id=0;
		StringBuffer sql=new StringBuffer("INSERT into easybuy_user(loginName,userName,password,sex,identityCode,email,mobile,type) values(?,?,?,?,?,?,?,?)");
		Object[] params=new Object[]{user.getLoginName(),user.getUserName(),user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType()};
		id=this.executeInsert(sql.toString(), params);
		return id;
	}

	@Override
	public List<User> getAllUser(UserParams params) throws Exception {
		List<User> userList=new ArrayList<User>();
		StringBuffer sql=new StringBuffer("select * from easybuy_user ");
		List<Object> param= new ArrayList<Object>();
		if(params.isPage()){
			sql.append(" limit "+params.getStartIndex()+","+params.getPageSize());
		}
		ResultSet rs=executeQuery(sql.toString(), param.toArray());
		User user =null;
		while(rs.next()){
			user=tableToClass(rs);
			userList.add(user);
		}
		return userList;
	}

	@Override
	public int queryUserCount(UserParams params) {
		List<Object> paramsList=new ArrayList<Object>();
		Integer count=0;
		StringBuffer sql=new StringBuffer("select count(*) count from easybuy_user ");
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

	@Override
	public void deleteUser(int id) throws SQLException {
		String sql="delete from easybuy_user where id=? ";
		Object params[]=new Object[]{ id };
		this.executeUpdate(sql, params);
		this.closeResource();
	}

	@Override
	public User getUserById(int id) throws Exception {
		StringBuffer sql=new StringBuffer("select * from easybuy_user where 1=1");
		List<Object> params= new ArrayList<Object>();
		if (!EmptyUtils.isEmpty(id)) {
			sql.append(" and id=?");
			params.add(id);
		}
		ResultSet rs=executeQuery(sql.toString(), params.toArray());
		User user =null;
		while(rs.next()){
			user=tableToClass(rs);
		}
		return user;
	}

	@Override
	public int modifyUser(User user) throws Exception {
		StringBuffer sql=new StringBuffer("update easybuy_user set loginName=?,userName=?,identityCode=?,email=?,mobile=?,type=? where id=? ");
		Object[] params=new Object[]{user.getLoginName(),user.getUserName(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType(),user.getId()};
		this.executeUpdate(sql.toString(), params);
		return 1;
	}

}
