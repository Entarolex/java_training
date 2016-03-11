package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToAddNewUserPage();
        app.getContactHelper().fillNewUserField(new ContactData("Aleksey", "Molodkin", "EnTaroLex", "+79266856646", "molodkin352@gmail.com"));
        app.getContactHelper().submitNewUser();
    }

 }
