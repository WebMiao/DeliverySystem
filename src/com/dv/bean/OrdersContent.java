package com.dv.bean;

public class OrdersContent {
	private int id;
	private int orderid;
	private int foodid;
	private int amount;
	
	public OrdersContent() {

	}
	
	public int getOrderid() {
		return orderid;
	}
	
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public int getFoodid() {
		return foodid;
	}
	
	public void setFoodid(int foodid) {
		this.foodid = foodid;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
