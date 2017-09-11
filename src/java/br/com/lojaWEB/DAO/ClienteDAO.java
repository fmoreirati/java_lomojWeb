package br.com.lojaWEB.DAO;

import br.com.projetoloja.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO extends br.com.projetoloja.DAO.ClienteDAO {

    private PreparedStatement stman;
    private ResultSet result;

    public Cliente login(String email, String senha) throws SQLException {
        String sql = ""
                + "SELECT * "
                + "FROM cliente as c, pessoa as p, endereco as e "
                + "where p.idPessoa = c.idPessoa "
                + "and p.pws = ? and p.email = ?";
        getConnection();
        stman = connection.prepareStatement(sql);
        stman.setString(1, senha);
        stman.setString(2, email);
        result = stman.executeQuery();
        Cliente cliente = new Cliente();
        while (result.first()) {
            cliente.setId(result.getLong("idCliente"));
            cliente.setNome(result.getString("nome"));
        }
        result.close();
        stman.close();
        close();
        return cliente;

    }
}
