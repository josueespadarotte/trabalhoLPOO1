package classes;

public class Cliente extends Pessoa {
    private Integer idade;
    private Double peso;
    private Double altura;
    private String objetivo;
    private String restricaoFisica;


    public Cliente(Integer id, String nome, String cpf, Integer telefone, String email,
                   String senha, String sexo, Integer idade, Double peso, Double altura, String objetivo, String restricaoFisica) {
        super(id, nome, cpf, telefone, email, senha, sexo);
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.restricaoFisica = restricaoFisica;
    }
    
    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getRestricaoFisica() {
        return restricaoFisica;
    }

    public void setRestricaoFisica(String restricaoFisica) {
        this.restricaoFisica = restricaoFisica;
    }

    // meotodos
    public double calcularIMC() {
        return peso / (altura * altura);
    }

    public void atualiarObjetivo (String novbOjetivo){ // atualizar objetivo
    }

    public void atualizarRestricao(String novaRestricao){ // atualizar restricao

    }
    public boolean solicitarAgendamento(Profissional pt,  Agendamento novoAgendamento) {
        return false;

    }
    public void cancelarAgendamento(Agendamento agendamento) {

    }
    /*public List<Treino> visualizarTreinos() { // visualizar a ficha de treino
        return treinos;
    }*/
    public void exibirDadosCompletos() { // visualizar os dados do meu perfil
        System.out.println("---- Cliente ----");
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + idade);
        System.out.println("Peso: " + peso);
        System.out.println("Altura: " + altura);
        System.out.println("Objetivo: " + objetivo);
        System.out.println("Restrição Física: " + restricaoFisica);
        //System.out.println("Total de Treinos: " + treinos.size());
        //System.out.println("Agendamentos: " + agendamentos.size());
        System.out.println("------------------");
    }

    }
