package bitproject.pms.dao;

import java.util.List;

import bitproject.pms.domain.CoupleRequest;

public interface CoupleRequestDao {
  
  
  int insert(CoupleRequest coupleRequest);
  
  List<CoupleRequest> selectList();
  
  int delete(String my_id);
  
  int reject(String request_id);
  
  int accept(String request_id);
  
  List<CoupleRequest> idList();
  
  List<CoupleRequest> requestCheck();

  CoupleRequest selectOne(String id);

  
  /* 
  int update(Student student);
  Student selectOne(String email);
  Student login(Map<String,Object> paramMap);
  */
  
}







