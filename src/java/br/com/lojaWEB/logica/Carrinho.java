package br.com.lojaWEB.logica;

import br.com.lojaWEB.controller.CtrlProduto;
import br.com.lojaWEB.model.Item;
import br.com.lojaWEB.model.Pedido;
import br.com.lojaWEB.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
public class Carrinho implements Logica {

    private static final long serialVersionUID = 1L;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        //Retorno da Pagina
        String pagina = "index.jsp";
        HttpSession carrinho = req.getSession(false);

        //Ação para Adicionar Produto 
        //<editor-fold>
        if (req.getParameter("action").equals("add")) {
            CtrlProduto ctrlProduto = new CtrlProduto();
            Produto produto = ctrlProduto.buscarID(Long.parseLong(req.getParameter("id")));
            Item item = new Item();
            item.setProduto(produto);
            Pedido pedido = new Pedido();
            item.setPedido(pedido);
            List<Item> itens = (List<Item>) carrinho.getAttribute("itens");
            carrinho.setAttribute("itens", adicionarProduto(itens, item));
            pagina = "index.jsp?p=carrinho";
        }//</editor-fold>

        //Retorna para a pagina
        return pagina;
    }

    //Função para criar e incrementar produtos na lista
    public List<Item> adicionarProduto(List itens, Item i) {
        if (itens == null) {
            itens = new ArrayList();
        }
        itens.add(i);
        return itens;
    }
}
