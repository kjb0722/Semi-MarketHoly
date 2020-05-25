package com.market.pay.dto;

import java.sql.Date;

public class PayDto {
	private int onum; 
	private int num; 
	private int opnum; 
	private int status; 
	private String pay_yn;
	private Date reg_date;
	private Date end_date;
	private String id;
	private int price;
	private int use_point;
	private int sale_price;
	private int pay_way;
	private int pnum;
	private int EA;
	private String addr;
	private String pname;
	
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getOnum() {
		return onum;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getUse_point() {
		return use_point;
	}
	public void setUse_point(int use_point) {
		this.use_point = use_point;
	}
	public int getSale_price() {
		return sale_price;
	}
	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}
	public int getPay_way() {
		return pay_way;
	}
	public void setPay_way(int pay_way) {
		this.pay_way = pay_way;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getEA() {
		return EA;
	}
	public void setEA(int eA) {
		EA = eA;
	}
	
	public PayDto(int onum, int num, int status, String pay_yn, Date reg_date, Date end_date,
			String id,int price, int use_point, int sale_price,
			int pay_way,String addr) {
		
		super();
		this.onum = onum;
		this.num = num;
		this.status = status;
		this.pay_yn = pay_yn;
		this.reg_date = reg_date;
		this.end_date = end_date;
		this.id = id;
		this.price=price;
		this.use_point = use_point;
		this.sale_price = sale_price;
		this.pay_way = pay_way;
		this.addr = addr;
	}
	
	public PayDto(int opnum,int pnum,String pname, int EA,int price) {
		super();
		this.opnum = opnum;
		this.pnum = pnum;
		this.EA = EA;
		this.pname = pname;
		this.price = price;
		

	}
}