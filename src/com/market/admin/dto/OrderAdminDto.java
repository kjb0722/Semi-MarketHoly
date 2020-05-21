package com.market.admin.dto;

import java.sql.Date;

public class OrderAdminDto {
	private String onum;
	private String id;
	private String statusName;
	private String prodName;
	private String pay_yn;
	private int price;
	private String addr;
	private String pay_wayName;
	private int use_point;
	private Date reg_date;
	public OrderAdminDto(String onum, String id, String statusName, String prodName, String pay_yn, int price,
			String addr, String pay_wayName, int use_point, Date reg_date) {
		this.onum = onum;
		this.id = id;
		this.statusName = statusName;
		this.prodName = prodName;
		this.pay_yn = pay_yn;
		this.price = price;
		this.addr = addr;
		this.pay_wayName = pay_wayName;
		this.use_point = use_point;
		this.reg_date = reg_date;
	}
	public String getOnum() {
		return onum;
	}
	public void setOnum(String onum) {
		this.onum = onum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getPay_yn() {
		return pay_yn;
	}
	public void setPay_yn(String pay_yn) {
		this.pay_yn = pay_yn;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPay_wayName() {
		return pay_wayName;
	}
	public void setPay_wayName(String pay_wayName) {
		this.pay_wayName = pay_wayName;
	}
	public int getUse_point() {
		return use_point;
	}
	public void setUse_point(int use_point) {
		this.use_point = use_point;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}