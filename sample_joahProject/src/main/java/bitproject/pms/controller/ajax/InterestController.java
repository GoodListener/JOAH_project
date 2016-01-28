package bitproject.pms.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitproject.pms.dao.InterestDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Interest;
import bitproject.pms.domain.Member;

@Controller("ajax.InterestController")
@RequestMapping("/bitproject/ajax/*")
public class InterestController {
  
  public static final String SAVED_DIR = "/file";
  
  @Autowired InterestDao interestDao;
  @Autowired ServletContext servletContext;

  @RequestMapping("interest")
  public Object detailInt(String id) throws Exception {
    ArrayList<Interest> interest = interestDao.selectList(id);
    List<String> interests = new ArrayList<>();
    for (int inter = 0; inter <interest.size(); inter++) {
      interests.add(interest.get(inter).getInterestCode());
      System.out.println(interests.get(inter));
    }
    return new AjaxResult("success", interests);
  }
  
  @RequestMapping("interestinsert")
  public Object insertInt(String interestList, Member member) throws Exception {
    Interest interest = new Interest();
    
    List<String> interests = new ArrayList<>();
    interestList = interestList.substring(1,interestList.length() - 1);
    System.out.println(interestList);
    for (String interToken : interestList.split(",")) {
      if(interToken.equals("null")) continue;
      interests.add(interToken.substring(1, interToken.length() - 1));
    }
    interest.setId(member.getId());
    for(String intResult : interests) {
      interest.setInterestCode(intResult);
      interestDao.insert(interest);
    }
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("interestdelete")
  public Object deleteInt(String id) throws Exception {
    if (interestDao.interestdelete(id) <= 0) {
      return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }
  
}
