package ru.stqa.island.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testsContactModification() {
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereaContact()) {
            app.getContactHelper().createContact(new ContactData("Bruce", "Wayne", "Batman", "Gotham City, Freedom str, 1", null, "brucewayne123@gmail.com", "1982", "test1"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Peter", "Parker", "Spider-man", "New York, Queens, 112", "+70992223322", "peterp@yahoo.com", "1964", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returntoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
