package com.market.qna.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.db.JDBCUtil;
import com.market.qna.dto.QnaDto;
import com.market.review.dao.ReviewDao;
import com.market.review.dto.ReviewDto;


public class QnaDao {
	private static QnaDao instance = new QnaDao();
	private QnaDao() {}
	public static QnaDao getInstance() {
		return instance;
	}
	
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
			String sql="select NVL(max(num),0) maxnum from qna";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxnum");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}	
	
	
	
	public int writeQna(QnaDto dto) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=JDBCUtil.getConn();
			int boardNum=getMaxNum()+1;//등록할 글번호 구하기
			int qnum= dto.getPnum();
			int ref= dto.getRef();
			int lev= dto.getLev();
			int step= dto.getStep();
			if(qnum==0) { //새글인 경우
				ref=boardNum;
			}else { //답글인 경우
				String sql1=
						"update qna set step=step+1 where ref=? and step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1,ref);
				pstmt1.setInt(2,step);
				pstmt1.executeUpdate();	
				lev += 1;
				step += 1;
			}		
			String sql2="insert into qna values(?,?,?,?,?,?,?,?,?,?,sysdate,'N',?)";	
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, dto.getPnum());
			pstmt2.setInt(2, dto.getNum());
			pstmt2.setInt(3,boardNum);
			pstmt2.setString(4,dto.getId());
			pstmt2.setString(5,dto.getName());
			pstmt2.setString(6,dto.getTitle());
			pstmt2.setString(7, dto.getContent());
			pstmt2.setInt(8,ref);
			pstmt2.setInt(9,lev);
			pstmt2.setInt(10,step);
			pstmt2.setString(11, dto.getPwd());
			pstmt2.executeUpdate();
			return 1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JDBCUtil.close(pstmt1);
			JDBCUtil.close(pstmt2);
			JDBCUtil.close(con);
		}
	}

	
	public int getCount() {//전체글의 갯수 리턴
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
			String sql="select NVL(count(num),0) cnt from qna";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}	
	public ArrayList<QnaDto> list(int startRow,int endRow){
		String sql="select * from " + 
				"(" + 
				"  select aa.*,rownum rnum from " + 
				"  ( " + 
				"	select * from qna order by ref desc,step asc " + 
				"  )aa " + 
				") " + 
				"where rnum>=? and rnum<=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			ArrayList<QnaDto> list=new ArrayList<QnaDto>();
			while(rs.next()) {
				
				int pnum = rs.getInt("pnum");
				int num= rs.getInt("num");
				int qnum = rs.getInt("qnum");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				Date regdate = rs.getDate("regdate");
				String del_yn = rs.getString("del_yn");
				String pwd = rs.getString("pwd");
				
				QnaDto dto = new QnaDto(pnum, num, qnum, id, name, title, content, ref, lev, step, regdate, del_yn, pwd);	
				list.add(dto);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}	
	}

}
