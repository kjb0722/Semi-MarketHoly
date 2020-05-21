package com.market.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.OrderAdminDto;
import com.market.db.JDBCUtil;

public class OrderAdminDao {
	public static OrderAdminDao instance = new OrderAdminDao();
	public static OrderAdminDao getInstance() {
		return instance;
	}
	private OrderAdminDao() {
		
	}
	public ArrayList<OrderAdminDto> selOrdList(int startRow, int endRow, String kind, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderAdminDto> list = new ArrayList<OrderAdminDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from(select aa.*,rownum rnum from (select a.*,(select name from common where type='주문상태' and val=status) statusName,select name from common where type='결제 방법' and val=pay_way) pay_wayName from orders a where status not in(5,6) order by reg_date desc) aa) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int onum = rs.getInt("onum");
				String id = rs.getString("id");
				String statusName = rs.getString("statusName");
				String prodName = rs.getString("prodName");
				String pay_yn = rs.getString("pay_yn");
				int price = rs.getInt("price");
				String addr = rs.getString("addr");
				String pay_wayName = rs.getString("pay_wayName");
				int use_point = rs.getInt("use_point");
				Date reg_date = rs.getDate("reg_date");
				list.add(new OrderAdminDto(onum,id,statusName,prodName,pay_yn,price,addr,pay_wayName,use_point,reg_date));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	public int selOrdCnt(String kind, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select nvl(count(*),0) cnt from orders a where status not in(5,6)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
