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
                {"giselemota", "@963741"},
                {"luciana.debrito", "@963741"},
                {"pereirajosedelaide", "@963741"},
                {"priscila.soares", "@963741"},
        };
    }

    @Test(dataProvider = "massa")
    public void loggedUser(String email, String pass){
        try {
            login
                    .open()
                    .with(email, pass)
                    .pressBtn()
                    .checkLogin(email)
                    .logout();

        } catch (Throwable ex) {
            Assert.fail("Desculpe, ocorreu o seguinte erro:");
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
