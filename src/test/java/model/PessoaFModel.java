package model;

import java.io.File;

public class PessoaFModel {
    public String cpf;
    public String nome;
    public String email;
    public String mae;
    public String cidade;
    public String docRg;
    public File doc;

    public PessoaFModel(String cpf, String nome, String email, String mae, String cidade, String docRg, File doc) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.mae = mae;
        this.cidade = cidade;
        this.docRg = docRg;
        this.doc = new File(this.coverPath() + doc);
    }

    private String coverPath() {
        String executionPath = System.getProperty("user.dir");
        String os = System.getProperty("os.name");
        String target;

        if (os.contains("Windows")) {
            target = executionPath + "\\src\\main\\resources\\doc\\";
        } else {
            target = executionPath + "/src/main/resources/doc/";
        }
        return target;
    }
}
