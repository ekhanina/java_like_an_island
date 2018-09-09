package ru.stqa.island.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;
import ru.stqa.island.addressbook.model.GroupData;
import ru.stqa.island.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().returntoHomePage();
            app.contact().create(new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman")
                    .withAddress("Gotham City, Freedom str, 1").withHomePhone("+88009988777").withEmail("brucewayne123@gmail.com"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();

        int beforeGroups = contact.getGroups().size();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        int beforeContacts = group.getContacts().size();

        for (ContactData c : contacts) {
            System.out.println("c " + c);
            if (c.getGroups().size() != 0) {
                contact = c;
                System.out.println("contact" + contact);
                break;
            }
        }

        if (beforeGroups == 0) {
            app.contact().addContactToGroup(contact, group);
            beforeContacts = app.db().groups().iterator().next().withId(group.getId()).getContacts().size();
            app.contact().removeGroup(contact, group);
        } else {
            group = contact.getGroups().iterator().next();
            app.contact().removeGroup(contact, group);
        }
        int afterGroups = contact.getGroups().size();
        int afterContacts = app.db().groups().iterator().next().withId(group.getId()).getContacts().size();

        assertThat(afterGroups, equalTo(beforeGroups));
        assertThat(afterContacts, equalTo(beforeContacts - 1));
    }
}
