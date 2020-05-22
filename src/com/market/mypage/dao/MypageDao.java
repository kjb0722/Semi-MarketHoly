package com.market.mypage.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.controller.OrderListController;
import com.market.db.JDBCUtil;
import com.market.mypage.dto.MypageReviewDto;
import com.market.mypage.dto.OrderListDto;
import com.market.review.dao.ReviewDao;

public class MypageDao {
	private static MypageDao instance = new MypageDao();
	private MypageDao() {}
	public static MypageDao getInstance() {
		return instance;
	}
	
	public ArrayList<OrderListDto> orderList(String ids){
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			con = JDBCUtil.getConn();
			String sql ="select o.status,o.pay_yn,o.end_date,p.thumb_save,p.name,p.description,op.price,op.ea "+ 
					"from orders o,product p,order_product op "+ 
					" where o.onum=op.onum and op.pnum=p.pnum and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			rs = pstmt.executeQuery();
			ArrayList<OrderListDto> list = new ArrayList<OrderListDto>();
			while(rs.next()) {
				int status = rs.getInt("status");
				String pay_yn = rs.getString("pay_yn");
				Date end_date = rs.getDate("end_date");
				String thum_save = rs.getString("thumb_save");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int price= rs.getInt("price");
				int ea = rs.getInt("ea");
				OrderListDto dto = new OrderListDto(status,pay_yn,end_date, thum_save, name, description, price, ea);
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
	
	
	
	
	public ArrayList<MypageReviewDto> mypageReview(String ids) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = JDBCUtil.getConn();
			String sql = "select r.onum,r.pnum,r.num,r.rnum,r.id,r.name,r.title,r.content,r.reg_date,r.orgfilename,r.savefilename,r.del_yn,p.name pname " + 
					"from review r,product p "
					+ "where r.pnum = p.pnum and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			rs =pstmt.executeQuery();
		
		ArrayList<MypageReviewDto> list = new ArrayList<MypageReviewDto>();	
		while(rs.next()) {
			int onum = rs.getInt("onum");
			int pnum = rs.getInt("pnum");
			int num = rs.getInt("num");
			int rnum = rs.getInt("rnum");
			String id = rs.getString("id");
			String name = rs.getString("name");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Date regdate =  rs.getDate("reg_date");
			String orgfilename = rs.getString("orgfilename");
			String savefilename = rs.getString("savefilename");
			String del_yn = rs.getString("del_yn");
			String pname = rs.getString("pname");

			MypageReviewDto dto2 = new MypageReviewDto(onum, pnum, num, rnum, id, name, title, content, regdate, orgfilename, savefilename, del_yn, pname);
			list.add(dto2);
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
