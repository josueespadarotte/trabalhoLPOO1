package classes;

public class Agendamento {

    public enum Status {
        AGENDADO,
        CONFIRMADO,
        CANCELADO,
        CONCLUIDO
    }

    private int id;
    private String data;
    private String horario;
    private Cliente cliente;
    private Profissional personal;
    private String tipoSessao; // Treino, Avaliação, Consultoria
    private Status status;

    public Agendamento(int id, String data, String horario, Cliente cliente, Profissional personal,
                       String tipoSessao) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.cliente = cliente;
        this.personal = personal;
        this.tipoSessao = tipoSessao;
        this.status = Status.AGENDADO;
    }

    //        MÉTODOS DE STATUS

    public void confirmarAgendamento() {
        if (this.status != Status.AGENDADO) {
            System.out.println("Agendamento só pode ser confirmado se estiver no status AGENDADO.");
            return;
        }
        this.status = Status.CONFIRMADO;
    }

    public void cancelarAgendamento() {
        if (this.status == Status.CANCELADO) {
            System.out.println("Agendamento já está cancelado.");
            return;
        }
        this.status = Status.CANCELADO;
    }

    public void concluirAgendamento() {
        if (this.status != Status.CONFIRMADO) {
            System.out.println("Agendamento só pode ser concluído se estiver confirmado.");
            return;
        }
        this.status = Status.CONCLUIDO;
    }

    public void editarAgendamento(String novaData, String novoHorario, String novoTipoSessao) {
        if (this.status == Status.CANCELADO || this.status == Status.CONCLUIDO) {
            System.out.println("Não é possível editar agendamento cancelado ou concluído.");
            return;
        }
        this.data = novaData;
        this.horario = novoHorario;
        this.tipoSessao = novoTipoSessao;
    }

    //       MÉTODOS DE VALIDAÇÃO

    public boolean verificarDisponibilidade() {
        // Aqui você pode implementar checagem de conflito no banco de dados ou lista
        return true; // por enquanto sempre disponível
    }

    public boolean validarHorario() {
        // Implementar regras de horário (ex: horário comercial)
        return true; // por enquanto válido
    }

    // ===============================
    // MÉTODOS RELACIONADOS AO CLIENTE
    // ===============================

    public void solicitarAgendamento(String data, String horario, String tipoSessao) {
        this.data = data;
        this.horario = horario;
        this.tipoSessao = tipoSessao;
        this.status = Status.AGENDADO;
    }

    public void cancelarAgendamento(int id) {
        if (this.id == id) {
            cancelarAgendamento();
        } else {
            System.out.println("ID do agendamento não corresponde.");
        }
    }

    //           GETTERS
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Profissional getPersonal() {
        return personal;
    }

    public String getTipoSessao() {
        return tipoSessao;
    }

    public Status getStatus() {
        return status;
    }
}