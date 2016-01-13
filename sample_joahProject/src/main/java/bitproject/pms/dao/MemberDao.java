package bitproject.pms.dao;

import java.util.Map;

import bitproject.pms.domain.Member;

public interface MemberDao {
/*  List<Student> selectList(Map<String,Object> paramMap);*/

  int insert(Member member);
  
  Member login(Map<String,Object> paramMap);
/*
  int delete(String email);
  
  int update(Student student);

  Student selectOne(String email);

  Student selectOneByEmailPassword(Map<String,Object> paramMap);*/
}







