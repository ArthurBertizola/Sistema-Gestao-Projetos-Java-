package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date; // para trabalhar com java.sql.Date
import src.Tarefa;

public class TarefaDAO {

    // Método para salvar uma nova tarefa
    public void salvar(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (descricao, data_de_vencimento, status, responsavel, projeto_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getDescricao());
            // Conversão de LocalDate para java.sql.Date
            stmt.setDate(2, Date.valueOf(tarefa.getDataDeVencimento()));
            stmt.setString(3, tarefa.getStatus());
            stmt.setString(4, tarefa.getResponsavel());
            stmt.setInt(5, tarefa.getIdProjeto());

            stmt.executeUpdate();

            System.out.println("Tarefa '" + tarefa.getDescricao() + "' salva com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar a tarefa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para buscar uma tarefa por ID
    public Tarefa buscarPorId(int id) {
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        Tarefa tarefa = null;

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tarefa = new Tarefa(
                            rs.getInt("id"),
                            rs.getString("descricao"),
                            rs.getDate("data_de_vencimento").toLocalDate(),
                            rs.getString("status"),
                            rs.getString("responsavel"),
                            rs.getInt("projeto_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefa: " + e.getMessage());
            e.printStackTrace();
        }
        return tarefa;
    }

    // Método para listar todas as tarefas
    public List<Tarefa> listarTodos() {
        String sql = "SELECT * FROM tarefas";
        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDate("data_de_vencimento").toLocalDate(),
                        rs.getString("status"),
                        rs.getString("responsavel"),
                        rs.getInt("projeto_id")
                );
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
            e.printStackTrace();
        }
        return tarefas;
    }

    // Método para atualizar uma tarefa
    public void atualizar(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET descricao = ?, data_de_vencimento = ?, status = ?, responsavel = ?, projeto_id = ? WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getDescricao());
            stmt.setDate(2, Date.valueOf(tarefa.getDataDeVencimento()));
            stmt.setString(3, tarefa.getStatus());
            stmt.setString(4, tarefa.getResponsavel());
            stmt.setInt(5, tarefa.getIdProjeto());
            stmt.setInt(6, tarefa.getId());

            stmt.executeUpdate();

            System.out.println("Tarefa '" + tarefa.getDescricao() + "' atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a tarefa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para deletar uma tarefa
    public void deletar(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Tarefa com ID " + id + " deletada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar a tarefa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}