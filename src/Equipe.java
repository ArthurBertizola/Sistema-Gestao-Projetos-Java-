package src;
import java.util.List;
import java.util.ArrayList;
import src.Usuario;
import src.Projeto;

public class Equipe {
    private int id;
    private String nome;
    private List<Usuario> membros;
    private List<Projeto> projetosAtuais;

    // Construtor
    public Equipe(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.membros = new ArrayList<>();
        this.projetosAtuais = new ArrayList<>();
    }

    // --- Métodos (Getters) ---
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public List<Projeto> getProjetosAtuais() {
        return projetosAtuais;
    }

    // --- Métodos (Setters/Adicionar) ---
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarMembro(Usuario usuario) {
        this.membros.add(usuario);
    }

    public void adicionarProjeto(Projeto projeto) {
        this.projetosAtuais.add(projeto);
    }

    public void removerMembro(Usuario usuario) {
        this.membros.remove(usuario);
    }

    public void removerProjeto(Projeto projeto) {
        this.projetosAtuais.remove(projeto);
    }
}