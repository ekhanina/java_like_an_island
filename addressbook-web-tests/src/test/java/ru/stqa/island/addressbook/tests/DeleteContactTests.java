package ru.stqa.island.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteContact() {
        if (! app.getContactHelper().isThereaContact()) {
            app.getContactHelper().createContact(new ContactData("Bruce", "Wayne", "Batman", "Gotham City, Freedom str, 1", null, "brucewayne123@gmail.com", "1982", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().returntoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
