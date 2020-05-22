package com.market.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.CategoryListDto;
import com.market.common.dto.CommonDto;
import com.market.db.JDBCUtil;

public class CommonDao {
	public static CommonDao dao = new CommonDao();

	public static CommonDao getInstance() {
		return dao;
	}

	private CommonDao() {

	}

	public ArrayList<CommonDto> selComList(String pType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommonDto> list = new ArrayList<CommonDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from common where type=? order by conum";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String type = rs.getString("type");
				int conum = rs.getInt("conum");
				String name = rs.getString("name");
				String val = rs.getString("val");
				list.add(new CommonDto(type, conum, name, val));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
