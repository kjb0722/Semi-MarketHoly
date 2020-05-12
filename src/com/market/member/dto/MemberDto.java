package com.market.member.dto;

import java.sql.Date;

public class MemberDto  {
	private int num;
	private String id;
	private String pwd;
	private String name;
	private int rating;
	private String email;
	private String birth;
	private String phone;
	private int gender;
	private String addr;
	private Date reg_date;
	private int point;
	private String del_yn;
	private Date del_date;
	  
	public MemberDto() {}
	
	public MemberDto(int num, String id, String pwd, String name, int rating, String email, String birth, String phone,
			int gender, String addr, Date reg_date, int point, String del_yn, Date del_date) {
		super();
		this.num = num;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.rating = rating;
		this.email = email;
		this.birth = birth;
		this.phone = phone;
		this.gender = gender;
		this.addr = addr;
		this.reg_date = reg_date;
		this.point = point;
		this.del_yn = del_yn;
		this.del_date = del_date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public Date getDel_date() {
		return del_date;
	}
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	
	
	
	
	
}
