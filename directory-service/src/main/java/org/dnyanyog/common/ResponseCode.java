package org.dnyanyog.common;

public enum ResponseCode {
  Add_User_Success("Success", "Add User Successfully"),
  Add_User_Fail("Fail", "Fail to Add User"),
  Update_User_Success("Success", "Update User Successfully"),
  Update_User_Fail("Fail", "Update User Unsuccessfully"),
  Search_User_Success("Success", " User Found Successfully"),
  Search_User_Fail("Fail", "User Not Found"),
  Delete_User_Success("Success", "Delete User Successfully"),
  Delete_User_Fail("Fail", "Delete User Unsuccessfully"),
  Login_Success("Success", "User Login Successfully"),
  Login_Fail("Fail", "User Login failed");

  private String message;
  private String status;

  private ResponseCode(String message, String status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
