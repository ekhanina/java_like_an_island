package ru.stqa.island.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.island.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContact() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
        if (!selectOption(By.xpath("//div[@id='content']/form/select[1]//option[9]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[9]"));
        }
        if (!selectOption(By.xpath("//div[@id='content']/form/select[2]//option[3]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[3]"));
        }
        type(By.name("byear"), contactData.getYearofbirth());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void selectContact() {
        click(By.xpath("//input[@type='checkbox']"));
    }

    public void initContactModifiation() {
        click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact, boolean b) {
        addNewContact();
        fillContactForm(contact,b);
        submitContact();
    }

    public boolean isThereaContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
    }
}
