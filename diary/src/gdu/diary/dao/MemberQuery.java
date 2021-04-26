package gdu.diary.dao;

public class MemberQuery {
	public final static String SELECT_MEMBER_BY_KEY;
	public final static String INSERT_MEMBER;
	public final static String DELETE_MEMBER_BY_KEY;
	public final static String CHECK_MEMBER_ID_BY_KEY;
	
	static {
		SELECT_MEMBER_BY_KEY = "SELECT member_no memberNo, member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)"; 
		INSERT_MEMBER = "INSERT INTO member(member_id, member_pw, member_date) VALUES(?,PASSWORD(?),NOW())";
		DELETE_MEMBER_BY_KEY = "DELETE FROM member WHERE member_no=? AND member_pw=PASSWORD(?)";
		CHECK_MEMBER_ID_BY_KEY = "SELECT member_id memberId FROM member WHERE member_id=?";
	}
}
