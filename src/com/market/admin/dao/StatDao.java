package com.market.admin.dao;

public class StatDao {
	public static StatDao instance = new StatDao();

	public static StatDao getInstance() {
		return instance;
	}

	private StatDao() {
	}
	
	
}
