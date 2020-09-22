package tests;

import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginTest extends BaseTest {

    @DataProvider(name= "massa-login")
    public Object [][] loginProvider(){
        return new Object[][]{
                {"lenimaria.palma","123456","A senha está incorreta! O acesso é bloqueado após 3 tentativas"},
                {"","@123456","Por favor, preencha os dados de acesso."},
                {"vanesas.dias","","Por favor, preencha os dados de acesso."},
        };
    }
    @DataProvider(name="massa")
    public Object[][] massaProvider(){
        return new Object[][]{
                {"lenimaria.palma","@123456"},
                {"vanesas.dias","@123456"},
                {"farluse.gomes","@123456"},
                {"vilma.lessa","@123456"},
        };
    }
    @Test (dataProvider = "massa")
    public void loggedUser(String email, String pass){
       try {
           login
                   .open()
                   .with(email, pass)
                   .pressBtn();
           if($("#pnlLogin_content span").isDisplayed()){
               login
                       .with(email,pass);
               $("span#recaptcha-anchor > div:nth-of-type(1)").
                       setSelected(true);
               login.pressBtn();
           }

       } catch (Exception ex){
               ex.printStackTrace();
               }

               login
               .checkLogin(email)
               .logout();
               }
@Test(dataProvider = "massa-login")
public void LoginAlerts(String email, String pass, String expectAlert) {


        login
        .open()
        .with(email, pass)
        .pressBtn()
        .alert().shouldHave(text(expectAlert));
        }
    /*@AfterMethod
    public void cleanup(){
        login.logout();
    }*/
        }
