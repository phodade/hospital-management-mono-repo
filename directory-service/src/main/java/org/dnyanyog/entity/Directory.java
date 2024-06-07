package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Directory {

  @Id @GeneratedValue @Column private long userid;
  @Column private String email;
  @Column private long mobileNumber;
  @Column private String role;
  @Column private String password;
  @Column private String encPassword;

  public String getEncPassword() {
    return encPassword;
  }

  public void setEncPassword(String encPassword) {
    this.encPassword = encPassword;
  }

  public long getmobileNumber() {
    return mobileNumber;
  }

  public void setmobileNumber(long mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public static Directory getInstance() {
    return new Directory();
  }

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
