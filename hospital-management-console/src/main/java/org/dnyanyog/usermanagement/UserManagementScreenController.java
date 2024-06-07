package org.dnyanyog.usermanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dnyanyog.appointmentmanagement.AppointmentManagementScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.user.AddUserScreen;

public class UserManagementScreenController {

  @FXML private Button Logout;

  @FXML private Button Appointments;

  @FXML private Button Cases;

  @FXML private Button Adduser;

  @FXML private Button Searchuser;

  @FXML private Button Patients;

  @FXML private Button Edituser;

  @FXML private Button Deleteuser;

  @FXML private Button DashBoard;

  @FXML private Button Users;

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void PatientButton(ActionEvent event) {
    new PatientManagementScreen().show();
  }

  @FXML
  void CaseButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  @FXML
  void AppointmentButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void UserButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void AddUserButton(ActionEvent event) {
    new AddUserScreen().show();
  }

  @FXML
  void SearchUserButton(ActionEvent event) {}

  @FXML
  void DeleteUserButton(ActionEvent event) {}

  @FXML
  void EditUserButton(ActionEvent event) {}
}
