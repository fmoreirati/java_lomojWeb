package br.com.lojaWEB.logica;

import br.com.lojaWEB.controller.CtrlProduto;
import br.com.lojaWEB.model.Produto;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class ProdutoLogica implements Logica {

    private static final long serialVersionUID = 1L;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        //Retorno da Pagina
        String pagina = "index.jsp";

        //Ação para cadastro
        //<editor-fold>
        if (req.getParameter("action").equals("cad")) {
            Produto produto = new Produto();
            try {
                //UpFiles up = new UpFiles();
                //up.setPath("../../web/img/produto/");
                //if (up.send(req)) {
                produto.setFoto1(req.getPart("foto1").getSubmittedFileName());
                produto.setFoto2(req.getPart("foto2").getSubmittedFileName());
                produto.setFoto3(req.getPart("foto3").getSubmittedFileName());

                produto.setDescricao(req.getParameter("descricao").trim());

                if (!req.getParameter("quant").equals("")) {
                    produto.setQuant(Integer.parseInt(req.getParameter("quant")));
                }
                if (!req.getParameter("valor").equals("")) {
                    produto.setValor(Double.parseDouble(req.getParameter("valor")));
                }

                //Trata o valor do botão de rádio
                if (req.getParameter("ativo").equals("1")) {
                    produto.setAtivo(true);
                } else {
                    produto.setAtivo(false);
                }

                produto.isProduto();

                CtrlProduto ctrProduto = new CtrlProduto();
                ctrProduto.salvar(produto);
                req.setAttribute("avisos", "Produto cadastrado com sucesso.");
                produto = null;
                //}

            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
                req.setAttribute("produto", produto);
            }
            pagina = "index.jsp?p=cadProduto";
        }//</editor-fold>

        //Ação para listar
        //<editor-fold>
        if (req.getParameter("action").equals("list")) {
            try {
                CtrlProduto ctrlProduto = new CtrlProduto();
                req.setAttribute("produtos", ctrlProduto.listarTodos(req.getParameter("busca")));

            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
            }
            pagina = "index.jsp?p=relProduto";
        }//</editor-fold>
        
        //Ação para Descrição
        //<editor-fold>
        if (req.getParameter("action").equals("desc")) {
            try {
                CtrlProduto ctrlProduto = new CtrlProduto();
                req.setAttribute("produto", ctrlProduto.buscarID(Long.parseLong(req.getParameter("id"))));

            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
            }
            pagina = "index.jsp?p=descProduto";
        }//</editor-fold>

        //Ação para Editar
        //<editor-fold>
        if (req.getParameter("action").equals("edit")) {
            Produto produto = new Produto();
            try {
                CtrlProduto ctrProduto = new CtrlProduto();
                produto = ctrProduto.buscarID(Long.parseLong(req.getParameter("id")));
                req.setAttribute("alt", true);
            } catch (Exception e) {
                req.setAttribute("Erros: ", e.getMessage().replace(".\n", ".<br>"));

            }
            req.setAttribute("produto", produto);
            pagina = "index.jsp?p=cadProduto";
        }//</editor-fold>

        //Ação para Alterar
        //<editor-fold>
        if (req.getParameter("action").equals("alt")) {
            Produto produto = new Produto();
            try {
                produto.setFoto1(req.getPart("foto1").getSubmittedFileName());
                produto.setFoto2(req.getPart("foto2").getSubmittedFileName());
                produto.setFoto3(req.getPart("foto3").getSubmittedFileName());

                produto.setId(Long.parseLong(req.getParameter("id").trim()));
                produto.setDescricao(req.getParameter("descricao").trim());

                if (!req.getParameter("quant").equals("")) {
                    produto.setQuant(Integer.parseInt(req.getParameter("quant")));
                }
                if (!req.getParameter("valor").equals("")) {
                    produto.setValor(Double.parseDouble(req.getParameter("valor")));
                }

                //Trata o valor do botão de rádio
                if (req.getParameter("ativo").equals("1")) {
                    produto.setAtivo(true);
                } else {
                    produto.setAtivo(false);
                }

                produto.isProduto();
                CtrlProduto ctrlProduto = new CtrlProduto();
                ctrlProduto.alterar(produto);
                req.setAttribute("avisos", "Produto alterado com sucesso.");
                produto = null;

            } catch (Exception e) {
                req.setAttribute("Erros: ", e.getMessage().replace(".\n", ".<br>"));
                req.setAttribute("produto", produto);

            }
            req.setAttribute("produto", produto);
            pagina = "index.jsp?p=cadProduto";
        }//</editor-fold>

        
        //Ação para apagar
        //<editor-fold>
        if (req.getParameter("action").equals("remove")) {
            Produto produto = new Produto();
            try {
                CtrlProduto ctrProduto = new CtrlProduto();
                ctrProduto.remover(Long.parseLong(req.getParameter("id")));
            } catch (Exception e) {
                req.setAttribute("erros", e.getMessage().replace(".\n", ".<br>"));
                req.setAttribute("produto", produto);
            }
            pagina = "index.jsp?p=reportProduto";
        }//</editor-fold>

       //Retorna para a pagina
        return pagina;
    }

}
