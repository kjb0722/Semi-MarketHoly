package com.market.admin.dto;

public class CategoryListDto {
	private int cnum;
	private String name;
	private int tnum;
	private String tname;

	public CategoryListDto(int cnum, String name, int tnum, String tname) {
		this.cnum = cnum;
		this.name = name;
		this.tnum = tnum;
		this.tname = tname;
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

	public int getTnum() {
		return tnum;
	}

	public void setTnum(int tnum) {
		this.tnum = tnum;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
}
