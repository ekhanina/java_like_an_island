package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        app.goTo().returntoHomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/succulentcupcakes.jpg");
        ContactData contact = new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman")
                .withAddress("Gotham City, Freedom str, 1").withHomePhone("").withEmail("brucewayne123@gmail.com")
                .withYearofbirth("1982").withGroup("test1")
                .withPhoto(photo);
        app.contact().create(contact, true);
        app.goTo().returntoHomePage();
        Contacts after = app.contact().all();
        //assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/succulentcupcakes.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

    @Test (enabled = false)
    public void testBadNewContact() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Bruce'").withLastname("Wayne").withNickname("Batman").withAddress("Gotham City, Freedom str, 1").withHomePhone("").withEmail("brucewayne123@gmail.com").withYearofbirth("1982").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().returntoHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}