package ru.stqa.island.mantis.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.island.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpenSoap(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        String status = app.soap().getBugStatus(issueId);
        System.out.println("Current status of a bug " + issueId + " is "+ status);
        return !status.equals("closed");
    }

    public void skipIfNotFixedSoap(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpenSoap(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpenRest(int issueId) {
        boolean isIssueOpen = true;
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json?limit=300").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement state_name = issues.getAsJsonArray().get(0).getAsJsonObject().get("state_name");
        String status = state_name.getAsString();
        if (status.equals("Resolved")||status.equals("Closed")) isIssueOpen = false;
        return isIssueOpen;
    }
    public void skipIfNotFixedRest(int issueId) {
        if (isIssueOpenRest(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
