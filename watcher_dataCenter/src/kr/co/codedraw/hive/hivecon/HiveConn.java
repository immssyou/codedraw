package kr.co.codedraw.hive.hivecon;

import java.sql.*;

// JDBC 드라이버를 이용한 하이브 연동하기 위한 객체 정의.
public class HiveConn {
	
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private static Connection conn;
	
	// 하이브 연동 관련 클래스 동적 로딩.
	public static void loadingDriver() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	// 하이브 연결.
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		conn = DriverManager.getConnection("jdbc:hive://172.16.12.135:10000/default", "", "");
		return conn;
	}
	
	// 하이브 연결 종료.
	public static void close() throws SQLException {
		if(null != conn) {
			if(!conn.isClosed()) {
				conn.close();
			}
		}
		conn = null;
	}
}
