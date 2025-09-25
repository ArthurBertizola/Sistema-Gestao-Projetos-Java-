package src;
import src.Equipe;
import src.Projeto;

public class Usuario {
    private int id;
    private String nomeCompleto;
    private String login;
    private String senha;
    private String perfil;
    private String cargo;
    private String email;


    public Usuario(int id, String nomeCompleto, String login, String senha, String perfil, String cargo, String email) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.cargo = cargo;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getCargo() {
        return cargo;
    }

    public String getEmail() {
        return email;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}