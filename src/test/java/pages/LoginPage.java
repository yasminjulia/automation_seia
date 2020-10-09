package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    public LoginPage open(){
        Selenide.open("/login");
        return this;
    }
    public LoginPage with(String email, String pass){
        $("#_dialogAlertaIndex .ui-icon-closethick").click();
        $("#j_username").setValue(email);
        $("#j_password").setValue(pass);
        return this;
    }
    public LoginPage pressBtn(){
        $("#btnEntrar").click();
        return this;
    }
    public LoginPage checkLogin(String email) throws NoSuchElementException {
        $("form[name='j_idt34']  tbody > tr > td:nth-of-type(4)").shouldHave(text(email));
        return this;
    }
    public SelenideElement alert(){
        return $("#pnlLogin_content span");
        //div que traz span de erro
    }
    public LoginPage logout(){
        $("a[title='Sair']").click();
        return this;
    }

}
