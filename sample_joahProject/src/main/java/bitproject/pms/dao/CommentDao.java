package bitproject.pms.dao;

import java.util.List;
import java.util.Map;

import bitproject.pms.domain.Comment;

public interface CommentDao {
  public List<Comment> selectList(int no);
  /*Location selectOne(int no);*/
  public int insert(Comment comment);
/*
  
  int update(Location location);*/
  
  public int delete(Map<String, Object> paramMap);
  
  public List<Comment> commentList();
  
  Comment countComment(int no);
  
  /*Comment selectOne(int no);*/
}







