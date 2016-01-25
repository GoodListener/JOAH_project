package bitproject.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Board;
import bitproject.pms.service.BoardService;

@Controller("ajax.BoardController")
@RequestMapping("/bitproject/ajax/*")
public class BoardController { 
  
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired BoardService boardService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("all")
  public AjaxResult countAll() {
    return new AjaxResult("success",boardService.countAllBoard());
  }
  
  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="6") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align) throws Exception {
    
    
    List<Board> boards = boardService.getBoardList(pageNo, pageSize, keyword, align);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boards);
    
    return resultMap;
  }
  
/*  
 @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "board/BoardForm";
  }*/

  
  @RequestMapping(value="addboard", method=RequestMethod.POST)
  public Object add(Board board) throws Exception {
    boardService.register(board);
    int boardNo = board.getBno();
    System.out.println("LastInsert boardNo" + boardNo);
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boardNo);
    return resultMap;
  }
  
  @RequestMapping("detail")
  public Object detail(int no) throws Exception {
    Board board = boardService.retieve(no);
    return new AjaxResult("success", board);
  }

  @RequestMapping(value="realAddboard", method=RequestMethod.POST)
  public AjaxResult update(Board board) throws Exception {
    
    /*if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    } else if (board.getAttachFile().length() == 0) {
      board.setAttachFile(null);
    }*/
    
    if (boardService.firstUpdate(board) <= 0) {
      return new AjaxResult("failure", null);
    } 
    
    return new AjaxResult("success", null);
  }
/*  
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
