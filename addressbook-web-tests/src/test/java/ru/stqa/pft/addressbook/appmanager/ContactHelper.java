package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getUserEmail());
  }

  public void create(ContactData contact) {
    goToAddNewUserPage();
    fillContactForm(contact);
    submitNewUser();
    contactCache = null;
    goToHomePage();
  }

  public void modify(ContactData contact) {
    goToHomePage();
    initContactModificationByid(contact.getId());
    fillContactForm(contact);
    submitContactUpdate();
    contactCache = null;
    goToHomePage();
  }

  public void delete(ContactData contact) {
    goToHomePage();
    selectContactById(contact.getId());
    deleteSelectedContact();
    closeAlertWindow();
    contactCache = null;
    goToHomePage();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
  }

  public void initContactModificationByid(int id) {
    wd.findElement(By.xpath("//a[@href=\"edit.php?id=" + id + "\"]")).click();
    //wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }
  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationByid(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

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


  private void goToHomePage() {
    if (isElementPresent(By.xpath("//input[@value=\"Send e-Mail\"]"))) {
      return;
    }
    click(By.linkText("home"));

  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));

    for (WebElement row : rows) {
     List<WebElement> cells = row.findElements(By.tagName("td"));
     int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return new Contacts(contactCache);
  }



}
