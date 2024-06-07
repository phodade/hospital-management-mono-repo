package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginResponse {
  private String status;
  private String message;

  public String getStatus() {
    return status;
  }

  public LoginResponse setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public LoginResponse setMessage(String message) {
    this.message = message;
    return this;
  }

  public LoginResponse getInstance() {
    return new LoginResponse();
  }
}
