package com.market.review.dto;

public class ReviewNumbersDto {
	private int onum;
	private int pnum;
	
	public ReviewNumbersDto() {}

	public ReviewNumbersDto(int onum, int pnum) {
		super();
		this.onum = onum;
		this.pnum = pnum;
	}

	public int getOnum() {
		return onum;
	}

	public void setOnum(int onum) {
		this.onum = onum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	
	
	
	
}
