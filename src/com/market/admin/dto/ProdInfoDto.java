package com.market.admin.dto;

public class ProdInfoDto {
	private int pnum;
	private String unit;
	private String volume;
	private String origin;
	private String pack_type;
	private String shelf_life;
	private String guidance;
	public ProdInfoDto(int pnum, String unit, String volume, String origin, String pack_type, String shelf_life, String guidance) {
		this.pnum = pnum;
		this.unit = unit;
		this.volume = volume;
		this.origin = origin;
		this.pack_type = pack_type;
		this.shelf_life = shelf_life;
		this.guidance = guidance;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getPack_type() {
		return pack_type;
	}
	public void setPack_type(String pack_type) {
		this.pack_type = pack_type;
	}
	public String getShelf_life() {
		return shelf_life;
	}
	public void setShelf_life(String shelf_life) {
		this.shelf_life = shelf_life;
	}
	public String getGuidance() {
		return guidance;
	}
	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}
	
}
