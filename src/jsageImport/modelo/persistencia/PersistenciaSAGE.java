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
import jsageImport.modelo.dominio.FuncionarioSAGE;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaSAGE;

/**
 *
 * @author Gustavo
 */
public class PersistenciaSAGE implements IPersistenciaSAGE {

    PropertiesJdbc jdbc = new PropertiesJdbc();
    TratamentoDados trataDados = new TratamentoDados();
    
    private static final String SQL_INCLUIR_EMPRESA ="INSERT INTO CRDEmpresa  (cd_empresa,razao,cnpj_cpf,dt_sistema,dt_inicial,dt_final,integracao_contabil,centro_custo," +
                                                                               "apropriacao_tributos,pagamento_tributos,contabilizacao_online,agrupar_lancamentos" +
                                                                               ",opcao_agrupamento,excluir_icms_entradas,excluir_ipi,contabilizar_nota_nota,nivel1" +
                                                                               ",mascara_plano_contas,num_niveis_plano_contas,num_digitos_plano_contas,comprimento_plano_contas,seq_conta" +
                                                                               ",opcao_caixa,libera_lote_aberto,lanca_lote_lib,opcao_conta_cliente,opcao_conta_fornecedor,op_calculo_horista" +
                                                                               ",possui_tomador,pagamento_mes,prolabore_mes,cargos_salarios,produtor_rural,naooptante_liminar" +
                                                                               ",optante_liminar,utiliza_conta_clifor,status,integracao_csc,simples_crh,faturamento_simples_crh" +
                                                                               ",microempresa_crh,epp_crh,importa_lote_diferenca,utiliza_controle_clifor,opcao_seguro_desemprego " + 
                                                                               ",descontar_13_negativo,op_calculo_numero_dias,EntSemFinsLucrativos,recolhimento_contr_sindical_centralizada" +
                                                                               ",calc30_opcao_salario,calc30_opcao_salario_fevereiro,calc30_opcao_dias,opcao_historico_contabil," + 
                                                                               "opcao_data_lancamento_contabil,optante_liminar_aviso_previo,micro_emp_indiv_crh,empresa_cidada," + 
                                                                               "utiliza_controle_parcelas_auditoria,emitir_aviso_ferias,opcao_data_aviso_previo,forma_digitacao_caixa_banco_auditoria" +
                                                                               ",grau_padrao_relatorio,data_hora_alteracao) " +
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_ESTABELECIMENTO = "INSERT INTO CRDEstabelecimento (cd_empresa,cd_estabelecimento,razao,fantasia,endereco,numero,bairro,cidade,uf,ddd_telefone" +
                                                                               ",telefone,natureza_juridica,categoria,cnpj_cpf,local_registro,nome_titular,denom_titular,cpf_titular,cd_responsavel_estabelecimento" +
                                                                               ",salario_educacao,denominacao_pagina_csc,contribuinte_icms,contribuinte_ipi,contribuinte_iss,opcao_ipi,compensacao_tributos_retido" +
                                                                               ",perc_cs_venda,denominacao_pagina_cef,substituto_tributario,utiliza_ecf,tributacao,qualificacao,dt_inicio_atividade,antecipar_irpj_csll" +
                                                                               ",calcular_excedente_antecipacao_irpj_csll,parcelamento_irpj_csll,tipo_estabelecimento,instituicao_financeira,status,razao_completa" +
                                                                               ",estatuto_microempresa,opcao_vencimento_darf,beneficiario_prodepe,difere_icms_rs,opcao_super,vl_super_icms_fixo,vl_super_iss_fixo" +
                                                                               ",protocolos_baixa_guias,vl_minimo_retencao_pis_cofins_csll,vl_minimo_retencao_irf,cd_classificacao,aliquota_fecp,natureza_juridica_ecf" +
                                                                               ",tipo_entidade_ecf,tipo_plano_ecf,coeficiente_ciap_opcao,data_hora_alteracao)" +
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_PESQUISARTODOS = "SELECT * FROM CRDEmpresa";
    private static final String SQL_PESQUISAREMPRESACNPJ = "SELECT * FROM CRDEmpresa WHERE cnpj_cpf = ?";
    
    /* Strings SQL para funcionários*/
    private static final String SQL_INCLUIR_FUNCIONARIO ="INSERT INTO Funcionario (cd_empresa,cd_funcionario,nome,endereco,nr_endereco,compl_endereco,bairro,cidade,estado,cep,pai,mae,sexo" +
                                                                                   ",estado_civil,nacionalidade,ano_chegada,grau_instrucao,dt_nascimento,ddd_fone,telefone,apelido,chave_acesso" +
                                                                                   ",senha_acesso,raca,deficiente,cidade_nascimento,estado_nascimento,ddd_celular,celular,nomecompleto,email" +
                                                                                   ",data_chegada,tipo_logradouro,cd_municipio,cd_municipio_nascimento,funcionario_aposentado,data_hora_alteracao" +
                                                                                   ")" + 
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_FUNCAO = "INSERT INTO FunFuncao (cd_empresa,cd_funcionario,dt_funcao,cd_funcao,dt_final,data_hora_alteracao)" +
                                                                    " VALUES (?,?,?,?,?,?)";
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
    private static final String SQL_PESQUISAR_DEP_ID = "SELECT * FROM FunDependente WHERE cd_dependente = ?";
    
    private static final String SQL_CADASTRO_EMPRESA = "INSERT INTO CRDEmpresa (cd_empresa, razao, cnpj_cpf, dt_sistema, dt_inicial, dt_final, integracao_contabil, centro_custo, apropriacao_tributos"
                                                                        +"pagamento_tributos, contabilizacao-online, agrupar_lancamentos,opcao_agrupamento,excluir_icms_entradas, excluir_ipi"  
                                                                        +"contabilizar_nota_nota, nivel1,mascara_plano_contas,num_niveis_plano_contas,num_digitos_plano_contas,comprimento_plano_contas"
                                                                        +"seq_conta,opcao_caixa,libera_lote_aberto,lanca_lote_lib,opcao_conta_cliente,opcao_conta_fornecedor,op_calculo_horista"
                                                                        +"possui_tomador,pagamento_mes,prolabore_mes,cargos_salarios,pagamento_mes,prolabore_mes, cargos_salarios,produtor_rural,  "
                                                                        +"naooptante_liminar, optante_liminar,nome_responsavel_rescisao,funcao_responsavel_rescisao,utiliza_conta_clifor,status"
                                                                        +"integracao_csc,simples_crh,faturamento_simples_crh,microempresa_crh,epp_crh,importa_lote_diferenca,utiliza_controle_clifor"
                                                                        +"opcao_seguro_desemprego,descontar_13_negativo,op_calculo_numero_dias,EntSemFinsLucrativos,recolhimento_contr_sindical_centralizada"
                                                                        +"calc30_opcao_salario,calc30_opcao_salario_fevereiro,calc30_opcao_dias,opcao_historico_contabil,optante_liminar_aviso_previo,micro_emp_indiv_crh"
                                                                        +"empresa_cidada,utiliza_controle_parcelas_auditoria,emitir_aviso_ferias,opcao_data_aviso_previo,forma_digitacao_caixa_banco_auditoria,grau_padrao_relatorio)"
                                                                        +"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_CADASTRO_ESTABELECIMENTO = "INSERT INTO CRDEstabelecimento   (cd_empresa,cd_estabelecimento,razao,fantasia,endereco,numero,complemento,bairro,cidade,cep,uf,cd_municipio,ddd_telefone\n" +
"								  ,telefone,natureza_juridica,categoria,cnpj_cpf,cnae,local_registro,atividades,nome_titular,denom_titular,cpf_titular\n" +
"								  ,cd_responsavel_estabelecimento,cd_responsavel_caged,cd_responsavel_sefip,salario_educacao,denominacao_pagina_csc\n" +
"								  ,contribuinte_icms,contribuinte_ipi,contribuinte_iss,credita_pis,opcao_ipi,compensacao_tributos_retido,denominacao_pagina_cef\n" +
"								  ,substituto_tributario,utiliza_ecf,tributacao,qualificacao,dt_inicio_atividade,antecipar_irpj_csll,calcular_excedente_antecipacao_irpj_csll\n" +
"								  ,parcelamento_irpj_csll,tipo_estabelecimento,instituicao_financeira,status,razao_completa,estatuto_microempresa,opcao_vencimento_darf\n" +
"								  ,beneficiario_prodepe,difere_icms_rs,opcao_super,vl_super_icms_fixo,vl_super_iss_fixo,protocolos_baixa_guias,cd_classificacao\n" +
"								  ,natureza_juridica_ecf,tipo_entidade_ecf,tipo_plano_ecf,coeficiente_ciap_opcao)\n" +
"								  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    /*Strings de url*/
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
   
    @Override
    public void gravarEmpresa (PessoaJuridica pj) throws JsageImportException{
        if (pj == null) {
            String mensagem = "Não foi informada a Empresa para importar";
            throw new JsageImportException(mensagem);
        }
        jdbc.lerPropriedades("SAGE");
        Connection con = null;
        PreparedStatement stmt = null;
                
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_EMPRESA);
            stmt.setInt(1, (short) pj.getIdPessoa());
            stmt.setString(2, pj.getNomePessoa());
            stmt.setString(3, pj.getCnpjFormatado());
            stmt.setTimestamp(4, pj.getDataFundacao());//dt_sistema
            stmt.setTimestamp(5, pj.getDataInicioAtividade());//dt_inicial
            stmt.setTimestamp(6, null);//dt_fim
            stmt.setString(7, "S");//integracao_contabil
            stmt.setString(8, "N");//centro_custo
            stmt.setString(9, "S");//apropriacao_tributo
            stmt.setString(10,"N");//pagamento_tributo
            stmt.setString(11,"N");//contabilizacao_online
            stmt.setString(12, "N");//agrupar_lancamento
            stmt.setShort(13, (short)1);//opcao_agrupamento
            stmt.setString(14, "N");//excluir_icms_entrada
            stmt.setString(15, "N");//excluir_ipi
            stmt.setString(16, "N");//contabilizar_nota_nota
            stmt.setString(17, "GERAL");//nivel1
            stmt.setString(18,"X-X-X-XX-X");//mascara_plano_contas
            stmt.setInt(19, 5);//num_niveis_plano_contas
            stmt.setString(20, "11123");//num_digito_plano_contas
            stmt.setString(21,"0102030508");//comprimento_plano_contas
            stmt.setInt(22, 0);//seq_conta
            stmt.setInt(23, 1);//opcao_caixa
            stmt.setString(24, "N");//libera_lote_aberto
            stmt.setString(25, "N");//lanca_lote_lib
            stmt.setString(26, "2");//opcao_conta_cliente
            stmt.setString(27, "2");//opcao_conta_fornecedor
            stmt.setInt(28, 3);//op_calculo_horista
            stmt.setString(29, "S");//possui_tomador
            stmt.setString(30, "N");//pgamento_mes
            stmt.setString(31, "N");//prolabore_mes
            stmt.setString(32, "N");//cargos_salarios
            stmt.setString(33, "N");//produtor_rural
            stmt.setString(34, "N");//naooptante_liminar
            stmt.setString(35, "N");//optante_liminar
            stmt.setString(36, "N");//utiliza_conta_clifor
            stmt.setString(37, "A");//status
            stmt.setString(38, "N");//integracao_csc
            stmt.setString(39, "S");//simpesl_crh
            stmt.setString(40, "N");//faturamento_simples_crh
            stmt.setString(41, "S");//microempresa_crh
            stmt.setString(42, "N");//epp_crh
            stmt.setString(43, "N");//importa_lote_diferenca
            stmt.setString(44, "N");//utiliza_controle_clifor
            stmt.setInt(45, 0);//opcao_seguro_desemprego
            stmt.setString(46,"N");//descontar_13_negativo
            stmt.setShort(47,(short) 1);//op_calculo_numero_dias
            stmt.setString(48, "N");//entSemFinsLucrativo
            stmt.setString(49, "N");//recolhimento_contr_sidical_centralizada
            stmt.setInt(50, 1);//calc30_opcao_salario
            stmt.setString(51, "N");//calc30_opca_salario_fevereiro
            stmt.setInt(52, 1);//calc30_opca_dias
            stmt.setString(53, "R");//opcao_shitorico_contabil
            stmt.setString(54, "C");//opcao_data_lancamento_contabil
            stmt.setString(55, "N");//optante_liminar_aviso_previo
            stmt.setString(56, "N");//micro_emp_indiv_crh
            stmt.setString(57, "N");//empresa_cidade
            stmt.setInt(58, 0);//utiliza_controle_parcelas_auditoria
            stmt.setString(59, "N");//emitir_aviso_ferias
            stmt.setString(60, "l");//opcao_data_aviso_previo
            stmt.setInt(61,0);//forma_digitacao_caixa_banco_auditoria
            stmt.setInt(62, 2);//grau_padrao_relatorio
            stmt.setTimestamp(63, trataDados.horaAtual());//data_hora_alteracao
            
            stmt.executeUpdate();
            this.gravarEstabelecimento(pj);
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir a Empresa no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    public void gravarEstabelecimento (PessoaJuridica pj) throws JsageImportException{
        if (pj == null) {
            String mensagem = "Não foi informada a Empresa para importar";
            throw new JsageImportException(mensagem);
        }
        jdbc.lerPropriedades("SAGE");
        Connection con = null;
        PreparedStatement stmt = null;
                
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_ESTABELECIMENTO);
            stmt.setInt(1, pj.getIdPessoa());//cd_empresa
            stmt.setInt(2, 1);//cd_estabelecimento
            stmt.setString(3, pj.getNomePessoa());//razao
            stmt.setString(4, pj.getNomeFantasia());//fantasia
            stmt.setString(5, pj.getLogradouro());//endereco
            //trataDados.converterSrintInt(pj.getNumeroEndereco())
            stmt.setInt(6, 1000);//numero
            stmt.setString(7, pj.getBairro());//bairro
            stmt.setString(8, trataDados.recuperarCidade(pj.getIdmunicipio()));
            stmt.setString(9, "PB");//uf
            stmt.setShort(10, (short) 83);//ddd_telefone
            stmt.setInt(11, 34210000); //telefone
            //trataDados.recuperarNaturezaJuridica(pj.getIdNaturezaJuridica())
            stmt.setString(12, "2602");//natureza_juridica
            stmt.setShort(13, (short)9);//categoria
            stmt.setString(14, pj.getCnpjFormatado());//cnpj_cpf
            stmt.setString(15, "J");//local_registro
            stmt.setString(16, "TITULAR PADRAO");//nome_titular
            stmt.setString(17, "ADMINISTRADOR");//denom_titular
            stmt.setString(18, "156.690.111-12");//CPF TITULAR
            stmt.setShort(19, (short) 1);//CD_RESPONSAVEL_ESTABELECIMENTO
            stmt.setString(20, "N");//SALARIO EDUCACAO
            stmt.setString(21, "PÁGINA");//denominacao_pagina_csc
            stmt.setString(22, "N");//contribuinte icms
            stmt.setString(23, "N");//contribuinte ipi
            stmt.setString(24, "N");//contribuinte iss
            stmt.setString(25, "M");//opcao ipi
            stmt.setInt(26, 1);//compensacao tributos retidos
            stmt.setDouble(27, 0);//perc vendas
            stmt.setString(28, "PÁGINA");//denominacao_pagina_cef
            stmt.setString(29, "N");//substituto tributario
            stmt.setString(30, "N");//utiliza ecf
            stmt.setShort(31,(short) 6);//tributacao
            stmt.setShort(32, (short) 7);//qualificacao
            stmt.setTimestamp(33, pj.getDataInicioAtividade());
            stmt.setString(34, "N");//antecipar_irpj_csll
            stmt.setString(35, "N");//calcular_excedente_antecipacao_irpj_csll
            stmt.setShort(36, (short) 1);//parcelamento_irpj_csll
            stmt.setString(37, "M");//tipo estabelecimento
            stmt.setString(38, "NA");//instituicao_financeira
            stmt.setString(39, "A");//status
            stmt.setString(40, pj.getNomeFantasia());//RAZAO COMPLETA
            stmt.setString(41, "N");//estatuto_microempresa
            stmt.setShort(42, (short) 0);//opcao_vencimento_darf
            stmt.setString(43, "N");//beneficio proged
            stmt.setString(44, "N");//difere_icms_rs
            stmt.setInt(45, 0);//OPCAO SUPER
            stmt.setDouble(46, 0);//vl_super_icms_fixo
            stmt.setDouble(47, 0);//vl_super_iss_fixo
            stmt.setString(48, "S");//protocolos_baixa_guias
            stmt.setDouble(49, 0);//VL MINIMO CSLL
            stmt.setDouble(50, 0);//VL MINIMO irf
            stmt.setInt(51, 205);//CODIGO CLASSIFICACAO
            stmt.setDouble(52, 0);//aliquota cef
            stmt.setInt(53, 1);//natureza juridica
            stmt.setInt(54, 0);//tipo entidade
            stmt.setInt(55, 1);//tipo plano
            stmt.setInt(56, 1);//coeficiente_ciap_opcao
            stmt.setTimestamp(57, trataDados.horaAtual());//hora alteracao
            
            stmt.executeUpdate();
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir a Empresa no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
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
        int cdMunicipioDefault =2510808;
        String funAposentado = "N";

        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_FUNCIONARIO);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, pf.getIdPessoa());//cd_funcionario
            stmt.setString(3, pf.getNomePessoa());//nome            
            stmt.setString(4, pf.getLogradouro());//endereco
            //trataDados.tratarNrEndereco(pf.getNumeroEndereco())
            stmt.setInt(5, trataDados.tratarNrEndereco(pf.getNumeroEndereco()));//nr_endereco
            //trataDados.recuperarComplemento(pf.getComplemento())
            stmt.setString(6, trataDados.recuperarComplemento(pf.getComplemento()));//compl_endereco
            //trataDados.recuperarBairro(pf.getBairro())
            stmt.setString(7, trataDados.recuperarBairro(pf.getBairro()));//bairro
            //trataDados.recuperarCidade(pf.getIdmunicipio())
            stmt.setString(8, trataDados.recuperarCidade(pf.getIdmunicipio()));//cidade
            stmt.setString(9, trataDados.recuperarEstado(pf.getIdmunicipio()));//estado
            //trataDados.recuperarCEP(pf.getCep())
            stmt.setInt(10, 58700001);//cep
            stmt.setString(11, trataDados.recuperarPai(pf.getIdPessoa()));//pai
            stmt.setString(12, trataDados.recuperarMae (pf.getIdPessoa()));//mae
            stmt.setString(13, pf.getIndSexo());//sexo
            stmt.setShort(14, trataDados.recuperarEstadoCivil(pf.getIdEStadoCivil()));//estado_civil
            stmt.setShort(15, (short) 10);//nacionalidade - todos como brasileiros
            stmt.setShort(16, anoChegadaDefault);//ano_chegada
            stmt.setShort(17, (short) 7);//grau_instrucao - todos com o ensino medio completo
            stmt.setTimestamp(18, pf.getDataNascimento());//dt_nascimento
            stmt.setShort(19, dddDefault);//ddd
            stmt.setShort(20, telefoneDefault);//telefone
            //trataDados.recuperaApelido(pf.getApelido())
            stmt.setString(21, null);//apelido
            stmt.setString(22, null);//chave_acesso
            stmt.setString(23, null);//senha_acesso
            stmt.setString(24, trataDados.recuperarRaca(pf.getIdRaca()));//raca
            //trataDados.recuperarTipoDeficiente(pf.getIdTipoDeficiencia())
            stmt.setString(25,"0" );//deficiente - todos não deficientes
            //trataDados.convertIntToString(pf.getIdMunicipioNaturalidade())
            stmt.setString(26, null);//cidade_nascimento
            stmt.setString(27, null);//estado_nascimento
            stmt.setShort(28, dddDefault);//ddd_celular
            stmt.setInt(29, celularDefault);//celular
            stmt.setString(30, pf.getNomePessoa());//nomecompleto
            stmt.setString(31, null);//email
            stmt.setTimestamp(32, pf.getDataChegada());//data_chegada
            stmt.setString(33, "R");//tipo logradouro
            stmt.setInt(34, cdMunicipioDefault);//cd_municipio
            stmt.setInt(35, pf.getIdMunicipioNaturalidade());//cd_municipio_nascimento
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
            stmt.setInt(4, 6);//cd_funcao - todos como aux administrativo tabelas incompativeis
            stmt.setTimestamp(5, trataDados.horaAtual());//dt_final
            stmt.setTimestamp(6, trataDados.horaAtual());//data_hora_alteracao           
            
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
            stmt.setTimestamp(9, df.getDataFim());//dt_final
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
            stmt.setTimestamp(3, trataDados.trataData(df.getDataIncio()));//dt_salario
            stmt.setString(4, trataDados.recuperaTipoSalario(df.getIdTipoSalario()));//tipo_salario
            stmt.setDouble(5, horasSemanaisDefault);//nr_horas_semanais
            stmt.setDouble(6, trataDados.trataSalario(df.getSalario()));//vl_salario
            stmt.setDouble(7, percAdiantamentoDefault);//perc_adiantamento
            stmt.setString(8, df.getMotivoMovimento());//motivo
            stmt.setDouble(9, percAdiantamentoDefault);//vl_adiantamento
            stmt.setTimestamp(10, df.getDataFim());//dt_final
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
            stmt.setString(4, df.getNomePessoa());// nome
                        
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
            stmt.setString(3, pf.getNumeroCtps());//nr_carteira
            stmt.setString(4, pf.getSerieCtps());//serie_carteira
            stmt.setString(5, carteiraDefault);//dv_serie_carteira
            stmt.setString(6, trataDados.recuperarUF(pf.getIdUfCtps()));//uf_carteira
            stmt.setString(7, trataDados.recuperarPIS(cdFuncionario));//pis
            stmt.setString(8, pf.getCpfFormatado());//cpf
            stmt.setString(9, pf.getNumeroDocumentoIdentidade());//nr_identidade
            stmt.setString(10, trataDados.tratarOrgaoRG(pf.getOrgaoExpedidorDocumentoIdentidade()));//orgao_identidade
            stmt.setString(11, null);//uf_identidade   
            //trataDados.converterSrintIntCom0(pf.getNumeroCnh())
            stmt.setInt(12, 0);//nr_habilitacao 
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
            stmt.setString(26, pf.getNumeroDocumentoMilitar());//certificado_militar
            stmt.setString(27, pf.getOrgaoExpedidorRegistroProfissional());//orgao_reg_prof
            stmt.setString(28, pf.getNumeroRegistroProfissional());//nr_reg_prof
            stmt.setTimestamp(29, pf.getDataExpedicaoRegistroProfissional());//dt_emissao_reg_prof
            stmt.setString(30, pf.getOrgaoEmissorCnh());//orgao_emissor_cnh
            stmt.setTimestamp(31, pf.getDataValidadeRegistroProfissional());//dt_vcto_reg_prof
            stmt.setTimestamp(32, pf.getDataEmissaoCertidaoCivil());//dt_emissao_certidao
            stmt.setString(33, pf.getTermoMatriculaCertidaoCivil());//matricula_certidao
            stmt.setString(34, pf.getLivroCertidaoCivil());//livro_certidao
            stmt.setString(35, pf.getFolhaCertidaoCivil());//folha_certidao
            stmt.setString(36, pf.getCartorioCertidaoCivil());//cartorio_certidao
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
            //trataDados.converterSrintInt(trataDados.recuperarAdmissaoCaged(df.getIdTipoAdmissaoCaged()))
            stmt.setInt(5, trataDados.trataTipoAdmissaoCaged(trataDados.recuperarAdmissaoCaged(df.getIdTipoAdmissaoCaged())) );//codigo_admissao_caged -> idtipoadmissaocaged string
            stmt.setInt(6, trataDados.converterSrintInt(df.getCodigoRegistro()));//nr_registro
            stmt.setInt(7, df.getNumeroDiasContratoExperiencia());//dias_experiencia
            stmt.setString(8, null);//temporario
            stmt.setTimestamp(9, null);//vcto_contrato_temporario
            //stmt.setInt(10, trataDados.converterSrintInt(trataDados.recuperarBanco(fun.getIdDadosBanco())));//cd_banco_temporario
            //stmt.setInt(11, trataDados.converterSrintInt(trataDados.recuperarAgencia(fun.getIdDadosAgencia())));//nr_agencia_temporario
            //stmt.setString(12, trataDados.recuperarDVAgencia(fun.getIdDadosAgencia()));//dv_agencia_temporario
            //stmt.setInt(13, trataDados.converterSrintInt(trataDados.recuperarConta(cdFuncionario)));//nr_conta_temporario
            stmt.setInt(10, 1);//cd_sindicato - não encontrado no ng
            //stmt.setString(14, trataDados.recuperarDVConta(fun.getIdDadosBanco()));//dv_conta_temporario
            stmt.setString(11, "N");//sindicalizado
            stmt.setInt(12, 2);//situacao_contr_sindical
            //stmt.setInt(18, cdEmpresa);//nr_cartao_ponto
            stmt.setInt(13, 1);//categoria
            stmt.setInt(14, 0);//ocorrencia
            //stmt.setInt(21, cdEmpresa);//cd_banco_fgts
            //trataDados.converterSrintInt(trataDados.recuperarBanco(fun.getIdDadosBanco()))
            stmt.setInt(15, 0);//cd_banco_deposito
            stmt.setInt(16, trataDados.converterSrintInt(trataDados.recuperarAgencia(fun.getIdDadosAgencia())));//nr_agencia_deposito
            stmt.setString(17, trataDados.recuperarDVAgencia(fun.getIdDadosAgencia()));//dv_agencia_deposito
            stmt.setInt(18, trataDados.converterSrintInt(trataDados.recuperarConta(cdFuncionario)));//nr_conta_deposito
            stmt.setString(19, trataDados.recuperarDVConta(fun.getIdDadosBanco()));//dv_conta_deposito
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
            stmt.setInt(6, trataDados.recuperarParentesco(dep.getIdRelacaoDependenciaPlanoSaude()));//tipo_parentesco
            stmt.setString(7, null);//descricao_parentesco
            stmt.setTimestamp(8, dep.getDataNascimento());//dt_nascimento - falta data
            stmt.setString(9, "N");//suspende_sf
            stmt.setString(10, null);//observacao
            stmt.setString(11, dep.getCpfFormatado());//cpf_dependente - o cadastro do dependente nao enocntrado cpf
            stmt.setInt(12, trataDados.recuperarEsocial(trataDados.recuperarParentesco(dep.getIdRelacaoDependenciaPlanoSaude())));//esocial_tipo_parentesco
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