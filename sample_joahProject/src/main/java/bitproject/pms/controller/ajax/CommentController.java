package bitproject.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bitproject.pms.dao.CommentDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Comment;

@Controller("ajax.CommentController")
@RequestMapping("/bitproject/ajax/*")
public class CommentController { 
  
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired CommentDao commentDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("comment")
  public Object comment(int no) throws Exception{
    List<Comment> comments = commentDao.selectList(no);

    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", comments);
    
    return resultMap;
  }
  
  @RequestMapping("listComment")
  public Object listComment() throws Exception{
    List<Comment> comments = commentDao.commentList();
    
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", comments);
    
    return resultMap;
  }
  
  
 /* @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "board/BoardForm";
  }*/
  
  
  @RequestMapping(value="addcomment", method=RequestMethod.POST)
  public AjaxResult add(Comment comment) throws Exception {
    
    commentDao.insert(comment);
    
    return new AjaxResult("success", null);
  }

/*  @RequestMapping("locationDetail")
  public Object detail(int no) throws Exception {
    Location location = locationDao.selectOne(no);
    return new AjaxResult("success", location);
  }*/
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
  */
  @RequestMapping("deletecomment")
  public AjaxResult delete(int no, String id) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("id", id);
    
    if (commentDao.delete(paramMap) <= 0) {
      return new AjaxResult("failure", null);
    } 

    return new AjaxResult("success", null);
  }
}
