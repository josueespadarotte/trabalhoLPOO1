package view;

import classes.Profissional;
import javax.swing.*;
import java.awt.*;

public class MenuPersonalPanel extends JPanel {
    private SistemaGymGui frame;
    private Profissional personalLogado;

    public MenuPersonalPanel(SistemaGymGui frame) {
        this.frame = frame;
        this.personalLogado = (Profissional) frame.getUsuarioLogado();

        setLayout(new BorderLayout());

        // Cabeçalho
        JPanel header = new JPanel();
        header.setBackground(new Color(46, 139, 87));
        JLabel lblBemVindo = new JLabel("Área do Personal: " + personalLogado.getNome());
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 18));
        header.add(lblBemVindo);
        add(header, BorderLayout.NORTH);

        // Botões
        JPanel menuGrid = new JPanel(new GridLayout(2, 2, 10, 10));
        menuGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAlunos = criarBotaoMenu("Ver Alunos");
        JButton btnAgenda = criarBotaoMenu("Gerenciar Agenda");
        JButton btnPerfil = criarBotaoMenu("Meu Perfil");
        JButton btnSair = criarBotaoMenu("Sair");
        btnSair.setBackground(new Color(205, 92, 92));

        menuGrid.add(btnAlunos);
        menuGrid.add(btnAgenda);
        menuGrid.add(btnPerfil);
        menuGrid.add(btnSair);

        add(menuGrid, BorderLayout.CENTER);

        // Ações
        btnSair.addActionListener(e -> {
            frame.setUsuarioLogado(null);
            frame.mostrarTela(SistemaGymGui.TELA_LOGIN);
        });

        btnPerfil.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Perfil Profissional:\n" +
                            "Nome: " + personalLogado.getNome() + "\n" +
                            "CREF: " + personalLogado.getNumeroRegistro() + "\n" +
                            "Especialidade: " + personalLogado.getEspecialidade() + "\n" +
                            "Valor Hora: R$ " + personalLogado.getValorAula()
            );
        });

        btnAlunos.addActionListener(e -> {
            // Em uma implementação completa, abriria uma JTable com os alunos
            JOptionPane.showMessageDialog(this, "Funcionalidade de listar alunos (Ver console para logs do sistema antigo).");
            personalLogado.listarClientes();
        });
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        return btn;
    }
}