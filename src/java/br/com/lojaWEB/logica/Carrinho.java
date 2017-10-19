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
        List<Item> itens = new ArrayList<>();
        
        //Ação para Adicionar Produto 
        //<editor-fold>
        if (req.getParameter("action").equals("add")) {
            CtrlProduto ctrlProduto = new CtrlProduto();
            Produto produto = ctrlProduto.buscarID(Long.parseLong(req.getParameter("id")));
            Item item = new Item();
            item.setProduto(produto);
            Pedido pedido = new Pedido();
            item.setPedido(pedido);
            itens.add(item);
            req.setAttribute("itens", itens);
            pagina = "index.jsp?p=carrinho";
        }//</editor-fold>

        //Retorna para a pagina
        return pagina;
    }

}
