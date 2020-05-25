package com.market.pay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.market.db.JDBCUtil;
import com.market.pay.dto.PayDto;

import oracle.net.aso.p;

public class PayDao {

	public int insertorders(PayDto dDto, PayDto opDto, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			con = JDBCUtil.getConn();
			pstmt2 = con.prepareStatement("insert into orders values(seq_orders_onum.nextval,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt2.setInt(1, dDto.getNum());
			pstmt2.setInt(2, dDto.getOpnum());
			pstmt2.setInt(3, dDto.getStatus());
			pstmt2.setString(4, dDto.getPay_yn());
			pstmt2.setDate(5, dDto.getReg_date());
			pstmt2.setDate(6, dDto.getEnd_date());
			pstmt2.setString(7, dDto.getId());
			pstmt2.setInt(8, dDto.getPrice());
			pstmt2.setInt(9, dDto.getUse_point());
			pstmt2.setInt(10, dDto.getSale_price());
			pstmt2.setInt(11, dDto.getPay_way());
			pstmt2.setString(12, dDto.getAddr());

			pstmt1 = con.prepareStatement("insert into order_product values(seq_order_product_opnum.nextval,seq_orders_onum.currval,?,?,?,?)");
			pstmt1.setInt(1, opDto.getPnum());
			pstmt1.setString(2, opDto.getPname());
			pstmt1.setInt(3, opDto.getEA());
			pstmt1.setInt(4, opDto.getPrice());

			String sql = "delete from cart where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);

		}
	}

	public int insertorderproduct(PayDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement("insert into order_product values(seq_order_product_opnum.nextval,?,?,?,?,?)");
			pstmt.setInt(2, dto.getPnum());
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
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		String sql = "delete from cart where id=?";
		try {

			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			n = pstmt.executeUpdate();

			return n;

		} catch (SQLException e) {
			// TODO: handle exception
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}

	}
}
