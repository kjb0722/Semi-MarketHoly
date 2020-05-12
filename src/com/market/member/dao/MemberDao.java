package com.market.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
		
		
	}
	
	
	
	
	
}
