package com.market.admin.dto;

public class OrdDetailDto {
	private int opnum;
	private String thumb_save;
	private String pname;
	private int ea;
	private int price;
	public OrdDetailDto(int opnum, String thumb_save, String pname, int ea, int price) {
		this.opnum = opnum;
		this.thumb_save = thumb_save;
		this.pname = pname;
		this.ea = ea;
		this.price = price;
	}
	public int getOpnum() {
		return opnum;
	}
	public void setOpnum(int opnum) {
		this.opnum = opnum;
	}
	public String getThumb_save() {
		return thumb_save;
	}
	public void setThumb_save(String thumb_save) {
		this.thumb_save = thumb_save;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
