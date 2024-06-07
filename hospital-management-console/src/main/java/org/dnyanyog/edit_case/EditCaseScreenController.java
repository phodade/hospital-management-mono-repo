package org.dnyanyog.edit_case;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.dnyanyog.appointmentmanagement.AppointmentManagementScreen;
import org.dnyanyog.casemanagement.CaseManagementScreen;
import org.dnyanyog.common.RestUtil;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.patientmanagement.PatientManagementScreen;
import org.dnyanyog.usermanagement.UserManagementScreen;

public class EditCaseScreenController {

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

      } else {
        showAlert("Error", "Casen not found", "Please enter valid patient Id or case Id");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to fetch case details",
          "An error occurred while fetching the case details.");
    }
  }

  public void SaveButton(ActionEvent event) throws IOException {
    CaseRequest updateCase = new CaseRequest();
    updateCase.setCase_number(caseNumber.getText());
    updateCase.setExamination_date(examinationDate.getText());
    updateCase.setPatientId(patientId.getText());
    updateCase.setPatientNameEnglish(patientName.getText());
    updateCase.setPrescription(prescription.getText());
    updateCase.setSymptoms(symptoms.getText());

    String caseId = Case_id_search.getText();

    try {
      CaseResponse response =
          RestUtil.sendPostRequest(
              "http://localhost:8083/api/v1/case/" + caseId, CaseResponse.class, updateCase);

      if (response != null && response.getStatus().equals("Success")) {
        showAlert("Success", "Case updated", "Case details have been updated successfully.");
      } else {
        showAlert(
            "Error", "Failed to update case", "An error occurred while updating the case details.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      showAlert(
          "Error",
          "Failed to update patient",
          "An error occurred while updating the case details.");
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
