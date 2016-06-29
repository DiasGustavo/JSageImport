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
import java.util.Calendar;
import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.DependenteSAGE;
import jsageImport.modelo.dominio.EmpresaSAGE;
import jsageImport.modelo.dominio.FuncionarioSAGE;
import jsageImport.modelo.dominio.PessoaFisica;
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
                                                                               ",nivel2,nivel3,cod_especifico_crh,cod_especifico_cef,cod_especifico_csc,cod_especifico_ccp" +
                                                                               ",valor_extra1,valor_extra2,valor_extra3,valor_extra4,valor_extra5,cd_receita_icms,mascara_plano_contas" +
                                                                               ",num_niveis_plano_contas,num_digitos_plano_contas,comprimento_plano_contas,seq_conta" +
                                                                               ",conta_patrimonio,conta_caixa,opcao_caixa,libera_lote_aberto,lanca_lote_lib,darf_cofins_ret_quinzenal" +
                                                                               ",digito_darf_cofins_ret_quinzenal,darf_pis_ret_quinzenal,digito_darf_pis_ret_quinzenal" +
                                                                               ",darf_csll_ret_quinzenal,digito_darf_csll_ret_quinzenal,darf_pis_cofins_csll_ret_quinzenal" +
                                                                               ",digito_darf_pis_cofins_csll_ret_quinzenal,opcao_conta_cliente,conta_cliente,conta_cliente_unico" +
                                                                               ",opcao_conta_fornecedor,conta_fornecedor,conta_fornecedor_unico,op_calculo_horista,vl_arredondamento" +
                                                                               ",msg_aniversario,msg_admissao,convenio_caged,vl_maximo_cheque,vl_limite_cheque1,vl_limite_cheque2" +
                                                                               ",vl_limite_cheque3,vl_limite_cheque4,vl_limite_cheque5,qt_cheque1,qt_cheque2,qt_cheque3" +
                                                                               ",qt_cheque4,qt_cheque5,possui_tomador,pagamento_mes,prolabore_mes,cargos_salarios,produtor_rural" +
                                                                               ",naooptante_liminar,optante_liminar,nr_memorial_mtb,nome_responsavel_rescisao,cpf_responsavel_rescisao" +
                                                                               ",rg_responsavel_rescisao,funcao_responsavel_rescisao,nome_responsavel_ppp,nit_responsavel_ppp" +
                                                                               ",rodape_crh,rodape_cef,rodape_csc,rodape_ccp,utiliza_conta_clifor,perc_contribuicao_social" +
                                                                               ",status,row_id,integracao_csc,simples_crh,faturamento_simples_crh,microempresa_crh,diretorio_dirf" +
                                                                               ",epp_crh,op_rateio_ferias,diretorio_urbs,cd_pagamento_gps_tomador,cd_recolhimento_tomador" +
                                                                               ",cd_recolhimento,diretorio_caged,diretorio_integracao,codigo_pagamento_gps,cd_pagamento_gps" +
                                                                               ",diretorio_sefip,participante_pat,diretorio_raiz,cd_pagamento_gps_rateio,cd_recolhimento_rateio" +
                                                                               ",importa_lote_diferenca,utiliza_controle_clifor,cd_conta_juros_pagos,cd_conta_juros_recebidos" +
                                                                               ",cd_conta_descontos_obtidos,cd_conta_descontos_concedidos,opcao_seguro_desemprego,descontar_13_negativo" +
                                                                               ",op_calculo_numero_dias,EntSemFinsLucrativos,recolhimento_contr_sindical_centralizada,cd_estabelecimento_centralizador_contr_sindical" +
                                                                               ",PathAndFileLogo,calc30_opcao_salario,calc30_opcao_salario_fevereiro,calc30_opcao_dias,cd_conta_despesas_bancarias_pgto" +
                                                                               ",cd_conta_despesas_bancarias_recebimento,diretorio_grrf,opcao_historico_contabil,opcao_filtro_natureza_op" +
                                                                               ",opcao_filtro_natureza_op_st,opcao_data_lancamento_contabil,imprimir_dados_destaque,recibo_ferias_rateio" +
                                                                               ",recibo_ferias_adiantamento,recibo_ferias_abono,diretorio_sped,esp_estabelecimento,optante_liminar_aviso_previo" +
                                                                               ",micro_emp_indiv_crh,empresa_cidada,email_rh,diretorio_dipj,utiliza_controle_parcelas_auditoria,historico_juros_pagos" +
                                                                               ",historico_juros_recebidos,historico_descontos_obtidos,historico_descontos_concedidos,historico_desp_bancaria_pagas" +
                                                                               ",historico_desp_bancaria_recebidas,historico_pagamentos,historico_recebimentos,logo,diretorio_tce_rs" +
                                                                               ",cd_remessa_tce_rs,emitir_aviso_ferias,diretorio_seguro_desemprego,cd_recolhimento_complemento_salarial" +
                                                                               ",cd_pagamento_gps_complemento_salarial,opcao_data_aviso_previo,forma_digitacao_caixa_banco_auditoria" +
                                                                               ",historico_retencoes,grau_padrao_relatorio,EFD_atividade_preponderante,EFD_natureza_pessoa_juridica" +
                                                                               ",id,id_empresa_escritorio,data_hora_alteracao) " +
                                                                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                                                                            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                                                                            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                                                                            "?,?,?,?,?,?,?,?)";
    
    private static final String SQL_PESQUISARTODOS = "SELECT * FROM CRDEmpresa";
    private static final String SQL_PESQUISAREMPRESACNPJ = "SELECT * FROM CRDEmpresa WHERE cnpj_cpf = ?";
    
    /* Strings SQL para funcionários*/
    private static final String SQL_INCLUIR_FUNCIONARIO ="INSERT INTO Funcionario (cd_empresa,cd_funcionario,nome,endereco,nr_endereco,compl_endereco,bairro,cidade,estado,cep,pai,mae,sexo" +
                                                                                   ",estado_civil,nacionalidade,ano_chegada,grau_instrucao,dt_nascimento,ddd_fone,telefone,apelido,chave_acesso" +
                                                                                   ",senha_acesso,raca,deficiente,cidade_nascimento,estado_nascimento,ddd_celular,celular,nomecompleto,email" +
                                                                                   ",data_chegada,tipo_logradouro,cd_municipio,cd_municipio_nascimento,funcionario_aposentado,data_hora_alteracao" +
                                                                                   ",id,id_endereco)" + 
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INCLUIR_DOCUMENTOS ="INSERT INTO FunDocumento  (cd_empresa,cd_funcionario,nr_carteira,serie_carteira,dv_serie_carteira,uf_carteira,pis,cpf,nr_identidade,orgao_identidade,uf_identidade" +
                                                                                    ",nr_habilitacao,categoria_habilitacao,vcto_habilitacao,foto,nr_titulo,zona_titulo,secao_titulo,dt_emissao_carteira,dt_emissao_identidade" +
                                                                                    ",dt_emissao_habilitacao,dt_emissao_titulo,dt_emissao_pis,novo_nr_titulo,novo_nr_habilitacao,certificado_militar,orgao_reg_prof,nr_reg_prof" +
                                                                                    ",dt_emissao_reg_prof,orgao_emissor_cnh,dt_vcto_reg_prof,dt_emissao_certidao,matricula_certidao,livro_certidao,folha_certidao,cartorio_certidao" +
                                                                                    ",uf_certidao,cd_municipio_certidao,dt_primeira_habilitacao,uf_habilitacao,data_hora_alteracao,id)" +
                                                                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_RECUP_CIDADE = "SELECT * FROM dom_municipio WHERE idmunicipio = ?;";
    private static final String SQL_PESQUISAREMPRESA_FUNCIONARIO = "SELECT * FROM Funcionario WHERE cd_funcionario = ?";
    private static final String SQL_INCLUIR_DEP = "INSERT INTO FunDependente (cd_empresa,cd_funcionario,cd_dependente,dt_inclusao,nome,tipo_parentesco,descricao_parentesco,dt_nascimento,suspende_sf,observacao,cpf_dependente"+
                                                                              ",esocial_tipo_parentesco,esocial_pensionista,esocial_evento_pensao,esocial_percentual_pensao,data_hora_alteracao,id)" +
                                                                              " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_PESQUISAR_DEP_ID = "SELECT * FROM FunDependente WHERE cd_dependente = ?";
    
    
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
        short num = 0;
        byte [] imagem = null;
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_EMPRESA);
            stmt.setInt(1, pj.getIdPessoa());
            stmt.setString(2, pj.getNomePessoa());
            stmt.setString(3, pj.getCnpjFormatado());
            stmt.setTimestamp(4, null);//dt_sistema
            stmt.setTimestamp(5, null);//dt_inicial
            stmt.setTimestamp(6, null);//dt_fim
            stmt.setString(7, null);//integracao_contabil
            stmt.setString(8, null);//centro_custo
            stmt.setString(9, null);//apropriacao_tributo
            stmt.setString(10,null);//pagamento_tributo
            stmt.setString(11,null);//contabilizacao_online
            stmt.setString(12, null);//agrupar_lancamento
            stmt.setShort(13,num);//opcao_agrupamento
            stmt.setString(14, null);//excluir_icms_entrada
            stmt.setString(15, null);//excluir_ipi
            stmt.setString(16, null);//contabilizar_nota_nota
            stmt.setString(17, null);//nivel1
            stmt.setString(18, null);//nivel2
            stmt.setString(19, null);//nivel3
            stmt.setShort(20, num);//codigo_especifico_crh
            stmt.setShort(21, num);//codigo_especifico_cef
            stmt.setShort(22, num);//codigo_especifico_csc
            stmt.setShort(23, num);//codigo_especifico_ccp
            stmt.setString(24, null);//valor_extra1
            stmt.setString(25, null);//valor_extra2
            stmt.setString(26, null);//valor_extra3
            stmt.setString(27,null);//valor_extra4
            stmt.setString(28,null);//valor_extra5
            stmt.setInt(29, 0);//cd_receita_icms
            stmt.setString(30,null);//mascara_plano_contas
            stmt.setInt(31, 0);//num_niveis_plano_contas
            stmt.setString(32, null);//num_digito_plano_contas
            stmt.setString(33,null);//comprimento_plano_contas
            stmt.setInt(34, 0);//seq_conta
            stmt.setString(35, null);//conta_patrimonio
            stmt.setString(36, null);//conta_caixa
            stmt.setInt(37, 0);//opcao_caixa
            stmt.setString(38, null);//libera_lote_aberto
            stmt.setString(39, null);//lanca_lote_lib
            stmt.setShort(40, num);//darf_cofins_ret_quinzenal
            stmt.setShort(41, num);//digito_darf_cofins_ret_quinzenal
            stmt.setShort(42, num);//darf_pis_ret_quinzenal
            stmt.setShort(43, num);//digito_darf_pis_ret_quinzenal
            stmt.setShort(44, num);//darf_csll_ret_quinzenal
            stmt.setShort(45, num);//digito_darf_csll_ret_quinzenal
            stmt.setShort(46, num);//darf_pis_cofins_csll_ret_quinzenal
            stmt.setShort(47, num);//digito_darf_pis_cofins_csll_ret_quinzenal
            stmt.setString(48, null);//opcao_conta_cliente
            stmt.setString(49, null);//conta_cliente
            stmt.setInt(50, 0);//conta_cliente_unico
            stmt.setString(51, null);//opcao_conta_fornecedor
            stmt.setString(52, null);//conta_fornecedor
            stmt.setInt(53, 0);//conta_fornecedor_unico
            stmt.setInt(54, 0);//op_calculo_horista
            stmt.setDouble(55, 0);//vl_arredontamento
            stmt.setString(56, null);//msg_aniversario
            stmt.setString(57, null);//msg_admissao
            stmt.setInt(58, 0);//convenio_caged
            stmt.setDouble(59, 0);//vl_maximo_cheque
            stmt.setDouble(60, 0);//vl_limite_cheque1
            stmt.setDouble(61, 0);//vl_limite_cheque2
            stmt.setDouble(62, 0);//vl_limite_cheque3
            stmt.setDouble(63, 0);//vl_limite_cheque4
            stmt.setDouble(64,0);//vl_limite_cheque5
            stmt.setInt(65, 0);//qt_cheque1
            stmt.setInt(66, 0);//qt_cheque2
            stmt.setInt(67, 0);//qt_cheque3
            stmt.setInt(68, 0);//qt_cheque4
            stmt.setInt(69, 0);//qt_cheque5
            stmt.setString(70, null);//possui_tomador
            stmt.setString(71, null);//pgamento_mes
            stmt.setString(72, null);//prolabore_mes
            stmt.setString(73, null);//cargos_salarios
            stmt.setString(74, null);//produtor_rural
            stmt.setString(75, null);//naooptante_liminar
            stmt.setString(76, null);//optante_liminar
            stmt.setString(77, null);//nr_meorial_mtb
            stmt.setString(78, null);//nome_responsavel_rescisao
            stmt.setString(79, null);//cpf_responsavel_rescisao
            stmt.setString(80, null);//rg_responsavel_rescisao
            stmt.setString(81, null);//funcao_responsavel_rescisao
            stmt.setString(82, null);//nome_responsavel_ppp
            stmt.setString(83, null);//nit_Responsavel_ppp
            stmt.setString(84, null);//rodape_crh
            stmt.setString(85, null);//rodape_cef
            stmt.setString(86, null);//rodape_csc
            stmt.setString(87, null);//rodape_ccp
            stmt.setString(88, null);//utiliza_conta_clifor
            stmt.setDouble(89,0);//perc_contribuicao_social
            stmt.setString(90, null);//status
            stmt.setInt(91, 0);//row_id
            stmt.setString(92,null);//integracao_csc
            stmt.setString(93, null);//simpesl_crh
            stmt.setString(94, null);//faturamento_simples_crh
            stmt.setString(95, null);//microempresa_crh
            stmt.setString(96, null);//diretorio_dirf
            stmt.setString(97, null);//epp_crh
            stmt.setShort(98, num);//op_ratio_ferias
            stmt.setString(99, null);//diretorio_urbs
            stmt.setShort(100, num);//cd_pagamento_gps_tomador
            stmt.setShort(101, num);//cd_recolhimento_tomador
            stmt.setShort(102, num);//cd_recolhimento
            stmt.setString(103, null);//diretorio_caged
            stmt.setString(104, null);//diretorio_integracao
            stmt.setShort(105, num);//codigo_pagamento_gps
            stmt.setShort(106, num);//cd_pagamento_gps
            stmt.setString(107, null);//diretorio_sefip
            stmt.setString(108, null);//participante_pat
            stmt.setString(109, null);//direotrio_raiz
            stmt.setShort(110, num);//cd_pagamento_gps_rateio
            stmt.setShort(111, num);//cd_recolhimento_rateio
            stmt.setString(112, null);//importa_lote_diferenca
            stmt.setString(113, null);//utiliza_controle_clifor
            stmt.setInt(114, 0);//cd_conta_juros_pagos
            stmt.setInt(115, 0);//cd_conta_juros_recebidos
            stmt.setInt(116, 0);//cd_conta_descontos_obtidos
            stmt.setInt(117, 0);//cd_conta_descontos_condedidos
            stmt.setInt(118, 0);//opcao_seguro_desemprego
            stmt.setString(119,null);//descontar_13_negativo
            stmt.setShort(120, num);//op_calculo_numero_dias
            stmt.setString(121, null);//entSemFinsLucrativo
            stmt.setString(122, null);//recolhimento_contr_sidical_centralizada
            stmt.setInt(123, 0);//cd_estabelecimento_centralizador?_contr_sindical
            stmt.setString(124, null);//pathAndFileLogo
            stmt.setInt(125, 0);//calc30_opcao_salario
            stmt.setString(126, null);//calc30_opca_salario_fevereiro
            stmt.setInt(127, 0);//calc30_opcao_dias
            stmt.setInt(128, 0);//cd_conta_despesas_bancarias_pgto
            stmt.setInt(129, 0);//cd_conta_despesas_bancarias_recebimento
            stmt.setString(130, null);//diretorio_grrf
            stmt.setString(131, null);//opcao_shitorico_contabil
            stmt.setString(132, null);//opcao_filtro_natureza_op;
            stmt.setString(133, null);//opcao_filtro_natureza_op_st
            stmt.setString(134,null);//opcao_data_lancamento_contabil
            stmt.setString(135, null);//imprimir_dados_destaque
            stmt.setString(136, null);//recibo_ferias_rateio
            stmt.setString(137, null);//recibo_ferias_adiantamento
            stmt.setString(138, null);//recibo_ferias_abono
            stmt.setString(139, null);//diretorio_sped
            stmt.setString(140, null);//esp_estabelecimento
            stmt.setString(141, null);//optante_liminar_aviso_previo
            stmt.setString(142, null);//micro_emp_indiv_crh
            stmt.setString(143, null);//empresa_cidade
            stmt.setString(144, null);//email_rh
            stmt.setString(145, null);//diretorio_dipj
            stmt.setInt(146, 0);//utiliza_controle_parcelas_auditoria
            stmt.setString(147, null);//historico_juros_pagos
            stmt.setString(148, null);//historico_juros_recebidos
            stmt.setString(149, null);//historico_descontos_obtidos
            stmt.setString(150, null);//historico_descontos_concedidos
            stmt.setString(151, null);//historico_desp_bancarias_pagas
            stmt.setString(152, null);//historico_desp_bancarias_recebidas
            stmt.setString(153, null);//historico_pagamento
            stmt.setString(154, null);//historico_recebimento
            stmt.setBytes(155, imagem);//logo
            stmt.setString(156, null);//diretorio_tc_Rs
            stmt.setInt(157, 0);//cd_remessa_tce_Rs
            stmt.setString(158, null);//emitir_aviso_ferias
            stmt.setString(159, null);//diretorio_seguro_desemprego
            stmt.setInt(160, 0);//cd_recolhimento_complemento_salarial
            stmt.setInt(161, 0);//cd_pagamento_gps_complemento_salarial
            stmt.setString(162, null);//opcao_data_aviso_previo
            stmt.setInt(163,0);//forma_digitacao_caixa_banco_auditoria
            stmt.setString(164, null);//historico_retencoes
            stmt.setInt(165, 0);//grau_padrao_relatorio
            stmt.setInt(166, 0);//EFD_atividade_preponderante
            stmt.setInt(167, 0);//EFD_natureza_pessoa_juridica
            stmt.setString(168, null);//id
            stmt.setString(169, null);//id_empresa_escritorio
            stmt.setTimestamp(170, currentTimestamp );//data_hora_alteracao
            
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
    public void gravarFuncionario (int cdEmpresa, PessoaFisica pf) throws JsageImportException{
        if (pf == null){
            String mensagem = "Não foi informada o Funcionario para importar";
            throw new JsageImportException(mensagem);
        }        
        Connection con = null;
        PreparedStatement stmt = null;
        short estadoCivilDefault = 1;
        short nacionalidadeDefault = 10;
        short anoChegadaDefault = 0;
        short grauInstrucaoDefault = 9;
        short dddDefault = 00;
        short telefoneDefault = 0000000;
        int celularDefault = 000000000;
        int cdMunicipioDefault =00000;
        String funAposentado = "N";
        int nrEnderecoDefault =0;
        int cepDefault = 00000000;
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_FUNCIONARIO);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, pf.getIdPessoa());//cd_funcionario
            stmt.setString(3, pf.getNomePessoa());//nome            
            stmt.setString(4, pf.getLogradouro());//endereco
            stmt.setInt(5, nrEnderecoDefault);//nr_endereco
            stmt.setString(6, pf.getComplemento());//compl_endereco
            stmt.setString(7, pf.getBairro());//bairro
            stmt.setString(8, trataDados.recuperarCidade(pf.getIdmunicipio()));//cidade
            stmt.setString(9, null);//estado
            stmt.setInt(10, cepDefault);//cep
            stmt.setString(11, trataDados.recuperarPai(pf.getIdPessoa()));//pai
            stmt.setString(12, trataDados.recuperarMae (pf.getIdPessoa()));//mae
            stmt.setString(13, pf.getIndSexo());//sexo
            stmt.setShort(14, estadoCivilDefault);//estado_civil
            stmt.setShort(15, nacionalidadeDefault);//nacionalidade
            stmt.setShort(16, anoChegadaDefault);//ano_chegada
            stmt.setShort(17, grauInstrucaoDefault);//grau_instrucao
            stmt.setTimestamp(18, pf.getDataNascimento());//dt_nascimento
            stmt.setShort(19, dddDefault);//ddd
            stmt.setShort(20, telefoneDefault);//telefone
            stmt.setString(21, null);//apelido
            stmt.setString(22, null);//chave_acesso
            stmt.setString(23, null);//senha_acesso
            stmt.setString(24, trataDados.convertIntToString(pf.getIdRaca()));//raca
            stmt.setString(25, trataDados.recuperarTipoDeficiente(pf.getIdTipoDeficiencia()));//deficiente
            stmt.setString(26, trataDados.convertIntToString(pf.getIdMunicipioNaturalidade()));//cidade_nascimento
            stmt.setString(27, null);//estado_nascimento
            stmt.setShort(28, dddDefault);//ddd_celular
            stmt.setInt(29, celularDefault);//celular
            stmt.setString(30, pf.getNomePessoa());//nomecompleto
            stmt.setString(31, null);//email
            stmt.setTimestamp(32, pf.getDataChegada());//data_chegada
            stmt.setString(33, trataDados.convertIntToString(pf.getIdTipoLogradouro()));
            stmt.setInt(34, cdMunicipioDefault);//cd_municipio
            stmt.setInt(35, pf.getIdMunicipioNaturalidade());//cd_municipio_nascimento
            stmt.setString(36, funAposentado);//funcionario_aposentado
            stmt.setTimestamp(37, currentTimestamp);//data_hora_alteracao
            stmt.setString(38, null);//id
            stmt.setString(39, null);//id_endereco
            
            stmt.executeUpdate();
            
        } catch (SQLException exc) {
            StringBuffer msg = new StringBuffer("Não foi possível incluir o Funcionario no SAGE.");
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
            //trataDados.recuperarUF(pf.getIdUfCtps())
            stmt.setString(6, null);//uf_carteira
            stmt.setString(7, trataDados.recuperarPIS(cdFuncionario));//pis
            stmt.setString(8, pf.getCpfFormatado());//cpf
            stmt.setString(9, pf.getNumeroDocumentoIdentidade());//nr_identidade
            stmt.setString(10, trataDados.tratarOrgaoRG(pf.getOrgaoExpedidorDocumentoIdentidade()));//orgao_identidade
            stmt.setString(11, carteiraDefault);//uf_identidade   
            stmt.setInt(12, trataDados.converterSrintInt(pf.getNumeroCnh()));//nr_habilitacao 
            //trataDados.convertIntToString(pf.getIdcategoriaHabilitacaoCnh()) tamanho da string problema
            stmt.setString(13, null);//categoria_habilitacao
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
            stmt.setString(37, null);//uf_certidao
            stmt.setInt(38, 0);//cd_municipio_certidao
            stmt.setTimestamp(39, pf.getDataPrimeiraHabilitacao());//dt_primeira_habilitacao
            //trataDados.recuperarUF(pf.getIdufcnh()) problema tamanho da string
            stmt.setString(40,null);//uf_habilitacao
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
    public void gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep) throws JsageImportException{
        if (dep == null || cdFuncionario == 0 || cdEmpresa == 0){
            String mensagem = "Não foi informada o dependente para importar";
            throw new JsageImportException(mensagem);
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_INCLUIR_DEP);
            stmt.setInt(1, cdEmpresa);//cd_empresa
            stmt.setInt(2, cdFuncionario);//cd_funcionario
            stmt.setInt(3, dep.getIdPessoa());//cd_dependente
            stmt.setTimestamp(4, trataDados.horaAtual());//dt_inclusao
            stmt.setString(5,dep.getNomePessoa());//nome
            stmt.setInt(6, 0);//tipo_parentesco
            stmt.setString(7, null);//descricao_parentesco
            stmt.setTimestamp(8, null);//dt_nascimento
            stmt.setString(9, null);//suspende_sf
            stmt.setString(10, dep.getObservacao());//observacao
            stmt.setString(11, null);//cpf_dependente
            stmt.setInt(12, 0);//esocial_tipo_parentesco
            stmt.setString(13, null);//esocial_pensionista
            stmt.setInt(14, 0);//esocial_evento_pensao
            stmt.setDouble(15, 0);//esocial_percentual_pensao
            stmt.setTimestamp(16, trataDados.horaAtual());
            stmt.setString(17, null);//id
            
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
    public List pesquisaId(String cnpj) throws JsageImportException {
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
                StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
                mensagem.append("\nMotivo: " + exc.getMessage());
                throw new JsageImportException(mensagem.toString());
            } finally {
                GerenciadorConexao.closeConexao(con, stmt, rs);
            }
    }
    
    @Override
    public List pesquisaIdFuncionario(int idPessoa) throws JsageImportException {
        if (idPessoa == 0) {
            return recuperarEmpresas();
        }
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_PESQUISAREMPRESA_FUNCIONARIO);
            stmt.setInt(1, idPessoa );
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
        emp.setNivel2(rs.getString("nivel2"));
        emp.setNivel3(rs.getString("nivel3"));
        emp.setCod_especifico_crh(rs.getShort("cod_especifico_crh"));
        emp.setCod_especifico_cef(rs.getShort("cod_especifico_cef"));
        emp.setCod_especifico_csc(rs.getShort("cod_especifico_csc"));
        emp.setCod_especifico_ccp(rs.getShort("cod_especifico_ccp"));
        emp.setValor_extra1(rs.getString("valor_extra1"));
        emp.setValor_extra2(rs.getString("valor_extra2"));
        emp.setValor_extra3(rs.getString("valor_extra3"));
        emp.setValor_extra4(rs.getString("valor_extra4"));
        emp.setValor_extra5(rs.getString("valor_extra5"));
        emp.setCd_receita_icms(rs.getInt("cd_receita_icms"));
        emp.setMascara_plano_contas(rs.getString("mascara_plano_contas"));
        emp.setNum_niveis_plano_contas(rs.getInt("num_niveis_plano_contas"));
        emp.setNum_digitos_plano_contas(rs.getString("num_digitos_plano_contas"));
        emp.setComprimento_plano_contas(rs.getString("comprimento_plano_contas"));
        emp.setSeq_conta(rs.getInt("seq_conta"));
        emp.setConta_patrimonio(rs.getString("conta_patrimonio"));
        emp.setConta_caixa(rs.getString("conta_caixa"));
        emp.setOpcao_caixa(rs.getInt("opcao_caixa"));
        emp.setLibera_lote_aberto(rs.getString("libera_lote_aberto"));
        emp.setLanca_lote_lib(rs.getString("lanca_lote_lib"));
        emp.setDarf_cofins_ret_quinzenal(rs.getShort("darf_cofins_ret_quinzenal"));
        emp.setDigito_darf_cofins_ret_quinzenal(rs.getShort("digito_darf_cofins_ret_quinzenal"));
        emp.setDarf_pis_ret_quinzenal(rs.getShort("darf_pis_ret_quinzenal"));
        emp.setDigito_darf_pis_ret_quinzenal(rs.getShort("digito_darf_pis_ret_quinzenal"));
        emp.setDarf_csll_ret_quinzenal(rs.getShort("darf_csll_ret_quinzenal"));
        emp.setDigito_darf_csll_ret_quinzenal(rs.getShort("digito_darf_csll_ret_quinzenal"));
        emp.setDarf_pis_cofins_csll_ret_quinzenal(rs.getShort("darf_pis_cofins_csll_ret_quinzenal"));
        emp.setDigito_darf_pis_cofins_csll_ret_quinzenal(rs.getShort("digito_darf_pis_cofins_csll_ret_quinzenal"));
        emp.setOpcao_conta_cliente(rs.getString("opcao_conta_cliente"));
        emp.setConta_cliente(rs.getString("conta_cliente"));
        emp.setConta_cliente_unico(rs.getInt("conta_cliente_unico"));
        emp.setOpcao_conta_fornecedor(rs.getString("opcao_conta_fornecedor"));
        emp.setConta_fornecedor(rs.getString("conta_fornecedor"));
        emp.setConta_fornecedor_unico(rs.getInt("conta_fornecedor_unico"));
        emp.setOp_calculo_horista(rs.getInt("op_calculo_horista"));
        emp.setVl_arredondamento(rs.getDouble("vl_arredondamento"));
        emp.setMsg_aniversario(rs.getString("msg_aniversario"));
        emp.setMsg_admissao(rs.getString("msg_admissao"));
        emp.setConvenio_caged(rs.getInt("convenio_caged"));
        emp.setVl_maximo_cheque(rs.getDouble("vl_maximo_cheque"));
        emp.setVl_limite_cheque1(rs.getDouble("vl_limite_cheque1"));
        emp.setVl_limite_cheque2(rs.getDouble("vl_limite_cheque2"));
        emp.setVl_limite_cheque3(rs.getDouble("vl_limite_cheque3"));
        emp.setVl_limite_cheque4(rs.getDouble("vl_limite_cheque4"));
        emp.setVl_limite_cheque5(rs.getDouble("vl_limite_cheque5"));
        emp.setQt_cheque1(rs.getInt("qt_cheque1"));
        emp.setQt_cheque2(rs.getInt("qt_cheque2"));
        emp.setQt_cheque3(rs.getInt("qt_cheque3"));
        emp.setQt_cheque4(rs.getInt("qt_cheque4"));
        emp.setQt_cheque5(rs.getInt("qt_cheque5"));
        emp.setPossui_tomador(rs.getString("possui_tomador"));
        emp.setPagamento_mes(rs.getString("pagamento_mes"));
        emp.setProlabore_mes(rs.getString("prolabore_mes"));
        emp.setCargos_salarios(rs.getString("cargos_salarios"));
        emp.setProdutor_rural(rs.getString("produtor_rural"));
        emp.setNaooptante_liminar(rs.getString("naooptante_liminar"));
        emp.setOptante_liminar(rs.getString("optante_liminar"));
        emp.setNr_memorial_mtb(rs.getString("nr_memorial_mtb"));
        emp.setNome_responsavel_rescisao(rs.getString("nome_responsavel_rescisao"));
        emp.setCpf_responsavel_rescisao(rs.getString("cpf_responsavel_rescisao"));
        emp.setRg_responsavel_rescisao(rs.getString("rg_responsavel_rescisao"));
        emp.setFuncao_responsavel_rescisao(rs.getString("funcao_responsavel_rescisao"));
        emp.setNome_responsavel_ppp(rs.getString("nome_responsavel_ppp"));
        emp.setNit_responsavel_ppp(rs.getString("nit_responsavel_ppp"));
        emp.setRodape_crh(rs.getString("rodape_crh"));
        emp.setRodape_cef(rs.getString("rodape_cef"));
        emp.setRodape_csc(rs.getString("rodape_csc"));
        emp.setRodape_ccp(rs.getString("rodape_ccp"));
        emp.setUtiliza_conta_clifor(rs.getString("utiliza_conta_clifor"));
        emp.setPerc_contribuicao_social(rs.getDouble("perc_contribuicao_social"));
        emp.setStatus(rs.getString("status"));
        emp.setRow_id(rs.getInt("row_id"));
        emp.setIntegracao_csc(rs.getString("integracao_csc"));
        emp.setSimples_crh(rs.getString("simples_crh"));
        emp.setFaturamento_simples_crh(rs.getString("faturamento_simples_crh"));
        emp.setMicroempresa_crh(rs.getString("microempresa_crh"));
        emp.setDiretorio_dirf(rs.getString("diretorio_dirf"));
        emp.setEpp_crh(rs.getString("epp_crh"));
        emp.setOp_rateio_ferias(rs.getShort("op_rateio_ferias"));
        emp.setDiretorio_urbs(rs.getString("diretorio_urbs"));
        emp.setCd_pagamento_gps_tomador(rs.getShort("cd_pagamento_gps_tomador"));
        emp.setCd_recolhimento_tomador(rs.getShort("cd_recolhimento_tomador"));
        emp.setCd_recolhimento(rs.getShort("cd_recolhimento"));
        emp.setDiretorio_caged(rs.getString("diretorio_caged"));
        emp.setDiretorio_integracao(rs.getString("diretorio_integracao"));
        emp.setCodigo_pagamento_gps(rs.getShort("codigo_pagamento_gps"));
        emp.setCd_pagamento_gps(rs.getShort("cd_pagamento_gps"));
        emp.setDiretorio_sefip(rs.getString("diretorio_sefip"));
        emp.setParticipante_pat(rs.getString("participante_pat"));
        emp.setDiretorio_raiz(rs.getString("diretorio_raiz"));
        emp.setCd_pagamento_gps_rateio(rs.getShort("cd_pagamento_gps_rateio"));
        emp.setCd_recolhimento_rateio(rs.getShort("cd_recolhimento_rateio"));
        emp.setImporta_lote_diferenca(rs.getString("importa_lote_diferenca"));
        emp.setUtiliza_controle_clifor(rs.getString("utiliza_controle_clifor"));
        emp.setCd_conta_juros_pagos(rs.getInt("cd_conta_juros_pagos"));
        emp.setCd_conta_juros_recebidos(rs.getInt("cd_conta_juros_recebidos"));
        emp.setCd_conta_descontos_obtidos(rs.getInt("cd_conta_descontos_obtidos"));
        emp.setCd_conta_descontos_concedidos(rs.getInt("cd_conta_descontos_concedidos"));
        emp.setOpcao_seguro_desemprego(rs.getInt("opcao_seguro_desemprego"));
        emp.setDescontar_13_negativo(rs.getString("descontar_13_negativo"));
        emp.setOp_calculo_numero_dias(rs.getShort("op_calculo_numero_dias"));
        emp.setEntSemFinsLucrativos(rs.getString("EntSemFinsLucrativos"));
        emp.setRecolhimento_contr_sindical_centralizada(rs.getString("recolhimento_contr_sindical_centralizada"));
        emp.setCd_estabelecimento_centralizador_contr_sindical(rs.getInt("cd_estabelecimento_centralizador_contr_sindical"));
        emp.setPathAndFileLogo(rs.getString("PathAndFileLogo"));
        emp.setCalc30_opcao_salario(rs.getInt("calc30_opcao_salario"));
        emp.setCalc30_opcao_salario_fevereiro(rs.getString("calc30_opcao_salario_fevereiro"));
        emp.setCalc30_opcao_dias(rs.getInt("calc30_opcao_dias"));
        emp.setCd_conta_despesas_bancarias_pgto(rs.getInt("cd_conta_despesas_bancarias_pgto"));
        emp.setCd_conta_despesas_bancarias_recebimento(rs.getInt("cd_conta_despesas_bancarias_recebimento"));
        emp.setDiretorio_grrf(rs.getString("diretorio_grrf"));
        emp.setOpcao_historico_contabil(rs.getString("opcao_historico_contabil"));
        emp.setOpcao_filtro_natureza_op(rs.getString("opcao_filtro_natureza_op"));
        emp.setOpcao_filtro_natureza_op_st(rs.getString("opcao_filtro_natureza_op_st"));
        emp.setOpcao_data_lancamento_contabil(rs.getString("opcao_data_lancamento_contabil"));
        emp.setImprimir_dados_destaque(rs.getString("imprimir_dados_destaque"));
        emp.setRecibo_ferias_rateio(rs.getString("recibo_ferias_rateio"));
        emp.setRecibo_ferias_adiantamento(rs.getString("recibo_ferias_adiantamento"));
        emp.setRecibo_ferias_abono(rs.getString("recibo_ferias_abono"));
        emp.setDiretorio_sped(rs.getString("diretorio_sped"));
        emp.setEsp_estabelecimento(rs.getString("esp_estabelecimento"));
        emp.setOptante_liminar_aviso_previo(rs.getString("optante_liminar_aviso_previo"));
        emp.setMicro_emp_indiv_crh(rs.getString("micro_emp_indiv_crh"));
        emp.setEmpresa_cidada(rs.getString("empresa_cidada"));
        emp.setEmail_rh(rs.getString("email_rh"));
        emp.setDiretorio_dipj(rs.getString("diretorio_dipj"));
        emp.setUtiliza_controle_parcelas_auditoria(rs.getInt("utiliza_controle_parcelas_auditoria"));
        emp.setHistorico_juros_pagos(rs.getString("historico_juros_pagos"));
        emp.setHistorico_juros_recebidos(rs.getString("historico_juros_recebidos"));
        emp.setHistorico_descontos_obtidos(rs.getString("historico_descontos_obtidos"));
        emp.setHistorico_descontos_concedidos(rs.getString("historico_descontos_concedidos"));
        emp.setHistorico_desp_bancaria_pagas(rs.getString("historico_desp_bancaria_pagas"));
        emp.setHistorico_desp_bancaria_recebidas(rs.getString("historico_desp_bancaria_recebidas"));
        emp.setHistorico_pagamentos(rs.getString("historico_pagamentos"));
        emp.setHistorico_recebimentos(rs.getString("historico_recebimentos"));
        emp.setLogo(rs.getBytes("logo"));
        emp.setDiretorio_tce_rs(rs.getString("diretorio_tce_rs"));
        emp.setCd_remessa_tce_rs(rs.getInt("cd_remessa_tce_rs"));
        emp.setEmitir_aviso_ferias(rs.getString("emitir_aviso_ferias"));
        emp.setDiretorio_seguro_desemprego(rs.getString("diretorio_seguro_desemprego"));
        emp.setCd_recolhimento_complemento_salarial(rs.getInt("cd_recolhimento_complemento_salarial"));
        emp.setCd_pagamento_gps_complemento_salarial(rs.getInt("cd_pagamento_gps_complemento_salarial"));
        emp.setOpcao_data_aviso_previo(rs.getString("opcao_data_aviso_previo"));
        emp.setForma_digitacao_caixa_banco_auditoria(rs.getInt("forma_digitacao_caixa_banco_auditoria"));
        emp.setHistorico_retencoes(rs.getString("historico_retencoes"));
        emp.setGrau_padrao_relatorio(rs.getInt("grau_padrao_relatorio"));
        emp.setEFD_atividade_preponderante(rs.getInt("EFD_atividade_preponderante"));
        emp.setEFD_natureza_pessoa_juridica(rs.getInt("EFD_natureza_pessoa_juridica"));
        emp.setId(rs.getString("id"));
        emp.setId_empresa_escritorio(rs.getString("id_empresa_escritorio"));
        emp.setData_hora_alteracao(rs.getTimestamp("data_hora_alteracao"));   
        
        
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