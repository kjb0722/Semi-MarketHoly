package com.market.pay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.market.db.JDBCUtil;
import com.market.pay.dto.PayDto;
import com.sun.org.apache.regexp.internal.recompile;

import oracle.net.aso.p;

public class PayDao {

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

	public int deletecart(String id,int point) {
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

	public int insertord(PayDto odto, String id, String[] pPnum, String[] pPname, String[] pEA, String[] pCartPrice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			con = JDBCUtil.getConn();
			pstmt2 = con.prepareStatement("insert into orders values(seq_orders_onum.nextval,?,1,?,sysdate,null,?,?,?,?,?,?)");
			pstmt2.setInt(1, odto.getNum());
			pstmt2.setString(2, odto.getPay_yn());
			pstmt2.setString(3, odto.getId());
			pstmt2.setInt(4, odto.getPrice());
			pstmt2.setInt(5, odto.getUse_point());
			pstmt2.setInt(6, odto.getSale_price());
			pstmt2.setInt(7,odto.getPay_way());
			pstmt2.setString(8, odto.getAddr());
			int n = pstmt2.executeUpdate();
			
			for(int i=0;i<pPnum.length;i++) {
				pstmt1 = con.prepareStatement("insert into order_product values(seq_order_product_opnum.nextval,seq_orders_onum.currval,?,?,?,?)");
				pstmt1.setInt(1, Integer.parseInt(pPnum[i]));
				pstmt1.setString(2, pPname[i]);
				pstmt1.setInt(3, Integer.parseInt(pEA[i]));
				pstmt1.setInt(4, Integer.parseInt(pCartPrice[i]));
				pstmt1.executeUpdate();
			}

			String sql = "delete from cart where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);

		}
	}
		public int updatepoint(int use_point, String id) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
			con = JDBCUtil.getConn();
			String sql="update member set point=point-? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, use_point);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
			return 1;
				
			}catch (SQLException se) {
				// TODO: handle exception
				return -1;
			}finally {
				JDBCUtil.close(null, pstmt, con);
		}
	}
}
