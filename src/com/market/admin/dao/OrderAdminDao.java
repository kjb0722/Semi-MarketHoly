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

	public ArrayList<OrderAdminDto> selOrdList(int startRow, int endRow, String kind, String word, String pStatus) {
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
						"                               AND rownum = 1)    prodName, (select rating from member where num = a.num and del_yn='N') rating \r\n" +
						"                FROM   orders a \r\n" + 
						"                WHERE  status IN( " + pStatus +" ) \r\n" + 
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
						"  (SELECT pname || Decode(bb.cnt, 1, '', ' 외 ' || bb.cnt || '건') prodName  FROM   order_product aa,  (SELECT onum, Count(*) cnt  FROM   order_product  GROUP  BY onum) bb  WHERE  aa.onum = a.onum  AND aa.onum = bb.onum AND rownum = 1)    prodName,(select rating from member where num = a.num and del_yn='N') rating" +  
						"                FROM   orders a \r\n" + 
						"                WHERE  status IN( " + pStatus + " ) \r\n" + 
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
				int num = rs.getInt("num");
				int rating = rs.getInt("rating");
				int status = rs.getInt("status");
				list.add(new OrderAdminDto(onum, id, statusName, prodName, pay_yn, price, addr, pay_wayName, use_point,
						reg_date, num, rating, status));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int selOrdCnt(int startRow, int endRow, String kind, String word, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "SELECT Nvl(Count(*), 0) cnt \r\n" + 
					" FROM   orders a WHERE  status IN("+status+")";
			
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

	public int updStatus(String[] onums, String[] nums, String[] ratings, int status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			con = JDBCUtil.getConn();
			con.setAutoCommit(false);
			int n = 0;
			String sql = "update orders set status = ?, end_date = null where onum = ?";
			if (status == 5 || status == 6) {
				sql = "update orders set status = ?, end_date = sysdate where onum = ?";
			}

			for (int i = 0; i < onums.length; i++) {
				int onum = Integer.parseInt(onums[i]);
				int num = Integer.parseInt(nums[i]);
				int rating = Integer.parseInt(ratings[i]);

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, status);
				pstmt.setInt(2, onum);
				n += pstmt.executeUpdate();

				if (status == 5) {
//					일반 0.5% 10만원 미만 10
//					화이트 1% 15만원 미만 20
//					라벤더 3% 20만원 미만 30
//					퍼플 5% 25만원 이상 40
					double multip = 0;
					if (rating == 10) {
						multip = 0.005;
					} else if (rating == 20) {
						multip = 0.01;
					} else if (rating == 30) {
						multip = 0.03;
					} else if (rating == 40) {
						multip = 0.05;
					}

					// 포인트 적립
					String sql1 = "update member set point=round(point+(select price * ? from orders where onum = ?)) where num = ?";
					pstmt1 = con.prepareStatement(sql1);
					pstmt1.setDouble(1, multip);
					pstmt1.setInt(2, onum);
					pstmt1.setInt(3, num);
					pstmt1.executeUpdate();

					// 등급 수정
					String sql2 = "SELECT nvl(Sum(price),0) totalPurchase \r\n" 
							+ "FROM   orders \r\n"
							+ "WHERE  1 = 1 \r\n"
							+ "       AND status = 5 \r\n"
							+ "       AND num = ? and status = 5 ";
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setInt(1, num);
					rs = pstmt2.executeQuery();
					rs.next();
					int totalPurchase = rs.getInt("totalPurchase");
					int afterRating = 0;
					if (totalPurchase < 100000) {
						afterRating = 10;
					} else if (totalPurchase >= 100000 && totalPurchase < 150000) {
						afterRating = 20;
					} else if (totalPurchase >= 150000 && totalPurchase < 200000) {
						afterRating = 30;
					} else if (totalPurchase >= 200000) {
						afterRating = 40;
					}

					if (rating != afterRating && rating != 99) {
						String sql3 = "update member set rating = ? where num = ?";
						pstmt3 = con.prepareStatement(sql3);
						pstmt3.setInt(1, afterRating);
						pstmt3.setInt(2, num);
						pstmt3.executeUpdate();
					}
				} else if (status == 6) {
					// 포인트 검색 후 환급
					String sql4 = "select nvl(max(use_point),0) use_point from orders where onum = ?";
					pstmt4 = con.prepareStatement(sql4);
					pstmt4.setInt(1, onum);
					rs2 = pstmt4.executeQuery();
					rs2.next();
					int use_point = rs2.getInt("use_point");
					
					String sql5 = "update member set point=point + ? where num = ?";
					pstmt5 = con.prepareStatement(sql5);
					pstmt5.setInt(1, use_point);
					pstmt5.setInt(2, num);
					pstmt5.executeUpdate();
				}
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
				con.commit();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			JDBCUtil.close(rs2);
			JDBCUtil.close(pstmt4);
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt3);
			JDBCUtil.close(pstmt2);
			JDBCUtil.close(pstmt1);
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
						reg_date,-1,-1,-1);
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
