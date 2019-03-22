package cn.easybuy.params;


import cn.easybuy.entity.Order;

public class OrderDetailParam extends Order {
	private Integer orderId;
	private String sort;
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
