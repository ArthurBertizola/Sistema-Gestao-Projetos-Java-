package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.Usuario;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome_completo, login, senha, perfil, cargo, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Define os valores de cada '?' usando os dados do objeto Usuario
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil());
            stmt.setString(5, usuario.getCargo());
            stmt.setString(6, usuario.getEmail());

            // Executa o comando SQL
            stmt.executeUpdate();

            System.out.println("Usuário salvo com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o usuário: " + e.getMessage());
            e.printStackTrace();
        }

    }
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id); // Define o ID que será buscado

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Mapeia o resultado da consulta para um objeto Usuario
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome_completo"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getString("perfil"),
                            rs.getString("cargo"),
                            rs.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            e.printStackTrace();
        }

        return usuario;
    }
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Mapeia cada linha do resultado para um objeto Usuario
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome_completo"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("perfil"),
                        rs.getString("cargo"),
                        rs.getString("email")
                );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }

        return usuarios;
    }
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome_completo = ?, login = ?, senha = ?, perfil = ?, cargo = ?, email = ? WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil());
            stmt.setString(5, usuario.getCargo());
            stmt.setString(6, usuario.getEmail());
            stmt.setInt(7, usuario.getId()); // O ID é usado no WHERE para encontrar o usuário

            stmt.executeUpdate();

            System.out.println("Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Usuário com ID " + id + " deletado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar o usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Usuario autenticar(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        Usuario usuario = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome_completo"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getString("perfil"),
                            rs.getString("cargo"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}