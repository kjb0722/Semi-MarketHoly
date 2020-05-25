package com.market.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.market.admin.dto.StatDayDto;
import com.market.admin.dto.StatGenderDto;
import com.market.admin.dto.StatMonDto;
import com.market.db.JDBCUtil;

public class StatDao {
	public static StatDao instance = new StatDao();

	public static StatDao getInstance() {
		return instance;
	}

	private StatDao() {
	}

	public StatMonDto selMonSell(Date startDate, Date endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
//			String sql = "SELECT Nvl(Sum(Decode(To_char(reg_date, 'mm'), '01', 1, \r\n" + 
//					"                                               0)), 0) \"1월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '02', 1, \r\n" + 
//					"                                               0)), 0) \"2월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '03', 1, \r\n" + 
//					"                                               0)), 0) \"3월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '04', 1, \r\n" + 
//					"                                               0)), 0) \"4월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '05', 1, \r\n" + 
//					"                                               0)), 0) \"5월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '06', 1, \r\n" + 
//					"                                               0)), 0) \"6월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '07', 1, \r\n" + 
//					"                                               0)), 0) \"7월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '08', 1, \r\n" + 
//					"                                               0)), 0) \"8월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '09', 1, \r\n" + 
//					"                                               0)), 0) \"9월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '10', 1, \r\n" + 
//					"                                               0)), 0) \"10월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '11', 1, \r\n" + 
//					"                                               0)), 0) \"11월\", \r\n" + 
//					"       Nvl(Sum(Decode(To_char(reg_date, 'mm'), '12', 1, \r\n" + 
//					"                                               0)), 0) \"12월\" \r\n" + 
//					"FROM   (SELECT * \r\n" + 
//					"        FROM   orders \r\n" + 
//					"        WHERE  status = 5 \r\n" + 
//					"               AND reg_date >= ? \r\n" + 
//					"               AND reg_date <= ?) ";
			String sql = "SELECT * \r\n" + 
					"FROM   ( \r\n" + 
					"              SELECT Decode (To_char(reg_date, 'MM'), '01', '1월', '02', '2월', '03', '3월', '04', '4월', '05', '5월', '06', '6월', '07', '7월', '08', '8월', '09', '9월', '10', '10월', '11', '11월', '12', '12월') AS mon , \r\n" + 
					"                     reg_date \r\n" + 
					"              FROM   orders \r\n" + 
					"              WHERE  status = 5 \r\n" + 
					"              AND    reg_date >= ? \r\n" + 
					"              AND    reg_date <= ?) PIVOT( count(reg_date) FOR mon IN('1월'  AS \"1월\", \r\n" + 
					"                                                                                 '2월'  AS \"2월\", \r\n" + 
					"                                                                                 '3월'  AS \"3월\", \r\n" + 
					"                                                                                 '4월'  AS \"4월\", \r\n" + 
					"                                                                                 '5월'  AS \"5월\", \r\n" + 
					"                                                                                 '6월'  AS \"6월\", \r\n" + 
					"                                                                                 '7월'  AS \"7월\", \r\n" + 
					"                                                                                 '8월'  AS \"8월\", \r\n" + 
					"                                                                                 '9월'  AS \"9월\", \r\n" + 
					"                                                                                 '10월' AS \"10월\", \r\n" + 
					"                                                                                 '11월' AS \"11월\", \r\n" + 
					"                                                                                 '12월' AS \"12월\") )";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, startDate);
			pstmt.setDate(2, endDate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int jan = rs.getInt("1월");
				int feb = rs.getInt("2월");
				int mar = rs.getInt("3월");
				int apr = rs.getInt("4월");
				int may = rs.getInt("5월");
				int jun = rs.getInt("6월");
				int jul = rs.getInt("7월");
				int aug = rs.getInt("8월");
				int sep = rs.getInt("9월");
				int oct = rs.getInt("10월");
				int nov = rs.getInt("11월");
				int dec = rs.getInt("12월");
				return new StatMonDto(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public StatMonDto selMonAmount(Date startDate, Date endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
//			String sql = "SELECT Sum(Decode(To_char(reg_date, 'mm'), '01', price * 0.0001, \r\n" + 
//					"                                           0)) \"1월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '02', price * 0.0001, \r\n" + 
//					"                                           0)) \"2월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '03', price * 0.0001, \r\n" + 
//					"                                           0)) \"3월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '04', price * 0.0001, \r\n" + 
//					"                                           0)) \"4월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '05', price * 0.0001, \r\n" + 
//					"                                           0)) \"5월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '06', price * 0.0001, \r\n" + 
//					"                                           0)) \"6월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '07', price * 0.0001, \r\n" + 
//					"                                           0)) \"7월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '08', price * 0.0001, \r\n" + 
//					"                                           0)) \"8월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '09', price * 0.0001, \r\n" + 
//					"                                           0)) \"9월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '10', price * 0.0001, \r\n" + 
//					"                                           0)) \"10월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '11', price * 0.0001, \r\n" + 
//					"                                           0)) \"11월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '12', price * 0.0001, \r\n" + 
//					"                                           0)) \"12월\" \r\n" + 
//					" FROM   (SELECT * \r\n" + 
//					"        FROM   orders \r\n" + 
//					"        WHERE  status = 5 and reg_date >= ? and reg_date <= ?) \r\n";
			String sql = "SELECT * \r\n" + 
					"FROM   ( \r\n" + 
					"              SELECT Decode (To_char(reg_date, 'MM'), '01', '1월', '02', '2월', '03', '3월', '04', '4월', '05', '5월', '06', '6월', '07', '7월', '08', '8월', '09', '9월', '10', '10월', '11', '11월', '12', '12월') AS mon , \r\n" + 
					"                     price \r\n" + 
					"              FROM   orders \r\n" + 
					"              WHERE  status = 5 \r\n" + 
					"              AND    reg_date >= ? \r\n" + 
					"              AND    reg_date <= ?) PIVOT( sum(price*0.0001) FOR mon IN ('1월'  AS \"1월\", \r\n" + 
					"                                                                                    '2월'  AS \"2월\", \r\n" + 
					"                                                                                    '3월'  AS \"3월\", \r\n" + 
					"                                                                                    '4월'  AS \"4월\", \r\n" + 
					"                                                                                    '5월'  AS \"5월\", \r\n" + 
					"                                                                                    '6월'  AS \"6월\", \r\n" + 
					"                                                                                    '7월'  AS \"7월\", \r\n" + 
					"                                                                                    '8월'  AS \"8월\", \r\n" + 
					"                                                                                    '9월'  AS \"9월\", \r\n" + 
					"                                                                                    '10월' AS \"10월\", \r\n" + 
					"                                                                                    '11월' AS \"11월\", \r\n" + 
					"                                                                                    '12월' AS \"12월\") )";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, startDate);
			pstmt.setDate(2, endDate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int jan = rs.getInt("1월");
				int feb = rs.getInt("2월");
				int mar = rs.getInt("3월");
				int apr = rs.getInt("4월");
				int may = rs.getInt("5월");
				int jun = rs.getInt("6월");
				int jul = rs.getInt("7월");
				int aug = rs.getInt("8월");
				int sep = rs.getInt("9월");
				int oct = rs.getInt("10월");
				int nov = rs.getInt("11월");
				int dec = rs.getInt("12월");
				return new StatMonDto(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public StatDayDto selDaySell(Date startDate, Date endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
//			String sql = "SELECT Sum(Decode(To_char(reg_date, 'dd'), '01', 1, \r\n" + 
//					"                                           0)) \"1일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '02', 1, \r\n" + 
//					"                                           0)) \"2일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '03', 1, \r\n" + 
//					"                                           0)) \"3일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '04', 1, \r\n" + 
//					"                                           0)) \"4일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '05', 1, \r\n" + 
//					"                                           0)) \"5일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '06', 1, \r\n" + 
//					"                                           0)) \"6일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '07', 1, \r\n" + 
//					"                                           0)) \"7일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '08', 1, \r\n" + 
//					"                                           0)) \"8일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '09', 1, \r\n" + 
//					"                                           0)) \"9일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '10', 1, \r\n" + 
//					"                                           0)) \"10일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '11', 1, \r\n" + 
//					"                                           0)) \"11일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '12', 1, \r\n" + 
//					"                                           0)) \"12일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '13', 1, \r\n" + 
//					"                                           0)) \"13일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '14', 1, \r\n" + 
//					"                                           0)) \"14일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '15', 1, \r\n" + 
//					"                                           0)) \"15일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '16', 1, \r\n" + 
//					"                                           0)) \"16일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '17', 1, \r\n" + 
//					"                                           0)) \"17일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '18', 1, \r\n" + 
//					"                                           0)) \"18일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '19', 1, \r\n" + 
//					"                                           0)) \"19일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '20', 1, \r\n" + 
//					"                                           0)) \"20일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '21', 1, \r\n" + 
//					"                                           0)) \"21일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '22', 1, \r\n" + 
//					"                                           0)) \"22일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '23', 1, \r\n" + 
//					"                                           0)) \"23일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '24', 1, \r\n" + 
//					"                                           0)) \"24일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '25', 1, \r\n" + 
//					"                                           0)) \"25일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '26', 1, \r\n" + 
//					"                                           0)) \"26일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '27', 1, \r\n" + 
//					"                                           0)) \"27일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '28', 1, \r\n" + 
//					"                                           0)) \"28일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '29', 1, \r\n" + 
//					"                                           0)) \"29일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '30', 1, \r\n" + 
//					"                                           0)) \"30일\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'dd'), '31', 1, \r\n" + 
//					"                                           0)) \"31일\" \r\n" + 
//					"FROM   (SELECT * \r\n" + 
//					"        FROM   orders \r\n" + 
//					"        WHERE  status = 5 \r\n" + 
//					"               AND reg_date >= ? \r\n" + 
//					"               AND reg_date <= ?) ";
			String sql = "SELECT * \r\n" + 
					"FROM   ( \r\n" + 
					"              SELECT Decode (To_char(reg_date, 'dd'), '01', '1일', '02', '2일', '03', '3일', '04', '4일', '05', '5일', '06', '6일', '07', '7일', '08', '8일', '09', '9일', '10', '10일', '11', '11일', '12', '12일', '13', '13일', '14', '14일', '15', '15일', '16', '16일', '17', '17일', '18', '18일', '19', '19일', '20', '20일', '21', '22일', '23', '24일', '25', '25일', '26', '26일', '27', '27일', '28', '29일', '30', '30일', '31', '31일') AS day , \r\n" + 
					"                     reg_date \r\n" + 
					"              FROM   orders \r\n" + 
					"              WHERE  status = 5 \r\n" + 
					"              AND    reg_date >= ? \r\n" + 
					"              AND    reg_date <= ?) PIVOT( count(reg_date) FOR day IN ('1일'  AS \"1일\", \r\n" + 
					"                                                                                  '2일'  AS \"2일\", \r\n" + 
					"                                                                                  '3일'  AS \"3일\", \r\n" + 
					"                                                                                  '4일'  AS \"4일\", \r\n" + 
					"                                                                                  '5일'  AS \"5일\", \r\n" + 
					"                                                                                  '6일'  AS \"6일\", \r\n" + 
					"                                                                                  '7일'  AS \"7일\", \r\n" + 
					"                                                                                  '8일'  AS \"8일\", \r\n" + 
					"                                                                                  '9일'  AS \"9일\", \r\n" + 
					"                                                                                  '10일' AS \"10일\", \r\n" + 
					"                                                                                  '11일' AS \"11일\", \r\n" + 
					"                                                                                  '12일' AS \"12일\", \r\n" + 
					"                                                                                  '13일' AS \"13일\", \r\n" + 
					"                                                                                  '14일' AS \"14일\", \r\n" + 
					"                                                                                  '15일' AS \"15일\", \r\n" + 
					"                                                                                  '16일' AS \"16일\", \r\n" + 
					"                                                                                  '17일' AS \"17일\", \r\n" + 
					"                                                                                  '18일' AS \"18일\", \r\n" + 
					"                                                                                  '19일' AS \"19일\", \r\n" + 
					"                                                                                  '20일' AS \"20일\", \r\n" + 
					"                                                                                  '21일' AS \"21일\", \r\n" + 
					"                                                                                  '22일' AS \"22일\", \r\n" + 
					"                                                                                  '23일' AS \"23일\", \r\n" + 
					"                                                                                  '24일' AS \"24일\", \r\n" + 
					"                                                                                  '25일' AS \"25일\", \r\n" + 
					"                                                                                  '26일' AS \"26일\", \r\n" + 
					"                                                                                  '27일' AS \"27일\", \r\n" + 
					"                                                                                  '28일' AS \"28일\", \r\n" + 
					"                                                                                  '29일' AS \"29일\", \r\n" + 
					"                                                                                  '30일' AS \"30일\", \r\n" + 
					"                                                                                  '31일' AS \"31일\") )";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, startDate);
			pstmt.setDate(2, endDate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int day1 = rs.getInt("1일");
				int day2 = rs.getInt("2일");
				int day3 = rs.getInt("3일");
				int day4 = rs.getInt("4일");
				int day5 = rs.getInt("5일");
				int day6 = rs.getInt("6일");
				int day7 = rs.getInt("7일");
				int day8 = rs.getInt("8일");
				int day9 = rs.getInt("9일");
				int day10 = rs.getInt("10일");
				int day11 = rs.getInt("11일");
				int day12 = rs.getInt("12일");
				int day13 = rs.getInt("13일");
				int day14 = rs.getInt("14일");
				int day15 = rs.getInt("15일");
				int day16 = rs.getInt("16일");
				int day17 = rs.getInt("17일");
				int day18 = rs.getInt("18일");
				int day19 = rs.getInt("19일");
				int day20 = rs.getInt("20일");
				int day21 = rs.getInt("21일");
				int day22 = rs.getInt("22일");
				int day23 = rs.getInt("23일");
				int day24 = rs.getInt("24일");
				int day25 = rs.getInt("25일");
				int day26 = rs.getInt("26일");
				int day27 = rs.getInt("27일");
				int day28 = rs.getInt("28일");
				int day29 = rs.getInt("29일");
				int day30 = rs.getInt("30일");
				int day31 = rs.getInt("31일");
				return new StatDayDto(day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17,
						day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31);
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public StatDayDto selDayAmount(Date startDate, Date endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConn();
//			String sql = "SELECT Sum(Decode(To_char(reg_date, 'mm'), '01', price * 0.0001, \r\n" + 
//					"                                           0)) \"1월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '02', price * 0.0001, \r\n" + 
//					"                                           0)) \"2월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '03', price * 0.0001, \r\n" + 
//					"                                           0)) \"3월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '04', price * 0.0001, \r\n" + 
//					"                                           0)) \"4월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '05', price * 0.0001, \r\n" + 
//					"                                           0)) \"5월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '06', price * 0.0001, \r\n" + 
//					"                                           0)) \"6월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '07', price * 0.0001, \r\n" + 
//					"                                           0)) \"7월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '08', price * 0.0001, \r\n" + 
//					"                                           0)) \"8월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '09', price * 0.0001, \r\n" + 
//					"                                           0)) \"9월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '10', price * 0.0001, \r\n" + 
//					"                                           0)) \"10월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '11', price * 0.0001, \r\n" + 
//					"                                           0)) \"11월\", \r\n" + 
//					"       Sum(Decode(To_char(reg_date, 'mm'), '12', price * 0.0001, \r\n" + 
//					"                                           0)) \"12월\" \r\n" + 
//					" FROM   (SELECT * \r\n" + 
//					"        FROM   orders \r\n" + 
//					"        WHERE  status = 5 and reg_date >= ? and reg_date <= ?) \r\n";
			String sql = "SELECT * \r\n" + 
					"FROM   ( \r\n" + 
					"              SELECT Decode (To_char(reg_date, 'dd'), '01', '1일', '02', '2일', '03', '3일', '04', '4일', '05', '5일', '06', '6일', '07', '7일', '08', '8일', '09', '9일', '10', '10일', '11', '11일', '12', '12일', '13', '13일', '14', '14일', '15', '15일', '16', '16일', '17', '17일', '18', '18일', '19', '19일', '20', '20일', '21', '22일', '23', '24일', '25', '25일', '26', '26일', '27', '27일', '28', '29일', '30', '30일', '31', '31일') AS day , \r\n" + 
					"                     price \r\n" + 
					"              FROM   orders \r\n" + 
					"              WHERE  status = 5 \r\n" + 
					"              AND    reg_date >= ? \r\n" + 
					"              AND    reg_date <= ?) PIVOT( sum(price*0.0001) FOR day IN ('1일'  AS \"1일\", \r\n" + 
					"                                                                             '2일'  AS \"2일\", \r\n" + 
					"                                                                             '3일'  AS \"3일\", \r\n" + 
					"                                                                             '4일'  AS \"4일\", \r\n" + 
					"                                                                             '5일'  AS \"5일\", \r\n" + 
					"                                                                             '6일'  AS \"6일\", \r\n" + 
					"                                                                             '7일'  AS \"7일\", \r\n" + 
					"                                                                             '8일'  AS \"8일\", \r\n" + 
					"                                                                             '9일'  AS \"9일\", \r\n" + 
					"                                                                             '10일' AS \"10일\", \r\n" + 
					"                                                                             '11일' AS \"11일\", \r\n" + 
					"                                                                             '12일' AS \"12일\", \r\n" + 
					"                                                                             '13일' AS \"13일\", \r\n" + 
					"                                                                             '14일' AS \"14일\", \r\n" + 
					"                                                                             '15일' AS \"15일\", \r\n" + 
					"                                                                             '16일' AS \"16일\", \r\n" + 
					"                                                                             '17일' AS \"17일\", \r\n" + 
					"                                                                             '18일' AS \"18일\", \r\n" + 
					"                                                                             '19일' AS \"19일\", \r\n" + 
					"                                                                             '20일' AS \"20일\", \r\n" + 
					"                                                                             '21일' AS \"21일\", \r\n" + 
					"                                                                             '22일' AS \"22일\", \r\n" + 
					"                                                                             '23일' AS \"23일\", \r\n" + 
					"                                                                             '24일' AS \"24일\", \r\n" + 
					"                                                                             '25일' AS \"25일\", \r\n" + 
					"                                                                             '26일' AS \"26일\", \r\n" + 
					"                                                                             '27일' AS \"27일\", \r\n" + 
					"                                                                             '28일' AS \"28일\", \r\n" + 
					"                                                                             '29일' AS \"29일\", \r\n" + 
					"                                                                             '30일' AS \"30일\", \r\n" + 
					"                                                                             '31일' AS \"31일\") )";
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, startDate);
			pstmt.setDate(2, endDate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int day1 = rs.getInt("1일");
				int day2 = rs.getInt("2일");
				int day3 = rs.getInt("3일");
				int day4 = rs.getInt("4일");
				int day5 = rs.getInt("5일");
				int day6 = rs.getInt("6일");
				int day7 = rs.getInt("7일");
				int day8 = rs.getInt("8일");
				int day9 = rs.getInt("9일");
				int day10 = rs.getInt("10일");
				int day11 = rs.getInt("11일");
				int day12 = rs.getInt("12일");
				int day13 = rs.getInt("13일");
				int day14 = rs.getInt("14일");
				int day15 = rs.getInt("15일");
				int day16 = rs.getInt("16일");
				int day17 = rs.getInt("17일");
				int day18 = rs.getInt("18일");
				int day19 = rs.getInt("19일");
				int day20 = rs.getInt("20일");
				int day21 = rs.getInt("21일");
				int day22 = rs.getInt("22일");
				int day23 = rs.getInt("23일");
				int day24 = rs.getInt("24일");
				int day25 = rs.getInt("25일");
				int day26 = rs.getInt("26일");
				int day27 = rs.getInt("27일");
				int day28 = rs.getInt("28일");
				int day29 = rs.getInt("29일");
				int day30 = rs.getInt("30일");
				int day31 = rs.getInt("31일");
				return new StatDayDto(day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17,
						day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31);
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}

	public ArrayList<StatGenderDto> SelGender() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StatGenderDto> list = new ArrayList<StatGenderDto>();
		try {
			con = JDBCUtil.getConn();
			String sql = "SELECT Decode(gender, 1, '남자', \r\n" + 
					"                      '여자') gender, \r\n" + 
					"       Count(*)                 cnt \r\n" + 
					"FROM   member \r\n" + 
					"WHERE  num IN(SELECT num \r\n" + 
					"              FROM   orders \r\n" + 
					"              WHERE  status = 5) \r\n" + 
					"GROUP  BY gender ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String gender = rs.getString("gender");
				int cnt = rs.getInt("cnt");
				list.add(new StatGenderDto(gender, cnt));
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
