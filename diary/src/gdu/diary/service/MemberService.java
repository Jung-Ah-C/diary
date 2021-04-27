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
	
	// 회원가입 및 아이디 중복 확인
	// 아이디가 중복되거나 예외가 발생하면 false를 리턴함
	public boolean insertMember(Member member) {
		// 객체 생성 및 초기화
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Member returnMember = null;
		Connection conn = null; // Dao에서 빼고 여기서 선언
		try {
			conn = dbUtil.getConnection();
			if(this.memberDao.checkMemberIdByKey(conn, member.getMemberId()) != null) { // 아이디가 중복이라면
				return false;
			}
			this.memberDao.insertMember(conn, member);
			System.out.println(conn+"<-- MemberService.addMember의 conn");
			conn.commit();
		} catch(SQLException e) {
			try {
				// 둘 중 하나라도 에러가 나면 롤백 시킴
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
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
			System.out.println(member+"<-- MemberService.getMemberByKey의 member");
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
