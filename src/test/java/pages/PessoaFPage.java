package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class PessoaFPage {
    SelenideElement btnConsulta;
    SelenideElement list;

    public PessoaFPage menuConsultaPF() {
        $("a#frmMenu\\:lnkConsultas").click();
        $("a#frmMenu\\:lnkPessoaFisica").click();
        return this;
    }

    public PessoaFPage consultaPFporNome(String nome) {
        //Campo de nome para consultar
        $("input#filtroPessoaFisica\\:txtNomPessoa").setValue(nome);
        return this;
    }

    public PessoaFPage consultaPFporCpf(String cpf) {
        //Campo de cpf para consultar
        $("input#filtroPessoaFisica\\:txtNumCpf").setValue(cpf);
        return this;
    }

    public PessoaFPage pressBtnConsultar() throws InterruptedException {
        //Botão consultar
        btnConsulta = $("button#filtroPessoaFisica\\:btnConsultar > .ui-button-text");
        btnConsulta.click();
        Thread.sleep(40000);
        return this;
    }

    public PessoaFPage limpaConsultaNome() {
        $("input#filtroPessoaFisica\\:txtNomPessoa").setValue(null);
        return this;
    }

    public PessoaFPage limpaConsultacpf() {
        $("input#filtroPessoaFisica\\:txtNumCpf").setValue(null);
        return this;
    }

    public PessoaFPage retornaConsulta() {
        //Retorna resultado da consulta
        list = $("span#lista_pessoas\\:pessoas\\:0\\:txtNomPessoa");
        if (list.isDisplayed()) {
            System.out.println("A pessoa física consultada foi encontrada.");
        } else {
            System.out.println("A pessoa física consultada não possui cadastro em nossa base.");
        }
        return this;
    }

    public PessoaFPage incluirPF() {
        $("button#filtroPessoaFisica\\:btnIncluir > .ui-button-text").click();
        return this;
    }

    public PessoaFPage validaCPF(String cpf, String nome, String email, String mae, String cidade, String docRG, File doc, String cel, String cep, String n) throws InterruptedException {
        //envia o cpf para o campo cpf
        SelenideElement cpfInput = $("input#tabviewpf\\:formpesquisacpf\\:cpf");
        cpfInput.click();
        cpfInput.sendKeys(cpf);
        //clica no botão pesquisar
        $("a#tabviewpf\\:formpesquisacpf\\:pesquisaPessoaFisicaCpf > img").click();
        Thread.sleep(5000);
        //span que retorna se ha alguma inconsistencia no cpf
        SelenideElement span = $(".ui-growl-title");
        if ($("input#tabviewpf\\:formpessoafisica\\:nomepessoafisica").is(Condition.empty)) {
            //aqui dentro realiza o cadastro
            Thread.sleep(5000);
            $("input#tabviewpf\\:formpessoafisica\\:nomepessoafisica").setValue(nome);
            //seta data de nascimento
            $("img[alt='...']").click();
            Select selectYear = new Select($(".ui-datepicker-year"));
            selectYear.selectByValue("1999");
            // seleciona mes e dia
            $(".ui-datepicker-month").click();
            Select selectMonth = new Select($(".ui-datepicker-month"));
            selectMonth.selectByValue("2");
            $(".ui-datepicker-calendar").findElement(By.linkText("9")).click();
            //preenche os inputs e-mail, nome da mae e cidade
            $("input#tabviewpf\\:formpessoafisica\\:txtDesEmail").setValue(email);
            $("input#tabviewpf\\:formpessoafisica\\:txtNomMae").setValue(mae);
            $("input#tabviewpf\\:formpessoafisica\\:naturalidadePF").setValue(cidade);
            // seleciona pais
            Select selectPais = new Select($("select#tabviewpf\\:formpessoafisica\\:idcombopais"));
            selectPais.selectByValue("1");
            //Botões salvar, aguardar pagina e avançar
            $("button#tabviewpf\\:formpessoafisica\\:btnSalvar > .ui-button-text").click();
            Thread.sleep(4000);
            // Assert.assertEquals("Inclusão efetuada com sucesso!", $(".ui-growl-message"));

            $("button#form_avancar\\:btnAvancar > .ui-button-text").click();

            //Adicionar documento| 2º aba
            $("[title='Incluir Documento']").click();
            $("div#tabviewpf\\:formdocumentos\\:dialogdocumentos").should(Condition.appear);
            // seleciona tipo de documento
            Select selectTipoDoc = new Select($("select#tabviewpf\\:formdocumentos\\:tipoDocumento"));
            selectTipoDoc.selectByValue("1");
            $("input#tabviewpf\\:formdocumentos\\:numDocumento").setValue(docRG);
            // seleciona orgao emissor
            Select selectOrgao = new Select($("select#tabviewpf\\:formdocumentos\\:orgaoEmissor"));
            selectOrgao.selectByValue("30");
            // seleciona UF orgao emissor
            Select selectUFOrgao = new Select($("select#tabviewpf\\:formdocumentos\\:uforgaoemissor"));
            selectUFOrgao.selectByValue("5");
            //Seleciona data de emissao do documento
            $("span#tabviewpf\\:formdocumentos\\:dataEmissao  img[alt='...']").click();
            Select selectYearDoc = new Select($(".ui-datepicker-year"));
            selectYearDoc.selectByValue("2018");

            $(".ui-datepicker-month").click();
            Select selectMonthDoc = new Select($(".ui-datepicker-month"));
            selectMonthDoc.selectByValue("2");
            $(".ui-datepicker-calendar").findElement(By.linkText("9")).click();
            //Data de validade não é campo obrigatório
            //bnt de upload do documento
            $("input#tabviewpf\\:formdocumentos\\:lblFileUpload_input").uploadFile(doc);

            $("button#tabviewpf\\:formdocumentos\\:botaoSalvar").click();


            $("div#tabviewpf\\:formdocumentos\\:dialogdocumentos .ui-icon.ui-icon-closethick").click();
            //btn avançar
            $("button#form_avancar\\:btnAvancar > .ui-button-text").click();
            //aba telefone
            $("[title='Incluir Telefone']").click();
            //Lista com tipo de telefone
            Select selectTel = new Select($("select#tabviewpf\\:formdialogs\\:idcombotipotelefoneNew"));
            selectTel.selectByValue("3");

            SelenideElement tel = $("input#tabviewpf\\:formdialogs\\:numeroNew");
            tel.click();
            tel.sendKeys(cel);
            $("button#tabviewpf\\:formdialogs\\:btnSalvar > .ui-button-text").click();
            //Fecha a div
            $("div#tabviewpf\\:formdialogs\\:telefoneDialog .ui-icon.ui-icon-closethick").click();
            //Btn Avançar
            $("button#form_avancar\\:btnAvancar > .ui-button-text").click();
            //Aba endereço

            SelenideElement cp = $("input#tabviewpf\\:formEnderecoPesquisaCep\\:cep");
            cp.click();
            cp.sendKeys(cep);
            $("form#tabviewpf\\:formEndereco").click();
            $("input#tabviewpf\\:txtNumEndereco").sendKeys(n);
            $("button#tabviewpf\\:btnSalvar > .ui-button-text").click();
            $("button#form_avancar\\:btnAvancar > .ui-button-text").click();
            //Aba Procurador
            $("[title='Incluir Procurador']").click();
            SelenideElement proc = $("input#tabviewpf\\:formpesquisacpfproc\\:cpfproc");
            proc.click();
            proc.sendKeys(cpf);
            $("[title='Consultar']").click();
            $("button#tabviewpf\\:formpessoafisicaprocurador\\:btnSalvar > .ui-button-text").click();
            //Finaliza cadastro
            $("button#form_avancar\\:btnFinalizar > .ui-button-text").click();
        }
        if (span.isDisplayed()) {
            //printa a msg do span
            System.out.println(span.getText());
            //valida se o campo nome esta nulo, se esta nulo esta apto para cadastrar
        } else { //valida se o cpf ja esta cadastrado
            System.out.println("O CPF informado encontra-se cadastrado em nossa base:  " +
                    $("input#tabviewpf\\:formpessoafisica\\:nomepessoafisica").getValue());
        }
        return this;
    }
}
