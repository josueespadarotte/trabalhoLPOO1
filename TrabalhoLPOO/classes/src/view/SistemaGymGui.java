package view;

import classes.Pessoa;
import classes.Cliente;
import classes.Profissional;
import persistencia.PessoaDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SistemaGymGui extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Pessoa> cadastros;
    private PessoaDAO pessoaDAO;
    private Pessoa usuarioLogado;

    // Identificadores das telas
    public static final String TELA_LOGIN = "LOGIN";
    public static final String TELA_CADASTRO = "CADASTRO";
    public static final String TELA_MENU_CLIENTE = "MENU_CLIENTE";
    public static final String TELA_MENU_PERSONAL = "MENU_PERSONAL";

    public SistemaGymGui() {
        super("Sistema de Academia - LPOO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Inicializar dados
        pessoaDAO = new PessoaDAO();
        cadastros = pessoaDAO.carregar();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adicionar as telas ao painel principal
        mainPanel.add(new LoginPanel(this), TELA_LOGIN);
        mainPanel.add(new CadastroPanel(this), TELA_CADASTRO);

        // Menus serão adicionados/atualizados dinamicamente ao logar
        mainPanel.add(new JPanel(), TELA_MENU_CLIENTE);
        mainPanel.add(new JPanel(), TELA_MENU_PERSONAL);

        add(mainPanel);
        mostrarTela(TELA_LOGIN);
    }

    public void mostrarTela(String nomeTela) {
        cardLayout.show(mainPanel, nomeTela);
    }

    // Métodos de Acesso aos Dados
    public List<Pessoa> getCadastros() {
        return cadastros;
    }

    public void salvarDados() {
        pessoaDAO.salvar(cadastros);
    }

    public void setUsuarioLogado(Pessoa pessoa) {
        this.usuarioLogado = pessoa;
        atualizarMenus();
    }

    public Pessoa getUsuarioLogado() {
        return usuarioLogado;
    }

    private void atualizarMenus() {
        // Recria os painéis de menu para garantir que mostrem os dados atuais
        if (usuarioLogado instanceof Cliente) {
            mainPanel.add(new MenuClientePanel(this), TELA_MENU_CLIENTE);
        } else if (usuarioLogado instanceof Profissional) {
            mainPanel.add(new MenuPersonalPanel(this), TELA_MENU_PERSONAL);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemaGymGui().setVisible(true);
        });
    }
}