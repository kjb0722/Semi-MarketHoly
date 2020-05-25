package com.market.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.cart.dto.CartDto;
import com.market.db.JDBCUtil;

public class CartDao {
	private static CartDao instance = new CartDao();
	public static CartDao getInstance() {
		return instance;
	}

	public int inCart(int pnum, String id, int EA) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement("insert into cart values(seq_cart_num.nextval,?,?,?)");
			pstmt.setInt(1, pnum);
			pstmt.setString(2, id);
			pstmt.setInt(3, EA);
			return pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);

		}
	}
  
	public ArrayList<CartDto> getcart(String id) {
		// 회원 아이디,상품번호,상품이름(name),개수,상품금액,상품 할인률,상품사진
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartDto> list=new ArrayList<CartDto>();
		try {
			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement(
					"select id,c.pnum,name,EA,price,thumb_save,nvl((select sale.percent from sale where p.pnum=pnum),1)percent ,cartnum\r\n" + 
					"from cart c , product p where c.pnum=p.pnum and id=?"
					);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					
					id=rs.getString("id");
					int pnum=rs.getInt("pnum");
					String name=rs.getString("name");
					int EA = rs.getInt("EA");
					int price = rs.getInt("price");
					float percent = rs.getInt("percent");
					String thumb_save=rs.getString("thumb_save");
					int cartnum = rs.getInt("cartnum");
					
					list.add(new CartDto(id,pnum,name,EA,price,percent,thumb_save,cartnum));
			}
			return list;
			

		} catch (SQLException se) {
			se.printStackTrace();
			return null;

		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	
		public int deletecart(int cartnum) {
			Connection con=null;
			PreparedStatement pstmt=null;
			int n=0;
			String sql="delete from cart where cartnum=?";
			try {
				
				con = JDBCUtil.getConn();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,cartnum);
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
