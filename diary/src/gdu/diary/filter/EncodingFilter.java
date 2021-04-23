package gdu.diary.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {
	// 필터 메서드
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 타겟 요청이 실행 전
		chain.doFilter(request, response);
		// 타켓 요청이 실행 후
	}
	
	// 생성자
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }
    
	// 필터 시작 시
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	// 필터 종료 시
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
