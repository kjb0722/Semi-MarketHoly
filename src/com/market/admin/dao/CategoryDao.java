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

	// 카테고리 전체 목록
	public ArrayList<CategoryListDto> selListAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryListDto> list = new ArrayList<CategoryListDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "SELECT a.cnum, \r\n" + 
					"       a.name, \r\n" + 
					"       b.cnum tnum, \r\n" + 
					"       b.name tname \r\n" + 
					"FROM   category a \r\n" + 
					"       inner join (SELECT * \r\n" + 
					"                   FROM   category \r\n" + 
					"                   WHERE  del_yn = 'N') b \r\n" + 
					"               ON a.cnum = b.TYPE(+) \r\n" + 
					"WHERE  a.TYPE = -1 \r\n" + 
					"       AND a.del_yn = 'N' \r\n" + 
					"ORDER  BY a.cnum, \r\n" + 
					"          b.cnum ";
			
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

	// 큰 카테고리 목록
	public ArrayList<CategoryDto> selList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type=-1 and del_yn='N' order by cnum";
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

	// 세부 카테고리 목록
	public ArrayList<CategoryDto> selTypeList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type!=-1 and del_yn='N' order by cnum desc";
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

	// 큰 카테고리 추가
	public int insCategory(String catName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into category values(seq_category_cnum_type.nextval,-1,?,'N')";
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

	// 세부 카테고리 추가
	public int insTypeCat(CategoryDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into category values(seq_category_cnum_type.nextval,?,?,'N')";
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

	// 카테고리 수정
	public int updateCat(CategoryDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "update category set name=? where cnum=? and del_yn='N'";
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

	// 카테고리 삭제
	public int delCat(int catNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = JDBCUtil.getConn();
			con.setAutoCommit(false);
			String sql2 = "update product set del_yn = 'Y' where type = ?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, catNum);
			pstmt2.executeUpdate();
			
			String sql1 = "update category set del_yn = 'Y' where type = ?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, catNum);
			pstmt1.executeUpdate();
			
			String sql = "update category set del_yn = 'Y' where cnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, catNum);
			int n = pstmt.executeUpdate();

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
			JDBCUtil.close(pstmt2);
			JDBCUtil.close(pstmt1);
			JDBCUtil.close(null, pstmt, con);
		}
	}

	// 세부 카테고리 번호로 세부 카테고리 목록 불러오기
	public ArrayList<CategoryDto> selTypeList(int pType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type=? and del_yn='N' order by cnum desc";
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
	// 카테고리 번호로 세부카테고리의 cnum가져오기

	public ArrayList<CategoryDto> selSub(int cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryDto> list = new ArrayList<CategoryDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from category where type=? and del_yn='N'";
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

	// 카테고리 이름 가져오기
	public String getName(int cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select name from category where cnum=? and del_yn='N'";
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

	//세부 카테고리 삭제
	public int delCatType(int catNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = JDBCUtil.getConn();
			con.setAutoCommit(false);
			String sql2 = "update product set del_yn = 'Y' where cnum = ?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, catNum);
			pstmt2.executeUpdate();
			
			String sql1 = "update category set del_yn = 'Y' where cnum = ?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, catNum);
			int n = pstmt1.executeUpdate();
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
			JDBCUtil.close(pstmt2);
			JDBCUtil.close(null, pstmt1, con);
		}
	}
}
