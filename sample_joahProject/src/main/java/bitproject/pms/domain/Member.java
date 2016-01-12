package bitproject.pms.domain;

import java.io.Serializable;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String id;
  protected String password;
  protected String name;
  protected String email;
  protected String gender;
  protected int    age;
  protected String photo;
  protected String cid; // couple id
  
  public Member() {}
  
  public Member(String name, String email) {
    this.email = email;
    this.name = name;
  }  
  
  @Override
  public String toString() {
    return "Student [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", gender=" + gender
        + ", age=" + age + ", photo=" + photo + ", cid=" + cid + "]";
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }
}
  
  

