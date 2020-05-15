package com.market.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.market.db.JDBCUtil;
import com.market.member.dao.MemberDao;
import com.market.review.dto.ReviewDto;

public class ReviewDao {
	private static ReviewDao instance = new ReviewDao();
	private ReviewDao() {}
	public static ReviewDao getInstance() {
		return instance;
	}
	
	public int writeReview(ReviewDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into review values(?,?,?,seq_review_num.nextval,?,?,?,?,sysdate,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getOnum());
			pstmt.setInt(2, dto.getPnum());
			pstmt.setInt(3, dto.getNum());
			pstmt.setString(4, dto.getId());
			pstmt.setString(5, dto.getName());
			pstmt.setString(6, dto.getTitle());
			pstmt.setString(7, dto.getContent());
			pstmt.setString(8, dto.getOrgfilename());
			pstmt.setString(9, dto.getSavefilename());
			pstmt.setString(10, dto.getDel_yn());
			pstmt.setString(11, dto.getPwd());
			
			int n =pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JDBCUtil.close(null, pstmt, con);
		}	
	}
	

	
	
	
	
}
