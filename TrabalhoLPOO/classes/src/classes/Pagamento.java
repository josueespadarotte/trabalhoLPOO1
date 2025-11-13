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
}

