package bitproject.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Board  implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int     bno;
  protected String  id;
  protected String  title;
  protected String  content;
  protected int     views;
  protected Date    createdDate;
  protected int     likes;
  protected int     dislikes;
  protected String  heart;
  protected String  startLocation;
  protected String  endLocation;
  protected String  icon;
  protected int     ageGroup;
  protected int     costAll;
  protected String  photo;
  protected String  intCode;
  protected int     commentCount; // 댓글수
  
 
  
  @Override
  public String toString() {
    return "Board [bno=" + bno + ", id=" + id + ", title=" + title + ", content=" + content + ", views=" + views
        + ", createdDate=" + createdDate + ", likes=" + likes + ", dislikes=" + dislikes + ", heart=" + heart
        + ", startLocation=" + startLocation + ", endLocation=" + endLocation + ", icon=" + icon + ", ageGroup="
        + ageGroup + ", costAll=" + costAll + ", photo=" + photo + ", intCode=" + intCode + ", commentCount="
        + commentCount + "]";
  }
  public int getCommentCount() {
    return commentCount;
  }
  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
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
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getViews() {
    return views;
  }
  public void setViews(int views) {
    this.views = views;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getLikes() {
    return likes;
  }
  public void setLikes(int likes) {
    this.likes = likes;
  }
  public int getDislikes() {
    return dislikes;
  }
  public void setDislikes(int dislikes) {
    this.dislikes = dislikes;
  }
  public String getHeart() {
    return heart;
  }
  public void setHeart(String heart) {
    this.heart = heart;
  }
  public String getStartLocation() {
    return startLocation;
  }
  public void setStartLocation(String startLocation) {
    this.startLocation = startLocation;
  }
  public String getEndLocation() {
    return endLocation;
  }
  public void setEndLocation(String endLocation) {
    this.endLocation = endLocation;
  }
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
  public int getAgeGroup() {
    return ageGroup;
  }
  public void setAgeGroup(int ageGroup) {
    this.ageGroup = ageGroup;
  }
  public int getCostAll() {
    return costAll;
  }
  public void setCostAll(int costAll) {
    this.costAll = costAll;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getIntCode() {
    return intCode;
  }
  public void setIntCode(String intCode) {
    this.intCode = intCode;
  }
  
}
