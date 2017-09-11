package br.com.lojaWEB.Servlet;

import br.com.lojaWEB.controller.CtrlCliente;
import br.com.projetoloja.model.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletCliente", urlPatterns = {"/ServletCliente"})
public class ServletCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action").equals("cad")) {
            try {
                Cliente cliente = new Cliente();
                //Pessoa
                cliente.setNome(request.getParameter("nome"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setCpfcnpj(request.getParameter("cpf"));
                cliente.setPws(request.getParameter("pws"));
                cliente.setTel(request.getParameter("tel"));
                cliente.setNumero(request.getParameter("numero"));
                cliente.setComplemento(request.getParameter("complemento"));
                //Endereco
                cliente.getEndereco().setCep(request.getParameter("cep"));
                cliente.getEndereco().setLogradouro(request.getParameter("logradouro"));
                cliente.getEndereco().setBairro(request.getParameter("bairro"));
                cliente.getEndereco().setCidade(request.getParameter("cidade"));
                cliente.getEndereco().setUf(request.getParameter("uf"));

                String erros = cliente.isCliente(request.getParameter("pws2"));

                if (erros.equals("")) {
                    CtrlCliente ctrlCliente = new CtrlCliente();

                    if (ctrlCliente.cadastrar(cliente)) {
                        request.setAttribute("avisos", "Cadastrado");
                    } else {
                        request.setAttribute("erros", "Cadastro não realizado");
                    }

                } else {
                    request.setAttribute("erros", erros);
                    request.setAttribute("cliente", cliente);
                }

                request.getRequestDispatcher("index.jsp?p=cadCliente").forward(request, response);

            } catch (IOException | SQLException | ServletException ex) {
                System.err.println("Erro cadastro:" + ex.toString());
                request.setAttribute("alertas", ex.toString());
            }

        }
        if (request.getParameter("action").equals("log")) {
            CtrlCliente ctrlCliente = new CtrlCliente();
            try {
                Cliente cliente = ctrlCliente.login(request.getParameter("email"), request.getParameter("pws"));
                if (cliente!=null){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", cliente);
                } else{
                    request.setAttribute("erros", "Login invalido");
                }
                request.getRequestDispatcher("index.jsp?p=login").forward(request, response);
            } catch (SQLException ex) {
                System.err.println("Erro login:" + ex.toString());
                request.setAttribute("alertas", ex.toString());
            }
                    
    }}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
