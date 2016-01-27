package bitproject.pms.domain;

public class Location {

  protected int lno;
  protected String name;
  protected String content;
  protected int cost;
  protected String photo;
  protected double lat;
  protected double lng;
  protected String interestCode;

  
  
  @Override
  public String toString() {
    return "Location [lno=" + lno + ", name=" + name + ", content=" + content + ", cost=" + cost + ", photo=" + photo
        + ", lat=" + lat + ", lng=" + lng + ", interestCode=" + interestCode + "]";
  }
  
  public int getLno() {
    return lno;
  }
  public void setLno(int lno) {
    this.lno = lno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getCost() {
    return cost;
  }
  public void setCost(int cost) {
    this.cost = cost;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public double getLat() {
    return lat;
  }
  public void setLat(double lat) {
    this.lat = lat;
  }
  public double getLng() {
    return lng;
  }
  public void setLng(double lng) {
    this.lng = lng;
  }
  public String getInterestCode() {
    return interestCode;
  }
  public void setInterestCode(String interestCode) {
    this.interestCode = interestCode;
  }






}
