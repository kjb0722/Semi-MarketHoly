package com.market.admin.dto;

import java.sql.Date;

public class SaleProdListDto {
	private int pnum;
	private int cnum;
	private String name;
	private Date reg_date;
	private int price;
	private int stock;
	private int type;
	private String thumb_save;
	private String onSaleName;

	public SaleProdListDto(int pnum, int cnum, String name, Date reg_date, int price, int stock, int type,
			String thumb_save, String onSaleName) {
		this.pnum = pnum;
		this.cnum = cnum;
		this.name = name;
		this.reg_date = reg_date;
		this.price = price;
		this.stock = stock;
		this.type = type;
		this.thumb_save = thumb_save;
		this.onSaleName = onSaleName;
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

	public String getThumb_save() {
		return thumb_save;
	}

	public void setThumb_save(String thumb_save) {
		this.thumb_save = thumb_save;
	}

	public String getOnSaleName() {
		return onSaleName;
	}

	public void setOnSaleName(String onSaleName) {
		this.onSaleName = onSaleName;
	}
}
