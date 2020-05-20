package com.market.admin.dto;

import java.sql.Date;

public class QnaAdminDto {
	private int qnum;
	private String cname;
	private String pname;
	private String title;
	private String name;
	private Date reg_date;
	private String content;
	private int pnum;
	private int level;
	public QnaAdminDto(int qnum, String cname, String pname, String title, String name, Date reg_date, String content,
			int pnum, int level) {
		this.qnum = qnum;
		this.cname = cname;
		this.pname = pname;
		this.title = title;
		this.name = name;
		this.reg_date = reg_date;
		this.content = content;
		this.pnum = pnum;
		this.level = level;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
