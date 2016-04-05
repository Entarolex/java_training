package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by a.molodkin on 15.03.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test(enabled = false)
  public void testContactDeletion(){
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Aleksey2", "Molodkin2", "EnTaroLex", "+79266856646", "molodkin352@gmail.com"));
      System.out.println("контактов нет, создаю новый");
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlertWindow();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() -1);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
