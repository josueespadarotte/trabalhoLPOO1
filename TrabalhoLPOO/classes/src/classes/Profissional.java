package classes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Profissional extends Pessoa{
    private String especialidade;
    private Integer numeroRegistro;
    private Double avaliacao;
    private String disponibilidade;


    public Profissional(Integer id, String nome, String cpf, String telefone, String email, String senha, String sexo,
                        String especialidade, Integer numeroRegistro, Double avaliacao, String disponibilidade) {
        super(id, nome, cpf, telefone, email, senha, sexo);
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


    /*métodos e funcionalidades*/

    ArrayList<Profissional> listaProfissional = new ArrayList();

    public void cadastrarCliete (Cliente cliente) {
        if (cliente != null) {

        }

    }
    public void removerCliete (Cliente cliente) {

    }


    public void addAgenda(Agendamento novoAgendamento){

    }
    public void removerAgenda(Agendamento rmAgendamento){

    }
    public void listarHrDisponivel(String disponibilidade){

    }
    public void calcularValorDisp(){

    }
    public boolean estaDisponivel(){
        return false;
    }
    public boolean marcarHr(Cliente c, Agendamento novoAgendamento){
        return false;
    }
    public void cancelarHr(Agendamento ag){

    }
    public void exibirAgenda(){

    }

}
