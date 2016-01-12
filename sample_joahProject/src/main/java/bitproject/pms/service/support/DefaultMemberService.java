package bitproject.pms.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitproject.pms.dao.MemberDao;
import bitproject.pms.domain.Member;
import bitproject.pms.service.MemberService;

@Service
public class DefaultMemberService implements MemberService {
  @Autowired MemberDao memberDao;
  
/*  public List<Student> getStudentList(
      int pageNo, 
      int pageSize, 
      String keyword, 
      String align) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    return studentDao.selectList(paramMap);
  }*/

  public void register(Member student) {
    memberDao.insert(student);
  }
/*
  public void remove(String email) {
    studentDao.delete(email);
  }
  
  public void change(Student student) {
    studentDao.update(student);
  }

  public Student retrieve(String email) {
    return studentDao.selectOne(email);
  }

  public Student retrieve(String email, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);
    
    return studentDao.selectOneByEmailPassword(paramMap);
  }*/
}







