package bitproject.pms.controller.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bitproject.pms.dao.InterestDao;
import bitproject.pms.dao.MemberDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Interest;
import bitproject.pms.domain.Member;

@Controller("ajax.MemberController")
@RequestMapping("/bitproject/ajax/*")
public class MemberController {
  
  public static final String SAVED_DIR = "/file";
  
  @Autowired InterestDao interestDao;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext servletContext;

  @RequestMapping("idcheck")
  public Object idcheck() throws Exception {
    
    List<Member> members = memberDao.idList();

    HashMap<String, Object> resultMap = new HashMap<>();

    resultMap.put("status", "success");
    resultMap.put("data", members);
    
    return resultMap;
  }
  
  @RequestMapping("/member/list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="id") String keyword,
      @RequestParam(defaultValue="desc") String align,
      String word,String search) throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    paramMap.put("search", search);
    paramMap.put("word", word);
    
    List<Member> members = memberDao.selectList(paramMap);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", members);
    
    return resultMap;
  }
  
  /*
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String add() {
    return "member/MemberForm";
  }
  */
  @RequestMapping(value="addmember", method=RequestMethod.POST)
  public AjaxResult add(String interestList, Member member/*,
      MultipartFile photofile*/) throws Exception {
    Interest interest = new Interest();
/*    if (photofile.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(photofile.getOriginalFilename());
      File file = new File(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      photofile.transferTo(file);
      
      makeThumbnailImage(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
          servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName);
      student.setPhoto(newFileName);
    }*/
    memberDao.insert(member);
    
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
    
  @RequestMapping(value="detailmember")
  public Object detail(String id) throws Exception {
    Member member = memberDao.selectOne(id);

    return new AjaxResult("success", member);
  }
  
  @RequestMapping(value="updateinfo", method=RequestMethod.POST)
  public AjaxResult update(Member member/* MultipartFile file,*/   /* 예전 파일 명 */) throws Exception {
    /*if (file.getSize() > 0) {  //새로 업로드 한 파일이 있다.
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());
      File attachfile = new File(servletContext.gtRealPath(SAVED_DIR) + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    } else if (board.getAttachFile().length() == 0) {
      board.setAttachFile(null);
    }*/
    if (memberDao.updateinfo(member) <= 0) {
      return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value="updatepwd", method=RequestMethod.POST)
  public AjaxResult updatep(String id, String password, String newPassword) throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);
    paramMap.put("newPassword", newPassword);
    
    if (memberDao.updatepwd(paramMap) <= 0) {
      return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value="secession", method=RequestMethod.POST)
  public AjaxResult secession(String id, String password) throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);
    
    if (memberDao.secession(paramMap) <= 0) {
      return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }

  
  @RequestMapping("deletemember.do")
  public AjaxResult delete(String id, String password) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);
    
    if (memberDao.deletemember(paramMap) <= 0) {
      return new AjaxResult("failure", null);
    } 

    return new AjaxResult("success", null);
  }
  
  @RequestMapping("deleteAdmin")
  public AjaxResult deleteAdmin(String id) throws Exception {
   
    if (memberDao.deleteAdmin(id) <= 0) {
      return new AjaxResult("failure", null);
    } 
    return new AjaxResult("success", null);
  }
 
 
/*  private void makeThumbnailImage(String originPath, String thumbPath) 
      throws IOException {
    Thumbnails.of(new File(originPath))
    .size(60,44)
    .outputFormat("png")
    .outputQuality(1.0)
    .toFile(new File(thumbPath));
  }*/
}
