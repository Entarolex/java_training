package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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

  public void fillContactForm(ContactData contactData, boolean creation) {

    // File photo = new File("src/test/resources/stru.png");
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    attach(By.name("photo"), contactData.getPhoto());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }


  }

  public void create(ContactData contact) {
    goToAddNewUserPage();
    fillContactForm(contact, true);
    submitNewUser();
    contactCache = null;
    goToHomePage();
  }

  public void modify(ContactData contact) {
    goToHomePage();
    initContactModificationByid(contact.getId());
    fillContactForm(contact, false);
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
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactDetailsInfoByid(int id) {
    wd.findElement(By.xpath("//a[@href=\"view.php?id=" + id + "\"]/img")).click();
  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    initContactDetailsInfoByid(contact.getId());
    String allDetails = wd.findElement(By.xpath("//div[4][text()]")).getText().replaceAll("\n\n", "\n").toString();
    List<String> strings = new ArrayList<>(Arrays.asList(allDetails.split("\n")));

    String firstname = strings.get(0).split(" ")[0];
    String lastname = strings.get(0).split(" ")[1];
    String address = strings.get(1);
    String home = strings.get(2).split(" ")[1];
    String mobile = strings.get(3).split(" ")[1];
    String work = strings.get(4).split(" ")[1];
    String email = strings.get(5).split(" ")[0];
    String email2 = strings.get(6).split(" ")[0];
    String email3 = strings.get(7).split(" ")[0];

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);

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
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));

    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmail = cells.get(4).getText();
      String allDetails = (cells.get(2).getText() + cells.get(1).getText() + cells.get(3).getText() + cells.get(5).getText() + cells.get(4).getText());
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAddress(address)
              .withAllPhones(allPhones)
              .withAllEmail(allEmail)
              .withAllDetails(allDetails));
    }
    return new Contacts(contactCache);
  }


}
