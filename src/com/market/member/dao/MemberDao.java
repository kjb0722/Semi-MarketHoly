
package com.market.member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.db.JDBCUtil;
import com.market.member.dto.MemberDto;

public class MemberDao {
	private static MemberDao instance = new MemberDao();

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		return instance;
	}

	public int delAccount(String ids) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "update member set del_yn='Y',del_date=sysdate where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			int n = pstmt.executeUpdate();

			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}

	}

	public MemberDto getDto(String ids) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			rs = pstmt.executeQuery();

			MemberDto dto = new MemberDto();
			while (rs.next()) {
				if (rs.getString("del_yn").equals("N")) {
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
					Date del_date = rs.getDate("del_date");
					dto = new MemberDto(num, id, pwd, name, rating, email, birth, phone, gender, addr, reg_date, point,
							del_yn, del_date);
				} else if (rs.getString("del_yn").equals("Y")) {
					return null;
				}
			}
			return dto;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int updateInfo2(String id, String name, String email, String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "update member set name=?,email=?,phone=? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setString(4, id);
			int n = pstmt.executeUpdate();
			return n;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}

	}

	public int updateInfo(String id, String curPwd, String nextPwd, String checkPwd, String name, String email,
			String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "update member set pwd=?,name=?,email=?,phone=? where id=? and pwd=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nextPwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			pstmt.setString(5, id);
			pstmt.setString(6, curPwd);
			int n = pstmt.executeUpdate();

			return n;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}

	}

	public MemberDto intoChangeInfo(String ids, String pwds) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where id=? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			pstmt.setString(2, pwds);
			rs = pstmt.executeQuery();

			MemberDto dto = new MemberDto();
			while (rs.next()) {
				if (rs.getString("del_yn").equals("N")) {
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
					Date del_date = rs.getDate("del_date");
					dto = new MemberDto(num, id, pwd, name, rating, email, birth, phone, gender, addr, reg_date, point,
							del_yn, del_date);
				}
			}
			return dto;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}

	}

	public MemberDto getList(String ids) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where id=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			rs = pstmt.executeQuery();

			if (rs.next()) {
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
				Date del_date = rs.getDate("del_date");
				MemberDto dto = new MemberDto(num, id, pwd, name, rating, email, birth, phone, gender, addr, reg_date,
						point, del_yn, del_date);
				return dto;
			}
			return null;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public MemberDto login(String ids, String pwds) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where id=? and pwd=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ids);
			pstmt.setString(2, pwds);
			rs = pstmt.executeQuery();

			MemberDto dto = new MemberDto();
			while (rs.next()) {
				if (rs.getString("del_yn").equals("N")) {
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
					Date del_date = rs.getDate("del_date");
					dto = new MemberDto(num, id, pwd, name, rating, email, birth, phone, gender, addr, reg_date, point,
							del_yn, del_date);
				} else if (rs.getString("del_yn").equals("Y")) {
					return null;
				}
			}
			return dto;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public boolean checkId(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;

		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("del_yn").equals("N")) {
					check = true;
				} else if (rs.getString("del_yn").equals("Y")) {
					check = false;
				}
			}
			return check;
		} catch (SQLException se) {
			se.printStackTrace();
			return check;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}

	}

	public String findPwd(String names, String ids, String emails) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "select pwd,del_yn from member where name=? and id=? and email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, names);
			pstmt.setString(2, ids);
			pstmt.setString(3, emails);

			rs = pstmt.executeQuery();
			String pwd = "이름 or 아이디 or email이 틀렸습니다.";

			while (rs.next()) {
				if (rs.getString("del_yn").equals("N")) {
					pwd = rs.getString("pwd");
				}
			}
			return pwd;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public String findId(String names, String emails) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql = "select id,del_yn from member where name=? and email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, names);
			pstmt.setString(2, emails);
			rs = pstmt.executeQuery();
			String id = "없는 아이디 입니다.";

			while (rs.next()) {
				if (rs.getString("del_yn").equals("N")) {
					id = rs.getString("id");
				}
			}
			return id;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int join(MemberDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConn();
			String sql1 = "select id,del_yn from member where id = ?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setString(1, dto.getId());
			rs = pstmt1.executeQuery();

			while (rs.next()) {
				if (rs.getString("del_yn").equals("N")) {
					return 0;
				}
			}
			pstmt1.close();

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
			pstmt.setString(9, dto.getAddr());
			pstmt.setInt(10, dto.getPoint());
			pstmt.setString(11, dto.getDel_yn());

			int n = pstmt.executeUpdate();
			return n;

		} catch (SQLException se) {
			se.getStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public boolean checkEmail(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;

		try {
			con = JDBCUtil.getConn();
			String sql = "select * from member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = true;
			}
			return check;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return false;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public ArrayList<MemberDto> selSearchList(int startRow, int endRow, String word, String type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "";
			if (word.equals("")) {
				sql = "select * from (select a.*,rownum rnum from(select * from member order by num desc)a) where rnum >= "
						+ startRow + " and rnum <= " + endRow;
			} else {
				sql = "select * from (select a.*,rownum rnum from(select * from member where " + type + " like '%"
						+ word + "%' order by num desc)a) where rnum >= " + startRow + " and rnum <= " + endRow;
			}

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setRating(rs.getInt("rating"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getString("birth"));
				dto.setPhone(rs.getString("phone"));
				dto.setGender(rs.getInt("gender"));
				dto.setAddr(rs.getString("addr"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setPoint(rs.getInt("point"));
				dto.setDel_yn(rs.getString("del_yn"));
				dto.setDel_date(rs.getDate("del_date"));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public int delMemNum(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "update member set del_yn = 'Y',del_date=sysdate where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public int updMemFromAdmin(MemberDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "update member set name=?,birth=?,email=?,gender=?,phone=?,addr=? where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());
			pstmt.setString(3, dto.getEmail());
			pstmt.setInt(4, dto.getGender());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getAddr());
			pstmt.setInt(7, dto.getNum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}

	public int selMemCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
			String sql = "select nvl(count(*),0) cnt from member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
