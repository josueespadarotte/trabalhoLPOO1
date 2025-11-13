package classes;

public class Pessoa {
    private Integer id;
    private String nome;
    private String cpf;
    private Integer telefone;
    private String email;
    private String sexo;

    public Pessoa() {

    }
    public Pessoa(String sexo, String nome, Integer id, String cpf, Integer telefone, String email) {
        this.nome = nome;
        this.id = id;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public String getSexo(){
        return sexo;
    }
    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
