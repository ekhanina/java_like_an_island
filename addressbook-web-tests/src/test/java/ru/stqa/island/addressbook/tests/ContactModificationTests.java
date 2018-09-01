package ru.stqa.island.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman").withAddress("Gotham City, Freedom str, 1").withHomePhone("").withEmail("brucewayne123@gmail.com").withYearofbirth("1982").withGroup("test1"), true);
        }
    }

    @Test
    public void testsContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Peter").withLastname("Parker");
        app.contact().modify(contact);
        app.goTo().returntoHomePage();
        assertThat(app.group().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
