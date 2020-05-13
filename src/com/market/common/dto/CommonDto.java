package com.market.common.dto;

public class CommonDto {
	private String type;
	private int conum;
	private String name;
	private String val;
	public CommonDto(String type, int conum, String name, String val) {
		this.type = type;
		this.conum = conum;
		this.name = name;
		this.val = val;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getConum() {
		return conum;
	}
	public void setConum(int conum) {
		this.conum = conum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
}
