package ru.stqa.island.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;
import ru.stqa.island.addressbook.model.GroupData;
import ru.stqa.island.addressbook.model.Groups;

public class AddContacttoGroupTests extends TestBase {

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
    public void testAddContacttoGroup() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData addedContact = before.iterator().next();
        ContactData contacttoadd = new ContactData().withId(addedContact.getId()).withFirstname("Bruce").withLastname("Wayne").withNickname("Batman")
                .withAddress("Gotham City, Freedom str, 1").withHomePhone("+88009988777").withEmail("brucewayne123@gmail.com");
        app.contact().create(contacttoadd);

        if (contacttoadd.getGroups().size() == 0) {

        }


    }

}
