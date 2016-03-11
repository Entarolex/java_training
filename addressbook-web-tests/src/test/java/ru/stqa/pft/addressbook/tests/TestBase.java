package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by a.molodkin on 10.03.2016.
 */
public class TestBase {


  protected final ApplicationManager app = new ApplicationManager();
  //FirefoxDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }


}
