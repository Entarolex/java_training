package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        app.contact().goToAddNewUserPage();
        ContactData contact = new ContactData().withFirstName("Aleksey").withLastName("Molodkin").withNickName("EnTaroLex").withMobileNumber("+79266856646").withUserEmail("molodkin352@gmail.com");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() +1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before,after);
    }

 }
