/*
 * Classe referente a persistencia de dados de funcionários
 */
package jsageImport.modelo.persistencia;

import jsageImport.log.LogSage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import jsageImport.controler.ControlerFuncionarioSAGE;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionaisNG;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.FeriasNG;
import jsageImport.modelo.dominio.MovimentacaoNG;
import jsageImport.modelo.dominio.PessoaFisica;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioNG;

/**
 * @author Gustavo Dias
 * Criação: 06/06/2016
 * Última modificação: 22/10/2016
 * Modificado por: Gustavo Dias
 */
public class PersistenciaFuncionarioNG implements IPersistenciaFuncionarioNG {
    
    private PropertiesJdbc jdbc = new PropertiesJdbc();
    private TratamentoDados trataDados = new TratamentoDados();
    private LogSage logarq = new LogSage();
    
    /*String SQL para consultas no banco NG*/
    
    /*Strings SQL para informações das empreas no NG*/
    
    private static final String SQL_PESQUISARTODOS ="SELECT * FROM bpm_dadospessoajuridica";
    private static final String SQL_PESQUISAREMPRESAID ="SELECT * FROM bpm_dadospessoajuridica " +"WHERE idpessoa = ?";
    
    private static final String SQL_EMPRESA = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              " WHERE (pj.cnpj <> '');";    
    
    /*Strings SQL para informações dos funcionarios no NG*/
    
    private static final String SQL_PESQUISAR_FUNCIONARIO = "SELECT * FROM " + "bpm_pessoa as pes" +" INNER JOIN bpm_dadospessoafisica as pesf ON pes.idpessoa = pesf.idpessoa" 
                                                                            + "	LEFT JOIN bpm_pessoaendereco as ed ON pes.idpessoa = ed.idpessoa"
                                                                            + "	LEFT JOIN bpm_dadosfuncionario as dfun ON pes.idpessoa = dfun.idpessoa"
                                                                            + " LEFT JOIN bpm_dadosbanco as bd ON dfun.iddadosbanco = bd.iddadosbanco"
                                                                            + " LEFT JOIN bpm_dadosagencia as ag ON bd.iddadosbanco = ag.iddadosbanco"
                                                                            + " LEFT JOIN bpm_contabancaria as conta ON ag.iddadosagencia = conta.iddadosagencia"
                                                                            + " WHERE pes.idpessoa = ?"
                                                                            + " ORDER BY pes.nomepessoa ASC";
    
    private static final String SQL_RECUP_FUNCIONARIOS = "SELECT idpessoaregistro  FROM flh_registro WHERE (idtipoadmissao <> 0 AND datademissao is null) AND idowner = ?"; 
    private static final String SQL_RECUP_DEPENDENTES = "SELECT idpessoa FROM (bpm_pessoa AS p INNER JOIN bpm_pessoarelacionamento AS pr ON p.idpessoa = pr.idpessoaprincipal)"
                                                                + " WHERE pr.idpessoasecundaria = ? AND idtiporelacionamentopessoa = 23";
    
    private static final String SQL_PESQUISAR_DEPENDENTES = "SELECT * FROM (ng.dbo.bpm_pessoa as pes " +
                                                                            " LEFT JOIN ng.dbo.bpm_dadospessoafisica as pesf ON pes.idpessoa = pesf.idpessoa " +
                                                                            " LEFT JOIN ng.dbo.bpm_dadosdependente as dep ON pes.idpessoa = dep.idpessoa " +
                                                                            " LEFT JOIN ng.dbo.bpm_dadosdependentedmed as depdmed  on dep.idpessoa = depdmed.idpessoa " +
                                                                            " LEFT JOIN ng.dbo.bpm_dadosdependenteirrf as depirrf on dep.iddadosdependente = depirrf.iddadosdependente)" +
                                                            "WHERE pes.idpessoa = ?";
    
    private static final String SQL_RECUP_PAI_ID = "SELECT idpessoa FROM (bpm_pessoa AS p INNER JOIN bpm_pessoarelacionamento AS pr ON p.idpessoa = pr.idpessoaprincipal)"
                                                                + " WHERE pr.idpessoasecundaria = ? AND idtiporelacionamentopessoa = 2";
    private static final String SQL_PESQUISAR_PAI_INFO = "SELECT * FROM " + "bpm_pessoa as pes" +" INNER JOIN bpm_dadospessoafisica as pesf ON pes.idpessoa = pesf.idpessoa" 
                                                                             + " WHERE pes.idpessoa = ?";
                                                                             
    private static final String SQL_RECUP_MAE_ID = "SELECT idpessoa FROM (bpm_pessoa AS p INNER JOIN bpm_pessoarelacionamento AS pr ON p.idpessoa = pr.idpessoaprincipal)"
                                                                + " WHERE pr.idpessoasecundaria = ? AND idtiporelacionamentopessoa = 3";
    
    private static final String SQL_PESQUISAR_MAE_INFO = "SELECT * FROM " + "bpm_pessoa as pes" +" INNER JOIN bpm_dadospessoafisica as pesf ON pes.idpessoa = pesf.idpessoa" 
                                                                             + " WHERE pes.idpessoa = ?";
                                                                             
    private static final String SQL_DADOS_FUNCIONAIS = "SELECT *  FROM (flh_registro AS fl LEFT JOIN flh_movimentosalario AS flSal ON fl.idregistro = flSal.idregistro " 
                                                                        + " LEFT JOIN  flh_movimentocargo AS flcargo ON fl.idregistro = flcargo.idregistro ) "
                                                                        + " LEFT JOIN  flh_movimentosindicato AS flsind ON fl.idregistro = flsind.idregistro"
                                                                        + " WHERE fl.idtipoadmissao <> 0 AND fl.idpessoaregistro = ?";
    
    private static final String SQL_CONSULTA_SALARIO = "SELECT *  FROM (flh_registro AS fl LEFT JOIN flh_movimentosalario AS flSal ON fl.idregistro = flSal.idregistro " 
                                                                        + " LEFT JOIN  flh_movimentocargo AS flcargo ON fl.idregistro = flcargo.idregistro ) "
                                                                        + " LEFT JOIN  flh_movimentosindicato AS flsind ON fl.idregistro = flsind.idregistro"
                                                                        + " WHERE fl.idtipoadmissao <> 0 AND fl.idpessoaregistro = ?"
                                                                        + " ORDER BY flSal.idmovimentosalario asc";
    
    private static final String SQL_CONSULTA_FERIAS = "SELECT * FROM flh_registro AS fl "+ 
                                                                " INNER JOIN flh_ferias AS flFerias ON fl.idregistro = flFerias.idregistro" +
                                                                " WHERE idpessoaregistro = ?" +
                                                                " order by flFerias.datainicioferias asc";
    
    private static final String SQL_MOVIMENTACAO = "SELECT * FROM (flh_registro as registro INNER JOIN flh_folhapay as folhapay on registro.idregistro = folhapay.idregistro " +
                                                                   " INNER JOIN flh_folhapayverba as payverba on folhapay.idpay = payverba.idpay) " +
                                                                   " WHERE registro.idregistro = ? and folhapay.competenciaano = ? and folhapay.competenciames = ?";
    private static final String SQL_IDREGISTRO = "SELECT DISTINCT idregistro FROM flh_registro where idpessoaregistro = ?";
    
    private static final String SQL_MESES_COMPETENCIA_ANO = "SELECT DISTINCT competenciames FROM flh_folhapay " +
                                                                  "  WHERE idregistro = ? AND competenciaano = ?";
    
    /*url para conexao com o banco do ng*/    
    //jdbc:sqlserver://servidor:porta;databaseName=banco;user=usuario;password=senha;"
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    
    
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
                DadosFuncionario fun = criarFuncionarioNG(rs);
                listaFuncionarios.add(fun);
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
    
    private List recuperarFuncionariosNG (int idEmpresa) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_RECUP_FUNCIONARIOS);
            stmt.setInt(1, idEmpresa);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                listaFuncionarios.add(rs.getInt("idpessoaregistro"));
            }
            return listaFuncionarios;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos funcionarios no ng_folha.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    @Override
    public List capturarInfoFuncionariosNG (int idEmpresa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List listaFuncionarios = new ArrayList();
            List listaConsulta = recuperarFuncionariosNG(idEmpresa);
            if (listaConsulta.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_PESQUISAR_FUNCIONARIO);
            
                for (int i = 0; i < listaConsulta.size(); i++ ){
                    int idpessoa = (int)listaConsulta.get(i);
                    stmt.setInt(1,idpessoa);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        DadosFuncionario pf = criarFuncionarioNG(rs);
                        listaFuncionarios.add(pf);
                    }
                } 
            }else{
                throw new JsageImportException("Não foi encontrado Funcionarios para a empresa: " + idEmpresa);
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
    
    @Override
    public List pesquisaId(int id) throws JsageImportException {
        if (id == 0) {
            return recuperarEmpresas();
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_PESQUISAREMPRESAID);
            stmt.setInt(1, id );
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
    
    private List pesquisaIdFuncionario(int id) throws JsageImportException {
        if (id == 0) {
            return recuperarEmpresas();
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);            
            stmt = con.prepareStatement(SQL_PESQUISAR_FUNCIONARIO);
            stmt.setInt(1, id );
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                DadosFuncionario pf = criarFuncionarioNG(rs);
                listaFuncionarios.add(pf);
            }
            return listaFuncionarios;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Funcionário não encontrado.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }         
    
    private List pesquisarSalario(int idRegistro) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_CONSULTA_SALARIO);
            stmt.setInt(1, idRegistro );
            
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            
            while (rs.next()) {
                DadosFuncionaisNG pj = criarDadosFuncionaisNG(rs);
                listaFuncionarios.add(pj);
            }
            return listaFuncionarios;
            
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do salário.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    private List recuperarDependentes (int idPessoa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_RECUP_DEPENDENTES);
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                listaFuncionarios.add(rs.getInt("idpessoa"));
            }
            
            return listaFuncionarios;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos funcionarios no ng_folha.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    /**
     * Retorna os dependentes dos funcionarios repassados pela id
     * @param idPessoa
     * @return List
     * @throws JsageImportException 
     */
    @Override
    public List capturarInfoDependente (int idPessoa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List listaDependentes = new ArrayList();
            List listaConsulta = recuperarDependentes(idPessoa);            
            if (listaConsulta.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_PESQUISAR_DEPENDENTES);
                for (int i = 0; i < listaConsulta.size(); i++ ){
                    int idpessoa = (int)listaConsulta.get(i);
                    stmt.setInt(1,idpessoa);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        DependenteNG pf = criarDependenteNG(rs);
                        listaDependentes.add(pf);
                    }
                }
            }    
                       
            return listaDependentes;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    private List recuperarPai (int idPessoa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            
            stmt = con.prepareStatement(SQL_RECUP_PAI_ID);
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                listaFuncionarios.add(rs.getInt("idpessoa"));
            }            
            return listaFuncionarios;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos funcionarios no ng.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    /**
     * Retorna os dependentes dos funcionarios repassados pela id
     * @param idPessoa
     * @return List
     * @throws JsageImportException 
     */
    @Override
    public List capturarInfoPai (int idPessoa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List listaDependentes = new ArrayList();
            //recupera a id do pai
            List listaConsulta = recuperarPai(idPessoa);
            if (listaConsulta.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_PESQUISAR_PAI_INFO);
                for (int i = 0; i < listaConsulta.size(); i++ ){
                    int idpessoa = (int)listaConsulta.get(i);
                    stmt.setInt(1,idpessoa);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        PessoaFisica pf = criarPessoaFisica(rs);
                            listaDependentes.add(pf);
                    }
                }                 
            }           
            return listaDependentes;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    private List recuperarMae (int idPessoa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_RECUP_MAE_ID);
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                listaFuncionarios.add(rs.getInt("idpessoa"));
            }
            
            return listaFuncionarios;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos funcionarios no ng_folha.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * Retorna os dependentes dos funcionarios repassados pela id
     * @param idPessoa
     * @return List
     * @throws JsageImportException 
     */
    @Override
    public List capturarInfoMae (int idPessoa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List listaDependentes = new ArrayList();
            List listaConsulta = recuperarMae(idPessoa);
            if (listaConsulta.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_PESQUISAR_MAE_INFO);
                for (int i = 0; i < listaConsulta.size(); i++ ){
                    int idpessoa = (int)listaConsulta.get(i);
                    stmt.setInt(1,idpessoa);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        PessoaFisica pf = criarPessoaFisica(rs);
                        listaDependentes.add(pf);
                    }
                }                 
            }           
            return listaDependentes;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    /**
     * Realiza um teste de conexão com um bando de dados repassado
     * @param id
     * @return boolean
     * @throws JsageImportException 
     */
    
    @Override
    public List recuperarDadosFuncionais(int id) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_DADOS_FUNCIONAIS);
            stmt.setInt(1, id );
            
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                DadosFuncionaisNG dados = criarDadosFuncionaisNG(rs);
                listaFuncionarios.add(dados);
            }
            return listaFuncionarios;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos dados funcionais.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    public List recuperarFeriasFuncionario (int id) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_CONSULTA_FERIAS);
            stmt.setInt(1, id );
            
            rs = stmt.executeQuery();
            List listaFerias = new ArrayList();
            while (rs.next()) {
                FeriasNG dados = criarFeriasFuncionario(rs);
                listaFerias.add(dados);
            }
            return listaFerias;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos dados das férias.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    
    @Override
    public boolean TestaConexao(String server, String bd, String port, String user, String password) throws JsageImportException {
        Connection con = null;
        boolean flag = false;
        con = GerenciadorConexao.getConnection(server, bd, port, user, password);
        if (con != null){
            flag = true;
        }        
        return flag;
    } 
    
    public int recuperarIdRegistroFolha (int idPessoa) throws JsageImportException{
        int idregistro = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_IDREGISTRO);
            stmt.setInt(1, idPessoa );
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                idregistro = rs.getInt("idpessoaregistro");                
            }
            
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível recuperar a idRegistro da " + idPessoa+ " .");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
        return idregistro;
    }
    
    public List recuperarMesesMovimentacaoFolha (int idRegistro, int competenciaAno)throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_MESES_COMPETENCIA_ANO);
            stmt.setInt(1, idRegistro );
            stmt.setInt(2, competenciaAno);
            
            rs = stmt.executeQuery();
            List listaMeses = new ArrayList();
            while (rs.next()) {
                int mes = rs.getInt("competenciames");
                listaMeses.add(mes);
            }
            return listaMeses;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta oos meses de movimentacao.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    public List recuperarMovimentacaoFuncionario (int idPessoa, int competenciaAno, int competenciaMes) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            int idRegistro = recuperarIdRegistroFolha(idPessoa); // recupera a id da pessoa no banco folha
            stmt = con.prepareStatement(SQL_MOVIMENTACAO);
            stmt.setInt(1, idRegistro );
            stmt.setInt(2, competenciaAno);
            stmt.setInt(3, competenciaMes);
            
            rs = stmt.executeQuery();
            List listaMovimentacao = new ArrayList();
            while (rs.next()) {
                MovimentacaoNG dados = criarMovimentacaoNG(rs);
                listaMovimentacao.add(dados);
            }
            return listaMovimentacao;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da movimentacao do funcionario "+idPessoa+" .");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    @Override
    public String importaFuncionarios (int idEmpresa, int idPessoa, String nome) throws JsageImportException{
        String funcionario = "";
        String log = "";
        ControlerFuncionarioSAGE controlSAGE = new ControlerFuncionarioSAGE();
        
        if (nome.length()>0){          
        
            //verifica se o funcionario informado se encontra no banco sage
            List listaEmpresaSAGE = controlSAGE.pesquisarFuncionarioNome(idEmpresa, trataDados.trataGrandesString(nome,40));       
            // se existir o a lista vai ter um tamanho maior do que zero, portanto não entra no if            
            log = "Existentes no SAGE: " +idPessoa + " --- " + idEmpresa + " ----- " + nome;
            logarq.LogTxt(log, "PersisntenciaNG", "emp"+idEmpresa);               
            if (listaEmpresaSAGE.isEmpty()){
                
                //captura as informações do funcionário que a id foi informado
                List listaDadosFun = pesquisaIdFuncionario(idPessoa);
                List listaDadosFuncionais = recuperarDadosFuncionais (idPessoa);                
                //captura as informações dos dependente do funcionario indicado pela id
                List listaDependentes = capturarInfoDependente(idPessoa);                
                //verifica os salarios dos funcionarios
                List listaSalarios = pesquisarSalario(idPessoa);
                //captura as informações das ferias do funcionario
                List listaFerias = recuperarFeriasFuncionario(idPessoa);

                //captura as informações de pessoa física do funcionário
                //captura dos dados funcionais do funcinário
                DadosFuncionario pjGravar =(DadosFuncionario) listaDadosFun.get(0);              
                DadosFuncionaisNG funDadosFuncionais = (DadosFuncionaisNG) listaDadosFuncionais.get(0);
                    
                //o funcionário finalmente é gravado no banco sage
                controlSAGE.gravarFuncionario(idEmpresa, pjGravar, funDadosFuncionais);
                //gravar os documentos do funcionário no banco
                controlSAGE.gravarDocumentos(idPessoa, idEmpresa, pjGravar);
                //grava a lotação do funcionario indicado
                controlSAGE.gravarDadosLotacao( idPessoa, idEmpresa, funDadosFuncionais);
                //grava o funcionario como colaborador da empresa
                controlSAGE.gravarColaborador(idEmpresa, idPessoa, pjGravar);
                //grava a função do funcionario
                controlSAGE.gravarFuncao(idPessoa, idEmpresa, funDadosFuncionais);
                //grava os dados especificos do funcionario
                controlSAGE.gravarFunEspecifico(idPessoa, idEmpresa);
                
                
                // havendo salario do funcionario é gravado 
                /*if (listaSalarios.size()> 0){
                    for (int i = 0; i < listaSalarios.size(); i++){
                        DadosFuncionaisNG funFuncionais = (DadosFuncionaisNG) listaSalarios.get(i);
                        controlSAGE.gravarSalario(idPessoa, idEmpresa, funFuncionais);
                    }
                    
                }*/
                int iterador = 0;
                do{
                    if (listaSalarios.size()>iterador){
                        DadosFuncionaisNG funSalarioAtual = (DadosFuncionaisNG) listaSalarios.get(iterador);
                        if (iterador == 0){
                            controlSAGE.gravarSalario(idPessoa, idEmpresa, funSalarioAtual);
                        }else {
                            if(listaSalarios.iterator().hasNext()){
                            
                                DadosFuncionaisNG funSalarioAnt = (DadosFuncionaisNG) listaSalarios.get(iterador-1);
                        
                                Timestamp dataAtual = funSalarioAtual.getDataIncio();
                                Timestamp dataAnt = funSalarioAnt.getDataIncio();
                                if(trataDados.compararData(dataAnt, dataAtual)){
                                    controlSAGE.gravarSalario(idPessoa, idEmpresa, funSalarioAtual);
                                }
                            }
                            
                        }
                                           
                    }
                    iterador++;
                }while(iterador < listaSalarios.size());
               
                // gravar as ferias do funcionario
                /*if (listaFerias.size()>0){
                    for (int i=0; i < listaFerias.size(); i++){
                        FeriasNG ferias = (FeriasNG) listaFerias.get(i);
                        controlSAGE.gravarFerias(idPessoa, idEmpresa, ferias);
                    }
                }*/
                
                do{
                    if (listaFerias.size()>iterador){
                        FeriasNG funFeriasAtual = (FeriasNG) listaFerias.get(iterador);
                        if (iterador == 0){
                            controlSAGE.gravarSalario(idPessoa, idEmpresa, funFeriasAtual);
                        }else {
                            if(listaFerias.iterator().hasNext()){
                            
                                FeriasNG funFeriasAnt = (FeriasNG) listaFerias.get(iterador-1);
                        
                                Timestamp dataAtual = funFeriasAtual.getDataIncio();
                                Timestamp dataAnt = funFeriasAnt.getDataIncio();
                                if(trataDados.compararData(dataAnt, dataAtual)){
                                    controlSAGE.gravarFerias(idPessoa, idEmpresa, funFeriasAtual);
                                }
                            }
                            
                        }
                                           
                    }
                    iterador++;
                }while(iterador < listaSalarios.size());
                
                
                //gravar o controleEsocial
                controlSAGE.gravarControleESocial(idPessoa, idEmpresa);
                controlSAGE.gravarControleCamposESocial(idEmpresa, idPessoa);
                
                //gravar os dependentes do funcionário.                
                if(listaDependentes.size() > 0){
                    for (int i =0; i < listaDependentes.size(); i++){
                        DependenteNG dep = (DependenteNG) listaDependentes.get(i);
                        // Remover cadastros duplicados de dependentes no banco do SAGE.                       
                        List duplicadosDependentes = controlSAGE.pesquisarIdDependente(idPessoa, dep.getIdPessoa());
                        if (duplicadosDependentes.isEmpty()){
                            //gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep)
                            controlSAGE.gravarDependentes(idPessoa, idEmpresa, dep);
                        }
                    }                       
                }
                //gravar os dados funcionais do funcionário
                controlSAGE.gravarDadosFuncionais(idEmpresa, idPessoa, funDadosFuncionais, pjGravar);               
                
                
                log =  "Gravado o funcionario de código: "+idPessoa + " nome: " + nome;
                //System.out.print(log);                
                logarq.LogTxt(log, "PersisntenciaNG", "emp"+idEmpresa);               
            }
        }
        funcionario = "código: " + idPessoa + " Nome:" + nome;
        
        return funcionario;
    }
        
    
    @Override
    public int SizeImport() throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_PESQUISAR_FUNCIONARIO);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while(rs.next()){
                //FuncionarioSAGE fun = criarFuncionarioNG(rs);
                //listaFuncionarios.add(fun);
            }
            return listaFuncionarios.size();
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * Cria uma empresa vinda do banco NG
     * @param rs
     * @return Pessoa Juridica 
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
    
    private DadosFuncionario criarFuncionarioNG(ResultSet rs )throws JsageImportException{
        DadosFuncionario pf = new DadosFuncionario();
        try{
            /*Dados de uma pessoa Comum*/
            pf.setIdPessoa(rs.getInt("idpessoa"));
            pf.setCodigoPessoa(rs.getString("codigopessoa"));
            pf.setNomePessoa(rs.getString("nomepessoa"));
            pf.setIndFabrica(rs.getBoolean("indfabrica"));
            pf.setObservacao(rs.getString("observacao"));
            pf.setInddadosConvertidos(rs.getBoolean("inddadosconvertidos"));
            pf.setIndDesativada(rs.getBoolean("inddesativada"));
            pf.setDataCadastramento(rs.getTimestamp("datacadastramento"));
            pf.setFotoPessoa(rs.getString("fotopessoa"));
            pf.setBiometria(rs.getBytes("biometria"));
            pf.setNumeroCei(rs.getString("numerocei"));
            pf.setCodigoExternoEmpresa(rs.getString("codigoexternoempresa"));
            pf.setCodigoExternoFilial(rs.getString("codigoexternofilial"));
            pf.setDocumentoEstrangeiro(rs.getString("documentoestrangeiro"));
            pf.setCno(rs.getString("cno"));
            pf.setCaepf(rs.getString("caepf"));
            pf.setIdtipocaepf(rs.getInt("idtipocaepf"));
            pf.setLogradouro(rs.getString("logradouro"));
            pf.setNumeroEndereco(rs.getString("numeroendereco"));
            pf.setComplemento(rs.getString("complemento"));
            pf.setBairro(rs.getString("bairro"));
            pf.setCep(rs.getString("cep"));
            pf.setIdmunicipio(rs.getInt("idmunicipio"));
            
            /*Dados referentes a pessoa física*/
            pf.setIdCategoriaDocumentoMilitar(rs.getInt("idcategoriadocumentomilitar"));
            pf.setIdMunicipioNaturalidade(rs.getInt("idmunicipionaturalidade"));
            pf.setIdRaca(rs.getInt("idraca"));
            pf.setIdGrauInstrucao(rs.getInt("idgrauinstrucao"));
            pf.setIdEStadoCivil(rs.getInt("idestadocivil"));
            pf.setCpf(rs.getString("cpf"));
            pf.setNumeroDocumentoIdentidade(rs.getString("numerodocumentoidentidade"));
            pf.setDataExpedicaoDocumentoIdentidade(rs.getTimestamp("dataexpedicaodocumentoidentidade"));
            pf.setInscricaoInss(rs.getString("inscricaoinss"));
            pf.setOrgaoExpedidorDocumentoIdentidade(rs.getString("orgaoexpedidordocumentoidentidade"));
            pf.setDataNascimento(rs.getTimestamp("datanascimento"));
            pf.setApelido(rs.getString("apelido"));
            pf.setIndSexo(rs.getString("indsexo"));
            pf.setTituloEleitor(rs.getString("tituloeleitor"));
            pf.setZonaEleitoral(rs.getString("zonaeleitoral"));
            pf.setSecaoEleitoral(rs.getString("secaoeleitoral"));
            pf.setNumeroDocumentoMilitar(rs.getString("numerodocumentomilitar"));
            pf.setNumeroRegistroProfissional(rs.getString("numeroregistroprofissional"));
            pf.setOrgaoExpedidorRegistroProfissional(rs.getString("orgaoexpedidorregistroprofissional"));
            pf.setHabilitacaoProfissional(rs.getString("habilitacaoprofissional"));
            pf.setDataEmissaocnh(rs.getTimestamp("dataemissaocnh"));
            pf.setIdufcnh(rs.getInt("idufcnh"));
            pf.setNumeroCnh(rs.getString("numerocnh"));
            pf.setVencimentoCnh(rs.getTimestamp("vencimentocnh"));
            pf.setIdcategoriaHabilitacaoCnh(rs.getInt("idcategoriahabilitacaocnh"));
            pf.setDataChegada(rs.getTimestamp("datachegada"));
            pf.setIndConjugeBrasileiro(rs.getBoolean("indconjugebrasileiro"));
            pf.setIndNaturalizado(rs.getBoolean("indnaturalizado"));
            pf.setNumeroDecreto(rs.getString("numerodecreto"));
            pf.setTipoVisto(rs.getString("tipovisto"));
            pf.setNumeroFilhosBrasileiros(rs.getInt("numerofilhosbrasileiros"));
            pf.setRegistroGeralEstrangeiro(rs.getString("registrogeralestrangeiro"));
            pf.setValidadeIdentidade(rs.getTimestamp("validadeidentidade"));
            pf.setValidadeCtps(rs.getTimestamp("validadectps"));
            pf.setIdPaisNacionalidadeEstrangeiro(rs.getInt("idpaisnacionalidadeestrangeiro"));
            pf.setNomeUfNacionalidadeEstrangeiro(rs.getString("nomeufnacionalidadeestrangeiro"));
            pf.setNomeMunicipioNaciolidadeEstrangeiro(rs.getString("nomemunicipionacionalidadeestrangeiro"));
            pf.setIdTipoDeficiencia(rs.getInt("idtipodeficiencia"));
            pf.setIdTipoInscricao(rs.getInt("idtipoinscricao"));
            pf.setNumeroInscricao(rs.getString("numeroinscricao"));
            pf.setDataInscricao(rs.getTimestamp("datainscricao"));
            pf.setTipoCerdidaoCivil(rs.getString("tipocertidaocivil"));
            pf.setDataEmissaoCertidaoCivil(rs.getTimestamp("dataemissaocertidaocivil"));
            pf.setTermoMatriculaCertidaoCivil(rs.getString("termomatriculacertidaocivil"));
            pf.setLivroCertidaoCivil(rs.getString("livrocertidaocivil"));
            pf.setFolhaCertidaoCivil(rs.getString("folhacertidaocivil"));
            pf.setCartorioCertidaoCivil(rs.getString("cartoriocertidaocivil"));
            pf.setIdUfCertidaoCivil(rs.getInt("idufcertidaocivil"));
            pf.setIdMunicipioCertidaoCivil(rs.getInt("idmunicipiocertidaocivil"));
            pf.setNumeroPassaporte(rs.getString("numeropassaporte"));
            pf.setOrgaoEmissorPassaporte(rs.getString("orgaoemissorpassaporte"));
            pf.setIdUfPassaporte(rs.getInt("idufpassaporte"));
            pf.setDataEmissaoPassaporte(rs.getTimestamp("dataemissaopassaporte"));
            pf.setDataValidadePassaporte(rs.getTimestamp("datavalidadepassaporte"));
            pf.setIdPaisPassaporte(rs.getInt("idpaispassaporte"));
            pf.setNumeroric(rs.getString("numeroric"));
            pf.setIdUfRic(rs.getInt("idufric"));
            pf.setOrgaoEmissorRic(rs.getString("orgaoemissorric"));
            pf.setIdMunicipioRic(rs.getInt("idmunicipioric"));
            pf.setDataExpedicaoRic(rs.getTimestamp("dataexpedicaoric"));
            pf.setCpfFormatado(rs.getString("cpfformatado"));
            pf.setOrgaoEmissorCnh(rs.getString("orgaoemissorcnh"));
            pf.setDataExpedicaoRegistroProfissional(rs.getTimestamp("dataexpedicaoregistroprofissional"));
            pf.setDataValidadeRegistroProfissional(rs.getTimestamp("datavalidaderegistroprofissional"));
            pf.setDataNaturalizacao(rs.getTimestamp("datanaturalizacao"));
            pf.setNumeroRegistroNacionalEstrangeiro(rs.getString("numeroregistronacionalestrangeiro"));
            pf.setOrgaoEmissorRegistroNacionalEstrangeiro(rs.getString("orgaoemissorregistronacionalestrangeiro"));
            pf.setDataExpedicaoREgistroNacionalEstrangeiro(rs.getTimestamp("dataexpedicaoregistronacionalestrangeiro"));
            pf.setIdPaisNacionalidade(rs.getInt("idpaisnacionalidade"));
            pf.setListaIdTipoDeficienciaEsocial(rs.getString("listaidtipodeficienciaesocial"));
            pf.setObervacaoDeficiencia(rs.getString("observacaodeficiencia"));
            pf.setNumeroPortariaNaturalizacao(rs.getString("numeroportarianaturalizacao"));
            pf.setIdIndicadorInscricaoEstadualNfe(rs.getInt("idindicadorinscricaoestadualnfe"));
            pf.setDataPrimeiraHabilitacao(rs.getTimestamp("dataprimeirahabilitacao"));
            pf.setIdClassificacaoTrabalhadorEstrangeiro(rs.getInt("idclassificacaotrabalhadorestrangeiro"));
            
            /*Dados dos funcionarios*/
            pf.setIdDadosAgencia(rs.getInt("iddadosagencia"));
            pf.setIdDadosBanco(rs.getInt("iddadosbanco"));
            pf.setNumeroCtps(rs.getString("numeroctps"));
            pf.setSerieCtps(rs.getString("seriectps"));
            pf.setDataCtps(rs.getTimestamp("datactps"));
            pf.setIdUfCtps(rs.getInt("idufctps"));
            pf.setNumContaBancaria(rs.getString("numcontabancaria"));
            pf.setNumDvContaBancaria(rs.getString("numdvcontabancaria"));
            pf.setIdFormaPagamento(rs.getInt("idformapagamento"));
            pf.setIdCodigoDirf(rs.getInt("idcodigodirf"));
            pf.setIdTipoFuncionario(rs.getInt("idtipofuncionario"));
            pf.setIndAtivo(rs.getBoolean("indativo"));
            /*Dados bancarios*/
            pf.setCodigoagencia(rs.getString("codigoagencia"));
            pf.setNumdvagencia(rs.getString("numdvagencia"));
            pf.setIdtipocontabancaria(rs.getInt("idtipocontabancaria"));
            pf.setNumeroconta(rs.getString("numeroconta"));
            pf.setDigitoverificador(rs.getString("digitoverificador"));
            
        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Pessoa Fisica." + pf.getIdPessoa());
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return pf;
    }
    
    private DependenteNG criarDependenteNG (ResultSet rs) throws JsageImportException{
        DependenteNG dep = new DependenteNG();
        try{
             /*Dados de uma pessoa Comum*/
            dep.setIdPessoa(rs.getInt("idpessoa"));
            dep.setCodigoPessoa(rs.getString("codigopessoa"));
            dep.setNomePessoa(rs.getString("nomepessoa"));
            dep.setIndFabrica(rs.getBoolean("indfabrica"));
            dep.setObservacao(rs.getString("observacao"));
            dep.setInddadosConvertidos(rs.getBoolean("inddadosconvertidos"));
            dep.setIndDesativada(rs.getBoolean("inddesativada"));
            dep.setDataCadastramento(rs.getTimestamp("datacadastramento"));
            dep.setFotoPessoa(rs.getString("fotopessoa"));
            dep.setBiometria(rs.getBytes("biometria"));
            dep.setNumeroCei(rs.getString("numerocei"));
            dep.setIndSexo(rs.getString("indsexo"));
            dep.setCodigoExternoEmpresa(rs.getString("codigoexternoempresa"));
            dep.setCodigoExternoFilial(rs.getString("codigoexternofilial"));
            dep.setDocumentoEstrangeiro(rs.getString("documentoestrangeiro"));
            dep.setCno(rs.getString("cno"));
            dep.setCaepf(rs.getString("caepf"));
            dep.setIdtipocaepf(rs.getInt("idtipocaepf"));
            
            /*dados pessoa fisica*/
            dep.setDataNascimento(rs.getTimestamp("datanascimento"));
            dep.setCpfFormatado(rs.getString("cpfformatado"));
            
            /*Dados referentes excluisivamente ao dependente*/
            dep.setIndDependenteIrrf(rs.getBoolean("inddependenteirrf"));
            dep.setIndDependenteSalarioFamilia(rs.getBoolean("inddependentesalariofamilia"));
            dep.setIndDependentePensaoAlimenticia(rs.getBoolean("inddependentepensaoalimenticia"));
            dep.setIndIncapacitadoTrabalho(rs.getBoolean("indincapacitadotrabalho"));
            dep.setIndAtivo(rs.getBoolean("indativo"));
            dep.setIdowner(rs.getInt("idowner"));
            dep.setIndDependentePlanoSaude(rs.getBoolean("inddependenteplanosaude"));
            dep.setIdRelacaoDependenciaPlanoSaude(rs.getInt("idrelacaodependenciaplanosaude"));
            dep.setIdtipodependenciairrf(rs.getInt("idtipodependenciairrf"));
            
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Pessoa Fisica." + dep.getIdPessoa());
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return dep;
    }
    
    private PessoaFisica criarPessoaFisica(ResultSet rs )throws JsageImportException{
        PessoaFisica pf = new PessoaFisica();
        try{
            /*Dados de uma pessoa Comum*/
            pf.setIdPessoa(rs.getInt("idpessoa"));
            pf.setCodigoPessoa(rs.getString("codigopessoa"));
            pf.setNomePessoa(rs.getString("nomepessoa"));
            pf.setIndFabrica(rs.getBoolean("indfabrica"));
            pf.setObservacao(rs.getString("observacao"));
            pf.setInddadosConvertidos(rs.getBoolean("inddadosconvertidos"));
            pf.setIndDesativada(rs.getBoolean("inddesativada"));
            pf.setDataCadastramento(rs.getTimestamp("datacadastramento"));
            pf.setFotoPessoa(rs.getString("fotopessoa"));
            pf.setBiometria(rs.getBytes("biometria"));
            pf.setNumeroCei(rs.getString("numerocei"));
            pf.setCodigoExternoEmpresa(rs.getString("codigoexternoempresa"));
            pf.setCodigoExternoFilial(rs.getString("codigoexternofilial"));
            pf.setDocumentoEstrangeiro(rs.getString("documentoestrangeiro"));
            pf.setCno(rs.getString("cno"));
            pf.setCaepf(rs.getString("caepf"));
            pf.setIdtipocaepf(rs.getInt("idtipocaepf"));            
            
            /*Dados referentes a pessoa física*/
            pf.setIdCategoriaDocumentoMilitar(rs.getInt("idcategoriadocumentomilitar"));
            pf.setIdMunicipioNaturalidade(rs.getInt("idmunicipionaturalidade"));
            pf.setIdRaca(rs.getInt("idraca"));
            pf.setIdGrauInstrucao(rs.getInt("idgrauinstrucao"));
            pf.setIdEStadoCivil(rs.getInt("idestadocivil"));
            pf.setCpf(rs.getString("cpf"));
            pf.setNumeroDocumentoIdentidade(rs.getString("numerodocumentoidentidade"));
            pf.setDataExpedicaoDocumentoIdentidade(rs.getTimestamp("dataexpedicaodocumentoidentidade"));
            pf.setInscricaoInss(rs.getString("inscricaoinss"));
            pf.setOrgaoExpedidorDocumentoIdentidade(rs.getString("orgaoexpedidordocumentoidentidade"));
            pf.setDataNascimento(rs.getTimestamp("datanascimento"));
            pf.setApelido(rs.getString("apelido"));
            pf.setIndSexo(rs.getString("indsexo"));
            pf.setTituloEleitor(rs.getString("tituloeleitor"));
            pf.setZonaEleitoral(rs.getString("zonaeleitoral"));
            pf.setSecaoEleitoral(rs.getString("secaoeleitoral"));
            pf.setNumeroDocumentoMilitar(rs.getString("numerodocumentomilitar"));
            pf.setNumeroRegistroProfissional(rs.getString("numeroregistroprofissional"));
            pf.setOrgaoExpedidorRegistroProfissional(rs.getString("orgaoexpedidorregistroprofissional"));
            pf.setHabilitacaoProfissional(rs.getString("habilitacaoprofissional"));
            pf.setDataEmissaocnh(rs.getTimestamp("dataemissaocnh"));
            pf.setIdufcnh(rs.getInt("idufcnh"));
            pf.setNumeroCnh(rs.getString("numerocnh"));
            pf.setVencimentoCnh(rs.getTimestamp("vencimentocnh"));
            pf.setIdcategoriaHabilitacaoCnh(rs.getInt("idcategoriahabilitacaocnh"));
            pf.setDataChegada(rs.getTimestamp("datachegada"));
            pf.setIndConjugeBrasileiro(rs.getBoolean("indconjugebrasileiro"));
            pf.setIndNaturalizado(rs.getBoolean("indnaturalizado"));
            pf.setNumeroDecreto(rs.getString("numerodecreto"));
            pf.setTipoVisto(rs.getString("tipovisto"));
            pf.setNumeroFilhosBrasileiros(rs.getInt("numerofilhosbrasileiros"));
            pf.setRegistroGeralEstrangeiro(rs.getString("registrogeralestrangeiro"));
            pf.setValidadeIdentidade(rs.getTimestamp("validadeidentidade"));
            pf.setValidadeCtps(rs.getTimestamp("validadectps"));
            pf.setIdPaisNacionalidadeEstrangeiro(rs.getInt("idpaisnacionalidadeestrangeiro"));
            pf.setNomeUfNacionalidadeEstrangeiro(rs.getString("nomeufnacionalidadeestrangeiro"));
            pf.setNomeMunicipioNaciolidadeEstrangeiro(rs.getString("nomemunicipionacionalidadeestrangeiro"));
            pf.setIdTipoDeficiencia(rs.getInt("idtipodeficiencia"));
            pf.setIdTipoInscricao(rs.getInt("idtipoinscricao"));
            pf.setNumeroInscricao(rs.getString("numeroinscricao"));
            pf.setDataInscricao(rs.getTimestamp("datainscricao"));
            pf.setTipoCerdidaoCivil(rs.getString("tipocertidaocivil"));
            pf.setDataEmissaoCertidaoCivil(rs.getTimestamp("dataemissaocertidaocivil"));
            pf.setTermoMatriculaCertidaoCivil(rs.getString("termomatriculacertidaocivil"));
            pf.setLivroCertidaoCivil(rs.getString("livrocertidaocivil"));
            pf.setFolhaCertidaoCivil(rs.getString("folhacertidaocivil"));
            pf.setCartorioCertidaoCivil(rs.getString("cartoriocertidaocivil"));
            pf.setIdUfCertidaoCivil(rs.getInt("idufcertidaocivil"));
            pf.setIdMunicipioCertidaoCivil(rs.getInt("idmunicipiocertidaocivil"));
            pf.setNumeroPassaporte(rs.getString("numeropassaporte"));
            pf.setOrgaoEmissorPassaporte(rs.getString("orgaoemissorpassaporte"));
            pf.setIdUfPassaporte(rs.getInt("idufpassaporte"));
            pf.setDataEmissaoPassaporte(rs.getTimestamp("dataemissaopassaporte"));
            pf.setDataValidadePassaporte(rs.getTimestamp("datavalidadepassaporte"));
            pf.setIdPaisPassaporte(rs.getInt("idpaispassaporte"));
            pf.setNumeroric(rs.getString("numeroric"));
            pf.setIdUfRic(rs.getInt("idufric"));
            pf.setOrgaoEmissorRic(rs.getString("orgaoemissorric"));
            pf.setIdMunicipioRic(rs.getInt("idmunicipioric"));
            pf.setDataExpedicaoRic(rs.getTimestamp("dataexpedicaoric"));
            pf.setCpfFormatado(rs.getString("cpfformatado"));
            pf.setOrgaoEmissorCnh(rs.getString("orgaoemissorcnh"));
            pf.setDataExpedicaoRegistroProfissional(rs.getTimestamp("dataexpedicaoregistroprofissional"));
            pf.setDataValidadeRegistroProfissional(rs.getTimestamp("datavalidaderegistroprofissional"));
            pf.setDataNaturalizacao(rs.getTimestamp("datanaturalizacao"));
            pf.setNumeroRegistroNacionalEstrangeiro(rs.getString("numeroregistronacionalestrangeiro"));
            pf.setOrgaoEmissorRegistroNacionalEstrangeiro(rs.getString("orgaoemissorregistronacionalestrangeiro"));
            pf.setDataExpedicaoREgistroNacionalEstrangeiro(rs.getTimestamp("dataexpedicaoregistronacionalestrangeiro"));
            pf.setIdPaisNacionalidade(rs.getInt("idpaisnacionalidade"));
            pf.setListaIdTipoDeficienciaEsocial(rs.getString("listaidtipodeficienciaesocial"));
            pf.setObervacaoDeficiencia(rs.getString("observacaodeficiencia"));
            pf.setNumeroPortariaNaturalizacao(rs.getString("numeroportarianaturalizacao"));
            pf.setIdIndicadorInscricaoEstadualNfe(rs.getInt("idindicadorinscricaoestadualnfe"));
            pf.setDataPrimeiraHabilitacao(rs.getTimestamp("dataprimeirahabilitacao"));
            pf.setIdClassificacaoTrabalhadorEstrangeiro(rs.getInt("idclassificacaotrabalhadorestrangeiro"));
            
            
        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Pessoa Fisica." + pf.getIdPessoa());
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return pf;
    }
    
    private DadosFuncionaisNG criarDadosFuncionaisNG (ResultSet rs) throws JsageImportException{
        DadosFuncionaisNG dados = new DadosFuncionaisNG();
        try{
                        
            /*Dados funcionais do funcionario informado*/
            dados.setIdRegistro(rs.getInt("idregistro"));
            dados.setCodigoRegistro(rs.getString("codigoregistro"));
            dados.setInddemissao(rs.getBoolean("inddemissao"));
            dados.setDataDemissao(rs.getTimestamp("datademissao"));
            dados.setDataAdmissao(rs.getTimestamp("dataadmissao"));
            dados.setDataOpcaoFgts(rs.getTimestamp("dataopcaofgts"));
            dados.setIdTipoAdmissao(rs.getInt("idtipoadmissao"));
            dados.setIdTipoContratacao(rs.getInt("idtipocontratacao"));
            dados.setNumeroMatricula(rs.getString("numeromatricula"));
            dados.setNumeroDiasContratoExperiencia(rs.getInt("numerodiascontratoexperiencia"));
            dados.setIdTipoAdmissaoCaged(rs.getInt("idtipoadmissaocaged"));
            dados.setNumeroContaFgts(rs.getString("numerocontafgts"));
            dados.setNumeroDvContaFgts(rs.getInt("numerodvcontafgts"));
            dados.setIndAlteracaoSalarial(rs.getBoolean("indalteracaosalarial"));
            dados.setIndAlvaraJudicial(rs.getBoolean("indalvarajudicial"));
            dados.setIndDireitoReciproco(rs.getBoolean("inddireitoreciproco"));
            dados.setNumeroRegistro(rs.getString("numeroregistro"));
            dados.setIdReajusteTipo(rs.getInt("idreajustetipo"));
            dados.setValorReajuste(rs.getDouble("valorreajuste"));
            dados.setIdCategoriaSefip(rs.getInt("idcategoriasefip"));
            dados.setDataUltimoExameMedico(rs.getTimestamp("dataultimoexamemedico"));
            dados.setNumeroMesesIntervaloExameMedico(rs.getInt("numeromesesintervaloexamemedico"));
            dados.setIdVinculoEmpregaticio(rs.getInt("idvinculoempregaticio"));
            dados.setDataConcessaoBeneficio(rs.getTimestamp("dataconcessaobeneficio"));
            dados.setPercentualAdiantamento(rs.getDouble("percentualadiantamento"));
            dados.setObservacaoRegistro(rs.getString("observacaoregistro"));
            dados.setDataInicioPeriodoAquisitivoFeriasPendente(rs.getTimestamp("datainicioperiodoaquisitivoferiaspendente"));
            dados.setDataFimPeriodoAquisitivoFeriasPendente(rs.getTimestamp("datafimperiodoaquisitivoferiaspendente"));
            dados.setDataVigenciaAlteracaoSalarial(rs.getTimestamp("datavigenciaalteracaosalarial"));
            dados.setIndRecolheInss(rs.getBoolean("indrecolheinss"));
            dados.setIndRecebe13Salario(rs.getBoolean("indrecebe13salario"));
            dados.setIndComissionista(rs.getBoolean("indcomissionista"));
            dados.setIndRecolhefgts(rs.getBoolean("indrecolhefgts"));
            dados.setIndRecolhePrevidencia(rs.getBoolean("indrecolheprevidencia"));
            dados.setIndRecolheIrrf(rs.getBoolean("indrecolheirrf"));
            dados.setIndConsolidarFolhas(rs.getBoolean("indconsolidarfolhas"));
            dados.setIdTipoComissionista(rs.getInt("idtipocomissionista"));
            dados.setIndInformarrais(rs.getBoolean("indinformarrais"));
            dados.setIndCentralizaInss(rs.getBoolean("indcentralizainss"));
            dados.setDataUltimaContribuicaoSindical(rs.getTimestamp("dataultimacontribuicaosindical"));
            dados.setIdDadosPessoa(rs.getInt("iddadospessoa"));
            dados.setDescricaoComplementoSalario(rs.getString("descricaocomplementosalario"));
            dados.setIndSindicalizado(rs.getBoolean("indsindicalizado"));
            dados.setIndRegraApropriacao(rs.getBoolean("indregraapropriacao"));
            dados.setIdTipoRegra(rs.getInt("idtiporegra"));
            dados.setFichario(rs.getString("fichario"));
            dados.setIdTipoDiaTrabalho(rs.getInt("idtipodiatrabalhado"));
            dados.setIndConsiderarTambemMesesFeriasAfastamento(rs.getBoolean("indconsiderartambemmesesferiasafastamento"));
            dados.setIndConsiderarTambemMesAdmissao(rs.getBoolean("indconsiderartambemmesadmissao"));
            dados.setOrdemVinculado(rs.getInt("ordemvinculado"));
            dados.setIdRegistroOrigem(rs.getInt("idregistroorigem"));
            dados.setDataTransferencia(rs.getTimestamp("datatransferencia"));
            dados.setIndTipoInclusao(rs.getInt("indtipoinclusao"));
            dados.setIndMotivoContratacao(rs.getInt("indmotivocontratacao"));
            dados.setJustificativaContratacao(rs.getString("justificativacontratacao"));
            dados.setJustificativaProrrogacao(rs.getString("justificativaprorrogacao"));
            dados.setIndAposentado(rs.getInt("indaposentado"));
            dados.setIndConsederarDiasMesFolhaFerias(rs.getBoolean("indconsiderardiasmesfolhaferias"));
            dados.setIdRegistroSubstituido(rs.getInt("idregistrosubstituido"));
            dados.setIdTipoTransferenciaEntreEmpresa(rs.getInt("idtipotransferenciaentreempresa"));
            dados.setIdOnusCessao(rs.getInt("idonuscessao"));
            dados.setObservacaoSucessao(rs.getString("observacaosucessao"));
            dados.setIndSeguroDesemprego(rs.getBoolean("indsegurodesemprego"));
            dados.setIndStatusCagedAdmissional(rs.getBoolean("indstatuscagedadmissional"));
            dados.setStatuRecebimento(rs.getString("statusrecebimento"));
            dados.setIndConsiderarDiasMesApropriacaoHoras(rs.getBoolean("indconsiderardiasmesapropriacaohoras"));
            dados.setIndDescontarContribuicaoSindicalreferenteAnoAdmissao(rs.getBoolean("inddescontarcontribuicaosindicalreferenteanoadmissao"));
            dados.setInVisualizadaOnline(rs.getBoolean("indvisualizadadosonline"));
            dados.setSenhaVisualizaDadosOnline(rs.getBytes("senhavisualizadadosonline"));
            dados.setIndContribuicaoSindical(rs.getBoolean("indcontribuicaosindical"));
            dados.setInddescontarsestsenat(rs.getBoolean("inddescontarsestsenat"));
            dados.setIndConsiderarFolhaRescisao(rs.getBoolean("indconsiderarfolharescisao"));
            dados.setIdTipoPagamentoSalario(rs.getInt("idtipopagamentosalario"));
            dados.setIdTipoSalario(rs.getInt("idtiposalario"));
            dados.setDataIncio(rs.getTimestamp("datainicio"));
            dados.setDataFim(rs.getTimestamp("datafim"));
            dados.setSalario(rs.getDouble("salario"));
            dados.setDescricaoComplemento(rs.getString("descricaocomplemento"));
            dados.setPercentualVariacao(rs.getDouble("percentualvariacao"));
            dados.setMotivoMovimento(rs.getString("motivomovimento"));
            dados.setDatadissidio(rs.getTimestamp("datadissidio"));
            dados.setInddissidio(rs.getBoolean("inddissidio"));
            dados.setIdcargo(rs.getInt("idcargo"));
            dados.setIddadossindicato(rs.getInt("iddadossindicato"));
            
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados funcionais do Funcionário.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return dados;
    } 
    
    private FeriasNG criarFeriasFuncionario (ResultSet rs) throws JsageImportException{
        FeriasNG ferias = new FeriasNG();
        try{
            ferias.setIdRegistro(rs.getInt("idregistro"));
            ferias.setCodigoRegistro(rs.getString("codigoregistro"));
            ferias.setDatainicioferias(rs.getTimestamp("datainicioferias"));
            ferias.setDatainicioperiodoaquisitivo(rs.getTimestamp("datainicioperiodoaquisitivo"));
            ferias.setDatafimperiodoaquisitivo(rs.getTimestamp("datafimperiodoaquisitivo"));
            ferias.setDatainicioperiodogozo(rs.getTimestamp("datainicioperiodogozo"));
            ferias.setDatafimperiodogozo(rs.getTimestamp("datafimperiodogozo"));
            ferias.setDatainicioabono(rs.getTimestamp("datainicioabono"));
            ferias.setDatafimabono(rs.getTimestamp("datafimabono"));
            ferias.setDiasfalta(rs.getInt("diasfalta"));
            ferias.setDiasferias(rs.getInt("diasferias"));
            ferias.setDiasdesconsiderar(rs.getInt("diasdesconsiderar"));
            ferias.setIndconcederabonopecuniario(rs.getBoolean("indconcederabonopecuniario"));
            ferias.setDiasabono(rs.getInt("diasabono"));
            ferias.setIndabonoinicioferias(rs.getBoolean("indabonoinicioferias"));
            ferias.setIndpagar13salario(rs.getBoolean("indpagar13salario"));
            ferias.setDataretornoferias(rs.getTimestamp("dataretornoferias"));
            ferias.setDatalimitegozo(rs.getTimestamp("datalimitegozo"));
            ferias.setIndstatus(rs.getInt("indstatus"));
            ferias.setSalario(rs.getDouble("salario"));
            ferias.setSalarioferias(rs.getDouble("salarioferias"));
            ferias.setMediasalarial(rs.getDouble("mediasalarial"));
            ferias.setDatairrf(rs.getTimestamp("datairrf"));
            ferias.setSaldodias(rs.getDouble("saldodias"));
            ferias.setIndoculto(rs.getBoolean("indoculto"));
            ferias.setIndferiascoletivas(rs.getBoolean("indferiascoletivas"));
            ferias.setDiaslicenca(rs.getDouble("diaslicenca"));
            ferias.setValorcomposicaosalarialferias(rs.getInt("valorcomposicaosalarialferias")); 
            ferias.setDatahoralog(rs.getTimestamp("datahoralog"));
            ferias.setDataInicioPeriodoAquisitivoFeriasPendente(rs.getTimestamp("datainicioperiodoaquisitivoferiaspendente"));
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados das férias do Funcionário.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return ferias;        
    }

    private MovimentacaoNG criarMovimentacaoNG(ResultSet rs) throws JsageImportException {
        MovimentacaoNG mov = new MovimentacaoNG();
        try{
            mov.setIdregistro(rs.getInt("idregistro"));
            mov.setIdpessoaregistro(rs.getInt("idpessoaregistro"));
            mov.setDataadmissao(rs.getTimestamp("dataadmissao"));
            mov.setDataopcaofgts(rs.getTimestamp("dataopcaofgts"));
            mov.setIdtipoadmissao(rs.getInt("idtipoadmissao"));
            mov.setIdtipocontratacao(rs.getInt("idtipocontratacao"));
            mov.setNumeromatricula(rs.getString("numeromatricula"));
            mov.setNumerodiascontratoexperiencia(rs.getInt("numerodiascontratoexperiencia"));
            mov.setNumerodiasprorrogacaocontratoexperiencia(rs.getInt("numerodiasprorrogacaocontratoexperiencia"));
            mov.setIdtipoadmissaocaged(rs.getInt("idtipoadmissaocaged"));
            mov.setNumerocontafgts(rs.getString("numerocontafgts"));
            mov.setNumerodvcontafgts(rs.getInt("numerodvcontafgts"));
            mov.setNumeroregistro(rs.getString("numeroregistro"));
            mov.setIdreajustetipo(rs.getInt("idreajustetipo"));
            mov.setValorreajuste(rs.getDouble("valorreajuste"));
            mov.setIdcategoriasefip(rs.getInt("idcategoriasefip"));
            mov.setDataultimoexamemedico(rs.getTimestamp("dataultimoexamemedico"));
            mov.setNumeromesesintervaloexamemedico(rs.getInt("numeromesesintervaloexamemedico"));
            mov.setIdvinculoempregaticio(rs.getInt("idvinculoempregaticio"));
            mov.setDataconcessaobeneficio(rs.getTimestamp("dataconcessaobeneficio"));
            mov.setPercentualadiantamento(rs.getDouble("percentualadiantamento"));
            mov.setIdowner(rs.getInt("idowner"));
            mov.setDatainicioperiodoaquisitivoferiaspendente(rs.getTimestamp("datainicioperiodoaquisitivoferiaspendente"));
            mov.setDatafimperiodoaquisitivoferiaspendente(rs.getTimestamp("datafimperiodoaquisitivoferiaspendente"));
            mov.setDatavigenciaalteracaosalarial(rs.getTimestamp("datavigenciaalteracaosalarial"));
            mov.setDataultimacontribuicaosindical(rs.getTimestamp("dataultimacontribuicaosindical"));
            mov.setIddadospessoa(rs.getInt("iddadospessoa"));
            mov.setDescricaocomplementosalario(rs.getString("descricacaocomplemntosalario"));
            mov.setIdtipodiatrabalhado(rs.getInt("idtipodiatrabalhado"));
            mov.setIdpay(rs.getInt("idpay"));
            mov.setCompetenciames(rs.getInt("competenciames"));
            mov.setCompetenciaano(rs.getInt("competenciaano"));
            mov.setIdtipofolha(rs.getInt("idtipofolha"));
            mov.setDatainiciofolha(rs.getTimestamp("datainiciofolha"));
            mov.setDatafimfolha(rs.getTimestamp("datafimfolha"));
            mov.setDatainicioapuracaofatogerador(rs.getTimestamp("datainicioapuracaofatogerador"));
            mov.setDatafimapuracaofatogerador(rs.getTimestamp("datafimapuracaofatogerador"));
            mov.setDatainicioapuracaodsr(rs.getTimestamp("datainicioapuracaodsr"));
            mov.setDatafimapuracaodsr(rs.getTimestamp("datafimapuracaodsr"));
            mov.setDatairrf(rs.getTimestamp("datairrf"));
            mov.setNumerodiastrabalhados(rs.getInt("numerodiastrabalhados"));
            mov.setInddissidio(rs.getBoolean("inddissidio"));
            mov.setIdferias(rs.getInt("idferias"));
            mov.setIdverba(rs.getInt("idverba"));
            mov.setValorreferenciainformada(rs.getDouble("valorreferenciainformada"));
            mov.setQtdereferenciainformada(rs.getDouble("qtdereferenciainformada"));
            mov.setQtdereferenciacalculada(rs.getDouble("qtdereferenciacalculada"));
            mov.setValorinformado(rs.getDouble("valorinformado"));
            mov.setValorcalculado(rs.getDouble("valorcalculado"));
            mov.setIndprovdesc(rs.getInt("indprovdesc"));
            
            
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível criar o objeto MovimentaçãoNG.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return mov;
        
    }
    
   
        
}