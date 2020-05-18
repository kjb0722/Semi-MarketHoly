package com.market.order.dto;

import java.sql.Date;

public class OrdersDto {
	private int pnum;
	private int cnum;
	private int cartnum;
	private String name;
	private Date reg_date;
	private int price;
	private int stock;
	private int type;
	private String thumb_org;
	private String thumb_save;
	private String description;
	private String detail_org;
	private String detail_save;
	private String del_yn;
	private String id;
	private int EA;
	private int percent;

	
	
	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public OrdersDto(String thumb_save,int cartnum,String id,int pnum,int EA,String name,int percent) {
		//주문 상세 장바구니,상품 테이블
	}
	
	public OrdersDto(String thumb_save, int cartnum, String id, int pnum, int EA, String name, int price, int percent) {
		
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

	public int getCartnum() {
		return cartnum;
	}

	public void setCartnum(int cartnum) {
		this.cartnum = cartnum;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEA() {
		return EA;
	}

	public void setEA(int eA) {
		EA = eA;
	}

	public OrdersDto(int pnum, int cnum, int cartnum, String name, Date reg_date, int price, int stock, int type,
			String thumb_org, String thumb_save, String description, String detail_org, String detail_save,
			String del_yn, String id, int eA) {
		super();
		this.pnum = pnum;
		this.cnum = cnum;
		this.cartnum = cartnum;
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
		this.id = id;
		EA = eA;
	}
	
	

}