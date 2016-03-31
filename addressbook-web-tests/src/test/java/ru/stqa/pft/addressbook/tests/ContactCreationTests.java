package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {
        int before = app.getContactHelper().getContactCounter();
        app.getContactHelper().goToAddNewUserPage();
        app.getContactHelper().createContact(new ContactData("Aleksey", "Molodkin", "EnTaroLex", "+79266856646", "molodkin352@gmail.com","test1"));
        int after = app.getContactHelper().getContactCounter();
        Assert.assertEquals(after, before +1);
    }

 }
