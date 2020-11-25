package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class PessoaFTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        login
                .open()
                .with("joao.borges", "@123456")
                .pressBtn();
    }

    @Test
    public void fazConsultaNome() {
        try {
            consult
                    .menuConsultaPF()
                    .consultaPFporNome("Rui de Oliveira Cardoso")
                    .pressBtnConsultar()
                    .retornaConsulta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consult.limpaConsultaNome();
    }

    @Test
    public void fazConsultaCpf() {
        try {
            consult
                    .menuConsultaPF()
                    .consultaPFporCpf("05160177566")
                    .pressBtnConsultar()
                    .retornaConsulta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consult.limpaConsultacpf();
    }

    @Test
    public void fazCadastro() throws InterruptedException {
        consult
                .menuConsultaPF()
                .incluirPF()
                .validaCPF("408.076.020-51", "Nome da pessoa", "emailteste@gmail.com",
                        "Nome mãe", "Nome cidade", "123456789", new File("doc.png"),
                        "9999999999", "41.195-430", "40");
    }
}
