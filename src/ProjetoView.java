package src;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.ProjetoDAO;
import java.util.List;

public class ProjetoView extends JFrame {

    private ProjetoDAO projetoDAO = new ProjetoDAO();

    private JTextField idField, nomeField, descricaoField, dataInicioField, dataFimField, statusField;
    private JButton salvarButton, buscarButton, atualizarButton, deletarButton, listarTodosButton, voltarButton;
    private JTextArea displayArea;

    public ProjetoView() {

        setTitle("Gestão de Projetos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 5, 5));


        idField = new JTextField(20);
        nomeField = new JTextField(20);
        descricaoField = new JTextField(20);
        dataInicioField = new JTextField(20);
        dataFimField = new JTextField(20);
        statusField = new JTextField(20);


        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Descrição:"));
        inputPanel.add(descricaoField);
        inputPanel.add(new JLabel("Data Início (YYYY-MM-DD):"));
        inputPanel.add(dataInicioField);
        inputPanel.add(new JLabel("Data Fim (YYYY-MM-DD):"));
        inputPanel.add(dataFimField);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(statusField);

        add(inputPanel);


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
                    String descricao = descricaoField.getText();
                    String dataInicio = dataInicioField.getText();
                    String dataFim = dataFimField.getText();
                    String status = statusField.getText();

                    Projeto novoProjeto = new Projeto(0, nome, descricao, dataInicio, dataFim, status);

                    projetoDAO.salvar(novoProjeto);

                    limparCampos();
                    displayMessage("Projeto '" + nome + "' salvo com sucesso!");
                } catch (Exception ex) {
                    displayMessage("Erro ao salvar o projeto: " + ex.getMessage());
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Projeto projetoBuscado = projetoDAO.buscarPorId(id);

                    if (projetoBuscado != null) {
                        nomeField.setText(projetoBuscado.getNome());
                        descricaoField.setText(projetoBuscado.getDescricao());
                        dataInicioField.setText(projetoBuscado.getDataInicio());
                        dataFimField.setText(projetoBuscado.getDataFim());
                        statusField.setText(projetoBuscado.getStatus());
                        displayMessage("Projeto com ID " + id + " encontrado!");
                    } else {
                        limparCampos();
                        displayMessage("Projeto com ID " + id + " não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao buscar projeto: " + ex.getMessage());
                }
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String nome = nomeField.getText();
                    String descricao = descricaoField.getText();
                    String dataInicio = dataInicioField.getText();
                    String dataFim = dataFimField.getText();
                    String status = statusField.getText();

                    Projeto projetoAtualizado = new Projeto(id, nome, descricao, dataInicio, dataFim, status);

                    projetoDAO.atualizar(projetoAtualizado);

                    limparCampos();
                    displayMessage("Projeto com ID " + id + " atualizado com sucesso!");
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao atualizar o projeto: " + ex.getMessage());
                }
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    projetoDAO.deletar(id);

                    limparCampos();
                    displayMessage("Projeto com ID " + id + " deletado com sucesso!");
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao deletar o projeto: " + ex.getMessage());
                }
            }
        });

        listarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayArea.setText("");
                    List<Projeto> projetos = projetoDAO.listarTodos();

                    if (projetos.isEmpty()) {
                        displayArea.append("Nenhum projeto encontrado.\n");
                    } else {
                        displayArea.append("--- Lista de Projetos ---\n");
                        for (Projeto projeto : projetos) {
                            displayArea.append("ID: " + projeto.getId() + ", Nome: " + projeto.getNome() + "\n");
                            displayArea.append("Descrição: " + projeto.getDescricao() + "\n");
                            displayArea.append("Início: " + projeto.getDataInicio() + ", Fim: " + projeto.getDataFim() + "\n");
                            displayArea.append("Status: " + projeto.getStatus() + "\n\n");
                        }
                    }
                } catch (Exception ex) {
                    displayMessage("Erro ao listar projetos: " + ex.getMessage());
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
        descricaoField.setText("");
        dataInicioField.setText("");
        dataFimField.setText("");
        statusField.setText("");
    }

    private void displayMessage(String message) {
        displayArea.append(message + "\n");
    }
}