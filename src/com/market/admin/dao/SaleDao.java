package com.market.admin.dao;

public class SaleDao {
	public static SaleDao dao = new SaleDao();
	public static SaleDao getInstance() {
		return dao;
	}
	private SaleDao() {
		
	}
	
}
