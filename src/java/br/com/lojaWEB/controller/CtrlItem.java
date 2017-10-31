package br.com.lojaWEB.controller;

import br.com.lojaWEB.model.Item;
import br.com.lojaWEB.DAO.ItemDAO;
import java.sql.SQLException;
import java.util.List;

public class CtrlItem {

    public void salvar(Item item) throws SQLException, Exception {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.create(item);
    }

    public List listar() throws SQLException, Exception {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.findItems();
    }

    public void alterar(Item item) throws Exception {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.edit(item);
    }

    public Item buscaID(long parseLong) {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.findItem(parseLong);
    }

    public void remover(long parseLong) throws Exception {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.destroy(parseLong);
    }

}
