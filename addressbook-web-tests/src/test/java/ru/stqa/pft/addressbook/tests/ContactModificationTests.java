package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by a.molodkin on 15.03.2016.
 */
public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("Aleksey2", "Molodkin2", "EnTaroLex", "+79266856646", "molodkin352@gmail.com"));
      System.out.println("контактов нет, создаю новый");
    }
  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = (before.size() - 1);
    ContactData contact = new ContactData(before.get(index).getId(),"Aleksey2", "Molodkin2", "EnTaroLex", "+79266856646", "molodkin352@gmail.com");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }


}
