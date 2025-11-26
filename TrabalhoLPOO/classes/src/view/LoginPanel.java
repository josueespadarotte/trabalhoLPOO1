package view;

import classes.Pessoa;
import classes.Cliente;
import classes.Profissional;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private SistemaGymGui frame;
    private JTextField txtEmail;
    private JPasswordField txtSenha;

    public LoginPanel(SistemaGymGui frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255)); // Cor de fundo suave

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("Bem-vindo ao GymSystem");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        // Campo Email
        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Email:"), gbc);

        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        // Campo Senha
        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Senha:"), gbc);

        txtSenha = new JPasswordField(20);
        gbc.gridx = 1;
        add(txtSenha, gbc);

        // Botão Entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(70, 130, 180));
        btnEntrar.setForeground(Color.WHITE);
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        add(btnEntrar, gbc);

        // Botão Cadastrar
        JButton btnCadastrar = new JButton("Não tem conta? Cadastre-se");
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.setForeground(Color.BLUE);
        gbc.gridy = 4;
        add(btnCadastrar, gbc);

        // Ações
        btnEntrar.addActionListener(e -> realizarLogin());
        btnCadastrar.addActionListener(e -> frame.mostrarTela(SistemaGymGui.TELA_CADASTRO));
    }

    private void realizarLogin() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        boolean loginSucesso = false;

        for (Pessoa p : frame.getCadastros()) {
            if (p.getEmail().equals(email) && p.verificarSenha(senha)) {
                loginSucesso = true;
                frame.setUsuarioLogado(p);

                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

                if (p instanceof Cliente) {
                    frame.mostrarTela(SistemaGymGui.TELA_MENU_CLIENTE);
                } else if (p instanceof Profissional) {
                    frame.mostrarTela(SistemaGymGui.TELA_MENU_PERSONAL);
                }
                break;
            }
        }

        if (!loginSucesso) {
            JOptionPane.showMessageDialog(this, "Email ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}