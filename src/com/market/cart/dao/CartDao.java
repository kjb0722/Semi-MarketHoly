package com.market.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.market.cart.dto.CartDto;
import com.market.db.JDBCUtil;

public class CartDao {
	private static CartDao instance = new CartDao();

	private CartDao() {
	}

	public static CartDao getInstance() {
		return instance;
	}

	public int inCart(int pnum, String id, int EA) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement("insert into cart values(seq,?,?,?)");
			pstmt.setString(1, id);
			pstmt.setInt(2, pnum);
			pstmt.setInt(3, EA);
			return pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);

		}
	}

	public CartDto getcart(String id) {
		// 회원 아이디,상품번호,상품이름(name),개수,상품금액,상품 할인률,상품사진
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement(
					"select id,pnum,name,EA,price,percent,thumb_save,(select percent  from sale where p.pnum=pnum) percent"
							+ "from cart c , product p where c.pnum=p.pnum and id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			CartDto dto = new CartDto(rs.getString("id"), rs.getInt("pnum"), rs.getString("name"), rs.getInt("EA"),
					rs.getInt("price"), rs.getInt("percent"), rs.getString("thumb_save"));

			return dto;

		} catch (SQLException se) {
			se.printStackTrace();
			return null;

		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

}
