package src;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.UsuarioDAO;
import java.util.List;

public class UsuarioView extends JFrame {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private JTextField idField, nomeCompletoField, loginField, senhaField, perfilField, cargoField, emailField;
    private JButton salvarButton, buscarButton, atualizarButton, deletarButton, listarTodosButton, voltarButton;
    private JTextArea displayArea;

    public UsuarioView() {

        setTitle("Gestão de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 5, 5));

        idField = new JTextField(20);
        nomeCompletoField = new JTextField(20);
        loginField = new JTextField(20);
        senhaField = new JTextField(20);
        perfilField = new JTextField(20);
        cargoField = new JTextField(20);
        emailField = new JTextField(20);

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nome Completo:"));
        inputPanel.add(nomeCompletoField);
        inputPanel.add(new JLabel("Login:"));
        inputPanel.add(loginField);
        inputPanel.add(new JLabel("Senha:"));
        inputPanel.add(senhaField);
        inputPanel.add(new JLabel("Perfil:"));
        inputPanel.add(perfilField);
        inputPanel.add(new JLabel("Cargo:"));
        inputPanel.add(cargoField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        add(inputPanel);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        salvarButton = new JButton("Salvar");
        buscarButton = new JButton("Buscar");
        atualizarButton = new JButton("Atualizar");
        deletarButton = new JButton("Deletar");
        listarTodosButton = new JButton("Listar Todos");
        voltarButton = new JButton("Voltar"); // Correção aqui

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


        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeCompleto = nomeCompletoField.getText();
                    String login = loginField.getText();
                    String senha = senhaField.getText();
                    String perfil = perfilField.getText();
                    String cargo = cargoField.getText();
                    String email = emailField.getText();
                    Usuario novoUsuario = new Usuario(0, nomeCompleto, login, senha, perfil, cargo, email);
                    usuarioDAO.salvar(novoUsuario);
                    limparCampos();
                    displayMessage("Usuário '" + nomeCompleto + "' salvo com sucesso!");
                } catch (Exception ex) {
                    displayMessage("Erro ao salvar o usuário: " + ex.getMessage());
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Usuario usuarioBuscado = usuarioDAO.buscarPorId(id);
                    if (usuarioBuscado != null) {
                        nomeCompletoField.setText(usuarioBuscado.getNomeCompleto());
                        loginField.setText(usuarioBuscado.getLogin());
                        senhaField.setText(usuarioBuscado.getSenha());
                        perfilField.setText(usuarioBuscado.getPerfil());
                        cargoField.setText(usuarioBuscado.getCargo());
                        emailField.setText(usuarioBuscado.getEmail());
                        displayMessage("Usuário com ID " + id + " encontrado!");
                    } else {
                        limparCampos();
                        displayMessage("Usuário com ID " + id + " não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao buscar usuário: " + ex.getMessage());
                }
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String nomeCompleto = nomeCompletoField.getText();
                    String login = loginField.getText();
                    String senha = senhaField.getText();
                    String perfil = perfilField.getText();
                    String cargo = cargoField.getText();
                    String email = emailField.getText();
                    Usuario usuarioAtualizado = new Usuario(id, nomeCompleto, login, senha, perfil, cargo, email);
                    usuarioDAO.atualizar(usuarioAtualizado);
                    limparCampos();
                    displayMessage("Usuário com ID " + id + " atualizado com sucesso!");
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao atualizar o usuário: " + ex.getMessage());
                }
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    usuarioDAO.deletar(id);
                    limparCampos();
                    displayMessage("Usuário com ID " + id + " deletado com sucesso!");
                } catch (NumberFormatException ex) {
                    displayMessage("Erro: O ID deve ser um número válido.");
                } catch (Exception ex) {
                    displayMessage("Erro ao deletar o usuário: " + ex.getMessage());
                }
            }
        });

        listarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayArea.setText("");
                    List<Usuario> usuarios = usuarioDAO.listarTodos();
                    if (usuarios.isEmpty()) {
                        displayArea.append("Nenhum usuário encontrado.\n");
                    } else {
                        displayArea.append("--- Lista de Usuários ---\n");
                        for (Usuario usuario : usuarios) {
                            displayArea.append("ID: " + usuario.getId() + ", Nome: " + usuario.getNomeCompleto() + "\n");
                            displayArea.append("Login: " + usuario.getLogin() + ", Perfil: " + usuario.getPerfil() + "\n");
                            displayArea.append("Cargo: " + usuario.getCargo() + ", Email: " + usuario.getEmail() + "\n\n");
                        }
                    }
                } catch (Exception ex) {
                    displayMessage("Erro ao listar usuários: " + ex.getMessage());
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
        nomeCompletoField.setText("");
        loginField.setText("");
        senhaField.setText("");
        perfilField.setText("");
        cargoField.setText("");
        emailField.setText("");
    }

    private void displayMessage(String message) {
        displayArea.append(message + "\n");
    }
}