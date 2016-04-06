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
        ContactData contact = new ContactData().withFirstName("Aleksey").withLastName("Molodkin").withNickName("EnTaroLex").withMobileNumber("+79266856646").withUserEmail("molodkin352@gmail.com");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(),equalTo(before.size() +1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

 }
