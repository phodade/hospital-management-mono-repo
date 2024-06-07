package org.dnyanyog.user;

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
import org.dnyanyog.dto.DirectoryServiceData;
import org.dnyanyog.dto.DirectoryServiceResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class AddUserScreenController {

  @FXML private Button Logout;

  @FXML private Button Appointments;

  @FXML private Button Cases;

  @FXML private Button Patient;

  @FXML private Button Cancle;

  @FXML private Button Save;

  @FXML private Button DashBoard;

  @FXML private Button Users;

  @FXML private TextField confirm;

  @FXML private TextField email;

  @FXML private TextField mobileNumber;

  @FXML private TextField password;

  @FXML private TextField role;

  @FXML private TextField patient_name_english;

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    new DashBoardScreen().show();
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
  void PatientButton(ActionEvent event) {
    new PatientManagementScreen().show();
  }

  @FXML
  void UserButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void CancleButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void SaveButton(ActionEvent event) {
    DirectoryServiceData addUser = new DirectoryServiceData();

    addUser.setConfirm(confirm.getText());
    addUser.setEmail(email.getText());
    addUser.setmobileNumber(mobileNumber.getText());
    addUser.setPassword(password.getText());
    addUser.setRole(role.getText());
    addUser.setUsername(patient_name_english.getText());

    try {
      DirectoryServiceResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8081/api/v1/directory/add",
              DirectoryServiceResponse.class,
              addUser);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("User Added");
      alert.setContentText("User added successfully!");
      alert.setHeaderText("Success!!");
      alert.show();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();

      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Failed to add user. Please try again.");
      alert.setHeaderText("Failure!!");
      alert.show();
    }
  }
}
