package src;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class Projeto {
    private int id;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String status;
    private List<Equipe> equipes;

    public Projeto(int id, String nome, String descricao, String dataInicio, String dataFim, String status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.equipes = new ArrayList<>(); // Inicializamos a lista no construtor
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public String getStatus() {
        return status;
    }

    public List<Equipe> getEquipes() { // Getter para a lista de equipes
        return equipes;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Método para adicionar uma equipe ao projeto
    public void adicionarEquipe(Equipe equipe) {
        this.equipes.add(equipe);
    }

    public static class Main {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new LoginView();
            });
        }

        public static void criarEMostrarMenu() {
            JFrame menuFrame = new JFrame("Menu Principal");
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setSize(400, 150);
            menuFrame.setLayout(new FlowLayout());

            JButton usuariosButton = new JButton("Gerenciar Usuários");
            usuariosButton.addActionListener(e -> {
                new UsuarioView();
            });

            JButton projetosButton = new JButton("Gerenciar Projetos");
            projetosButton.addActionListener(e -> {
                new ProjetoView();
            });

            JButton equipesButton = new JButton("Gerenciar Equipes");
            equipesButton.addActionListener(e -> {
                new EquipeView();
            });

            menuFrame.add(usuariosButton);
            menuFrame.add(projetosButton);
            menuFrame.add(equipesButton);

            menuFrame.setLocationRelativeTo(null);
            menuFrame.setVisible(true);
        }
    }
}