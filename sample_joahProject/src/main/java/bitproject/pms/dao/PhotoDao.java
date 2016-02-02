package bitproject.pms.dao;

import java.util.ArrayList;

import bitproject.pms.domain.Photo;

public interface PhotoDao {
  
  int insertPhoto(Photo photo);
  int deletePhoto(int pno);
  ArrayList<Photo> selectPhotoOne(int no);
}







