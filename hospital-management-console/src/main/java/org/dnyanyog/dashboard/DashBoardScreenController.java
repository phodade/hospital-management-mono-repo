package org.dnyanyog.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dnyanyog.appointmentmanagement.AppointmentManagementScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.login.LoginScreen;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class DashBoardScreenController {

  @FXML private Button Appointments;

  @FXML private Button Logout;

  @FXML private Button Users;

  @FXML private Button Patients;

  @FXML private Button DashBoard;

  @FXML private Button Cases;

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
  void LogOutButton(ActionEvent event) {
    new LoginScreen().show();
  }
}
