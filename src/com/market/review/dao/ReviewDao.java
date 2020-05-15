package com.market.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.market.db.JDBCUtil;
import com.market.member.dao.MemberDao;

public class ReviewDao {
	private static ReviewDao instance = new ReviewDao();
	private ReviewDao() {}
	public static ReviewDao getInstance() {
		return instance;
	}
	
	public int writeReview() {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			
			
			
			
		
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			JDBCUtil.close(null, pstmt, con);
			
		}
		
		
		
	}
	
	
	
	
	
	
}
