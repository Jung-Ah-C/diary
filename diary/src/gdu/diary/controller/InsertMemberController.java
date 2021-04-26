package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.dao.MemberDao;
import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/insertMember")
public class InsertMemberController extends HttpServlet {
	private MemberService memberService;
	
	// insertMember 입력 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/insertMember.jsp").forward(request, response);
	}
	
	// insertMember 액션 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		
		// 입력 form에서 받은 memberId, memberPw 수집
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		// 디버깅
		System.out.println(memberId+"<-- InsertMemberController의 memberId");
		System.out.println(memberPw+"<-- InsertMemberController의 memberPw");
		
		// 전처리: member vo 객체에 저장
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		
		// Service에서 insert 메서드 호출
		boolean checkId = this.memberService.checkMemberIdByKey(member);
		if(checkId == true) {
			System.out.println("이미 사용중인 아이디입니다.");
			response.sendRedirect(request.getContextPath()+"/insertMember");
			return;
		} else {
			System.out.println("회원가입 성공");
		}
		
		// redirect
		// 회원가입 완료 후, 로그인 페이지로 재요청
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
