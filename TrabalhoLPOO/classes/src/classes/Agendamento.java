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
    private String personal;
    private String tipoSessao; // Treino, Avaliação, Consultoria
    private Status status;

    public Agendamento(String data, String horario, Cliente cliente, String personal,
                       String tipoSessao) {

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

    //           GETTERS

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Status getStatus() {
        return status;
    }
}