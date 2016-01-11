package java76.pms.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import java76.pms.domain.Student;

public class AuthInterceptor extends HandlerInterceptorAdapter{
  private static final Logger log = Logger.getLogger(AuthInterceptor.class);
  
  @Override
  public boolean preHandle(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler) throws Exception {
    
    Student loginUser = (Student) request.getSession().getAttribute("loginUser");
    log.debug("INTERCEPTOR!!!!!!!!!!!!!!");
    if (!request.getServletPath().startsWith("/auth") 
        && loginUser == null) {
      
      response.sendRedirect(request.getContextPath() + "/auth/login.do");
      return false; // 다음으로 가는 것을 멈춰라.
    }
    
    return true;  // 다음 인터셉트 페이지 컨트롤러롤 가라.
  }
  
  
}
