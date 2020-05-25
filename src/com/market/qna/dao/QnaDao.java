package com.market.qna.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.db.JDBCUtil;
import com.market.qna.dto.QnaDto;
import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewDto;

public class QnaDao {
	private static QnaDao instance = new QnaDao();

	private QnaDao() {
	}

	public static QnaDao getInstance() {
		return instance;
	}

	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select NVL(max(qnum),0) maxnum from qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("maxnum");
			} else {
				return 0;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int writeQna(QnaDto dto) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = JDBCUtil.getConn();
			int boardNum = getMaxNum() + 1;// 등록할 글번호 구하기
			int qnum = dto.getPnum();
			int ref = dto.getRef();
			if (qnum == 0) { // 새글인 경우
				ref = boardNum;
			}

			/*
			 * else { //답글인 경우 String sql1= "update qna set ref=1 where ref=?";
			 * pstmt1=con.prepareStatement(sql1); pstmt1.setInt(1,ref);
			 * pstmt1.executeUpdate(); }
			 */

			String sql2 = "insert into qna values(?,?,?,?,?,?,?,null,sysdate,'N',?)";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, dto.getPnum());
			pstmt2.setInt(2, dto.getNum());
			pstmt2.setInt(3, boardNum);
			pstmt2.setString(4, dto.getId());
			pstmt2.setString(5, dto.getName());
			pstmt2.setString(6, dto.getTitle());
			pstmt2.setString(7, dto.getContent());
			pstmt2.setString(8, dto.getLocker());
			pstmt2.executeUpdate();
			return 1;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(pstmt1);
			JDBCUtil.close(pstmt2);
			JDBCUtil.close(con);
		}
	}

	
	public int getCount(int pnums) {// 전체글의 갯수 리턴
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select NVL(count(qnum),0) cnt from qna where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnums);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			} else {
				return 0;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public ArrayList<QnaDto> list(int startRow, int endRow, int pnums) {
		String sql = "select * from(select bb.*,rownum rnum2 from (select * from(select aa.*,rownum rnum from (select level,a.* from qna a where del_yn = 'N' start with ref is null connect by prior qnum = ref ORDER SIBLINGS BY qnum desc) aa) where pnum=?) bb)where rnum2>=? and rnum2<=?";
		//String sql = "select * from(select aa.*,rownum rnum2 from(select level,a.* from qna a where del_yn = 'N' and pnum = ? start with ref is null connect by prior qnum = ref ORDER SIBLINGS BY qnum desc) aa) where rnum2 >= ? and rnum2 <= ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnums);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<QnaDto> list = new ArrayList<QnaDto>();
			
			while (rs.next()) {
				int pnum = rs.getInt("pnum");
				int num = rs.getInt("num");
				int qnum = rs.getInt("qnum");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int ref = rs.getInt("ref");
				Date reg_date = rs.getDate("reg_date");
				String del_yn = rs.getString("del_yn");
				String locker = rs.getString("locker");
				int level = rs.getInt("level");
				int rnum2 = rs.getInt("rnum2");
				QnaDto dto = new QnaDto(pnum, num, qnum, id, name, title, content, ref, reg_date, del_yn, locker,
						level,rnum2);
				list.add(dto);
			}
			
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

}
