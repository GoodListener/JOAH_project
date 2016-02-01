package bitproject.pms.dao;

import java.util.List;
import java.util.Map;

import bitproject.pms.domain.Board;

public interface BoardDao {
  List<Board> selectList(Map<String,Object> paramMap);
  
  List<Board> selectIngList(Map<String,Object> paramMap);
  
  List<Board> selectMyList(Map<String,Object> paramMap);
  
  Board selectOne(int no);
  
  int insert(Board board);
  
  int firstUpdate(Board board);
  
  int selectCountAll();

  int updateViews(int bno);
  
  /*
  int delete(Map<String,Object> paramMap);
  
  int update(Board board);

  Board selectOne(int no);*/
  
  
}







