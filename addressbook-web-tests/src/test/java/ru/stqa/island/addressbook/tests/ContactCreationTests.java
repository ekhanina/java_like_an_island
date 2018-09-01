package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman").withAddress("Gotham City, Freedom str, 1").withPhone("").withEmail("brucewayne123@gmail.com").withYearofbirth("1982").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().returntoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadNewContact() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Bruce'").withLastname("Wayne").withNickname("Batman").withAddress("Gotham City, Freedom str, 1").withPhone("").withEmail("brucewayne123@gmail.com").withYearofbirth("1982").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().returntoHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}