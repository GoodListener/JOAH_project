package bitproject.pms.dao;

import java.util.List;

import bitproject.pms.domain.Location;

public interface LocationDao {
  List<Location> selectList(String intCode);
  List<Location> selectAll();
  /*Location selectOne(int no);*/
  int insert(Location location);
/*
  int delete(Map<String,Object> paramMap);
  
  int update(Location location);*/

  Location selectOne(int no);
}







