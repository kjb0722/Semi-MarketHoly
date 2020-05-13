
package com.market.member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<MemberDto> getList(String ids){
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		
		try {
			con = JDBCUtil.getConn();
			String sql ="select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			rs = pstmt.executeQuery();
			
			ArrayList<MemberDto> list = new ArrayList<MemberDto>();
			while(rs.next()) {
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				int rating = rs.getInt("rating");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				String phone = rs.getString("phone");
				int gender = rs.getInt("gender");
				String addr = rs.getString("addr");
				Date reg_date = rs.getDate("reg_date");
				int point = rs.getInt("point");
				String del_yn = rs.getString("del_yn");
				Date del_date =rs.getDate("del_date");
				MemberDto dto = new MemberDto(num, id, pwd, name, rating, email, birth, phone, gender, addr, reg_date, point, del_yn, del_date);
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
	
	
	
	
	public ArrayList<MemberDto> login(String ids,String pwds) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con= JDBCUtil.getConn();
			String sql = "select * from member where id=? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			pstmt.setString(2, pwds);
			rs = pstmt.executeQuery();
			
			
			ArrayList<MemberDto> list = new ArrayList<MemberDto>();
			while(rs.next()){
				if(rs.getString("del_yn").equals("N")) {
					int num = rs.getInt("num");
					String id = rs.getString("id");
					String pwd = rs.getString("pwd");
					String name = rs.getString("name");
					int rating = rs.getInt("rating");
					String email = rs.getString("email");
					String birth = rs.getString("birth");
					String phone = rs.getString("phone");
					int gender = rs.getInt("gender");
					String addr = rs.getString("addr");
					Date reg_date = rs.getDate("reg_date");
					int point = rs.getInt("point");
					String del_yn = rs.getString("del_yn");
					Date del_date =rs.getDate("del_date");
					MemberDto dto = new MemberDto(num, id, pwd, name, rating, email, birth, phone, gender, addr, reg_date, point, del_yn, del_date);
					list.add(dto);
				}else if(rs.getString("del_yn").equals("Y")) {
					return null;
				}	
			}
			return list;
			
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
		
		
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
