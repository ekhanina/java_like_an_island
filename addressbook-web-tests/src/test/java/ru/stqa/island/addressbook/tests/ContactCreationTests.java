package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Bruce", "Wayne", "Batman", "Gotham City, Freedom str, 1", null, "brucewayne123@gmail.com", "1982", "test1"), true);
        app.getContactHelper().submitContact();
        app.getNavigationHelper().returntoHomePage();
    }

}
