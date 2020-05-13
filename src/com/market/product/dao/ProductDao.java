package com.market.product.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.db.JDBCUtil;
import com.market.product.dto.ProductDto;

public class ProductDao {

	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select max(nvl(pnum,0)) maxnum from product";
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

	public int getCount() { 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select NVL(count(pnum),0) cnt from product";
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

	public ArrayList<ProductDto> getList(int startRow,int endRow,String list_filter){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ProductDto> list=new ArrayList<ProductDto>();
		try {
			con=JDBCUtil.getConn();
			String sql = "select * from(select aa.*,rownum rnum from (" + 
					"select * from product order by reg_date asc)aa)" + 
					"where rnum>=? and rnum<=?";

			if(list_filter==null || list_filter.equals("new")) {
				sql += " order by reg_date desc";
			}else if(list_filter.equals("best")) {
				sql += " order by ";
 			}else if(list_filter.equals("lowprice")) {
				sql += " order by price";
			}else if(list_filter.equals("highprice")){
				sql += " order by price desc";
			}

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
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
				list.add(new ProductDto(pnum,cnum,cartnum,name,reg_date,
						price,stock,type,thumb_org,thumb_save,description,null,null,del_yn));
				
			}
			return list;
		
			
		}catch(SQLException se) {
			se.getStackTrace();
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

}