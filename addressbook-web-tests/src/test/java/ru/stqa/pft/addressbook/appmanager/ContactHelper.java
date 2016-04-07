package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by a.molodkin on 11.03.2016.
 */
public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  private Contacts contactCache = null;

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

  public void modify(ContactData contact) {
    goToHomePage();
    initContactModificationByid(contact.getId());
    fillContactForm(contact);
    submitContactUpdate();
    goToHomePage();
  }

  public void delete(ContactData contact) {
    goToHomePage();
    selectContactById(contact.getId());
    deleteSelectedContact();
    closeAlertWindow();
    goToHomePage();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
  }

  public void initContactModificationByid(int id) {
    wd.findElement(By.xpath("//a[@href=\"edit.php?id=" + id + "\"]")).click();
    //wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  public void create(ContactData contact) {
    goToAddNewUserPage();
    fillContactForm(contact);
    submitNewUser();
    goToHomePage();
  }

  private void goToHomePage() {
    if (isElementPresent(By.xpath("//input[@value=\"Send e-Mail\"]"))) {
      return;
    }
    click(By.linkText("home"));

  }

  public int getContactCounter() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> fio = wd.findElements(By.name("selected[]"));

    for (WebElement element : fio) {
      //String firstname = String.format(element.getAttribute("title"),("Select (%s %s)")).toString().split(" ")[1].replace("(","");
      String firstname = element.getAttribute("title").toString().split(" ")[1].replace("(", "");
      String lastname = element.getAttribute("title").toString().split(" ")[2].replace(")", "");
      int id = Integer.parseInt(element.getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
    }
    return new Contacts(contactCache);
  }


}
