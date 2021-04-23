package gdu.diary.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBDriverListener implements ServletContextListener { // DBDriverListener는 Object 상속 받음
	// 생성자
    public DBDriverListener() {
        
    }
    // 톰캣 종료시
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }
    // 톰캣 부팅 시
    public void contextInitialized(ServletContextEvent sce)  { 
         try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println(this.getClass() + "DB Driver 로딩 성공"); // getClass: 자기자신 클래스를 리턴해준다
		} catch (ClassNotFoundException e) {
			System.out.println("DB Driver 로딩 실패");
			e.printStackTrace();
		}
    }
	
}
