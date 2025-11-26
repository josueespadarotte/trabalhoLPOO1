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
    private final String SEPARADOR = ";";

    //lista com nomes e atributos de todos os cadastros do sistema

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


    public List<Pessoa> carregar() {
        List<Pessoa> cadastrosCarregados = new ArrayList<>();
        //  criaa o arquivo se não tiver
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

    // metodos auxiliares de formatação
    private String extrairValor(String dadoComRotulo) {
        if (dadoComRotulo != null && dadoComRotulo.contains(":")) {
            String[] partes = dadoComRotulo.split(":");
            if (partes.length > 1) {
                return partes[1].trim();
            }
            return "";
        }
        return dadoComRotulo != null ? dadoComRotulo.trim() : "";
    }

    private String formatarParaPersistencia(Pessoa pessoa) {
        String base = "ID: " + pessoa.getId() + SEPARADOR +
                "Nome: " + pessoa.getNome() + SEPARADOR +
                "CPF: " + pessoa.getCpf() + SEPARADOR +
                "Tel: " + pessoa.getTelefone() + SEPARADOR +
                "Email: " + pessoa.getEmail() + SEPARADOR +
                "Senha: " + pessoa.getSenha() + SEPARADOR +
                "Sexo: " + pessoa.getSexo();

        if (pessoa instanceof Cliente) {
            Cliente c = (Cliente) pessoa;
            return "TIPO: CLIENTE" + SEPARADOR + base + SEPARADOR +
                    "Idade: " + c.getIdade() + SEPARADOR +
                    "Peso: " + c.getPeso() + SEPARADOR +
                    "Altura: " + c.getAltura() + SEPARADOR +
                    "Obj: " + c.getObjetivo() + SEPARADOR +
                    "Restricao: " + c.getRestricaoFisica();

        } else if (pessoa instanceof Profissional) {
            Profissional p = (Profissional) pessoa;
            return "TIPO: PROFISSIONAL" + SEPARADOR + base + SEPARADOR +
                    "Espec: " + p.getEspecialidade() + SEPARADOR +
                    "CREF: " + p.getNumeroRegistro() + SEPARADOR +
                    "Valor: " + p.getValorAula();
        }
        return "";
    }

    private Pessoa parseLinhaParaObjeto(String linha) {
        String[] partes = linha.split(SEPARADOR);

        if (partes.length < 8) {
            return null;
        }

        try {
            String tipo = extrairValor(partes[0]);

            int id = Integer.parseInt(extrairValor(partes[1]));
            String nome = extrairValor(partes[2]);
            String cpf = extrairValor(partes[3]);
            String telefone = extrairValor(partes[4]);
            String email = extrairValor(partes[5]);
            String senha = extrairValor(partes[6]);
            String sexo = extrairValor(partes[7]);

            if (tipo.equals("CLIENTE") && partes.length >= 13) {
                // atributos de Cliente: IDADE(8), PESO(9), ALTURA(10), OBJETIVO(11), RESTRICAO(12)
                int idade = Integer.parseInt(extrairValor(partes[8]));
                double peso = Double.parseDouble(extrairValor(partes[9]));
                double altura = Double.parseDouble(extrairValor(partes[10]));
                String objetivo = extrairValor(partes[11]);
                String restricaoFisica = extrairValor(partes[12]);

                return new Cliente(id, nome, cpf, telefone, email, senha, sexo, idade, peso, altura, objetivo, restricaoFisica);

            } else if (tipo.equals("PROFISSIONAL") && partes.length >= 11) {
                // Atributos de Profissional: ESPECIALIDADE(8), NUMEROREGISTRO(9), VALORAULA(10)
                String especialidade = extrairValor(partes[8]);
                int registro = Integer.parseInt(extrairValor(partes[9]));
                double valorAula = Double.parseDouble(extrairValor(partes[10]));

                return new Profissional(id, nome, cpf, telefone, email, senha, sexo, especialidade, registro, valorAula);
            }

        } catch (NumberFormatException e) {
            System.err.println("formato inválido: " + linha);
        }
        return null;
    }
}