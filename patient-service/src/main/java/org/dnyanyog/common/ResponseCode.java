package org.dnyanyog.common;

public enum ResponseCode {
  ADD_PATIENT("Success", "Patient added successfully!!"),
  ADD_PATIENT_FAILED("Fail", "Unable to add patient"),
  UPDATE_PATIENT("Success", "Patient updated successfully!!"),
  UPDATE_PATIENT_FAILED("Fail", "Unable to update patient"),
  SEARCH_PATIENT("Success", "Patient found successfully!!"),
  SEARCH_PATIENT_FAILED("Fail", "Patient not found"),
  DELETE_PATIENT("Success", "Patient deleted successfully!!"),
  DELETE_PATIENT_FAILED("Fail", "Unable to remove patient");

  private final String status;
  private final String message;

  ResponseCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
