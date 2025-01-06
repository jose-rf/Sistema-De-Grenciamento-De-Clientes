package sistemagerenciamentoclientes.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemagerenciamentoclientes.model.Clientes;
import static sistemagerenciamentoclientes.repository.ConexaoMySQL.connection;

public class ClienteRepository implements Crud<Clientes> {
    
    //método inserir
    @Override
    public boolean inserir(Connection connection, Clientes entity) {
        String comando = "INSERT INTO CLIENTES(nome, endereco, email, telefone, historicoMedico, dataNascimento, cpf) " +
                         "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(comando)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEndereco());
            stmt.setString(3, entity.getEmail());
            stmt.setString(4, entity.getTelefone());
            stmt.setString(5, entity.getHistoricoMedico());
            stmt.setString(6, entity.getDataNascimento());
            stmt.setString(7, entity.getCpf());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    //método de atualizar com base no cpf
    @Override
    public boolean atualizar(Connection connection, Clientes entity) {
          String comando = "UPDATE CLIENTES SET nome = ?, endereco = ?, email = ?, telefone = ?, historicoMedico = ?, " +
                     "dataNascimento = ?, cpf = ? WHERE cpf = ?";
    try (PreparedStatement stmt = connection.prepareStatement(comando)) {
        stmt.setString(1, entity.getNome());
        stmt.setString(2, entity.getEndereco());
        stmt.setString(3, entity.getEmail());
        stmt.setString(4, entity.getTelefone());
        stmt.setString(5, entity.getHistoricoMedico());
        stmt.setString(6, entity.getDataNascimento());
        stmt.setString(7, entity.getCpf());
        stmt.setString(8, entity.getCpf());  // Usando o CPF para localizar o cliente
        stmt.executeUpdate();
        return true;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    }
    }   
    
    //método de deletar com base no cpf
    @Override
    public boolean deletar(Connection connection, Clientes entity) {
            String comando = "DELETE FROM CLIENTES WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(comando)) {
            stmt.setString(1, entity.getCpf());  // Usando CPF para excluir o cliente
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    // Selecionar pelo CPF
    @Override
    public Clientes selecionar(String cpf) {
        String comando = "SELECT * FROM CLIENTES WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(comando)) {
            stmt.setString(1, cpf);  // Usando CPF para consulta
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setHistoricoMedico(rs.getString("historicoMedico"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCpf(rs.getString("cpf"));
                return cliente;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;  // Retorna null caso não encontre o CPF
    }
}
