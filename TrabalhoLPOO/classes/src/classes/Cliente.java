package classes;

public class Cliente extends Pessoa {
    private Integer idade;
    private Double peso;
    private Double altura;
    private String objetivo;
    private String restricaoFisica;

    public Cliente(String sexo, String nome, Integer id, String cpf, Integer telefone, String email,
                   Integer idade, Double peso, Double altura, String objetivo, String restricaoFisica) {
        super(sexo, nome, id, cpf, telefone, email);
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.restricaoFisica = restricaoFisica;
    }

    public Cliente(Integer idade, Double peso, Double altura, String objetivo, String restricaoFisica) {
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.restricaoFisica = restricaoFisica;
    }

    // metodo
    public double calcularIMC() {
        return peso / (altura * altura);
    }




























    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getRestricaoFisica() {
        return restricaoFisica;
    }

    public void setRestricaoFisica(String restricaoFisica) {
        this.restricaoFisica = restricaoFisica;
    }

    public void pagamento(){

    }






































}
