package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteContact() {
        if (! app.getContactHelper().isThereaContact()) {
            app.getContactHelper().createContact(new ContactData("Bruce", "Wayne", "Batman", "Gotham City, Freedom str, 1", null, "brucewayne123@gmail.com", "1982", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().returntoHomePage();
    }

}
