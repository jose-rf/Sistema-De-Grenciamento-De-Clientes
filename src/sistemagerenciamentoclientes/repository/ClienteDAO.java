package sistemagerenciamentoclientes.repository;

import sistemagerenciamentoclientes.model.Clientes;
import java.sql.*;

public class ClienteDAO {

    private ConexaoMySQL conexaoMySQL;

    public ClienteDAO() {
        this.conexaoMySQL = new ConexaoMySQL(); // Inicializa a conexão com o banco de dados
    }

    // Alteração para buscar o cliente pelo CPF
 public Clientes buscarPorCpfNoBanco(String cpf) {
    Connection conn = conexaoMySQL.conectar(); // Obtém a conexão com o banco de dados

   
        System.out.println("CPF inserido: " + cpf);
        String sql = "SELECT * FROM CLIENTES WHERE cpf = ?"; // Consulta SQL para buscar cliente pelo CPF
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf); // Atribui o CPF à consulta

            ResultSet rs = stmt.executeQuery(); // Executa a consulta

            if (rs.next()) {
                // Cria e retorna o objeto Clientes com os dados retornados do banco
                Clientes cliente = new Clientes();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                return cliente;
            } else {
                // Retorna null se o CPF não for encontrado no banco
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Em caso de erro, imprime o stack trace
        } finally {
            ConexaoMySQL.fecharConexao(); // Fechar a conexão após o uso
        }
    
    
    // Se a conexão com o banco de dados falhar, retorna null
    return null;
}

}
