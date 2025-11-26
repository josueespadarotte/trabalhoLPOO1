package classes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import classes.Cliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Profissional extends Pessoa{
    private String especialidade;
    private Integer numeroRegistro;
    private Double avaliacao;
    private String disponibilidade;
    private double valorAula;

    public List<Agendamento> getAgenda() {
        return this.agenda;
    }

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
                        String especialidade, Integer numeroRegistro, double valorAula) {
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

    public void cadastrarCliente(Cliente cliente) {
        if (cliente != null && !listaClientes.contains(cliente)){
            listaClientes.add(cliente);
            System.out.println("Cliente " + cliente.getNome() + " cadastrado com sucesso.");
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

    public double calcularValorAula(int dias) {
        return this.valorAula * dias;
    }

    public void salvarFichaTreinoEmTxt(Cliente aluno) {
        String nomePasta = "fichas_de_treino";
        // 2. Define o caminho, nome da pasta e do cliente
        String nomeArquivo = nomePasta + "/" + aluno.getNome().replaceAll("\\s+", "_") + "_ficha.txt";

        try {
            // 3. Cria o objeto File para a pasta
            File pasta = new File(nomePasta);

            // 4. verifica se a pasta existe, se não, cria
            if (!pasta.exists()) {
                if (pasta.mkdirs()) {
                    System.out.println("Diretório criado: " + nomePasta);
                } else {
                    System.out.println("Erro ao criar o diretório: " + nomePasta);
                    return; // cancela o salvamento
                }
            }

            FileWriter writer = new FileWriter(nomeArquivo);
            writer.write("=== FICHA DE TREINO - " + aluno.getNome() + " ===\n");
            writer.write("Objetivo: " + aluno.getObjetivo() + "\n");
            writer.write("Restrição Física: " + aluno.getRestricaoFisica() + "\n");
            writer.write("----------------------------------------\n");

            if (aluno.carga.isEmpty()) {
                writer.write("Nenhum exercício cadastrado.");
            } else {
                for (Pessoa.exercicies exercicio : aluno.carga) {
                    writer.write(exercicio.getNomeex() +
                            " | Peso: " + exercicio.getPesoex() +
                            " | Reps: " + exercicio.getReps() + "\n");
                }
            }

            writer.close();
            System.out.println("Ficha de treino de " + aluno.getNome() + " salvo em: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao salvar a ficha de treino: " + e.getMessage());
        }
    }
    @Override
    public String toString() {
        return "Profissional{" +
                "especialidade='" + especialidade + '\'' +
                ", numeroRegistro=" + numeroRegistro +
                ", avaliacao=" + avaliacao +
                ", disponibilidade='" + disponibilidade + '\'' +
                ", valorAula=" + valorAula +
                ", agenda=" + agenda +
                ", listaClientes=" + listaClientes +
                "} " + super.toString();

    }
    public void verPerfilProf(){
    System.out.println("\n=== PERFIL PROFISSIONAL ===");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("CPF: " + getCpf());
        System.out.println("Especialidade: " + this.especialidade);
        System.out.println("Registro (CREF): " + this.numeroRegistro);
        System.out.printf("Valor da Aula: R$ %.2f\n", this.valorAula);
        System.out.println("Disponibilidade: " + (this.disponibilidade != null ? this.disponibilidade : "Não informada"));

        int qtdAlunos = (listaClientes != null) ? listaClientes.size() : 0;
        System.out.println("Alunos Vinculados: " + qtdAlunos);

        if (this.avaliacao != null) {
        System.out.println("Avaliação Média: " + this.avaliacao);
    }
        System.out.println("==============================\n");
    }
}