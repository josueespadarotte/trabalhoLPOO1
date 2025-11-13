package classes;

public class Profissional extends Pessoa{
    private String especialidade;
    private Integer numeroRegistro;
    private Double avaliacao;
    private String disponibilidade;

    //Construtor
    public Profissional(){
        super();
    }

    public Profissional(String nome, Integer id, String cpf, Integer telefone, String email,
                        String sexo, String especialidade, Integer numeroRegistro, Double avaliacao, String disponibilidade) {
        super(sexo, nome, id, cpf, telefone, email);
        this.especialidade = especialidade;
        this.numeroRegistro = numeroRegistro;
        this.avaliacao = avaliacao;
        this.disponibilidade = disponibilidade;
    }
    //metodo para atualizar a disponibilidade do personal
    public void atualizarDisponibilidade(String nova) {
        this.disponibilidade = nova;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }
}

