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
            //Busca produto por ID;
            Produto produto = ctrlProduto.buscarID(Long.parseLong(req.getParameter("id")));
            //Cria e set campos Item para adicionar;
            Item item = new Item();
            item.setProduto(produto);
            item.setQuant(1);
            item.setValorItens();
            //Cria novo Pedido;
            Pedido pedido = new Pedido();
            //Adiciona item no Pedido
            item.setPedido(pedido);
            //Cria uma lista com itens na sessão "itens"
            List<Item> itens = (List<Item>) carrinho.getAttribute("itens");
            //Se a sessão de "itens" for null. Cria uma nova lista;

            if (itens == null) {
                itens = new ArrayList<>();
            }
            //Contador para produtos duplicados
            int cont = 0;
            for (Item iten : itens) {
                if (iten.getProduto().equals(item.getProduto())) {
                    cont++;
                }
            }
            if (cont == 0) {
                //Atualiza sessão com nova lista de itens. Agora atualizada
                carrinho.setAttribute("itens", adicionarProduto(itens, item));
            }

            //Pega tamanho da lista do carrinho
            carrinho.setAttribute("tamanho", itens.size());
            //Retorna para o carrinho
            pagina = "index.jsp?p=carrinho";
        }//</editor-fold>

        //Ação para Remover Produto 
        //<editor-fold>
        if (req.getParameter("action").equals("remove")) {
            CtrlProduto ctrlProduto = new CtrlProduto();

            //Cria uma lista com itens na sessão "itens"
            List<Item> itens = (List<Item>) carrinho.getAttribute("itens");
            //Remove item da lista de Itens
            itens.remove(Integer.parseInt(req.getParameter("index")));
            
            //Atualiza sessão com nova lista de itens. Agora atualizada
            carrinho.setAttribute("itens",itens);

            //Pega tamanho da lista do carrinho
            carrinho.setAttribute("tamanho", itens.size());
            
            //Retorna para o carrinho
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
