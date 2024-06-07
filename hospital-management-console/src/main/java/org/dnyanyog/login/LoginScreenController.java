package org.dnyanyog.login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.dnyanyog.dashboard.DashBoardScreen;
import org.dnyanyog.db_operations.DBUtils;

public class LoginScreenController {
  @FXML private TextField password;

  @FXML private Button Next;

  @FXML private TextField mobile_number;

  @FXML
  void NextButton(ActionEvent event) {

    boolean loginStatus = false;
    try {
      loginStatus =
          LoginScreenController.validateUserAndPassword(
              mobile_number.getText(), password.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (loginStatus) {
      new DashBoardScreen().show();
    } else {
      System.out.println("Login Unsuccessful");
    }
  }

  public static boolean validateUserAndPassword(String mobile_number, String password)
      throws IOException {

    String query =
        "select * from directory where mobile_number ='"
            + mobile_number
            + "' and password ='"
            + password
            + "' ";

    ResultSet rs = DBUtils.executeQueryGetResult(query);

    try {
      if (rs.next()) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;
  }
}
