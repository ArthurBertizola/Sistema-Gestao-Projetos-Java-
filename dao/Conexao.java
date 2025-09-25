package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class Conexao {

    private static Properties getProperties() {
        Properties props = new Properties();
        try (InputStream is = Conexao.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is == null) {
                System.err.println("Arquivo db.properties não encontrado no Classpath.");
                return props;
            }
            props.load(is);
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo db.properties: " + e.getMessage());
            e.printStackTrace();
        }
        return props;
    }

    public static Connection getConexao() {
        Properties props = getProperties();
        if (props.isEmpty()) {
            return null;
        }

        String url = props.getProperty("db.url");
        String usuario = props.getProperty("db.user");
        String senha = props.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}