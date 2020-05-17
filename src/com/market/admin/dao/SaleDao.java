package com.market.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.controller.SaleProdList;
import com.market.admin.dto.CategoryListDto;
import com.market.admin.dto.SaleDto;
import com.market.db.JDBCUtil;

public class SaleDao {
	public static SaleDao dao = new SaleDao();

	public static SaleDao getInstance() {
		return dao;
	}

	private SaleDao() {

	}

	public int insSale(SaleDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into sale values(seq_sale_snum.nextval,?,?,?,?,?,'N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getPnum());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPercent());
			pstmt.setDate(4, dto.getStartDate());
			pstmt.setDate(5, dto.getEndDate());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public ArrayList<SaleProdList> selProdList(int cnum, int type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SaleProdList> list = new ArrayList<SaleProdList>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				list.add(new SaleProdList());
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
