package bitproject.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bitproject.pms.dao.CourseDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Course;

@Controller("ajax.CourseController")
@RequestMapping("/bitproject/ajax/*")
public class CourseController { 
  
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired CourseDao courseDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("courselist")
  public Object loc(int bno){
    List<Course> courses;
    courses = courseDao.selectList(bno);
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", courses);
    
    return resultMap;
  }
  
  
 /* @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "board/BoardForm";
  }*/
  
  
  @RequestMapping(value="addcourse", method=RequestMethod.POST)
  public AjaxResult add(String lnoArray, int bno) throws Exception {
    int[] lnos = new int[4];
    Course course = new Course();
    lnoArray = lnoArray.substring(1,lnoArray.length() - 1);
    int i = 0;
    for (String lno : lnoArray.split(",")) {
      if(lno.equals("null")) continue;
      lnos[i] = Integer.parseInt(lno);
      i++;
    }
    for(int lno : lnos) {
      course.setLno(lno);
      course.setBno(bno);
      courseDao.insert(course);
    }
    return new AjaxResult("success", null);
  }
  
 /*
  @RequestMapping("locationDetail")
  public Object detail(int no) throws Exception {
    Location location = locationDao.selectOne(no);
    return new AjaxResult("success", location);
  }

  @RequestMapping(value="update", method=RequestMethod.POST)
  public AjaxResult update(Board board, MultipartFile file) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    } else if (board.getAttachFile().length() == 0) {
      board.setAttachFile(null);
    }
    
    
    if (boardDao.update(board) <= 0) {
      return new AjaxResult("failure", null);
    } 
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("delete.do")
  public AjaxResult delete(int no, String password) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);
    
    if (boardDao.delete(paramMap) <= 0) {
      return new AjaxResult("failure", null);
    } 

    return new AjaxResult("success", null);
  }*/
}
