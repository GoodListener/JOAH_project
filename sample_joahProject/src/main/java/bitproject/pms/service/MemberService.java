package bitproject.pms.service;

import bitproject.pms.domain.Member;

public interface MemberService {
 /* List<Student> getStudentList(
      int pageNo, 
      int pageSize, 
      String keyword, 
      String align);*/
  void register(Member member);
  /*void remove(String email);
  void change(Student student);*/
  
  Member retrieve(String id);
  /*
  Student retrieve(String email, String password);*/
}







