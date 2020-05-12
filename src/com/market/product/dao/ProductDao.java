package com.market.product.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.market.db.JDBCUtil;
import com.market.product.dto.ProductDto;

public class ProductDao {

	//페이징해줄 메소드 (getMaxNum,getCount)
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select max(nvl(num,0)) maxnum from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("maxnum");
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
	// 전체글의 갯수 리턴
	public int getCount(String op,String keyword) { 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select NVL(count(num),0) cnt from product";
			if(op!=null &&!op.equals("")) {
				sql += " where " + op + " like '%"+ keyword + "%'";

			}
			pstmt = con.prepareStatement(sql);
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
	
	//전체상품을 가져오는 getlist
	public ArrayList<ProductDto> getList(int startRow,int endRow,String filter){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
			String sql="select * from product p,category c where p.cnum=c.cnum";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum=rs.getInt("pnum");
				int cnum=rs.getInt("cnum");
				int cartnum=rs.getInt("cartnum");
				String name=rs.getString("name");
				Date reg_date=rs.getDate("reg_date");
				int price=rs.getInt("price");
				int stock=rs.getInt("stock");
				int type=rs.getInt("type");
				String thumb_org=rs.getString("thumb_org");
				String thumb_save=rs.getString("thumb_save");
				String description=rs.getString("description");
				String del_yn=rs.getString("del_yn");
				ProductDto list=new ProductDto(pnum,cnum,cartnum,name,reg_date,
						price,stock,type,thumb_org,thumb_save,description,null,null,del_yn);
				
			}
			 
			return list;
			
			
		}catch(SQLException se) {
			se.getStackTrace();
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	/*검색한 상품 가져오는 select
	public ArrayList<ProductDto> search(string op,string keyword){
	}
	상세설명 가져오기 (해당상품 이미지,제목 클릭시)
	public ArrayList<ProductDto> detail(pnum){
		
	}
	*/
}