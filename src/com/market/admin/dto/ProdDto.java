package com.market.admin.dto;

import java.sql.Date;

public class ProdDto {
	private int pnum;
	private int cnum;
	private int type;
	private String name;
	private int price;
	private int stock;
	private String description;
	private String thumb_org;
	private String thumb_save;
	private String detail_org;
	private String detail_save;
	private Date reg_date;
	private String del_yn;
	
	public ProdDto() {
		
	}
	public ProdDto(int pnum, int cnum, int type, String name, int price, int stock, String description,
			String thumb_org, String thumb_save, String detail_org, String detail_save, Date reg_date, String del_yn) {
		this.pnum = pnum;
		this.cnum = cnum;
		this.type = type;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.thumb_org = thumb_org;
		this.thumb_save = thumb_save;
		this.detail_org = detail_org;
		this.detail_save = detail_save;
		this.reg_date = reg_date;
		this.del_yn = del_yn;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumb_org() {
		return thumb_org;
	}
	public void setThumb_org(String thumb_org) {
		this.thumb_org = thumb_org;
	}
	public String getThumb_save() {
		return thumb_save;
	}
	public void setThumb_save(String thumb_save) {
		this.thumb_save = thumb_save;
	}
	public String getDetail_org() {
		return detail_org;
	}
	public void setDetail_org(String detail_org) {
		this.detail_org = detail_org;
	}
	public String getDetail_save() {
		return detail_save;
	}
	public void setDetail_save(String detail_save) {
		this.detail_save = detail_save;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
}
