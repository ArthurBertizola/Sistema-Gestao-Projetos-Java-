package src;

import javax.swing.*;
import java.awt.FlowLayout;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView(); // Inicia o programa com a tela de login
        });
    }

    public static void criarEMostrarMenu(Usuario usuarioLogado) {
        JFrame menuFrame = new JFrame("Menu Principal");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(500, 150); // Aumenta o tamanho para mais botões
        menuFrame.setLayout(new FlowLayout());

        JButton usuariosButton = new JButton("Gerenciar Usuários");
        JButton projetosButton = new JButton("Gerenciar Projetos");
        JButton equipesButton = new JButton("Gerenciar Equipes");
        JButton logoutButton = new JButton("Sair"); // Novo botão de logout


        if (usuarioLogado.getPerfil().equalsIgnoreCase("admin")) {
            menuFrame.add(usuariosButton);
            menuFrame.add(projetosButton);
            menuFrame.add(equipesButton);
        } else {
            JLabel welcomeLabel = new JLabel("Bem-vindo, " + usuarioLogado.getNomeCompleto() + "!");
            menuFrame.add(welcomeLabel);
            menuFrame.add(new JLabel("Apenas usuários administradores podem gerenciar."));
        }

        menuFrame.add(logoutButton);

        // ActionListeners para os botões
        usuariosButton.addActionListener(e -> {
            new UsuarioView();
        });

        projetosButton.addActionListener(e -> {
            new ProjetoView();
        });

        equipesButton.addActionListener(e -> {
            new EquipeView();
        });

        logoutButton.addActionListener(e -> {
            menuFrame.dispose(); // Fecha a janela do menu
            new LoginView(); // Abre a tela de login novamente
        });

        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }
}