import java.sql.*;

public class DBConn {

	// 멤버변수 형식
	// 접근지정자 자료형 멤버변수이름; //자동초기화
	private static Connection conn;

	// 메소드 형식
	// 접근지정자 반환자료형 메소드이름(파라미터){
	// 실행 코드;
	// return 반환값;
	// }
	public static Connection getConnection() throws ClassNotFoundException, //Connection 객체를 반환
			SQLException {
		// Connection - 데이터베이스를 연결객체를 저장하는 클래스

		// 같은 객체가 반환되도록 지정
		// ->싱글콘 기법
		if (conn == null) {

			// 오라클 연결에 필요한 정보 제공
			// url, userid, pw
			conn = DriverManager.getConnection( // conn = 객체 생성 결과;
					"jdbc:mysql://localhost:3306/guestbook", "swgae", "12345");
		}
		return conn;
	}
	
	//외부 자원은 사용후에는 반드시 연결 해제 과정이 필요함.
	public static void close() throws SQLException {

		if (conn != null) {
			if (!conn.isClosed()) {
				conn.close();
			}
		}
		conn = null;

	}
}
