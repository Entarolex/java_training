package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by a.molodkin on 15.03.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    if (! app.getContactHelper().isThereAContact()){
      System.out.println("элемент не найден, создаю новый контакт");
      app.getContactHelper().createContact(new ContactData("Aleksey", "Molodkin", "EnTaroLex", "+79266856646", "molodkin352@gmail.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Aleksey2", "Molodkin2", "EnTaroLex", "+79266856646", "molodkin352@gmail.com");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1 );
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
