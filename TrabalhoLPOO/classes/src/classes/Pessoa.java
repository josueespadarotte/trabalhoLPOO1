package classes;

public class Pessoa {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private String sexo;


    public Pessoa(Integer id, String nome, String cpf, String telefone, String email, String senha, String sexo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    // metodos
    public void atualizarDados(String novoNome, String novoEmail, String novoTelefone) {
        if (novoNome != null && !novoNome.isEmpty()) {
            this.nome = novoNome;
            System.out.println("Nome atualizado.");
        }
        if (novoEmail != null && !novoEmail.isEmpty()) {
            this.email = novoEmail;
            System.out.println("Email atualizado.");
        }
        if (novoTelefone != null && !novoTelefone.isEmpty()) {
            if (novoTelefone.matches("\\d+")) {
                this.telefone = novoTelefone;
                System.out.println("Telefone atualizado.");
            } else {
                System.out.println("Erro: Telefone deve conter apenas números.");
            }
        }
    }
    public boolean verificarSenha(String senhaInput) {
        return this.senha != null && this.senha.equals(senhaInput);
    }

    public void alterarSenha(String novaSenha) {
        if (novaSenha != null && novaSenha.length() >= 4) { // Exemplo de validação simples
            this.senha = novaSenha;
            System.out.println("Senha alterada com sucesso.");
        } else {
            System.out.println("A senha deve ter pelo menos 4 caracteres.");
        }
    }
}
