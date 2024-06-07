package org.dnyanyog.patient;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.dnyanyog.appointmentmanagement.AppointmentManagementScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.common.RestUtil;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class AddPatientScreenController {

  @FXML private Button Logout;

  @FXML private Button Appointments;

  @FXML private Button Cases;

  @FXML private Button Cancle;

  @FXML private Button Patients;

  @FXML private Button Save;

  @FXML private Button User;

  @FXML private Button DashBoard;

  @FXML private TextField address;

  @FXML private TextField mobile_number;

  @FXML private TextField patient_name_marathi;

  @FXML private TextField patient_name_english;

  @FXML private DatePicker birth_date;

  @FXML private DatePicker first_examination_date;

  @FXML private TextField gender;

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void CaseButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  @FXML
  void AppointmentsButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void UserButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void PatientButton(ActionEvent event) {
    new PatientManagementScreen().show();
  }

  @FXML
  void CancleButton(ActionEvent event) {
    new PatientManagementScreen().show();
  }

  @FXML
  void SaveButton(ActionEvent event) throws IOException {
    PatientRequest addPatient = new PatientRequest();

    addPatient.setAddress(address.getText());
    addPatient.setMobile_number(mobile_number.getText());
    addPatient.setPatient_name_marathi(patient_name_marathi.getText());
    addPatient.setPatient_name_english(patient_name_english.getText());

    if (birth_date.getValue() != null) {
      addPatient.setBirth_date(birth_date.getValue().toString());
    }

    if (first_examination_date.getValue() != null) {
      addPatient.setFirst_examination_date(first_examination_date.getValue().toString());
    }

    try {
      PatientResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8082/api/v1/patient/add", PatientResponse.class, addPatient);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Patient Added");
    alert.setContentText("Patient added!");
    alert.setHeaderText("Success!!");
    alert.show();
  }
}
