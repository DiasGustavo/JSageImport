/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaTributacao;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaSAGE;

/**
 *
 * @author Jeff-Info
 */
public class PersistenciaEmpresaSAGE implements IPersistenciaEmpresaSAGE{
    
    PropertiesJdbc jdbc = new PropertiesJdbc();
    TratamentoDados trataDados = new TratamentoDados();
    
     /*Strings de url*/
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
   
    
    
    private static final String SQL_CADASTRO_EMPRESA = "INSERT INTO CRDEmpresa (cd_empresa, razao, cnpj_cpf, dt_sistema, dt_inicial, dt_final, integracao_contabil, centro_custo, apropriacao_tributos,"
                                                                        +"pagamento_tributos, contabilizacao_online, agrupar_lancamentos,opcao_agrupamento,excluir_icms_entradas, excluir_ipi,"  
                                                                        +"contabilizar_nota_nota, nivel1,mascara_plano_contas,num_niveis_plano_contas,num_digitos_plano_contas,comprimento_plano_contas,"
                                                                        +"seq_conta,opcao_caixa,libera_lote_aberto,lanca_lote_lib,opcao_conta_cliente,opcao_conta_fornecedor,op_calculo_horista,"
                                                                        +"possui_tomador,pagamento_mes,prolabore_mes,cargos_salarios,produtor_rural,  "
                                                                        +"naooptante_liminar, optante_liminar,nome_responsavel_rescisao, funcao_responsavel_rescisao,utiliza_conta_clifor,status,"
                                                                        +"integracao_csc,simples_crh,faturamento_simples_crh,microempresa_crh,epp_crh,importa_lote_diferenca,utiliza_controle_clifor,"
                                                                        +"opcao_seguro_desemprego,descontar_13_negativo,op_calculo_numero_dias,EntSemFinsLucrativos,recolhimento_contr_sindical_centralizada,"
                                                                        +"calc30_opcao_salario,calc30_opcao_salario_fevereiro,calc30_opcao_dias,opcao_historico_contabil,optante_liminar_aviso_previo,micro_emp_indiv_crh,"
                                                                        +"empresa_cidada,utiliza_controle_parcelas_auditoria,emitir_aviso_ferias,opcao_data_aviso_previo,forma_digitacao_caixa_banco_auditoria,grau_padrao_relatorio,data_hora_alteracao)"
                                                                        +"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_CADASTRO_ESTABELECIMENTO = "INSERT INTO CRDEstabelecimento   (cd_empresa,cd_estabelecimento,razao,fantasia,endereco,numero,bairro,cidade,cep,uf,ddd_telefone\n" +
"								  ,telefone,natureza_juridica,categoria,cnpj_cpf,cnae,local_registro,nome_titular,denom_titular,cpf_titular\n" +
"								  ,cd_responsavel_estabelecimento,cd_responsavel_caged,cd_responsavel_sefip,salario_educacao,denominacao_pagina_csc\n" +
"								  ,contribuinte_icms,contribuinte_ipi,contribuinte_iss,opcao_ipi,compensacao_tributos_retido,perc_cs_venda,denominacao_pagina_cef\n" +
"								  ,substituto_tributario,utiliza_ecf,tributacao,qualificacao,dt_inicio_atividade,antecipar_irpj_csll,calcular_excedente_antecipacao_irpj_csll\n" +
"								  ,parcelamento_irpj_csll,tipo_estabelecimento,instituicao_financeira,status,razao_completa,estatuto_microempresa,opcao_vencimento_darf\n" +
"								  ,beneficiario_prodepe,difere_icms_rs,opcao_super,vl_super_icms_fixo,vl_super_iss_fixo,protocolos_baixa_guias,cd_classificacao\n" +
"								  ,aliquota_fecp,natureza_juridica_ecf,tipo_entidade_ecf,tipo_plano_ecf,coeficiente_ciap_opcao, data_hora_alteracao)\n" +
"								  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_CADASTRO_EMPRESA_MATRIZ = " INSERT INTO EmpresaMatrizContabilizacao (cd_empresa, id_matriz, operacao_contabil)"
                                                              +"VALUES (?,?,?)";
    
    private static final String SQL_ESTABELECIMENTO_PARAMETRO = "INSERT INTO CRDEstabelecimentoParametro (cd_empresa,cd_estabelecimento,apura_grcsu_patronal,dia_vencto_GIA_RS,opcao_calculo_piscofins"+
"                                                               ,regime_piscofins,opcao_agrupamento_retencao_tributos,opcao_relatorio_agrupamento_itens,prestador_servico_telecom,regravar_emit_dest_entrada" +
"                                                               ,importar_nf_dupli_entrada,listar_reg_adv_entrada,regravar_emit_dest_saida,importar_nf_dupli_saida,listar_reg_adv_saida,op_importar_automatico" +
"                                                               ,aliq_pis_cum,aliq_cofins_cum,regime_caixa_competencia,averb_exportacao,info_cred_icms_ex_apur,comercio_varejista_combust,prod_acucar_alcool_carbur" +
"                                                               ,info_vl_adicionado,distr_ener_consum_outra_uf,vendas_cartao_credito_debito,contr_utiliz_doc_fiscais_papel,serv_trans_aereo_cargas_pass,aliq_dif_uni_med" +
"                                                               ,soma_icms_st,soma_ipi,zerar_credito_icms_importacao,opcao_importar_notas_dropdrive_nfe,opcao_buscar_notas_iob_gerencia,opcao_buscar_notas_armazenadas" +
"                                                               ,opcao_rateio_despesas,livro_diario_numero,livro_diario_registro,livro_diario_folha_inicial,livro_diario_folha_final,sociedade_conselho_fiscal_instalado" +
"                                                               ,sociedade_auditoria_independente,oficio_circular_dnrc,credito_aquisicao_bebidas,iob_armazernar_notas,monitorar_ncm,opcao_estimativa_anual" +
"                                                               ,opcao_fechamento_trimestral)"+
                                                                " VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
    
    
    private static final String SQL_CAPA_LOTE = "INSERT INTO CapaLote (nr_lote,enterprise_id,descricao,dt_lote,periodo,origem,tipo,situacao,repete_debito,repete_credito,repete_historico,repete_ccusto,repete_complemento" +
"                                                                      ,identificador,historico_especial,lote_caixa,repete_valor)"+
                                                                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_CRDSCPRODEC = " INSERT INTO CRDSCPRODEC (cd_empresa, cd_estabelecimento,solicita_confirmacao,status)"+
                                                                "VALUES (?,?,?,?)";
    
    private static final String SQL_TIPODRE = " INSERT INTO TipoDRE (cd_tipoDRE, enterprise_id, descricao, nr_ordem, natureza)"+
                                                                " SELECT DISTINCT dre.cd_tipoDRE, emp.cd_empresa, dre.descricao, dre.nr_ordem, dre.natureza "+
                                                                " FROM TipoDRE dre, CRDEmpresa emp "+
                                                                " where emp.cd_empresa = ? and dre.cd_tipoDRE = ?";
    
    private static final String SQL_TITULODRE = "INSERT INTO TituloDRE (cd_tituloDRE, enterprise_id, descricao, ordem)"+
                                                " SELECT DISTINCT dre.cd_tituloDRE, emp.cd_empresa, dre.descricao, dre.ordem"+
                                                " FROM TituloDRE dre, CRDEmpresa emp"+ 
                                                " WHERE emp.cd_empresa = ? and dre.cd_tituloDRE = ?";
    
    private static final String SQL_TOMADOR = "INSERT INTO Tomador (cd_empresa,cd_tomador,nome,obra_construcao_civil,cd_filial,status,sujeita_a_desoneracao)"+
                                                                 "VALUES (?,?,?,?,?,?,?)";
    
    private static final String SQL_CSCDFCEQUIVALENTECAIXA = "INSERT INTO CSCDFCEquivalenteCaixa (enterprise_id, opcao_plano, classificacao)"+
                                                                  "VALUES (?,?,?)";
    
    private static final String SQL_CSCDFCPLANO = "INSERT INTO CSCDFCPlano (enterprise_id, cd_plano_dfc, opcao_plano, descricao,opcao_metodo, opcao_niveis)"+
                                                    " SELECT DISTINCT emp.cd_empresa, plano.cd_plano_dfc, plano.opcao_plano, plano.descricao, plano.opcao_metodo, plano.opcao_niveis"+
                                                    " FROM CSCDFCPlano plano, CRDEmpresa emp"+
                                                    " where emp.cd_empresa = ? and plano.cd_plano_dfc = ?";
    
    private static final String SQL_CSCDRAPLANO = "INSERT INTO CSCDRAPlano (enterprise_id, cd_plano_DRA, descricao, opcao_plano)"+
                                                    " VALUES (?,?,?,?)";
    
    private static final String SQL_CRDPERMISSAOGRUPOESTABELECIMENTO = "INSERT INTO CRDPermissaoGrupoEstabelecimento (cd_empresa, cd_estabelecimento, cd_grupo_estabelecimento)"+
                                                                    "VALUES (?,?,?)";
    
    private static final String SQL_CSCDMPL_PLANO = "INSERT INTO CSCDMPLPlano (enterprise_id, cd_plano_dmpl, classificacao_pl, descricao, opcao_plano)\n" +
"                                                     SELECT DISTINCT emp.cd_empresa, plano.cd_plano_dmpl,plano.classificacao_pl, plano.descricao, plano.opcao_plano\n" +
"                                                     FROM CSCDMPLPlano plano, CRDEmpresa emp\n" +
"                                                     where emp.cd_empresa = ? and plano.cd_plano_dmpl = ? and (plano.classificacao_pl = '' or plano.classificacao_pl <> '')";
    
    private static final String SQL_CRD_EMPRESAPARAMETRO = "INSERT INTO CRDEmpresaParametro (cd_empresa,opcao_tomador_servico_cooperativa,agrupar_inss_tomador_cmo,op_calc_juros_comp_inss" +
"                                                                                           ,pagamento_tributos_crh,op_calculo_prolabore_folha_mensal,op_deducao_inss_retido_outras_empresas_irrf" +
"                                                                                           ,informar_compl_13_ano_subsequente,informar_cnpj_inscrestadual,crh_op_ferias_coletivas_admitidos_ano,considerar_nova_regra_aviso_pedido_dispensa" +
"                                                                                           ,dia_pagamento_adiantamento,op_calcular_adiantamento_diretor,op_calcular_prolabore_diretor,op_calcular_media_evento_variavel_13" +
"                                                                                           ,dia_pagamento_13,crh_op_enviar_relatorio_email,op_nao_listar_recibo_prolabore_holerith,op_emitir_uma_via_modo_grafico_holerith" +
"                                                                                           ,crh_op_imprimir_folha_rosto,op_nao_listar_recibo_prolabore_folha_mensal,op_nao_listar_recibo_autonomo_folha_mensal,crh_op_nao_imprimir_dados_destaque" +
"                                                                                           ,op_listar_evento_neutro,cpf_obrigatorio_cad_funcionarios,lancamentos_rtt,considerar_ano_completo_calculo_dias_conceder,considerar_projecao_aviso_calculo_dias_conceder" +
"                                                                                           ,mp540_enquadramento,op_nao_listar_foto_funcionario_holerith,crh_op_totalizar_valores_inss,op_emitir_termo_prorrogacao_experiencia,op_preencher_data_termo_prorrogacao_experiencia" +
"                                                                                           ,op_nao_imprimir_obs_func_ficha_registro,op_nao_imprimir_alteracao_funcao_ficha_registro,op_nao_imprimir_foto_ficha_registro,op_nao_imprimir_dependentes_ficha_registro" +
"                                                                                           ,op_contabilizar_item,op_confere_cpf_cnpj_banco_brasil_240,mp540_origem_receitas_servicos_prestados_sujeitos_iss,mp540_origem_receitas_merc_servicos_prestados_sujeitos_icms" +
"                                                                                           ,mp540_origem_receitas_fabricacao_produtos_incentivados,mp540_calcula_inss_provisao,op_gerar_rubricas_externas,esocial_valida_campos_nao_obrigatorios,esocial_empresa_obrigada,iob_diagnostico_processado" +
"                                                                                           ,crh_op_periodo_ferias_dobro,crh_op_pagamento_ferias_dobro,op_fornecimento_vt_folha_mensal,op_fornecimento_vr_folha_mensal,mp540_origem_receitas_construcao_civil,esocial_regime_proprio" +
"                                                                                           ,lei13161_possui_mais_de_uma_aliquota)"+
                                                                                            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  
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
            stmt = con.prepareStatement(SQL_CADASTRO_EMPRESA);
            stmt.setInt(1, (short) pj.getIdPessoa());
            stmt.setString(2, pj.getNomePessoa());
            stmt.setString(3, pj.getCnpjFormatado());
            stmt.setTimestamp(4,trataDados.horaAtual()) ;//dt_sistema
            stmt.setTimestamp(5, pj.getDataInicioAtividade());//dt_inicial
            stmt.setTimestamp(6, pj.getDataTerminoSociedade());//dt_fim
            stmt.setString(7, "N");//integracao_contabil
            stmt.setString(8, "N");//centro_custo
            stmt.setString(9, "N");//apropriacao_tributo
            stmt.setString(10,"N");//pagamento_tributo
            stmt.setString(11,"N");//contabilizacao_online
            stmt.setString(12, "N");//agrupar_lancamento
            stmt.setShort(13, (short)1);//opcao_agrupamento
            stmt.setString(14, "N");//excluir_icms_entrada
            stmt.setString(15, "N");//excluir_ipi
            stmt.setString(16, "N");//contabilizar_nota_nota
            stmt.setString(17, "GERAL");//nivel1
            stmt.setString(18,"X");//mascara_plano_contas
            stmt.setInt(19, 1);//num_niveis_plano_contas
            stmt.setString(20, "1");//num_digito_plano_contas
            stmt.setString(21,"01");//comprimento_plano_contas
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
            stmt.setString(36, pj.getNomePessoa()); //nome_responsavel_rescisao
            stmt.setString (37, "Diretor");//funcao_responsavel_rescisao
            stmt.setString(38, "N");//utiliza_conta_clifor
            stmt.setString(39, "A");//status
            stmt.setString(40, "N");//integracao_csc
            stmt.setString(41, "N");//simpesl_crh
            stmt.setString(42, "N");//faturamento_simples_crh
            stmt.setString(43, "N");//microempresa_crh
            stmt.setString(44, "N");//epp_crh
            stmt.setString(45, "N");//importa_lote_diferenca
            stmt.setString(46, "N");//utiliza_controle_clifor
            stmt.setInt(47, 0);//opcao_seguro_desemprego
            stmt.setString(48,"N");//descontar_13_negativo
            stmt.setShort(49,(short) 1);//op_calculo_numero_dias
            stmt.setString(50, "N");//entSemFinsLucrativo
            stmt.setString(51, "N");//recolhimento_contr_sidical_centralizada
            stmt.setInt(52, 1);//calc30_opcao_salario
            stmt.setString(53, "N");//calc30_opca_salario_fevereiro
            stmt.setInt(54, 1);//calc30_opca_dias
            stmt.setString(55, "F");//opcao_shitorico_contabil
            stmt.setString(56, "N");//optante_liminar_aviso_previo
            stmt.setString(57, "N");//micro_emp_indiv_crh
            stmt.setString(58, "N");//empresa_cidade
            stmt.setInt(59, 0);//utiliza_controle_parcelas_auditoria
            stmt.setString(60, "N");//emitir_aviso_ferias
            stmt.setString(61, "l");//opcao_data_aviso_previo
            stmt.setInt(62,0);//forma_digitacao_caixa_banco_auditoria
            stmt.setInt(63, 2);//grau_padrao_relatorio
            stmt.setTimestamp(64, trataDados.horaAtual());//data_hora_alteracao
            
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
    public void gravarEstabelecimento (PessoaJuridica pj, EmpresaTributacao empTrib,EmpresaTributacao empCnae, EmpresaFolha empFolha) throws JsageImportException{
        if (pj == null) {
            String mensagem = "Não foi informada a Empresa para importar";
            throw new JsageImportException(mensagem);
        }
        jdbc.lerPropriedades("SAGE");
        Connection con = null;
        PreparedStatement stmt = null;
                
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CADASTRO_ESTABELECIMENTO);
            stmt.setInt(1, pj.getIdPessoa());//cd_empresa
            stmt.setInt(2, 1);//cd_estabelecimento
            stmt.setString(3, pj.getNomePessoa());//razao
            stmt.setString(4, pj.getNomeFantasia());//fantasia
            stmt.setString(5, pj.getLogradouro());//endereco
            //trataDados.converterSrintInt(pj.getNumeroEndereco())
            stmt.setInt(6, trataDados.tratarNrEndereco(pj.getNumeroEndereco()));//numero
          
            stmt.setString(7, pj.getBairro());//bairro
            stmt.setString(8, trataDados.recuperarCidade(pj.getIdmunicipio()));//cidade
            stmt.setInt(9, trataDados.converterSrintIntCom0(pj.getCep()));//cep
            stmt.setString(10, "PE");//UF
            
            stmt.setInt(11, (short) 81); //DDD
            stmt.setInt(12, 000000000); //telefone
            //trataDados.recuperarNaturezaJuridica(pj.getIdNaturezaJuridica())
            stmt.setString(13, trataDados.recuperarNaturezaJuridica(pj.getIdNaturezaJuridica()));//natureza_juridica
            stmt.setShort(14, (short)9);//categoria
            stmt.setString(15, pj.getCnpjFormatado());//cnpj_cpf
            System.out.println(trataDados.converterSrintInt(trataDados.recuperarCnaeEmpresa(empCnae.getIdCNAE())));
            stmt.setInt(16, trataDados.converterSrintInt(trataDados.recuperarCnaeEmpresa(empCnae.getIdCNAE())));
            stmt.setString(17, "J");//local_registro
            
            stmt.setString(18, pj.getNomePessoa());//nome_titular
            stmt.setString(19, "ADMINISTRADOR");//denom_titular
            stmt.setString(20, trataDados.recupararCPFTitular(pj.getIdPessoa()));//CPF TITULAR
            stmt.setShort(21, (short) 1);//CD_RESPONSAVEL_ESTABELECIMENTO
            stmt.setShort(22, (short) 1);//CD_RESPONSAVEL_CAGED
            stmt.setShort(23, (short) 1);//CD_RESPONSAVEL_SEFIP
            stmt.setString(24, "N");//SALARIO EDUCACAO
            stmt.setString(25, "PÁGINA");//denominacao_pagina_csc
            stmt.setString(26, "N");//contribuinte icms
            stmt.setString(27, "N");//contribuinte ipi
            stmt.setString(28, "N");//contribuinte iss
            stmt.setString(29, "M");//opcao ipi
            stmt.setInt(30, 1);//compensacao tributos retidos
            stmt.setDouble(31, 0 );//perc_cs_venda
            stmt.setString(32, "PÁGINA");//denominacao_pagina_cef
            stmt.setString(33, "N");//substituto tributario
            stmt.setString(34, "N");//utiliza ecf
            //System.out.println(empTrib.getIdformatributacao() + "passou aki nesse caraii");
            stmt.setShort(35,(short)6 /*trataDados.recuperarFormaTributacao(empTrib.getIdformatributacao()*/ );//tributacao
            stmt.setShort(36, (short) 7);//qualficacao
            stmt.setTimestamp(37, pj.getDataInicioAtividade());//dt_inicio_atividade
            stmt.setString(38, "N");//antecipar_irpj_csll
            stmt.setString(39, "N");//calcular_excedente_antecipacao_irpj_csll
            stmt.setShort(40, (short) 1);//parcelamento_irpj_csll
            stmt.setString(41, "M");//tipo estabelecimento
            stmt.setString(42, "NA");//instituicao_financeira
            stmt.setString(43, "A");//status
            stmt.setString(44, pj.getNomeFantasia());//RAZAO COMPLETA
            stmt.setString(45, "N");//estatuto_microempresa
            stmt.setShort(46, (short)0);//opcao_vencimento_darf
            stmt.setString(47, "N");//beneficio prodepe
            stmt.setString(48, "N");//difere_icms_rs
            stmt.setInt(49, 0);//OPCAO SUPER
            stmt.setDouble(50, 0);//vl_super_icms_fixo
            stmt.setDouble(51, 0);//vl_super_iss_fixo
            stmt.setString(52, "S");//protocolos_baixa_guias
            stmt.setInt(53, 203 );//CODIGO CLASSIFICACAO
            stmt.setDouble(54, 0);//aliquota_fecp
            stmt.setInt(55, 1);//natureza juridica ecf
            stmt.setInt(56, 0);//tipo entidade ecf
            stmt.setInt(57, 1);//tipo plano ecf
            stmt.setInt(58, 1);//coeficiente_ciap_opcao
            stmt.setTimestamp(59, trataDados.horaAtual());//data_hora_alteracao
            
            stmt.executeUpdate();
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o estabelecimento no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    @Override
    public void gravarEmpresaMatrizContabilizacao (int cd_empresa)throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
       
        int [] listaOperacaoContabil = {1,2,3,4,5,6,7,21,22,23,24,41,42,43,44,101,102,103,104,121,122,123,124,201,202,203,204,205,206,207,208,209,210,211,212,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,401,402,403,404,421,422,316,105,230,330};
        
          
        
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CADASTRO_EMPRESA_MATRIZ);
            
            for (int i =  0; i < 60; i++) {
            stmt.setInt(1, cd_empresa);
            stmt.setInt(2, i + 1);
            stmt.setInt(3, listaOperacaoContabil[i]);
            
            stmt.executeUpdate();
        }
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir a empresa matriz no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
        
         
    }
    
    public void gravarEstabelecimentoParametro(PessoaJuridica pj ) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_ESTABELECIMENTO_PARAMETRO);
            
            stmt.setInt(1, (short) pj.getIdPessoa());
            stmt.setInt(2, 1);
            stmt.setString(3, "N");
            stmt.setInt(4,21 ) ;
            stmt.setInt(5, 1);
            stmt.setInt(6, 3);
            stmt.setInt(7, 1);
            stmt.setString(8, "N");
            stmt.setInt(9, 0);
            stmt.setString(10,"N");
            stmt.setString(11,"N");
            stmt.setString(12, "N");
            stmt.setString(13, "N");
            stmt.setString(14, "N");
            stmt.setString(15, "N");
            stmt.setString(16, "N");
            stmt.setDouble(17, 0.65);
            stmt.setDouble(18,3);
            stmt.setInt(19, 1);
            stmt.setString(20, "N");
            stmt.setString(21,"N");
            stmt.setString(22, "N");
            stmt.setString(23, "N");
            stmt.setString(24, "N");
            stmt.setString(25, "N");
            stmt.setString(26, "N");
            stmt.setString(27, "N");
            stmt.setString(28, "N");
            stmt.setString(29, "N");;
            stmt.setString(30, "N");
            stmt.setString(31, "N");
            stmt.setString(32, "N");
            stmt.setString(33, "N");
            stmt.setString(34, "N");
            stmt.setString(35, "N");
            stmt.setInt(36, 1); 
            stmt.setInt(37, 0);
            stmt.setInt(38, 0);
            stmt.setInt(39, 1);
            stmt.setInt(40, 1);
            stmt.setString(41, "N");
            stmt.setString(42, "N");
            stmt.setString(43, "N");
            stmt.setString(44, "N");
            stmt.setInt(45, 1);
            stmt.setString(46, "N");
            stmt.setInt(47, 1);
            stmt.setInt(48,1);
            
                      
            
            stmt.executeUpdate();
        
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o estabelecimento paramentro no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    public void gravarTomador (int cd_empresa) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_TOMADOR);
            
            stmt.setInt(1, cd_empresa);
            stmt.setInt(2, 0);
            stmt.setString(3, "RETORNO A MATRIZ");
            stmt.setString(4, "N");
            stmt.setInt(5, 1);
            stmt.setString(6, "A");
            stmt.setString(7, "N");
            
            
            stmt.executeUpdate();
        
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Tomador no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
        
    }
    
    public void gravarCSCDFCEquivalenteCaixa (int cd_empresa)throws JsageImportException{
        
         Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CSCDFCEQUIVALENTECAIXA);
            
            stmt.setInt(1, cd_empresa);
            stmt.setInt(2, 2);
            stmt.setString(3, "1.01.01");
            
            
            
            stmt.executeUpdate();
        
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o CSCDFCEquivalenteCaixa no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
    }
    
    public void gravarCRDPermissaoGrupoEstabelecimento (int cd_empresa) throws JsageImportException{
         Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CRDPERMISSAOGRUPOESTABELECIMENTO);
               
          
            stmt.setInt(1, cd_empresa);
            stmt.setInt(2, 1);
            stmt.setInt(3,1);
           
            
            stmt.executeUpdate();
        
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o CRDPermissaoGrupoEstabelecimento no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
    }
    
    public void gravarCSCDRAPlano(int cd_empresa)throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CSCDRAPLANO);
               
          
            stmt.setInt(1, cd_empresa);
            stmt.setInt(2, 501);
            stmt.setString(3, "Padrão, plano da empresa");
            stmt.setInt(4, 1);
            
            stmt.executeUpdate();
        
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o CSCDRAPlano no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
    }
    
    public void gravarCSCDFCEPlano (int cd_empresa)throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CSCDFCPLANO);
            
            int [] tipoPlanoDFC = {501,505,551,555,601,605,651,655};
            
            for (int i = 0; i < 8; i++) {
                stmt.setInt(1, cd_empresa);
                stmt.setInt(2, tipoPlanoDFC[i]);
                stmt.executeUpdate();
            }
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o CSCDFCPlano no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
    }
    
    public void gravarCRDSCPRODEC (int cd_empresa) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CRDSCPRODEC);
            stmt.setInt(1, cd_empresa);
            stmt.setInt(2, 1);
            stmt.setString(3, "N");
            stmt.setString(4, "A");
            
            
            stmt.executeUpdate();
        
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o CDRSCPRODEC no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
    }
    
    public void gravarTituloDRE (int cd_empresa)throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        
          
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_TITULODRE);
            
            for (int i =  1; i < 31; i++) {
                stmt.setInt(1, cd_empresa);
                stmt.setInt(2, i);            
                
                stmt.executeUpdate();
            
            }
       
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o tituloDRE no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
        
    }
    
    public void gravarTipoDRE (int cd_empresa) throws JsageImportException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        String [] listaCdTipoDRE = {"CM", "CS","CT", "DO", "DO1", "DO2","DO4", "DO5", "DO6", "DO7","DO8", "BR", "PI","RB","RN1","RN2"};
       
        
        
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_TIPODRE);
            
            for (int i =  0; i < 16; i++) {
            stmt.setInt(1, cd_empresa);
            stmt.setString(2, listaCdTipoDRE[i]);
           
            
            stmt.executeUpdate();
        }
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o tipoDRE no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
        
    }
    
    public void gravarCapaLote (int cd_empresa) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CAPA_LOTE);
            stmt.setInt(1, 0);
            stmt.setInt(2, cd_empresa);
            stmt.setString(3, "ONLINE");
            stmt.setTimestamp(4, trataDados.horaAtual());
            stmt.setString(5, "M");
            stmt.setString(6, "CTB");
            stmt.setString(7, "N");
            stmt.setString(8, "L");
            stmt.setString(9, "S");
            stmt.setString(10, "S");
            stmt.setString(11, "S");
            stmt.setString(12, "S");
            stmt.setString(13, "S");
            stmt.setString(14, null);
            stmt.setString(15, "S");
            stmt.setString(16, "N");
            stmt.setString(17, "S");
            
            
            stmt.executeUpdate();
        
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o capaLote no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
         
     }
  
    public void gravarCSCDMPLPLANO (int cd_empresa) throws JsageImportException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CSCDMPL_PLANO);
            
            int [] tipoPlano = {501,551};
            
            for (int i = 0; i < 2; i++) {
                stmt.setInt(1, cd_empresa);
                stmt.setInt(2, tipoPlano[i]);
                stmt.executeUpdate();
            }
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o CSCDMPLPlano no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
    }
    
    public void gravarEmpresaParametro (PessoaJuridica pj) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_CRD_EMPRESAPARAMETRO);
            
            stmt.setInt(1, pj.getIdPessoa());
            stmt.setString(2,"N");
            stmt.setString(3, "S");
            stmt.setString(4,"S");
            stmt.setString(5,"S");
            stmt.setString(6,"N");
            stmt.setString(7,"N");
            stmt.setString(8, "N");
            stmt.setInt(9, 1);
            stmt.setInt(10,1);
            stmt.setString(11,"N");
            stmt.setInt(12, 20);
            stmt.setString(13, "S");
            stmt.setString(14, "S");
            stmt.setString(15, "N");
            stmt.setInt(16, 20);
            stmt.setString(17,"N");
            stmt.setString(18,"N");
            stmt.setString(19,"N");
            stmt.setString(20, "N");
            stmt.setString(21,"N");
            stmt.setString(22, "N");
            stmt.setString(23, "N");
            stmt.setString(24, "N");
            stmt.setString(25, "N");
            stmt.setString(26, "N");
            stmt.setString(27, "N");
            stmt.setString(28, "N");
            stmt.setInt(29, 0);
            stmt.setString(30, "N");
            stmt.setString(31, "N");
            stmt.setString(32, "N");
            stmt.setString(33, "N");
            stmt.setString(34, "N");
            stmt.setString(35, "N");
            stmt.setString(36,"N");
            stmt.setString(37,"N");
            stmt.setString(38,"N");
            stmt.setString(39,"N");
            stmt.setString(40,"N");
            stmt.setString(41, "N");
            stmt.setString(42, "N");
            stmt.setString(43, "N");
            stmt.setString(44, "N");
            stmt.setString(45,"N");
            stmt.setString(46, "N");
            stmt.setString(47,"N");
            stmt.setInt(48,2);
            stmt.setInt(49,2);
            stmt.setString(50,"N");
            stmt.setString(51,"N");
            stmt.setString(52,"N");
            stmt.setString(53,"N");
            stmt.setString(54,"N");
                
            stmt.executeUpdate();
            
        }catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Empresa Parâmetro no SAGE.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }    
        
    }
    
    
}
