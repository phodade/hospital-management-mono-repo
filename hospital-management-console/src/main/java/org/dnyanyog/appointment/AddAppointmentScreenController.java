package org.dnyanyog.appointment;

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

public class AddAppointmentScreenController {

  @FXML private TextField patient_name_english;

  @FXML private TextField patient_id;

  @FXML private TextField appointment_id;

  @FXML private TextField examination_date;

  @FXML private TextField appointment_time;

  @FXML private Button Logout;

  @FXML private Button Appointments;

  @FXML private Button Cases;

  @FXML private Button Cancle;

  @FXML private Button Patients;

  @FXML private Button Save;

  @FXML private Button DashBoard;

  @FXML private Button Users;

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
  void UsersButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void AppointmentsButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void CancleButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  public void SaveButton(ActionEvent event) {
    AppointmentRequest addAppointment = new AppointmentRequest();

    addAppointment.setAppointment_time(appointment_time.getText());
    addAppointment.setAppointmentId(appointment_id.getText());
    addAppointment.setExamination_date(examination_date.getText());
    addAppointment.setPatientNameEnglish(patient_name_english.getText());

    try {

      AppointmentResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8084/api/v1/appointment/add",
              AppointmentResponse.class,
              addAppointment);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Appointment Added");
      alert.setContentText("Appointment added!");
      alert.setHeaderText("Success!!");
      alert.show();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  private void showErrorAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
  }
}
