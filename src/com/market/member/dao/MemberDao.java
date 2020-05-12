package com.market.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import com.market.db.JDBCUtil;
import com.market.member.dto.MemberDto;

import jdk.nashorn.api.scripting.JSObject;

public class MemberDao {
	private static MemberDao instance = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {
		return instance;
	}
	

	public boolean checkId(String id) {
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		 
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				if(rs.getString("del_yn").equals("N")) {
					check=true;	
				}else if(rs.getString("del_yn").equals("Y")){
					check=false;
				}
			}	
			return check;	
		}catch(SQLException se) {
			se.printStackTrace();
			return check;	
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
		
	}
	
	
	
	
	public int join(MemberDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
	
		try {
			con =JDBCUtil.getConn();
			String sql = "insert into member values(seq_member_num.nextval,?,?,?,?,?,?,?,?,?,sysdate,?,?,'')";
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
			pstmt.setInt(10, dto.getPoint());
			pstmt.setString(11, dto.getDel_yn());
		
			int n = pstmt.executeUpdate();
			return n;
			
		}catch(SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}
	
	public boolean checkEmail(String email) {
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				check = true;
			}
			return check;
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return false;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	
	
}
