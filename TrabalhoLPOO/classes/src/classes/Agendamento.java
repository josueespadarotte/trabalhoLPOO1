package classes;

public class Agendamento {
    private String data;
    private String horarioInicio;
    private String horarioFim;
    private String modalidade;
    private String status;

    public Agendamento(String data, String inicio, String fim, String modalidade, String status) {
        this.data = data;
        this.horarioInicio = inicio;
        this.horarioFim = fim;
        this.modalidade = modalidade;
        this.status = status;
    }

    // metodos de alteração de status
    public void confirmar() {
        this.status = "Confirmado";
    }

    public void cancelar() {
        this.status = "Cancelado";
    }

     public String getData() {
        return data;
    }

    //gets

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public String getStatus() {
        return status;
    }

    public String getModalidade() {
        return modalidade;
    }
       



}



