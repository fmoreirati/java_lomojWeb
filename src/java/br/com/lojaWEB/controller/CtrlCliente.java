
package br.com.lojaWEB.controller;

import br.com.lojaWEB.DAO.ClienteDAO;
import br.com.projetoloja.model.Cliente;
import java.sql.SQLException;

public class CtrlCliente extends br.com.projetoloja.controller.CtrlCliente{
    
    public Cliente login(String email, String pws) throws SQLException{
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.login(email, pws);
    }
    
}
