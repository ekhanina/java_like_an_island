package ru.stqa.island.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.island.mantis.model.MailMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void clickManageUsers() {
        wd.findElement(By.linkText("Manage Users")).click();
    }

    public void clickUser(String user) {
        String xpath = "//a[text()='"+user+"']";
        click(By.xpath(xpath));
    }

    public void resetPassword() {
        wd.findElement(By.xpath("//div[4]/form[1]/input[3]")).click();
    }

    public String findResetLink(List<MailMessage> mailMessages, String email){
        MailMessage message = mailMessages.stream().filter((m)->m.to.equals("Request for a password change")).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);
    }

    public String findChangePasswordLink(List<MailMessage> mailMessages, String email){
        ArrayList<Long> datesList = new ArrayList<>();
        for (int i=0;i<mailMessages.size();i++) {
            datesList.add(mailMessages.get(i).messageDate.getTime());
        }
        long max = Collections.max(datesList);
        int latestIndex = 0;
        for (int i=0;i<mailMessages.size();i++) {
            if (mailMessages.get(i).messageDate.getTime()==max) {
                latestIndex = i;
                break;
            }
        }
        MailMessage message = mailMessages.get(latestIndex);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
