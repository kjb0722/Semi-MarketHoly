package com.market.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.controller.SaleProdListController;
import com.market.admin.dto.SaleDto;
import com.market.admin.dto.SaleProdListDto;
import com.market.db.JDBCUtil;

public class SaleDao {
	public static SaleDao dao = new SaleDao();

	public static SaleDao getInstance() {
		return dao;
	}

	private SaleDao() {

	}

	public int insSale(SaleDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "INSERT INTO sale \r\n" + 
					"SELECT seq_sale_snum.nextval, \r\n" + 
					"       ?, \r\n" + 
					"       ?, \r\n" + 
					"       ?, \r\n" + 
					"       ?, \r\n" + 
					"       ?, \r\n" + 
					"       'N' \r\n" + 
					"FROM   dual \r\n" + 
					"WHERE  NOT EXISTS(SELECT pnum \r\n" + 
					"                  FROM   sale \r\n" + 
					"                  WHERE  pnum = ? \r\n" + 
					"                         AND del_yn = 'N') ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getPnum());
			pstmt.setString(2, dto.getName());
			pstmt.setFloat(3, dto.getPercent());
			pstmt.setDate(4, dto.getStartDate());
			pstmt.setDate(5, dto.getEndDate());
			pstmt.setInt(6, dto.getPnum());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public ArrayList<SaleProdListDto> selProdList(int startRow, int endRow, int pCnum, int pType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SaleProdListDto> list = new ArrayList<SaleProdListDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "";
			if (pType == -1) {
				sql = "SELECT * \r\n" + 
						"FROM   ( \r\n" + 
						"              SELECT aa.*, \r\n" + 
						"                     rownum rnum \r\n" + 
						"              FROM   ( \r\n" + 
						"                            SELECT a.*, \r\n" + 
						"                                   Nvl( \r\n" + 
						"                                        ( \r\n" + 
						"                                        SELECT NAME \r\n" + 
						"                                        FROM   sale \r\n" + 
						"                                        WHERE  pnum=a.pnum \r\n" + 
						"                                        AND    del_yn='N'),-1) onsalename \r\n" + 
						"                            FROM   product a \r\n" + 
						"                            WHERE  a.type = " + pCnum + " \r\n" + 
						"                            AND    del_yn = 'N') aa) \r\n" + 
						"WHERE  rnum >= ? \r\n" + 
						"AND    rnum <= ?";
			} else {
				sql = "SELECT * \r\n" + 
						"FROM (SELECT aa.*, \r\n" + 
						"       rownum rnum \r\n" + 
						"FROM   ( \r\n" + 
						"              SELECT a.*, \r\n" + 
						"                     Nvl( \r\n" + 
						"                          ( \r\n" + 
						"                          SELECT NAME \r\n" + 
						"                          FROM   sale \r\n" + 
						"                          WHERE  pnum=a.pnum \r\n" + 
						"                          AND    del_yn='N'),-1) onsalename \r\n" + 
						"              FROM   product a \r\n" + 
						"              WHERE  a.cnum = " + pCnum + " \r\n" + 
						"              AND    type = " + pType + " \r\n" + 
						"              AND    del_yn = 'N') aa) WHERE rnum >= ? \r\n" + 
						"AND \r\n" + 
						"rnum <= ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pnum = rs.getInt("pnum");
				int cnum = rs.getInt("cnum");
				String name = rs.getString("name");
				Date reg_date = rs.getDate("reg_date");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				int type = rs.getInt("type");
				String thumb_save = rs.getString("thumb_save");
				String onSaleName = rs.getString("onSaleName");
				list.add(new SaleProdListDto(pnum, cnum, name, reg_date, price, stock, type, thumb_save, onSaleName));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int insSaleProd(SaleDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into sale select seq_sale_snum.nextval,?,?,?,?,?,'N' from dual where not exists(select pnum from sale where pnum=? and del_yn='N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getPnum());
			pstmt.setString(2, dto.getName());
			pstmt.setFloat(3, dto.getPercent());
			pstmt.setDate(4, dto.getStartDate());
			pstmt.setDate(5, dto.getEndDate());
			pstmt.setInt(6, dto.getPnum());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public int delSale(int pCnum, int pType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "";
			if (pType == -1) {
				sql = "update sale set del_yn='Y' where pnum in(select a.pnum from sale a inner join product b on a.pnum=b.pnum where a.del_yn = 'N' and b.del_yn='N' and b.type = "
						+ pCnum + ") and del_yn='N'";
			} else {
				sql = "update sale set del_yn='Y' where pnum in(select a.pnum from sale a inner join product b on a.pnum=b.pnum where a.del_yn = 'N' and b.del_yn='N' and b.cnum = "
						+ pCnum + " and type = " + pType + ") and del_yn = 'N'";
			}
			pstmt = con.prepareStatement(sql);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public int delSaleProd(int pnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "update sale set del_yn='Y' where pnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public double selProdCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select nvl(count(*),0) cnt from product where del_yn = 'N'";
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
