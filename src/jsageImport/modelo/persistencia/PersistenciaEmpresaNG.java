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
import jsageImport.controler.ControlerEmpresaSAGE;
import jsageImport.controler.ControlerFuncionarioSAGE;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.AgenciaNG;
import jsageImport.modelo.dominio.CargoFun;
import jsageImport.modelo.dominio.CentroCusto;
import jsageImport.modelo.dominio.ContaBancaria;
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaTributacao;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.dominio.PorteEmpresa;
import jsageImport.modelo.dominio.Sindicato;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaNG;

/**
 * @author Gustavo Dias
 * Criação: 21/10/2016
 * Última modificação: 24/10/2016
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


    private static final String SQL_EMPRESATRIBUTACAO = "SELECT * FROM (bpm_dadosempresaformatributacao AS eformatributacao INNER JOIN bpm_dadosempresaformatributacaocomplementofolha AS etribcompfolha "
                                                            + "ON eformatributacao.iddadosempresaformatributacao = etribcompfolha.iddadosempresaformatributacao )" 
                                                            + "WHERE  eformatributacao.idpessoa = ?";
    
    private static final String SQL_PORTE_EMPRESA = "SELECT * FROM bpm_dadosempresaporteempresa WHERE idpessoa = ? and ano = 2016";
    
    private static final String SQL_EMPRESA_PJ_CNAE = "SELECT * FROM bpm_dadospessoajuridicacnae WHERE idpessoa = ? ";
   
    private static final String SQL_FOLHA_ID = "SELECT idpessoa,iddadosempresafolha FROM bpm_dadosempresafolha WHERE idpessoa = ?;";
    private static final String SQL_EMPRESA_FOLHA = "SELECT DISTINCT *" +"FROM (bpm_dadosempresafolha AS efolha INNER JOIN bpm_dadosempresafolhaparametrogeral AS fgeral " 
                                                             +"ON efolha.iddadosempresafolha = fgeral.iddadosempresafolha INNER JOIN NG.dbo.bpm_dadosempresafolhaparametro13salario AS fsalario "
                                                             +"ON efolha.iddadosempresafolha = fsalario.iddadosempresafolha INNER JOIN NG.dbo.bpm_dadosempresafolhaparametroesocial AS fesocial " 
                                                             +"ON fesocial.iddadosempresafolha = efolha.iddadosempresafolha INNER JOIN NG.dbo.bpm_dadosempresafolhaparametroferias AS fferias " 
                                                             +"ON efolha.iddadosempresafolha = fferias.iddadosempresafolha LEFT JOIN NG.dbo.bpm_dadosempresafolhasindicato AS fsindicato " 
                                                             +"ON efolha.iddadosempresafolha = fsindicato.iddadosempresafolha)" 
                                                             +"WHERE (efolha.iddadosempresafolha = ? )";
    
    private static final String SQL_AGENCIANG = "SELECT * FROM (ng.dbo.bpm_pessoa as pes LEFT JOIN ng.dbo.bpm_pessoaendereco as pesEnd ON pes.idpessoa = pesEnd.idpessoa "+
                                                      "LEFT JOIN ng.dbo.bpm_dadosbanco as bd ON pes.idpessoa = bd.idpessoa LEFT JOIN ng.dbo.bpm_dadosagencia as ag ON bd.iddadosbanco = ag.iddadosbanco " +
                                                      "LEFT JOIN bpm_contabancaria AS cont ON ag.iddadosagencia = cont.iddadosagencia)"+
                                                      "WHERE pes.idpessoa = ? ";
    
    private static final String SQL_BANCO = " SELECT *  from bpm_dadosbanco ";
    
    private static final String SQL_FLH_CARGO = "SELECT * from flh_cargo where idowner = ?";
    
    private static final String SQL_CENTRO_CUSTO = "SELECT * from bpm_centrocusto where idowner = ?";
    
    private static final String SQL_SINDICATO = "SELECT * FROM (bpm_dadossindicato as sindicato INNER JOIN bpm_pessoa as pessoa on sindicato.idpessoa = pessoa.idpessoa " +
                                                "INNER JOIN bpm_pessoaendereco as pessoaEnd on sindicato.idpessoa = pessoaEnd.idpessoa)";
                                                 

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
    public List recuperarSindicato () throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_SINDICATO);
            
            
            rs = stmt.executeQuery();
            List listaSindicato = new ArrayList();
            while (rs.next()) {
                Sindicato sind = criarSindicato(rs);
                listaSindicato.add(sind);
            }
            return listaSindicato;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos dados do sindicato.");
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
    
    private List recuperarCargoFuncioario (int idempresa)throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = GerenciadorConexao.getConnection(urlNGFOLHA);
            stmt = con.prepareStatement(SQL_FLH_CARGO);
            stmt.setInt (1, idempresa);
            rs = stmt.executeQuery();
            List listaCargoFun = new ArrayList();
            
            while (rs.next()){
               CargoFun cargofun = criarCargo(rs);
               listaCargoFun.add(cargofun);
            }
            
        return listaCargoFun;
        
        }catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível recuperar o cargo do funcionário.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
        
    }
    
    public List recuperarCentroCusto (int idempresa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_CENTRO_CUSTO);
            stmt.setInt (1, idempresa);
            rs = stmt.executeQuery();
            List listaCentroCusto = new ArrayList();
            
            while (rs.next()){
               CentroCusto centroCusto = criarCentroCusto(rs);
               listaCentroCusto.add(centroCusto);
            }
            
        return listaCentroCusto;
        
        }catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível recuperar o centro custo da empresa.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
        
        
    }
    /**
     * Recuperar a id da empresa que é utilizado na configuração da folha
     * @return List
     * @throws JsageImportException 
     */
    private List recuperarIdFolha (int id) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_FOLHA_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            List listaIdFolhas = new ArrayList();
            while (rs.next()) {
                listaIdFolhas.add(rs.getInt("iddadosempresafolha"));
            }
            return listaIdFolhas;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da id da empresa.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * Retorna todas as informações de configuração da folha de uma empresa
     * @param id
     * @return List
     * @throws JsageImportException 
     */
    public List capturarInfoEmpresasFolha(int id) throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List listaEmpresaFolha = new ArrayList();
            List listaConsulta = recuperarIdFolha(id);
            if (listaConsulta.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_EMPRESA_FOLHA);
            
                for (int i = 0; i < listaConsulta.size(); i++ ){
                    int iddadosempresafolha = (int)listaConsulta.get(i);
                    stmt.setInt(1,iddadosempresafolha);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        EmpresaFolha pj = criarEmpresaFolhaNG(rs);
                        listaEmpresaFolha.add(pj);
                    }
                } 
            }else{
                throw new JsageImportException("Não foi encontrado Empreasas!");
            }           
            return listaEmpresaFolha;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta das informações da folha da empresa.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    /**
     * 
     * @param id
     * @return
     * @throws JsageImportException 
     */
    private List capturarInfoEmpresaTributacao (int id) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_EMPRESATRIBUTACAO);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            List listaEmpresaTributacao = new ArrayList();
            while (rs.next()) {
                EmpresaTributacao empTributo = criarEmpresaTributacao(rs);
                listaEmpresaTributacao.add(empTributo);
            }
            return listaEmpresaTributacao;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da forma da tributação da empresa.");
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
            List listaEmpresas = new ArrayList();
            while (rs.next()) {
                PessoaJuridica pj = criarEmpresaNG(rs);
                listaEmpresas.add(pj);
            }
            return listaEmpresas;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do CNPJ.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    private List recuperarPorteEmpresa (int id) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_PORTE_EMPRESA);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            List listaPorteEmpresa = new ArrayList();
            while (rs.next()) {
                PorteEmpresa pj = criarPorteEmpresa(rs);
                listaPorteEmpresa.add(pj);
            }
            return listaPorteEmpresa;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do porte da Empresa.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    private List recuperarCnaeEmpresa (int id) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_EMPRESA_PJ_CNAE);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            List listaCnaeEmpresa = new ArrayList();
            while (rs.next()) {
                EmpresaTributacao pj = criarEmpresaCnae(rs);
                listaCnaeEmpresa.add(pj);
            }
            return listaCnaeEmpresa;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta do CNAE da Empresa.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    private List recuperarBanco () throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_BANCO);
            
            rs = stmt.executeQuery();
            List listaBanco = new ArrayList();
            
            while(rs.next()){
                listaBanco.add(rs.getInt("idpessoa"));
            }
            
            return listaBanco;
            
            
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a recuperação do Banco.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        }finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    public List recuperarAgenciaNG () throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
                   
            
            List listaAgenciaNG = new ArrayList();
            List listaBanco = recuperarBanco();
            if (listaBanco.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_AGENCIANG);
                
                for (int i = 0; i < listaBanco.size(); i++) {
                    int idpessoa = (int) listaBanco.get(i);
                    
                    stmt.setInt(1, idpessoa);
                    rs = stmt.executeQuery();
                    
                    while (rs.next()){
                        ContaBancaria conta = criarAgenciaNG(rs);
                        listaAgenciaNG.add(conta);
                    }
                }    
            }else {
                JOptionPane.showMessageDialog(null, "O Banco não pode ser inserido");
            }
            return listaAgenciaNG;
            
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a Agência do Banco.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        }finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
        
    }
    /**
     * Realiza a importação de uma empresa para o banco de dados do SAGE
     * @param idEmpresa
     * @param cnpj
     * @throws JsageImportException 
     */
    @Override
    public void ImportaEmpresas(int idEmpresa, String cnpj) throws JsageImportException {
        ControlerFuncionarioSAGE controlFunSAGE = new ControlerFuncionarioSAGE();
        ControlerEmpresaSAGE controlEmpSAGE = new ControlerEmpresaSAGE();
        List listaEmpresaSAGE = controlFunSAGE.pesquisarCNPJ(cnpj);
        if (listaEmpresaSAGE.isEmpty()){
            //JOptionPane.showMessageDialog(null, "Empresa precisa ser primeiro cadastrada no SAGE\n para importar os seus Funcionários!");
            //throw new JsageImportException("Primeiro Cadastre a Empresa no SAGE\n para Depois importar os Funcionários.");
            int reply = JOptionPane.showConfirmDialog(null, "Empresa de CNPJ: "+cnpj+" não esta cadastrada no SAGE, Deseja Importar agora?", "Aviso de importação", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                //Pesquisar no banco de dados do NG as informações cadastrais da empresa do cnpj
                List listaEmpresa = pesquisarCnpj(cnpj);
                //Pesquisa informações sobre o porte da empresa no banco do NG
                //List listaPorteEmpresa = recuperarPorteEmpresa(idEmpresa);
                //Pesquisa informaçoes sobre a tributação da empresa no banco NG
                List listaTributacaoEmpresa = capturarInfoEmpresaTributacao(idEmpresa);
                //Pesquisa informações sobre o cnae da empresa no banco NG
                List listaCnaeEmpresa = recuperarCnaeEmpresa(idEmpresa);
                //Pesquisa configuracoes da folha da empresa no banco NG
                List listaFolhaEmpresa = capturarInfoEmpresasFolha(idEmpresa);                
                List listaBanco = recuperarAgenciaNG();                
                List listaCargo = recuperarCargoFuncioario(idEmpresa);                
                List listaCentroCusto = recuperarCentroCusto(idEmpresa);                
                List listaSindicato = recuperarSindicato();
                
                 //Sequencia de gravação das informações no SAGE
                PessoaJuridica pjGravar = null;
                if (listaEmpresa.size() > 0){
                    pjGravar =(PessoaJuridica) listaEmpresa.get(0);
                    controlEmpSAGE.gravarEmpresa(pjGravar);
                }
                controlEmpSAGE.gravarEmpresaParametro(pjGravar);
                
                EmpresaTributacao empTrib = null;
                if (listaTributacaoEmpresa.size()> 0){
                    empTrib = (EmpresaTributacao)listaTributacaoEmpresa.get(listaTributacaoEmpresa.size()-1);
                }
                EmpresaTributacao empTribCNAE = null;
                if (listaCnaeEmpresa.size() > 0){
                    empTribCNAE = (EmpresaTributacao)listaCnaeEmpresa.get(0);
                }
                EmpresaFolha empFolha = null;
                if (listaFolhaEmpresa.size()>0){
                    empFolha = (EmpresaFolha) listaFolhaEmpresa.get(0);
                }
                
                controlEmpSAGE.gravarBancoGeral(idEmpresa);
                
                ContaBancaria conta = null;                
                if(listaBanco.size() > 0){
                    
                    for (int i = 0; i < listaBanco.size(); i++) {
                        conta = (ContaBancaria) listaBanco.get(i); 
                        controlEmpSAGE.gravarBanco(conta, idEmpresa);
                    }
                }
                
                CargoFun cargo = null;
                if (listaCargo.size()>0){
                    for (int i = 0; i < listaCargo.size(); i++) {
                        cargo = (CargoFun) listaCargo.get(i);
                        controlEmpSAGE.gravarCargo(cargo, idEmpresa);
                    }
                }
                
                CentroCusto centroCusto = null;
                if(listaCentroCusto.size()>0){
                    for (int i = 0; i < listaCentroCusto.size(); i++) {
                        centroCusto = (CentroCusto) listaCentroCusto.get(i);
                        controlEmpSAGE.gravarCentroCusto(centroCusto, idEmpresa);
                    }
                }else{
                    controlEmpSAGE.gravarCentroCusto(centroCusto, idEmpresa);
                }
                
                Sindicato sind = null;
                if (listaSindicato.size()> 0){
                    for (int i = 0;i < listaSindicato.size(); i++) {
                        sind = (Sindicato) listaSindicato.get(i);
                        controlEmpSAGE.gravarSindicato(sind);
                    }
                }
                
                
                
                controlEmpSAGE.gravarEstabelecimento(pjGravar, empTrib, empTribCNAE, empFolha);
                controlEmpSAGE.gravarEstabelecimentoParametro(pjGravar);
                controlEmpSAGE.gravarEstrutura(idEmpresa);                
                controlEmpSAGE.gravarCRDPermissaoGrupoEstabelecimento(idEmpresa);
                controlEmpSAGE.gravarCSCDRAPlano(idEmpresa);
                controlEmpSAGE.gravarCSCDFCEPlano(idEmpresa);
                //controlSAGE.gravarCSCDFCEquivalenteCaixa(idEmpresa);
                controlEmpSAGE.gravarTomador(idEmpresa);
                //controlSAGE.gravarTituloDRE(idEmpresa);
                controlEmpSAGE.gravarTipoDRE(idEmpresa);
                controlEmpSAGE.gravarCRDSCPRODEC(idEmpresa);
                controlEmpSAGE.gravarCapaLote(idEmpresa);
                controlEmpSAGE.gravarEmpresaMatrizContabilizacao(idEmpresa);
                controlEmpSAGE.gravarCSCDMPLPLANO(idEmpresa);
                
                
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
            /*Dados endereço*/
            pj.setLogradouro(rs.getString("logradouro"));
            pj.setNumeroEndereco(rs.getString("numeroendereco"));
            pj.setComplemento(rs.getString("complemento"));
            pj.setBairro(rs.getString("bairro"));
            pj.setIdmunicipio(rs.getInt("idmunicipio"));
            pj.setCep(rs.getString("cep"));
            pj.setIdmunicipio(rs.getInt("idmunicipio"));
            
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
    /**
     * Configura o objeto com os dados da folha de pagamento
     * @param ResulSet
     * @return EmpresaFolha
     * @throws JsageImportException 
     */
    private EmpresaFolha criarEmpresaFolhaNG (ResultSet rs) throws JsageImportException{
        EmpresaFolha empFolha = new EmpresaFolha();
        try{
            empFolha.setIddadosempresafolha(rs.getInt("iddadosempresafolha"));
            empFolha.setIdpessoa(rs.getInt("idpessoa"));
            empFolha.setIdfpas(rs.getInt("idfpas"));
            empFolha.setIdcodigosterceiros(rs.getInt("idcodigosterceiros"));
            empFolha.setIdtipoarredondamento(rs.getInt("idtipoarredondamento"));
            empFolha.setContafgts(rs.getString("contafgts"));
            empFolha.setIdcodigogps(rs.getInt("idcodigogps"));
            empFolha.setPercentualrat(rs.getDouble("percentualrat"));
            empFolha.setIndcalcularpis(rs.getBoolean("indcalcularpis"));
            empFolha.setIndencargosgpsporcc(rs.getBoolean("indencargosgpsporcc"));
            empFolha.setIndparticipapat(rs.getBoolean("indparticipapat"));
            empFolha.setPercentualterceiro(rs.getDouble("percentualterceiro"));
            empFolha.setIdoptantesimples(rs.getInt("idoptantesimples"));
            empFolha.setPercentualinss(rs.getDouble("percentualinss"));
            empFolha.setIndativo(rs.getBoolean("indativo"));
            empFolha.setIndfiliadasindicato(rs.getBoolean("indfiliadasindicato"));
            empFolha.setIndapropriacaohorasfatogerador(rs.getBoolean("indapropriacaohorasfatogerador"));
            empFolha.setIndsuprimirapropriacaofatogerador(rs.getBoolean("indsuprimirapropriacaofatogerador"));
            empFolha.setIdregimetributario(rs.getString("idregimetributario"));
            empFolha.setIndexcluido(rs.getBoolean("indexcluido"));
            empFolha.setIdtipoempresalei12546(rs.getInt("idtipoempresalei12546"));
            empFolha.setIndefetuarecolhimentopatronallei12546(rs.getBoolean("indefetuarecolhimentopatronallei12546"));
            empFolha.setIdtipovigencialei12546(rs.getInt("idtipovigencialei12546"));
            empFolha.setIndeduzirdiasdescontodsrcalculodiasuteis(rs.getBoolean("indeduzirdiasdescontodsrcalculodiasuteis"));
            empFolha.setIndconsideraradiantamento13provisao(rs.getBoolean("indconsideraradiantamento13provisao"));
            empFolha.setIndcopafifa(rs.getBoolean("indcopafifa"));
            empFolha.setIdaliquotalei12546(rs.getInt("idaliquotalei12546"));
            empFolha.setIndinssempresaintegralfolhaeprovisaoferias(rs.getBoolean("indinssempresaintegralfolhaeprovisaoferias"));
            empFolha.setIndvisualizadadosonline(rs.getBoolean("indvisualizadadosonline"));
            empFolha.setInddarfindividual(rs.getBoolean("inddarfindividual"));
            empFolha.setIndconsolidardarfporcodigo(rs.getBoolean("indconsolidardarfporcodigo"));
            empFolha.setIdtipocontagemmesadiantamento(rs.getInt("idtipocontagemmesadiantamento"));
            empFolha.setIdtipocalculomediaadiantamento(rs.getInt("idtipocalculomediaadiantamento"));
            empFolha.setIndconsiderarmescorrenteadiantamento(rs.getBoolean("indconsiderarmescorrenteadiantamento"));
            empFolha.setInddeduzirvaloroutroadiantamento(rs.getBoolean("inddeduzirvaloroutroadiantamento"));
            empFolha.setIndconsiderarmetadereferenciaeventoadiantamento(rs.getBoolean("indconsiderarmetadereferenciaeventoadiantamento"));
            empFolha.setPercentualcalculoadiantamento(rs.getDouble("percentualcalculoadiantamento"));
            empFolha.setIdtipocontagemmes13salario(rs.getInt("idtipocontagemmes13salario"));
            empFolha.setIdtipocalculomedia13salario(rs.getInt("idtipocalculomedia13salario"));
            empFolha.setIndconsiderarmescorrente13salario(rs.getBoolean("indconsiderarmescorrente13salario"));
            empFolha.setIndpagarproporcionalacidentetrabalho(rs.getBoolean("indpagarproporcionalacidentetrabalho"));
            empFolha.setIdtipocalculomediacomplemento(rs.getInt("idtipocalculomediacomplemento"));
            empFolha.setIndconsiderarmescorrentecomplemento(rs.getBoolean("indconsiderarmescorrentecomplemento"));
            empFolha.setIndbaixapagto13salario(rs.getBoolean("indbaixapagto13salario"));
            //empFolha.setIndativoSalario(rs.getBoolean("indativosalario"));
            empFolha.setIdclassificacaotributaria(rs.getInt("idclassificacaotributaria"));
            empFolha.setIdcooperativa(rs.getInt("idcooperativa"));
            empFolha.setIdconstrutora(rs.getInt("idconstrutora"));
            empFolha.setIndprocessorat(rs.getBoolean("indprocessorat"));
            empFolha.setIdtipoprocessorat(rs.getInt("idtipoprocessorat"));
            empFolha.setNumeroprocessorat(rs.getString("numeroprocessorat"));
            empFolha.setIndprocessofap(rs.getBoolean("indprocessofap"));
            empFolha.setIdtipoprocessofap(rs.getInt("idtipoprocessofap"));
            empFolha.setNumeroprocessofap(rs.getString("numeroprocessofap"));
            empFolha.setInddadosisencao(rs.getBoolean("inddadosisencao"));
            empFolha.setIdcertificador(rs.getInt("idcertificador"));
            empFolha.setDataemissaocertificado(rs.getTimestamp("dataemissaocertificado"));
            empFolha.setDatarenovacaocertificado(rs.getTimestamp("datarenovacaocertificado"));
            empFolha.setDatavencimentocertificado(rs.getTimestamp("datavencimentocertificado"));
            empFolha.setDatapublicacaodoucertificado(rs.getTimestamp("datapublicacaodoucertificado"));
            empFolha.setNumerocertificado(rs.getString("numerocertificado"));
            empFolha.setNumeroprotocolorenovacaocertificado(rs.getString("numeroprotocolorenovacaocertificado"));
            empFolha.setNumeropaginadoucertificado(rs.getString("numeropaginadoucertificado"));
            empFolha.setInddadosinternacionalizacao(rs.getBoolean("inddadosinternacionalizacao"));
            empFolha.setIdacordointernacionalisencaomulta(rs.getInt("idacordointernacionalisencaomulta"));
            empFolha.setIndsocioostensivo(rs.getBoolean("indsocioostensivo"));
            empFolha.setIdsituacaoespecial(rs.getInt("idsituacaoespecial"));
            empFolha.setIddesoneracaofolha(rs.getInt("iddesoneracaofolha"));
            empFolha.setIdregistroeletronicoempregados(rs.getInt("idregistroeletronicoempregados"));
            empFolha.setIndinformacoescomplementares(rs.getBoolean("indinformacoescomplementares"));
            empFolha.setIndprocessosjudiciaisrelativooutrasentidadesfundos(rs.getBoolean("indprocessosjudiciaisrelativooutrasentidadesfundos"));
            empFolha.setNumerosiafi(rs.getString("numerosiafi"));
            empFolha.setIdsituacaopf(rs.getInt("idsituacaopf"));
            //empFolha.setIndativoEsocial(rs.getBoolean("indativoesocial"));
            empFolha.setInddescontoproporcionalinss(rs.getBoolean("inddescontoproporcionalinss"));
            empFolha.setInddesconsiderarfalta(rs.getBoolean("inddesconsiderarfalta"));
            empFolha.setInddescontarcontribuicaosindical(rs.getBoolean("inddescontarcontribuicaosindical"));
            empFolha.setIndmostrardataretornorecibo(rs.getBoolean("indmostrardataretornorecibo"));
            empFolha.setInddataretornodianaoutil(rs.getBoolean("inddataretornodianaoutil"));
            empFolha.setIndemitiralertavencimentoavisoprevio(rs.getBoolean("indemitiralertavencimentoavisoprevio"));
            empFolha.setIndconsiderarabonopecuniario(rs.getBoolean("indconsiderarabonopecuniario"));
            empFolha.setQuantidadeanosdireitolicensapremio(rs.getInt("quantidadeanosdireitolicensapremio"));
            empFolha.setIndagruparvinculado(rs.getBoolean("indagruparvinculado"));
            empFolha.setIndpagarsalariofamiliaabonofamilia(rs.getBoolean("indpagarsalariofamiliaabonofamilia"));
            empFolha.setIndpagarlicencaremuneradafuncionariomaisumano(rs.getBoolean("indpagarlicencaremuneradafuncionariomaisumano"));
            empFolha.setIndmudarperiodoaquisitivo(rs.getBoolean("indmudarperiodoaquisitivo"));
            empFolha.setIndpagarlicencaremunerada(rs.getBoolean("indpagarlicencaremunerada"));
            empFolha.setIndpagaradiantamento13salario(rs.getBoolean("indpagaradiantamento13salario"));
            //empFolha.setIndativoFerias(rs.getBoolean("indativoferias"));
            empFolha.setIndconsiderardiasfaltasperiodofatosgeradores(rs.getBoolean("indconsiderardiasfaltasperiodofatosgeradores"));
            empFolha.setIndpagarmediaferiasverbaseparada(rs.getBoolean("indpagarmediaferiasverbaseparada"));
            empFolha.setIndconsiderarvalorinssferiasverbaseparada(rs.getBoolean("indconsiderarvalorinssferiasverbaseparada"));
            empFolha.setIndconsiderarbaixaproporcionalprovisaoferias(rs.getBoolean("indconsiderarbaixaproporcionalprovisaoferias"));
            empFolha.setIndconsiderarbaixaprovisaoferiasconformevaloresferias(rs.getBoolean("indconsiderarbaixaprovisaoferiasconformevaloresferias"));
            empFolha.setInddescontarinss(rs.getBoolean("inddescontarinss"));
            empFolha.setIndgeralmostrarverbaferias(rs.getBoolean("indgeralmostrarverbaferias"));
            empFolha.setIndutilizarcasadecimal(rs.getBoolean("indutilizarcasadecimal"));
            empFolha.setIndcontroleautomaticosaldo(rs.getBoolean("indcontroleautomaticosaldo"));
            empFolha.setIndhorascalculosalariofamilia(rs.getBoolean("indhorascalculosalariofamilia"));
            empFolha.setIndabonarirmenor(rs.getBoolean("indabonarirmenor"));
            empFolha.setIdtipodiatrabalhado(rs.getInt("idtipodiatrabalhado"));
            //empFolha.setIdtipoarredondamentoGeral(rs.getInt("idtipoarredondamentogeral"));
            empFolha.setValorarredondamento(rs.getDouble("valorarredondamento"));
            empFolha.setIdtipocalculomedia(rs.getInt("idtipocalculomedia"));
            empFolha.setIndmescorrentecalculomedia(rs.getBoolean("indmescorrentecalculomedia"));
            empFolha.setIndbasecalculomediadsr(rs.getBoolean("indbasecalculomediadsr"));
            empFolha.setInddeduzirfaltadsr(rs.getBoolean("inddeduzirfaltadsr"));
            empFolha.setIndcalculocomissionistadsr(rs.getBoolean("indcalculocomissionistadsr"));
            empFolha.setDiainicioapuracaodsr(rs.getInt("diainicioapuracaodsr"));
            empFolha.setDiafimapuracaodsr(rs.getInt("diafimapuracaodsr"));
            empFolha.setIndparalisacaoafastamentodoenca(rs.getBoolean("indparalisacaoafastamentodoenca"));
            empFolha.setIndutilizareventoocorridomesrecisaocalculomedia(rs.getBoolean("indutilizareventoocorridomesrecisaocalculomedia"));
            empFolha.setIndparalisacaocontagemcontrato(rs.getBoolean("indparalisacaocontagemcontrato"));
            empFolha.setIndpagardescansoindenizadodemissao(rs.getBoolean("indpagardescansoindenizadodemissao"));
            empFolha.setIndbeneficiotransporteabaterdiasferias(rs.getBoolean("indbeneficiotransporteabaterdiasferias"));
            empFolha.setIndbeneficiotransporteabaterdiasafastamento(rs.getBoolean("indbeneficiotransporteabaterdiasafastamento"));
            empFolha.setIndbeneficiotransporteproporcionaljornadareduzida(rs.getBoolean("indbeneficiotransporteproporcionaljornadareduzida"));
            empFolha.setIndbeneficioticketabaterdiasferias(rs.getBoolean("indbeneficioticketabaterdiasferias"));
            empFolha.setIndbeneficioticketabaterdiasafastamento(rs.getBoolean("indbeneficioticketabaterdiasafastamento"));
            empFolha.setIndbeneficioticketproporcionaljornadareduzida(rs.getBoolean("indbeneficioticketproporcionaljornadareduzida"));
            empFolha.setIndlancarverbadescontodsrmensalistaquinzenalista(rs.getBoolean("indlancarverbadescontodsrmensalistaquinzenalista"));
            empFolha.setIndlancarverbadescontodsrhoristasemanalistadiarista(rs.getBoolean("indlancarverbadescontodsrhoristasemanalistadiarista"));
            empFolha.setIndmensalista(rs.getBoolean("indmensalista"));
            empFolha.setIndsemanalista(rs.getBoolean("indsemanalista"));
            empFolha.setIndhorista(rs.getBoolean("indhorista"));
            empFolha.setIndquinzenalista(rs.getBoolean("indquinzenalista"));
            empFolha.setInddiarista(rs.getBoolean("inddiarista"));
            empFolha.setIndtarefeiro(rs.getBoolean("indtarefeiro"));
            empFolha.setIndconsiderartambemmesesferiasafastamento(rs.getBoolean("indconsiderartambemmesesferiasafastamento"));
            empFolha.setIdtipoadiantamentoadmissao(rs.getInt("idtipoadiantamentoadmissao"));
            empFolha.setNumerominimodiatrabalhoadiantamentoadmissao(rs.getInt("numerominimodiatrabalhoadiantamentoadmissao"));
            empFolha.setIdpagamentofolhamensal(rs.getInt("idpagamentofolhamensal"));
            empFolha.setDiainicioapuracaofatogerador(rs.getInt("diainicioapuracaofatogerador"));
            empFolha.setDiafimapuracaofatogerador(rs.getInt("diafimapuracaofatogerador"));
            //empFolha.setIndativoGeral(rs.getBoolean("indativogeral"));
            empFolha.setIndconsiderararredondamentofolhaferias(rs.getBoolean("indconsiderararredondamentofolhaferias"));
            empFolha.setIdconsiderarparacalculodsr(rs.getInt("idconsiderarparacalculodsr"));
            empFolha.setIndconsiderartambemmesadmissao(rs.getBoolean("indconsiderartambemmesadmissao"));
            empFolha.setIndcontrolarmotivocalculosalariofamilia(rs.getBoolean("indcontrolarmotivocalculosalariofamilia"));
            empFolha.setIndconsiderarverbasrescisaomedia(rs.getBoolean("indconsiderarverbasrescisaomedia"));
            empFolha.setIndbeneficiotransporteabaterfaltas(rs.getBoolean("indbeneficiotransporteabaterfaltas"));
            empFolha.setIndconsiderardiasapuracaodsrintegral(rs.getBoolean("indconsiderardiasapuracaodsrintegral"));
            empFolha.setIndverbaprogramadaperiodoafastamento(rs.getBoolean("indverbaprogramadaperiodoafastamento"));
            empFolha.setIndsepararumavoferiasindenizado(rs.getBoolean("indsepararumavoferiasindenizado"));
            empFolha.setIndmediacalculoferiasindenizado(rs.getBoolean("indmediacalculoferiasindenizado"));
            empFolha.setIdtipoadiantamentoferias(rs.getInt("idtipoadiantamentoferias"));
            empFolha.setIdtipoadiantamentoafastamento(rs.getInt("idtipoadiantamentoafastamento"));
            empFolha.setNumerominimodiatrabalhoadiantamentoferias(rs.getInt("numerominimodiatrabalhoadiantamentoferias"));
            empFolha.setNumerominimodiatrabalhoadiantamentoafastamento(rs.getInt("numerominimodiatrabalhoadiantamentoafastamento"));
            empFolha.setIndpagarfaltasjustificadasseparadassalario(rs.getBoolean("indpagarfaltasjustificadasseparadassalario"));
            empFolha.setIndtrabalhotemporario(rs.getBoolean("indtrabalhotemporario"));
            empFolha.setIndconsiderarsalfamcalculocontroleautomaticosaldo(rs.getBoolean("indconsiderarsalfamcalculocontroleautomaticosaldo"));
            empFolha.setIndconsiderararredondamentoparaautonomostransportadores(rs.getBoolean("indconsiderararredondamentoparaautonomostransportadores"));
            empFolha.setIndconsiderararredondamentoparasocios(rs.getBoolean("indconsiderararredondamentoparasocios"));
            empFolha.setIndlimitarcontagemdiastrabalhadosdatair(rs.getBoolean("indlimitarcontagemdiastrabalhadosdatair"));
            empFolha.setIndcalculardsrsobreferiadosverba(rs.getBoolean("indcalculardsrsobreferiadosverba"));
            empFolha.setIndconsiderardiasmesfolhaferias(rs.getBoolean("indconsiderardiasmesfolhaferias"));
            empFolha.setIndgerarfluxocaixa(rs.getBoolean("indgerarfluxocaixa"));
            empFolha.setIndclientedireto(rs.getBoolean("indclientedireto"));
            empFolha.setIndconsiderardiasmesapropriacaohoras(rs.getBoolean("indconsiderardiasmesapropriacaohoras"));
            empFolha.setDatainicioservico(rs.getTimestamp("datainicioservico"));
            empFolha.setIndpreencherdatairrfautomaticamentefolhamensal(rs.getBoolean("indpreencherdatairrfautomaticamentefolhamensal"));
            empFolha.setDiapagamentoirrffolhamensal(rs.getInt("diapagamentoirrffolhamensal"));
            empFolha.setIndtipodiairrffolhamensal(rs.getBoolean("indtipodiairrffolhamensal"));
            empFolha.setIndantecipadiautildatairrf(rs.getBoolean("indantecipadiautildatairrf"));
            empFolha.setIndconsiderasabadodiautil(rs.getBoolean("indconsiderasabadodiautil"));
            empFolha.setIndconsiderarlimitemaximoavisopreviotrabalhado(rs.getBoolean("indconsiderarlimitemaximoavisopreviotrabalhado"));
            empFolha.setIndoperacaoparalelo(rs.getBoolean("indoperacaoparalelo"));
            empFolha.setIndconsiderar15diaspagopelaempresareferenteafastamentos(rs.getBoolean("indconsiderar15diaspagopelaempresareferenteafastamentos"));
            empFolha.setPercentualadiantamento(rs.getDouble("percentualadiantamento"));
            empFolha.setIndcalcularadiantamentodiasmesesdiferente30empregadoshoristas(rs.getBoolean("indcalcularadiantamentodiasmesesdiferente30empregadoshoristas"));
            empFolha.setIndconsiderarfolharescisao(rs.getBoolean("indconsiderarfolharescisao"));
            empFolha.setIndgerarferiasproporcionaisrescisoesjustacausa(rs.getBoolean("indgerarferiasproporcionaisrescisoesjustacausa"));
            empFolha.setIndbeneficioticketabaterfaltas(rs.getBoolean("indbeneficioticketabaterfaltas"));
            empFolha.setDatafimservico(rs.getTimestamp("datafimservico"));
            empFolha.setPercentualdescontoBeneficioFolha(rs.getDouble("percentualdescontobeneficiofolha"));
            empFolha.setIddadossindicato(rs.getInt("iddadossindicato"));
            empFolha.setAno(rs.getInt("ano"));
            empFolha.setValorcontribuicaosindical(rs.getDouble("valorcontribuicaosindical"));
            empFolha.setValorcontribuicaoassociativa(rs.getDouble("valorcontribuicaoassociativa"));
            empFolha.setValorcontribuicaoassistencial(rs.getDouble("valorcontribuicaoassistencial"));
            empFolha.setValorcontribuicaoconfederativa(rs.getDouble("valorcontribuicaoconfederativa"));
            //empFolha.setIndativoSindicato(rs.getBoolean("indativosindicato"));
            empFolha.setIndprincipal(rs.getBoolean("indprincipal"));         
            
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Folha da Empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        
        return empFolha;
    }
    
    private EmpresaTributacao criarEmpresaTributacao (ResultSet rs)throws JsageImportException{
        EmpresaTributacao empTrib = new EmpresaTributacao ();
        
        try {
            empTrib.setAno((rs.getInt("ano")));
            empTrib.setDatafimvigenciaTributo(rs.getTimestamp("datafimvigencia"));
            empTrib.setDatainiciovigenciaTributo(rs.getTimestamp("datainiciovigencia"));
            empTrib.setFap(rs.getDouble("fap"));
            empTrib.setIdcodigogps(rs.getInt("idcodigogps"));
            empTrib.setIdcodigosterceiros(rs.getInt("idcodigosterceiros"));
            empTrib.setIddadosempresaformatributacao(rs.getInt("iddadosempresaformatributacao"));
            empTrib.setIdformatributacao(rs.getInt("idformatributacao"));
            empTrib.setIdoptantesimples(rs.getInt("idoptantesimples"));
            empTrib.setIdpessoa(rs.getInt("idpessoa"));
            empTrib.setPercentualinss(rs.getDouble("percentualinss"));
            empTrib.setPercentualrat(rs.getDouble("percentualrat"));
            empTrib.setPercentualterceiro(rs.getDouble("percentualterceiro"));
            
            
        } catch (SQLException ex) {
             StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Tributação da empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return empTrib;
        
    } 
    
    private ContaBancaria criarAgenciaNG (ResultSet rs) throws JsageImportException{
        ContaBancaria conta = new ContaBancaria();
        
        
        try {
            conta.setIdPessoa(rs.getInt("idpessoa"));
            conta.setIddadosbanco(rs.getInt("iddadosbanco"));
            conta.setCodigobanco(rs.getString("codigobanco"));
            conta.setPracacompensacao(rs.getString("pracacompensacao"));
            conta.setIndativo(rs.getBoolean("indativo"));
            conta.setCodigoagencia(rs.getString("codigoagencia"));
            conta.setNumdvagencia(rs.getString("numdvagencia"));
            conta.setDescricao(rs.getString("descricao"));
            conta.setCodcompensacao(rs.getString("codcompensacao"));
            conta.setNomePessoa(rs.getString("nomepessoa"));
            conta.setLogradouro(rs.getString("logradouro"));
            conta.setNumeroEndereco(rs.getString("numeroendereco"));
            conta.setBairro(rs.getString("bairro"));
            conta.setIdmunicipio(rs.getInt("idmunicipio"));
            conta.setCep(rs.getString("cep"));
            conta.setIdtipocontabancaria(rs.getInt("idtipocontabancaria"));
            conta.setNumeroconta(rs.getString("numeroconta"));
            conta.setDigitoverificador(rs.getString("digitoverificador"));
            conta.setDataabertura(rs.getTimestamp("dataabertura"));
            conta.setDatasaldoinicial(rs.getTimestamp("datasaldoinicial"));
            conta.setSaldoinicial(rs.getDouble("saldoinicial"));
            conta.setResponsavel(rs.getString("responsavel"));
         
            
        } catch (Exception ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da agência da empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        
        return conta;
        
    }
    
    private EmpresaTributacao criarEmpresaCnae (ResultSet rs)throws JsageImportException{
        EmpresaTributacao empTrib = new EmpresaTributacao ();
        
        try {
            empTrib.setDatafimvigenciaCNAE(rs.getTimestamp("datafimvigencia"));
            empTrib.setDatainiciovigenciaCNAE(rs.getTimestamp("datainiciovigencia"));
            empTrib.setIdCNAE(rs.getInt("idcnae"));
            empTrib.setIdpessoa(rs.getInt("idpessoa"));
            empTrib.setIndPrincipal(rs.getBoolean("indprincipal"));         
            
        } catch (SQLException ex) {
             StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do CNAE da empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return empTrib;
        
    }
    
    private PorteEmpresa criarPorteEmpresa (ResultSet rs) throws JsageImportException{
        PorteEmpresa empPorte = new PorteEmpresa();
        try {
            empPorte.setIdporteempresa(rs.getInt("idporteempresa"));
            empPorte.setIdpessoa(rs.getInt("idpessoa"));
            empPorte.setDatainiciovigencia(rs.getTimestamp("datainiciovigencia"));
            empPorte.setDatafimvigencia(rs.getTimestamp("datafimvigencia"));
            empPorte.setAno(rs.getInt("ano"));
            
        } catch (SQLException ex) {
             StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do porte da empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return empPorte;
    }
    
    private CargoFun criarCargo (ResultSet rs) throws JsageImportException {
        CargoFun cargofun = new CargoFun();
        
         try {
             
             cargofun.setIdcargo(rs.getInt ("idcargo"));
             cargofun.setCodigocargo(rs.getString("codigocargo"));
             cargofun.setDescricaocargoreduzida(rs.getString("descricaocargoreduzida"));
             cargofun.setDescricaocargo(rs.getString("descricaocargo"));
             cargofun.setDescricaocargodetalhada(rs.getString("descricaocargodetalhada"));
             cargofun.setIndinativo(rs.getBoolean("indinativo"));
             cargofun.setSalariosugeridocargo(rs.getBigDecimal("salariosugeridocargo"));
             cargofun.setIdcbo(rs.getInt("idcbo"));
             cargofun.setIndpericulosidade(rs.getBoolean("indpericulosidade"));
             cargofun.setIndinsalubridade(rs.getBoolean("indinsalubridade"));
             cargofun.setIdtiposalario(rs.getInt("idtiposalario"));
             cargofun.setIdowner(rs.getInt("idowner"));
             cargofun.setCodigosincronismo(rs.getString("codigosincronismo"));
             cargofun.setCodigoesocial(rs.getString("codigoesocial"));
             cargofun.setIndcargopublico(rs.getBoolean("indcargopublico"));
             cargofun.setIdacumulacao(rs.getInt("idacumulacao"));
             cargofun.setIdcontagemtempoespecial(rs.getInt("idcontagemtempoespecial"));
             cargofun.setInddedicacaoexclusiva(rs.getBoolean("inddedicacaoexclusiva"));
             cargofun.setNumerolei(rs.getString("numerolei"));
             cargofun.setDatalei(rs.getTimestamp("datalei"));
             cargofun.setIdsituacaogeradapelalei(rs.getInt("idsituacaogeradapelalei"));
             cargofun.setIndquebracaixa(rs.getBoolean("indquebracaixa"));
            
            
        } catch (SQLException ex) {
             StringBuffer mensagem = new StringBuffer("Não foi possível obter o cargo do funcionário da empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return cargofun;
        
        
        
    }
    
    private CentroCusto criarCentroCusto (ResultSet rs)throws JsageImportException {
        CentroCusto centrocusto = new CentroCusto();
        
        try{
            centrocusto.setIdowner(rs.getInt("idcentrocusto"));
            centrocusto.setIdcentrocusto(rs.getInt("idowner"));
            centrocusto.setCodigocentrocusto(rs.getString("codigocentrocusto"));
            centrocusto.setCodigoestruturado(rs.getString("codigoestruturado"));
            centrocusto.setDescricaocentrocusto(rs.getString("descricaocentrocusto"));
            centrocusto.setIndativo(rs.getBoolean("indativo"));  
       
        }catch (SQLException ex) {
             StringBuffer mensagem = new StringBuffer("Não foi possível obter o centro custo da empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return centrocusto;
        
    }
    
     private Sindicato criarSindicato (ResultSet rs) throws JsageImportException{
        Sindicato sind = new Sindicato();
        
        try{
            sind.setIddadossindicato(rs.getInt("iddadossindicato"));
            sind.setIdPessoa(rs.getInt("idpessoa"));
            sind.setIdtiposindicato(rs.getInt("idtiposindicato"));
            sind.setCodigoentidade(rs.getString("codigoentidade"));
            sind.setMescontribuicao(rs.getInt("mescontribuicao"));
            sind.setMesdissidio(rs.getInt("mesdissidio"));
            sind.setInddescontarmesadmissao(rs.getBoolean("inddescontarmesadmissao"));
            sind.setFatoradicionalferias(rs.getDouble("fatoradicionalferias"));
            sind.setIndativo(rs.getBoolean("indativo"));
            sind.setDiaslicencamaternidade(rs.getInt("diaslicencamaternidade"));
            sind.setPercentualminimohoraextra(rs.getDouble("percentualminimohoraextra"));
            sind.setNumerodiasavisoprevio(rs.getInt("numerodiasavisoprevio"));
            sind.setNumerodiasantecedenciapgtoferias(rs.getInt("numerodiasantecedenciapgtoferias"));
            sind.setDesconsiderardiasferiascoletivas(rs.getInt("desconsiderardiasferiascoletivas"));
            sind.setNomePessoa(rs.getString("nomepessoa"));
            sind.setIdmunicipio(rs.getInt("idmunicipio"));
            sind.setCep(rs.getString("cep"));
            sind.setBairro(rs.getString("bairro"));
            sind.setNumeroEndereco(rs.getString("numeroendereco"));
            sind.setLogradouro(rs.getString("logradouro"));
            
           
                    
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do sindicato do Funcionário.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return sind;        
        
    }
    
}
