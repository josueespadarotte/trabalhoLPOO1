package view;

import classes.Cliente;
import javax.swing.*;
import java.awt.*;

public class MenuClientePanel extends JPanel {
    private SistemaGymGui frame;
    private Cliente clienteLogado;

    public MenuClientePanel(SistemaGymGui frame) {
        this.frame = frame;
        this.clienteLogado = (Cliente) frame.getUsuarioLogado();

        setLayout(new BorderLayout());

        // Cabeçalho
        JPanel header = new JPanel();
        header.setBackground(new Color(100, 149, 237));
        JLabel lblBemVindo = new JLabel("Bem-vindo(a), " + clienteLogado.getNome());
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 18));
        header.add(lblBemVindo);
        add(header, BorderLayout.NORTH);

        // Botões do Menu
        JPanel menuGrid = new JPanel(new GridLayout(2, 3, 10, 10));
        menuGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgendar = criarBotaoMenu("Agendar Personal");
        JButton btnTreinos = criarBotaoMenu("Meus Treinos");
        JButton btnCarga = criarBotaoMenu("Registrar Carga");
        JButton btnMedidas = criarBotaoMenu("Minhas Medidas");
        JButton btnPerfil = criarBotaoMenu("Meu Perfil");
        JButton btnSair = criarBotaoMenu("Sair");
        btnSair.setBackground(new Color(205, 92, 92));

        menuGrid.add(btnAgendar);
        menuGrid.add(btnTreinos);
        menuGrid.add(btnCarga);
        menuGrid.add(btnMedidas);
        menuGrid.add(btnPerfil);
        menuGrid.add(btnSair);

        add(menuGrid, BorderLayout.CENTER);

        // Ações Simplificadas
        btnSair.addActionListener(e -> {
            frame.setUsuarioLogado(null);
            frame.mostrarTela(SistemaGymGui.TELA_LOGIN);
        });

        btnPerfil.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Dados do Cliente:\n" +
                            "Nome: " + clienteLogado.getNome() + "\n" +
                            "Email: " + clienteLogado.getEmail() + "\n" +
                            "Objetivo: " + clienteLogado.getObjetivo()
            );
        });

        btnTreinos.addActionListener(e -> {
            // Exemplo simples de como chamar os métodos existentes
            try {
                // Redirecionando a saída padrão para pegar o texto do console seria complexo,
                // então aqui mostramos apenas uma mensagem fixa, mas chamamos a lógica.
                JOptionPane.showMessageDialog(this, "Verifique o console ou a pasta 'fichas_de_treino' para detalhes.");
                clienteLogado.lerFichaTreinoDoArquivo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao ler ficha.");
            }
        });
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        return btn;
    }
}