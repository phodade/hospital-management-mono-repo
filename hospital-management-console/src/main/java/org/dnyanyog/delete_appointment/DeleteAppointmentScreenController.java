package org.dnyanyog.delete_appointment;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.dnyanyog.appointmentmanagement.AppointmentManagementScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.common.RestUtil;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class DeleteAppointmentScreenController {

  @FXML private TextField appointmentTime;

  @FXML private TextField examinationDate;

  @FXML private TextField AppointmentId;

  @FXML private TextField patientNameMarathi;

  @FXML private TextField patientNameEnglish;

  @FXML private TextField AppointmentId_search;

  @FXML private TextField PatientId_search;

  @FXML
  private void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  private void DashBoardButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  private void PatientsButton(ActionEvent event) {
    new PatientManagementScreen().show();
  }

  @FXML
  private void CasesButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  @FXML
  private void UsersButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  private void AppointmentsButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  private void CancleButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
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
        AppointmentId.setText(response.getAppointmentId());
        examinationDate.setText(response.getExamination_date());
        appointmentTime.setText(response.getAppointment_time());
        setFieldsEditable(false);

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

  public void DeleteButton(ActionEvent event) throws IOException {
    String appointmentId = AppointmentId_search.getText().trim();

    if (appointmentId.isEmpty()) {
      showAlert("Error", "Appointment Id Required", "Please enter a appointment Id to delete.");
      return;
    }

    try {
      AppointmentResponse response =
          RestUtil.sendDeleteRequest(
              "http://localhost:8084/api/v1/appointment/" + appointmentId,
              AppointmentResponse.class);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert("Success", "appointment deleted", "appointment has been deleted successfully.");
        PatientId_search.clear();
        AppointmentId.clear();
        patientNameEnglish.clear();
        AppointmentId.clear();
        examinationDate.clear();
        AppointmentId_search.clear();
        appointmentTime.clear();

      } else {
        showAlert(
            "Error",
            "Failed to delete appointment",
            "An error occurred while deleting the appointment.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to delete appointment",
          "An error occurred while deleting the appointment.");
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
    patientNameEnglish.setEditable(editable);
    AppointmentId.setEditable(editable);
    examinationDate.setDisable(!editable);
    AppointmentId.setEditable(editable);
    appointmentTime.setEditable(editable);
  }
}
