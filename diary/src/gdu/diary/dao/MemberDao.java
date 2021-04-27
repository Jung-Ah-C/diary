package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	
	// 회원가입 시 아이디 중복 확인
	public String checkMemberIdByKey(Connection conn, String memberId) throws SQLException {
		String checkMemberId = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.CHECK_MEMBER_ID_BY_KEY);
			// view 에서 입력한 memberId를 가져와서 입력
			stmt.setString(1, memberId);
			System.out.println(stmt+"<-- MemberDao.checkMemberIdByKey의 stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				checkMemberId = rs.getString("memberId");
			}
		} finally {
			stmt.close();
		}
		return checkMemberId;
	}
	
	// 회원가입 
	public Member insertMember(Connection conn, Member member) throws SQLException {
		Member returnMember = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.INSERT_MEMBER);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println(stmt+"<-- MemberDao.insertMember의 stmt");
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return returnMember;
	}
	
	// 회원탈퇴
	public int deleteMemberByKey(Connection conn, Member member) throws SQLException {
		int rowCnt = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.DELETE_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			System.out.println(stmt+"<-- MemberDao.deleteMemberByKey의 stmt");
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return rowCnt;
	}
	
	// 회원 로그인
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException { // Connection 무조건 매개변수로 받아야된다
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println(stmt+"<-- MemberDao.selectMemberByKey의 stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberNo(rs.getInt("memberNo"));
				returnMember.setMemberId(rs.getString("memberId"));
			}
		} finally {
			stmt.close();
		}
		return returnMember;
	}
}
