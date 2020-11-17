package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.PessoaFPage;

public class BaseTest {
    protected static LoginPage login;
    protected static PessoaFPage consult;

    @BeforeMethod
    public void start() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://10.90.2.65";
        Configuration.timeout = 50000;

        login = new LoginPage();
        consult = new PessoaFPage();

    }
}
