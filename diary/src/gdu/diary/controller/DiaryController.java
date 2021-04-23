package gdu.diary.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.DiaryService;

@WebServlet("/auth/diary") // view 이름, 요청이름 동일하게
public class DiaryController extends HttpServlet {
	private DiaryService diaryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.diaryService = new DiaryService();
		String targetYear = request.getParameter("targetYear"); // 값이 넘어오지 않으면 null 이 넘어감
		String targetMonth = request.getParameter("targetMonth");
		
		Map<String, Object> diaryMap = this.diaryService.getDiary(targetYear, targetMonth);
		
		request.setAttribute("diaryMap", diaryMap);
		request.getRequestDispatcher("/WEB-INF/view/auth/diary.jsp").forward(request, response);
		// view : diary.jsp
	}
}
