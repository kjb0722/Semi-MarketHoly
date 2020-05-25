package com.market.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCUtil {
//	public static Connection getConn() throws SQLException{
//		Connection con = null;
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			//String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String url = "jdbc:oracle:thin:@192.168.0.2:1521:xe";
//			con=DriverManager.getConnection(url,"scott","tiger");
//			return con;
//		}catch (ClassNotFoundException ce) {
//			System.out.println(ce.getMessage());
//			return null;
//		}
//	}
	static DataSource ds = null;

	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Connection getConn() throws SQLException {
		Connection con = ds.getConnection();
		return con;
	}
	 
	public static void close(ResultSet rs, Statement stmt, Connection con) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) rs.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	public static void close(Statement stmt) {
		try {	
			if(stmt!=null) stmt.close();	
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	public static void close(Connection con) {
		try {	
			if(con!=null) con.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}

