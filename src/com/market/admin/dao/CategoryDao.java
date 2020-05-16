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

	//카테고리 전체 목록
	public ArrayList<CategoryListDto> selListAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryListDto> list = new ArrayList<CategoryListDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select a.cnum,a.name,b.cnum tnum,b.name tname from category a inner join category b on a.cnum=b.type(+) where a.type = -1 order by a.cnum, b.cnum";
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

	//큰 카테고리 목록
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
	
	//세부 카테고리 목록
	public ArrayList<CategoryDto> selTypeList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type!=-1 order by cnum desc";
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

	//큰 카테고리 추가
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
	
	//세부 카테고리 추가
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

	//카테고리 수정
	public int updateCat(CategoryDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "update category set name=? where cnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getCnum());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	//카테고리 삭제
	public int delCat(int catNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "delete from category where cnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, catNum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	//세부 카테고리 번호로 세부 카테고리 목록 불러오기
	public ArrayList<CategoryDto> selTypeList(int pType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type=? order by cnum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pType);
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
	//카테고리 번호로 세부카테고리의 cnum가져오기
	
	public ArrayList<CategoryDto> selSub(int cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int tcnum = rs.getInt("cnum");
				int type = rs.getInt("type");
				String name = rs.getString("name");
				list.add(new CategoryDto(tcnum, type, name));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	
	
	//카테고리 이름 가져오기
	public String getName(int cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name=null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select name from category where cnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}
			return name;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
