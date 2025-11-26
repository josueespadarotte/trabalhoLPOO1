package dados;

import classes.Cliente;
import classes.Pessoa;
import classes.Profissional;
import java.io.*;
import java.util.*;

public class LeDados {
    private Scanner entrada;

    public LeDados(String nome) throws FileNotFoundException {
        try {
            this.entrada = new Scanner(new File(nome));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("ARQUIVO NAO ENCONTRADO");
        }
    }

    public List<Pessoa> leArquivo() throws NoSuchElementException, NumberFormatException {
        List<Pessoa> lista = new ArrayList<>();
        String linha;

        while (this.entrada.hasNext()) {
            linha = this.entrada.nextLine();
            // Evita linhas vazias
            if (!linha.trim().isEmpty()) {
                Pessoa p = separaDados(linha);
                if (p != null) {
                    lista.add(p);
                }
            }
        }
        return lista;
    }

    private Pessoa separaDados(String linha) {
        String[] dados = linha.split(";");
        // Verifica se a linha tem dados suficientes (segurança básica)
        if (dados.length < 8) return null;

        char tipo = dados[0].charAt(0);

        try {
            int id = Integer.parseInt(dados[1]);
            String nome = dados[2];
            String cpf = dados[3];
            String telefone = dados[4];
            String email = dados[5];
            String senha = dados[6];
            String sexo = dados[7];

            switch (tipo) {
                case 'C': // Cliente
                    // 8:IDADE, 9:PESO, 10:ALTURA, 11:OBJETIVO, 12:RESTRICAO
                    int idade = Integer.parseInt(dados[8]);
                    double peso = Double.parseDouble(dados[9]);
                    double altura = Double.parseDouble(dados[10]);
                    String objetivo = dados[11];
                    String restricao = (dados.length > 12) ? dados[12] : "Nenhuma";

                    return new Cliente(id, nome, cpf, telefone, email, senha, sexo, idade, peso, altura, objetivo, restricao);

                case 'P': // Profissional
                    // 8:ESPECIALIDADE, 9:REGISTRO, 10:VALOR
                    String especialidade = dados[8];
                    int registro = Integer.parseInt(dados[9]);
                    double valor = Double.parseDouble(dados[10]);

                    return new Profissional(id, nome, cpf, telefone, email, senha, sexo, especialidade, registro, valor);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler linha: " + linha + " -> " + e.getMessage());
        }
        return null;
    }

    public void fechaArquivo() {
        if (this.entrada != null) {
            this.entrada.close();
        }
    }
}
