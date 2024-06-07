package org.dnyanyog.hospital_management_main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.dnyanyog.common.StageFactory;
import org.dnyanyog.login.LoginScreen;

public class ApplicationMain extends Application {

  public static void main(String args[]) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {

    StageFactory.stage = stage;
    new LoginScreen().show();
  }
}
