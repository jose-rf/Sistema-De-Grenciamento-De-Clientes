package sistemagerenciamentoclientes.repository;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    private Conexao conexao;

    public static Connection connection = null;

    public ConexaoMySQL(Conexao conexao) {
        this.conexao = conexao;
    }

    public ConexaoMySQL() {
    }

    // Conexão com o MySQL - Agora retorna a Connection diretamente
    public Connection conectar() {
        if (conexao != null) {
            try {
                String url = "jdbc:mysql://" + conexao.getEndereco() +
                        ":" + conexao.getPorta() +
                        "/" + conexao.getNomeBanco();

                // Carregar o driver JDBC para MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Estabelece a conexão e retorna a Connection
                connection = DriverManager.getConnection(
                        url,
                        conexao.getUser(),
                        conexao.getPassword()
                );
                return connection;  // Retorna a Connection estabelecida
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Erro ao conectar no banco de dados: " + ex.getMessage(),
                        "Erro ao conectar",
                        JOptionPane.ERROR_MESSAGE
                );
                return null; // Caso haja erro, retorna null
            }
        } else {
            return null;
        }
    }
}
