package kr.co.codedraw.mysql.dao;

import java.sql.*;

import kr.co.codedraw.mysql.dbcon.DBConn;
import kr.co.codedraw.mysql.dto.BoardDTO;

public class BoardDao {
	private Connection conn;
	
	//�����ͺ��̽� ����
	public void connect() 
				throws ClassNotFoundException, SQLException {
		 conn = DBConn.getConnection();
	}
	
	//�����ͺ��̽� ���� ����
	public void close() throws SQLException {
		DBConn.close();
	}
	
	//���� �۾��� ����
	public int	add(BoardDTO dto) throws SQLException {
		int result = 0;
		String sql = String
				.format("INSERT INTO board (title, content, registDate, url) VALUES ( '%s', '%s', '%s')",
						dto.getTitle(), dto.getContent(), dto.getRegistDate(), dto.getUrl());
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		result = stmt.executeUpdate(sql);
		return result;
	}
	
}
