package classes;

import java.util.Date;

public class Avaliacao {
    private Integer nota;
    private Date data;
    private int qtdAvaliacao;

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






}


