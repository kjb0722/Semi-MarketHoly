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
			String sql = "insert into sale select seq_sale_snum.nextval,?,?,?,?,?,'N' from dual where not exists(select pnum from sale where pnum=? and del_yn='N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getPnum());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPercent());
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

	public ArrayList<SaleProdListDto> selProdList(int pCnum, int pType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SaleProdListDto> list = new ArrayList<SaleProdListDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "";
			if (pType == -1) {
				sql = "select a.*,nvl((select name from sale where pnum=a.pnum and del_yn='N'),-1) onSaleName from product a where a.type = "
						+ pCnum + " and del_yn = 'N'";
			} else {
				sql = "select a.*,nvl((select name from sale where pnum=a.pnum and del_yn='N'),-1) onSaleName from product a where a.cnum = "
						+ pCnum + " and type = " + pType + " and del_yn = 'N'";
			}
			pstmt = con.prepareStatement(sql);
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
			pstmt.setInt(3, dto.getPercent());
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
}
