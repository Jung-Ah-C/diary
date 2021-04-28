package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.vo.Member;

@WebServlet("/auth/modifyMember")
public class ModifyMemberController extends HttpServlet {
	// 수정 입력 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyMember.jsp").forward(request, response);
	}
	
	// 수정 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 호출, modifyMember.jsp에서 입력한 값을 받아옴
		String newMemberPw = request.getParameter("newMemberPw");
		Member member = (Member)request.getSession().getAttribute("sessionMember");
		member.setMemberPw(newMemberPw);
		
		// 서비스 호출
		
		// 세션 삭제
		request.getSession().invalidate();
		// 로그인 페이지로 재요청
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
