package com.market.pay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.market.db.JDBCUtil;
import com.market.pay.dto.PayDto;

import oracle.net.aso.p;

public class PayDao {
	
	public int insertorders(PayDto dto) {
		Connection con=null;
		PreparedStatement pstmt = null;
		
	try {
		con = JDBCUtil.getConn();
		pstmt = con.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pstmt.setInt(1, dto.getOnum());
		pstmt.setInt(2, dto.getNum());
		pstmt.setInt(3, dto.getOpnum());
		pstmt.setInt(4, dto.getStatus());
		pstmt.setString(5, dto.getPay_yn());
		pstmt.setDate(6, dto.getReg_date());
		pstmt.setDate(7, dto.getEnd_date());
		pstmt.setString(8, dto.getId());
		pstmt.setInt(9, dto.getPrice());
		pstmt.setInt(10, dto.getUse_point());
		pstmt.setInt(11, dto.getSale_price());
		pstmt.setInt(12, dto.getPay_way());
		pstmt.setString(13, dto.getAddr());
		
		return pstmt.executeUpdate();

	} catch (SQLException se) {
		se.printStackTrace();
		return -1;
	} finally {
		JDBCUtil.close(null, pstmt, con);

		}
	}
	
	public int insertorderproduct(PayDto dto) {
		Connection con=null;
		PreparedStatement pstmt = null;
		
	try {
		con = JDBCUtil.getConn();
		pstmt = con.prepareStatement("insert into order_product values(?,?,?,?,?,?)");
		pstmt.setInt(1, dto.getOpnum());
		pstmt.setInt(2, dto.getOnum());
		pstmt.setInt(2, dto.getNum());
		pstmt.setString(3, dto.getPname());
		pstmt.setInt(4, dto.getEA());
		pstmt.setInt(5, dto.getPrice());
		
		return pstmt.executeUpdate();

	} catch (SQLException se) {
		se.printStackTrace();
		return -1;
	} finally {
		JDBCUtil.close(null, pstmt, con);

		}
	}
	public int deletecart(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int n=0;
		String sql="delete from cart where id=?";
		try {
			
			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			n=pstmt.executeUpdate();
			
			return n;
		
		}catch (SQLException e) {
			// TODO: handle exception
			return -1;
		}finally {
			JDBCUtil.close(null, pstmt, con);
		}
	
	}
}
