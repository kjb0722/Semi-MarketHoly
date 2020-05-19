package com.market.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.QnaAdminDto;
import com.market.db.JDBCUtil;

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
				list.add(new QnaAdminDto(qnum, cname, pname, title, name, reg_date, content));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int insAns(String title, String content) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into qna values()";
			pstmt = con.prepareStatement(sql);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}
}
