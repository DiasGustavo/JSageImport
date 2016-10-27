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
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaSAGE;
import jsageImport.modelo.dominio.EmpresaTributacao;
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
            stmt.setShort(35,(short) trataDados.recuperarFormaTributacao(empTrib.getIdformatributacao()));//tributacao
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
            /*stmt.setString(49, "N");
            stmt.setString(50, "N");
            stmt.setString(51, "N");
            stmt.setString(52, "N");
            stmt.setString(53, "N");
            stmt.setString(54, "N");
            stmt.setString(55, "N");
            stmt.setString(56, "N");
            stmt.setString(57, "N");
            stmt.setInt(58, 1);
            stmt.setInt(59, 1);
            stmt.setInt(60, 1);
            stmt.setInt(61, 1);
            stmt.setInt(62,1);
            stmt.setInt(63, 1);
            stmt.setString(64, "N");
            stmt.setInt(65, 1);
            stmt.setInt(66, 1);
            stmt.setInt(67, 1);
            stmt.setInt(68, 1);
            stmt.setInt(69, 1);
            stmt.setInt(70, 1);
            stmt.setString(71, "N");
            stmt.setInt(72, 1);
            stmt.setInt(73, 1);
            stmt.setInt(74, 1);
            stmt.setInt(75, 1);
            stmt.setInt(76, 1);
            stmt.setInt(77, 1);*/
                      
            
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