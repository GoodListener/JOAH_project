package bitproject.pms.dao;

import java.util.List;

import bitproject.pms.domain.Comment;

public interface CommentDao {
  public List<Comment> selectList(int no);
  /*Location selectOne(int no);*/
  public int insert(Comment comment);
/*
  int delete(Map<String,Object> paramMap);
  
  int update(Location location);*/


  /*Comment selectOne(int no);*/
}







