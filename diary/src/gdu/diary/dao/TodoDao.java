package gdu.diary.dao;

import java.sql.*;
import java.util.*;

import gdu.diary.vo.Todo;

public class TodoDao {
	
	public List<Todo> selectTodoListByDate(Connection conn, int memberNo, int targetYear, int targetMonth) throws SQLException {
		List<Todo> list = new ArrayList<Todo>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_LIST_BY_DATE);
			 stmt.setInt(1, memberNo);
			 stmt.setInt(2, targetYear);
			 stmt.setInt(3, targetMonth);
			 rs = stmt.executeQuery();
			 if(rs.next()) {
				 Todo todo = new Todo();
				 todo.setTodoNo(rs.getInt("todoNo"));
				 todo.setTodoDate(rs.getString("todoDate"));
				 todo.setTodoTitle(rs.getString("todoTitle"));
				 todo.setTodoFontColor(rs.getString("todoFontColor"));
				 list.add(todo);
			 }
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
	
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		int rowCnt = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.INSERT_TODO);
			stmt.setInt(1, todo.getMemberNo());
			stmt.setString(2, todo.getTodoDate());
			stmt.setString(3, todo.getTodoTitle());
			stmt.setString(4, todo.getTodoContent());
			stmt.setString(5, todo.getTodoFontColor());
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return rowCnt;
	}
	
	public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException { // 매개변수를 하나로 고정시킨다.
		int rowCnt = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return rowCnt;
	}
}
