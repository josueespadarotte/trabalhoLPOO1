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
    private List<Agendamento> meusAgendamentos; // Lista solicitada no comentário

    public Cliente(Integer id, String nome, String cpf, String telefone, String email,
                   String senha, String sexo, Integer idade, Double peso, Double altura, String objetivo,
                   String restricaoFisica) {
        super(id, nome, cpf, telefone, email, senha, sexo);
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.restricaoFisica = restricaoFisica;
        // Inicializa as listas vazias. Não precisa passar no construtor.
        this.fichasTreino = new ArrayList<>();
        this.meusAgendamentos = new ArrayList<>();
    }

    public Double getAltura() {
        return altura;
    }
    public Double getPeso() {
        return peso;
    }


    // --- FUNCIONALIDADES ---

    public double calcularIMC() {
        if (altura != null && altura > 0) {
            return peso / (altura * altura);
        }
        return 0.0;
    }

    public void visualizarFichaTreino() {
        System.out.println("=== Ficha de Treino de " + getNome() + " ===");
        if (fichasTreino.isEmpty()) {
            System.out.println("Nenhuma ficha cadastrada.");
        } else {
            for (FichaTreino f : fichasTreino) {
                // Certifique-se que FichaTreino tenha um método toString() útil ou use f.getId()
                System.out.println("Ficha ID: " + f.getId());
            }
        }
    }

    // O cliente solicita o agendamento ao Profissional
    public boolean solicitarAgendamento(Profissional personal, Agendamento novoAgendamento) {
        if (personal == null || novoAgendamento == null) return false;

        // Tenta marcar na agenda do profissional
        boolean agendou = personal.marcarHr(this, novoAgendamento);

        if (agendou) {
            // Se deu certo, salva na lista do cliente também
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

    // Método auxiliar para o sistema adicionar fichas ao cliente
    public void adicionarFicha(FichaTreino ficha) {
        this.fichasTreino.add(ficha);
    }
}