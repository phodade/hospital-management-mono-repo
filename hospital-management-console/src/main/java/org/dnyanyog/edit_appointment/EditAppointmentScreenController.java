package org.dnyanyog.edit_appointment;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.dnyanyog.appointmentmanagement.AppointmentManagementScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.common.RestUtil;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class EditAppointmentScreenController {

  @FXML private TextField appointmentTime;

  @FXML private TextField examinationDate;

  @FXML private TextField AppointmentId;

  @FXML private TextField patientNameMarathi;

  @FXML private TextField patientNameEnglish;

  @FXML private TextField AppointmentId_search;

  @FXML private TextField PatientId_search;

  @FXML private Button Search;

  @FXML private Button Save;

  @FXML private Button Cancle;

  @FXML private Button DashBoard;

  @FXML private Button Cases;

  @FXML private Button Appointments;

  @FXML private Button Users;

  @FXML private Button Patients;

  @FXML private Button Logout;

  @FXML
  void CancleButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void PatientsButton(ActionEvent event) {
    new PatientManagementScreen().show();
  }

  @FXML
  void CasesButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  @FXML
  void AppointmentsButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void UsersButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  public void SearchButton(ActionEvent event) throws IOException {
    String appointmentId = AppointmentId_search.getText().trim();
    String patientId = PatientId_search.getText().trim();

    AppointmentResponse response = null;
    try {
      if (!patientId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8084/api/v1/appointment/patient/" + patientId,
                AppointmentResponse.class);
      } else if (!appointmentId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8084/api/v1/appointment/" + appointmentId,
                AppointmentResponse.class);
      }
      if (response != null && response.getStatus().equals("Success")) {
        patientNameEnglish.setText(response.getPatientNameEnglish());
        PatientId_search.setText(response.getPatientId());
        AppointmentId_search.setText(response.getAppointmentId());
        examinationDate.setText(response.getExamination_date());
        appointmentTime.setText(response.getAppointment_time());

      } else {
        showAlert(
            "Error", "Appointment not found", "Please enter valid patient Id or appointment Id");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to fetch appointment details",
          "An error occurred while fetching the appointment details.");
    }
  }

  public void SaveButton(ActionEvent event) throws IOException {
    AppointmentRequest updateAppointment = new AppointmentRequest();
    updateAppointment.setAppointment_time(appointmentTime.getText());
    updateAppointment.setExamination_date(examinationDate.getText());
    updateAppointment.setPatientId(PatientId_search.getText());
    updateAppointment.setPatientNameEnglish(patientNameEnglish.getText());
    updateAppointment.setAppointmentId(AppointmentId_search.getText());

    String appointmentId = AppointmentId_search.getText();

    try {
      AppointmentResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8084/api/v1/appointment/" + appointmentId,
              AppointmentResponse.class,
              updateAppointment);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert(
            "Success",
            "Appointment updated",
            "Appointment details have been updated successfully.");
      } else {
        showAlert(
            "Error",
            "Failed to Appointment case",
            "An error occurred while updating the Appointment details.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to update Appointment",
          "An error occurred while updating the Appointment details.");
    }
  }

  private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.show();
  }
}
