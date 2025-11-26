package persistencia;

import classes.Cliente;
import classes.Pessoa;
import classes.Profissional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private final String NOME_ARQUIVO = "cadastros.txt";
    private final String DELIMITADOR = ";";

    /**
     * Salva a lista de cadastros no arquivo de texto.
     * @param cadastros A lista de Pessoa (Cliente e Profissional) a ser salva.
     */
    public void salvar(List<Pessoa> cadastros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Pessoa pessoa : cadastros) {
                String linha = formatarParaPersistencia(pessoa);
                if (!linha.isEmpty()) {
                    writer.write(linha);
                    writer.newLine();
                }
            }
            System.out.println(" Dados salvos em " + NOME_ARQUIVO + " com sucesso.");
        } catch (IOException e) {
            System.err.println(" Erro ao salvar dados: " + e.getMessage());
        }
    }

    /*
     Carrega a lista de cadastros do arquivo de texto.
     retorna Uma lista de Pessoa (Cliente e Profissional) carregada do arquivo.
     */
    public List<Pessoa> carregar() {
        List<Pessoa> cadastrosCarregados = new ArrayList<>();
        // Tenta criar o arquivo se ele não existir
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Pessoa pessoa = parseLinhaParaObjeto(linha);
                if (pessoa != null) {
                    cadastrosCarregados.add(pessoa);
                }
            }
            System.out.println("Dados carregados de " + NOME_ARQUIVO + " com sucesso.");
        } catch (IOException e) {
            System.err.println("Arquivo de dados não encontrado ou erro ao carregar. Iniciando com lista vazia. Erro: " + e.getMessage());
        }
        return cadastrosCarregados;
    }

    // metodos p auxilio da formatação

    private String formatarParaPersistencia(Pessoa pessoa) {
        // Atributos base: ID;NOME;CPF;TELEFONE;EMAIL;SENHA;SEXO
        String base = pessoa.getId() + DELIMITADOR + pessoa.getNome() + DELIMITADOR + pessoa.getCpf() +
                DELIMITADOR + pessoa.getTelefone() + DELIMITADOR + pessoa.getEmail() + DELIMITADOR +
                pessoa.getSenha() + DELIMITADOR + pessoa.getSexo();

        if (pessoa instanceof Cliente) {
            Cliente c = (Cliente) pessoa;
            return "CLIENTE" + DELIMITADOR + base + DELIMITADOR + c.getIdade() + DELIMITADOR + c.getPeso() +
                    DELIMITADOR + c.getAltura() + DELIMITADOR + c.getObjetivo() + DELIMITADOR + c.getRestricaoFisica();

        } else if (pessoa instanceof Profissional) {
            Profissional p = (Profissional) pessoa;
            return "PROFISSIONAL" + DELIMITADOR + base + DELIMITADOR + p.getEspecialidade() + DELIMITADOR +
                    p.getNumeroRegistro() + DELIMITADOR + p.getValorAula();
        }
        return ""; // Caso não seja Cliente nem Profissional
    }

    private Pessoa parseLinhaParaObjeto(String linha) {
        String[] partes = linha.split(DELIMITADOR);

        if (partes.length < 8) {
            return null;
        }

        String tipo = partes[0];
        try {
            // Extração dos atributos base (índices 1 a 7)
            int id = Integer.parseInt(partes[1]);
            String nome = partes[2];
            String cpf = partes[3];
            String telefone = partes[4];
            String email = partes[5];
            String senha = partes[6];
            String sexo = partes[7];

            if (tipo.equals("CLIENTE") && partes.length >= 13) {
                // Atributos de Cliente: IDADE(8), PESO(9), ALTURA(10), OBJETIVO(11), RESTRICAO(12)
                int idade = Integer.parseInt(partes[8]);
                double peso = Double.parseDouble(partes[9]);
                double altura = Double.parseDouble(partes[10]);
                String objetivo = partes[11];
                String restricaoFisica = partes[12];

                return new Cliente(id, nome, cpf, telefone, email, senha, sexo, idade, peso, altura, objetivo, restricaoFisica);

            } else if (tipo.equals("PROFISSIONAL") && partes.length >= 11) {
                // Atributos de Profissional: ESPECIALIDADE(8), NUMEROREGISTRO(9), VALORAULA(10)
                String especialidade = partes[8];
                int registro = Integer.parseInt(partes[9]);
                double valorAula = Double.parseDouble(partes[10]);

                return new Profissional(id, nome, cpf, telefone, email, senha, sexo, especialidade, registro, valorAula);
            }

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Erro de parsing de linha (formato inválido): " + linha);
        }
        return null;
    }
}