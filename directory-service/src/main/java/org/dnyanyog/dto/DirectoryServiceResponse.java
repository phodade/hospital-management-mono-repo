package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class DirectoryServiceResponse {

  private String status;
  private String message;
  private long mobileNumber;
  private String role;
  private String password;
  private String encPassword;

  public String getEncPassword() {
    return encPassword;
  }

  public void setEncPassword(String encPassword) {
    this.encPassword = encPassword;
  }

  private long userid;

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }

  private String email;

  public static DirectoryServiceResponse getInstance() {
    return new DirectoryServiceResponse();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getmobileNumber() {
    return mobileNumber;
  }

  public void setmobileNumber(long mobileNumber) {
    this.mobileNumber = mobileNumber;
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
