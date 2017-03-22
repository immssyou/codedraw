package kr.co.codedraw.hive.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

import kr.co.codedraw.hive.dto.HiveDto;
import kr.co.codedraw.hive.dto.WordDto;
import kr.co.codedraw.hive.hivecon.HiveConn;

public class HiveDao {
	private Connection conn;
	private Statement stmt;
	private ResultSet res;
	
	// 하이브 연동 클래스 동적 로딩 및 하이브 연결.
	public void connect() throws ClassNotFoundException, SQLException {
		System.out.println("Running : 하이브 클래스를 동적 로딩 중입니다.\n");
		HiveConn.loadingDriver();
		
		System.out.println("Running : 하이브 연결을 시도 중입니다.\n");
		conn = HiveConn.getConnection();
		
		if(null != conn) {
			System.out.println("Running : 하이브 연결에 성공하였습니다.\n");
			stmt = conn.createStatement();
		} else {
			System.out.println("Running : 하이브 연결에 실패하였습니다.\n");
		
		}
	}
	
	// 하이브 연결 종료.
	public void close() throws SQLException {
		System.out.println("Running : 하이브 연결을 종료하였습니다.");
		HiveConn.close();
	}
	
	// 테이블 확인.
	public boolean confirmTable(String tableName) throws SQLException {
		boolean result = false;
		String sql = "show tables '" + tableName + "'";
		res = stmt.executeQuery(sql);
		if(res.next()) {
			if(res.getString(1).equals(tableName))
				result = true;
		}
		return result;
	}
	
	// 테이블 생성.
	public void createTable(String tableName) throws SQLException {
		//dropTable(tableName);
		
		// 테이블이 존재하지 않을 경우에만 테이블 생성.
		if(!confirmTable(tableName)) {
			System.out.println("Running : 새로운 테이블을 생성 중입니다.");
			stmt.executeQuery("create table " + tableName + " (title string, content string, registDate string, url string) Partitioned by (days String)"
			+ "ROW FORMAT DELIMITED FIELDS TERMINATED BY '$' LINES TERMINATED BY '\n' STORED AS TEXTFILE");
		}
	}
	
	// 테이블 삭제.
	public void dropTable(String tableName) throws SQLException {
		System.out.println("Running : 기존 테이블을 삭제합니다.");
		stmt.executeQuery("drop table " + tableName);
	}
	
	// 데이터 삽입.
	public void insertData(String localFilePath, String tableName, String partitionDays) throws SQLException {
		System.out.println("Running : LOCAL 데이터를 HDFS에 삽입하고 있습니다 - " + tableName + " / " + partitionDays + ".\n");
		String sql = "LOAD DATA LOCAL INPATH " + localFilePath + " OVERWRITE INTO TABLE " + tableName + " PARTITION (days='" + partitionDays + "')";
		stmt.executeQuery(sql);
		System.out.println("Running : 데이터 삽입이 완료되었습니다.\n");
	}
	
	// 데이터 가져오기 및 아카이브.
	// 사용법 : ArrayList<HiveDto> resList = hDao.getData(tableName, yesterday);
	public ArrayList<HiveDto> getData(String tableName, String yesterday) throws SQLException {
		System.out.println("Running : HDFS에서 데이터를 가져오고 있습니다.");
		ArrayList<HiveDto> resList = new ArrayList<HiveDto>();
		
		String sql = "select * from " + tableName + " where days='" + yesterday + "'";
		res = stmt.executeQuery(sql);
		
		System.out.println("Running : 데이터 출력 및 아카이브를 시작합니다.");
		while(res.next()) {
			HiveDto temp = new HiveDto();
			temp.setTitle(String.valueOf(res.getString(1)));
			temp.setContent(String.valueOf(res.getString(2)));
			temp.setRegistDate(String.valueOf(res.getString(3)));
			temp.setUrl(String.valueOf(res.getString(4)));
			resList.add(temp);
			System.out.println("제목 : " + temp.getTitle() + "\n" + "컨텐츠 : " + temp.getContent() + "\n" + "등록일 : " + temp.getRegistDate() + "\n" + "주소 : " + temp.getUrl() + "\n");
		}
		System.out.println("Running : 데이터 아카이브가 완료되었습니다.\n");
		return resList;
	}
	
	// 범죄 관련 키워드가 포함되어 있는 게시글 추출.
	public ArrayList<HiveDto> getCriminalKeyword(String tableName, String yesterday, String [] keyword) throws SQLException {
		System.out.println("Running : 범죄 관련 키워드가 포함되어 있는 게시글 추출을 시작합니다.");
		ArrayList<HiveDto> resList = new ArrayList<HiveDto>();
		HashSet<String> resSet = new HashSet<String>();
		for(int index = 0; index < keyword.length; index++) {
			System.out.println("\nRunning : 키워드 - " + keyword[index]);
			String sql = "SELECT content FROM " + tableName + " WHERE days = '" + yesterday + "' and content like '%" + keyword[index] + "%'";
			res = stmt.executeQuery(sql);
			while(res.next()) {
				resSet.add(String.valueOf(res.getString(1)));
			}
		}
		
		for(String tempContent : resSet) {
			HiveDto temp = new HiveDto();
			temp.setContent(tempContent);
			resList.add(temp);
		}
		System.out.println("\nRunning : 범죄 관련 키워드가 포함되어 있는 게시글 추출이 완료되었습니다.\n");
		return resList;
	}
	
	// 범죄 관련 키워드 워드 카운팅 및 정렬.
	public ArrayList<WordDto> getWordCountData(String tableName) throws SQLException {
		ArrayList<WordDto> resList = new ArrayList<WordDto>();
		dropTable("wordcnt_" + tableName);
		String sql = "CREATE TABLE " + "wordcnt_" + tableName + " AS SELECT word,count(1) AS cnt FROM (SELECT explode(split(content,' ')) AS word FROM " + tableName + ") W GROUP BY word ORDER BY cnt desc";
		stmt.executeQuery(sql);
		sql = "select * from wordcnt_" + tableName;
		res = stmt.executeQuery(sql);
		while(res.next()) {
			WordDto temp = new WordDto();
			temp.setWord(String.valueOf(res.getString(1)));
			temp.setCount(String.valueOf(res.getString(2)));
			resList.add(temp);
		}
		return resList;
	}
}
