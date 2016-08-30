import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {
	private Connection conn;
	
	//데이터베이스 연결
	public void connect() 
				throws ClassNotFoundException, SQLException {
		 conn = DBConn.getConnection();
	}
	
	//데이터베이스 연결 종료
	public void close() throws SQLException {
		DBConn.close();
	}
	
	public int memberchk(MemberDTO dto) throws SQLException {
		int result = 0;
		String sql = String.format("SELECT COUNT(*) AS count 	FROM membersList WHERE id='%s'", dto.getId());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			result = rs.getInt("count");
		}
		return result;
	}
	
	public int add(MemberDTO dto) throws SQLException {
		int result;
		String sql = String.format("INSERT INTO memberslist (id, pswd, wdate, admin) VALUES ('%s', '%s', now(), 'N')", dto.getId(), dto.getPwd());
		Statement stmt = conn.createStatement();
		result = stmt.executeUpdate(sql);
		return result;
	}
}
