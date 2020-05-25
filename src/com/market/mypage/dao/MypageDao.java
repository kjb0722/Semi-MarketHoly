package com.market.mypage.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.controller.OrderListController;
import com.market.db.JDBCUtil;
import com.market.mypage.dto.MypagePointDto;
import com.market.mypage.dto.MypageReviewDto;
import com.market.mypage.dto.OrderDetailDto;
import com.market.mypage.dto.OrderListDto;
import com.market.qna.dto.QnaDto;
import com.market.review.dao.ReviewDao;

public class MypageDao {
	private static MypageDao instance = new MypageDao();
	private MypageDao() {}
	public static MypageDao getInstance() {
		return instance;
	}
	
	
	public int cancleOrder(int onums) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		
		
		try {
			con = JDBCUtil.getConn();
			
			String sql2 ="select * from orders where onum=?";
			pstmt2 =  con.prepareStatement(sql2);
			pstmt2.setInt(1, onums);
			rs = pstmt2.executeQuery();
			
			while(rs.next()) {
				if (rs.getInt("status") == 6) {
					return 0;
				}
			}
			pstmt2.close();
			
			
			String sql = "update orders set status=6 where onum=? and status<=2";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, onums);
			int n =pstmt.executeUpdate();			
			return n;
			
		
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}		
	}
	

	public ArrayList<OrderDetailDto> orderDetail(int onums, int opnums) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con= JDBCUtil.getConn();
			String sql = "select o.onum, o.num, op.opnum, o.status, o.pay_yn, o.reg_date, o.end_date, o.id, o.price, o.use_point,\r\n" + 
					"o.sale_price,o.pay_way, op.pnum, op.pname, op.ea, op.price price2\r\n" + 
					"from orders o, order_product op \r\n" + 
					"where o.onum = op.onum and o.onum=? and op.opnum=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, onums);
			pstmt.setInt(2, opnums);
			rs = pstmt.executeQuery();
			
			ArrayList<OrderDetailDto> list = new ArrayList<OrderDetailDto>();
			while(rs.next()) {
				int onum = rs.getInt("onum");
				int	num = rs.getInt("num");
				int opnum =	rs.getInt("opnum");
				int status = rs.getInt("status");
				String pay_yn = rs.getString("pay_yn");
				Date reg_date = rs.getDate("reg_date");
				Date end_date = rs.getDate("end_date");
				String id = rs.getString("id");
				int price =	rs.getInt("price");
				int use_point= rs.getInt("use_point");
				int sale_price=	rs.getInt("sale_price");
				int pay_way= rs.getInt("pay_way");
				int pnum= rs.getInt("pnum");
				String pname = rs.getString("pname");
				int ea= rs.getInt("ea");
				int price2= rs.getInt("price2");				
				OrderDetailDto dto = new OrderDetailDto(onum, num, opnum, status, pay_yn, reg_date, end_date, id, price, use_point, sale_price, pay_way, pnum, pname, ea, price2);
				list.add(dto);
			}
			System.out.println(list.get(0).getOnum());
			return list;
			
			
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
			
		}
		
		
	}
	
	
	
	
	
	
	
	public ArrayList<OrderListDto> orderList( String ids){
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			con = JDBCUtil.getConn();
			String sql ="select p.pnum,o.onum,op.opnum,o.status,o.pay_yn,o.reg_date,o.end_date,p.thumb_save,p.name,p.description,op.price,op.ea "+ 
					"from orders o,product p,order_product op "+ 
					" where o.onum=op.onum and op.pnum=p.pnum and id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			rs = pstmt.executeQuery();
			ArrayList<OrderListDto> list = new ArrayList<OrderListDto>();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				int onum = rs.getInt("onum");
				int opnum = rs.getInt("opnum");
				int status = rs.getInt("status");
				String pay_yn = rs.getString("pay_yn");
				Date reg_date = rs.getDate("reg_date");
				Date end_date = rs.getDate("end_date");
				String thum_save = rs.getString("thumb_save");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int price= rs.getInt("price");
				int ea = rs.getInt("ea");
				OrderListDto dto = new OrderListDto(pnum,onum,opnum,status,pay_yn,reg_date,end_date, thum_save, name, description, price, ea);
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
	
	
	
	public int getCount2(String ids) {// 전체글의 갯수 리턴
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select NVL(count(qnum),0) cnt from qna where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
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
	
	public ArrayList<QnaDto> mypageQna(String ids,int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from(select bb.*,rownum rnum2 from (select * from(select aa.*,rownum rnum from (select level,a.* from qna a where del_yn = 'N' start with ref is null connect by prior qnum = ref ORDER SIBLINGS BY qnum desc) aa) where id=?) bb)"
					+ "where rnum2>=? and rnum2<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			ArrayList<QnaDto> list = new ArrayList<QnaDto>();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				int num = rs.getInt("num");
				int qnum = rs.getInt("qnum");
				String id  =rs.getString("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int ref = rs.getInt("ref");
				Date reg_date = rs.getDate("reg_date");
				String del_yn = rs.getString("del_yn");
				String locker =rs.getString("locker");
				int level = rs.getInt("level");
				int rnum2 = rs.getInt("rnum2");
				QnaDto dto = new QnaDto(pnum, pnum, qnum, id, name, title, content, ref, reg_date, del_yn, locker, level, rnum2); 			
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
	
	
	public MypagePointDto pointList(String ids) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConn();
			String sql = "select id,name,point,rating from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int point = rs.getInt("point");
				int rating = rs.getInt("rating");
			
				MypagePointDto dto = new MypagePointDto(id, name, point, rating);
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
	
	
	
	
	
}
