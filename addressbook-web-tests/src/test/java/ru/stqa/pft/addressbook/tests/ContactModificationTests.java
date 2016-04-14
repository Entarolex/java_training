package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by a.molodkin on 15.03.2016.
 */
public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withFirstName("Aleksey3").withLastName("Molodkin3")
              .withHomePhone("555-66-33").withMobilePhone("+79266856646").withWorkPhone("495-200-10-20")
              .withEmail("molodkin352@gmail.com"));
      System.out.println("контактов нет, создаю новый");
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Aleksey3").withLastName("Molodkin3")
            .withHomePhone("555-66-33").withMobilePhone("+79266856646").withWorkPhone("495-200-10-20")
            .withEmail("molodkin352@gmail.com");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }


}
