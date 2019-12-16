package com.ibm.oauth2.recieveorder;

import java.util.List;

public class OrderDetails {

	
	public String orderId;
	public List<String> items;
	public OrderDetails() {
		super();
		
	}
	public OrderDetails(String orderId, List<String> items) {
		super();
		this.orderId = orderId;
		this.items = items;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}
	
	
}
