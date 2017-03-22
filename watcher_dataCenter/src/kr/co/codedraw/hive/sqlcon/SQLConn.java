package kr.co.codedraw.hive.sqlcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.codedraw.hive.dto.WordDto;

//JDBC 드라이버를 이용한 MySQL 연동하기 위한 객체 정의.
public class SQLConn {
	private static Connection con = null;
	String [] fillter_ko = {};
	String [] fillter_en = {"RT", "the", "to", "a", "of", "is", "and", "in", "", "for", "I", "you", "on", "that", "are", "&", "with", "not", "be", "by", "it", "was", "murder", "have", "this", "rape", "about", "crime", "as", "but", "they", "terrorist", "who", "my", "The", "all", "violence", "criminal", "at", "like", "from", "will", "we", "has", "people", "or", "so", "he", "just", "if", "your", "get", "terror", "me", "an", "terrorists", "don't", "no", "can", "when", "do", "against", "his", "their", "how", "-", "up", "terrorism", "murdered", "more", "our", "should", "out", "what", "her", "being", "I'm", "one", "it's", "black", "If"
											, "raped", "We", "been", "would", "only", "crimes", "than", "them", "she", "u", "were", "know", "i", "because", "police", "You", "A"};
	String [] fillter_ru = {};
	String [] fillter_ja = {};
	String [] fillter_fr = {};
	String [] fillter_de = {"kriminalität", "verbrecher", "terror", "vergewaltigen","mord", "gewalt", "diebstahl", "Kriminalität","Verbrecher", "Terror", "Vergewaltigen", "Mord", "Gewalt", "Diebstahl"
											,"RT","die","und", "in", "der", "ist", "", "von", "mit","das", "zu", "den","eine", "als", "auch", "Die", "gegen", "im", "hat", "dass", "so", "wird", "man", "an", "Terror", "wie", "-", "dem", "aber", "sie", "vor", "nur", "bei", "wenn", "Ich" 
											,"Ich", "Terroristen", "noch", "Das", "nach", "&", "oder", "werden", "dann", "wir", "ja", "über", "du", "um", "Mord", "schon", "mal", "aus", "zum", "doch", "Sie", "des", "Und", "was", "einen", "einem", "mir", "mehr", "einer", "haben", "Der", "durch", "da", "immer", "kann", "jetzt", "heute", "Es", "muss", "Wir", "er", "am", "Deutschland", "Wenn"
											,"nichts", "wegen", "In", "keine", "u", "wieder", "war", "mich", "wurde", "kein", "Frau", "diese"};
	// MySQL 서버 연결.
	public boolean startConnect(String url, String userID, String userPW) {
		System.out.println("Running : MySQL 서버 접속을 시도합니다.\n");
		try {
			con = DriverManager.getConnection(url, userID, userPW);
			System.out.println("Running : MySQL 서버 접속에 성공하였습니다.\n");
			return true;
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return false;
		}
	}
	
	// MySQL 데이터 삽입.
	public boolean insertDB(String countryCodeNm, ArrayList<WordDto> resWordCntList, String yesterday) {
		System.out.println("Running : MySQL에 데이터 전송을 시작합니다.\n");
		try {
			for(int i = 0; i < 300; i++) {
				String sql = String.format("INSERT INTO word_table ( countryCode, word, frequency, date ) VALUES ( '%s', '%s', '%s', '%s' )",
						countryCodeNm, resWordCntList.get(i).getWord(), resWordCntList.get(i).getCount(), yesterday);
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
			}
			System.out.println("Running : MySQL에 데이터 전송을 완료하였습니다.\n");
			return true;
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return false;
		}
	}
	public void close() throws SQLException {
		if(null != con) {
			if(!con.isClosed()) {
				con.close();
			}
		}
		con = null;
	}
}