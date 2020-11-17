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
                .with("girlene.silva", "@123456")
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
                    .consultaPFporCpf("02649874546")
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
                .validaCPF("285.517.750-22", "Nome da pessoa", "emailteste@gmail.com", "Nome mãe", "Nome cidade", "123456789", new File("doc.png"));

    }
}
