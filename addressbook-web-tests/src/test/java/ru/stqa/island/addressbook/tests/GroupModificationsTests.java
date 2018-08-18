package ru.stqa.island.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.island.addressbook.model.GroupData;

public class GroupModificationsTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereaGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "test5", "test6"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returntoGroupPage();
    }
}
