package br.com.lojaWEB.logica;

import br.com.lojaWEB.controller.CtrlCliente;
import br.com.lojaWEB.controller.CtrlItem;
import br.com.lojaWEB.controller.CtrlPedido;
import br.com.lojaWEB.controller.CtrlProduto;
import br.com.lojaWEB.model.Cliente;
import br.com.lojaWEB.model.Item;
import br.com.lojaWEB.model.Pedido;
import br.com.lojaWEB.model.Produto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
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
        HttpSession user = req.getSession(false);

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
                carrinho.setAttribute("total", calculaTotal(itens));
                carrinho.setAttribute("frete", calculaFrete());
            }

            //Pega tamanho da lista do carrinho
            carrinho.setAttribute("tamanho", itens.size());
            //Retorna para o carrinho
            pagina = "index.jsp?p=carrinho";
        }//</editor-fold>

        //Ação para Calcular quantidade e valor dos Produto 
        //<editor-fold>
        if (req.getParameter("action").equals("calcular")) {
            String quant[] = req.getParameterValues("quant");

            //Cria uma lista com itens na sessão "itens"
            List<Item> itens = (List<Item>) carrinho.getAttribute("itens");
            int x = 0;
            for (Item iten : itens) {
                iten.setQuant(Integer.parseInt(quant[x]));
                iten.setValorItens();
                x++;
            }

            //Atualiza sessão com nova lista de itens. Agora atualizada
            carrinho.setAttribute("itens", itens);
            carrinho.setAttribute("total", calculaTotal(itens));
            carrinho.setAttribute("frete", calculaFrete());

            //Retorna para o carrinho
            pagina = "index.jsp?p=carrinho";
        }//</editor-fold>

        //Ação para Remover Produto 
        //<editor-fold>
        if (req.getParameter("action").equals("remove")) {
            //Cria uma lista com itens na sessão "itens"
            List<Item> itens = (List<Item>) carrinho.getAttribute("itens");
            //Remove item da lista de Itens
            itens.remove(Integer.parseInt(req.getParameter("index")));

            //Atualiza sessão com nova lista de itens. Agora atualizada
            carrinho.setAttribute("itens", itens);
            carrinho.setAttribute("total", calculaTotal(itens));
            carrinho.setAttribute("frete", calculaFrete());
            //Pega tamanho da lista do carrinho
            carrinho.setAttribute("tamanho", itens.size());

            //Retorna para o carrinho
            pagina = "index.jsp?p=carrinho";
        }//</editor-fold>

        //Ação para Comprar Produtos 
        //<editor-fold>
        if (req.getParameter("action").equals("compra")) {
            CtrlCliente ctrlCliente = new CtrlCliente();
            CtrlPedido ctrlPedido = new CtrlPedido();
            CtrlItem ctrlItem = new CtrlItem();

            if (user.getAttribute("id") == null) {
                pagina = "index.jsp?p=login";
            } else {
                //Busca cliente no banco pelo ID
                Cliente cliente = ctrlCliente.buscaID((Long) user.getAttribute("id"));
                //Cria uma lista com itens na sessão "itens"
                List<Item> itens = (List<Item>) carrinho.getAttribute("itens");
                //Cria novo Pedido;
                Pedido pedido = new Pedido();

                //Adicionando: Cliente, total produto , frete e calculo de totaldo pedido
                pedido.setCliente(cliente);
                Calendar dt = new GregorianCalendar(Locale.ROOT);
                pedido.setDataPedido(dt);
                pedido.setFrete(calculaFrete());
                pedido.setValor(calculaTotal(itens));
                pedido.setTotal();
                pedido.setFechado(true);
                //Cadastrar Pedidos no Banco
                ctrlPedido.salvar(pedido);

                //Cadastrar no banco os itens da lista
                for (Item iten : itens) {
                    iten.setPedido(pedido);
                    ctrlItem.salvar(iten);
                }
                carrinho.invalidate();

            }
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

    private double calculaFrete() {
        return 50;
    }

    private double calculaTotal(List<Item> itens) {
        double total = 0;
        for (Item iten : itens) {
            total += iten.getValorItens();
        }
        return total;
    }
}
