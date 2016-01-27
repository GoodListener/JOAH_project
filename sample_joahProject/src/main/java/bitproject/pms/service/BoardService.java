package bitproject.pms.service;

import java.util.List;

import bitproject.pms.domain.Board;

public interface BoardService {
  List<Board> getBoardList(
      int pageNo, int pageSize, 
      String keyword, String align);
  
  // 작성중인 게시물 isboard=0 이고 id=#{id}인 게시물
  List<Board> getBoardIngList(
      int pageNo, int pageSize, 
      String keyword, String align, String id);
  
  // 내가 작성한 게시물 isboard=1 이고 id=#{id}인 게시물
  List<Board> getBoardMyList(
      int pageNo, int pageSize, 
      String keyword, String align, String id);
  
  void register(Board board);
  
  int countAllBoard();
  
  Board retieve(int no);
  
  int firstUpdate(Board board);
  
  /*
  void remove(int no, String password);
  void change(Board board);
  */
}







