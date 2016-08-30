import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GuestBookDAO {
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
	
	//방명록 글쓰기 쿼리
	public int	add(GuestBookDTO dto) throws SQLException {
		int result = 0;
		String sql = String
				.format("INSERT INTO guestBook ( id, content, wdate) VALUES ( '%s', '%s', now())",
						dto.getId(), dto.getContent());
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		result = stmt.executeUpdate(sql);
		return result;
	}
	
	//글 건수 출력 쿼리
	public int count() {
		int result = 0;
		
		return result;
	}
	
	
	//방명록 글보기 쿼리
		public ArrayList<GuestBookDTO> view() throws SQLException{
			ArrayList<GuestBookDTO> result = new ArrayList<GuestBookDTO>();
			
			String sql = String.format("SELECT gid, id, content, wdate FROM guestBook ORDER BY gid");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				GuestBookDTO dto = new GuestBookDTO();
				dto.setGid(rs.getInt("gid"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));			
				dto.setWdate(rs.getString("wdate"));
				
				result.add(dto);			
			}
			rs.close();
			
			return result;
		}
		
		//방명록 글보기 쿼리
		public ArrayList<GuestBookDTO> viewMyContents(MemberDTO dto) throws SQLException{
			ArrayList<GuestBookDTO> result = new ArrayList<GuestBookDTO>();
			
			String sql = String.format("SELECT gid, id, content, wdate FROM guestBook WHERE id = '%s' ORDER BY gid",dto.getId());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				GuestBookDTO gDto = new GuestBookDTO();
				gDto.setGid(rs.getInt("gid"));
				gDto.setId(rs.getString("id"));
				gDto.setContent(rs.getString("content"));			
				gDto.setWdate(rs.getString("wdate"));
				 
				result.add(gDto);			
			}
			rs.close();
			
			return result;
		}
		
		public void delete(int gid) throws SQLException{
			String sql = String
					.format("DELETE FROM GUESTBOOK WHERE gid = %d",gid);
			System.out.println(sql);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		
		public void modify(int gid, String content) throws SQLException {
			String sql = String
					.format("UPDATE  GUESTBOOK Set content = '%s'  WHERE gid = %d",content, gid);
			System.out.println(sql);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		
}
