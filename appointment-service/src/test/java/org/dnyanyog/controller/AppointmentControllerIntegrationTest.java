package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.dnyanyog.AppointmentServiceMain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void verifyDirectoryOperationForCaseSuccess() throws Exception {

        String requestBody =
                  "{\n"
                            + "  \"appointmentId\": \"APT59B4A972\",\n"
                            + "  \"patientNameEnglish\": \"revati\",\n"
                            + "  \"appointment_time\": \"1:00\",\n"
                            + "  \"examination_date\": \"2021-08-08\",\n"
                            + "  \"patientId\": \"PATF833FE89\",\n" 
                            + "  \"status\": \"Success\",\n"
                            + "  \"message\": \"Appointment added successfully!\"\n"
                            + "}";

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.post("/api/v1/appointment/add")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Appointment added successfully!"))
            .andReturn();
    }

    @Test
    public void verifyAppointmentServiceForSearchAppointmentId() throws Exception {

        String appointment_id = "APT59B4A972";

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get("/api/v1/appointment/" + appointment_id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Appointment found successfully!"))
            .andReturn();
    }

    @Test
    public void verifyAppointmentServiceForSearchPatientId() throws Exception {

        String patient_id = "PATF833FE89";

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get("/api/v1/appointment/patient/" + patient_id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Appointment found successfully!"))
            .andReturn();
    }

    @Test
    public void verifyAppointmentServiceForDelete() throws Exception {

        String appointment_id = "APT59B4A972";

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.delete("/api/v1/appointment/" + appointment_id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Appointment deleted successfully !"))
            .andReturn();
    }
}
