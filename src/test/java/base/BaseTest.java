package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.$;

public class BaseTest {
    protected static LoginPage login;
    //protected static SideBar side;

    @BeforeMethod
    public void start() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://10.90.2.22";
        Configuration.timeout = 50000;

        login = new LoginPage();

    }
}
