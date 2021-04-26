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
	
	// 회원가입 아이디 중복 검사
	// 아이디 중복 = true, 아이디 중복 아님 = false
	public boolean checkMemberIdByKey(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Connection conn = null;
		boolean checkId = false;
		try {
			conn = dbUtil.getConnection();
			if(this.memberDao.checkMemberIdByKey(conn, member)!=null) { // 아이디가 중복이라면
				checkId = true;
			} else {
				checkId = false;
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback(); // 예외 발생했을 때 select 빼고 다 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
			// catch절에서 끝나면 무조건 false가 리턴
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return checkId;
	}
	
	// 회원가입
	public Member addMember(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		try {
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.insertMember(conn, member);
			System.out.println(conn+"<-- MemberService.addMember의 conn");
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returnMember;
	}
	
	// 회원탈퇴
	// 삭제 성공: true, 삭제 실패(rollback): false 
	public boolean removeMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		this.todoDao = new TodoDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		int todoRowCnt = 0;
		int memberRowCnt = 0;
		try {
			conn = dbUtil.getConnection();
			todoRowCnt = this.todoDao.deleteTodoByMember(conn, member.getMemberNo());
			memberRowCnt = this.memberDao.deleteMemberByKey(conn, member);
			System.out.println(todoRowCnt+"<-- MemberService.removeMemberByKey의 todoRowCnt");
			System.out.println(memberRowCnt+"<-- MemberService.removeMemberByKey의 memberRowCnt");
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback(); // 예외 발생했을 때 select 빼고 다 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
			// catch절에서 끝나면 무조건 false가 리턴
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (todoRowCnt+memberRowCnt) > 0;
	}
	
	// 로그인
	public Member getMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao();
		Connection conn = null; // Dao에서 빼고 여기서 선언
		try {
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.selectMemberByKey(conn, member);
			System.out.println(conn+"<-- MemberService.getMemberByKey의 conn");
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returnMember;
	}
	
}
