package bitproject.pms.dao;

import java.util.List;
import java.util.Map;

import bitproject.pms.domain.Location;

public interface LocationDao {
  List<Location> selectList(Map<String,Object> paramMap);
  List<Location> selectAll(Map<String,Object> paramMap);
  /*Location selectOne(int no);*/
  int insert(Location location);
/*
  int delete(Map<String,Object> paramMap);
  
  int update(Location location);*/

  Location selectOne(int no);
  
  List<Location> allLocation();
}







