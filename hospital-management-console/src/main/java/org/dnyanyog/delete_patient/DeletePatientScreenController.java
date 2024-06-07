package org.dnyanyog.delete_patient;

import java.io.IOException;
import java.time.LocalDate;
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
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class DeletePatientScreenController {

  @FXML private Button Logout;

  @FXML private Button DashBoard;

  @FXML private Button Cases;

  @FXML private Button Appointments;

  @FXML private Button User;

  @FXML private Button Patients;

  @FXML private Button Cancle;

  @FXML private Button Delete;

  @FXML private Button Search;

  @FXML private DatePicker birth_date;

  @FXML private DatePicker first_examination_date;

  @FXML private TextField address;

  @FXML private TextField gender;

  @FXML private TextField mobile_number;

  @FXML private TextField patient_name_english;

  @FXML private TextField patient_name_marathi;

  @FXML private TextField patient_name_english_search;

  @FXML private TextField PatientId_search;

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

  public void SearchButton(ActionEvent event) throws IOException {
    String patientId = PatientId_search.getText().trim();
    String patientNameEnglish = patient_name_english.getText().trim();

    PatientResponse response = null;

    try {
      if (!patientId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8082/api/v1/patient/" + patientId, PatientResponse.class);
      } else if (!patientNameEnglish.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8082/api/v1/patient/name/" + patientNameEnglish,
                PatientResponse.class);
      }

      if (response != null && response.getStatus().equals("Success")) {
        patient_name_english.setText(response.getPatient_name_english());
        patient_name_marathi.setText(response.getPatient_name_marathi());
        mobile_number.setText(response.getMobile_number());
        gender.setText(response.getGender());
        birth_date.setValue(LocalDate.parse(response.getBirth_date()));
        first_examination_date.setValue(LocalDate.parse(response.getFirst_examination_date()));
        address.setText(response.getAddress());
        setFieldsEditable(false);
      } else {
        showAlert("Error", "Patient not found", "Please enter a valid patient ID or Name.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to fetch patient details",
          "An error occurred while fetching the patient details.");
    }
  }

  public void DeleteButton(ActionEvent event) throws IOException {
    String patientId = PatientId_search.getText().trim();

    if (patientId.isEmpty()) {
      showAlert("Error", "Patient ID Required", "Please enter a patient ID to delete.");
      return;
    }

    try {
      PatientResponse response =
          RestUtil.sendDeleteRequest(
              "http://localhost:8082/api/v1/patient/" + patientId, PatientResponse.class);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert("Success", "Patient deleted", "Patient has been deleted successfully.");

        patient_name_english.clear();
        patient_name_english.clear();
        patient_name_marathi.clear();
        mobile_number.clear();
        gender.setText(null);
        birth_date.setValue(null);
        first_examination_date.setValue(null);
        address.clear();
      } else {
        showAlert(
            "Error", "Failed to delete patient", "An error occurred while deleting the patient.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error", "Failed to delete patient", "An error occurred while deleting the patient.");
    }
  }

  private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.show();
  }

  private void setFieldsEditable(boolean editable) {
    patient_name_english.setEditable(editable);
    patient_name_marathi.setEditable(editable);
    mobile_number.setEditable(editable);
    gender.setDisable(!editable);
    birth_date.setEditable(editable);
    first_examination_date.setEditable(editable);
    address.setEditable(editable);
  }
}
