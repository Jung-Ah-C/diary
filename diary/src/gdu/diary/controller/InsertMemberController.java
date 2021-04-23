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

@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	private MemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/insertMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		
		// 입력 form에서 받은 memberId, memberPw 수집
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		// 디버깅
		System.out.println("memberId: "+ memberId);
		System.out.println("memberPw: "+ memberPw);
		
		// 전처리: member vo 객체에 저장
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// Service에서 insert 메서드 호출
		Member returnMember = this.memberService.addMember(member);
		
		// redirect
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
