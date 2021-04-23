package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
	private TodoDao todoDao;
	// select -> get
	// insert -> add
	// update -> modify
	// delete -> remove
	
	// 삭제 성공: true, 삭제 실패(rollback): false 
	public boolean removeMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao();
		this.todoDao = new TodoDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		int todoRowCnt = 0;
		int memberRowCnt = 0;
		try {
			conn = dbUtil.getConnection();
			todoRowCnt = this.todoDao.deleteTodoByMember(conn, member.getMemberNo());
			System.out.println(todoRowCnt+"<-- MemberService.removeMemberByKey의 todoRowCnt");
			memberRowCnt = this.memberDao.deleteMemberByKey(conn, member);
			System.out.println(memberRowCnt+"<-- MemberService.removeMemberByKey의 memberRowCnt");
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback(); // 예외 발생했을 때 select 빼고 다 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
			// 캐치절에서 끝나면 무조건 false가 리턴
		}
		return (todoRowCnt+memberRowCnt) > 0;
	}
	
	public Member getMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		try {
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.selectMemberByKey(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, null, null);
		}
		
		return returnMember;
	}
	
	public Member addMember(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		try {
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.insertMember(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, null, null);
		}
		
		return returnMember;
	}
}
