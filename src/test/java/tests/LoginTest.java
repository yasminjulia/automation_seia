package tests;

import base.BaseTest;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class LoginTest extends BaseTest {

    @DataProvider(name = "massa-login")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"jose.1", "123456", "A senha está incorreta! O acesso é bloqueado após 3 tentativas"},
                {"", "@123456", "Por favor, preencha os dados de acesso."},
                {"vanesas.dias", "", "Por favor, preencha os dados de acesso."},
        };
    }

    @DataProvider(name = "massa")
    public Object[][] massaProvider() {
        return new Object[][]{
                {"jose.1", "@123456"},
                {"luciana.debrito", "@123456"},
                {"pereirajosedelaide", "@123456"},
                {"priscila.soares", "@123456"},
        };
    }

    @Test(dataProvider = "massa")
    public void loggedUser(String email, String pass) {
        try {
            login
                    .open()
                    .with(email, pass)
                    .pressBtn()
                    .checkLogin(email)
                    .logout();

        } catch (Throwable ex) {
            Assert.fail("Não é possivel utilizar esse usuário: " + email);
        }
    }

    @Test(dataProvider = "massa-login")
    public void LoginAlerts(String email, String pass, String expectAlert) {

        login
                .open()
                .with(email, pass)
                .pressBtn()
                .alert().shouldHave(text(expectAlert));
    }
}
