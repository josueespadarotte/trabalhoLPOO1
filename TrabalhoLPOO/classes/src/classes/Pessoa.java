package classes;

public class Pessoa {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private String sexo;

    public  class exercicies{
        private String nomeex;
        private double pesoex;
        private int reps;
        public exercicies(String nomeex, double pesoex, int reps){
            this.nomeex = nomeex;
            this.pesoex = pesoex;
            this.reps = reps;
        }

        public String getNomeex() {
            return nomeex;
        }

        public String getPesoex() {
            return pesoex + "";
        }

        public String getReps() {
            return reps + "";
        }
    }
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
    public boolean verificarSenha(String senhaInput) {
        return this.senha != null && this.senha.equals(senhaInput);
    }

    public void alterarSenha(String novaSenha) {
        if (novaSenha != null && novaSenha.length() >= 4) {
            this.senha = novaSenha;
            System.out.println("Senha alterada com sucesso.");
        } else {
            System.out.println("A senha deve ter pelo menos 4 caracteres.");
        }
    }

    public void atualizarDados(String novoNome, String novoEmail, String novoTelefone) {
        if (novoNome != null && !novoNome.isEmpty()) {
            this.nome = novoNome;
            System.out.println("Nome atualizado para: " + this.nome);
        }

        if (novoEmail != null && !novoEmail.isEmpty()) {
            this.email = novoEmail;
            System.out.println("Email atualizado para: " + this.email);
        }

        if (novoTelefone != null && !novoTelefone.isEmpty()) {
            // Validação simples para aceitar apenas números
            if (novoTelefone.matches("\\d+")) {
                this.telefone = novoTelefone;
                System.out.println("Telefone atualizado para: " + this.telefone);
            } else {
                System.out.println("Erro: O telefone deve conter apenas números. Alteração ignorada.");
            }
        }
    }

    // métdos criados para diminuir a quantidade de while no main

    public static boolean validarNome(String nome) {
        // Aceita apenas letras e deve ser maior que 3 letras
        return nome != null && nome.length() > 3 && nome.matches("^[a-zA-Z\\s]+$");
    }

    public static boolean validarCpf(String cpf) {
        // Apenas números positivos e exatamente 11 dígitos
        return cpf != null && cpf.matches("\\d{11}");
    }

    public static boolean validarEmail(String email) {
        // padrão email
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean validarPositivo(double valor) {
        return valor > 0;
    }

    public static boolean validarPeso(double peso) {
        return peso > 10;
    }
}
