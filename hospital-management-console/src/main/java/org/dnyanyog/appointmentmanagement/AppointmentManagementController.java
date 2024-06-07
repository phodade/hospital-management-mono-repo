package org.dnyanyog.appointmentmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dnyanyog.appointment.AddAppointmentScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.delete_appointment.DeleteAppointmentScreen;
import org.dnyanyog.edit_appointment.EditAppointmentScreen;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class AppointmentManagementController {

  @FXML private Button Editappointment;

  @FXML private Button Logout;

  @FXML private Button Appointments;

  @FXML private Button Cases;

  @FXML private Button Patient;

  @FXML private Button DeleteAppointment;

  @FXML private Button SearchAppointment;

  @FXML private Button DashBoard;

  @FXML private Button Users;

  @FXML private Button AddAppointment;

  @FXML
  void DashBoardButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void PatientButton(ActionEvent event) {
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
  void AddAppointmentButton(ActionEvent event) {
    new AddAppointmentScreen().show();
  }

  @FXML
  void SearchAppointmentButton(ActionEvent event) {}

  @FXML
  void DeleteAppointmentButton(ActionEvent event) {
    new DeleteAppointmentScreen().show();
  }

  @FXML
  void EditAppointmentButton(ActionEvent event) {
    new EditAppointmentScreen().show();
  }

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }
}
