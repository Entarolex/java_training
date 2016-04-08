package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by a.molodkin on 07.04.2016.
 */
public class ContactPhoneTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withFirstName("Aleksey3").withLastName("Molodkin3").withNickName("EnTaroLex").withHomePhone("555-66-33").withMobilePhone("+79266856646").withWorkPhone("495-200-10-20").withUserEmail("molodkin352@gmail.com"));
      System.out.println("контактов нет, создаю новый");
    }
  }
  @Test
  public void testContactPhones(){
    app.goTo().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }
  public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
