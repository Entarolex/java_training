package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by a.molodkin on 22.04.2016.
 */
public class AdminHelper extends HelperBase{

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void resetPassword(){
    loginAdmin();
    goToManageUsersPage();
    selectUser();

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

  public void selectUser() {
    List<WebElement> elements = wd.findElements(By.xpath(".//table[3]/tbody/tr[position() > 2]/td[1]/a"));
    System.out.println(elements);
    WebElement chosenUser = elements.stream().filter((e) -> ! e.getText().equals("administrator")).findAny().get();
    chosenUser.click();
  }

  public String getUserEmail(){
    WebElement email = wd.findElement(By.name("email"));
    return email.getAttribute("value");
  }

  public String getUserName(){
    WebElement email = wd.findElement(By.name("username"));
    return email.getAttribute("value");
  }

  public void resertPasswordButtonClick() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void completeResetPassword(String confirmationLink, String newPassword) {
    wd.get(confirmationLink);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.cssSelector("input[value='Update User']"));
  }


}
