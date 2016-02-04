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

  List<Board> getRecommendList(
      int pageNo, int pageSize, 
      String keyword, String align, String id);

  // Admin 작성한 게시물
  List<Board> getAdminBoardList(
      int pageNo, int pageSize, 
      String keyword, String align);
  
  void register(Board board);
  
  int countAllBoard();
  
  int selectAdminCountAll();
  
  Board retieve(int no);
  
  int firstUpdate(Board board);

  int updateViews(int bno);

  int pluslikes(int bno);
  
  int plusdislikes(int bno);
  
  
  int updatePhoto(Board board);
  /*
  void remove(int no, String password);
  void change(Board board);
  */



}







