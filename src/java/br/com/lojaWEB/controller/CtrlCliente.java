
package br.com.lojaWEB.controller;

import br.com.lojaWEB.model.Cliente;
import br.com.lojaWEB.DAO.ClienteDAO;
import java.sql.SQLException;
import java.util.List;

public class CtrlCliente {
    
    public Cliente login(String email, String pws) throws SQLException{
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.findCliente(email, pws);
    }
    public void salvar(Cliente cliente) throws SQLException, Exception{
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.create(cliente);
    }
    public List listar(String dados) throws SQLException, Exception{
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.findClientes(dados);
    }
    
}
