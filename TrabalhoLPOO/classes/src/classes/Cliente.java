package classes;

import java.util.ArrayList;
import java.util.List;


public class Cliente extends Pessoa {
    private Integer idade;
    private Double peso;
    private Double altura;
    private String objetivo;
    private String restricaoFisica;

    // Listas
    private List<FichaTreino> fichasTreino;
    private List<Agendamento> meusAgendamentos;
    private ArrayList<Double> medidas = new ArrayList();
    public ArrayList<exercicies> carga = new ArrayList();
    private List<String> historicoMedidas;   // Guarda textos como "Peso: 80kg..."
    private List<Avaliacao> listaAvaliacoes;


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
    }

    public Double getAltura() {
        return altura;
    }

    public Double getPeso() {
        return peso;
    }


    // --- FUNCIONALIDADES ---
    // cadastrar exercicios
    public void registrarCarga(String nome, double peso, int reps) {
        Pessoa.exercicies carga = new exercicies(nome, peso, reps);
    }


    public double calcularIMC() {
        if (altura != null && altura > 0) {
            return peso / (altura * altura);
        }
        return 0.0;
    }

    public void atualizarMedidas(double peso, double braco, double cintura, double altura) {
        medidas.add(peso + braco + cintura + altura);
    }

    public String getMedida() {
        return "Medida: " + medidas;
    }

    public void visualizarFichaTreino() {
        System.out.println("=== Ficha de Treino de " + getNome() + " ===");
        if (fichasTreino.isEmpty()) {
            System.out.println("Nenhuma ficha cadastrada.");
        } else {
            for (FichaTreino f : fichasTreino) {
                System.out.println("Ficha ID: " + f.getId());
            }
        }
    }

    public boolean solicitarAgendamento(Profissional personal, Agendamento novoAgendamento) {
        if (personal == null || novoAgendamento == null) return false;
        // Tenta marcar na agenda do profissional
        boolean agendou = personal.marcarHr(this, novoAgendamento);

        if (agendou) {
            this.meusAgendamentos.add(novoAgendamento);
            System.out.println("Agendamento confirmado e salvo no histórico do cliente.");
            return true;
        } else {
            System.out.println("Não foi possível agendar (Horário indisponível).");
            return false;
        }
    }

    public void cancelarAgendamento(Agendamento agendamento) {
        if (meusAgendamentos.contains(agendamento)) {
            agendamento.cancelarAgendamento();
            System.out.println("Agendamento cancelado.");
        } else {
            System.out.println("Este agendamento não foi encontrado na sua lista.");
        }
    }

    public void adicionarFicha(FichaTreino novaFicha) {
        if (this.fichasTreino == null) {
            this.fichasTreino = new ArrayList<>();
        }
        this.fichasTreino.add(novaFicha);
    }

    public void verProgresso() {
        System.out.println("===Meu Progresso");

        System.out.println("=== ATUAL ===");
        System.out.println("Aluno: " + getNome());
        System.out.println("Objetivo: " + this.objetivo);
        System.out.println("Peso Atual: " + this.peso + "kg");
        System.out.printf("IMC Atual: %.2f\n", calcularIMC());

        System.out.println("\n=== HISTÓRICO DE MEDIDAS ===");
        if (historicoMedidas == null || historicoMedidas.isEmpty()) {
            System.out.println(" - Nenhum registro de medidas feito por você ainda.");
        } else {
            for (String registro : historicoMedidas) {
                System.out.println(" - " + registro);
            }
        }

        System.out.println("\n=== AVALIAÇÕES FÍSICAS DO PERSONAL ===");
        if (listaAvaliacoes == null || listaAvaliacoes.isEmpty()) {
            System.out.println(" - Nenhuma avaliação profissional registrada.");
        } else {
            for (Avaliacao av : listaAvaliacoes) {
                System.out.println(" [Data: " + av.getData() + "]");
                System.out.println("   Nota/Resultado: " + av.getNota());
                System.out.println("   -----------------------------");
            }
        }
        System.out.println("========================================\n");
    }

    public void visualizarAvaliacoes() {
        System.out.println("=== Histórico de Avaliações Físicas de " + getNome() + " ===");
        if (listaAvaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação registrada pelo profissional.");
        } else {
            for (Avaliacao av : listaAvaliacoes) {
                // Acessando os dados da classe Avaliacao que você forneceu anteriormente
                System.out.println("Data: " + av.getData() +
                        " | Nota/Medida: " + av.getNota());
            }
        }
    }

    public void adicionarAvaliacao(Avaliacao novaAvaliacao) {

    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Nome: " + getNome() + " | Objetivo: " + this.objetivo;
    }
}