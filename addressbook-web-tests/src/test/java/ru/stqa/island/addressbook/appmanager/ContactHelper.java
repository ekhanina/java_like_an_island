package ru.stqa.island.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;
import ru.stqa.island.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContact() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        //attach(By.name("photo"), contactData.getPhoto());
        if (!selectOption(By.xpath("//div[@id='content']/form/select[1]//option[9]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[9]"));
        }
        if (!selectOption(By.xpath("//div[@id='content']/form/select[2]//option[3]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[3]"));
        }
        type(By.name("byear"), contactData.getYearofbirth());

        /*if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }} else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }*/
        }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href = 'edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    private void returnToGroupPage(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='./?group=%s']", id))).click();
    }

    public void create(ContactData contact) {
        addNewContact();
        fillContactForm(contact);
        submitContact();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        addContactToGroup(group);
        clickAddGroup();
        contactCache = null;
        returnToGroupPage(group.getId());
    }

    public void removeGroup(ContactData contact, GroupData groups) {
        selectGroup(groups);
        selectContactById(contact.getId());
        removeGroupFromContact();
        contactCache = null;
        returnToGroupPage(groups.getId());
        returnToAllGroupPage();
    }

    private void removeGroupFromContact() {
        click(By.name("remove"));
    }

    private void returnToAllGroupPage() {
        new Select(wd.findElement(By.name("group")))
                .selectByVisibleText("[all]");
    }

    private void selectGroup(GroupData group) {
        new Select(wd.findElement(By.name("group")))
                .selectByVisibleText(group.getName());
    }

    private void addContactToGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group")))
                .selectByVisibleText(group.getName());
    }

    private void clickAddGroup() {
        click(By.name("add"));
    }

    public boolean isThereaContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
    }

    public int count() {
        return wd.findElements((By.xpath("//tr[@name='entry']//input"))).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
