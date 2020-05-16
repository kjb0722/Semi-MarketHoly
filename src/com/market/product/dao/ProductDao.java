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

	// ����¡���� �޼ҵ� (getMaxNum,getCount)
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

	public int getCount(int cnum, int type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = JDBCUtil.getConn();
			if (type == -1) {
				sql = "select NVL(count(pnum),0) cnt from product where type=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cnum);
			} else {
				sql = "select NVL(count(pnum),0) cnt from product where cnum=? and type=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cnum);
				pstmt.setInt(2, type);
			}
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

	// 상세페이지
	public ProductDto getDetail(int pnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDto dto = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select * from product where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				Date reg_date = rs.getDate("reg_date");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String thumb_org = rs.getString("thumb_org");
				String thumb_save = rs.getString("thumb_save");
				String detail_org = rs.getString("detail_org");
				String detail_save = rs.getString("detail_save");
				String description = rs.getString("description");
				String del_yn = rs.getString("del_yn");
				dto = new ProductDto(pnum, 0, name, reg_date, price, stock, 0, thumb_org, thumb_save, description,
						detail_org, detail_save, del_yn);
			}
			return dto;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public ArrayList<ProductDto> getList(int startRow, int endRow, String list_filter, int cnum, int type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		try {
			con = JDBCUtil.getConn();
			if (type == -1) {
				sql = "select * from(select aa.*,rownum rnum from (select * from product where type=? order by reg_date desc)"
						+ "aa)where rnum>=? and rnum<=? order by reg_date desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cnum);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);

			}else if(cnum==0 && type==0){
				sql = "select * from(select aa.*,rownum rnum from (select * from product order by reg_date desc)"
						+ "aa)where rnum>=? and rnum<=? order by reg_date desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
			}else {

				sql = "select * from(select aa.*,rownum rnum from (select * from product where cnum=? and type=? order by reg_date desc)"
						+ "aa)where rnum>=? and rnum<=? order by reg_date desc";

				/*
				 * if(list_filter==null || list_filter.equals("new")) { sql +=
				 * " order by reg_date desc"; }else if(list_filter.equals("best")) { sql +=
				 * " order by "; }else if(list_filter.equals("lowprice")) { sql +=
				 * " order by price"; }else if(list_filter.equals("highprice")){ sql +=
				 * " order by price desc"; }
				 */

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cnum);
				pstmt.setInt(2, type);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);

			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pnum = rs.getInt("pnum");
				String name = rs.getString("name");
				Date reg_date = rs.getDate("reg_date");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String thumb_org = rs.getString("thumb_org");
				String thumb_save = rs.getString("thumb_save");
				String description = rs.getString("description");
				String del_yn = rs.getString("del_yn");
				list.add(new ProductDto(pnum, cnum, name, reg_date, price, stock, type, thumb_org, thumb_save,
						description, null, null, del_yn));

			}
			return list;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	//신상품 리스트
	public ArrayList<ProductDto> getNewList(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		try {
			con = JDBCUtil.getConn();

			String sql = "select * from(select aa.*,rownum rnum from "
					+ "(select * from product where reg_date between sysdate-7 and sysdate "
					+ "order by reg_date desc)"
					+ "aa)where rnum>=? and rnum<=? order by reg_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pnum = rs.getInt("pnum");
				String name = rs.getString("name");
				Date reg_date = rs.getDate("reg_date");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String thumb_org = rs.getString("thumb_org");
				String thumb_save = rs.getString("thumb_save");
				String description = rs.getString("description");
				String del_yn = rs.getString("del_yn");
				list.add(new ProductDto(pnum,name,reg_date,price,stock,thumb_org
						 ,thumb_save,description,del_yn));

			}
			return list;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

}