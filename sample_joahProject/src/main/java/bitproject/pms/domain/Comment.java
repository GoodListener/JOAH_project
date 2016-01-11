package bitproject.pms.domain;

import java.util.Date;

public class Comment {
  protected int cno;
  protected int bno;
  protected String id;  
  protected String content;
  protected Date createdTime;
  
  @Override
  public String toString() {
    return "Comment [cno=" + cno + ", bno=" + bno + ", id=" + id + ", content=" + content + ", createdTime="
        + createdTime + "]";
  }
  public int getCno() {
    return cno;
  }
  public void setCno(int cno) {
    this.cno = cno;
  }
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getCreatedTime() {
    return createdTime;
  }
  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }
  
}
