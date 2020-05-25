package com.market.mypage.dto;

public class MypagePointDto {
	private String id;
	private String name;
	private int point;
	private int rating;
	
	public MypagePointDto(String id, String name, int point, int rating) {
		super();
		this.id = id;
		this.name = name;
		this.point = point;
		this.rating = rating;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
