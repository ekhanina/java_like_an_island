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
        if (app.db().contacts().size() == 0) {
            app.goTo().returntoHomePage();
            app.contact().create(new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman")
                    .withAddress("Gotham City, Freedom str, 1").withHomePhone("+88009988777").withEmail("brucewayne123@gmail.com"));
        }
    }

    @Test
    public void testsContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Bruce").withLastname("Wayne").withNickname("Batman")
                .withAddress("Gotham City, Freedom str, 1").withHomePhone("+88009988777").withEmail("brucewayne123@gmail.com");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
