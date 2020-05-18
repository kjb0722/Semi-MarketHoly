package com.market.order.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.db.JDBCUtil;
import com.market.member.dto.MemberDto;
import com.market.order.dto.OrdersDto;



/*
 * 1.장바구니에 담긴 물품 불러오기
	 
	 * 	1.cartnum	장바구니 번호
	 *	2.id	아이디
	 *	3.pnum	상품번호
	 *	4.EA 갯수 
	 
	 *언니 질문 있는데 sql에서 where id로 select 해와야 하는건가요? 
 * 
 * 2.주문자 정보 불러오기
 * 3.배송정보 불러오기,요청사항 저장하기/0512
 * 4.사용가능한 적립금 불러오기
 * 5.결제수단은...모르겠음 ^^
 */
public class OrdersDao {
//	1.장바구니에 담긴 물품 불러오기
	
	public ArrayList<OrdersDto> cartlist(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement("select * from cart c , product p where c.id=p.id and id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<OrdersDto> list=new ArrayList<OrdersDto>();
			OrdersDto dto=new OrdersDto
					(
					rs.getString("thumb_save"),
					rs.getInt("cartnum"),
					rs.getString("id"),
					rs.getInt("pnum"),
					rs.getInt("EA"),
					rs.getString("name"),
					rs.getInt("price"),
					rs.getInt("percent")
					);
			list.add(dto);
		
		return list;
		
		
	}catch(SQLException se) {
		se.printStackTrace();
		return null;
			
	}finally {
		JDBCUtil.close(rs, pstmt, con);
		}
	}
	
	public void name() {
		
	}
	
	

	
	
}
