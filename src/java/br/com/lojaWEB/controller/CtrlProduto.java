package br.com.lojaWEB.controller;

import br.com.lojaWEB.model.Produto;
import br.com.lojaWEB.DAO.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;

public class CtrlProduto {
    
    public void salvar(Produto produto) throws SQLException, Exception {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.create(produto);
    }

    public List listar(String dados) throws SQLException, Exception {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.findProdutos(dados);
    }
    public List listarTodos(String dados) throws SQLException, Exception {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.findProdutosALL(dados);
    }

    public Produto buscarID(long id) throws SQLException, Exception {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.findProduto(id);
    }

    public void alterar(Produto produto) throws SQLException, Exception {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.edit(produto);
    }
    
    public void remover(long parseLong) throws Exception {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.destroy(parseLong);
    }
    
}
