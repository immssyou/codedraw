import java.sql.*;

public class DBConn {

	// ������� ����
	// ���������� �ڷ��� ��������̸�; //�ڵ��ʱ�ȭ
	private static Connection conn;

	// �޼ҵ� ����
	// ���������� ��ȯ�ڷ��� �޼ҵ��̸�(�Ķ����){
	// ���� �ڵ�;
	// return ��ȯ��;
	// }
	public static Connection getConnection() throws ClassNotFoundException, //Connection ��ü�� ��ȯ
			SQLException {
		// Connection - �����ͺ��̽��� ���ᰴü�� �����ϴ� Ŭ����

		// ���� ��ü�� ��ȯ�ǵ��� ����
		// ->�̱��� ���
		if (conn == null) {

			// ����Ŭ ���ῡ �ʿ��� ���� ����
			// url, userid, pw
			conn = DriverManager.getConnection( // conn = ��ü ���� ���;
					"jdbc:mysql://localhost:3306/guestbook", "swgae", "12345");
		}
		return conn;
	}
	
	//�ܺ� �ڿ��� ����Ŀ��� �ݵ�� ���� ���� ������ �ʿ���.
	public static void close() throws SQLException {

		if (conn != null) {
			if (!conn.isClosed()) {
				conn.close();
			}
		}
		conn = null;

	}
}
