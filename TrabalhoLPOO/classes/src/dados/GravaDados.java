package dados;

import classes.Cliente;
import classes.Pessoa;
import classes.Profissional;

import java.io.*;
import java.util.List;

public class GravaDados {
    private FileWriter arquivo;
    private PrintWriter saida;

    public GravaDados(String nome) throws IOException {
        try {
            // false = sobrescreve o arquivo toda vez que salvar a lista completa
            arquivo = new FileWriter(new File(nome), false);
            saida = new PrintWriter(arquivo);
        } catch (IOException e) {
            throw new IOException("ARQUIVO NAO PODE SER ABERTO PARA GRAVACAO");
        }
    }

    public void gravarLista(List<Pessoa> lista) {
        for (Pessoa p : lista) {
            if (p instanceof Cliente) {
                this.saida.println(((Cliente) p).toStringArquivo());
            } else if (p instanceof Profissional) {
                this.saida.println(((Profissional) p).toStringArquivo());
            }
        }
    }

    public void fechaArquivo() throws IOException {
        try {
            this.saida.close();
            this.arquivo.close();
        } catch (IOException e) {
            throw new IOException("ERRO AO FECHAR O ARQUIVO");
        }
    }
}