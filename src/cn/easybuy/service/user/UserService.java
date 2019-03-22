package cn.easybuy.service.user;

import java.util.List;

import cn.easybuy.entity.User;
import cn.easybuy.params.UserParams;
import cn.easybuy.utils.Pager;

public interface UserService {
	public User getUserByLoginName(String loginName);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public boolean save(User user);
	public List<User> getAllUser(Pager pager);
	public int getUserRowCount(UserParams params);
	public void deleteUser(int id);
	public User getUserById(int id);
	public boolean modifyUser(User user);
}
