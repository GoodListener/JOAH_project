package bitproject.pms.controller.ajax;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bitproject.pms.dao.MemberDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Member;

@Controller("ajax.MemberController")
@RequestMapping("/bitproject/ajax/*")
public class MemberController {
  
  public static final String SAVED_DIR = "/file";
  
  @Autowired MemberDao memberDao;
  @Autowired ServletContext servletContext;

  /*@RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align) 
          throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    List<Student> students = memberDao.selectList(paramMap);

    HashMap<String, Object> resultMap = new HashMap<>();

    resultMap.put("status", "success");
    resultMap.put("data", students);
    
    return resultMap;
  }*/
  
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String add() {
    return "member/MemberForm";
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add( Member member/*,
      MultipartFile photofile*/) throws Exception {

/*    if (photofile.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(photofile.getOriginalFilename());
      File file = new File(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      photofile.transferTo(file);
      
      makeThumbnailImage(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
          servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName);
      student.setPhoto(newFileName);
    }*/
    
    memberDao.insert(member);

    return new AjaxResult("success", null);

  }
  
  
  /*@RequestMapping("detail")
  public String detail(String email, Model model) 
          throws Exception {

    Student student = studentService.retrieve(email);
    model.addAttribute("student", student);

    return "student/StudentDetail";
  }

  @RequestMapping("update")
  public String update(
      String name,
      String email,
      String tel,
      String cid,
      String photo,
      MultipartFile photofile,
      Model model) throws Exception {

    String newFileName = null;
    
    if (photofile.getSize() > 0) {
      newFileName = MultipartHelper.generateFilename(photofile.getOriginalFilename());  
      File attachfile = new File(
          servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      photofile.transferTo(attachfile);
      
      makeThumbnailImage(
          servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
          servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName + ".png");
    }
    
    Student student = new Student();
    student.setName(name);
    student.setEmail(email);
    student.setTel(tel);
    student.setCid(cid);
    
    if (newFileName != null) {
      student.setPhoto(newFileName);
    } else if (newFileName == null && photo.length() > 0) {
      student.setPhoto(photo);
    }
    
    studentService.change(student);
    return "redirect:list.do";
  }
  
  @RequestMapping("delete")
  public String delete(String email, Model model) throws Exception {
    studentService.remove(email);
    return "redirect:list.do";
  }
  */
/*  private void makeThumbnailImage(String originPath, String thumbPath) 
      throws IOException {
    Thumbnails.of(new File(originPath))
    .size(60,44)
    .outputFormat("png")
    .outputQuality(1.0)
    .toFile(new File(thumbPath));
  }*/
}
