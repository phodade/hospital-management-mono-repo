package org.dnyanyog.login;

public class MainClass {

  public static void main(String[] args) {

    MainClass m = new MainClass();
    m.printData();
  }

  private void printData() {

    String myClassName = getClass().getSimpleName() + ".class";

    System.out.println(myClassName);
  }
}
