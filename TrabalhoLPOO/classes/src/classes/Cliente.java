package classes;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private Integer idade;
    private Double peso;
    private Double altura;
    private String objetivo;
    private String restricaoFisica;

    // Inicializando as listas para evitar erro (NullPointerException)
    private List<FichaTreino> fichasTreino = new ArrayList<>();
    private List<Agendamento> meusAgendamentos = new ArrayList<>();
    private ArrayList<Double> medidas = new ArrayList<>();
    // Usando a classe interna que você criou em Pessoa
    public ArrayList<Pessoa.exercicies> carga = new ArrayList<>();
    private List<String> historicoMedidas = new ArrayList<>();
    private List<Avaliacao> listaAvaliacoes = new ArrayList<>();

    public Cliente(int id, String nome, String cpf, String telefone, String email, String senha, String sexo,
                   int idade, double peso, double altura, String objetivo, String restricaoFisica) {
        super(id, nome, cpf, telefone, email, senha, sexo);
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.restricaoFisica = restricaoFisica;
    }

    public Cliente(int id, String nome, String cpf, String telefone, String email, String senha, String sexo,
                   int idade, double peso, double altura, String objetivo) {
        super(id, nome, cpf, telefone, email, senha, sexo);
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.restricaoFisica = "Nenhuma";
    }

    // --- MÉTODOS CORRIGIDOS ---

    // Agora salva o exercício na lista 'carga'
    public void registrarCarga(String nome, double peso, int reps) {
        Pessoa.exercicies novoExercicio = new Pessoa(0,"","","","","","").new exercicies(nome, peso, reps);
        this.carga.add(novoExercicio);
        System.out.println("Exercício " + nome + " registrado com " + peso + "kg.");
    }

    // Corrigido: Agora salva uma String legível no histórico, não a soma dos valores
    public void atualizarMedidas(double peso, double braco, double cintura, double altura) {
        this.peso = peso;
        this.altura = altura;
        String registro = "Peso: " + peso + "kg | Braço: " + braco + "cm | Cintura: " + cintura + "cm";
        this.historicoMedidas.add(registro);
        // Também adiciona na lista numérica se precisar de gráfico depois
        medidas.add(peso);
    }

    public void adicionarFicha(FichaTreino novaFicha) {
        this.fichasTreino.add(novaFicha);
    }

    // Estava vazio no original, agora adiciona na lista
    public void adicionarAvaliacao(Avaliacao novaAvaliacao) {
        if(novaAvaliacao != null) {
            this.listaAvaliacoes.add(novaAvaliacao);
        }
    }

    public void visualizarAvaliacoes() {
        System.out.println("=== Histórico de Avaliações ===");
        if (listaAvaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação registrada.");
        } else {
            for (Avaliacao av : listaAvaliacoes) {
                // Ajuste conforme seus getters em Avaliacao
                System.out.println("Nota: " + av.getNota() + " | Data: " + av.getData());
            }
        }
    }

    // --- GETTERS E OUTROS MÉTODOS ORIGINAIS ---
    public String getNome() { return super.getNome(); }
    public List<FichaTreino> getFichasTreino() { return fichasTreino; }

    public void verProgresso() {
        System.out.println("=== Histórico ===");
        for(String m : historicoMedidas) {
            System.out.println(m);
        }
    }

    // Mantive o método original getMedida
    public String getMedida() {
        return "Medidas atuais registradas: " + medidas.size();
    }

    // ... restante dos seus métodos (agendamento, etc) ...
    public void visualizarFichaTreino() {
        if(fichasTreino.isEmpty()){
            System.out.println("Sem fichas.");
        } else {
            for(FichaTreino f : fichasTreino){
                System.out.println("Ficha ID: " + f.getId() + " - " + f.getExercicios());
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Nome: " + getNome();
    }

    // Adicione este método dentro da classe Cliente
    public boolean solicitarAgendamento(Profissional personal, Agendamento novoAgendamento) {
        // 1. Valida se os objetos não são nulos
        if (personal == null || novoAgendamento == null) {
            System.out.println("Erro: Personal ou Agendamento inválidos.");
            return false;
        }

        // 2. Tenta marcar na agenda do profissional (ele verifica disponibilidade lá)
        // Passamos 'this' porque 'this' é o próprio Cliente que está chamando o método
        boolean conseguiuAgendar = personal.marcarHr(this, novoAgendamento);

        // 3. Se o profissional confirmou, salvamos na lista do cliente também
        if (conseguiuAgendar) {
            this.meusAgendamentos.add(novoAgendamento);
            System.out.println("Agendamento confirmado e salvo no seu histórico!");
            return true;
        } else {
            System.out.println("Não foi possível realizar o agendamento (Horário indisponível ou erro).");
            return false;
        }
    }
}