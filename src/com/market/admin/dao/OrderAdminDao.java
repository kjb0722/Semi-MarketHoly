package com.market.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.OrdDetailDto;
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
			String sql = "";
			if(kind.equals("pname")) {
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
						"                       (SELECT pname \r\n" + 
						"                               || Decode(bb.cnt, 1, '', \r\n" + 
						"                                                 ' 외 ' \r\n" + 
						"                                                 || bb.cnt \r\n" + 
						"                                                 || '건') prodName \r\n" + 
						"                        FROM   order_product aa, \r\n" + 
						"                               (SELECT onum, \r\n" + 
						"                                       Count(*) cnt \r\n" + 
						"                                FROM   order_product \r\n" + 
						"                                GROUP  BY onum) bb \r\n" + 
						"                        WHERE  aa.onum = a.onum \r\n" + 
						"                               AND aa.onum = bb.onum \r\n" + 
						"                               AND rownum = 1)    prodName \r\n" + 
						"                FROM   orders a \r\n" + 
						"                WHERE  status IN( 1, 2, 3, 4 ) \r\n" + 
						"                       AND onum LIKE '%" + word + "%' \r\n" + 
						"                ORDER  BY reg_date DESC) aa) \r\n" + 
						"WHERE  rnum >= ? \r\n" + 
						"       AND rnum <= ? ";
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
						"  (SELECT pname || Decode(bb.cnt, 1, '', ' 외 ' || bb.cnt || '건') prodName  FROM   order_product aa,  (SELECT onum, Count(*) cnt  FROM   order_product  GROUP  BY onum) bb  WHERE  aa.onum = a.onum  AND aa.onum = bb.onum AND rownum = 1)    prodName" +  
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

	public int selOrdCnt(String kind, String word, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "SELECT Nvl(Count(*), 0) cnt "
					+ " FROM   orders a \r\n" + " WHERE  status NOT IN( " + status + " ) ";
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

	public int updStatus(String[] onums, int status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			con.setAutoCommit(false);
			int n = 0;
			String sql = "update orders set status = ?, end_date = null where onum = ?";
			if (status == 5 || status == 6) {
				sql = "update orders set status = ?, end_date = sysdate where onum = ?";
			}
			for (String onum : onums) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, status);
				pstmt.setString(2, onum);
				n += pstmt.executeUpdate();
			}
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
			return -1;
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public ArrayList<OrdDetailDto> selOrdDetail(int pOnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrdDetailDto> list = new ArrayList<OrdDetailDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "SELECT a.*, \r\n" + 
					"       (SELECT thumb_save \r\n" + 
					"        FROM   product \r\n" + 
					"        WHERE  pnum = a.pnum) thumb_save \r\n" + 
					"FROM   order_product a \r\n" + 
					"WHERE  onum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pOnum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int opnum = rs.getInt("opnum");
				String thumb_save = rs.getString("thumb_save");
				String pname = rs.getString("pname");
				int ea = rs.getInt("ea");
				int price = rs.getInt("price");
				list.add(new OrdDetailDto(opnum, thumb_save, pname, ea, price));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public OrderAdminDto selOrdInfo(int pOnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "SELECT a.*, \r\n" + 
					"       (SELECT NAME \r\n" + 
					"        FROM   common \r\n" + 
					"        WHERE  type = '주문상태' \r\n" + 
					"               AND val = status)  statusName, \r\n" + 
					"       (SELECT NAME \r\n" + 
					"        FROM   common \r\n" + 
					"        WHERE  type = '결제 방법' \r\n" + 
					"               AND val = pay_way) pay_wayName \r\n" + 
					"FROM   orders a \r\n" + 
					"WHERE  onum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pOnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int onum = rs.getInt("onum");
				String id = rs.getString("id");
				String statusName = rs.getString("statusName");
				String pay_yn = rs.getString("pay_yn");
				int price = rs.getInt("price");
				String addr = rs.getString("addr");
				String pay_wayName = rs.getString("pay_wayName");
				int use_point = rs.getInt("use_point");
				Date reg_date = rs.getDate("reg_date");
				return new OrderAdminDto(onum, id, statusName, "", pay_yn, price, addr, pay_wayName, use_point,
						reg_date);
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
