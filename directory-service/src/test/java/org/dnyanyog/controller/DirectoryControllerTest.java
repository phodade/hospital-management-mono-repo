package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.DirectoryServiceMain;
import org.dnyanyog.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Order;
import org.springframework.test.web.servlet.RequestBuilder;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = DirectoryServiceMain.class)
public class DirectoryControllerTest {

  @Autowired MockMvc mockMvc;

  @Test
  @Order(1)
  public void verifyDirectoryOperationForDirectorySuccess() throws Exception {

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/add")
            .content(
            
            "{\r\n"
            + "  \"userName\": \"raniaaglave\",\r\n"
            + "  \"email\": \"rani@gmail.com\",\r\n"
            + "  \"mobileNumber\": \"8877993459\",\r\n"
            + "  \"role\": \"admin\",\r\n"
            + "  \"password\": \"rani\",\r\n"
            + "  \"confirm_password\": \"rani\"\r\n"
            + "}")

            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.Add_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value(ResponseCode.Add_User_Success.getMessage()))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verifyDirectoryOperationForUpdate() throws Exception {

    String user_id = "202";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/" + user_id)
            .content(
            		 "{\r\n"
            		            + "  \"userName\": \"raniaaglave\",\r\n"
            		            + "  \"email\": \"rani@gmail.com\",\r\n"
            		            + "  \"mobileNumber\": \"8877993459\",\r\n"
            		            + "  \"role\": \"admin\",\r\n"
            		            + "  \"password\": \"rani123\",\r\n"
            		            + "  \"confirm_password\": \"rani123\"\r\n"
            		            + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.Update_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value(ResponseCode.Update_User_Success.getMessage()))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyDirectoryOperationForSearch() throws Exception {

    String user_id = "202";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.Search_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value(ResponseCode.Search_User_Success.getMessage()))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyDirectoryOperationForDelete() throws Exception {

    String user_id = "202";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.Delete_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value(ResponseCode.Delete_User_Success.getMessage()))
        .andReturn();
  }

  // XML test cases

  @Test
  @Order(5)
  public void verifyDirectoryServiceforAddUser() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/add")
            .content(
            		"<request>\r\n"
            				+ "  <confirm_password>rani</confirm_password>\r\n"
            				+ "  <password>rani</password>\r\n"
            				+ "  <email>rani@gmail.com</email>\r\n"
            				+ "  <userName>raniaaglave</userName>\r\n"
            				+ "  <role>admin</role>\r\n"
            				+ "  <mobileNumber>8877993459</mobileNumber>\r\n"
            				+ "</request>")

            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.Add_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.Add_User_Success.getMessage()))
        .andReturn();
  }

  @Test
  @Order(6)
  public void verifyDirectoryServiceforUpdateUser() throws Exception {
    String user_id = "202";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/" + user_id)
            .content(
            		"<request>\r\n"
            				+ "  <confirm_password>rani</confirm_password>\r\n"
            				+ "  <password>rani</password>\r\n"
            				+ "  <email>rani@gmail.com</email>\r\n"
            				+ "  <userName>raniaaglave</userName>\r\n"
            				+ "  <role>admin</role>\r\n"
            				+ "  <mobileNumber>8877993450</mobileNumber>\r\n"
            				+ "</request>")

            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.Update_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.Update_User_Success.getMessage()))
        .andReturn();
  }

  @Test
  @Order(7)
  public void verifyDirectoryServiceforSearchUser() throws Exception {
    String user_id = "202";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.Search_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.Search_User_Success.getMessage()))
        .andReturn();
  }

  @Test
  @Order(8)
  public void verifyDirectoryServiceforDeleteUser() throws Exception {
    String user_id = "202";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.Delete_User_Success.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.Delete_User_Success.getMessage()))
        .andReturn();
  }
}