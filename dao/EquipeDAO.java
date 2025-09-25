package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.Equipe;

public class EquipeDAO {

    // Método para salvar uma nova equipe
    public void salvar(Equipe equipe) {
        String sql = "INSERT INTO equipes (nome) VALUES (?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, equipe.getNome());
            stmt.executeUpdate();

            System.out.println("Equipe '" + equipe.getNome() + "' salva com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar a equipe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para buscar uma equipe por ID
    public Equipe buscarPorId(int id) {
        String sql = "SELECT * FROM equipes WHERE id = ?";
        Equipe equipe = null;

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    equipe = new Equipe(
                            rs.getInt("id"),
                            rs.getString("nome")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar equipe: " + e.getMessage());
            e.printStackTrace();
        }
        return equipe;
    }

    // Método para listar todas as equipes
    public List<Equipe> listarTodos() {
        String sql = "SELECT * FROM equipes";
        List<Equipe> equipes = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipe equipe = new Equipe(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
                equipes.add(equipe);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar equipes: " + e.getMessage());
            e.printStackTrace();
        }
        return equipes;
    }

    // Método para atualizar uma equipe
    public void atualizar(Equipe equipe) {
        String sql = "UPDATE equipes SET nome = ? WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, equipe.getNome());
            stmt.setInt(2, equipe.getId());
            stmt.executeUpdate();

            System.out.println("Equipe '" + equipe.getNome() + "' atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a equipe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para deletar uma equipe
    public void deletar(int id) {
        String sql = "DELETE FROM equipes WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Equipe com ID " + id + " deletada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar a equipe: " + e.getMessage());
            e.printStackTrace();
        }
    }
}