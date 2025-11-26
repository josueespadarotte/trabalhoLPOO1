package view;

import classes.Agendamento;
import classes.Cliente;
import classes.Pessoa;
import classes.Profissional;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuPersonalPanel extends JPanel {
    private SistemaGymGui frame;
    private Profissional personalLogado;

    public MenuPersonalPanel(SistemaGymGui frame) {
        this.frame = frame;
        this.personalLogado = (Profissional) frame.getUsuarioLogado();

        setLayout(new BorderLayout());

        //cabeçalho
        JPanel header = new JPanel();
        header.setBackground(new Color(46, 139, 87)); // SeaGreen
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblBemVindo = new JLabel("Área do Personal: " + personalLogado.getNome());
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(lblBemVindo);
        add(header, BorderLayout.NORTH);

        //grid de Botões
        JPanel menuGrid = new JPanel(new GridLayout(2, 2, 15, 15));
        menuGrid.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        menuGrid.setBackground(Color.WHITE);

        JButton btnAlunos = criarBotaoMenu("Ver Alunos");
        JButton btnAgenda = criarBotaoMenu("Gerenciar Agenda");
        JButton btnFicha = criarBotaoMenu("Criar Ficha de Treino"); // Novo botão
        JButton btnPerfil = criarBotaoMenu("Meu Perfil");
        JButton btnSair = criarBotaoMenu("Sair");

        //destaque para sair
        btnSair.setBackground(new Color(220, 53, 69));
        btnSair.setForeground(Color.WHITE);

        menuGrid.add(btnAlunos);
        menuGrid.add(btnAgenda);
        menuGrid.add(btnFicha); // Adicionado ao grid
        menuGrid.add(btnPerfil);
        menuGrid.add(btnSair);

        add(menuGrid, BorderLayout.CENTER);



        // ver Alunos
        btnAlunos.addActionListener(e -> listarAlunos());

        // gerenciar Agenda
        btnAgenda.addActionListener(e -> gerenciarAgenda());

        //criar Ficha de Treino
        btnFicha.addActionListener(e -> criarFichaTreino());

        // Perfil
        btnPerfil.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "PERFIL PROFISSIONAL\n\n" +
                            "Nome: " + personalLogado.getNome() + "\n" +
                            "CREF: " + personalLogado.getNumeroRegistro() + "\n" +
                            "Especialidade: " + personalLogado.getEspecialidade() + "\n" +
                            "Valor Hora: R$ " + personalLogado.getValorAula()
            );
        });

        // Sair
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.setUsuarioLogado(null);
                frame.mostrarTela(SistemaGymGui.TELA_LOGIN);
            }
        });
    }

    // funcionalidades

    private void listarAlunos() {
        // Obter lista de alunos cadastrados
        List<Cliente> alunos = new ArrayList<>();
        for (Pessoa p : frame.getCadastros()) {
            if (p instanceof Cliente) {
                alunos.add((Cliente) p);
            }
        }

        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum aluno cadastrado no sistema.");
            return;
        }

        // Criar tabela para exibir
        String[] colunas = {"ID", "Nome", "Idade", "Objetivo", "Restrições"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Cliente c : alunos) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getIdade(),
                    c.getObjetivo(),
                    c.getRestricaoFisica()
            });
        }

        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Lista de Alunos", JOptionPane.PLAIN_MESSAGE);
    }

    private void gerenciarAgenda() {
        List<Agendamento> agenda = personalLogado.getAgenda();

        if (agenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sua agenda está vazia.");
            return;
        }

        // Painel de gerenciamento
        JPanel panelAgenda = new JPanel(new BorderLayout(10, 10));

        String[] colunas = {"ID", "Data", "Hora", "Aluno", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        // Preencher tabela
        for (int i = 0; i < agenda.size(); i++) {
            Agendamento ag = agenda.get(i);
            model.addRow(new Object[]{
                    i, // índice  como ID temporário para facilitar
                    ag.getData(),
                    ag.getHorario(),
                    ag.getCliente().getNome(),
                    ag.getStatus()
            });
        }

        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panelAgenda.add(scrollPane, BorderLayout.CENTER);

        // Botões de ação
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnConcluir = new JButton("Concluir Aula");

        btnPanel.add(btnConfirmar);
        btnPanel.add(btnCancelar);
        btnPanel.add(btnConcluir);
        panelAgenda.add(btnPanel, BorderLayout.SOUTH);

        // botões da agenda
        btnConfirmar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row != -1) {
                Agendamento ag = agenda.get(row);
                ag.confirmarAgendamento();
                model.setValueAt(ag.getStatus(), row, 4); // Atualiza status na tabela
                JOptionPane.showMessageDialog(this, "Agendamento Confirmado!");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um agendamento na tabela.");
            }
        });

        btnCancelar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row != -1) {
                Agendamento ag = agenda.get(row);
                ag.cancelarAgendamento();
                model.setValueAt(ag.getStatus(), row, 4);
                JOptionPane.showMessageDialog(this, "Agendamento Cancelado.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um agendamento na tabela.");
            }
        });

        btnConcluir.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row != -1) {
                Agendamento ag = agenda.get(row);
                ag.concluirAgendamento();
                model.setValueAt(ag.getStatus(), row, 4);
                JOptionPane.showMessageDialog(this, "Aula Concluída!");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um agendamento na tabela.");
            }
        });

        // Mostrar janela
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Gerenciar Agenda", true);
        dialog.setContentPane(panelAgenda);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void criarFichaTreino() {
        // selecionar Aluno
        List<Cliente> alunos = new ArrayList<>();
        for (Pessoa p : frame.getCadastros()) {
            if (p instanceof Cliente) {
                alunos.add((Cliente) p);
            }
        }

        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum aluno cadastrado.");
            return;
        }

        JComboBox<Cliente> cmbAlunos = new JComboBox<>(alunos.toArray(new Cliente[0]));

        int resultAluno = JOptionPane.showConfirmDialog(this, cmbAlunos, "Selecione o Aluno", JOptionPane.OK_CANCEL_OPTION);

        if (resultAluno == JOptionPane.OK_OPTION) {
            Cliente alunoSelecionado = (Cliente) cmbAlunos.getSelectedItem();
            if (alunoSelecionado == null) return;

            // 2. Formulário para adicionar exercício
            JTextField txtExercicio = new JTextField();
            JTextField txtPeso = new JTextField();
            JTextField txtReps = new JTextField();

            JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
            panel.add(new JLabel("Exercício:")); panel.add(txtExercicio);
            panel.add(new JLabel("Carga (kg):")); panel.add(txtPeso);
            panel.add(new JLabel("Repetições:")); panel.add(txtReps);

            int resultTreino = JOptionPane.showConfirmDialog(this, panel, "Adicionar Exercício para " + alunoSelecionado.getNome(), JOptionPane.OK_CANCEL_OPTION);

            if (resultTreino == JOptionPane.OK_OPTION) {
                try {
                    String nomeEx = txtExercicio.getText();
                    double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));
                    int reps = Integer.parseInt(txtReps.getText());

                    if (nomeEx.isEmpty()) throw new Exception("Nome do exercício obrigatório");

                    // add em aluno
                    alunoSelecionado.registrarCarga(nomeEx, peso, reps);

                    // Salva no arquivo TXT
                    personalLogado.salvarFichaTreinoEmTxt(alunoSelecionado);

                    JOptionPane.showMessageDialog(this, "Exercício adicionado à ficha com sucesso!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Peso e Repetições devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(245, 245, 245));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Efeito hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!texto.equals("Sair")) btn.setBackground(new Color(230, 230, 250));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!texto.equals("Sair")) btn.setBackground(new Color(245, 245, 245));
            }
        });

        return btn;
    }
}