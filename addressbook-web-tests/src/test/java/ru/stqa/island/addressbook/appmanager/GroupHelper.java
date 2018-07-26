package ru.stqa.island.addressbook.appmanager;

import com.sun.javafx.binding.ExpressionHelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.island.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void returntoGroupPage() {
        click(wd.findElement(By.linkText("group page")));
    }

    public void submitGroupCreation() {
        click(wd.findElement(By.name("submit")));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(wd.findElement(By.name("new")));
    }

    public void deleteSelectedGroups() {
        click(wd.findElement(By.name("delete")));
    }

    public void selectGroup() {
        click(wd.findElement(By.name("selected[]")));
    }
}
