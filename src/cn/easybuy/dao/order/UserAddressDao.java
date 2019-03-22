package cn.easybuy.dao.order;

import java.util.List;

import cn.easybuy.entity.UserAddress;

public interface UserAddressDao {
	public List<UserAddress> queryUserAddressList(Integer userId);
	
	public Integer saveUserAddress(UserAddress userAddress);
	
	public UserAddress getUserAddressById(Integer addressId);
}
