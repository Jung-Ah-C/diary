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
}
