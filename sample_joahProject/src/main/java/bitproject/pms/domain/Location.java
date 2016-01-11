package bitproject.pms.domain;

public class Location {
  protected int no;
  protected String name;
  protected String content;
  protected int cost;
  protected String photo;
  protected double lat;
  protected double lng;
  
  
  @Override
  public String toString() {
    return "Location [no=" + no + ", name=" + name + ", content=" + content + ", cost=" + cost + ", photo=" + photo
        + ", lat=" + lat + ", lng=" + lng + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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
  
  
  
  
  
  
}
