package bitproject.pms.domain;

public class Interest {
  protected String id;
  protected String interestCode;
  
  
  @Override
  public String toString() {
    return "Interest [id=" + id + ", interestCode=" + interestCode + "]";
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getInterestCode() {
    return interestCode;
  }
  public void setInterestCode(String interestCode) {
    this.interestCode = interestCode;
  }
  
  
}
