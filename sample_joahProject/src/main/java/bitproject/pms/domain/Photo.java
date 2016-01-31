package bitproject.pms.domain;

import java.io.Serializable;

public class Photo implements Serializable {
  private static final long serialVersionUID = 1L;

  protected String  photoName;
  protected String  originName;
  protected int     pno;
  protected int     bno;
  public String getPhotoName() {
    return photoName;
  }
  public void setPhotoName(String photoName) {
    this.photoName = photoName;
  }
  public String getOriginName() {
    return originName;
  }
  public void setOriginName(String originName) {
    this.originName = originName;
  }
  public int getPno() {
    return pno;
  }
  public void setPno(int pno) {
    this.pno = pno;
  }
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }
  @Override
  public String toString() {
    return "Photo [photoName=" + photoName + ", originName=" + originName + ", pno=" + pno + ", bno=" + bno + "]";
  }
  
  
  
}





