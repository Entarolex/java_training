package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by a.molodkin on 15.03.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    if (! app.getContactHelper().isThereAContact()){
      System.out.println("элемент не найден, создаю новый контакт");
      app.getContactHelper().createContact(new ContactData("Aleksey", "Molodkin", "EnTaroLex", "+79266856646", "molodkin352@gmail.com",null),false);
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Aleksey2", "Molodkin2", "EnTaroLex", "+79266856646", "molodkin352@gmail.com","test1"),false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().goToHomePage();


  }
}
