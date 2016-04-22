package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by a.molodkin on 22.04.2016.
 */
public class ResetPasswordHelper extends HelperBase{

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAdmin() {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");
    type(By.name("username"),("administrator"));
    type(By.name("password"),("root"));
    click(By.cssSelector("input[value='Login']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
  public void goToManageUsersPage() {
    click(By.xpath("//a[text()='Manage']"));
    click(By.xpath("//a[text()='Manage Users']"));
  }
}
