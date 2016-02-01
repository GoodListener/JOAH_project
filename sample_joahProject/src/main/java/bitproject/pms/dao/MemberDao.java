package bitproject.pms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bitproject.pms.domain.Member;

public interface MemberDao {
  List<Member> selectList(Map<String,Object> paramMap);

  int insert(Member member);
  
  Member login(Map<String,Object> paramMap);
  
  List<Member> idList();

  Member selectOne(String id);
  
  int updateinfo(Member member);

  int updatepwd(HashMap<String, Object> paramMap);
  
  int deletemember(HashMap<String, Object> paramMap);
  
  int deleteAdmin(String id);

  int secession(HashMap<String, Object> paramMap);
  
  Member coupleinfo(String id);
  
/*
  
  int update(Student student);

  Student selectOne(String email);

  Student selectOneByEmailPassword(Map<String,Object> paramMap);*/





}







