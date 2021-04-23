package gdu.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil { 
	// 1. DB 연결
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/diary","root","java1004");
			conn.setAutoCommit(false); 
			// setAutoCommit(false): 여러 개의 쿼리 문장이 하나의 작업으로 수행되어야 할 경우 각각의 문장이 작동되지 못하게 한다.
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// 1) finally 절을 구현하여 conn.close()를 하면 안되는 이유?
		// conn을 리턴해야돼서 close하면 안되기 때문에 finally를 사용하지 않는다 
		return conn;
	}
	
	// 2) 1)의 이유로 메서드 호출 쪽에서 자원을 해제(close)해야하는데 편하게 해제가능하도록 메서드 제공
	// 2. DB 자원(conn, stmt, rs) 해제
	public void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		// 생성된 역순으로 해제
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
