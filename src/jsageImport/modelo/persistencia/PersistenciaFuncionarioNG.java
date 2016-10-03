/*
 * Classe referente a persistencia de dados de funcionários
 */
package jsageImport.modelo.persistencia;

import jsageImport.log.LogSage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jsageImport.controler.ControlerFuncionarioSAGE;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionaisNG;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.FuncionarioSAGE;
import jsageImport.modelo.dominio.PessoaFisica;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioNG;

/**
 * @author Gustavo Dias
 * Criação: 06/06/2016
 * Última modificação: 07/06/2016
 */
public class PersistenciaFuncionarioNG implements IPersistenciaFuncionarioNG {
    
    private PropertiesJdbc jdbc = new PropertiesJdbc();
    private TratamentoDados TrataDados = new TratamentoDados();
    private LogSage logarq = new LogSage();
    
    /*String SQL para consultas no banco NG*/
    
    /*Strings SQL para informações das empreas no NG*/
    
    private static final String SQL_PESQUISARTODOS ="SELECT * FROM bpm_dadospessoajuridica";
    private static final String SQL_PESQUISAREMPRESAID ="SELECT * FROM bpm_dadospessoajuridica " +"WHERE idpessoa = ?";
    
    private static final String SQL_EMPRESA = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              "WHERE (pj.cnpj <> '');";
    private static final String SQL_EMPRESA_COM_FUN = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              "WHERE (P.idpessoa = ?);";
    private static final String SQL_RECUP_EMPRESA_FUN = "SELECT DISTINCT idowner FROM flh_registro";
    private static final String SQL_EMPRESA_CNPJ = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              " WHERE (pj.cnpjformatado = ?);";
    
    /*Strings SQL para informações dos funcionarios no NG*/
    private static final String SQL_INCLUIR = "INSERT INTO Funcionario " + "(nome, endereco, nr_endereco, compl_endereco,bairro, cidade,estado, cep, pai, mae, sexo, estado_civil, nacionalidade, ano_chegada, grau_instrucao,"
                                                                         + "dt_nascimento, ddd_fone, telefone, apelido, chave_acesso, senha_acesso, raca, deficiente, cidade_nascimento, estado_nascimento, ddd_celular, celular,"
                                                                         + "nomecompleto, email, data_chegada, tipo_logradouro, cd_municipio, cd_municipio_nascimento, funcionario_aposentado, data_hora_alteracao, id, id_endereco) " 
                                                                         + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";    
    private static final String SQL_PESQUISAR_FUNCIONARIO = "SELECT * FROM " + "bpm_pessoa as pes" +" INNER JOIN bpm_dadospessoafisica as pesf ON pes.idpessoa = pesf.idpessoa" 
                                                                            + "	INNER JOIN dbo.bpm_pessoaendereco as ed ON pes.idpessoa = ed.idpessoa"
                                                                            + "	INNER JOIN dbo.bpm_dadosfuncionario as dfun ON pes.idpessoa = dfun.idpessoa"
                                                                            + " WHERE pes.idpessoa = ?"
                                                                            + " ORDER BY pes.nomepessoa ASC";
    
    private static final String SQL_RECUP_FUNCIONARIOS = "SELECT idpessoaregistro  FROM flh_registro WHERE idtipoadmissao <> 0 AND idowner = ?"; 
    private static final String SQL_RECUP_DEPENDENTES = "SELECT idpessoa FROM (bpm_pessoa AS p INNER JOIN bpm_pessoarelacionamento AS pr ON p.idpessoa = pr.idpessoaprincipal)"
                                                                + " WHERE pr.idpessoasecundaria = ? AND idtiporelacionamentopessoa = 23";
    
    private static final String SQL_PESQUISAR_DEPENDENTES = "SELECT * FROM " + "bpm_pessoa as pes" +" INNER JOIN bpm_dadospessoafisica as pesf ON pes.idpessoa = pesf.idpessoa INNER JOIN bpm_dadosdependente as dep ON pes.idpessoa = dep.idpessoa" 
                                                                             + " WHERE pes.idpessoa = ?";
    
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
                                                                        + " WHERE fl.idtipoadmissao <> 0 AND fl.idpessoaregistro = ?";
    
    private static final String SQL_CONSULTA_SALARIO = "SELECT *  FROM (flh_registro AS fl LEFT JOIN flh_movimentosalario AS flSal ON fl.idregistro = flSal.idregistro " 
                                                                        + " INNER JOIN  flh_movimentocargo AS flcargo ON fl.idregistro = flcargo.idregistro ) "
                                                                        + " WHERE fl.idtipoadmissao <> 0 AND fl.idpessoaregistro = ?"
                                                                        + " ORDER BY flSal.idmovimentosalario desc";
    
    
    private static final String SQL_DOCUMENTOS = "SELECT * FROM " + "bpm_pessoa as pes" +" INNER JOIN bpm_dadospessoafisica as pesf ON pes.idpessoa = pesf.idpessoa" 
                                                                             + " WHERE pes.idpessoa = ?";
    private static final String SQL_EXCLUIR ="DELETE FROM Funcionario " +"WHERE id = ?";    
    
    /*url para conexao com o banco do ng*/    
    //jdbc:sqlserver://servidor:porta;databaseName=banco;user=usuario;password=senha;"
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    
    
    @Override   
    public void gravarFuncionario(FuncionarioSAGE fun) throws JsageImportException {
        if (fun == null) {
            String mensagem = "Não foi informado o Funcionario para cadastrar.";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_INCLUIR);
            stmt.setString(1, fun.getNome());
            stmt.setString(2, fun.getEndreco());
            stmt.setInt(3, fun.getNr_endereco());
            stmt.setString(4, fun.getCompl_endereco());
            stmt.setString(5,fun.getBairro());
            stmt.setString(6, fun.getCidade());
            stmt.setString(7, fun.getEstado());
            stmt.setInt(8, fun.getCep());
            stmt.setString(9, fun.getPai());
            stmt.setString(10, fun.getMae());
            stmt.setString(11,fun.getSexo());
            stmt.setShort(12, fun.getEstado_civil());
            stmt.setShort(13, fun.getNacionalidade());
            stmt.setShort(14, fun.getAno_chegada());
            stmt.setShort(15, fun.getGrau_instrucao());
            stmt.setTimestamp(16, fun.getDt_nascimento());
            stmt.setShort(17, fun.getDdd_fone());
            stmt.setInt(18, fun.getTelefone());
            stmt.setString(19,fun.getApelido());
            stmt.setString(20, fun.getChave_acesso());
            stmt.setString(21, fun.getSenha_acesso());
            stmt.setString(22, fun.getRaca());
            stmt.setString(23, fun.getDeficiente());
            stmt.setString(24, fun.getCidade_nascimento());
            stmt.setString(25, fun.getEstado_nascimento());
            stmt.setShort(26, fun.getDdd_celular());
            stmt.setInt(27, fun.getCelular());
            stmt.setString(28, fun.getNomecompleto());
            stmt.setString(29, fun.getEmail());
            stmt.setTimestamp(30, fun.getData_chegada());
            stmt.setString(31, fun.getTipo_logradouro());
            stmt.setInt(32, fun.getCd_municipio());
            stmt.setInt(33, fun.getCd_municipio_nascimento());
            stmt.setString(34, fun.getFuncionario_aposentado());
            stmt.setTimestamp(35, fun.getData_hora_alteracao());
            stmt.setString(36, fun.getId());
            stmt.setString(37, fun.getId_endereco());
            
            stmt.executeUpdate();
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Funcionário.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void excluirFuncionario(FuncionarioSAGE fun) throws JsageImportException {
        if (fun == null) {
            String mensagem = "Não foi informado o Leitor a excluir.";
            throw new JsageImportException(mensagem);
        }        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_EXCLUIR);
            stmt.setString(1, fun.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível excluir o Funcionário.");
            msg.append("\nMotivo:" + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }

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
    @Override
    public List capturarInfoEmpresasComFun () throws JsageImportException{
        
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
      
    @Override
    public void ImportaEmpresas(String cnpj) throws JsageImportException {
               
        ControlerFuncionarioSAGE controlSAGE = new ControlerFuncionarioSAGE();
        List listaEmpresaSAGE = controlSAGE.pesquisarCNPJ(cnpj);
        if (listaEmpresaSAGE.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empresa Precisa ser primeiro cadastrada no SAGE\n para importar os seus Funcionários!");
            //throw new JsageImportException("Primeiro Cadastre a Empresa no SAGE\n para Depois importar os Funcionários.");
            int reply = JOptionPane.showConfirmDialog(null, "Empresa não esta cadastrada no SAGE, Deseja Importar agora?", "Aviso de importação", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                List listaEmpresa = pesquisarCnpj(cnpj);//Pesquisar no banco de dados do NG
                PessoaJuridica pjGravar =(PessoaJuridica) listaEmpresa.get(0);
                controlSAGE.gravarEmpresa(pjGravar);
                JOptionPane.showMessageDialog(null, "Empresa Gravada com Sucesso!");
            }else if (reply == JOptionPane.NO_OPTION){
                throw new JsageImportException("Primeiro Importe a Empresa para Depois importar os Funcionários.");
            }
                       
        }
    }
    
    @Override
    public String importaFuncionarios (int idEmpresa, int idPessoa, String nome) throws JsageImportException{
        String funcionario = "";
        String log = "";
        ControlerFuncionarioSAGE controlSAGE = new ControlerFuncionarioSAGE();
        
        if (nome.length()>0){          
        
            //verifica se o funcionario informado se encontra no banco sage
            List listaEmpresaSAGE = controlSAGE.pesquisarFuncionarioNome(idEmpresa, nome);       
            // se existir o a lista vai ter um tamanho maior do que zero, portanto não entra no if
            // System.out.println("Existentes no SAGE: " +idPessoa + " --- " + idEmpresa + " ----- " + nome);
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
                
                // havendo salario do funcionario é gravado 
                if (listaSalarios.size()> 0){
                    DadosFuncionaisNG funFuncionais = (DadosFuncionaisNG) listaSalarios.get(0);
                    controlSAGE.gravarSalario(idPessoa, idEmpresa, funFuncionais);
                    
                }               
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
                System.out.print(log);                
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
            
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados funcionais do Funcionário.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return dados;
    }       
        
}