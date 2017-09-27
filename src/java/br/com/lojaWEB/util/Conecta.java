package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import java.sql.*;
public class Conecta {

    // Instancia Classe de Conexao;
    public static Connection connection = null;

    // Define Constantes de acesso ao BD;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String BANCO = "loja";
    private final String URL = "jdbc:mysql://localhost:3306/" + BANCO;
    private final String LOGIN = "root";
    //private final String SENHA = "";
    private final String SENHA = "12345";

    //Carregando drives de concexao;
    public boolean getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectado!");
            return true;
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver não encontrado: " + ex.toString());
            return false;
        } catch (SQLException ex) {
            System.err.println("Erro de Conexão: " + ex.toString());
            return false;
        }
    }

    // Desconeta Banco
    public void close() {
        try {
            connection.close();
            System.err.println("Desconectado.");
        } catch (SQLException ex) {
            System.err.println("Erro de Desconexão: " + ex.toString());
        }
    }
}
