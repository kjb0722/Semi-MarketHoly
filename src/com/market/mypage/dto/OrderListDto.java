package com.market.mypage.dto;

import java.sql.Date;

public class OrderListDto {
	
	private int status;
	private int pay_yn;
	private Date end_date;
	private String thum_save;
	private String name;
	private String description;
	private int price;
	private int ea;
	public OrderListDto(int status, int pay_yn, Date end_date, String thum_save, String name, String description,
			int price, int ea) {
		super();
		this.status = status;
		this.pay_yn = pay_yn;
		this.end_date = end_date;
		this.thum_save = thum_save;
		this.name = name;
		this.description = description;
		this.price = price;
		this.ea = ea;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPay_yn() {
		return pay_yn;
	}
	public void setPay_yn(int pay_yn) {
		this.pay_yn = pay_yn;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getThum_save() {
		return thum_save;
	}
	public void setThum_save(String thum_save) {
		this.thum_save = thum_save;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	
	
}
