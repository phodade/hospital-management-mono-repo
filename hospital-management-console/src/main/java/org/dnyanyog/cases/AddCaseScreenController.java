package org.dnyanyog.cases;

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
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class AddCaseScreenController {

  @FXML private Button Logout;

  @FXML private Button Cases;

  @FXML private Button Cancle;

  @FXML private Button Patients;

  @FXML private Button Save;

  @FXML private Button Appointment;

  @FXML private Button DashBoard;

  @FXML private Button Users;

  @FXML private TextField caseNumber;

  @FXML private TextField patientId;

  @FXML private TextField patientName;

  @FXML private TextField prescription;

  @FXML private TextField symptoms;

  @FXML private TextField examinationDate;

  @FXML
  void LogoutButton(ActionEvent event) {
    new DashBoardScreen().show();
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
  void AppointmentButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  void UsersButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  void CasesButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  @FXML
  void CancleButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  @FXML
  void SaveButton(ActionEvent event) {
    CaseRequest addCase = new CaseRequest();

    addCase.setCase_number(caseNumber.getText());
    addCase.setExamination_date(examinationDate.getText());
    addCase.setPatientId(patientId.getText());
    addCase.setPatientNameEnglish(patientName.getText());
    addCase.setPrescription(prescription.getText());
    addCase.setSymptoms(symptoms.getText());

    try {
      CaseResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8083/api/v1/case/add", CaseResponse.class, addCase);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Case Added");
      alert.setContentText("Case added!");
      alert.setHeaderText("Success!!");
      alert.show();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
