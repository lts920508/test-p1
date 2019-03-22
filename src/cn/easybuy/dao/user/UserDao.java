package cn.easybuy.dao.user;

import java.sql.SQLException;
import java.util.List;

import cn.easybuy.entity.User;
import cn.easybuy.params.UserParams;
public interface UserDao {
	/**
	 * 根据用户的账号获取用户的记录	
	 * @author 0
	 * @params
	 * @return
	 */
	public User getUserByLoginName(String loginName) throws Exception;
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public int save(User user);

	public List<User> getAllUser(UserParams params) throws Exception;

	public int queryUserCount(UserParams params);

	public void deleteUser(int id) throws SQLException;

	public User getUserById(int id) throws Exception;

	public int modifyUser(User user) throws Exception;
}
