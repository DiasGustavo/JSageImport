/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsageImport.exception.JsageImportException;

/**
 *
 * @author Gustavo
 */
public class TesteConexao {
    
    public static void main (String[] args){
        System.out.println("Conectando ao banco");
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url ="jdbc:sqlserver://192.168.10.105:1433;databaseName=sage_gestao_contabil;user=sa;password=S@geBr.2014;";
        System.out.println(url);
	try{
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url);
                if (conn != null){
                    System.out.println("conectado.");
                }else{
                    System.out.println("Falha na conexao.");
                }
	} catch (SQLException ex){
            String mensagem = "Não foi possível realizar a conexão com o banco de dados";
            System.out.println(mensagem);
	} catch (ClassNotFoundException ex) {
            String mensagem = "Não foi possível localizar o driver de conexão!";
            System.out.println(mensagem);
        } catch (InstantiationException ex) {
            String mensagem = "Problema na instancia do driver";
            System.out.println(mensagem);
        } catch (IllegalAccessException ex) {
            String mensagem = "Problema na instancia do driver";
            System.out.println(mensagem);
        }
    }
    
}
