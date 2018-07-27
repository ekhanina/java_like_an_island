package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.GroupData;

public class GroupModificationsTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returntoGroupPage();
    }
}
