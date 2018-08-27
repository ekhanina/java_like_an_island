package ru.stqa.island.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Bruce").withLastname("Wayne").withNickname("Batman").withAddress("Gotham City, Freedom str, 1").withPhone("").withEmail("brucewayne123@gmail.com").withYearofbirth("1982").withGroup("test1"), true);
        }
    }

    @Test
    public void testDeleteContact() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().returntoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
