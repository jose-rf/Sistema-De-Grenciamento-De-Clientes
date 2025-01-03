package sistemagerenciamentoclientes.repository;

import sistemagerenciamentoclientes.model.Clientes;
import java.sql.*;

public class ClienteDAO {

    // Método para inserir um cliente no banco de dados
    public boolean inserir(Clientes cliente) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();  // Cria uma instância da classe ConexaoMySQL
        Connection connection = conexaoMySQL.conectar();  // Obtém a conexão com o banco
        PreparedStatement stmt = null;

        if (connection == null) {
            System.out.println("Erro: Não foi possível conectar ao banco de dados.");
            return false;  // Caso não tenha conseguido conectar
        }

        try {
            String comando = "INSERT INTO CLIENTES (nome, endereco, email, telefone, historicoMedico, dataNascimento, cpf) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(comando);
            
            // Definindo os parâmetros
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getHistoricoMedico());
            stmt.setString(6, cliente.getDataNascimento());
            stmt.setString(7, cliente.getCpf());
            
            // Executando a inserção
            stmt.executeUpdate();
            return true;  // Inserção bem-sucedida
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir cliente: " + ex.getMessage());
            return false;
        } finally {
            try {
                // Fechando a PreparedStatement
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
            }
        }
    }

    // Método para buscar um cliente por CPF
    public Clientes buscarPorCpf(String cpf) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();  // Cria uma instância da classe ConexaoMySQL
        Connection connection = conexaoMySQL.conectar();  // Obtém a conexão com o banco
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes cliente = null;

        if (connection == null) {
            System.out.println("Erro: Não foi possível conectar ao banco de dados.");
            return null;  // Caso não tenha conseguido conectar
        }

        try {
            String comando = "SELECT * FROM CLIENTES WHERE cpf = ?";
            stmt = connection.prepareStatement(comando);
            stmt.setString(1, cpf);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new Clientes();
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setHistoricoMedico(rs.getString("historicoMedico"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar cliente: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return cliente;
    }

    // Método para atualizar os dados de um cliente
    public boolean atualizar(Clientes cliente) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();  // Cria uma instância da classe ConexaoMySQL
        Connection connection = conexaoMySQL.conectar();  // Obtém a conexão com o banco
        PreparedStatement stmt = null;

        if (connection == null) {
            System.out.println("Erro: Não foi possível conectar ao banco de dados.");
            return false;  // Caso não tenha conseguido conectar
        }

        try {
            String comando = "UPDATE CLIENTES SET nome = ?, endereco = ?, email = ?, telefone = ?, historicoMedico = ?, dataNascimento = ? WHERE cpf = ?";
            stmt = connection.prepareStatement(comando);
            
            // Definindo os parâmetros
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getHistoricoMedico());
            stmt.setString(6, cliente.getDataNascimento());
            stmt.setString(7, cliente.getCpf());
            
            // Executando a atualização
            stmt.executeUpdate();
            return true;  // Atualização bem-sucedida
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar cliente: " + ex.getMessage());
            return false;
        } finally {
            try {
                // Fechando a PreparedStatement
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
            }
        }
    }

    // Método para excluir um cliente pelo CPF
    public boolean excluir(String cpf) {
        ConexaoMySQL conexaoMySQL = new ConexaoMySQL();  // Cria uma instância da classe ConexaoMySQL
        Connection connection = conexaoMySQL.conectar();  // Obtém a conexão com o banco
        PreparedStatement stmt = null;

        if (connection == null) {
            System.out.println("Erro: Não foi possível conectar ao banco de dados.");
            return false;  // Caso não tenha conseguido conectar
        }

        try {
            String comando = "DELETE FROM CLIENTES WHERE cpf = ?";
            stmt = connection.prepareStatement(comando);
            stmt.setString(1, cpf);
            
            // Executando a exclusão
            stmt.executeUpdate();
            return true;  // Exclusão bem-sucedida
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir cliente: " + ex.getMessage());
            return false;
        } finally {
            try {
                // Fechando a PreparedStatement
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
            }
        }
    }
}
