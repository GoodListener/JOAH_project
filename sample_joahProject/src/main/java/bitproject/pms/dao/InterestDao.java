package bitproject.pms.dao;

import java.util.ArrayList;

import bitproject.pms.domain.Interest;

public interface InterestDao {
  int insert(Interest interest);

  ArrayList<Interest> selectList(String id);

  int updateinterest(Interest interest);

  int interestdelete(String id);
}







