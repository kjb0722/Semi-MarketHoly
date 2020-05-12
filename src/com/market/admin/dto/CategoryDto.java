package com.market.admin.dto;

public class CategoryDto {
	private int cnum;
	private int type;
	private String name;
	public CategoryDto(int cnum, int type, String name) {
		this.cnum = cnum;
		this.type = type;
		this.name = name;
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
	
}
