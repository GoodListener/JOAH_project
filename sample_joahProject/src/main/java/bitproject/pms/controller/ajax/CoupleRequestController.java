package bitproject.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bitproject.pms.dao.CoupleRequestDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.CoupleRequest;


@Controller("ajax.CoupleRequestController")
@RequestMapping("/bitproject/ajax/*")
public class CoupleRequestController { 
  private static final Logger logger = Logger.getLogger(CoupleRequestController.class); 
  
  @Autowired CoupleRequestDao coupleRequestDao;
  
  @RequestMapping("/CR/list")
  public Object list() throws Exception {
    
    logger.debug("커플 신청 list");
    
    
    List<CoupleRequest> coupleRequests = coupleRequestDao.selectList();
    
    HashMap<String,Object> resultMap = new HashMap<>();
    
    resultMap.put("status", "success");
    resultMap.put("data", coupleRequests);
    
    return resultMap;
  }
  
  @RequestMapping(value="/CR/add", method=RequestMethod.POST)
  public AjaxResult add(CoupleRequest coupleRequest) throws Exception {
    
    logger.debug("친구 신청됨.");
    
    coupleRequestDao.insert(coupleRequest);
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("/CR/delete")
  public AjaxResult delete(String my_id) throws Exception {
    logger.debug("커플 신청취소됨.");
    if (coupleRequestDao.delete(my_id) <= 0) {
      return new AjaxResult("failure", null);
    } 
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("/CR/reject")
  public AjaxResult reject(String request_id) throws Exception {
    logger.debug("커플 신청 거절됨.");
    if (coupleRequestDao.reject(request_id) <= 0) {
      return new AjaxResult("failure", null);
    } 
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("/CR/accept")
  public AjaxResult accept(String request_id) throws Exception {
    logger.debug("커플 신청 거절됨.");
    if (coupleRequestDao.accept(request_id) <= 0) {
      return new AjaxResult("failure", null);
    } 
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("/CR/requestCheck")
  public Object requestCheck() throws Exception {
    logger.debug("requestCheck ㅎ");
    List<CoupleRequest> coupleRequests = coupleRequestDao.requestCheck();
    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", coupleRequests);
    return resultMap;
  }
  
  @RequestMapping("/CR/idcheck")
  public Object idcheck() throws Exception {
    logger.debug("커플 중복신청 방지.");
    List<CoupleRequest> coupleRequests = coupleRequestDao.idList();
    HashMap<String, Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", coupleRequests);
    return resultMap;
  }
  
  
  
  @RequestMapping("/CR/alarm")
  public Object alarm(String id) throws Exception {
    CoupleRequest coupleRequest = coupleRequestDao.selectOne(id);
    System.out.println(coupleRequest);
    return new AjaxResult("success", coupleRequest);
  }

  /*@RequestMapping(value="update", method=RequestMethod.POST)
  public AjaxResult update(Student student, MultipartFile file) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    } else if (board.getAttachFile().length() == 0) {
      board.setAttachFile(null);
    }
    
    
    if (studentDao.update(student) <= 0) {
      return new AjaxResult("failure", null);
    } 
    
    return new AjaxResult("success", null);
  }*/
  
}
