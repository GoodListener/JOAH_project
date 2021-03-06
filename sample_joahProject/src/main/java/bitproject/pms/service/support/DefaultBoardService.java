package bitproject.pms.service.support;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitproject.pms.dao.BoardDao;
import bitproject.pms.domain.Board;
import bitproject.pms.service.BoardService;

@Service
public class DefaultBoardService implements BoardService {
  @Autowired BoardDao boardDao;
  
  public List<Board> getBoardList(int pageNo, int pageSize, 
      String keyword, String align) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    return boardDao.selectList(paramMap);
  }
  
  public List<Board> getBoardIngList(int pageNo, int pageSize, 
      String keyword, String align, String id) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    paramMap.put("id", id);
    
    return boardDao.selectIngList(paramMap);
  }
  
  public List<Board> getBoardMyList(int pageNo, int pageSize, 
      String keyword, String align, String id) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    paramMap.put("id", id);
    
    return boardDao.selectMyList(paramMap);
  }
  
  public List<Board> getAdminBoardList(int pageNo, int pageSize, 
      String keyword, String align) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    return boardDao.selectAdminlist(paramMap);
  }
 
  public void register(Board board) {
    boardDao.insert(board);
  }
  
  public int countAllBoard() {
    return boardDao.selectCountAll();
  }
  
  public int selectAdminCountAll() {
    return boardDao.selectAdminCountAll();
  }
  
  
  public Board retieve(int no) {
    return boardDao.selectOne(no);
  }

  @Override
  public int firstUpdate(Board board) {
    return boardDao.firstUpdate(board);
  }

  @Override
  public int updateViews(int bno) {
    return boardDao.updateViews(bno);
  }

  @Override
  public int updatePhoto(Board board) {
    return boardDao.updatePhoto(board);
  }

  @Override
  public List<Board> getRecommendList(
      int pageNo, int pageSize, String keyword, String align, String id) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    paramMap.put("id", id);
    
    return boardDao.recommendList(paramMap);
  }
  
  @Override
  public int pluslikes(int bno) {
    return boardDao.pluslikes(bno);
  }
  
  @Override
  public int minuslikes(int bno) {
    return boardDao.minuslikes(bno);
  }
  
  @Override
  public int plusdislikes(int bno) {
    return boardDao.plusdislikes(bno);
  }
  
  /*
  public void remove(int no, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);
    
    boardDao.delete(paramMap);
  }
  
  public void change(Board board) {
    boardDao.update(board);
  }

  */
}







