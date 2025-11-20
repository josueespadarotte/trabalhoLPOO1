package classes;

public class Pagamento {
    private double valor;
    private String dataPagamento;
    private String status;
    private String metodoPagamento;

    public Pagamento(double valor, String dataPagamento, String status, String metodoPagamento) {
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.metodoPagamento = metodoPagamento;
    }

    // Métodos úteis
    public void confirmarPagamento() {
        this.status = "Confirmado";
    }

    public void cancelarPagamento() {
        this.status = "Cancelado";
    }
    public double getValor() {
        return valor;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}


