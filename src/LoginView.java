package src;

import javax.swing.*;
import java.awt.*;
import dao.UsuarioDAO;


public class LoginView extends JFrame {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private JTextField loginField;
    private JPasswordField senhaField;
    private JButton loginButton;

    public LoginView() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Login:"));
        loginField = new JTextField(15);
        panel.add(loginField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField(15);
        panel.add(senhaField);

        loginButton = new JButton("Entrar");
        panel.add(new JLabel("")); // Espaço vazio
        panel.add(loginButton);

        add(panel);

        // Adicionando a lógica do botão
        loginButton.addActionListener(e -> {
            String login = loginField.getText();
            String senha = new String(senhaField.getPassword());

            Usuario usuario = usuarioDAO.autenticar(login, senha);

            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem-vindo, " + usuario.getNomeCompleto() + ".");

                // Fecha a tela de login
                dispose();

                // Abre o menu principal
                Main.criarEMostrarMenu(usuario);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas. Tente novamente.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}