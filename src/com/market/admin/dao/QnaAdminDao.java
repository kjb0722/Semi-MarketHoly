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

	public ArrayList<QnaAdminDto> selQnaList(String kind, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "";
			if (kind.equals("")) {
				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
			} else if (kind.equals("pname")) {
				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where b.name like '%"
						+ word + "%' and a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
			} else if (kind.equals("cname")) {
				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where type in(select type from category where name like '%"
						+ word + "%' and a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
			} else {
				sql = "select a.*,(select name from category where cnum in(b.cnum) and type in(b.type)) cname,b.name pname from qna a inner join product b on a.pnum = b.pnum where "
						+ kind + " like '%" + word + "%' and a.del_yn = 'N' and b.del_yn = 'N' order by qnum desc";
			}
			pstmt = con.prepareStatement(sql);
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
				list.add(new QnaAdminDto(qnum, cname, pname, title, name, reg_date, content, pnum));
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
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into qna(?,?,?,?,?,?,?,?,sysdate,'N','N')";
			pstmt = con.prepareStatement(sql);
//			public QnaDto(int pnum, int num, int qnum, String id, String name, String title, String content, int ref,
//					Date reg_date, String del_yn, String locker) {
			pstmt.setInt(1, dto.getPnum());
			pstmt.setInt(2, dto.getNum());
			pstmt.setInt(3, dto.getQnum());
			pstmt.setString(4, dto.getId());
			pstmt.setString(5, dto.getName());
			pstmt.setString(6, dto.getTitle());
			pstmt.setString(7, dto.getContent());
			pstmt.setInt(8, dto.getRef());
			pstmt.setString(9, x);
			pstmt.setString(10, x);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
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
}
