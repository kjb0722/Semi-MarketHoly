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

	public int getCount() { 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select NVL(count(num),0) cnt from product";
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
	//필터선택 x 디폴트값 > 최신순
	public ArrayList<ProductDto> getList(int startRow,int endRow,String list_filter){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ProductDto> list=new ArrayList<ProductDto>();
		try {
			con=JDBCUtil.getConn();
			String sql = "select * from (select aa.*,rownum rnum from ("
					+ "select * from product order by ref desc,step asc)aa" + ") where rnum>=? and rnum<=?";

			//String sql="select * from product p,category c where p.cnum=c.cnum";
			if(list_filter==null || list_filter.equals("new")) {
				sql += " order by reg_date desc";
			}else if(list_filter.equals("best")) {
				sql += " order by ";  //판매량 많은순....어케가져옴 ㅠ
 			}else if(list_filter.equals("lowprice")) {
				sql += " order by price";
			}else if(list_filter.equals("highprice")){
				sql += " order by price desc";
			}
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
	/*검색한 상품 가져오는 select
	public ArrayList<ProductDto> search(string op,string keyword){
	}
	상세설명 가져오기 (해당상품 이미지,제목 클릭시)
	public ArrayList<ProductDto> detail(pnum){
		
	}
	*/
}