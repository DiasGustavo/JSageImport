/*
 * Trata a persistencia dos dados das empresas no banco NG 
 */
package jsageImport.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jsageImport.controler.ControlerFuncionarioSAGE;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaNG;

/**
 * @author Gustavo Dias
 * Criação: 21/10/2016
 * Última modificação: 22/10/2016
 * Modificado por: Gustavo Dias
 */
public class PersistenciaEmpresaNG implements IPersistenciaEmpresaNG {
    private PropertiesJdbc jdbc = new PropertiesJdbc();
    
   /*
    * String SQL para consultas no banco NG  
    * Strings SQL para informações das empreas no NG
    */
    
    private static final String SQL_PESQUISARTODOS ="SELECT * FROM bpm_dadospessoajuridica";
    private static final String SQL_RECUP_EMPRESA_FUN = "SELECT DISTINCT idowner FROM flh_registro";
    private static final String SQL_EMPRESA_COM_FUN = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              "WHERE (P.idpessoa = ?);";
    private static final String SQL_EMPRESA = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              "WHERE (pj.cnpj <> '');";
    private static final String SQL_EMPRESA_CNPJ = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              " WHERE (pj.cnpjformatado = ?);";
    private static final String SQL_EMPRESA_FOLHA = "SELECT DISTINCT * FROM (bpm_dadosempresafolha AS efolha INNER JOIN bpm_dadosempresafolhaparametrogeral AS fgeral "
                                                                        + "ON efolha.iddadosempresafolha = fgeral.iddadosempresafolha INNER JOIN bpm_dadosempresafolhaparametro13salario AS fsalario "
                                                                        + "ON efolha.iddadosempresafolha = fsalario.iddadosempresafolha INNER JOIN bpm_dadosempresafolhaparametroesocial AS fesocial "
                                                                        + "ON fesocial.iddadosempresafolha = efolha.iddadosempresafolha INNER JOIN bpm_dadosempresafolhaparametroferias AS fferias " 
                                                                        + "ON efolha.iddadosempresafolha = fferias.iddadosempresafolha LEFT JOIN NG.dbo.bpm_dadosempresafolhasindicato AS fsindicato "
                                                                        + "ON efolha.iddadosempresafolha = fsindicato.iddadosempresafolha) " 
                                                                        + "WHERE (efolha.iddadosempresafolha = ?);";
    /*url para conexao com o banco do ng*/    
    //jdbc:sqlserver://servidor:porta;databaseName=banco;user=usuario;password=senha;"
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";";
    /**
     * Pesquisa a lista de empresas presentes no banco de dados do banco NG
     * @return List
     * @throws JsageImportException 
     */
    @Override
    public List pesquisarTodos() throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_PESQUISARTODOS);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                PessoaJuridica fun = criarEmpresaNG(rs);
                listaFuncionarios.add(fun);
            }
            return listaFuncionarios;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos dados da empresa.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * Recupera todas as empresas com suas informações cadastrais
     * @return List
     * @throws JsageImportException 
     */
    @Override
    public List recuperarEmpresas() throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_EMPRESA);
            rs = stmt.executeQuery();
            List listaEmpresas = new ArrayList();
            while (rs.next()) {
                PessoaJuridica emp = criarEmpresaNG(rs);
                listaEmpresas.add(emp);
            }
            return listaEmpresas;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta das Empresas.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * Retorna as empresas que possuem folha de pagamento no banco NGFOLHA
     * @return List
     * @throws JsageImportException 
     */
    private List recuperarEmpresasComFun () throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_RECUP_EMPRESA_FUN);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                listaFuncionarios.add(rs.getInt("idowner"));
            }
            return listaFuncionarios;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta das Empresas no ng_folha.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * Recupera de uma empresa que possui folha de pagamento específica os seus dados cadastrais
     * @return
     * @throws JsageImportException 
     */
    @Override
    public List capturarInfoEmpresasComFun() throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List listaFuncionarios = new ArrayList();
            List listaConsulta = recuperarEmpresasComFun();
            if (listaConsulta.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_EMPRESA_COM_FUN);
            
                for (int i = 0; i < listaConsulta.size(); i++ ){
                    int idpessoa = (int)listaConsulta.get(i);
                    stmt.setInt(1,idpessoa);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        PessoaJuridica pj = criarEmpresaNG(rs);
                        listaFuncionarios.add(pj);
                    }
                } 
            }else{
                throw new JsageImportException("Não foi encontrado Empreasas!");
            }
                        
            
            return listaFuncionarios;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * Pesquisa as informações de uma empresa específica através do seu cnpj
     * @param cnpj
     * @return List
     * @throws JsageImportException 
     */
    private List pesquisarCnpj(String cnpj) throws JsageImportException {
        if (cnpj == null || cnpj.isEmpty()) {
            return recuperarEmpresas();
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_EMPRESA_CNPJ);
            stmt.setString(1, cnpj );
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                PessoaJuridica pj = criarEmpresaNG(rs);
                listaFuncionarios.add(pj);
            }
            return listaFuncionarios;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    /**
     * Realiza a importação de uma empresa para o banco de dados do SAGE
     * @param cnpj
     * @throws JsageImportException 
     */
    @Override
    public void ImportaEmpresas(String cnpj) throws JsageImportException {
        ControlerFuncionarioSAGE controlSAGE = new ControlerFuncionarioSAGE();
        List listaEmpresaSAGE = controlSAGE.pesquisarCNPJ(cnpj);
        if (listaEmpresaSAGE.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empresa precisa ser primeiro cadastrada no SAGE\n para importar os seus Funcionários!");
            //throw new JsageImportException("Primeiro Cadastre a Empresa no SAGE\n para Depois importar os Funcionários.");
            int reply = JOptionPane.showConfirmDialog(null, "Empresa de CNPJ: "+cnpj+" não esta cadastrada no SAGE, Deseja Importar agora?", "Aviso de importação", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                List listaEmpresa = pesquisarCnpj(cnpj);//Pesquisar no banco de dados do NG
                PessoaJuridica pjGravar =(PessoaJuridica) listaEmpresa.get(0);
                controlSAGE.gravarEmpresa(pjGravar);
                JOptionPane.showMessageDialog(null, "Empresa Gravada com Sucesso!");
            }else if (reply == JOptionPane.NO_OPTION){
                throw new JsageImportException("Primeiro importe a empresa para depois importar os Funcionários.");
            }
                       
        }
    }
    /**
     * Cria uma pessoa juridica que é a reprensentação de empresa no NG
     * @param ResultSet
     * @return PessoaJuridica
     * @throws JsageImportException 
     */
    private PessoaJuridica criarEmpresaNG(ResultSet rs) throws JsageImportException{
        PessoaJuridica pj = new PessoaJuridica();
        try{
            /* Dados de uma pessoa comum*/
            pj.setIdPessoa(rs.getInt("idpessoa"));
            pj.setCodigoPessoa(rs.getString("codigopessoa"));
            pj.setNomePessoa(rs.getString("nomepessoa"));
            pj.setIndFabrica(rs.getBoolean("indfabrica"));
            pj.setObservacao(rs.getString("observacao"));
            pj.setInddadosConvertidos(rs.getBoolean("inddadosconvertidos"));
            pj.setIndDesativada(rs.getBoolean("inddesativada"));
            pj.setDataCadastramento(rs.getTimestamp("datacadastramento"));
            pj.setFotoPessoa(rs.getString("fotopessoa"));
            pj.setBiometria(rs.getBytes("biometria"));
            pj.setNumeroCei(rs.getString("numerocei"));
            pj.setCodigoExternoEmpresa(rs.getString("codigoexternoempresa"));
            pj.setCodigoExternoFilial(rs.getString("codigoexternofilial"));
            pj.setDocumentoEstrangeiro(rs.getString("documentoestrangeiro"));
            pj.setCno(rs.getString("cno"));
            pj.setCaepf(rs.getString("caepf"));
            pj.setIdtipocaepf(rs.getInt("idtipocaepf"));
            /*Dados da pessoa juridica*/
            pj.setNomeFantasia(rs.getString("nomefantasia"));
            pj.setCnpj(rs.getString("cnpj"));
            pj.setInscricaoEstadual(rs.getString("inscricaoestadual"));
            pj.setInscricaoMunicipal(rs.getString("inscricaomunicipal"));
            pj.setObjetoSocial(rs.getString("objetosocial"));
            pj.setNumeroRegistoJunta(rs.getString("numeroregistrojunta"));
            pj.setNomeJunta(rs.getString("nomejunta"));
            pj.setDataInicioAtividade(rs.getTimestamp("datainicioatividade"));
            pj.setDataTerminoSociedade(rs.getTimestamp("dataterminosociedade"));
            pj.setCapitalSocialInicial(rs.getDouble("capitalsocialInicial"));
            pj.setDataFundacao(rs.getTimestamp("datafundacao"));
            pj.setDataFimAtividade(rs.getTimestamp("datafimatividade"));
            pj.setNumeroProprietarios(rs.getInt("numeroproprietarios"));
            pj.setNirc(rs.getString("nirc"));
            pj.setIdNaturezaJuridica(rs.getInt("idnaturezajuridica"));
            pj.setNomeAbreviado(rs.getString("nomeabreviado"));
            pj.setIdQualificacaoEmpresa(rs.getInt("idqualificacaoempresa"));
            pj.setCodigoUfRama(rs.getString("codigosuframa"));
            pj.setIdTipoOrgaoPublico(rs.getInt("idtipoorgaopublico"));
            pj.setIdTipoEntidade(rs.getInt("idtipoentidade"));
            pj.setDataInicioInscricaoEstadual(rs.getTimestamp("datainicioinscricaoestadual"));
            pj.setCnpjFormatado(rs.getString("cnpjformatado"));
            pj.setIdIdentificadorInscricaoEstadualNfe(rs.getInt("idindicadorinscricaoestadualnfe"));
            pj.setIndEmpresaExterna(rs.getBoolean("indempresaexterna"));
            
        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Pessoa Jurídica.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return pj;
    }
    
}
