package com.market.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.market.db.JDBCUtil;
import com.market.member.dto.MemberDto;

public class MemberDao {
	private static MemberDao instance = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {
		return instance;
	}
	

	public int join(MemberDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con =JDBCUtil.getConn();
			String sql = "insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate,?,'',?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getRating());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getBirth());
			pstmt.setString(7, dto.getPhone());
			pstmt.setInt(8, dto.getGender());
			pstmt.setString(9,dto.getAddr());
			pstmt.setString(10, dto.getDel_yn());
			pstmt.setInt(11, dto.getPoint());
			
			int n = pstmt.executeUpdate();
			return n;
			
		}catch(SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}
	
	
	
	
	
}
