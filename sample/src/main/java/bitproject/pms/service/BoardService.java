package bitproject.pms.service;

import java.util.List;

import bitproject.pms.domain.Board;

public interface BoardService {
  List<Board> getBoardList(
      int pageNo, int pageSize, 
      String keyword, String align);
 /* void register(Board board);
  void remove(int no, String password);
  void change(Board board);
  Board retieve(int no);*/
}







