package com.market.review.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.db.JDBCUtil;
import com.market.member.dto.MemberDto;
import com.market.mypage.dto.MypageReviewDto;
import com.market.review.dto.ReviewDto;
import com.market.review.dto.ReviewNumbersDto;


public class ReviewDao {
	private static ReviewDao instance = new ReviewDao();
	private ReviewDao() {}
	public static ReviewDao getInstance() {
		return instance;
	}
	
	
	public ReviewNumbersDto getNumbers(String ids,int pnums) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConn();
			String sql ="select o.onum, op.pnum from orders o, order_product op where id=? and status=5 and pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			pstmt.setInt(2, pnums);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int onum=rs.getInt("onum");
				int pnum=rs.getInt("pnum");
				ReviewNumbersDto dto = new ReviewNumbersDto(onum, pnum);
				return dto;
			}
			return null;		
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
		
		
		
	}
	
	
	
	
	
	
	public int writeReview(ReviewDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = JDBCUtil.getConn();
			String sql = "insert into review values(?,?,?,seq_review_num.nextval,?,?,?,?,sysdate,?,?,'N')";
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
			
			int n =pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JDBCUtil.close(null, pstmt, con);
		}	
	}
	
	
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
			String sql="select NVL(count(*),0) from review";
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt(1);
				return cnt;
			}
			return 0;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}	
	}
	
	
	
	public ArrayList<ReviewDto> listReview(int startRow,int endRow,int pnums){ 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConn();
		
			String sql ="select * from"
					+ "(select aa.*,rownum rnums from "
					+ "(select * from review order by num desc)aa) "
					+ "where rnums>=? and rnums<=? and pnum=?";
			
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, pnums);
			rs = pstmt.executeQuery();
			
			
			
			ArrayList<ReviewDto> list = new ArrayList<ReviewDto>();
			while(rs.next()) {
				int onum = rs.getInt("onum");
				int pnum = rs.getInt("pnum");
				int num = rs.getInt("num");
				int rnum = rs.getInt("rnum");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date regdate = rs.getDate("reg_date");
				String orgfilename = rs.getString("orgfilename");
				String savefilename = rs.getString("savefilename");
				String del_yn = rs.getString("del_yn");
				ReviewDto dto = new ReviewDto(onum, pnum, num, rnum, id, name, title, content, regdate, orgfilename, savefilename, del_yn);
				
				list.add(dto);
			}
			return list;
			
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	
	
	
	
}
