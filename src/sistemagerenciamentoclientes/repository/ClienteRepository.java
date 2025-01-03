package sistemagerenciamentoclientes.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemagerenciamentoclientes.model.Clientes;

/**
 *
 * @author joser
 */
public class ClienteRepository implements Crud<Clientes> {

    @Override
    
    //conexão com o banco de dados
    
    //método de inserir
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

    @Override
    
    //método de atualizar
    public boolean atualizar(Connection connection, Clientes entity) {
        String comando = "UPDATE CLIENTES SET nome = ?, endereco = ?, email = ?, telefone = ?, historicoMedico = ?, " +
                         "dataNascimento = ?, cpf = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(comando)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEndereco());
            stmt.setString(3, entity.getEmail());
            stmt.setString(4, entity.getTelefone());
            stmt.setString(5, entity.getHistoricoMedico());
            stmt.setString(6, entity.getDataNascimento());
            stmt.setString(7, entity.getCpf());
            stmt.setInt(8, entity.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    
    //método de deletar
    public boolean deletar(Connection connection, Clientes entity) {
        String comando = "DELETE FROM CLIENTES WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(comando)) {
            stmt.setInt(1, entity.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    
    //método de selecionar
    public Clientes selecionar(Connection connection, String operador, int id) {
        String comando = "SELECT * FROM CLIENTES WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(comando)) {
            stmt.setInt(1, id);
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
        return null;
    }
}
