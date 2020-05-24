package com.market.admin.dto;

public class StatGenderDto {
	private String gender;
	private int cnt;
	public StatGenderDto(String gender, int cnt) {
		this.gender = gender;
		this.cnt = cnt;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
