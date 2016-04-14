package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by a.molodkin on 11.04.2016.
 */
public class ContactEmailTests extends TestBase{
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
  public void testContactEmail(){
    app.goTo().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEdinForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmail(), equalTo(mergeEmails(contactInfoFromEdinForm)));
  }

  private String mergeEmails(ContactData contact){
   return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email){
    return email.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
