package bitproject.pms.domain;

public class Comment {
  protected int cno;
  protected int bno;
  protected String id;  
  protected String content;
  protected String createdTime;
  protected String  photo;
  protected String  title;
  
  @Override
  public String toString() {
    return "Comment [cno=" + cno + ", bno=" + bno + ", id=" + id + ", content=" + content + ", createdTime="
        + createdTime + "]";
  }
  
  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
  public String getCreatedTime() {
    return createdTime;
  }
  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }
  
}
