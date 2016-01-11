package bitproject.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitproject.pms.dao.LocationDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Location;

@Controller("ajax.LocationController")
@RequestMapping("/bitproject/ajax/*")
public class LocationController { 
  
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired LocationDao locationDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("location")
  public Object loc(){
    List<Location> locations = locationDao.selectList();
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", locations);
    
    return resultMap;
  }
  
  
 /* @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "board/BoardForm";
  }
  
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add(Board board, MultipartFile file) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    }
    
    boardDao.insert(board);
    
    return new AjaxResult("success", null);
  }
  */
  @RequestMapping("locationDetail")
  public Object detail(int no) throws Exception {
    Location location = locationDao.selectOne(no);
    return new AjaxResult("success", location);
  }
/*
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
