package com.market.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConn() throws SQLException{
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con=DriverManager.getConnection(url,"scott","tiger");
			return con;
		}catch (ClassNotFoundException ce) {
			System.out.println(ce.getMessage());
			return null;
		}
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

