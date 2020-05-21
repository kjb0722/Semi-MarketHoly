package com.market.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.CategoryListDto;
import com.market.admin.dto.OrderAdminDto;
import com.market.db.JDBCUtil;

public class OrderAdminDao {
	public static OrderAdminDao instance = new OrderAdminDao();
	public static OrderAdminDao getInstance() {
		return instance;
	}
	private OrderAdminDao() {
		
	}
	public static ArrayList<OrderAdminDto> selOrdList(int startRow, int endRow, String kind, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderAdminDto> list = new ArrayList<OrderAdminDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cnum = rs.getInt("cnum");
				
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	public static double selOrdCnt(String kind, String word) {
		// TODO Auto-generated method stub
		return 0;
	}
}
