package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import src.Projeto;

public class ProjetoDAO {
    public void salvar(Projeto projeto) {
        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_fim, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(4, Date.valueOf(projeto.getDataFim()));
            stmt.setString(5, projeto.getStatus());

            stmt.executeUpdate();
            System.out.println("Projeto '" + projeto.getNome() + "' salvo com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o projeto: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Projeto buscarPorId(int id) {
        String sql = "SELECT * FROM projetos WHERE id = ?";
        Projeto projeto = null;

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    projeto = new Projeto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getDate("data_inicio").toString(),
                            rs.getDate("data_fim").toString(),
                            rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar projeto: " + e.getMessage());
            e.printStackTrace();
        }
        return projeto;
    }
    public List<Projeto> listarTodos() {
        String sql = "SELECT * FROM projetos";
        List<Projeto> projetos = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = new Projeto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data_inicio").toString(),
                        rs.getDate("data_fim").toString(),
                        rs.getString("status")
                );
                projetos.add(projeto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
            e.printStackTrace();
        }
        return projetos;
    }
    public void atualizar(Projeto projeto) {
        String sql = "UPDATE projetos SET nome = ?, descricao = ?, data_inicio = ?, data_fim = ?, status = ? WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(4, Date.valueOf(projeto.getDataFim()));
            stmt.setString(5, projeto.getStatus());
            stmt.setInt(6, projeto.getId());

            stmt.executeUpdate();
            System.out.println("Projeto '" + projeto.getNome() + "' atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o projeto: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void deletar(int id) {
        String sql = "DELETE FROM projetos WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("Projeto com ID " + id + " deletado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar o projeto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
