package ru.stqa.island.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman").withAddress("Gotham City, Freedom str, 1").withPhone("").withEmail("brucewayne123@gmail.com").withYearofbirth("1982").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().returntoHomePage();
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        assertThat(after, equalTo(before));
    }
}