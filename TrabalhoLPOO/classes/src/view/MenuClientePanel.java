package view;

import classes.Agendamento;
import classes.Cliente;
import classes.Pessoa;
import classes.Profissional;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuClientePanel extends JPanel {
    private SistemaGymGui frame;
    private Cliente clienteLogado;

    public MenuClientePanel(SistemaGymGui frame) {
        this.frame = frame;
        this.clienteLogado = (Cliente) frame.getUsuarioLogado();

        setLayout(new BorderLayout());

        //Cabeçalho
        JPanel header = new JPanel();
        header.setBackground(new Color(100, 149, 237)); // Azul Cornflower
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblBemVindo = new JLabel("Painel do Aluno: " + clienteLogado.getNome());
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(lblBemVindo);
        add(header, BorderLayout.NORTH);

        // Grid de Botões
        JPanel menuGrid = new JPanel(new GridLayout(2, 3, 15, 15));
        menuGrid.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        menuGrid.setBackground(Color.WHITE);

        JButton btnAgendar = criarBotaoMenu("Agendar Personal");
        JButton btnTreinos = criarBotaoMenu("Meus Treinos");
        JButton btnCarga = criarBotaoMenu("Registrar Carga");
        JButton btnMedidas = criarBotaoMenu("Minhas Medidas");
        JButton btnPerfil = criarBotaoMenu("Meu Perfil");
        JButton btnSair = criarBotaoMenu("Sair");


        btnSair.setBackground(new Color(220, 53, 69));
        btnSair.setForeground(Color.WHITE);

        menuGrid.add(btnAgendar);
        menuGrid.add(btnTreinos);
        menuGrid.add(btnCarga);
        menuGrid.add(btnMedidas);
        menuGrid.add(btnPerfil);
        menuGrid.add(btnSair);

        add(menuGrid, BorderLayout.CENTER);


        // Agendar Personal
        btnAgendar.addActionListener(e -> abrirAgendamento());

        // visualizar Treinos
        btnTreinos.addActionListener(e -> verTreinos());

        //Registrar Carga
        btnCarga.addActionListener(e -> registrarCarga());

        // Minhas Medidas (Mostra dados do objeto)
        btnMedidas.addActionListener(e -> verMedidas());

        // Perfil
        btnPerfil.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "DADOS CADASTRAIS\n\n" +
                            "Nome: " + clienteLogado.getNome() + "\n" +
                            "CPF: " + clienteLogado.getCpf() + "\n" +
                            "Email: " + clienteLogado.getEmail() + "\n" +
                            "Telefone: " + clienteLogado.getTelefone(),
                    "Meu Perfil",
                    JOptionPane.INFORMATION_MESSAGE
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
    private void abrirAgendamento() {
        // 1. Filtrar apenas os Profissionais da lista geral de cadastros
        List<Profissional> personais = new ArrayList<>();
        for (Pessoa p : frame.getCadastros()) {
            if (p instanceof Profissional) {
                personais.add((Profissional) p);
            }
        }

        if (personais.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há personais cadastrados no sistema.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Criar componentes para o diálogo de agendamento
        // Converte a lista para array para usar no ComboBox
        JComboBox<Profissional> cmbPersonais = new JComboBox<>(personais.toArray(new Profissional[0]));

        cmbPersonais.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Profissional) {
                    Profissional p = (Profissional) value;
                    // Formata o texto aqui: Nome - CREF - Valor
                    setText(String.format("%s - CREF: %d - R$ %.2f", p.getNome(), p.getNumeroRegistro(), p.getValorAula()));
                }
                return this;
            }
        });

        JTextField txtData = new JTextField(10); // Ex: 25/12/2023
        JTextField txtHora = new JTextField(8);  // Ex: 14:00

        String[] tipos = {"Treino", "Avaliação"};
        JComboBox<String> cmbTipo = new JComboBox<>(tipos);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Escolha o Personal:"));
        panel.add(cmbPersonais);
        panel.add(new JLabel("Data (dd/mm/aaaa):"));
        panel.add(txtData);
        panel.add(new JLabel("Horário (hh:mm):"));
        panel.add(txtHora);
        panel.add(new JLabel("Tipo de Sessão:"));
        panel.add(cmbTipo);

        int result = JOptionPane.showConfirmDialog(null, panel, "Agendar Aula", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Profissional personalSelecionado = (Profissional) cmbPersonais.getSelectedItem();
            String data = txtData.getText();
            String hora = txtHora.getText();
            String tipo = (String) cmbTipo.getSelectedItem();

            if (personalSelecionado != null && !data.isEmpty() && !hora.isEmpty()) {
                // Cria o  agendamento
                Agendamento novoAgendamento = new Agendamento(
                        data, hora, clienteLogado, personalSelecionado.getNome(), tipo
                );

                // Chama o método da sua lógica de negócios
                boolean sucesso = clienteLogado.solicitarAgendamento(personalSelecionado, novoAgendamento);

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Agendamento realizado com sucesso com " + personalSelecionado.getNome() + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível agendar. Verifique a disponibilidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void verTreinos() {
        // ler o arquivo e exibe na tela
        String nomeSanitizado = clienteLogado.getNome().replaceAll("\\s+", "_");
        String caminhoArquivo = "fichas_de_treino/" + nomeSanitizado + "_ficha.txt";

        File arquivo = new File(caminhoArquivo);
        StringBuilder conteudo = new StringBuilder();

        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    conteudo.append(linha).append("\n");
                }
            } catch (IOException e) {
                conteudo.append("Erro ao ler arquivo: ").append(e.getMessage());
            }
        } else {
            conteudo.append("Nenhuma ficha de treino encontrada para este aluno.\n")
                    .append("Peça ao seu personal para criar uma ficha.");
        }

        //barra de rolagem para mostrar o treino
        JTextArea textArea = new JTextArea(conteudo.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setMargin(new Insets(10,10,10,10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Minha Ficha de Treino", JOptionPane.PLAIN_MESSAGE);
    }

    private void registrarCarga() {
        JTextField txtExercicio = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtReps = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Nome do Exercício:"));
        panel.add(txtExercicio);
        panel.add(new JLabel("Carga (kg):"));
        panel.add(txtPeso);
        panel.add(new JLabel("Repetições:"));
        panel.add(txtReps);

        int result = JOptionPane.showConfirmDialog(null, panel, "Registrar Carga", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String exercicio = txtExercicio.getText();
                double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));
                int reps = Integer.parseInt(txtReps.getText());

                // 1. Atualiza na memória do objeto Cliente
                clienteLogado.registrarCarga(exercicio, peso, reps);

                // 2. Atualiza o arquivo TXT para salvar a alteração
                atualizarArquivoFicha(clienteLogado);

                JOptionPane.showMessageDialog(this, "Carga registrada e salva com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite números válidos para carga e repetições.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarArquivoFicha(Cliente aluno) {
        String nomePasta = "fichas_de_treino";
        String nomeArquivo = nomePasta + "/" + aluno.getNome().replaceAll("\\s+", "_") + "_ficha.txt";

        try {
            File pasta = new File(nomePasta);
            if (!pasta.exists()) pasta.mkdirs();

            // Sobrescreve o arquivo com os dados atuais da lista de cargas
            FileWriter writer = new FileWriter(nomeArquivo, false);

            writer.write("=== FICHA DE TREINO - " + aluno.getNome() + " ===\n");
            writer.write("Objetivo: " + aluno.getObjetivo() + "\n");
            writer.write("Restrição Física: " + aluno.getRestricaoFisica() + "\n");
            writer.write("----------------------------------------\n");

            if (aluno.carga.isEmpty()) {
                writer.write("Nenhum exercício cadastrado.\n");
            } else {
                for (Pessoa.exercicies ex : aluno.carga) {
                    writer.write(ex.getNomeex() + " | Peso: " + ex.getPesoex() + "kg | Reps: " + ex.getReps() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void verMedidas() {
        // Monta um texto formatado com os dados
        StringBuilder sb = new StringBuilder();
        sb.append("--- MINHAS MEDIDAS ---\n\n");
        sb.append(String.format("Idade: %d anos\n", clienteLogado.getIdade()));
        sb.append(String.format("Peso Atual: %.2f kg\n", clienteLogado.getPeso()));
        sb.append(String.format("Altura: %.2f m\n", clienteLogado.getAltura()));

        // Calcula IMC simples
        if (clienteLogado.getAltura() > 0) {
            double imc = clienteLogado.getPeso() / (clienteLogado.getAltura() * clienteLogado.getAltura());
            sb.append(String.format("IMC: %.2f\n", imc));
        }

        sb.append("\nObjetivo: ").append(clienteLogado.getObjetivo());
        sb.append("\nRestrições: ").append(clienteLogado.getRestricaoFisica());

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setMargin(new Insets(15, 15, 15, 15));

        JOptionPane.showMessageDialog(this, textArea, "Avaliação Física", JOptionPane.PLAIN_MESSAGE);
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