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
public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withFirstName("Aleksey2").withLastName("Molodkin2").withNickName("EnTaroLex").withMobileNumber("+79266856646").withUserEmail("molodkin352@gmail.com"));
      System.out.println("контактов нет, создаю новый");
    }
  }
  @Test
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    assertThat(after, equalTo(before.withOut(deletedContact)));

  }


}
