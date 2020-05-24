package com.market.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.market.admin.dto.ProdDto;
import com.market.admin.dto.ProdInfoDto;
import com.market.db.JDBCUtil;

public class ProdDao {
	public static ProdDao dao = new ProdDao();

	public static ProdDao getInstance() {
		return dao;
	}

	private ProdDao() {

	}

	public int insProd(ProdDto dto, ProdInfoDto infoDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
			con = JDBCUtil.getConn();
			con.setAutoCommit(false);
			String sql = "insert into product values(seq_product_pnum.nextval,?,?,?,?,?,?,?,?,?,?,sysdate,'N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCnum());
			pstmt.setInt(2, dto.getType());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setInt(5, dto.getStock());
			pstmt.setString(6, dto.getDescription());
			pstmt.setString(7, dto.getThumb_org());
			pstmt.setString(8, dto.getThumb_save());
			pstmt.setString(9, dto.getDetail_org());
			pstmt.setString(10, dto.getDetail_save());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				String sql1 = "insert into prod_info values(seq_product_pnum.CURRVAL,?,?,?,?,?,?)";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, infoDto.getUnit());
				pstmt1.setString(2, infoDto.getVolume());
				pstmt1.setString(3, infoDto.getOrigin());
				pstmt1.setString(4, infoDto.getPack_type());
				pstmt1.setString(5, infoDto.getShelf_life());
				pstmt1.setString(6, infoDto.getGuidance());
				return pstmt1.executeUpdate();
			}
			return -1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
			return -1;
		} finally {
			JDBCUtil.close(pstmt1);
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public int updProd(int pnum, String name, String description, int price) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "update product set name=?, description=?, price=? where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setInt(3, price);
			pstmt.setInt(4, pnum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}
}
