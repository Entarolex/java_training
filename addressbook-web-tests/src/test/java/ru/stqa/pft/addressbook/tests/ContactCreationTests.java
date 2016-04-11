package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.contact().goToAddNewUserPage();
        ContactData contact = new ContactData().withFirstName("Aleksey").withLastName("Molodkin").withNickName("EnTaroLex").withHomePhone("555-66-33").withMobilePhone("+79266856646").withWorkPhone("495-200-10-20").withEmail("molodkin352@gmail.com");
        app.contact().create(contact);
        assertThat(app.contact().count(),equalTo(before.size() +1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

 }
