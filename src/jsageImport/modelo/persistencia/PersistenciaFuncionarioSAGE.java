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
import java.util.ArrayList;
import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionaisNG;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.DependenteSAGE;
import jsageImport.modelo.dominio.EmpresaSAGE;
import jsageImport.modelo.dominio.FeriasNG;
import jsageImport.modelo.dominio.FuncionarioSAGE;
import jsageImport.modelo.dominio.MovimentacaoNG;
import jsageImport.modelo.dominio.PlanoSaudeNG;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioSAGE;

/**
 *
 * @author Gustavo
 */
public class PersistenciaFuncionarioSAGE implements IPersistenciaFuncionarioSAGE {

    PropertiesJdbc jdbc = new PropertiesJdbc();
    TratamentoDados trataDados = new TratamentoDados();
    
    private static final String SQL_PESQUISARTODOS = "SELECT * FROM CRDEmpresa";
    private static final String SQL_PESQUISAREMPRESACNPJ = "SELECT * FROM CRDEmpresa WHERE cnpj_cpf = ?";
    private static final String SQL_PESQUISAREMPRESAIDCNPJ = "SELECT * FROM CRDEmpresa WHERE cd_empresa = ? AND cnpj_cpf = ?";
    /* Strings SQL para funcionários*/
    private static final String SQL_INCLUIR_FUNCIONARIO ="INSERT INTO Funcionario (cd_empresa,cd_funcionario,nome,endereco,nr_endereco,compl_endereco,bairro,cidade,estado,cep,pai,mae,sexo" +
                                                                                   ",estado_civil,nacionalidade,ano_chegada,grau_instrucao,dt_nascimento,ddd_fone,telefone,apelido,chave_acesso" +
                                                                                   ",senha_acesso,raca,deficiente,cidade_nascimento,estado_nascimento,ddd_celular,celular,nomecompleto,email" +
                                                                                   ",data_chegada,tipo_logradouro,cd_municipio,cd_municipio_nascimento,funcionario_aposentado,data_hora_alteracao" +
                                                                                   ")" + 
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_FUNCAO = "INSERT INTO FunFuncao (cd_empresa,cd_funcionario,dt_funcao,cd_nivel,cd_funcao,dt_final,data_hora_alteracao)" +
                                                                    " VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_DOCUMENTOS ="INSERT INTO FunDocumento  (cd_empresa,cd_funcionario,nr_carteira,serie_carteira,dv_serie_carteira,uf_carteira,pis,cpf,nr_identidade,orgao_identidade,uf_identidade" +
                                                                                    ",nr_habilitacao,categoria_habilitacao,vcto_habilitacao,foto,nr_titulo,zona_titulo,secao_titulo,dt_emissao_carteira,dt_emissao_identidade" +
                                                                                    ",dt_emissao_habilitacao,dt_emissao_titulo,dt_emissao_pis,novo_nr_titulo,novo_nr_habilitacao,certificado_militar,orgao_reg_prof,nr_reg_prof" +
                                                                                    ",dt_emissao_reg_prof,orgao_emissor_cnh,dt_vcto_reg_prof,dt_emissao_certidao,matricula_certidao,livro_certidao,folha_certidao,cartorio_certidao" +
                                                                                    ",uf_certidao,cd_municipio_certidao,dt_primeira_habilitacao,uf_habilitacao,data_hora_alteracao,id)" +
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_DADOS_FUNCIONAIS = "INSERT INTO FunFuncional (cd_empresa,cd_funcionario,dt_admissao,vinculo_empregaticio_rais,codigo_admissao_caged,nr_registro,dias_experiencia,temporario,vcto_contrato_temporario" +
                                                                                          ",cd_sindicato,sindicalizado,situacao_contr_sindical,categoria,ocorrencia,cd_banco_deposito,nr_agencia_deposito,dv_agencia_deposito" +
                                                                                          ",nr_conta_deposito,dv_conta_deposito,op_desconto_inss,adto_13_ferias,nr_tabela_ats" +
                                                                                          ",deficiente_fisico,nr_sic,cd_param_sindicato,fgts_empregado_domestico,enviar_holerith_email,enviar_informe_rendimentos_email" +
                                                                                          ",membro_cipa,regime_juridico,natureza_cargo,regime_previdenciario,funcionario_pensionista,matricula" +
                                                                                          ",tipo_admissao,indicativo_admissao,caged_percepcao_requerimento_seguro_desemprego_admissao,data_hora_alteracao)" +
                                                                    " VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_INCLUIR_SALARIO = "INSERT INTO FunSalario  (cd_empresa,cd_funcionario,dt_salario,tipo_salario,nr_horas_semanais,vl_salario" +
                                                                                ",perc_adiantamento,motivo,vl_adiantamento,dt_final,perc_adiantamento_mes_anterior" +
                                                                                ",remuneracao_registro,dt_validade_salario,nao_compensar_reajuste,op_definir_regra_especifica_funcionario" +
                                                                                ",descricao_salario_variavel,data_hora_alteracao,id)" +
                                                                    " VALUES   (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_LOTACAO = "INSERT INTO FunLotacao (cd_empresa,cd_funcionario,dt_lotacao,cd_filial,cd_nivel1,cd_nivel2" +
                                                                               ",cd_nivel3,cd_ccusto,dt_final,data_hora_alteracao)" +
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_INCLUIR_COLABORADOR ="INSERT INTO CRHColaborador (cd_empresa,cd_colaborador,tp_colaborador,nome)" +
                                                                    " VALUES (?,?,?,?)";
    
    private static final String SQL_PESQUISAREMPRESA_FUNCIONARIO = "SELECT * FROM Funcionario AS fun  "
                                                                        + "   WHERE fun.cd_funcionario = ? AND fun.cd_empresa = ? AND fun.nome = ?";
    private static final String SQL_PESQUISAREMPRESA_FUN = "SELECT * FROM Funcionario AS fun  "
                                                                        + "   WHERE fun.cd_empresa = ? AND fun.nome = ?";
    private static final String SQL_INCLUIR_DEP = "INSERT INTO FunDependente (cd_empresa,cd_funcionario,cd_dependente,dt_inclusao,nome,tipo_parentesco,descricao_parentesco,dt_nascimento,suspende_sf,observacao,cpf_dependente"+
                                                                              ",esocial_tipo_parentesco,esocial_pensionista,data_hora_alteracao)" +
                                                                              " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_FERIAS = "INSERT INTO Ferias (cd_empresa,cd_funcionario,dt_processamento,tipo_ferias,dt_inicio_aquisicao " +
                                                                          ",dt_fim_aquisicao,dt_inicio_gozo,dt_fim_gozo,dt_inicio_abono,dt_fim_abono " +
                                                                          ",dt_retorno,vl_salario,nr_faltas,dt_pagamento,alterado,nr_dias_ferias,dt_nova_aquisicao,vl_abono,seq_processamento,dias_ferias " +
                                                                          ",saldo_ferias,ferias_tributada) " +
                                                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_PESQUISAR_DEP_ID = "SELECT * FROM FunDependente WHERE cd_dependente = ?";
    
    private static final String SQL_CONTROLE_ESOCIAL = "IF NOT EXISTS(SELECT * FROM CRHFuncionarioControleESocial WHERE cd_empresa = ? and cd_funcionario = ? and leiaute = ? and nome_tabela = ?)"+
                                                                        " INSERT INTO CRHFuncionarioControleESocial (cd_empresa,cd_funcionario,leiaute,nome_tabela,status)" +            
                                                                        " VALUES (?,?,?,?,?)";
    
    private static final String SQL_CONTROLE_CAMPOS_ESOCIAL = "INSERT INTO CRHFuncionarioControleCamposESocial (cd_empresa,cd_funcionario,leiaute,nome_tabela)" +
                                                                    " VALUES(?,?,?,?)";
    private static final String SQL_INCLUIR_FUNESPECIFICO = "INSERT INTO FunEspecificos (cd_empresa,cd_funcionario) VALUES (?,?)";
    
     private static final String SQL_MOV_EVENTO =  "IF NOT EXISTS(SELECT * FROM MovEvento WHERE cd_empresa = ? and cd_funcionario = ? and cd_evento = ? and ano = ? and mes = ?)\n" +
                                                   "  INSERT INTO MovEvento (cd_empresa,mes,ano,cd_funcionario,cd_evento,referencia)" +
                                                 "VALUES (?,?,?,?,?,?)";
    
    private static final String SQL_PROC_BASE = " IF NOT EXISTS(SELECT * FROM ProcBase WHERE cd_empresa = ? and cd_funcionario = ? and tipo = ? and ano = ? and mes = ?)"
                                                + "INSERT INTO ProcBase (cd_empresa,cd_funcionario,mes,tipo,ano,vl_base_rais,vl_base_fgts,vl_base_inss,vl_excesso_inss,vl_base_irrf,vl_ded_dep_irrf," + 
                                                " vl_outras_ded_irrf,dt_pagamento,alterado,vl_base_irrf_dist_lucros,vl_base_rb,vl_base_he,vl_base_faltas,ded_ir_mp202_2004,vl_base_pis,vl_base_irrf_tributacao_exclusiva,  " +
                                                " vl_ded_dep_irrf_dist_lucros)"+
                                                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_PROC_EVENTO = " IF NOT EXISTS(SELECT * FROM ProcEvento WHERE cd_empresa = ? and cd_funcionario = ? and tipo = ? and cd_evento = ? and ano = ? and mes = ?)" + ""
            + "                                    INSERT INTO ProcEvento (cd_empresa,cd_funcionario,mes,tipo,cd_evento,ano,referencia,referencia_editada,valor)" +
                                                  "VALUES (?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_PROC_IMPOSTO = " IF NOT EXISTS(SELECT * FROM ProcImposto WHERE cd_empresa = ? and cd_funcionario = ? and tipo = ? and ano = ? and mes = ?)" + 
                                                    "INSERT INTO ProcImposto (cd_empresa,tipo,mes,ano,cd_funcionario,vl_inss_fpas,vl_inss_sat,vl_inss_terceiros, " +
                                                    "vl_inss_sal_educ,vl_fgts,vl_pis,vl_sesi)"+
                                                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_PLANO_SAUDE = "IF NOT EXISTS (SELECT * FROM CRHOperadoraPlanoSaudeG WHERE cd_operadora= ? ) " + 
                                                    " INSERT INTO CRHOperadoraPlanoSaudeG (cd_operadora,razao,fantasia,cnpj,cd_registro_ans,status) " +
                                                        " VALUES (?,?,?,?,?,?)";
    
    private static final String SQL_PLANO_SAUDE_GERAL = "IF NOT EXISTS (SELECT * FROM CRHPlanoSaude WHERE enterprise_id = ? and cd_plano = ? and cd_operadora = ?)" +
                                                                " INSERT INTO CRHPlanoSaude (enterprise_id,cd_plano,cd_operadora,descricao,cd_evento_plano) " +
                                                                " VALUES (?,?,?,?,?)";
    private static final String SQL_PLANO_SAUDE_FUNCIONARIO = "IF NOT EXISTS (SELECT * FROM CRHPlanoSaudeFun WHERE enterprise_id = ? and cd_plano = ? and cd_funcionario = ?)" +
                                                                " INSERT INTO CRHPlanoSaudeFun (enterprise_id,cd_plano,cd_funcionario,vl_desc,status) " +
                                                                " VALUES (?,?,?,?,?)" +                                                 
                                                                "ELSE "+
                                                                " UPDATE CRHPlanoSaudeFun  SET vl_desc = ?" +
                                                                " WHERE enterprise_id = ? and cd_funcionario = ?";                                        
    private static final String SQL_PLANO_SAUDE_MOV_FUN = "IF NOT EXISTS (SELECT * FROM CRHPlanoSaudeMovFun WHERE enterprise_id = ? and cd_plano = ? and cd_funcionario = ? and ano = ? and mes = ?)" + 
                                                                " INSERT INTO CRHPlanoSaudeMovFun (enterprise_id,cd_plano,cd_funcionario,ano,mes,vl_desc_plano,vl_desc_plano_coparticipacao,status) " +
                                                                " VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_PLANO_SAUDE_PROC_FUN = "IF NOT EXISTS (SELECT * FROM CRHPlanoSaudeProcFun WHERE enterprise_id = ? and cd_plano = ? and cd_funcionario = ? and ano = ? and mes = ?)" + 
                                                                " INSERT INTO CRHPlanoSaudeProcFun (enterprise_id,cd_plano,cd_funcionario,ano,mes,vl_desc_plano,vl_desc_plano_coparticipacao)" +
                                                                " VALUES (?,?,?,?,?,?,?)";
     
    /*Strings de url*/
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
   
    
    @Override
    public void gravarFuncionario (int cdEmpresa, DadosFuncionario pf, DadosFuncionaisNG fun) throws JsageImportException{
        if (pf == null){
            String mensagem = "Não foi informada o Funcionario para importar";
            throw new JsageImportException(mensagem);
        }        
        Connection con = null;
        PreparedStatement stmt = null;
        short anoChegadaDefault = 0;        
        short dddDefault = 00;
        short telefoneDefault = 0000000;
        int celularDefault = 000000000;
        String funAposentado = "N";

        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_FUNCIONARIO);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, pf.getIdPessoa());//cd_funcionario
            stmt.setString(3, trataDados.trataGrandesString(pf.getNomePessoa(),40));//nome            
            stmt.setString(4, trataDados.trataGrandesString(pf.getLogradouro(),40));//endereco
            stmt.setInt(5, trataDados.tratarNrEndereco(pf.getNumeroEndereco()));//nr_endereco
            stmt.setString(6, trataDados.trataGrandesString(trataDados.recuperarComplemento(pf.getComplemento()),15));//compl_endereco
            stmt.setString(7, trataDados.trataGrandesString(pf.getBairro(),20));//bairro
            stmt.setString(8, trataDados.recuperarCidade(pf.getIdmunicipio()));//cidade
            stmt.setString(9, trataDados.recuperarUFMunicipio(pf.getIdmunicipio()));//estado
            stmt.setInt(10, trataDados.converterSrintIntCom0(pf.getCep()));//cep
            stmt.setString(11, trataDados.recuperarPai(pf.getIdPessoa()));//pai
            stmt.setString(12, trataDados.recuperarMae (pf.getIdPessoa()));//mae
            stmt.setString(13, pf.getIndSexo());//sexo
            stmt.setShort(14, trataDados.recuperarEstadoCivil(pf.getIdEStadoCivil()));//estado_civil
            stmt.setShort(15, (short) 10);//nacionalidade - todos como brasileiros
            stmt.setShort(16, anoChegadaDefault);//ano_chegada
            stmt.setInt(17, pf.getIdGrauInstrucao());//grau_instrucao - todos com o ensino medio completo
            stmt.setTimestamp(18, pf.getDataNascimento());//dt_nascimento
            stmt.setShort(19, dddDefault);//ddd
            stmt.setShort(20, telefoneDefault);//telefone
            stmt.setString(21, trataDados.trataGrandesString(pf.getApelido(),15));//apelido
            stmt.setString(22, null);//chave_acesso
            stmt.setString(23, null);//senha_acesso
            stmt.setString(24, trataDados.recuperarRaca(pf.getIdRaca()));//raca
            stmt.setString(25,trataDados.convertIntToString(pf.getIdTipoDeficiencia()));//deficiente
            stmt.setString(26, trataDados.recuperarCidade(pf.getIdMunicipioNaturalidade()));//cidade_nascimento
            stmt.setString(27, trataDados.recuperarUFMunicipio(pf.getIdMunicipioNaturalidade()));//estado_nascimento
            stmt.setShort(28, dddDefault);//ddd_celular
            stmt.setInt(29, celularDefault);//celular
            stmt.setString(30, trataDados.trataGrandesString(pf.getNomePessoa(),70));//nomecompleto
            stmt.setString(31, null);//email
            stmt.setTimestamp(32, pf.getDataChegada());//data_chegada
            stmt.setString(33, "R");//tipo logradouro
            stmt.setInt(34, trataDados.recuperarCodigoIBGE(pf.getIdMunicipioNaturalidade()));//cd_municipio
            stmt.setInt(35, trataDados.recuperarCodigoIBGE(pf.getIdMunicipioNaturalidade()));//cd_municipio_nascimento
            stmt.setString(36, funAposentado);//funcionario_aposentado
            stmt.setTimestamp(37, trataDados.horaAtual());//data_hora_alteracao
                        
            stmt.executeUpdate();          
                       
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Funcionario " + pf.getIdPessoa() + " no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }

    @Override
    public void gravarFuncao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG df) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
         
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_FUNCAO);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setTimestamp(3, df.getDataAdmissao());//dt_funcao
            stmt.setString (4, "");
            stmt.setInt(5, df.getIdcargo());//cd_funcao - todos como aux administrativo tabelas incompativeis
            stmt.setTimestamp(6, trataDados.DataFimSistema());//dt_final
            stmt.setTimestamp(7, trataDados.horaAtual());//data_hora_alteracao           
            
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir a Função do Funcionário: " + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarLotacao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG df) throws JsageImportException{
              
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_LOTACAO);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setTimestamp(3, df.getDataAdmissao());//dt_lotacao
            stmt.setInt(4, 1);//cd_filial
            stmt.setInt(5, 1);//cd_nivel1
            stmt.setInt(6, 0);//cd_nivel2
            stmt.setInt(7, 0);//cd_nivel3
            stmt.setInt(8, 1);//cd_ccusto
            stmt.setTimestamp(9, trataDados.DataFimSistema());//dt_final
            stmt.setTimestamp(10, trataDados.horaAtual());//data_hora_alteracao
                        
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir a lotação do Funcionário: " + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarSalario (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG df) throws JsageImportException{
        if (df == null){
            String mensagem = "Não foi informada o funcionario para importar o salario";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        Double horasSemanaisDefault = 44.0;
        Double percAdiantamentoDefault = 0.0;
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_SALARIO);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            System.out.println(df.getDataIncio());
            stmt.setTimestamp(3, df.getDataIncio());//dt_salario
            stmt.setString(4, trataDados.recuperaTipoSalario(df.getIdTipoSalario()));//tipo_salario
            stmt.setDouble(5, horasSemanaisDefault);//nr_horas_semanais
            stmt.setDouble(6, trataDados.trataSalario(df.getSalario()));//vl_salario
            stmt.setDouble(7, percAdiantamentoDefault);//perc_adiantamento
            stmt.setString(8, df.getMotivoMovimento());//motivo
            stmt.setDouble(9, percAdiantamentoDefault);//vl_adiantamento
            stmt.setTimestamp(10, trataDados.DataFimSistema());//dt_final
            stmt.setDouble(11, percAdiantamentoDefault);//perc_adiantamento_mes_anterior
            stmt.setString(12, null);//remuneracao_registro
            stmt.setTimestamp(13, null);//dt_validade_salario
            stmt.setString(14, "N");//nao_compensar_reajuste
            stmt.setString(15, "N");//op_definir_regra_especifica_funcionario
            stmt.setString(16, null);
            stmt.setTimestamp(17, trataDados.horaAtual());
            stmt.setString(18, null);
            
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o salário do Funcionário: " + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
        
    @Override
    public void gravarColaborador (int cdEmpresa, int cdFuncionario, DadosFuncionario df) throws JsageImportException{
        if (df == null){
            String mensagem = "Não foi informada o colaborador";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_COLABORADOR);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setString(3, "F");//tp_colaborador
            stmt.setString(4, trataDados.trataGrandesString(df.getNomePessoa(),40));// nome
                        
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o colaborador: " + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarDocumentos (int cdFuncionario, int cdEmpresa, DadosFuncionario pf) throws JsageImportException{
        if (pf == null || cdFuncionario == 0 || cdEmpresa == 0){
            String mensagem = "Não foi informada os documentos para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        String carteiraDefault = "";
        byte [] fotoDefault = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_DOCUMENTOS);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setString(3, trataDados.trataGrandesString(pf.getNumeroCtps(),8));//nr_carteira
            stmt.setString(4, trataDados.trataGrandesString(pf.getSerieCtps(),5));//serie_carteira
            stmt.setString(5, carteiraDefault);//dv_serie_carteira
            stmt.setString(6, trataDados.recuperarUF(pf.getIdUfCtps()));//uf_carteira
            stmt.setString(7, trataDados.trataGrandesString(trataDados.recuperarPIS(cdFuncionario),14));//pis
            stmt.setString(8, pf.getCpfFormatado());//cpf
            stmt.setString(9, trataDados.trataGrandesString(pf.getNumeroDocumentoIdentidade(),20));//nr_identidade
            stmt.setString(10, trataDados.trataGrandesString(trataDados.tratarOrgaoRG(pf.getOrgaoExpedidorDocumentoIdentidade()),12));//orgao_identidade
            stmt.setString(11, trataDados.tratarUFRG(pf.getOrgaoExpedidorDocumentoIdentidade()));//uf_identidade   
            stmt.setInt(12, trataDados.converterSrintIntCom0(pf.getNumeroCnh()));//nr_habilitacao 
            //trataDados.convertIntToString(pf.getIdcategoriaHabilitacaoCnh()) tamanho da string problema
            stmt.setString(13, trataDados.recuperarCNH(pf.getIdcategoriaHabilitacaoCnh()));//categoria_habilitacao
            stmt.setTimestamp(14, pf.getVencimentoCnh());//vcto_habilitacao
            stmt.setBytes(15, fotoDefault);//foto
            stmt.setInt(16, 0);//nr_titulo
            stmt.setShort(17,trataDados.convertStringToShort(pf.getZonaEleitoral()) );//zona_titulo
            stmt.setShort(18, trataDados.convertStringToShort(pf.getSecaoEleitoral()));//secao_titulo
            stmt.setTimestamp(19, pf.getDataCtps());//dt_emissao_carteira
            stmt.setTimestamp(20, pf.getDataExpedicaoDocumentoIdentidade());//dt_emissao_identidade
            stmt.setTimestamp(21, pf.getDataEmissaocnh());//dt_emissao_habilitacao
            stmt.setTimestamp(22, null);//dt_emissao_titulo
            stmt.setTimestamp(23, pf.getDataInscricao());//dt_emissao_pis
            stmt.setString(24, carteiraDefault);//novo_nr_titulo
            stmt.setString(25, carteiraDefault);//novo_nr_habilitacao
            stmt.setString(26, trataDados.trataGrandesString(pf.getNumeroDocumentoMilitar(),20));//certificado_militar
            stmt.setString(27, trataDados.trataGrandesString(pf.getOrgaoExpedidorRegistroProfissional(),10));//orgao_reg_prof
            stmt.setString(28, trataDados.trataGrandesString(pf.getNumeroRegistroProfissional(),20));//nr_reg_prof
            stmt.setTimestamp(29, pf.getDataExpedicaoRegistroProfissional());//dt_emissao_reg_prof
            stmt.setString(30, trataDados.trataGrandesString(pf.getOrgaoEmissorCnh(),10));//orgao_emissor_cnh
            stmt.setTimestamp(31, pf.getDataValidadeRegistroProfissional());//dt_vcto_reg_prof
            stmt.setTimestamp(32, pf.getDataEmissaoCertidaoCivil());//dt_emissao_certidao
            stmt.setString(33, trataDados.trataGrandesString(pf.getTermoMatriculaCertidaoCivil(),32));//matricula_certidao
            stmt.setString(34, trataDados.trataGrandesString(pf.getLivroCertidaoCivil(),8));//livro_certidao
            stmt.setString(35, trataDados.trataGrandesString(pf.getFolhaCertidaoCivil(),4));//folha_certidao
            stmt.setString(36, trataDados.trataGrandesString(pf.getCartorioCertidaoCivil(),115));//cartorio_certidao
            stmt.setString(37, trataDados.recuperarUF(pf.getIdUfCertidaoCivil()));//uf_certidao
            stmt.setInt(38, 0);//cd_municipio_certidao
            stmt.setTimestamp(39, pf.getDataPrimeiraHabilitacao());//dt_primeira_habilitacao
            //trataDados.recuperarUF(pf.getIdufcnh()) problema tamanho da string
            stmt.setString(40,trataDados.recuperarUF(pf.getIdufcnh()));//uf_habilitacao
            stmt.setTimestamp(41, trataDados.horaAtual());
            stmt.setString(42, null);//id
            
            stmt.executeUpdate();
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir os documentos do Funcionário." + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarDadosFuncionais (int cdEmpresa, int cdFuncionario, DadosFuncionaisNG df, DadosFuncionario fun) throws JsageImportException{
        if (df == null && fun == null){
            String mensagem = "Não foi informada o dependente para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
                
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_DADOS_FUNCIONAIS);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setTimestamp(3, df.getDataAdmissao());//dt_admissao
            stmt.setInt(4, trataDados.recuperarVinculo(df.getIdVinculoEmpregaticio()));//vinculo_empregaticio_rais 
            stmt.setInt(5, trataDados.trataTipoAdmissaoCaged(trataDados.recuperarAdmissaoCaged(df.getIdTipoAdmissaoCaged())) );//codigo_admissao_caged -> idtipoadmissaocaged string
            stmt.setInt(6, trataDados.converterSrintInt(df.getCodigoRegistro()));//nr_registro
            stmt.setInt(7, df.getNumeroDiasContratoExperiencia());//dias_experiencia
            stmt.setString(8, "N");//temporario
            stmt.setTimestamp(9, null);//vcto_contrato_temporario
            //stmt.setInt(10, trataDados.converterSrintInt(trataDados.recuperarBanco(fun.getIdDadosBanco())));//cd_banco_temporario
            //stmt.setInt(11, trataDados.converterSrintInt(trataDados.recuperarAgencia(fun.getIdDadosAgencia())));//nr_agencia_temporario
            //stmt.setString(12, trataDados.recuperarDVAgencia(fun.getIdDadosAgencia()));//dv_agencia_temporario
            //stmt.setInt(13, trataDados.converterSrintInt(trataDados.recuperarConta(cdFuncionario)));//nr_conta_temporario
            stmt.setInt(10, trataDados.pesquisarIDSindicato(df.getIddadossindicato()));//cd_sindicato - não encontrado no ng
            //stmt.setString(14, trataDados.recuperarDVConta(fun.getIdDadosBanco()));//dv_conta_temporario
            stmt.setString(11, trataDados.ValidaCampo(df.isIndSindicalizado()));//sindicalizado
            stmt.setInt(12, trataDados.compararTimestamp(df.getDataUltimaContribuicaoSindical()));//situacao_contr_sindical
            //stmt.setInt(18, cdEmpresa);//nr_cartao_ponto
            stmt.setInt(13, 1);//categoria
            stmt.setInt(14, 0);//ocorrencia
            //stmt.setInt(21, cdEmpresa);//cd_banco_fgts
            //trataDados.converterSrintInt(trataDados.recuperarBanco(fun.getIdDadosBanco()))
            stmt.setInt(15, fun.getIdDadosBanco()+1);//cd_banco_deposito
            //trataDados.converterSrintInt(trataDados.recuperarAgencia(fun.getIdDadosAgencia()))
            stmt.setInt(16, trataDados.converterSrintInt(fun.getCodigoagencia()));//nr_agencia_deposito
            stmt.setString(17, fun.getNumdvagencia());//dv_agencia_deposito
            stmt.setInt(18, trataDados.converterSrintInt(fun.getNumeroconta()));//nr_conta_deposito
            stmt.setString(19, fun.getDigitoverificador());//dv_conta_deposito
            stmt.setInt(20, 1);//op_desconto_inss - recolhimento normal
            stmt.setString(21, "N");//adto_13_ferias
            stmt.setInt(22, 0);//nr_tabela_ats
            //stmt.setTimestamp(30, null);//dt_base_Ats
            //stmt.setDouble(31, cdEmpresa);//vl_fixo1
            //stmt.setDouble(32, cdEmpresa);//vl_fixo2
            //stmt.setDouble(33, cdEmpresa);//vl_fixo3
            //stmt.setDouble(34, cdEmpresa);//vl_fixo4
            //stmt.setDouble(35, cdEmpresa);//vl_fixo5
            //stmt.setString(22, null);//nat_salarial1
            //stmt.setString(23, null);//nat_salarial2
            //stmt.setString(24, null);//nat_salarial3
            //stmt.setString(25, null);//nat_salarial4
            //stmt.setString(26, null);//nat_salarial5
            //stmt.setString(27, null);//descricao1
            //stmt.setString(28, null);//descricao2
            //stmt.setString(29, null);//descricao3
            //stmt.setString(30, null);//descricao4
            //stmt.setString(31, null);//descricao5
            //stmt.setString(32, null);//ref_horas1
            //stmt.setString(33, null);//ref_horas2
            //stmt.setString(34, null);//ref_horas3
            //stmt.setString(35, null);//ref_horas4
            //stmt.setString(36, null);//ref_horas5
            //stmt.setInt(51, 0);//operacao_deposito
            //stmt.setDouble(52, cdEmpresa);//perc_adquirido_ATS
            //stmt.setTimestamp(53, null);//dt_inicio_aviso
            //stmt.setInt(54, cdEmpresa);//op_dispensa
            //stmt.setInt(55, cdEmpresa);//nr_dias_aviso
            //stmt.setInt(56, cdEmpresa);//dias_experiencia_pro
            //stmt.setInt(57, cdEmpresa);//op_exame
            //stmt.setInt(58, cdEmpresa);//nr_meses_exame
            //stmt.setInt(59, cdEmpresa);//op_exposicao
            //stmt.setString(37, null);//frequencia_exposicao
            stmt.setString(23, "NA");//deficiente_fisico
            //stmt.setString(39, null);//temporario2
            //stmt.setTimestamp(63, null);//dt_vencimento_contrato_temporario
            stmt.setString(24, "0");//nr_sic
            //stmt.setString(41, null);//nr_cartao_salario
            //stmt.setString(42, null);//dv_cartao_salario
            stmt.setInt(25, 1);//cd_param_sindicato
            stmt.setString(26, "S");//fgts_empregado_domestico
            //stmt.setInt(69, cdEmpresa);//folhas_ficha
            stmt.setString(27, "N");//enviar_holerith_email
            stmt.setString(28, "N");//enviar_informe_rendimentos_email
            //stmt.setTimestamp(72, null);//dt_vigenciaini
            //stmt.setTimestamp(73, null);//dt_vigenciafin
            stmt.setString(29, "N");//membro_cipa
            stmt.setString(30, "C");//regime_juridico
            stmt.setString(31, "E");//natureza_cargo
            stmt.setInt(32, 1);//regime_previdenciario
            stmt.setString(33, "N");//funcionario_pensionista
            stmt.setInt(34, trataDados.converterSrintInt(df.getNumeroMatricula()));//matricula
            //stmt.setInt(80, cdEmpresa);//tipo_alocacao_simples
            stmt.setInt(35, 1);//tipo_admissao - todos ficaram com admissao
            stmt.setInt(36, 1);//indicativo_admissao
            stmt.setString(37, "N");//caged_percepcao_requerimento_seguro_desemprego_admissao
            stmt.setTimestamp(38, trataDados.horaAtual());//data_hora_alteracao
                       
            stmt.executeUpdate();
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir os dados funcionais do Funcionário: " + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep) throws JsageImportException{
        if (dep == null || cdFuncionario == 0 || cdEmpresa == 0){
            String mensagem = "Não foi informada o dependente para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
               
        String cpf = "000.000.000-00";
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_DEP);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setInt(3, dep.getIdPessoa());//cd_dependente
            stmt.setTimestamp(4, trataDados.horaAtual());//dt_inclusao
            stmt.setString(5, dep.getNomePessoa());//nome
            stmt.setInt(6, trataDados.recuperarParentescoPrincipal(trataDados.recuperarParentescoIrrf(dep.getIdtipodependenciairrf()), dep.isIndDependenteSalarioFamilia(), dep.getIndSexo()));//tipo_parentesco
            stmt.setString(7, null);//descricao_parentesco
            stmt.setTimestamp(8, dep.getDataNascimento());//dt_nascimento - falta data
            stmt.setString(9, "N");//suspende_sf
            stmt.setString(10, null);//observacao
            stmt.setString(11, dep.getCpfFormatado());//cpf_dependente - o cadastro do dependente nao enocntrado cpf
            stmt.setInt(12, trataDados.recuperarParentescoIrrf(dep.getIdtipodependenciairrf()));//esocial_tipo_parentesco
            stmt.setString(13, "N");//esocial_pensionista
            stmt.setTimestamp(14, trataDados.horaAtual());
                       
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir os dependentes do Funcionário." + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarFerias (int cdFuncionario,int cdEmpresa, FeriasNG ferias) throws JsageImportException{
        if (ferias == null || cdFuncionario == 0 || cdEmpresa == 0){
            String mensagem = "Não foi informada os dados de ferias da empresa para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;               
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_FERIAS);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setTimestamp(3, trataDados.trataDataVazia(ferias.getDatainicioperiodogozo()));//data de processamento
            stmt.setString(4, "N");
            stmt.setTimestamp(5, ferias.getDatainicioperiodoaquisitivo());
            stmt.setTimestamp (6, ferias.getDatafimperiodoaquisitivo());
            stmt.setTimestamp(7, ferias.getDatainicioperiodogozo());
            stmt.setTimestamp(8, ferias.getDatafimperiodogozo());
            stmt.setTimestamp(9, ferias.getDatainicioabono());
            stmt.setTimestamp(10, ferias.getDatafimabono());
            stmt.setTimestamp(11, ferias.getDataretornoferias());
            stmt.setDouble(12, ferias.getSalario());
            stmt.setDouble(13, ferias.getDiasfalta());
            stmt.setTimestamp(14, trataDados.trataDataVazia(ferias.getDatainicioperiodogozo()));
            stmt.setString(15,"N");
            stmt.setInt(16, (int)ferias.getDiasferias());
            stmt.setTimestamp(17, trataDados.trataDataVazia(ferias.getDataInicioPeriodoAquisitivoFeriasPendente()));
            stmt.setDouble(18, 0);
            stmt.setInt(19, 1);
            stmt.setInt(20, (int)ferias.getDiasferias());
            stmt.setDouble(21, ferias.getSaldodias());
            stmt.setString(22, "N");            
            
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir as férias do Funcionário." + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarFunEspecifico (int cdFuncionario, int cdEmpresa) throws JsageImportException{
        if (cdFuncionario == 0 || cdEmpresa == 0){
            String mensagem = "Não foi informada os dados de Específico da empresa para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;               
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_FUNESPECIFICO);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario           
            
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir os dados específicos do Funcionário." + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarControleESocial (int cdEmpresa, int cdFuncionario) throws JsageImportException{
        if ( cdFuncionario == 0 || cdEmpresa == 0){
            String mensagem = "Não foi informada os dados do esocial da empresa para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;  
        String tabelas [] = {"CRHFuncionarioEstagio","CRHFuncionarioTipoAdmissao","CRHFunDadosEstrangeiro","CRHFunTemporario","Funcionario","FunDependente","FunDocumento","FunFuncao","Funfuncional","FunHorario","FunLotacao","FunSalario"};
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CONTROLE_ESOCIAL);
            for (int i=0; i < tabelas.length; i++){
                stmt.setInt(1, cdEmpresa);//cd_empresa
                stmt.setInt(2, cdFuncionario);//cd_funcionario
                stmt.setString(3, "S-2200");
                stmt.setString(4, tabelas[i]);
                stmt.setInt(5, cdEmpresa);//cd_empresa
                stmt.setInt(6, cdFuncionario);//cd_funcionario
                stmt.setString(7, "S-2200");
                stmt.setString(8, tabelas[i]);
                stmt.setString(9, "A");            
            
            stmt.executeUpdate();
            }
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o controle do esocial do Funcionário." + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarControleCamposESocial (int cdEmpresa, int cdFuncionario) throws JsageImportException{
        if ( cdFuncionario == 0 || cdEmpresa == 0){
            String mensagem = "Não foi informada os dados do controle campos esocial da empresa para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;  
        String tabelas [] = {"Funfuncional","FunHorario","FunHorario"};
        String descricao [] ={"CNPJ do Sindicato","Tipo de Regime de Jornada","Tabela de Horário"};
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CONTROLE_CAMPOS_ESOCIAL);
            for (int i=0; i < tabelas.length; i++){
                stmt.setInt(1, cdEmpresa);//cd_empresa
                stmt.setInt(2, cdFuncionario);//cd_funcionario
                stmt.setString(3, "S-2200");
                stmt.setString(4, tabelas[i]);
                //stmt.setInt(5, trataDados.gerarSequenciaESocial()+1);
                //stmt.setString(6, descricao[i]);
                //stmt.setString(7, "verdadeiro");            
            
            stmt.executeUpdate();
            }
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir os controles dos campos do esocial do Funcionário." + cdFuncionario);
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
     public void gravarMovEvento (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_MOV_EVENTO);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, cdFuncionario);
            stmt.setInt(3, trataDados.recuperarLancamentoFolha(movimentacao.getIdverba()));
            stmt.setInt(4, movimentacao.getCompetenciaano());
            stmt.setInt(5, movimentacao.getCompetenciames());
            stmt.setInt(6, cdEmpresa);
            stmt.setInt(7, movimentacao.getCompetenciames());
            stmt.setInt(8, movimentacao.getCompetenciaano());
            stmt.setInt(9, cdFuncionario);
            stmt.setInt(10, trataDados.recuperarLancamentoFolha(movimentacao.getIdverba()));
            stmt.setDouble(11,0);
            
             stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o MovEvento na Empresa: ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
     }
     
    @Override
     public void gravarProcBase (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario, DadosFuncionaisNG dadosFuncionais) throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PROC_BASE);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, cdFuncionario);
            stmt.setInt(3, 2);
            stmt.setInt(4, movimentacao.getCompetenciaano());
            stmt.setInt(5, movimentacao.getCompetenciames());
            stmt.setInt(6, cdEmpresa);
            stmt.setInt(7,  cdFuncionario);
            stmt.setInt(8, movimentacao.getCompetenciames());
            stmt.setInt(9, 2);
            stmt.setInt(10, movimentacao.getCompetenciaano());
            stmt.setDouble(11, dadosFuncionais.getSalario());
            stmt.setDouble(12, dadosFuncionais.getSalario());
            stmt.setDouble(13, dadosFuncionais.getSalario() );
            stmt.setDouble(14, 0);
            stmt.setDouble(15, dadosFuncionais.getSalario());
            stmt.setDouble(16, 189.59 );
            stmt.setDouble(17,0);
            stmt.setTimestamp(18, movimentacao.getDatainiciofolha() );
            stmt.setString(19, "0");
            stmt.setDouble(20, 0 );
            stmt.setDouble(21, dadosFuncionais.getSalario());
            stmt.setDouble(22, dadosFuncionais.getSalario());
            stmt.setDouble(23,dadosFuncionais.getSalario());
            stmt.setDouble(24, 0 );
            stmt.setDouble(25, dadosFuncionais.getSalario());
            stmt.setDouble(26, 0);
            stmt.setDouble(27,0);
            
             stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o ProcBase na Empresa: ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
     }
     
    @Override
     public void gravarProcEvento (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PROC_EVENTO);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, cdFuncionario);
            stmt.setInt(3, 2);
            stmt.setDouble(4, trataDados.recuperarLancamentoFolha(movimentacao.getIdverba()));
            stmt.setInt(5, movimentacao.getCompetenciaano());
            stmt.setInt(6, movimentacao.getCompetenciames());
            stmt.setInt(7, cdEmpresa);
            stmt.setInt(8,  cdFuncionario);
            stmt.setInt(9, movimentacao.getCompetenciames());
            stmt.setInt(10, 2);
            stmt.setDouble(11, trataDados.recuperarLancamentoFolha(movimentacao.getIdverba()));
            stmt.setInt(12, movimentacao.getCompetenciaano());
            stmt.setDouble (13, trataDados.trataReferencia(movimentacao));
            stmt.setString(14, "" + trataDados.trataReferencia(movimentacao));
            stmt.setDouble(15, trataDados.trataValorProcEvento(movimentacao));
            
            stmt.executeUpdate();
      
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o ProcEvento da Empresa: ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
     
     }
     
    @Override
     public void gravarProcImposto (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PROC_IMPOSTO);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, cdFuncionario);
            stmt.setInt(3, 2);
            stmt.setInt(4, movimentacao.getCompetenciaano());
            stmt.setInt(5, movimentacao.getCompetenciames());
            stmt.setInt(6, cdEmpresa);
            stmt.setInt(7,  2);
            stmt.setInt(8, movimentacao.getCompetenciames());
            stmt.setInt(9, movimentacao.getCompetenciaano());
            stmt.setInt(10, cdFuncionario);
            stmt.setDouble(11, 0);
            stmt.setDouble(12, 0);
            stmt.setDouble (13, 0);
            stmt.setDouble(14, 0);
            stmt.setDouble(15, 72);
            stmt.setDouble(16, 0);
            stmt.setDouble(17, 13.5);
     
            stmt.executeUpdate();
      
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o ProcImposto da Empresa: ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
     }
     
    @Override
    public void gravarPlanoSaude (PlanoSaudeNG plano) throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PLANO_SAUDE);
            stmt.setInt(1, trataDados.converterSrintInt(plano.getCodigooperadoraplanosaude()));
            stmt.setInt(2, trataDados.converterSrintInt(plano.getCodigooperadoraplanosaude()));
            stmt.setString (3, plano.getNomecompletooperadoraplanosaude());
            stmt.setString(4, plano.getNomereduzidooperadoraplanosaude() );
            stmt.setString(5, trataDados.formatarCampo("##.###.###/####-##", plano.getCnpj()));
            stmt.setString(6, plano.getRegistroans());
            stmt.setString(7, "A");
                
            stmt.executeUpdate();
      
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Plano de Saude ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
     }
    
    @Override
    public void gravarPlanoSaudeGeral (PlanoSaudeNG plano, int cdEmpresa) throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PLANO_SAUDE_GERAL);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, 1);
            stmt.setInt(3, trataDados.converterSrintInt(plano.getCodigooperadoraplanosaude()));
            stmt.setInt(4, cdEmpresa);
            stmt.setInt (5, 1);
            stmt.setInt(6, trataDados.converterSrintInt(plano.getCodigooperadoraplanosaude()));
            stmt.setString(7, "Geral");
            stmt.setInt(8, 201);
            stmt.executeUpdate();
      
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Plano de Saude ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
     }
    @Override
    public void gravarPlanoSaudeFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PLANO_SAUDE_FUNCIONARIO);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, 1);
            stmt.setInt(3, cdFuncionario);
            stmt.setInt(4, cdEmpresa);
            stmt.setInt (5, 1);
            stmt.setInt(6, cdFuncionario);
            stmt.setDouble(7, trataDados.trataValorProcEvento(movimento));
            stmt.setString(8, "A");
            stmt.setDouble(9, trataDados.trataValorProcEvento(movimento));
            stmt.setInt(10, cdEmpresa);
            stmt.setInt(11, cdFuncionario);
            stmt.executeUpdate();
      
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Plano de Saude ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    
    @Override
    public void gravarPlanoSaudeMovFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PLANO_SAUDE_MOV_FUN);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, 1);
            stmt.setInt(3, cdFuncionario);
            stmt.setInt(4, movimento.getCompetenciaano());
            stmt.setInt (5, movimento.getCompetenciames());
            stmt.setInt(6, cdEmpresa);
            stmt.setInt(7, 1);
            stmt.setInt(8, cdFuncionario);
            stmt.setInt(9, movimento.getCompetenciaano());
            stmt.setInt(10, movimento.getCompetenciames());
            stmt.setDouble(11, trataDados.trataValorProcEvento(movimento));
            stmt.setInt(12, 0);
            stmt.setInt(13, 2);
            stmt.executeUpdate();
      
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Plano de Saude movimento do Funcionario. ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarPlanoSaudeProcFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;  
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PLANO_SAUDE_PROC_FUN);
            stmt.setInt(1, cdEmpresa);
            stmt.setInt (2, 1);
            stmt.setInt(3, cdFuncionario);
            stmt.setInt(4, movimento.getCompetenciaano());
            stmt.setInt (5, movimento.getCompetenciames());
            stmt.setInt(6, cdEmpresa);
            stmt.setInt(7, 1);
            stmt.setInt(8, cdFuncionario);
            stmt.setInt(9, movimento.getCompetenciaano());
            stmt.setInt(10, movimento.getCompetenciames());
            stmt.setDouble(11, trataDados.trataValorProcEvento(movimento));
            stmt.setInt(12, 0);
           
            stmt.executeUpdate();
      
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Plano de Saude movimento do Funcionario. ");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    public List pesquisarTodos() throws JsageImportException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PESQUISARTODOS);
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                FuncionarioSAGE fun = criarFuncionario(rs);
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
    /**
     * Pesquisa se uma empresa existe no sage atraves do cnpj da empresa
     * @param cnpj
     * @return
     * @throws JsageImportException 
     */
    @Override
    public List pesquisaCNPJ(String cnpj) throws JsageImportException {
        if (cnpj == null || cnpj.isEmpty()) {
            return recuperarEmpresas();
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PESQUISAREMPRESACNPJ);
            stmt.setString(1, cnpj );
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                EmpresaSAGE pj = criarEmpresaSAGE(rs);
                listaFuncionarios.add(pj);
            }
            return listaFuncionarios;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da empresa de CNPJ: " + cnpj);
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    @Override
    public List pesquisaIDCNPJ(int id, String cnpj) throws JsageImportException {
        if (cnpj == null || cnpj.isEmpty()) {
            return recuperarEmpresas();
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PESQUISAREMPRESAIDCNPJ);
            stmt.setInt(1,id);
            stmt.setString(2, cnpj );
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                EmpresaSAGE pj = criarEmpresaSAGE(rs);
                listaFuncionarios.add(pj);
            }
            return listaFuncionarios;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta da empresa de CNPJ: " + cnpj);
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    
    @Override
    public List pesquisaFuncionario(int idPessoa, int cdEmpresa, String cpf) throws JsageImportException {
        if (idPessoa == 0) {
            return recuperarEmpresas();
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PESQUISAREMPRESA_FUNCIONARIO);
            
            stmt.setInt(1, idPessoa);
            stmt.setInt(2, cdEmpresa);
            stmt.setString(3, cpf );
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                FuncionarioSAGE pf = criarFuncionario(rs);
                listaFuncionarios.add(pf);
            }
            return listaFuncionarios;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível capturar os funcionários.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    @Override
    public List pesquisaFuncionarioNome( int cdEmpresa, String cpf) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PESQUISAREMPRESA_FUN);
            
            stmt.setInt(1, cdEmpresa);
            stmt.setString(2, cpf );
            
            rs = stmt.executeQuery();
            List listaFuncionarios = new ArrayList();
            while (rs.next()) {
                FuncionarioSAGE pf = criarFuncionario(rs);
                listaFuncionarios.add(pf);
            }
            return listaFuncionarios;
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Não foi possível capturar os funcionários.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    @Override
    public List pesquisaIdDependente (int cdFuncionario, int idDependente) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PESQUISAR_DEP_ID);
            stmt.setInt(1, idDependente );
            rs = stmt.executeQuery();
            List listaDependentes = new ArrayList();
            while (rs.next()) {
                DependenteSAGE pf = criarDependente(rs);
                listaDependentes.add(pf);
            }
            return listaDependentes;
            
            } catch (SQLException exc) {
                StringBuffer mensagem = new StringBuffer("Impossível consulta de dependentes duplicados.");
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
            stmt = con.prepareStatement(SQL_PESQUISARTODOS);
            rs = stmt.executeQuery();
            List listaEmpresas = new ArrayList();
            while (rs.next()) {
                EmpresaSAGE emp = criarEmpresaSAGE(rs);
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
    
    @Override
    public boolean testaConexaoSAGE (String server, String bd, String port, String user, String password) throws JsageImportException{
        Connection con = null;
        boolean flag = false;
        con = GerenciadorConexao.getConnection(server, bd, port, user, password);
        if (con != null){
            flag = true;
        }
        
        return flag;
    }
    
    private EmpresaSAGE criarEmpresaSAGE (ResultSet rs) throws JsageImportException{
        EmpresaSAGE emp = new EmpresaSAGE();
        try{
        emp.setCd_empresa(rs.getInt("cd_empresa"));
        emp.setRazao(rs.getString("razao"));
        emp.setCnpj_cpf(rs.getString("cnpj_cpf"));
        emp.setDt_sistema(rs.getTimestamp("dt_sistema"));
        emp.setDt_inicial(rs.getTimestamp("dt_inicial"));
        emp.setDt_final(rs.getTimestamp("dt_final"));
        emp.setIntegracao_contabil(rs.getString("integracao_contabil"));
        emp.setCentro_custo(rs.getString("centro_custo"));
        emp.setApropriacao_tributos(rs.getString("apropriacao_tributos"));
        emp.setPagamento_tributos(rs.getString("pagamento_tributos"));
        emp.setContabilizacao_online(rs.getString("contabilizacao_online"));
        emp.setAgrupar_lancamentos(rs.getString("agrupar_lancamentos"));
        emp.setOpcao_agrupamento(rs.getShort("opcao_agrupamento"));
        emp.setExcluir_icms_entradas(rs.getString("excluir_icms_entradas"));
        emp.setExcluir_ipi(rs.getString("excluir_ipi"));
        emp.setContabilizar_nota_nota(rs.getString("contabilizar_nota_nota"));
        emp.setNivel1(rs.getString("nivel1"));
        emp.setMascara_plano_contas(rs.getString("mascara_plano_contas"));
        emp.setNum_niveis_plano_contas(rs.getInt("num_niveis_plano_contas"));
        emp.setNum_digitos_plano_contas(rs.getString("num_digitos_plano_contas"));
        emp.setComprimento_plano_contas(rs.getString("comprimento_plano_contas"));
        emp.setSeq_conta(rs.getInt("seq_conta"));
        emp.setOpcao_caixa(rs.getInt("opcao_caixa"));
        emp.setLibera_lote_aberto(rs.getString("libera_lote_aberto"));
        emp.setLanca_lote_lib(rs.getString("lanca_lote_lib"));
        emp.setOpcao_conta_cliente(rs.getString("opcao_conta_cliente"));
        emp.setOpcao_conta_fornecedor(rs.getString("opcao_conta_fornecedor"));
        emp.setOp_calculo_horista(rs.getInt("op_calculo_horista"));
        emp.setPossui_tomador(rs.getString("possui_tomador"));
        emp.setPagamento_mes(rs.getString("pagamento_mes"));
        emp.setProlabore_mes(rs.getString("prolabore_mes"));
        emp.setCargos_salarios(rs.getString("cargos_salarios"));
        emp.setProdutor_rural(rs.getString("produtor_rural"));
        emp.setNaooptante_liminar(rs.getString("naooptante_liminar"));
        emp.setOptante_liminar(rs.getString("optante_liminar"));
        emp.setUtiliza_conta_clifor(rs.getString("utiliza_conta_clifor"));
        emp.setStatus(rs.getString("status"));
        emp.setIntegracao_csc(rs.getString("integracao_csc"));
        emp.setSimples_crh(rs.getString("simples_crh"));
        emp.setFaturamento_simples_crh(rs.getString("faturamento_simples_crh"));
        emp.setMicroempresa_crh(rs.getString("microempresa_crh"));
        emp.setEpp_crh(rs.getString("epp_crh"));
        emp.setImporta_lote_diferenca(rs.getString("importa_lote_diferenca"));
        emp.setUtiliza_controle_clifor(rs.getString("utiliza_controle_clifor"));
        emp.setOpcao_seguro_desemprego(rs.getInt("opcao_seguro_desemprego"));
        emp.setDescontar_13_negativo(rs.getString("descontar_13_negativo"));
        emp.setOp_calculo_numero_dias(rs.getShort("op_calculo_numero_dias"));
        emp.setEntSemFinsLucrativos(rs.getString("EntSemFinsLucrativos"));
        emp.setRecolhimento_contr_sindical_centralizada(rs.getString("recolhimento_contr_sindical_centralizada"));
        emp.setCalc30_opcao_salario(rs.getInt("calc30_opcao_salario"));
        emp.setCalc30_opcao_salario_fevereiro(rs.getString("calc30_opcao_salario_fevereiro"));
        emp.setCalc30_opcao_dias(rs.getInt("calc30_opcao_dias"));
        emp.setOpcao_historico_contabil(rs.getString("opcao_historico_contabil"));
        emp.setOpcao_data_lancamento_contabil(rs.getString("opcao_data_lancamento_contabil"));
        emp.setOptante_liminar_aviso_previo(rs.getString("optante_liminar_aviso_previo"));
        emp.setMicro_emp_indiv_crh(rs.getString("micro_emp_indiv_crh"));
        emp.setEmpresa_cidada(rs.getString("empresa_cidada"));
        emp.setUtiliza_controle_parcelas_auditoria(rs.getInt("utiliza_controle_parcelas_auditoria"));
        emp.setOpcao_data_aviso_previo(rs.getString("opcao_data_aviso_previo"));
        emp.setForma_digitacao_caixa_banco_auditoria(rs.getInt("forma_digitacao_caixa_banco_auditoria"));
        emp.setHistorico_retencoes(rs.getString("historico_retencoes"));
           
        
        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Empresa.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return emp;
    } 
    
    private FuncionarioSAGE criarFuncionario (ResultSet rs) throws JsageImportException{
        FuncionarioSAGE fun = new FuncionarioSAGE();
        try {
            fun.setNome(rs.getString("nome"));
            fun.setEndreco(rs.getString("endereco"));
            fun.setNr_endereco(rs.getInt("nr_endereco"));
            fun.setCompl_endereco(rs.getString("compl_endereco"));
            fun.setBairro(rs.getString("bairro"));
            fun.setCidade(rs.getString("cidade"));
            fun.setEstado(rs.getString("estado"));
            fun.setCep(rs.getInt("cep"));
            fun.setPai(rs.getString("pai"));
            fun.setMae(rs.getString("mae"));
            fun.setSexo(rs.getString("sexo"));
            fun.setEstado_civil(rs.getShort("estado_civil"));
            fun.setNacionalidade(rs.getShort("nacionalidade"));
            fun.setAno_chegada(rs.getShort("ano_chegada"));
            fun.setGrau_instrucao(rs.getShort("grau_instrucao"));
            fun.setDt_nascimento(rs.getTimestamp("dt_nascimento"));
            fun.setDdd_fone(rs.getShort("ddd_fone"));
            fun.setTelefone(rs.getInt("telefone"));
            fun.setApelido(rs.getString("apelido"));
            fun.setChave_acesso(rs.getString("chave_acesso"));
            fun.setSenha_acesso(rs.getString("senha_acesso"));
            fun.setRaca(rs.getString("raca"));
            fun.setDeficiente(rs.getString("deficiente"));
            fun.setCidade_nascimento(rs.getString("cidade_nascimento"));
            fun.setEstado_nascimento(rs.getString("estado_nascimento"));
            fun.setDdd_celular(rs.getShort("ddd_celular"));
            fun.setCelular(rs.getInt("celular"));
            fun.setNomecompleto(rs.getString("nomecompleto"));
            fun.setEmail(rs.getString("email"));
            fun.setData_chegada(rs.getTimestamp("data_chegada"));
            fun.setTipo_logradouro(rs.getString("tipo_logradouro"));
            fun.setCd_municipio(rs.getInt("cd_municipio"));
            fun.setCd_municipio_nascimento(rs.getInt("cd_municipio_nascimento"));
            fun.setFuncionario_aposentado(rs.getString("funcionario_aposentado"));
            fun.setData_hora_alteracao(rs.getTimestamp("data_hora_alteracao"));
            fun.setId(rs.getString("id"));
            fun.setId_endereco(rs.getString("id_endereco"));
            
        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do Funcionario.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        
        return fun;
    }
    
    private DependenteSAGE criarDependente (ResultSet rs) throws JsageImportException{
        DependenteSAGE dep = new DependenteSAGE();
        try{
            dep.setCdEmpresa(rs.getInt("cd_empresa"));
            dep.setCdFuncionario(rs.getInt("cd_funcionario"));
            dep.setCdDependente(rs.getInt("cd_dependente"));
            dep.setDtInclusao(trataDados.horaAtual());
            dep.setNome(rs.getString("nome"));
            dep.setTipoParentesco(rs.getInt("tipo_parentesco"));
            dep.setDescricaoParentesco(rs.getString("descricao_parentesco"));
            dep.setDtNascimento(rs.getTimestamp("dt_nascimento"));
            dep.setSuspendeSf(rs.getString("suspende_sf"));
            dep.setObservacao(rs.getString("observacao"));
            dep.setCpfDependente(rs.getString("cpf_dependente"));
            dep.setEsocialTipoParentesco(rs.getInt("esocial_tipo_parentesco"));
            dep.setEsocialPensionista(rs.getString("esocial_pensionista"));
            dep.setEsocialEventoPensao(rs.getInt("esocial_evento_pensao"));
            dep.setEsocialPercentualPensao(rs.getDouble("esocial_percentual_pensao"));
            
            return dep;
        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados do Funcionario.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
    }  
}