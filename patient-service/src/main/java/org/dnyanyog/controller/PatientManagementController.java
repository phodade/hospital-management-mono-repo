package org.dnyanyog.controller;

import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.service.PatientManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientManagementController {

  @Autowired PatientManagementServiceImpl patientService;

  @PostMapping(
      path = "/api/v1/patient/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public PatientResponse addUser(@RequestBody PatientRequest patientRequest) throws Exception {
    return patientService.addPatient(patientRequest);
  }

  @PostMapping("/api/v1/patient/{patient_id}")
  public PatientResponse updatePatient(
      @PathVariable long patient_id, @RequestBody PatientRequest request) {
    return patientService.updatePatient(patient_id, request);
  }

  @GetMapping(path = "/api/v1/patient/{patient_id}")
  public PatientResponse searchPatient(@PathVariable long patient_id) {

    return patientService.searchPatient(patient_id);
  }

  @DeleteMapping(path = "/api/v1/patient/{patient_id}")
  public PatientResponse deletePatient(@PathVariable long patient_id) {
    return patientService.deletePatient(patient_id);
  }
}
