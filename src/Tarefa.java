package src;
import java.time.LocalDate;

public class Tarefa {
    private int id;
    private String descricao;
    private LocalDate dataDeVencimento;
    private String status;
    private String responsavel;
    private int idProjeto;


    public Tarefa(int id, String descricao, LocalDate dataDeVencimento, String status, String responsavel, int idProjeto) {
        this.id = id;
        this.descricao = descricao;
        this.dataDeVencimento = dataDeVencimento;
        this.status = status;
        this.responsavel = responsavel;
        this.idProjeto = idProjeto;
    }

    // --- Métodos (Getters) ---

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public String getStatus() {
        return status;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public int getIdProjeto() {
        return idProjeto;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}