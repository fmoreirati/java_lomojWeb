
package br.com.lojaWEB.controller;

import br.com.lojaWEB.DAO.FuncionarioDAO;
import br.com.projetoloja.model.Funcionario;
import java.sql.SQLException;

public class CtrlFuncionario extends br.com.projetoloja.controller.CtrlFuncionario{
    
    public Funcionario login(String email, String pws) throws SQLException{
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.login(email, pws);
    }
    
}
