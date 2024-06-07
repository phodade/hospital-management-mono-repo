package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.CaseServiceMain;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CaseServiceMain.class)
public class CaseControllerTest {

  @Autowired private MockMvc mockMvc;
  @Test
  @Order(1)
  public void verifyDirectoryOperationForCaseSuccess() throws Exception {

    String requestBody =
    		"{\n"
    				+ "  \"patientId\": \"PAT0648A25A\",\n"
    				+ "  \"patientNameEnglish\": \"ridhisha patil\",\n"
    				+ "  \"case_number\": \"100\",\n"
    				+ "  \"examination_date\": \"2024-09-27\",\n"
    				+ "  \"prescription\": \"Paracetamol\",\n"
    				+ "  \"symptoms\": \"cold,fever\"\n"
    				+ "}";


    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/add")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Succee"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case added successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("100"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-09-27"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("Paracetamol"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("cold"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verfiyCaseServiceUpdateCase() throws Exception {
    String case_id = "CASC398FDFC";

    String requestBody =
    		"{\n"
    				+ "  \"patientId\": \"PAT0648A25A\",\n"
    				+ "  \"patientNameEnglish\": \"ridhisha patil\",\n"
    				+ "  \"case_number\": \"100\",\n"
    				+ "  \"examination_date\": \"2024-09-27\",\n"
    				+ "  \"prescription\": \"Paracetamol\",\n"
    				+ "  \"symptoms\": \"cold\"\n"
    				+ "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/" + case_id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case updated successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("100"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-09-27"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("Paracetamol"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("cold"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyCaseServiceForSearch() throws Exception {
    String case_id = "CASC398FDFC";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/patient/" + case_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case found successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("100"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-09-27"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("Paracetamol"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("cold"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyCaseServiceForSearchPatientId() throws Exception {
    String patient_id = "PAT0648A25A";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/" + patient_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case found successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("100"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-09-27"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("Paracetamol"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("cold"))
        .andReturn();
  }

  @Test
  @Order(5)
  public void verifyCaseServiceDelete() throws Exception {

    String case_id = "CASC398FDFC";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/case/" + case_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case deleted successfully !"))
        .andReturn();
  }

  // XML test cases..

  @Test
  @Order(9)
  public void verifyCaseServiceforAddCase() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/add")
            .content(
            		"<request>\r\n"
            				+ "  <patientId>11</patientId>\r\n"
            				+ "  <patientNameEnglish>ridhisha patil</patientNameEnglish>\r\n"
            				+ "  <case_number>100</case_number>\r\n"
            				+ "  <examination_date>2024-09-27</examination_date>\r\n"
            				+ "  <prescription>Paracetamol</prescription>\r\n"
            				+ "  <symptoms>cold,fever</symptoms>\r\n"
            				+ "</request>")

            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case added successfully!"))
        .andReturn();
  }

  @Test
  @Order(10)
  public void verifyCaseServiceforUpdateCaseSuccess() throws Exception {

    String case_id = "CASC398FDFC";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/" + case_id)
            .content(
            		"<request>\r\n"
            				+ "  <patientId>11</patientId>\r\n"
            				+ "  <patientNameEnglish>ridhisha patil</patientNameEnglish>\r\n"
            				+ "  <case_number>100</case_number>\r\n"
            				+ "  <examination_date>2024-09-27</examination_date>\r\n"
            				+ "  <prescription>Paracetamol</prescription>\r\n"
            				+ "  <symptoms>cold</symptoms>\r\n"
            				+ "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string(""))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("100"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("Paracetamol"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("cold"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-09-27"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case updated successfully!"))
        .andReturn();
  }

  @Test
  @Order(11)
  public void verifyCaseServiceforSearchCaseCaseId() throws Exception {

    String case_id = "CASC398FDFC";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/patient/" + case_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string(""))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("100"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("Paracetamol"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("cold"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-09-27"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case found successfully!"))
        .andReturn();
  }

  @Test
  @Order(12)
  public void verifyCaseServiceforSearchCasePatientId() throws Exception {

    String patient_id = "PAT0648A25A";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/" + patient_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string(""))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("100"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("Paracetamol"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("cold"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-09-27"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case found successfully!"))
        .andReturn();
  }

  @Test
  @Order(13)
  public void verifyCaseServiceforDeleteCase() throws Exception {

    String case_id = "CASC398FDFC";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/case/" + case_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string("PAT0648A25A"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ridhisha patil"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("100"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("Paracetamol"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("cold"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-09-27"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case deleted successfully !"))
        .andReturn();
  }
}