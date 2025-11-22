package classes;

public class Pagamento {

    public enum StatusPagamento {
        PENDENTE,
        CONFIRMADO,
        CANCELADO
    }

    public enum MetodoPagamento {
        DINHEIRO,
        CARTAO,
        PIX,
        BOLETO
    }

    public enum StatusAssinatura {
        ATIVA,
        CANCELADA,
        NAO_ASSINADO
    }

    private int idPagamento;
    private double valor;
    private String dataPagamento;
    private StatusPagamento statusPagamento;
    private MetodoPagamento metodoPagamento;

    // Associação com plano
    private Plano planoAssinado;
    private StatusAssinatura statusAssinatura;

    public Pagamento(int idPagamento, double valor, MetodoPagamento metodoPagamento, String dataPagamento) {
        this.idPagamento = idPagamento;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.statusAssinatura = StatusAssinatura.NAO_ASSINADO;
    }

    // ================================
    //     MÉTODOS DE PAGAMENTO
    // ================================

    public void confirmarPagamento() {
        this.statusPagamento = StatusPagamento.CONFIRMADO;
    }

    public void cancelarPagamento() {
        this.statusPagamento = StatusPagamento.CANCELADO;
    }

    public boolean isConfirmado() {
        return this.statusPagamento == StatusPagamento.CONFIRMADO;
    }

    // ================================
    //       MÉTODOS DE ASSINATURA
    // ================================

    public void assinarPlano(Plano plano) {
        if (this.statusPagamento != StatusPagamento.CONFIRMADO) {
            System.out.println("Pagamento ainda não confirmado. Não é possível assinar o plano.");
            return;
        }

        this.planoAssinado = plano;
        this.statusAssinatura = StatusAssinatura.ATIVA;
        System.out.println("Plano " + plano.getNome() + " assinado com sucesso!");
    }

    public void cancelarAssinatura() {
        if (this.statusAssinatura == StatusAssinatura.ATIVA) {
            this.statusAssinatura = StatusAssinatura.CANCELADA;
            System.out.println("Assinatura cancelada.");
        } else {
            System.out.println("Nenhuma assinatura ativa para cancelar.");
        }
    }

    public StatusAssinatura verStatusAssinatura() {
        return this.statusAssinatura;
    }

    // ================================
    //           GETTERS
    // ================================

    public int getIdPagamento() {
        return idPagamento;
    }

    public double getValor() {
        return valor;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public Plano getPlanoAssinado() {
        return planoAssinado;
    }
}