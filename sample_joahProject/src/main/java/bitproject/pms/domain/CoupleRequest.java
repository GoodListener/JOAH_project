package bitproject.pms.domain;

import java.util.Date;

public class CoupleRequest {
  
  protected String my_id;
  protected String my_name;
  protected String request_id;
  protected Date   request_dt;
  
  @Override
  public String toString() {
    return "CoupleRequest [my_id=" + my_id + ", my_name=" + my_name + ", request_id=" + request_id + ", request_dt="
        + request_dt + "]";
  }
  
  public String getMy_id() {
    return my_id;
  }
  public void setMy_id(String my_id) {
    this.my_id = my_id;
  }
  public String getMy_name() {
    return my_name;
  }
  public void setMy_name(String my_name) {
    this.my_name = my_name;
  }
  public String getRequest_id() {
    return request_id;
  }
  public void setRequest_id(String request_id) {
    this.request_id = request_id;
  }
  public Date getRequest_dt() {
    return request_dt;
  }
  public void setRequest_dt(Date request_dt) {
    this.request_dt = request_dt;
  }
  
  
  
}
