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

    public void registrarCarga(String nome, double peso, int reps) {
        // A sintaxe se torna mais limpa dentro da subclasse
        Pessoa.exercicies novoExercicio = this.new exercicies(nome, peso, reps);
        this.carga.add(novoExercicio);
        System.out.println(" Exercício " + nome + " registrado com " + peso + "kg.");
    }


    public void removerCarga(int posicao) {
        if (this.carga.isEmpty()) {
            System.out.println(" Sua ficha de treino está vazia. Nada para remover.");
            return;
        }
        if (posicao >= 0 && posicao < this.carga.size()) {
            String nomeExercicio = this.carga.get(posicao).getNomeex();
            this.carga.remove(posicao);

            System.out.println(" Exercício '" + nomeExercicio + "' na posição [" + posicao + "] removido da ficha de treino.");
        } else {
            int ultimoIndice = this.carga.size() - 1;
            System.out.println(" Erro: Posição inválida (" + posicao + "). Digite um número entre 0 e " + ultimoIndice + ".");
        }
    }
    public void listarCargaComIndices() {
        System.out.print("\n=== EXERCÍCIOS CADASTRADOS DE " + this.getNome() + " ===");
        if (this.carga.isEmpty()) {
            System.out.println("Não há exercícios cadastrados na sua ficha.");
            return;
        }

        int indice = 0;
        for (Pessoa.exercicies ex : this.carga) {
            System.out.println("[" + indice + "] - " + ex.getNomeex() + " | Peso: " + ex.getPesoex() + "kg | Reps: " + ex.getReps());
            indice++;
        }
        System.out.println("=========================================================");
    }

    // Corrigido: Agora salva uma String legível no histórico, não a soma dos valores
    public void atualizarMedidas(double peso, double braco, double cintura, double altura) {
        this.peso = peso;
        this.altura = altura;
        String registro = "Peso: " + peso + "kg | Braço: " + braco + "cm | Cintura: " + cintura + "cm";
        this.historicoMedidas.add(registro);
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

    public String getNome() {
        return super.getNome();
    }
    public List<FichaTreino> getFichasTreino() {
        return fichasTreino; }

    public void verPerfilAluno() {
        System.out.println("\n=== PERFIL DO ALUNO ===");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("CPF: " + getCpf());
        System.out.println("Idade: " + this.idade + " anos");
        System.out.println("Peso: " + this.peso + " kg");
        System.out.println("Altura: " + this.altura + " m");
        System.out.println("Objetivo: " + this.objetivo);
        System.out.println("Restrição Física: " + (this.restricaoFisica != null ? this.restricaoFisica : "Nenhuma"));
        System.out.println("------------------------------");
    }

    // Mantive o método original getMedida
    public String getMedida() {
        return "Medidas atuais registradas: " + medidas.size();
    }

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

    public boolean solicitarAgendamento(Profissional personal, Agendamento novoAgendamento) {
        // 1. Valida se os objetos não são nulos
        if (personal == null || novoAgendamento == null) {
            System.out.println("Erro: Personal ou Agendamento inválidos.");
            return false;
        }

        boolean conseguiuAgendar = personal.marcarHr(this, novoAgendamento);

        if (conseguiuAgendar) {
            this.meusAgendamentos.add(novoAgendamento);
            System.out.println("Agendamento salvo no seu histórico!");
            return true;
        } else {
            System.out.println("Não foi possível realizar o agendamento (Horário indisponível ou erro).");
            return false;
        }
    }
    public String toStringArquivo() {
        // Formato: C;ID;NOME;CPF;TELEFONE;EMAIL;SENHA;SEXO;IDADE;PESO;ALTURA;OBJETIVO;RESTRICAO
        return "C;" +
                getId() + ";" +
                getNome() + ";" +
                getCpf() + ";" +
                getTelefone() + ";" +
                getEmail() + ";" +
                getSenha() + ";" +
                getSexo() + ";" +
                this.idade + ";" +
                this.peso + ";" +
                this.altura + ";" +
                this.objetivo + ";" +
                (this.restricaoFisica != null ? this.restricaoFisica : "Nenhuma");
    }
}