package view;

import classes.Cliente;
import classes.Pessoa;
import classes.Profissional;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CadastroPanel extends JPanel {
    private SistemaGymGui frame;

    // Campos Comuns
    private JTextField txtNome, txtCpf, txtTelefone, txtEmail;
    private JPasswordField txtSenha;
    private JComboBox<String> cbSexo;
    private JRadioButton rbAluno, rbPersonal;

    // Painéis Específicos
    private JPanel cardPanelEspecifico;
    private CardLayout cardLayoutEspecifico;

    // Campos Aluno
    private JTextField txtIdade, txtPeso, txtAltura, txtObjetivo, txtRestricao;

    // Campos Personal
    private JTextField txtCref, txtEspecialidade, txtValorAula;

    public CadastroPanel(SistemaGymGui frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // --- Topo: Título e Seleção de Tipo ---
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel lblTitulo = new JLabel("Novo Cadastro", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(lblTitulo);

        JPanel typePanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        rbAluno = new JRadioButton("Aluno", true);
        rbPersonal = new JRadioButton("Personal Trainer");
        group.add(rbAluno);
        group.add(rbPersonal);
        typePanel.add(rbAluno);
        typePanel.add(rbPersonal);
        topPanel.add(typePanel);
        add(topPanel, BorderLayout.NORTH);

        // --- Centro: Formulário ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dados Pessoais (Comuns)
        addCampo(formPanel, gbc, 0, "Nome:", txtNome = new JTextField(20));
        addCampo(formPanel, gbc, 1, "CPF:", txtCpf = new JTextField(15));
        addCampo(formPanel, gbc, 2, "Telefone:", txtTelefone = new JTextField(15));
        addCampo(formPanel, gbc, 3, "Email:", txtEmail = new JTextField(20));
        addCampo(formPanel, gbc, 4, "Senha:", txtSenha = new JPasswordField(15));

        String[] sexos = {"M", "F"};
        addCampo(formPanel, gbc, 5, "Sexo:", cbSexo = new JComboBox<>(sexos));

        // Área Dinâmica (Aluno vs Personal)
        cardLayoutEspecifico = new CardLayout();
        cardPanelEspecifico = new JPanel(cardLayoutEspecifico);

        // Painel Aluno
        JPanel pnlAluno = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlAluno.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
        pnlAluno.add(new JLabel("Idade:")); pnlAluno.add(txtIdade = new JTextField());
        pnlAluno.add(new JLabel("Peso (kg):")); pnlAluno.add(txtPeso = new JTextField());
        pnlAluno.add(new JLabel("Altura (m):")); pnlAluno.add(txtAltura = new JTextField());
        pnlAluno.add(new JLabel("Objetivo:")); pnlAluno.add(txtObjetivo = new JTextField());
        pnlAluno.add(new JLabel("Restrição Física:")); pnlAluno.add(txtRestricao = new JTextField("Nenhuma"));

        // Painel Personal
        JPanel pnlPersonal = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlPersonal.setBorder(BorderFactory.createTitledBorder("Dados do Profissional"));
        pnlPersonal.add(new JLabel("CREF:")); pnlPersonal.add(txtCref = new JTextField());
        pnlPersonal.add(new JLabel("Especialidade:")); pnlPersonal.add(txtEspecialidade = new JTextField());
        pnlPersonal.add(new JLabel("Valor Aula (R$):")); pnlPersonal.add(txtValorAula = new JTextField());

        cardPanelEspecifico.add(pnlAluno, "ALUNO");
        cardPanelEspecifico.add(pnlPersonal, "PERSONAL");

        gbc.gridy = 6; gbc.gridwidth = 2;
        formPanel.add(cardPanelEspecifico, gbc);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        // --- Rodapé: Botões ---
        JPanel botPanel = new JPanel();
        JButton btnSalvar = new JButton("Salvar Cadastro");
        JButton btnVoltar = new JButton("Voltar");

        btnSalvar.setBackground(new Color(60, 179, 113));
        btnSalvar.setForeground(Color.WHITE);

        botPanel.add(btnVoltar);
        botPanel.add(btnSalvar);
        add(botPanel, BorderLayout.SOUTH);

        // Listeners
        rbAluno.addActionListener(e -> cardLayoutEspecifico.show(cardPanelEspecifico, "ALUNO"));
        rbPersonal.addActionListener(e -> cardLayoutEspecifico.show(cardPanelEspecifico, "PERSONAL"));

        btnVoltar.addActionListener(e -> frame.mostrarTela(SistemaGymGui.TELA_LOGIN));
        btnSalvar.addActionListener(e -> salvarCadastro());
    }

    private void addCampo(JPanel p, GridBagConstraints gbc, int y, String label, JComponent comp) {
        gbc.gridy = y;
        gbc.gridx = 0; gbc.gridwidth = 1;
        p.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        p.add(comp, gbc);
    }

    private void salvarCadastro() {
        try {
            int id = new Random().nextInt(9000) + 1000;
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String tel = txtTelefone.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());
            String sexo = (String) cbSexo.getSelectedItem();

            if (rbAluno.isSelected()) {
                int idade = Integer.parseInt(txtIdade.getText());
                double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));
                double altura = Double.parseDouble(txtAltura.getText().replace(",", "."));
                String obj = txtObjetivo.getText();
                String rest = txtRestricao.getText();

                Cliente c = new Cliente(id, nome, cpf, tel, email, senha, sexo, idade, peso, altura, obj, rest);
                frame.getCadastros().add(c);
            } else {
                int cref = Integer.parseInt(txtCref.getText());
                String espec = txtEspecialidade.getText();
                double valor = Double.parseDouble(txtValorAula.getText().replace(",", "."));

                Profissional p = new Profissional(id, nome, cpf, tel, email, senha, sexo, espec, cref, valor);
                frame.getCadastros().add(p);
            }

            frame.salvarDados(); // Salva no TXT via DAO
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            frame.mostrarTela(SistemaGymGui.TELA_LOGIN);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique se os campos numéricos estão corretos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}