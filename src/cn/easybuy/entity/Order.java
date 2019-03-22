package cn.easybuy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
	private Integer id;
	private Integer userId;
	private String loginName;
	private String userAddress;
	private Date createTime;
	private float cost;
	private String serialNumber;
	private List<OrderDetail> orderDetailList;

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList=orderDetailList;
	}

}
