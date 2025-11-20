package classes;

public class Avaliacao {
    private Integer nota;
    private String comentario;
    private String data;

    public Avaliacao(int nota, String comentario, String data) {
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
    }

    public Integer getNota() {
        return nota;
    }
    public void setNota(Integer nota) {
        this.nota = nota;
    }
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


