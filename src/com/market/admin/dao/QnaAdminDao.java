package com.market.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.QnaAdminDto;
import com.market.db.JDBCUtil;
import com.market.qna.dto.QnaDto;

public class QnaAdminDao {
	public static QnaAdminDao instance = new QnaAdminDao();

	public static QnaAdminDao getInstance() {
		return instance;
	}

	private QnaAdminDao() {

	}

	public ArrayList<QnaAdminDto> selQnaList(int startRow, int endRow, String kind, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "";
//			if (kind.equals("")) {
//				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
//			} else if (kind.equals("pname")) {
//				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where b.name like '%"
//						+ word + "%' and a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
//			} else if (kind.equals("cname")) {
//				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where type in(select type from category where name like '%"
//						+ word + "%' and a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
//			} else {
//				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where "
//						+ kind + " like '%" + word + "%' and a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
//			}
			if (kind.equals("writer")) {
				sql = "select * from(select aa.*,rownum rnum from(select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname,level from qna a inner join product b on a.pnum = b.pnum where a.name like '%"
						+ word
						+ "%' and a.del_yn = 'N' and b.del_yn = 'N' start with ref is null connect by prior a.qnum = ref ORDER SIBLINGS BY a.qnum desc) aa) where rnum >= ? and rnum <= ?";
			} else if (kind.equals("pname")) {
				sql = "select * from(select aa.*,rownum rnum from(select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname,level from qna a inner join product b on a.pnum = b.pnum where b.name like '%"
						+ word
						+ "%' and a.del_yn = 'N' and b.del_yn = 'N' start with ref is null connect by prior a.qnum = ref ORDER SIBLINGS BY a.qnum desc) aa) where rnum >= ? and rnum <= ?";
			} else if (kind.equals("cname")) {
				sql = "select * from(select aa.*,rownum rnum from(select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname,level from qna a inner join product b on a.pnum = b.pnum where a.del_yn = 'N' and b.del_yn = 'N' and b.type in(select type from category where name like '%"
						+ word
						+ "%') start with ref is null connect by prior a.qnum = ref ORDER SIBLINGS BY a.qnum desc) aa) where rnum >= ? and rnum <= ?";
			} else if (kind.equals("title")) {
				sql = "select * from(select aa.*,rownum rnum from(select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname,level from qna a inner join product b on a.pnum = b.pnum where a.title like '%"
						+ word
						+ "%' and a.del_yn = 'N' and b.del_yn = 'N' start with ref is null connect by prior a.qnum = ref ORDER SIBLINGS BY a.qnum desc) aa) where rnum >= ? and rnum <= ?";
			} else {
				sql = "select * from(select aa.*,rownum rnum from(select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname, b.name pname,level from qna a inner join product b on a.pnum = b.pnum where a.del_yn = 'N' and b.del_yn = 'N' start with ref is null connect by prior a.qnum = ref ORDER SIBLINGS BY a.qnum desc) aa) "
						+ "where rnum >= ? and rnum <= ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<QnaAdminDto> list = new ArrayList<QnaAdminDto>();
			while (rs.next()) {
				int qnum = rs.getInt("qnum");
				String cname = rs.getString("cname");
				String pname = rs.getString("pname");
				String title = rs.getString("title");
				String name = rs.getString("name");
				Date reg_date = rs.getDate("reg_date");
				String content = rs.getString("content");
				int pnum = rs.getInt("pnum");
				int level = rs.getInt("level");
				list.add(new QnaAdminDto(qnum, cname, pname, title, name, reg_date, content, pnum, level));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int insAns(QnaDto dto) {
		Connection con = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			con.setAutoCommit(false);
			String sql2 = "update qna set qnum=qnum+1,ref=ref+1 where qnum >= ?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, dto.getQnum());
			int n = pstmt2.executeUpdate();
			if (n > 0) {
				String sql = "insert into qna values(?,?,?,?,'관리자',?,?,?,sysdate,'N','N')";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getPnum());
				pstmt.setInt(2, dto.getNum());
				pstmt.setInt(3, dto.getQnum());
				pstmt.setString(4, dto.getId());
				pstmt.setString(5, dto.getTitle());
				pstmt.setString(6, dto.getContent());
				pstmt.setInt(7, dto.getRef() + 1);
				return pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
			return -1;
		} finally {
			JDBCUtil.close(pstmt2);
			JDBCUtil.close(null, pstmt, con);
		}
		return 0;
	}

	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select nvl(max(qnum),0) maxnum from qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("maxnum");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int selQnaCount(String kind, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "";
			if (kind.equals("")) {
				sql = "select nvl(count(*),0) cnt from qna";
			} else if (kind.equals("pname")) {
				sql = "select nvl(count(*),0) cnt from qna a inner join product b on a.pnum = b.pnum where b.name like '%"
						+ word + "%'";
			} else if (kind.equals("cname")) {
				sql = "select nvl(count(*),0) cnt from qna a inner join product b on a.pnum = b.pnum where b.type in(select type from category where name like '%"
						+ word + "%')";
			} else {
				sql = "select nvl(count(*),0) cnt from qna where name like '%" + word + "%'";
			}
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

	public ArrayList<QnaAdminDto> selUnanswerList(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnaAdminDto> list = new ArrayList<QnaAdminDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from(select aa.*, rownum rnum from(select a.*,(select name from category where cnum = b.cnum and type = b.type) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where a.qnum not in(select ref from qna where ref is not null) and a.ref is null and a.del_yn = 'N' order by qnum desc) aa) where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int qnum = rs.getInt("qnum");
				String cname = rs.getString("cname");
				String pname = rs.getString("pname");
				String title = rs.getString("title");
				String name = rs.getString("name");
				Date reg_date = rs.getDate("reg_date");
				String content = rs.getString("content");
				int pnum = rs.getInt("pnum");
				list.add(new QnaAdminDto(qnum, cname, pname, title, name, reg_date, content, pnum, -1));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int selUnanswerCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select nvl(count(*),0) cnt from qna a where a.qnum not in(select ref from qna where ref is not null) and a.ref is null and a.del_yn = 'N'";
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
