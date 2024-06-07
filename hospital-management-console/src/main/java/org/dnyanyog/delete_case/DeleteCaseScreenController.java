package org.dnyanyog.delete_case;

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
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class DeleteCaseScreenController {

  @FXML private Button Logout;

  @FXML private Button DashBoard;

  @FXML private Button Patients;

  @FXML private Button Appointment;

  @FXML private Button Users;

  @FXML private Button Cases;

  @FXML private Button Cancle;

  @FXML private Button Delete;

  @FXML private Button Search;

  @FXML private TextField symptoms;

  @FXML private TextField examinationDate;

  @FXML private TextField caseNumber;

  @FXML private TextField patientId;

  @FXML private TextField patientName;

  @FXML private TextField prescription;

  @FXML private TextField Case_id_search;

  @FXML private TextField Patient_id_search;

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
  private void AppointmentButton(ActionEvent event) {
    new AppointmentManagementScreen().show();
  }

  @FXML
  private void UsersButton(ActionEvent event) {
    new UserManagementScreen().show();
  }

  @FXML
  private void CasesButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  @FXML
  private void CancleButton(ActionEvent event) {
    new CaseManagementScreen().show();
  }

  public void SearchButton(ActionEvent event) throws IOException {
    String patientId = Patient_id_search.getText().trim();
    String caseId = Case_id_search.getText().trim();

    CaseResponse response = null;
    try {
      if (!patientId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8083/api/v1/case/" + patientId, CaseResponse.class);
      } else if (!caseId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8083/api/v1/case/patient/" + caseId, CaseResponse.class);
      }
      if (response != null && response.getStatus().equals("Success")) {
        patientName.setText(response.getPatientNameEnglish());
        Patient_id_search.setText(response.getPatientId());
        caseNumber.setText(response.getCase_number());
        examinationDate.setText(response.getExamination_date());
        symptoms.setText(response.getSymptoms());
        prescription.setText(response.getPrescription());
        setFieldsEditable(false);
      } else {
        showAlert("Error", "Case not found", "Please enter valid patient Id or case Id");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to fetch case details",
          "An error occurred while fetching the case details.");
    }
  }

  public void DeleteButton(ActionEvent event) throws IOException {
    String caseId = Case_id_search.getText().trim();

    if (caseId.isEmpty()) {
      showAlert("Error", "Case ID Required", "Please enter a case ID to delete.");
      return;
    }

    try {
      CaseResponse response =
          RestUtil.sendDeleteRequest(
              "http://localhost:8083/api/v1/case/" + caseId, CaseResponse.class);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert("Success", "Case deleted", "Case has been deleted successfully.");
        Patient_id_search.clear();
        Case_id_search.clear();
        patientName.clear();
        patientId.clear();
        caseNumber.clear();
        examinationDate.clear();
        prescription.clear();
        symptoms.clear();
      } else {
        showAlert("Error", "Failed to delete case", "An error occurred while deleting the case.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert("Error", "Failed to delete case", "An error occurred while deleting the case.");
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
    patientName.setEditable(editable);
    patientId.setEditable(editable);
    caseNumber.setEditable(editable);
    examinationDate.setDisable(!editable);
    symptoms.setEditable(editable);
    prescription.setEditable(editable);
  }
}
