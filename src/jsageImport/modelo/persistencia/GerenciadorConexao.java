/*
 * Gerenciamento das conexões com o banco de dados
 */
package jsageImport.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsageImport.exception.JsageImportException;

/**
 * @author Gustavo Dias
 * Criação: 06/06/2016
 * Última modificação: 07/06/2016
 */
class GerenciadorConexao {
    
    /**
     * Gera conexão com o banco de dados sem parâmetro
     * @return Connection
     * @throws ClassNotFoundException 
     */
    static Connection getConnection() throws JsageImportException{
		//System.out.println("Conectando ao banco");
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        //jdbc:sqlserver://servidor:porta;databaseName=banco;user=usuario;password=senha;"
			return DriverManager.getConnection("jdbc:sqlserver://192.168.10.105:1433;databaseName=sage_gestao_contabil;user=sa;password=S@geBr.2014;");
		} catch (SQLException ex){
                    String mensagem = "Não foi possível realizar a conexão com o banco de dados";
                    throw new JsageImportException(mensagem);
		} catch (ClassNotFoundException ex) {
                    String mensagem = "Não foi possível localizar o driver de conexão!";
                    throw new JsageImportException (mensagem);
                }
	}
    
    static Connection getConnection(String url) throws JsageImportException{
		//System.out.println("Conectando ao banco");
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        //jdbc:sqlserver://servidor:porta;databaseName=banco;user=usuario;password=senha;"
			return DriverManager.getConnection(url);
		} catch (SQLException ex){
                    String mensagem = "Não foi possível realizar a conexão com o banco de dados";
                    throw new JsageImportException(mensagem);
		} catch (ClassNotFoundException ex) {
                    String mensagem = "Não foi possível localizar o driver de conexão!";
                    throw new JsageImportException (mensagem);
                }
	}
    /**
     * Gera a conexao do banco de dados repassando os parâmetros
     * @param server
     * @param bd
     * @param port
     * @param user
     * @param password
     * @return Connection
     * @throws ClassNotFoundException 
     */
    static Connection getConnection(String server, String bd, String port, String user, String password){
        
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url ="jdbc:sqlserver://"+server+":"+port+";databaseName="+bd+";user="+user+";password="+password+";";
        Connection con = null;        
	try{
		Class.forName(driver);
                con = DriverManager.getConnection(url);
               
	// return con;	
	} catch (SQLException ex){
            String mensagem = "Não foi possível realizar a conexão com o banco de dados";
            //throw new JsageImportException(mensagem);
	} catch (ClassNotFoundException ex) {
            String mensagem = "Não foi possível localizar o driver de conexão!";
            //throw new JsageImportException (mensagem);
        }  
        return con;
    }
   /**
    * Fecha a conexao com o banco apenas passando a conexao utilizada
    * @param con conexao a ser fechada
    * @throws JsageImportException 
    */ 
   static void closeConnection (Connection con) throws JsageImportException{
       closeConexao(con, null, null);
   }
   
   /**
    * Fecha a conexão com o banco de dados e o objeto PreparedStatement
    * @param con a conexão a ser fechada
    * @param stmt o objeto PreparedStatement a ser fechado
    * @throws JsageImportException
    */
    static void closeConexao(Connection con, PreparedStatement stmt)throws JsageImportException {
        closeConexao(con, stmt, null);
    }
   
    /**
    * Fecha a conexão com o banco de dados e os objetos PreparedStatement e ResultSet
    * @param con a conexão a ser fechada
    * @param stmt o objeto PreparedStatement a ser fechado
    * @param rs o objeto ResultSet a ser fechado
    * @throws JsageImportException
    */
    static void closeConexao(Connection con, PreparedStatement stmt, ResultSet rs) throws JsageImportException {
        try {
            if (rs != null){
                rs.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if (con != null){
                con.close();
            }
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível finalizar a conexão com banco de dados.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
    }
}
