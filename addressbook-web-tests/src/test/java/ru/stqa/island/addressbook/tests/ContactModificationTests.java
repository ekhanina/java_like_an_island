package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testsContactModification() {
        app.getContactHelper().initContactModifiation();
        app.getContactHelper().fillContactForm(new ContactData("Peter", "Parker", "Spider-man", "New York, Queens, 112", "+70992223322", "peterp@yahoo.com", "1964", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returntoHomePage();
    }
}
