package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/auth/modifyMember")
public class ModifyMemberController extends HttpServlet {
	private MemberService memberService;
	
	// 수정 입력 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyMember.jsp").forward(request, response);
	}
	
	// 수정 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		
		// request 호출, modifyMember.jsp에서 입력한 값을 받아옴
		String newMemberPw = request.getParameter("newMemberPw");
		// 세션에서 memberNo, memberId를 가져오기 위해서 요청
		Member member = (Member)request.getSession().getAttribute("sessionMember");
		member.setMemberPw(newMemberPw);
		System.out.println(newMemberPw+"<-- ModifyMemberController의 newMemberPw"); // 디버깅
		
		// 서비스 호출
		boolean result = this.memberService.modifyMember(member);
		
		// 비밀번호 수정이 잘 되었는지 확인
		if(result == false) { // 수정 완료 : true
			System.out.println("비밀번호 수정 실패");
			response.sendRedirect(request.getContextPath()+"/auth/modifyMember"); // 다시 회원정보 수정 페이지로 재요청
			return; // 코드 멈춤, 수정 완료시에만 밑에 있는 코드를 실행하게 함
		} else {
			System.out.println("비밀번호 수정 성공");
		}
		
		// 세션 초기화
		request.getSession().invalidate();
		
		// 로그인 페이지로 재요청
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
