package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.UserAddress;

public interface UserAddressService {
	public List<UserAddress> queryUserAddressList(Integer userId) throws Exception;

	public Integer addUserAddress(Integer id, String newAddress,
			String newRemark);
	public UserAddress getUserAddressById(Integer addressId);
}
