package classes;

import java.util.Date;


public class Avaliacao {
    private Integer nota;
    private Date data;
    private int qtdAvaliacao;
    private String comentario;


    public Avaliacao(Integer nota, Date data, int qtdAvaliacao, String comentario) {
        this.nota = nota;
        this.data = data;
        this.qtdAvaliacao = qtdAvaliacao;
        this.comentario = comentario;
    }

    public Avaliacao(int nota, Date data) {
        this.nota = nota;
        this.data = data;
    }

    public Integer getNota() {
        return nota;
    }
    public void setNota(Integer nota) {
        qtdAvaliacao +=1;
        this.nota += nota ;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtdAvaliacao() {
        return qtdAvaliacao;
    }

    public void setQtdAvaliacao(int qtdAvaliacao) {
        this.qtdAvaliacao = qtdAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}


