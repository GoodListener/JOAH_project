package java76.pms.dao;

import java.util.List;
import java.util.Map;

import java76.pms.domain.Student;

public interface StudentDao {
  
  public List<Student> selectList(Map<String, Object> paramMap);

  public int insert(Student student);

  public int delete(String email);
  
  public int update(Student student);

  public Student selectOne(String email);

  public Student selectOneByEmailPassword(Map<String, Object> paramMap);
}

