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

	public ArrayList<OrderAdminDto> selOrdList(int startRow, int endRow, String kind, String word, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderAdminDto> list = new ArrayList<OrderAdminDto>();
		try {
			con = JDBCUtil.getConn();
			//String sql = "select * from(select aa.*,rownum rnum from (select a.*,(select name from common where type='주문상태' and val=status) statusName,(select name from common where type='결제 방법' and val=pay_way) pay_wayName,(select (select pname from order_product where rownum = 1) || '외 '||count(*)||'종' from order_product group by onum) prodName from orders a where status not in(5,6) order by reg_date desc) aa) where rnum >= ? and rnum <= ?";
			String sql = "";
			if(kind.equals("prodName")) {
				sql = "SELECT * \r\n" + 
						"FROM   (SELECT aa.*, \r\n" + 
						"               rownum rnum \r\n" + 
						"        FROM   (SELECT a.*, \r\n" + 
						"                       (SELECT NAME \r\n" + 
						"                        FROM   common \r\n" + 
						"                        WHERE  type = '주문상태' \r\n" + 
						"                               AND val = status)  statusName, \r\n" + 
						"                       (SELECT NAME \r\n" + 
						"                        FROM   common \r\n" + 
						"                        WHERE  type = '결제 방법' \r\n" + 
						"                               AND val = pay_way) pay_wayName, \r\n" + 
						"                       (SELECT (SELECT pname \r\n" + 
						"                                FROM   order_product \r\n" + 
						"                                WHERE  rownum = 1) \r\n" + 
						"                               || '외 ' \r\n" + 
						"                               || Count(*) \r\n" + 
						"                               || '종' \r\n" + 
						"                        FROM   order_product \r\n" + 
						"                        GROUP  BY onum)           prodName \r\n" + 
						"                FROM   orders a \r\n" + 
						"                WHERE  status IN( " + status + " ) \r\n" + 
						"                       AND onum IN(SELECT onum \r\n" + 
						"                                   FROM   order_product \r\n" + 
						"                                   WHERE  pname LIKE '%" + word + "%') \r\n" + 
						"                ORDER  BY reg_date DESC) aa) \r\n" + 
						"WHERE  rnum >= ? \r\n" + 
						"       AND rnum <= ?";
			}else {
				sql = "SELECT * \r\n" + 
						"FROM   (SELECT aa.*, \r\n" + 
						"               rownum rnum \r\n" + 
						"        FROM   (SELECT a.*, \r\n" + 
						"                       (SELECT NAME \r\n" + 
						"                        FROM   common \r\n" + 
						"                        WHERE  type = '주문상태' \r\n" + 
						"                               AND val = status)  statusName, \r\n" + 
						"                       (SELECT NAME \r\n" + 
						"                        FROM   common \r\n" + 
						"                        WHERE  type = '결제 방법' \r\n" + 
						"                               AND val = pay_way) pay_wayName, \r\n" + 
						"                       (SELECT (SELECT pname \r\n" + 
						"                                FROM   order_product \r\n" + 
						"                                WHERE  rownum = 1) \r\n" + 
						"                               || '외 ' \r\n" + 
						"                               || Count(*) \r\n" + 
						"                               || '종' \r\n" + 
						"                        FROM   order_product \r\n" + 
						"                        GROUP  BY onum)           prodName \r\n" + 
						"                FROM   orders a \r\n" + 
						"                WHERE  status IN( " + status + " ) \r\n" + 
						"                       AND " + kind + " LIKE '%" + word +"%' \r\n" + 
						"                ORDER  BY reg_date DESC) aa) \r\n" + 
						"WHERE  rnum >= ? \r\n" + 
						"       AND rnum <= ? ";
			}
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
				list.add(new OrderAdminDto(onum, id, statusName, prodName, pay_yn, price, addr, pay_wayName, use_point,
						reg_date));
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
			//String sql = "select nvl(count(*),0) cnt from orders a where status not in(5,6)";
			String sql = "SELECT Nvl(Count(*), 0) cnt \r\n" + 
					"FROM   orders a \r\n" + 
					"WHERE  status NOT IN( 5, 6 ) ";
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
