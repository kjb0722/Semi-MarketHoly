package com.market.mypage.dto;

import java.sql.Date;

public class OrderListDto {
	
	private int pnum;
	private int onum;
	private int opnum;
	private int status;
	private String pay_yn;
	private Date reg_date;
	private Date end_date;
	private String thum_save;
	private String name;
	private String description;
	private int price;
	private int ea;
	public OrderListDto(int pnum, int onum, int opnum, int status, String pay_yn, Date reg_date, Date end_date,
			String thum_save, String name, String description, int price, int ea) {
		super();
		this.pnum = pnum;
		this.onum = onum;
		this.opnum = opnum;
		this.status = status;
		this.pay_yn = pay_yn;
		this.reg_date = reg_date;
		this.end_date = end_date;
		this.thum_save = thum_save;
		this.name = name;
		this.description = description;
		this.price = price;
		this.ea = ea;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getOnum() {
		return onum;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public int getOpnum() {
		return opnum;
	}
	public void setOpnum(int opnum) {
		this.opnum = opnum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPay_yn() {
		return pay_yn;
	}
	public void setPay_yn(String pay_yn) {
		this.pay_yn = pay_yn;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
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
