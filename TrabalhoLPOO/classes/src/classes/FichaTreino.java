package classes;

import java.util.List;

public class FichaTreino {
    private int idFicha;
    private String Exercicios;
    private List lista;

    public FichaTreino(int id, String Exercicios) {
        this.idFicha = id;
        this.Exercicios = Exercicios;
    }
    public int getId() {
        return idFicha;
    }
    public void setId(int id) {
        this.idFicha = id;
    }
    public String getExercicios() {
        return Exercicios;
    }
    public void setExercicios(String Exercicios) {
        this.Exercicios = Exercicios;
    }

    public void registrarCarga(String nomeExercicio, double cargaUsada){

    }
}

