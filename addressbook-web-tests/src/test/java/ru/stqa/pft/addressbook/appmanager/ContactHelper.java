package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.molodkin on 11.03.2016.
 */
public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewUser() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {

    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("mobile"), contactData.getMobileNumber());
    type(By.name("email"), contactData.getUserEmail());

  }


  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("*//tr[2]/td[8]/a"));
  }
  public void goToAddNewUserPage() {
    if (isElementPresent(By.tagName("Edit / add address book entry"))) {
      return;
    }
    click(By.linkText("add new"));
  }
  public void createContact(ContactData contact) {
    goToAddNewUserPage();
    fillContactForm(contact);
    submitNewUser();
    returnToContactListPage();
  }

  private void returnToContactListPage() {
    if (isElementPresent(By.xpath("//input[@value=\"Send e-Mail\"]"))) {
      return;
    }
    click(By.linkText("home"));

  }

  public int getContactCounter() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> fio = wd.findElements(By.name("selected[]"));

      for (WebElement element : fio) {
        //String firstname = String.format(element.getAttribute("title"),("Select (%s %s)")).toString().split(" ")[1].replace("(","");
        String firstname = element.getAttribute("title").toString().split(" ")[1].replace("(","");
        String lastname = element.getAttribute("title").toString().split(" ")[2].replace(")","");
        int id =Integer.parseInt(element.getAttribute("value"));
        ContactData contact = new ContactData(id,firstname, lastname, null, null, null);
        contacts.add(contact);
      }
     return contacts;
  }
}
