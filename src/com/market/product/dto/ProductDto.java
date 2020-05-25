package com.market.product.dto;

import java.sql.Date;

public class ProductDto {
	int pnum;
	int cnum;
	String name;
	Date reg_date;
	int price;
	int stock;
	int type;
	String thumb_org;
	String thumb_save;
	String description;
	String detail_org;
	String detail_save;
	String del_yn;
	float percent;
	public ProductDto() {}
	public ProductDto(int pnum, int cnum, String name, Date reg_date, int price, int stock, int type, String thumb_org,
			String thumb_save, String description, String detail_org, String detail_save, String del_yn) {
		super();
		this.pnum = pnum;
		this.cnum = cnum;
		this.name = name;
		this.reg_date = reg_date;
		this.price = price;
		this.stock = stock;
		this.type = type;
		this.thumb_org = thumb_org;
		this.thumb_save = thumb_save;
		this.description = description;
		this.detail_org = detail_org;
		this.detail_save = detail_save;
		this.del_yn = del_yn;
	}
	public ProductDto(int pnum, String name, Date reg_date, int price, int stock, String thumb_org,
			String thumb_save, String description, String del_yn) {
		super();
		this.pnum = pnum;
		this.name = name;
		this.reg_date = reg_date;
		this.price = price;
		this.stock = stock;
		this.thumb_org = thumb_org;
		this.thumb_save = thumb_save;
		this.description = description;
		this.del_yn = del_yn;
		
	}
	public ProductDto(int pnum, String name, Date reg_date, int price, int stock, String thumb_save,
			String description, float percent) {
		super();
		this.pnum = pnum;
		this.name = name;
		this.reg_date = reg_date;
		this.price = price;
		this.stock = stock;
		this.thumb_save = thumb_save;
		this.description = description;
		this.percent = percent;
	}
	
	public ProductDto(int pnum, int cnum, String name, Date reg_date, int price, int stock, int type,
			String thumb_org, String thumb_save, String description, String detail_org, String detail_save, String del_yn,
			float percent) {
		super();
		this.pnum = pnum;
		this.cnum = cnum;
		this.name = name;
		this.reg_date = reg_date;
		this.price = price;
		this.stock = stock;
		this.type = type;
		this.thumb_org = thumb_org;
		this.thumb_save = thumb_save;
		this.description = description;
		this.detail_org = detail_org;
		this.detail_save = detail_save;
		this.del_yn = del_yn;
		this.percent = percent;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
}