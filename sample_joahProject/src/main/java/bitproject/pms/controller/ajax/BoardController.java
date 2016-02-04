package bitproject.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bitproject.pms.dao.CommentDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Board;
import bitproject.pms.domain.Comment;
import bitproject.pms.service.BoardService;

@Controller("ajax.BoardController")
@RequestMapping("/bitproject/ajax/*")
public class BoardController { 
  private static final Logger logger = Logger.getLogger(BoardController.class); 
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired BoardService boardService;
  @Autowired CommentDao commentDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("all")
  public AjaxResult countAll() {
    logger.debug("Board All() 호출됨.");
    return new AjaxResult("success",boardService.countAllBoard());
  }
  
  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="6") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align) throws Exception {
    
    logger.debug("Board list() 호출됨.");
    List<Board> boards = boardService.getBoardList(pageNo, pageSize, keyword, align);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boards);
    
    return resultMap;
  }
  
/*  @RequestMapping("adminlist")
  public Object adminlist(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="6") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align) throws Exception {
    
    logger.debug("Board list() 호출됨.");
    List<Board> boards = boardService.getBoardList(pageNo, pageSize, keyword, align);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boards);
    
    return resultMap;
  }*/
  
  @RequestMapping("recommendlist")
  public Object recommendlist(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="6") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
      @RequestParam(defaultValue="guest") String id) throws Exception {
    
    logger.debug("Board list() 호출됨.");
    List<Board> boards = boardService.getRecommendList(pageNo, pageSize, keyword, align, id);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boards);
    
    return resultMap;
  }
  
//작성중인 게시물 isboard=0 이고 id=#{id}인 게시물
  @RequestMapping("inglist")
  public Object inglist(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="6") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
      String id) throws Exception {
    
    logger.debug("Board inglist() 호출됨");
    
    List<Board> boards = boardService.getBoardIngList(pageNo, pageSize, keyword, align, id);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boards);
    
    return resultMap;
  }
  
//작성중인 게시물 isboard=1 이고 id=#{id}인 게시물
  @RequestMapping("mylist")
  public Object mylist(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="6") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
      String id) throws Exception {
    
    
    logger.debug("Board mylist() 호출됨.");
    
    List<Board> boards = boardService.getBoardMyList(pageNo, pageSize, keyword, align, id);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boards);
    
    return resultMap;
  }
  
  @RequestMapping("Adminlist")
  public Object adminList(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="6") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
      @RequestParam(defaultValue="admin")String id) throws Exception {
    
    
    logger.debug("Board Adminlist() 호출됨.");
    
    List<Board> boards = boardService.getAdminBoardList(pageNo, pageSize, keyword, align, id);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boards);
    
    return resultMap;
  }
  
  @RequestMapping(value="addboard", method=RequestMethod.POST)
  public Object add(Board board) throws Exception {
    boardService.register(board);
    int boardNo = board.getBno();
    
    logger.debug("Board addboard() 호출됨.");
    System.out.println("LastInsert boardNo" + boardNo);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", boardNo);
    return resultMap;
  }
  
  @RequestMapping("detail")
  public Object detail(int no) throws Exception {
    
    logger.debug("Board detail() 호출됨.");
    Comment comment = commentDao.countComment(no);
    Board board = boardService.retieve(no);
    board.setCommentCount(comment.getCount());
    return new AjaxResult("success", board);
  }

  @RequestMapping(value="realAddboard", method=RequestMethod.POST)
  public AjaxResult update(Board board) throws Exception {

    if (boardService.firstUpdate(board) <= 0) {
      return new AjaxResult("failure", null);
    } 
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("updateViews")
  public AjaxResult updateViews(int no) throws Exception {
    boardService.updateViews(no);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("pluslikes")
  public AjaxResult pluslikes(int no) throws Exception {
    boardService.pluslikes(no);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("plusdislikes")
  public AjaxResult plusdislikes(int no) throws Exception {
    boardService.plusdislikes(no);
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
