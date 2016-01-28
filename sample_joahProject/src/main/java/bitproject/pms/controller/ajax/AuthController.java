package bitproject.pms.controller.ajax;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bitproject.pms.dao.MemberDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Member;

@Controller("ajax.AuthController")
@RequestMapping("/bitproject/ajax/*")
public class AuthController { 
  private static final Logger logger = Logger.getLogger(AuthController.class); 
  @Autowired MemberDao memberDao;
  
/*  @RequestMapping(value="login", method=RequestMethod.GET)
  public String loginform() {
    logger.debug("Login() 호출됨.");
    return "auth/LoginForm";
  }*/
      
  @RequestMapping(value="login", method=RequestMethod.POST)
  public AjaxResult login(
      String id,
      String password,
      HttpServletResponse response, 
      HttpSession session) {
    
    logger.debug("Login() 호출됨.");

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);
    
    Member member = memberDao.login(paramMap);

    if (member == null) { // 로그인 실패!
      session.invalidate(); // 세션을 무효화시킴. => 새로 세션 객체 생성!
      return new AjaxResult("failure", null);
    }

    session.setAttribute("loginUser", member);
    return new AjaxResult("success", member);
  }
  
  @RequestMapping(value="logout", method=RequestMethod.POST)
  public AjaxResult logout(HttpSession session) {
    
    logger.debug("Logout() 호출됨.");
    session.invalidate();
    return new AjaxResult("success", null);
  }
  
}
