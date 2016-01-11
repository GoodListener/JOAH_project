package java76.pms.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java76.pms.dao.StudentDao;
import java76.pms.domain.Student;
import java76.pms.service.StudentService;

@Controller
@RequestMapping("/auth/*")
public class AuthController {  
  @Autowired StudentDao studentDao;
  @Autowired StudentService studentService;

  @RequestMapping(value="login", method=RequestMethod.GET)
  public String loginform() {
    return "auth/LoginForm";
  }
  
  
  @RequestMapping(value="login", method=RequestMethod.POST)
  public String login(
      String email,
      String password,
      String saveEmail,
      HttpSession session,
      HttpServletResponse response) 
          throws Exception {
    
    System.out.println("로그인까지 들어옴");
    Cookie emailCookie = null;
    if (saveEmail != null) { // 이메일 저장을 체크했으면,
      emailCookie = new Cookie("email", email);
      emailCookie.setMaxAge(60 * 60 * 24 * 15);
    } else {
      emailCookie = new Cookie("email", "");
      emailCookie.setMaxAge(0); // 웹브라우저에게 email 쿠키 삭제를 명령한다.
    }
    response.addCookie(emailCookie);
    
    HashMap<String, Object> paramMap = new HashMap<>();
    
    paramMap.put("email", email);
    paramMap.put("password", password);
    
    Student student = studentService.retrieve(email, password);
    
    if (student == null) { // 로그인 실패!
      session.invalidate(); // 세션을 무효화시킴. => 새로 세션 객체 생성!
      return "/auth/logout.do";
    }

    session.setAttribute("loginUser", student);
    return "redirect:../board/list.do";
  }
  
  @RequestMapping("logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:login.do";
  }
}

