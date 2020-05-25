package com.market.cart.dto;

public class CartDto {
	//회원 아이디,상품번호,상품이름(name),개수,상품금액,상품 할인률
	private String id;
	private int pnum;
	private String name;
	private int EA;
	private int price;
	private float percent;
	private String thumb_save;
	private int cartnum;
	
	
	public int getCartnum() {
		return cartnum;
	}
	public void setCartnum(int cartnum) {
		this.cartnum = cartnum;
	}
	public CartDto() {
		
	}
	public String getThumb_save() {
		return thumb_save;
	}
	public void setThumb_save(String thumb_save) {
		this.thumb_save = thumb_save;
	}
	public CartDto(String id, int pnum, String name, int EA, int price, float percent,String thumb_save,int cartnum) {
		super();
		this.id = id;
		this.pnum = pnum;
		this.name = name;
		this.EA = EA;
		this.price = price;
		this.percent = percent;
		this.thumb_save = thumb_save;
		this.cartnum=cartnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getEA() {
		return EA;
	}
	public void setEA(int EA) {
		this. EA = EA;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "CartDto [id=" + id + ", pnum=" + pnum + ", name=" + name + ", EA=" + EA + ", price=" + price
				+ ", percent=" + percent + ", thumb_save=" + thumb_save + ", cartnum=" + cartnum + "]";
	}
	
	
}
