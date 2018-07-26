package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

public class NewContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        addNewContact();
        fillContactForm(new ContactData("Bruce", "Wayne", "Batman", "Gotham City, Freedom str, 1", "+79851231212", "brucewayne123@gmail.com", "1982"));
        submitContact();
        returntoHomePage();
    }

}
