package br.com.lojaWEB.Servlet;

import br.com.lojaWEB.controller.CtrlFuncionario;
import br.com.projetoloja.model.Funcionario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletFuncionario", urlPatterns = {"/ServletFuncionario"})
public class ServletFuncionario extends HttpServlet {

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
                Funcionario funcionario = new Funcionario();
                //Pessoa
                funcionario.setNome(request.getParameter("nome"));
                funcionario.setEmail(request.getParameter("email"));
                funcionario.setCpfcnpj(request.getParameter("cpf"));
                funcionario.setPws(request.getParameter("pws"));
                funcionario.setTel(request.getParameter("tel"));
                funcionario.setNumero(request.getParameter("numero"));
                funcionario.setComplemento(request.getParameter("complemento"));
                //Endereco
                funcionario.getEndereco().setCep(request.getParameter("cep"));
                funcionario.getEndereco().setLogradouro(request.getParameter("logradouro"));
                funcionario.getEndereco().setBairro(request.getParameter("bairro"));
                funcionario.getEndereco().setCidade(request.getParameter("cidade"));
                funcionario.getEndereco().setUf(request.getParameter("uf"));

                String erros = funcionario.isFuncionario(request.getParameter("pws2"));

                if (erros.equals("")) {
                    CtrlFuncionario ctrlFuncionario = new CtrlFuncionario();

                    if (ctrlFuncionario.cadastrar(funcionario)) {
                        request.setAttribute("avisos", "Cadastrado");
                    } else {
                        request.setAttribute("erros", "Cadastro não realizado");
                    }

                } else {
                    request.setAttribute("erros", erros);
                    request.setAttribute("funcionario", funcionario);
                }

                request.getRequestDispatcher("index.jsp?p=cadFuncionario").forward(request, response);

            } catch (IOException | SQLException | ServletException ex) {
                System.err.println("Erro cadastro:" + ex.toString());
                request.setAttribute("alertas", ex.toString());
            }

        }
        if (request.getParameter("action").equals("log")) {
            CtrlFuncionario ctrlFuncionario = new CtrlFuncionario();
            try {
                Funcionario funcionario = ctrlFuncionario.login(request.getParameter("email"), request.getParameter("pws"));
                if (funcionario!=null){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", funcionario);
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
