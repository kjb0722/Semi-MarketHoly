package com.market.admin.dto;

import java.sql.Date;

public class SaleDto {
	private int snum;
	private int pnum;
	private String name;
	private float percent;
	private Date startDate;
	private Date endDate;
	private String del_yn;
	public SaleDto(int snum, int pnum, String name, float percent, Date startDate, Date endDate, String del_yn) {
		this.snum = snum;
		this.pnum = pnum;
		this.name = name;
		this.percent = percent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.del_yn = del_yn;
	}
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

}
