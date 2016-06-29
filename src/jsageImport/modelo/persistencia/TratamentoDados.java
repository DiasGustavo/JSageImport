/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import jsageImport.controler.ControlerFuncionarioNG;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.PessoaFisica;

/**
 *
 * @author Gustavo
 */
public class TratamentoDados {
    
    private static final String SQL_RECUP_CIDADE = "SELECT * FROM dom_municipio WHERE idmunicipio = ?;";
    private static final String SQL_RECUP_ESTADO = "SELECT * FROM dom_uf WHERE iduf = ?;";
    private static final String SQL_PESQUISA_PIS = "SELECT idtipoinscricao, numeroinscricao FROM bpm_dadospessoafisica WHERE idpessoa = ?";
    
    PropertiesJdbc jdbc = new PropertiesJdbc();
    
    /*Strings de url*/
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    /**
     * Converte os valores Strings para inteiros
     * @param string
     * @return Retorna o valor em inteiro (int)
     */
    public int converterSrintInt (String string){
        int valor;
        if (string != null){
            valor = Integer.parseInt(string);
        } else {
            valor = 0;            
        }        
        return valor;
    }
    /**
     * Converte o inteiro int para short
     * @param inteiro
     * @return short
     */
    private short converterIntShort (int inteiro){
        short valor;
        valor = (short) inteiro;              
        return valor;
    } 
    
    public String convertIntToString (int tipo) throws JsageImportException{
        String lTipo = Integer.toString(tipo);
        
        return lTipo;
    }
    
    public short convertStringToShort (String zona) throws JsageImportException{
        short s= 0;
        if (zona != null){
            String g = zona;     
            s = Short.parseShort(g); 
        }        
        return s;
    }
    
    public String recuperarTipoDeficiente(int deficiencia)throws JsageImportException{
        String cid = "";
        if (deficiencia == 0) {
            return cid;
        } 
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGDOMINIO);
            stmt = con.prepareStatement(SQL_RECUP_CIDADE);
            stmt.setString(1, "%" + deficiencia + "%");
            rs = stmt.executeQuery();
            System.out.println(stmt);
            cid = rs.getString("nomemunicipio");
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return cid;
    }
    
    /**
     * Converte o int que representa a cidade para String
     * @param cidade
     * @return String
     * @throws JsageImportException 
     */
    public String recuperarCidade(int cidade)throws JsageImportException{
        String cid = "";
        if (cidade == 0) {
            return cid;
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGDOMINIO);
            stmt = con.prepareStatement(SQL_RECUP_CIDADE);
            
            stmt.setInt(1,  cidade );
            
            rs = stmt.executeQuery();
                       
            while(rs.next()){
                cid = rs.getString("nomemunicipio");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta para a cidade.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return cid;
    }
    
    public String recuperarUF (int iduf) throws JsageImportException{
        String est = "";
        if (iduf == 0) {
            return est;
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGDOMINIO);
            stmt = con.prepareStatement(SQL_RECUP_ESTADO);
            
            stmt.setInt(1,  iduf );
            
            rs = stmt.executeQuery();
                       
            while(rs.next()){
                est = rs.getString("nomeuf");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do estado.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return est;
    }
    private int tratarNrEndereco (String nr) throws JsageImportException{
        // remove os espaços na string que vem
        String stnr = nr;
        stnr = stnr.trim();
        stnr = stnr.replaceAll(" ","  ");
        // #### fim da remoção #####
        int nri = 0;
        String c = "/";
        String d = ",";
        int pos = stnr.indexOf(c);
        
        if (stnr.equals("sn") || stnr.equals("SN")||stnr.equals("S/N")||stnr.equals("s/n")||stnr.isEmpty()){
            return nri;                
        } 
        if (pos != -1){
                String pc = stnr.substring(0, pos);
                nri = Integer.parseInt(pc);                
        } else {
            nri = Integer.parseInt(stnr);            
        }
                
        return nri;                
    }
    
                       
    
    /**
     * Converte o cep em String para int
     * @param cep
     * @return int
     */
    private int recuperarCEP (String cep){
        int cepInt = 0;
        String stcep = cep;
        stcep = stcep.trim();
        String c = "-";
        int pos = stcep.indexOf(c);
        if (cep.equals("") || stcep.isEmpty()){
            return cepInt;
        } else if (pos == -1) {
            stcep = stcep.replace(c,"");
            cepInt = Integer.parseInt(stcep);
        }
        
        return cepInt;
    }
    
    public Timestamp horaAtual() throws JsageImportException{
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        return currentTimestamp;
    }
    
    public String recuperarPai (int idPessoa) throws JsageImportException{
        String nomePai;
        ControlerFuncionarioNG fun = new ControlerFuncionarioNG();
        List pai = fun.listarPai(idPessoa);
        if (pai.isEmpty()){
            nomePai = "semPai";
        } else {
            PessoaFisica pf = (PessoaFisica) pai.get(0);        
            nomePai = pf.getNomePessoa();
        }
                
        return nomePai;
        
    }
    
    public String recuperarMae (int idPessoa) throws JsageImportException{
        String nomeMae;
        ControlerFuncionarioNG fun = new ControlerFuncionarioNG();
        List mae = fun.listarMae(idPessoa);
        if(mae.isEmpty()){
            nomeMae = "semMae";
        } else {
            PessoaFisica pf = (PessoaFisica) mae.get(0);        
            nomeMae = pf.getNomePessoa();
        }       
        return nomeMae;
        
    }
    
    public int recuperarTituloEleitor(String titulo) throws JsageImportException{
        
        int tituloEleitor = 0;
        if (titulo != null){
             String str = "100";
            Integer intObj2 = Integer.valueOf(titulo);
            System.out.println(intObj2);
            tituloEleitor =Integer.parseInt(titulo, 10);
        }
        return tituloEleitor;
    }
    
    private int recuperarCNH(String cnh) throws JsageImportException{
        int cn = 0;
        
        if(cnh != null){
            cn = Integer.parseInt(cnh);
        }        
        return cn;
    }
    
    public String tratarOrgaoRG (String rg) throws JsageImportException{
        String normalRG = "";
        if (rg != null){
            String stnr = rg;
            stnr = stnr.trim();
            stnr = stnr.replaceAll(" ","");
            stnr = stnr.replace("'", "");
            normalRG = stnr;
        }
        return normalRG;
    }
    
    public String recuperarPIS (int idFuncionario)throws JsageImportException{
        String pis = "";
        if (idFuncionario == 0) {
            return pis;
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_PESQUISA_PIS);
            
            stmt.setInt(1,  idFuncionario );
            
            rs = stmt.executeQuery();
                       
            while(rs.next()){
                int tipoInscricao = 0;
                tipoInscricao = rs.getInt("idtipoinscricao");
                if (tipoInscricao == 1){
                  pis = rs.getString("numeroinscricao");  
                }                
            }
            
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta para a cidade.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return pis;
    }
}
