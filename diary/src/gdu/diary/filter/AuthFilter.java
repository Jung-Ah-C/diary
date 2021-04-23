package gdu.diary.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/auth/*") 
//  Auth/IndexController -> 요청 1. EncodingFilter 2. AuthFilter 3. IndexController 4. AuthFilter 5. EncodingFilter
public class AuthFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 로그인 했는지 안 했는지 확인하는 코드 안 적어도 된다.
		// 로그인 되어있지 않은 상태에서 "auth/"문자로 시작하는 요청이 들어오면 redirect
		HttpServletRequest httpRequest = (HttpServletRequest)request; // 형변환
		HttpSession session = httpRequest.getSession(); 
	
		if(session.getAttribute("sessionMember") == null) {
			// 자식타입으로 형변환
			HttpServletResponse httpResponse = (HttpServletResponse)response; 
			// 주소창에 http://localhost/diary/auth/~ 검색하면 필터되어 login.jsp로 이동
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login"); 
			return; // 새로운 요청 발생으로 doFilter 메서드를 종료
		}
		chain.doFilter(request, response); 
	}

    public AuthFilter() {
      
    }

	public void destroy() {
	
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
