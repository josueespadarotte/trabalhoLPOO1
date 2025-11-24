package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Profissional extends Pessoa{
    private String especialidade;
    private Integer numeroRegistro;
    private Double avaliacao;
    private String disponibilidade;
    private double valorAula;

    private List<Avaliacao> avAlunos;


    public class exercicies{
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
        public double getPesoex() {
            return pesoex;
        }
        public int getReps() {
            return reps;
        }

        public String imprimirList(){
            return getNomeex() + "kg " + getPesoex() + "reps: " +getReps();
        }
    }

    public Profissional(Integer id, String nome, String cpf, String telefone, String email, String senha, String sexo,
                        String especialidade, Integer numeroRegistro) {
        super(id, nome, cpf, telefone, email, senha, sexo);
        this.especialidade = especialidade;
        this.numeroRegistro = numeroRegistro;
        this.avaliacao = avaliacao;
        this.disponibilidade = disponibilidade;
        this.valorAula = valorAula;
        this.listaClientes = new ArrayList<>();
        this.agenda = new ArrayList<>();

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

    public double getValorAula() {
        return valorAula;
    }

    public void setValorAula(double valorAula) {
        this.valorAula = valorAula;
    }

     private List<Agendamento> agenda;
    private List<Cliente> listaClientes;
    private List<Avaliacao> historicoAvaliacoes;

    public boolean marcarHr(Cliente c, Agendamento ag) {
        if(estaDisponivel(ag.getData(), ag.getHorario())){
            ag.confirmarAgendamento();
            this.agenda.add(ag);
            cadastrarCliente(c);
        System.out.println("Agendamento do cliente " + c.getNome() + "para " + ag.getData() +
                " hora " + ag.getHorario());
        return true;
        }
        System.out.println("Horário indisponivel.");
        return false;
    }

    public void cancelarHr(Agendamento ag) {
        if (agenda.contains(ag)) {
            ag.cancelarAgendamento();
            // apagar do hist
            agenda.remove(ag);
            System.out.println("Agendamento cancelado.");
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }

    public boolean estaDisponivel(String data, String horario) {
        for (Agendamento ag : agenda) {
            // Verifica conflito de dia e hora
            if (ag.getData().equals(data) &&
                    ag.getHorario().equals(horario) &&
                    ag.getStatus() != Agendamento.Status.CANCELADO) {
                return false;
            }
        }
        return true;
    }

    public void listarHrDisponivel(String data) {
    }

    public void exibirAgenda() {
        System.out.println("=== Agenda Completa ===");
        if (agenda.isEmpty()) {
            System.out.println("Nenhum agendamento registrado.");
        } else {
            for (Agendamento ag : agenda) {
                System.out.println("Data: " + ag.getData() + " | Hora: " + ag.getHorario() +
                        " | Aluno: " + ag.getCliente().getNome() +
                        " | Status: " + ag.getStatus());
            }
        }
    }


    // gestao de cliente



    public void registrarCarga(String nome, double peso, int reps){
        Pessoa.exercicies carga = new Pessoa.exercicies(nome, peso, reps);
    }
    public void cadastrarCliente(Cliente cliente) {
        if (cliente != null && !listaClientes.contains(cliente)){
            listaClientes.add(cliente);
            System.out.println("Cliente " + cliente.getNome() + " cadastrado com sucesso.");
        }
    }

    public void removerCliente(Cliente cliente) {
        if (listaClientes.contains(cliente)) {
            listaClientes.remove(cliente);
            System.out.println("Cliente " + cliente.getNome() + " removido com sucesso.");
        }
    }

    public void listarClientes() {
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
    }

    public Cliente buscarClienteId(int id) {
        for (Cliente c : listaClientes) {
            if (c.getId().equals(id)) {
                System.out.println("Cliente encontrado.");
                return c;
            }
        }
        System.out.println("Cliente não encontrado.");
        return null;
    }


    // treinos e fichas
    public void criarFichaTreino(Cliente cliente, FichaTreino novaFicha) {
        if (cliente != null && novaFicha != null) {
            cliente.adicionarFicha(novaFicha);
            System.out.println("Ficha para " + cliente.getNome() + " criada com sucesso.");
            }
        }
    // PERFIL
    public void atualizarDisp(String novaDisponibilidade) {
        if (novaDisponibilidade != null && !novaDisponibilidade.isEmpty()) {
            this.disponibilidade = novaDisponibilidade;
            System.out.println("Disponibilidade atualizada para: " + novaDisponibilidade);
        }
    }

    public double calcularValorAula(int dias) {
        return this.valorAula * dias;
    }
    public void registrarAvaliacao(Cliente aluno, Avaliacao novaAvaliacao) {
        // Verifica se os objetos não são nulos antes de tentar salvar
        if (aluno != null && novaAvaliacao != null) {
            aluno.adicionarAvaliacao(novaAvaliacao); // falta fazer esse metodo
            System.out.println("Avaliação registrada com sucesso para " + aluno.getNome());
        } else {
            System.out.println("Erro: Aluno ou Avaliação inválidos.");
        }
    }

    public Cliente buscarAluno(String emailVerAval) {
        return null;
    }

    public void addFeedback(Avaliacao avaliacao) {
        this.avAlunos.add(avaliacao);
    }

    public void visualizarAvaliacoesDosAlunos() {
        System.out.println("\n=== FEEDBACKS DOS ALUNOS ===");

        if (avAlunos.isEmpty()) {
            System.out.println("Nenhuma avaliação recebida ainda.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Avaliacao av : avAlunos) {
                System.out.println("------------------------------------------------");
                System.out.println("Data: " + sdf.format(av.getData()));
                System.out.println("Nota: " + av.getNota() + "/5");
                System.out.println("Comentário: " + av.getComentario());
            }
            System.out.println("------------------------------------------------\n");
        }
    }
}


