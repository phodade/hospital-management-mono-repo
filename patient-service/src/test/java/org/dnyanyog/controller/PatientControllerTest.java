package org.dnyanyog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dnyanyog.dto.PatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.service.PatientManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @Mock
    private PatientManagementServiceImpl patientService;

    @InjectMocks
    private PatientManagementController patientController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddPatient() throws Exception {
        PatientRequest request = new PatientRequest();
        request.setAddress("123 Street");
        request.setBirth_date("2023-06-01");
        request.setFirst_examination_date("2023-06-01");
        request.setGender("Female");
        request.setMobile_number("1234567890");
        request.setPatient_name_english("John Doe");
        request.setPatient_name_marathi("जॉन डो");

        PatientResponse response = new PatientResponse();
        response.setAddress(request.getAddress());
        response.setBirth_date(request.getBirth_date());
        response.setFirst_examination_date(request.getFirst_examination_date());
        response.setGender(request.getGender());
        response.setMobile_number(request.getMobile_number());
        response.setPatient_name_english(request.getPatient_name_english());
        response.setPatient_name_marathi(request.getPatient_name_marathi());
        response.setStatus("Success");
        response.setMessage("Patient added successfully");

        when(patientService.addPatient(any(PatientRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.message").value("Patient added successfully"))
                .andExpect(jsonPath("$.patient_name_english").value(request.getPatient_name_english()));
    }

    @Test
    public void testUpdatePatient() throws Exception {
        long patientId = 1L;
        PatientRequest request = new PatientRequest();
        request.setAddress("456 Street");
        request.setBirth_date("2023-06-01");
        request.setFirst_examination_date("2023-06-01");
        request.setGender("Male");
        request.setMobile_number("0987654321");
        request.setPatient_name_english("Jane Doe");
        request.setPatient_name_marathi("जेन डो");

        PatientResponse response = new PatientResponse();
        response.setAddress(request.getAddress());
        response.setBirth_date(request.getBirth_date());
        response.setFirst_examination_date(request.getFirst_examination_date());
        response.setGender(request.getGender());
        response.setMobile_number(request.getMobile_number());
        response.setPatient_name_english(request.getPatient_name_english());
        response.setPatient_name_marathi(request.getPatient_name_marathi());
        response.setStatus("200");
        response.setMessage("Patient updated successfully");

        when(patientService.updatePatient(anyLong(), any(PatientRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/patient/" + patientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.message").value("Patient updated successfully"))
                .andExpect(jsonPath("$.patient_name_english").value(request.getPatient_name_english()));
    }

    @Test
    public void testSearchPatient() throws Exception {
        long patientId = 1L;

        PatientResponse response = new PatientResponse();
        response.setAddress("123 Street");
        response.setBirth_date("2023-06-01");
        response.setFirst_examination_date("2023-06-01");
        response.setGender("Female");
        response.setMobile_number("1234567890");
        response.setPatient_name_english("John Doe");
        response.setPatient_name_marathi("जॉन डो");
        response.setStatus("200");
        response.setMessage("Patient found");

        when(patientService.searchPatient(patientId)).thenReturn(response);

        mockMvc.perform(get("/api/v1/patient/" + patientId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.message").value("Patient found"))
                .andExpect(jsonPath("$.patient_name_english").value(response.getPatient_name_english()));
    }

    @Test
    public void testDeletePatient() throws Exception {
        long patientId = 1L;

        PatientResponse response = new PatientResponse();
        response.setStatus("Success");
        response.setMessage("Patient deleted successfully");

        when(patientService.deletePatient(patientId)).thenReturn(response);

        mockMvc.perform(delete("/api/v1/patient/" + patientId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.message").value("Patient deleted successfully"));
    }
}
