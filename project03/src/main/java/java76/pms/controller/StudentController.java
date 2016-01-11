package java76.pms.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java76.pms.domain.Student;
import java76.pms.service.StudentService;
import java76.pms.util.MultipartHelper;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/student/*")
public class StudentController{
  public final static String SAVED_DIR = "/file";
  
  @Autowired StudentService studentService;
  @Autowired ServletContext servletContext;

  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
      HttpServletRequest request, 
      HttpServletResponse response) 
          throws Exception {

    List<Student> students = studentService.getStudentList(pageNo, pageSize, keyword, align);

    request.setAttribute("students", students);

    return "student/StudentList";
  }
  
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String add() {
    return "student/StudentForm";
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public String add(
      Student student,
      MultipartFile photofile) throws Exception {

    if (photofile.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(photofile.getOriginalFilename());
      File file = new File(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      photofile.transferTo(file);
      
      makeThumbnailImage(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
          servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName);
      student.setPhoto(newFileName);
    }
    
    studentService.register(student);

    return "redirect:list.do";

  }
  
  @RequestMapping("delete")
  public String delete(
      String email,
      Model model) throws Exception {

    studentService.remove(email);
    return "redirect:list.do";
  }
  
  @RequestMapping("detail")
  public String detail(
      String email,
      Model model) 
          throws Exception {

    Student student = studentService.retrieve(email);
    model.addAttribute("student", student);

    return "student/StudentDetail";
  }

  @RequestMapping(value="update", method=RequestMethod.POST)
  public String update(
      Student student,
      MultipartFile photofile,
      Model model) throws Exception {

    String newFileName = null;
    if (photofile.getSize() > 0) {
      newFileName = MultipartHelper.generateFilename(photofile.getOriginalFilename());
      File file = new File(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      photofile.transferTo(file);
      
      makeThumbnailImage(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
              servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName);
      student.setPhoto(newFileName);
    } else if (student.getPhoto().length() == 0) {
      student.setPhoto(null);
    } 

    studentService.change(student);

    return "redirect:list.do";
  }
  
  private void makeThumbnailImage(String originPhoto, String saveDir) throws Exception {
    Thumbnails.of(new File(originPhoto))
    .size(60, 60)
    .toFile(saveDir);
  }
  
  
}
