package org.dnyanyog.patientmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dnyanyog.appointmentmanagement.AppointmentManagementScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.delete_patient.DeletePatientScreen;
import org.dnyanyog.edit_patient.EditPatientScreen;
import org.dnyanyog.patient.AddPatientScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class PatientManagementScreenController {

  @FXML private Button SearchPatients;

  @FXML private Button Logout;

  @FXML private Button Appointments;

  @FXML private Button AddPatients;

  @FXML private Button Cases;

  @FXML private Button Patients;

  @FXML private Button DeletePatients;

  @FXML private Button DashBoard;

  @FXML private Button Users;

  @FXML private Button EditPatients;

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void DashBoardButton(ActionEvent event) {
    new DashBoardScreen().show();
  }

  @FXML
  void UserButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void AppointmentButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void PatientsButton(ActionEvent event) {
    new PatientManagementScreen().show();
  }

  @FXML
  void AddpatientButton(ActionEvent event) {
    new AddPatientScreen().show();
  }

  @FXML
  void SearchpatientButton(ActionEvent event) {}

  @FXML
  void DeletepatientButton(ActionEvent event) {
    new DeletePatientScreen().show();
  }

  @FXML
  void EditpatientButton(ActionEvent event) {
    new EditPatientScreen().show();
  }

  @FXML
  void CaseButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }
}
