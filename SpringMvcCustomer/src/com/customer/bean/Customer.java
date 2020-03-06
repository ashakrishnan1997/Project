package com.customer.bean;

import java.util.Date;

public class Customer {
	// instance variable
	private Integer custId;
	private String custName;
	private String city;
	private String contactNumber;
	private Date orderDate;
	private Integer totalAmount;
	private String isActive;

	// default constructor
	public Customer() {

	}

	// parameterised constructor
	public Customer(Integer custId, String custName, String city,
			String contactNumber, Date orderDate, Integer totalAmount,
			String isActive) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.city = city;
		this.contactNumber = contactNumber;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.isActive = isActive;
	}

	// getters and setters

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
