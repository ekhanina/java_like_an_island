package ru.stqa.island.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().returntoHomePage();
            app.contact().create(new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman")
                    .withAddress("Gotham City, Freedom str, 1").withHomePhone("+88009988777").withEmail("brucewayne123@gmail.com"));
        }
    }

    @Test
    public void testDeleteContact() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().returntoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
