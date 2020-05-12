package com.market.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.CategoryDto;
import com.market.admin.dto.CategoryListDto;
import com.market.db.JDBCUtil;

public class CategoryDao {
	public static CategoryDao dao = new CategoryDao();

	public static CategoryDao getInstance() {
		return dao;
	}

	private CategoryDao() {
	}

	public ArrayList<CategoryListDto> selListAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryListDto> list = new ArrayList<CategoryListDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select a.cnum,a.name,b.cnum tnum,b.name tname from category a inner join category b on a.cnum=b.type(+) where a.type = -1 order by a.cnum";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cnum = rs.getInt("cnum");
				String name = rs.getString("name");
				int tnum = rs.getInt("tnum");
				String tname = rs.getString("tname");
				list.add(new CategoryListDto(cnum, name, tnum, tname));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public ArrayList<CategoryDto> selList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type=-1 order by cnum";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cnum = rs.getInt("cnum");
				int type = rs.getInt("type");
				String name = rs.getString("name");
				list.add(new CategoryDto(cnum, type, name));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int insCategory(String catName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into category values(seq_category_cnum_type.nextval,-1,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, catName);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public int insTypeCat(CategoryDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into category values(seq_category_cnum_type.nextval,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getType());
			pstmt.setString(2, dto.getName());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}
}
