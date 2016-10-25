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
    private static final String SQL_RECUP_CNAE = "SELECT * FROM dom_cnae WHERE idcnae = ?;";
    private static final String SQL_RECUP_ESTADO = "SELECT * FROM dom_uf WHERE iduf = ?;";
    private static final String SQL_RECUP_CATCNH = "SELECT * FROM dom_categoriahabilitacaocnh WHERE idcategoriahabilitacaocnh = ?;";
    private static final String SQL_PESQUISA_PIS = "SELECT idtipoinscricao, numeroinscricao FROM bpm_dadospessoafisica WHERE idpessoa = ?";
    private static final String SQL_PESQUISA_ADMISSAO_CAGED = "SELECT * FROM dom_tipoadmissaocaged WHERE idtipoadmissaocaged = ?";
    private static final String SQL_PESQUISA_NATUREZA = "SELECT * FROM dom_naturezajuridica WHERE idnaturezajuridica = ?";
    private static final String SQL_AGENCIA = "SELECT * FROM bpm_dadosagencia WHERE iddadosagencia = ? ";
    private static final String SQL_BANCO = "SELECT * FROM bpm_dadosbanco WHERE iddadosbanco = ? ";
    private static final String SQL_CONTA = "SELECT * FROM bpm_dadosfuncionario WHERE idpessoa = ? ";
    
    
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
        if ((string != null) && (string.isEmpty() == false)){
            //valor = Integer.parseInt(string);
            valor = Integer.valueOf(string);
        } else {
            valor = 0;            
        }        
        return valor;
    }
    
    public int converterSrintIntCom0 (String string){
        int valor;
        String aux;
        if (string != null){
            aux = string.replace(" ", "");
            aux = aux.trim();
            System.out.println(string.substring(0, 1) +" - " +string.substring(1));
            //if (string.substring(0, 1).equals("0")){
                
                //valor = Integer.parseInt(string.substring(1));
                //valor = Integer.valueOf(string);
            //}else {
                valor = Integer.parseInt(aux);
            //}
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
    public short converterIntShort (int inteiro){
        short valor;
        try{
            valor = (short) inteiro;              
        } catch(Exception e){
            valor = 0;
        }
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
    public int trataID (int id) throws JsageImportException{
        int idTratada = id;
        
        if (id > 9999){
            idTratada = id/10;
        }
        return idTratada;
    }
    public int recuperarParentesco (int pat) throws JsageImportException{
        int parent = 0;
        if ( pat == 1){
            parent = 1;
        }
        if (pat == 2){
            parent = 2;
        }else{
            parent = 7;
        }
        return parent;
    }
    
    public int recuperarEsocial (int esocial) throws JsageImportException{
        int eso = 0;
        if (esocial == 1){
            eso = 1;
        }
        if (esocial == 2){
            eso = 3;
        }else{
            eso = 6;
        }
        
        return eso;
    }
    
    public int recuperarVinculo (int vinculo) throws JsageImportException{
        int vin = 10;
        if (vinculo == 18){
            vin = 10;
        }
        if (vinculo == 19){
            vin = 15;
        }
        if (vinculo == 20){
            vin = 20;
        }
        if (vinculo == 21){
            vin = 25;
        }
        if (vinculo == 22){
            vin = 30;
        }
        if (vinculo == 23){
            vin = 31;
        }
        if (vinculo == 24){
            vin = 35;
        }
        if (vinculo == 25){
            vin = 40;
        }
        if (vinculo == 26){
            vin = 50;
        }
        if (vinculo == 27){
            vin = 55;
        }
        if (vinculo == 28){
            vin = 60;
        }
        if (vinculo == 29){
            vin = 65;
        }
        if (vinculo == 30){
            vin = 70;
        }
        if (vinculo == 31){
            vin = 75;
        }
        if (vinculo == 32){
            vin = 80;
        }
        if (vinculo == 33){
            vin = 90;
        }
        if (vinculo == 34){
            vin = 95;
        }
        if (vinculo == 36 || vinculo == 37){
            vin = 97;
        }
        return vin;
    }
    public short recuperarEstadoCivil (int estado) throws JsageImportException{
        short resultado = 0;
        if (estado == 1){
            resultado = 2;
        }            
        if (estado == 2){
            resultado = 1;
        }            
        if (estado == 3){
            resultado = 5;
        }
        if (estado == 4){
            resultado = 3;
        }
        if (estado == 5){
            resultado = 4;
        }
        if (estado == 6){
            resultado = 1; 
        }
        return resultado;
    }
    
    public String recuperarRaca (int raca) throws JsageImportException{
        String ra ="";
        if(raca == 1){
            ra = "1";
        }
        if(raca == 2){
            ra = "2";
        }
        if (raca == 3){
            ra = "9";
        }
        if (raca == 4){
            ra = "6";
        }
        if (raca == 5){
            ra = "8";
        }
        if (raca == 6){
            ra = "9";
        }
        return ra;
    }
    
    public String recuperaTipoSalario (int tipo) throws JsageImportException {
        String tipoSalario = "M";
        if( tipo == 1 ){
            tipoSalario = "M";
        }
        if ( tipo == 5){
            tipoSalario = "H";
        }else{
            tipoSalario = "M";
        }
        
        return tipoSalario;
    }
    
    public Double trataSalario (Double vl){
        Double vlfixo = 0.0;
        if (vl != null){
            vlfixo = vl;
        }
        return vlfixo;
    }
    
    public Timestamp trataData (Timestamp dt) throws JsageImportException{
        Timestamp data = dt;
        if (dt == null){
            data = this.horaAtual();
        }
        return data;
    }
    
    public String recuperarAdmissaoCaged (int idTipoAdmissao) throws JsageImportException {
        String admissao = "";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGDOMINIO);
            stmt = con.prepareStatement(SQL_PESQUISA_ADMISSAO_CAGED);
            stmt.setInt(1, idTipoAdmissao);
            rs = stmt.executeQuery();
            
            //System.out.println(rs.getString("codigotipoadmissaocaged"));
            while(rs.next()){
                admissao = rs.getString("codigotipoadmissaocaged");
            }
            
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da admissao caged.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return admissao;
    }
    
    public int trataTipoAdmissaoCaged ( String caged) throws JsageImportException{
        int tipoAd = 10;
        if (caged.equals("1")){
            tipoAd = 10;
        }
        if (caged.equals("2")){
            tipoAd = 20;
        }
        if (caged.equals("3")){
            tipoAd = 25;
        }
        if (caged.equals("4")){
            tipoAd = 10;
        }
        if (caged.equals("5")){
            tipoAd = 70;
        }
        
        return tipoAd;
    }
    
    public String recuperarCnaeEmpresa (int idcnae) throws JsageImportException {
        String cnae = "";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGDOMINIO);
            stmt = con.prepareStatement(SQL_RECUP_CNAE);
            
            stmt.setInt(1,  idcnae );
            
            rs = stmt.executeQuery();
                       
            while(rs.next()){
                cnae = rs.getString("subclasse");
            }
            cnae = cnae.replace("-", "");
            cnae = cnae.replace("/", "");
            
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do cnae.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        return cnae;
    }
    
    public short recuperarFormaTributacao (int tributacao) throws JsageImportException{
        short tributo;
        switch (tributacao){
            case 21:
                tributo = 0;
                break;
            case 20:
                tributo = 1;
                break;
            case 22:
                tributo = 2;
                break;
            case 27:
                tributo = 3;
                break;
            case 23:
                tributo = 4;
                break;
            case 24:
                tributo = 5;
                break;
            default:
                tributo = 6;
        }       
        return tributo;
    }
    
    public String recuperarNaturezaJuridica (int natureza)throws JsageImportException{
        String cid = "";
        if (natureza == 0) {
            return cid;
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGDOMINIO);
            stmt = con.prepareStatement(SQL_PESQUISA_NATUREZA);
            
            stmt.setInt(1,  natureza );
            
            rs = stmt.executeQuery();
                       
            while(rs.next()){
                cid = rs.getString("codigonaturezajuridica");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da natureza juridica.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return cid;
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
            stmt.setInt(1, deficiencia );
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
                StringBuffer mensagem = new StringBuffer("Não foi possível recuperar a cidade.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        if (cid.length() > 19){
            cid = cid.substring(0, 18);
        }
        return cid;
    }
    
    public String trataLogradouro (String logradouro){
        String cert="";
        if (logradouro.length()> 40){
            cert = logradouro.substring(0, 39);
        }
        cert = logradouro;
        return cert;
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
                est = rs.getString("abreviaturauf");
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
    
    public String recuperarEstado (int iduf) throws JsageImportException{
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
                StringBuffer mensagem = new StringBuffer("Não foi possível recuperar o Estado.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        if (est.length() > 2){
            est = est.substring(0, 2);
        }
        return est;
    }
    public int tratarNrEndereco (String nr) throws JsageImportException{
        int nri = 0;
        
        try{
            if (nr == null){
                return nri;
            }
            // remove os espaços na string que vem
            String stnr = nr;
            stnr = stnr.trim();
            stnr = stnr.replaceAll(" ","  ");
            // #### fim da remoção #####        
            String c = "/";
            String d = ",";
            int pos = stnr.indexOf(c);
        
            if (stnr.equals("sn") || stnr.equals("SN")||stnr.equals("S/N")||stnr.equals("s/n")||stnr.isEmpty()){
                return nri;                
            } 
            if (pos != -1){
                String pc = stnr.substring(0, pos);
                pc = pc.substring(0, 5);
                nri = Integer.parseInt(pc);                
            } else {
                nri = Integer.parseInt(stnr);            
            }
        } catch (Exception exc) {
                nri = 0;
         }
        
                
        return nri;                
    }
    
    public String recuperarComplemento (String complemento) throws JsageImportException{
        String ap= "";
        if (complemento != null){
            if(complemento.length() > 15){
                ap = complemento.substring(0, 14);
            } else {
                ap = complemento;
            }
        }        
        return ap;
    }
    
    public String recuperarBairro (String bairro) throws JsageImportException{
        String ap= "";
        if (bairro != null){
            if(bairro.length() > 15){
                ap = bairro.substring(0, 14);
            } else {
                ap = bairro;
            }
        }
        if (ap.length()> 19){
            ap = ap.substring(0, 19);
        }
        return ap;
    }
    
    /**
     * Converte o cep em String para int
     * @param cep
     * @return int
     */
    public int recuperarCEP (String cep){
        int cepInt = 0;
        String stcep = cep;
        stcep = stcep.trim();
        String c = "-";
        int pos = stcep.indexOf(c);
        if (cep.equals("") || stcep.isEmpty()){
            return cepInt;
        } else if (pos == -1) {
            stcep = stcep.replace(c,"");
            stcep = stcep.replace(" ", "");
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
    
    public String recuperaApelido (String apelido) throws JsageImportException{
        String ap= "";
        if (apelido != null){
            if (apelido.length() > 10){
                ap = apelido.substring(0, 8);
            }
            ap = apelido;
        }
        
        return ap;
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
   
    public String recuperarCNH(int cnh) throws JsageImportException{
       String categoria = "";
        if (cnh == 0) {
            return categoria;
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGDOMINIO);
            stmt = con.prepareStatement(SQL_RECUP_CATCNH);
            
            stmt.setInt(1,  cnh );
            
            rs = stmt.executeQuery();
                       
            while(rs.next()){
                categoria = rs.getString("descricaocategoriahabilitacaocnh");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da categoria da cnh.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return categoria;
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
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do PIS.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return pis;
    }
    
    public String recuperarAgencia (int idAgencia) throws JsageImportException {
        String admissao = "";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_AGENCIA);
            stmt.setInt(1, idAgencia);
            rs = stmt.executeQuery();
            while (rs.next()){
                admissao = rs.getString("codigoagencia");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da agencia.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return admissao;
    }
    
    public String recuperarDVAgencia (int idAgencia) throws JsageImportException {
        String admissao = "";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_AGENCIA);
            stmt.setInt(1, idAgencia);
            rs = stmt.executeQuery();
            while(rs.next()){
                admissao = rs.getString("numdvagencia");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do dv da agência.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return admissao;
    }
    
    public String recuperarBanco (int idBanco) throws JsageImportException {
        String admissao = "";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_BANCO);
            stmt.setInt(1, idBanco);
            rs = stmt.executeQuery();
            while (rs.next()){
                admissao = rs.getString("codigobanco");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do banco.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return admissao;
    }
    
   
    public String recuperarConta (int idPessoa) throws JsageImportException {
        String admissao = "";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_CONTA);
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
            while (rs.next()){
                admissao = rs.getString("numcontabancaria");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da da conta.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return admissao;
    }
    
    public String recuperarDVConta (int idPessoa) throws JsageImportException {
        String admissao = "";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_CONTA);
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
            while(rs.next()){
                admissao = rs.getString("numdvcontabancaria");
            }
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta o dv da conta.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        
        return admissao;
    }
}