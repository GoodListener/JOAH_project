package bitproject.pms.dao;

import java.util.List;

import bitproject.pms.domain.Course;

public interface CourseDao {
  int insert(Course course);

  List<Course> selectList(int bno);
}







