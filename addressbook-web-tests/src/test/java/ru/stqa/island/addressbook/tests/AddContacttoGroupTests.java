package ru.stqa.island.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;
import ru.stqa.island.addressbook.model.GroupData;
import ru.stqa.island.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContacttoGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().returntoHomePage();
            Groups groups = app.db().groups();
            app.contact().create(new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman")
                    .withAddress("Gotham City, Freedom str, 1").withHomePhone("+88009988777").withEmail("brucewayne123@gmail.com")
                    .inGroup(groups.iterator().next()));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testAddContacttoGroup() {
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        int oldContacts;

        for (ContactData c: contacts) {
            System.out.println("c " + c);
            if (groups.size() != c.getGroups().size()) {
                contact = c;
                System.out.println("contact" + contact);
                break;
            }
        }

        int before = contact.getGroups().size();

        if(groups.size() == contact.getGroups().size()){
            app.contact().removeGroup(contact, group);
            group = app.db().groups().iterator().next();
            oldContacts = app.db().groups().iterator().next().withId(group.getId()).getContacts().size();
            app.contact().addContactToGroup(contact, group);
        } else {
            groups.removeAll(contact.getGroups());
            oldContacts = group.getContacts().size();
            app.contact().addContactToGroup(contact, group);
        }

        int after = contact.getGroups().size();
        int newContacts =  app.db().groups().iterator().next().withId(group.getId()).getContacts().size();

        assertThat(after, equalTo(before));
        assertThat(newContacts, equalTo(oldContacts +  1));
    }
}
