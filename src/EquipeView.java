package src;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.EquipeDAO;
import java.util.List;

public class EquipeView extends JFrame {

    private EquipeDAO equipeDAO = new EquipeDAO();

    private JTextField idField, nomeField;
    private JButton salvarButton, buscarButton, atualizarButton, deletarButton, listarTodosButton, voltarButton;
    private JTextArea displayArea;

    public EquipeView() {
        // Configuração da janela
        setTitle("Gestão de Equipes");
        setSize(600, 400); // Tamanho menor
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Painel para os campos de input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5));

        // Campos de entrada
        idField = new JTextField(20);
        nomeField = new JTextField(20);

        // Adicionando labels e campos ao painel
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);

        add(inputPanel);

        // Painel para os botões de ação
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        salvarButton = new JButton("Salvar");
        buscarButton = new JButton("Buscar");
        atualizarButton = new JButton("Atualizar");
        deletarButton = new JButton("Deletar");
        listarTodosButton = new JButton("Listar Todos");
        voltarButton = new JButton("Voltar");

        buttonPanel.add(salvarButton);
        buttonPanel.add(buscarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(deletarButton);
        buttonPanel.add(listarTodosButton);
        buttonPanel.add(voltarButton);

        add(buttonPanel);

        // Criando a área de exibição
        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane);

        // Adicionando os ActionListeners aos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeField.getText();
                    Equipe novaEquipe = new Equipe(0, nome);
                    equipeDAO.salvar(novaEquipe);
                    limparCampos();
                    displayMessage("Equipe '" + nome + "' salva com sucesso!");
                } catch (Exception ex) {
                    displayMessage("Erro ao salvar a equipe: " + ex.getMessage());
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Equipe equipeBuscada = equipeDAO.buscarPorId(id);
                    if (equipeBuscada != null) {
                        nomeField.setText(equipeBuscada.getNome());
                        displayMessage("Equipe com ID " + id + " encontrada!");
                    } else {
                        limparCampos();
                        displayMessage("Equipe com ID " + id + " não encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao buscar equipe: " + ex.getMessage());
                }
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String nome = nomeField.getText();
                    Equipe equipeAtualizada = new Equipe(id, nome);
                    equipeDAO.atualizar(equipeAtualizada);
                    limparCampos();
                    displayMessage("Equipe com ID " + id + " atualizada com sucesso!");
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao atualizar a equipe: " + ex.getMessage());
                }
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    equipeDAO.deletar(id);
                    limparCampos();
                    displayMessage("Equipe com ID " + id + " deletada com sucesso!");
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao deletar a equipe: " + ex.getMessage());
                }
            }
        });

        listarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayArea.setText("");
                    List<Equipe> equipes = equipeDAO.listarTodos();
                    if (equipes.isEmpty()) {
                        displayArea.append("Nenhuma equipe encontrada.\n");
                    } else {
                        displayArea.append("--- Lista de Equipes ---\n");
                        for (Equipe equipe : equipes) {
                            displayArea.append("ID: " + equipe.getId() + ", Nome: " + equipe.getNome() + "\n\n");
                        }
                    }
                } catch (Exception ex) {
                    displayMessage("Erro ao listar equipes: " + ex.getMessage());
                }
            }
        });

        voltarButton.addActionListener(e -> {
            this.dispose();
        });

        setVisible(true);
    }

    private void limparCampos() {
        idField.setText("");
        nomeField.setText("");
    }

    private void displayMessage(String message) {
        displayArea.append(message + "\n");
    }
}