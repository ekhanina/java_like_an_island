package ru.stqa.island.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman").withAddress("Gotham City, Freedom str, 1").withPhone("").withEmail("brucewayne123@gmail.com").withYearofbirth("1982").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().returntoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}