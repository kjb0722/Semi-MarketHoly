package com.market.admin.dao;

import java.sql.Connection;
import java.sql.Date;
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
			String sql = "select * from(select aa.*,rownum rnum from (select a.*,(select name from common where type='주문상태' and val=status) statusName,select name from common where type='결제 방법' and val=pay_way) pay_wayName from orders a where status not in(5,6) order by reg_date desc) aa) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int onum = rs.getInt("onum");
				String id = rs.getString("id");
				String statusName = rs.getString("statusName");
				String statusName = rs.getString("statusName");
				String statusName = rs.getString("statusName");
				int price = rs.getInt("");       
				private String onum;
				private String id;
				private String statusName;
				private String prodName;
				private String pay_yn;
				private int price;
				private String addr;
				private String pay_wayName;
				private int use_point;
				private Date reg_date;
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
