package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by a.molodkin on 11.03.2016.
 */
public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewUser() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewUserField(ContactData contactData) {

      type(By.name("firstname"),contactData.getFirstName());
      type(By.name("lastname"),contactData.getLastName());
      type(By.name("nickname"),contactData.getNickName());
      type(By.name("mobile"),contactData.getMobileNumber());
      type(By.name("email"),contactData.getUserEmail());
  }

  public void initContactModification() {click(By.xpath("*//tr[2]/td[8]/a"));
  }

  public void submitContactUpdate() {
  click(By.name("update"));
  }

  public void selectContact() {
   click(By.xpath("*//tr[2]/td[1]/input"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("*//form[2]/div[2]/input"));
  }
 }
