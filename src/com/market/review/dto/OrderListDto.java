package com.market.review.dto;

import java.sql.Date;

public class OrderListDto {
	
	private int opnum;
	private Date end_date;
	private String thum_save;
	private String name;
	private String description;
	private int price;
	private int ea;
	
	public OrderListDto(int opnum, Date end_date, String thum_save, String name, String description, int price,
			int ea) {
		super();
		this.opnum = opnum;
		this.end_date = end_date;
		this.thum_save = thum_save;
		this.name = name;
		this.description = description;
		this.price = price;
		this.ea = ea;
	}
	
	public int getOpnum() {
		return opnum;
	}
	public void setOpnum(int opnum) {
		this.opnum = opnum;
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
