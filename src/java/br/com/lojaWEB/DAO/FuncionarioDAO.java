package br.com.lojaWEB.DAO;

import br.com.projetoloja.model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO extends br.com.projetoloja.DAO.FuncionarioDAO {

    private PreparedStatement stman;
    private ResultSet result;

    public Funcionario login(String email, String senha) throws SQLException {
        String sql = ""
                + "SELECT * "
                + "FROM funcionario as c, pessoa as p, endereco as e "
                + "where p.idPessoa = c.idPessoa "
                + "and p.pws = ? and p.email = ?";
        getConnection();
        stman = connection.prepareStatement(sql);
        stman.setString(1, senha);
        stman.setString(2, email);
        result = stman.executeQuery();
        Funcionario funcionario = new Funcionario();
        while (result.first()) {
            funcionario.setId(result.getLong("idFuncionario"));
            funcionario.setNome(result.getString("nome"));
        }
        result.close();
        stman.close();
        close();
        return funcionario;

    }
}
