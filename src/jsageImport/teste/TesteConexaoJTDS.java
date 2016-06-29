/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class TesteConexaoJTDS {
    public static void main (String [] args){
        String url = "jdbc:jtds:sqlserver://192.168.10.105:1433/sage_gestao_contabil";
        String usuario = "sa";
        String senha = "S@geBr.2014";
        
        try {
            JFrame mensagem = null;
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(mensagem,"Conexão Efetuada com sucesso!");
        } catch (ClassNotFoundException e) {
            // Erro caso o driver JDBC não foi instalado
            e.printStackTrace();
        } catch (SQLException e) {
            // Erro caso haja problemas para se conectar ao banco de dados
            e.printStackTrace();
        }
    }
}
