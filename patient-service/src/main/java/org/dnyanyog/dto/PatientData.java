package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class PatientData {

  @NotNull(message = "Username is mandatory")
  @NotBlank(message = "Username should not be blank")
  @Size(max = 50, message = "Username length should be at most 50 characters")
  private String patient_name_english;

  @NotNull(message = "Username in Marathi is mandatory")
  @NotBlank(message = "Username in Marathi should not be blank")
  @Size(max = 50, message = "Username in Marathi length should be at most 50 characters")
  @Pattern(
      regexp = "\"([अ-ज्ञ]\"",
      message = "Username in Marathi should only contain Marathi characters")
  private String patient_name_marathi;

  @NotNull(message = "Mobile_Number is mandatory")
  @Pattern(
      regexp = "^\\d{10}$",
      message = "Invalid mobile number format. It should be a 10-digit number.")
  private String mobile_number;

  @NotNull(message = "Gender is mandatory")
  @NotBlank(message = "Gender should not be blank")
  @Pattern(
      regexp = "^(Male|Female|Other)$",
      message = "Gender must be one of the following: Male, Female, Other")
  private String gender;

  @NotNull(message = "Birth_Date is mandatory")
  @PastOrPresent(message = "Birth date cannot be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String birth_date;

  @NotNull(message = "First_Examination date is mandatory")
  @PastOrPresent(message = "First_examination date cannot be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String first_examination_date;

  @NotNull(message = "Address is mandatory")
  @NotBlank(message = "Address should not be blank")
  @Size(max = 50, message = "Address length should be at most 50 characters")
  private String address;

  public String getPatient_name_english() {
    return patient_name_english;
  }

  public void setPatient_name_english(String patient_name_english) {
    this.patient_name_english = patient_name_english;
  }

  public String getPatient_name_marathi() {
    return patient_name_marathi;
  }

  public void setPatient_name_marathi(String patient_name_marathi) {
    this.patient_name_marathi = patient_name_marathi;
  }

  public String getMobile_number() {
    return mobile_number;
  }

  public void setMobile_number(String mobile_number) {
    this.mobile_number = mobile_number;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirth_date() {
    return birth_date;
  }

  public void setBirth_date(String birth_date) {
    this.birth_date = birth_date;
  }

  public String getFirst_examination_date() {
    return first_examination_date;
  }

  public void setFirst_examination_date(String first_examination_date) {
    this.first_examination_date = first_examination_date;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
