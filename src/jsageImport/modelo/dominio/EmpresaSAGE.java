/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.dominio;

import java.sql.Timestamp;

/**
 *
 * @author Gustavo
 */
public class EmpresaSAGE {
    int cd_empresa;
    String razao;
    String cnpj_cpf;
    Timestamp dt_sistema;
    Timestamp dt_inicial;
    Timestamp dt_final;
    String integracao_contabil;
    String centro_custo;
    String apropriacao_tributos;
    String pagamento_tributos;
    String contabilizacao_online;
    String agrupar_lancamentos;
    short opcao_agrupamento;
    String excluir_icms_entradas;
    String excluir_ipi;
    String contabilizar_nota_nota;
    String nivel1;
    String nivel2;
    String nivel3;
    short cod_especifico_crh;
    short cod_especifico_cef;
    short cod_especifico_csc;
    short cod_especifico_ccp;
    String valor_extra1;
    String valor_extra2;
    String valor_extra3;
    String valor_extra4;
    String valor_extra5;
    int cd_receita_icms;
    String mascara_plano_contas;
    int num_niveis_plano_contas;
    String num_digitos_plano_contas;
    String comprimento_plano_contas;
    int seq_conta;
    String conta_patrimonio;
    String conta_caixa;
    int opcao_caixa;
    String libera_lote_aberto;
    String lanca_lote_lib;
    short darf_cofins_ret_quinzenal;
    short digito_darf_cofins_ret_quinzenal;
    short darf_pis_ret_quinzenal;
    short digito_darf_pis_ret_quinzenal;
    short darf_csll_ret_quinzenal;
    short digito_darf_csll_ret_quinzenal;
    short darf_pis_cofins_csll_ret_quinzenal;
    short digito_darf_pis_cofins_csll_ret_quinzenal;
    String opcao_conta_cliente;
    String conta_cliente;
    int conta_cliente_unico;
    String opcao_conta_fornecedor;
    String conta_fornecedor;
    int conta_fornecedor_unico;
    int op_calculo_horista;
    double vl_arredondamento;
    String msg_aniversario;
    String msg_admissao;
    int convenio_caged;
    double vl_maximo_cheque;
    double vl_limite_cheque1;
    double vl_limite_cheque2;
    double vl_limite_cheque3;
    double vl_limite_cheque4;
    double vl_limite_cheque5;
    int qt_cheque1;
    int qt_cheque2;
    int qt_cheque3;
    int qt_cheque4;
    int qt_cheque5;
    String possui_tomador;
    String pagamento_mes;
    String prolabore_mes;
    String cargos_salarios;
    String produtor_rural;
    String naooptante_liminar;
    String optante_liminar;
    String nr_memorial_mtb;
    String nome_responsavel_rescisao;
    String cpf_responsavel_rescisao;
    String rg_responsavel_rescisao;
    String funcao_responsavel_rescisao;
    String nome_responsavel_ppp;
    String nit_responsavel_ppp;
    String rodape_crh;
    String rodape_cef;
    String rodape_csc;
    String rodape_ccp;
    String utiliza_conta_clifor;
    double perc_contribuicao_social;
    String status;
    int row_id;
    String integracao_csc;
    String simples_crh;
    String faturamento_simples_crh;
    String microempresa_crh;
    String diretorio_dirf;
    String epp_crh;
    short op_rateio_ferias;
    String diretorio_urbs;
    short cd_pagamento_gps_tomador;
    short cd_recolhimento_tomador;
    short cd_recolhimento;
    String diretorio_caged;
    String diretorio_integracao;
    short codigo_pagamento_gps;
    short cd_pagamento_gps;
    String diretorio_sefip;
    String participante_pat;
    String diretorio_raiz;
    short cd_pagamento_gps_rateio;
    short cd_recolhimento_rateio;
    String importa_lote_diferenca;
    String utiliza_controle_clifor;
    int cd_conta_juros_pagos;
    int cd_conta_juros_recebidos;
    int cd_conta_descontos_obtidos;
    int cd_conta_descontos_concedidos;
    int opcao_seguro_desemprego;
    String descontar_13_negativo;
    short op_calculo_numero_dias;
    String EntSemFinsLucrativos;
    String recolhimento_contr_sindical_centralizada;
    int cd_estabelecimento_centralizador_contr_sindical;
    String PathAndFileLogo;
    int calc30_opcao_salario;
    String calc30_opcao_salario_fevereiro;
    int calc30_opcao_dias;
    int cd_conta_despesas_bancarias_pgto;
    int cd_conta_despesas_bancarias_recebimento;
    String diretorio_grrf;
    String opcao_historico_contabil;
    String opcao_filtro_natureza_op;
    String opcao_filtro_natureza_op_st;
    String opcao_data_lancamento_contabil;
    String imprimir_dados_destaque;
    String recibo_ferias_rateio;
    String recibo_ferias_adiantamento;
    String recibo_ferias_abono;
    String diretorio_sped;
    String esp_estabelecimento;
    String optante_liminar_aviso_previo;
    String micro_emp_indiv_crh;
    String empresa_cidada;
    String email_rh;
    String diretorio_dipj;
    int utiliza_controle_parcelas_auditoria;
    String historico_juros_pagos;
    String historico_juros_recebidos;
    String historico_descontos_obtidos;
    String historico_descontos_concedidos;
    String historico_desp_bancaria_pagas;
    String historico_desp_bancaria_recebidas;
    String historico_pagamentos;
    String historico_recebimentos;
    byte [] logo;
    String diretorio_tce_rs;
    int cd_remessa_tce_rs;
    String emitir_aviso_ferias;
    String diretorio_seguro_desemprego;
    int cd_recolhimento_complemento_salarial;
    int cd_pagamento_gps_complemento_salarial;
    String opcao_data_aviso_previo;
    int forma_digitacao_caixa_banco_auditoria;
    String historico_retencoes;
    int grau_padrao_relatorio;
    int EFD_atividade_preponderante;
    int EFD_natureza_pessoa_juridica;
    String id;
    String id_empresa_escritorio;
    Timestamp data_hora_alteracao;

    public int getCd_empresa() {
        return cd_empresa;
    }

    public void setCd_empresa(int cd_empresa) {
        this.cd_empresa = cd_empresa;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

    public Timestamp getDt_sistema() {
        return dt_sistema;
    }

    public void setDt_sistema(Timestamp dt_sistema) {
        this.dt_sistema = dt_sistema;
    }

    public Timestamp getDt_inicial() {
        return dt_inicial;
    }

    public void setDt_inicial(Timestamp dt_inicial) {
        this.dt_inicial = dt_inicial;
    }

    public Timestamp getDt_final() {
        return dt_final;
    }

    public void setDt_final(Timestamp dt_final) {
        this.dt_final = dt_final;
    }

    public String getIntegracao_contabil() {
        return integracao_contabil;
    }

    public void setIntegracao_contabil(String integracao_contabil) {
        this.integracao_contabil = integracao_contabil;
    }

    public String getCentro_custo() {
        return centro_custo;
    }

    public void setCentro_custo(String centro_custo) {
        this.centro_custo = centro_custo;
    }

    public String getApropriacao_tributos() {
        return apropriacao_tributos;
    }

    public void setApropriacao_tributos(String apropriacao_tributos) {
        this.apropriacao_tributos = apropriacao_tributos;
    }

    public String getPagamento_tributos() {
        return pagamento_tributos;
    }

    public void setPagamento_tributos(String pagamento_tributos) {
        this.pagamento_tributos = pagamento_tributos;
    }

    public String getContabilizacao_online() {
        return contabilizacao_online;
    }

    public void setContabilizacao_online(String contabilizacao_online) {
        this.contabilizacao_online = contabilizacao_online;
    }

    public String getAgrupar_lancamentos() {
        return agrupar_lancamentos;
    }

    public void setAgrupar_lancamentos(String agrupar_lancamentos) {
        this.agrupar_lancamentos = agrupar_lancamentos;
    }

    public short getOpcao_agrupamento() {
        return opcao_agrupamento;
    }

    public void setOpcao_agrupamento(short opcao_agrupamento) {
        this.opcao_agrupamento = opcao_agrupamento;
    }

    public String getExcluir_icms_entradas() {
        return excluir_icms_entradas;
    }

    public void setExcluir_icms_entradas(String excluir_icms_entradas) {
        this.excluir_icms_entradas = excluir_icms_entradas;
    }

    public String getExcluir_ipi() {
        return excluir_ipi;
    }

    public void setExcluir_ipi(String excluir_ipi) {
        this.excluir_ipi = excluir_ipi;
    }

    public String getContabilizar_nota_nota() {
        return contabilizar_nota_nota;
    }

    public void setContabilizar_nota_nota(String contabilizar_nota_nota) {
        this.contabilizar_nota_nota = contabilizar_nota_nota;
    }

    public String getNivel1() {
        return nivel1;
    }

    public void setNivel1(String nivel1) {
        this.nivel1 = nivel1;
    }

    public String getNivel2() {
        return nivel2;
    }

    public void setNivel2(String nivel2) {
        this.nivel2 = nivel2;
    }

    public String getNivel3() {
        return nivel3;
    }

    public void setNivel3(String nivel3) {
        this.nivel3 = nivel3;
    }

    public short getCod_especifico_crh() {
        return cod_especifico_crh;
    }

    public void setCod_especifico_crh(short cod_especifico_crh) {
        this.cod_especifico_crh = cod_especifico_crh;
    }

    public short getCod_especifico_cef() {
        return cod_especifico_cef;
    }

    public void setCod_especifico_cef(short cod_especifico_cef) {
        this.cod_especifico_cef = cod_especifico_cef;
    }

    public short getCod_especifico_csc() {
        return cod_especifico_csc;
    }

    public void setCod_especifico_csc(short cod_especifico_csc) {
        this.cod_especifico_csc = cod_especifico_csc;
    }

    public short getCod_especifico_ccp() {
        return cod_especifico_ccp;
    }

    public void setCod_especifico_ccp(short cod_especifico_ccp) {
        this.cod_especifico_ccp = cod_especifico_ccp;
    }

    public String getValor_extra1() {
        return valor_extra1;
    }

    public void setValor_extra1(String valor_extra1) {
        this.valor_extra1 = valor_extra1;
    }

    public String getValor_extra2() {
        return valor_extra2;
    }

    public void setValor_extra2(String valor_extra2) {
        this.valor_extra2 = valor_extra2;
    }

    public String getValor_extra3() {
        return valor_extra3;
    }

    public void setValor_extra3(String valor_extra3) {
        this.valor_extra3 = valor_extra3;
    }

    public String getValor_extra4() {
        return valor_extra4;
    }

    public void setValor_extra4(String valor_extra4) {
        this.valor_extra4 = valor_extra4;
    }

    public String getValor_extra5() {
        return valor_extra5;
    }

    public void setValor_extra5(String valor_extra5) {
        this.valor_extra5 = valor_extra5;
    }

    public int getCd_receita_icms() {
        return cd_receita_icms;
    }

    public void setCd_receita_icms(int cd_receita_icms) {
        this.cd_receita_icms = cd_receita_icms;
    }

    public String getMascara_plano_contas() {
        return mascara_plano_contas;
    }

    public void setMascara_plano_contas(String mascara_plano_contas) {
        this.mascara_plano_contas = mascara_plano_contas;
    }

    public int getNum_niveis_plano_contas() {
        return num_niveis_plano_contas;
    }

    public void setNum_niveis_plano_contas(int num_niveis_plano_contas) {
        this.num_niveis_plano_contas = num_niveis_plano_contas;
    }

    public String getNum_digitos_plano_contas() {
        return num_digitos_plano_contas;
    }

    public void setNum_digitos_plano_contas(String num_digitos_plano_contas) {
        this.num_digitos_plano_contas = num_digitos_plano_contas;
    }

    public String getComprimento_plano_contas() {
        return comprimento_plano_contas;
    }

    public void setComprimento_plano_contas(String comprimento_plano_contas) {
        this.comprimento_plano_contas = comprimento_plano_contas;
    }

    public int getSeq_conta() {
        return seq_conta;
    }

    public void setSeq_conta(int seq_conta) {
        this.seq_conta = seq_conta;
    }

    public String getConta_patrimonio() {
        return conta_patrimonio;
    }

    public void setConta_patrimonio(String conta_patrimonio) {
        this.conta_patrimonio = conta_patrimonio;
    }

    public String getConta_caixa() {
        return conta_caixa;
    }

    public void setConta_caixa(String conta_caixa) {
        this.conta_caixa = conta_caixa;
    }

    public int getOpcao_caixa() {
        return opcao_caixa;
    }

    public void setOpcao_caixa(int opcao_caixa) {
        this.opcao_caixa = opcao_caixa;
    }

    public String getLibera_lote_aberto() {
        return libera_lote_aberto;
    }

    public void setLibera_lote_aberto(String libera_lote_aberto) {
        this.libera_lote_aberto = libera_lote_aberto;
    }

    public String getLanca_lote_lib() {
        return lanca_lote_lib;
    }

    public void setLanca_lote_lib(String lanca_lote_lib) {
        this.lanca_lote_lib = lanca_lote_lib;
    }

    public short getDarf_cofins_ret_quinzenal() {
        return darf_cofins_ret_quinzenal;
    }

    public void setDarf_cofins_ret_quinzenal(short darf_cofins_ret_quinzenal) {
        this.darf_cofins_ret_quinzenal = darf_cofins_ret_quinzenal;
    }

    public short getDigito_darf_cofins_ret_quinzenal() {
        return digito_darf_cofins_ret_quinzenal;
    }

    public void setDigito_darf_cofins_ret_quinzenal(short digito_darf_cofins_ret_quinzenal) {
        this.digito_darf_cofins_ret_quinzenal = digito_darf_cofins_ret_quinzenal;
    }

    public short getDarf_pis_ret_quinzenal() {
        return darf_pis_ret_quinzenal;
    }

    public void setDarf_pis_ret_quinzenal(short darf_pis_ret_quinzenal) {
        this.darf_pis_ret_quinzenal = darf_pis_ret_quinzenal;
    }

    public short getDigito_darf_pis_ret_quinzenal() {
        return digito_darf_pis_ret_quinzenal;
    }

    public void setDigito_darf_pis_ret_quinzenal(short digito_darf_pis_ret_quinzenal) {
        this.digito_darf_pis_ret_quinzenal = digito_darf_pis_ret_quinzenal;
    }

    public short getDarf_csll_ret_quinzenal() {
        return darf_csll_ret_quinzenal;
    }

    public void setDarf_csll_ret_quinzenal(short darf_csll_ret_quinzenal) {
        this.darf_csll_ret_quinzenal = darf_csll_ret_quinzenal;
    }

    public short getDigito_darf_csll_ret_quinzenal() {
        return digito_darf_csll_ret_quinzenal;
    }

    public void setDigito_darf_csll_ret_quinzenal(short digito_darf_csll_ret_quinzenal) {
        this.digito_darf_csll_ret_quinzenal = digito_darf_csll_ret_quinzenal;
    }

    public short getDarf_pis_cofins_csll_ret_quinzenal() {
        return darf_pis_cofins_csll_ret_quinzenal;
    }

    public void setDarf_pis_cofins_csll_ret_quinzenal(short darf_pis_cofins_csll_ret_quinzenal) {
        this.darf_pis_cofins_csll_ret_quinzenal = darf_pis_cofins_csll_ret_quinzenal;
    }

    public short getDigito_darf_pis_cofins_csll_ret_quinzenal() {
        return digito_darf_pis_cofins_csll_ret_quinzenal;
    }

    public void setDigito_darf_pis_cofins_csll_ret_quinzenal(short digito_darf_pis_cofins_csll_ret_quinzenal) {
        this.digito_darf_pis_cofins_csll_ret_quinzenal = digito_darf_pis_cofins_csll_ret_quinzenal;
    }

    public String getOpcao_conta_cliente() {
        return opcao_conta_cliente;
    }

    public void setOpcao_conta_cliente(String opcao_conta_cliente) {
        this.opcao_conta_cliente = opcao_conta_cliente;
    }

    public String getConta_cliente() {
        return conta_cliente;
    }

    public void setConta_cliente(String conta_cliente) {
        this.conta_cliente = conta_cliente;
    }

    public int getConta_cliente_unico() {
        return conta_cliente_unico;
    }

    public void setConta_cliente_unico(int conta_cliente_unico) {
        this.conta_cliente_unico = conta_cliente_unico;
    }

    public String getOpcao_conta_fornecedor() {
        return opcao_conta_fornecedor;
    }

    public void setOpcao_conta_fornecedor(String opcao_conta_fornecedor) {
        this.opcao_conta_fornecedor = opcao_conta_fornecedor;
    }

    public String getConta_fornecedor() {
        return conta_fornecedor;
    }

    public void setConta_fornecedor(String conta_fornecedor) {
        this.conta_fornecedor = conta_fornecedor;
    }

    public int getConta_fornecedor_unico() {
        return conta_fornecedor_unico;
    }

    public void setConta_fornecedor_unico(int conta_fornecedor_unico) {
        this.conta_fornecedor_unico = conta_fornecedor_unico;
    }

    public int getOp_calculo_horista() {
        return op_calculo_horista;
    }

    public void setOp_calculo_horista(int op_calculo_horista) {
        this.op_calculo_horista = op_calculo_horista;
    }

    public double getVl_arredondamento() {
        return vl_arredondamento;
    }

    public void setVl_arredondamento(double vl_arredondamento) {
        this.vl_arredondamento = vl_arredondamento;
    }

    public String getMsg_aniversario() {
        return msg_aniversario;
    }

    public void setMsg_aniversario(String msg_aniversario) {
        this.msg_aniversario = msg_aniversario;
    }

    public String getMsg_admissao() {
        return msg_admissao;
    }

    public void setMsg_admissao(String msg_admissao) {
        this.msg_admissao = msg_admissao;
    }

    public int getConvenio_caged() {
        return convenio_caged;
    }

    public void setConvenio_caged(int convenio_caged) {
        this.convenio_caged = convenio_caged;
    }

    public double getVl_maximo_cheque() {
        return vl_maximo_cheque;
    }

    public void setVl_maximo_cheque(double vl_maximo_cheque) {
        this.vl_maximo_cheque = vl_maximo_cheque;
    }

    public double getVl_limite_cheque1() {
        return vl_limite_cheque1;
    }

    public void setVl_limite_cheque1(double vl_limite_cheque1) {
        this.vl_limite_cheque1 = vl_limite_cheque1;
    }

    public double getVl_limite_cheque2() {
        return vl_limite_cheque2;
    }

    public void setVl_limite_cheque2(double vl_limite_cheque2) {
        this.vl_limite_cheque2 = vl_limite_cheque2;
    }

    public double getVl_limite_cheque3() {
        return vl_limite_cheque3;
    }

    public void setVl_limite_cheque3(double vl_limite_cheque3) {
        this.vl_limite_cheque3 = vl_limite_cheque3;
    }

    public double getVl_limite_cheque4() {
        return vl_limite_cheque4;
    }

    public void setVl_limite_cheque4(double vl_limite_cheque4) {
        this.vl_limite_cheque4 = vl_limite_cheque4;
    }

    public double getVl_limite_cheque5() {
        return vl_limite_cheque5;
    }

    public void setVl_limite_cheque5(double vl_limite_cheque5) {
        this.vl_limite_cheque5 = vl_limite_cheque5;
    }

    public int getQt_cheque1() {
        return qt_cheque1;
    }

    public void setQt_cheque1(int qt_cheque1) {
        this.qt_cheque1 = qt_cheque1;
    }

    public int getQt_cheque2() {
        return qt_cheque2;
    }

    public void setQt_cheque2(int qt_cheque2) {
        this.qt_cheque2 = qt_cheque2;
    }

    public int getQt_cheque3() {
        return qt_cheque3;
    }

    public void setQt_cheque3(int qt_cheque3) {
        this.qt_cheque3 = qt_cheque3;
    }

    public int getQt_cheque4() {
        return qt_cheque4;
    }

    public void setQt_cheque4(int qt_cheque4) {
        this.qt_cheque4 = qt_cheque4;
    }

    public int getQt_cheque5() {
        return qt_cheque5;
    }

    public void setQt_cheque5(int qt_cheque5) {
        this.qt_cheque5 = qt_cheque5;
    }

    public String getPossui_tomador() {
        return possui_tomador;
    }

    public void setPossui_tomador(String possui_tomador) {
        this.possui_tomador = possui_tomador;
    }

    public String getPagamento_mes() {
        return pagamento_mes;
    }

    public void setPagamento_mes(String pagamento_mes) {
        this.pagamento_mes = pagamento_mes;
    }

    public String getProlabore_mes() {
        return prolabore_mes;
    }

    public void setProlabore_mes(String prolabore_mes) {
        this.prolabore_mes = prolabore_mes;
    }

    public String getCargos_salarios() {
        return cargos_salarios;
    }

    public void setCargos_salarios(String cargos_salarios) {
        this.cargos_salarios = cargos_salarios;
    }

    public String getProdutor_rural() {
        return produtor_rural;
    }

    public void setProdutor_rural(String produtor_rural) {
        this.produtor_rural = produtor_rural;
    }

    public String getNaooptante_liminar() {
        return naooptante_liminar;
    }

    public void setNaooptante_liminar(String naooptante_liminar) {
        this.naooptante_liminar = naooptante_liminar;
    }

    public String getOptante_liminar() {
        return optante_liminar;
    }

    public void setOptante_liminar(String optante_liminar) {
        this.optante_liminar = optante_liminar;
    }

    public String getNr_memorial_mtb() {
        return nr_memorial_mtb;
    }

    public void setNr_memorial_mtb(String nr_memorial_mtb) {
        this.nr_memorial_mtb = nr_memorial_mtb;
    }

    public String getNome_responsavel_rescisao() {
        return nome_responsavel_rescisao;
    }

    public void setNome_responsavel_rescisao(String nome_responsavel_rescisao) {
        this.nome_responsavel_rescisao = nome_responsavel_rescisao;
    }

    public String getCpf_responsavel_rescisao() {
        return cpf_responsavel_rescisao;
    }

    public void setCpf_responsavel_rescisao(String cpf_responsavel_rescisao) {
        this.cpf_responsavel_rescisao = cpf_responsavel_rescisao;
    }

    public String getRg_responsavel_rescisao() {
        return rg_responsavel_rescisao;
    }

    public void setRg_responsavel_rescisao(String rg_responsavel_rescisao) {
        this.rg_responsavel_rescisao = rg_responsavel_rescisao;
    }

    public String getFuncao_responsavel_rescisao() {
        return funcao_responsavel_rescisao;
    }

    public void setFuncao_responsavel_rescisao(String funcao_responsavel_rescisao) {
        this.funcao_responsavel_rescisao = funcao_responsavel_rescisao;
    }

    public String getNome_responsavel_ppp() {
        return nome_responsavel_ppp;
    }

    public void setNome_responsavel_ppp(String nome_responsavel_ppp) {
        this.nome_responsavel_ppp = nome_responsavel_ppp;
    }

    public String getNit_responsavel_ppp() {
        return nit_responsavel_ppp;
    }

    public void setNit_responsavel_ppp(String nit_responsavel_ppp) {
        this.nit_responsavel_ppp = nit_responsavel_ppp;
    }

    public String getRodape_crh() {
        return rodape_crh;
    }

    public void setRodape_crh(String rodape_crh) {
        this.rodape_crh = rodape_crh;
    }

    public String getRodape_cef() {
        return rodape_cef;
    }

    public void setRodape_cef(String rodape_cef) {
        this.rodape_cef = rodape_cef;
    }

    public String getRodape_csc() {
        return rodape_csc;
    }

    public void setRodape_csc(String rodape_csc) {
        this.rodape_csc = rodape_csc;
    }

    public String getRodape_ccp() {
        return rodape_ccp;
    }

    public void setRodape_ccp(String rodape_ccp) {
        this.rodape_ccp = rodape_ccp;
    }

    public String getUtiliza_conta_clifor() {
        return utiliza_conta_clifor;
    }

    public void setUtiliza_conta_clifor(String utiliza_conta_clifor) {
        this.utiliza_conta_clifor = utiliza_conta_clifor;
    }

    public double getPerc_contribuicao_social() {
        return perc_contribuicao_social;
    }

    public void setPerc_contribuicao_social(double perc_contribuicao_social) {
        this.perc_contribuicao_social = perc_contribuicao_social;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRow_id() {
        return row_id;
    }

    public void setRow_id(int row_id) {
        this.row_id = row_id;
    }

    public String getIntegracao_csc() {
        return integracao_csc;
    }

    public void setIntegracao_csc(String integracao_csc) {
        this.integracao_csc = integracao_csc;
    }

    public String getSimples_crh() {
        return simples_crh;
    }

    public void setSimples_crh(String simples_crh) {
        this.simples_crh = simples_crh;
    }

    public String getFaturamento_simples_crh() {
        return faturamento_simples_crh;
    }

    public void setFaturamento_simples_crh(String faturamento_simples_crh) {
        this.faturamento_simples_crh = faturamento_simples_crh;
    }

    public String getMicroempresa_crh() {
        return microempresa_crh;
    }

    public void setMicroempresa_crh(String microempresa_crh) {
        this.microempresa_crh = microempresa_crh;
    }

    public String getDiretorio_dirf() {
        return diretorio_dirf;
    }

    public void setDiretorio_dirf(String diretorio_dirf) {
        this.diretorio_dirf = diretorio_dirf;
    }

    public String getEpp_crh() {
        return epp_crh;
    }

    public void setEpp_crh(String epp_crh) {
        this.epp_crh = epp_crh;
    }

    public short getOp_rateio_ferias() {
        return op_rateio_ferias;
    }

    public void setOp_rateio_ferias(short op_rateio_ferias) {
        this.op_rateio_ferias = op_rateio_ferias;
    }

    public String getDiretorio_urbs() {
        return diretorio_urbs;
    }

    public void setDiretorio_urbs(String diretorio_urbs) {
        this.diretorio_urbs = diretorio_urbs;
    }

    public short getCd_pagamento_gps_tomador() {
        return cd_pagamento_gps_tomador;
    }

    public void setCd_pagamento_gps_tomador(short cd_pagamento_gps_tomador) {
        this.cd_pagamento_gps_tomador = cd_pagamento_gps_tomador;
    }

    public short getCd_recolhimento_tomador() {
        return cd_recolhimento_tomador;
    }

    public void setCd_recolhimento_tomador(short cd_recolhimento_tomador) {
        this.cd_recolhimento_tomador = cd_recolhimento_tomador;
    }

    public short getCd_recolhimento() {
        return cd_recolhimento;
    }

    public void setCd_recolhimento(short cd_recolhimento) {
        this.cd_recolhimento = cd_recolhimento;
    }

    public String getDiretorio_caged() {
        return diretorio_caged;
    }

    public void setDiretorio_caged(String diretorio_caged) {
        this.diretorio_caged = diretorio_caged;
    }

    public String getDiretorio_integracao() {
        return diretorio_integracao;
    }

    public void setDiretorio_integracao(String diretorio_integracao) {
        this.diretorio_integracao = diretorio_integracao;
    }

    public short getCodigo_pagamento_gps() {
        return codigo_pagamento_gps;
    }

    public void setCodigo_pagamento_gps(short codigo_pagamento_gps) {
        this.codigo_pagamento_gps = codigo_pagamento_gps;
    }

    public short getCd_pagamento_gps() {
        return cd_pagamento_gps;
    }

    public void setCd_pagamento_gps(short cd_pagamento_gps) {
        this.cd_pagamento_gps = cd_pagamento_gps;
    }

    public String getDiretorio_sefip() {
        return diretorio_sefip;
    }

    public void setDiretorio_sefip(String diretorio_sefip) {
        this.diretorio_sefip = diretorio_sefip;
    }

    public String getParticipante_pat() {
        return participante_pat;
    }

    public void setParticipante_pat(String participante_pat) {
        this.participante_pat = participante_pat;
    }

    public String getDiretorio_raiz() {
        return diretorio_raiz;
    }

    public void setDiretorio_raiz(String diretorio_raiz) {
        this.diretorio_raiz = diretorio_raiz;
    }

    public short getCd_pagamento_gps_rateio() {
        return cd_pagamento_gps_rateio;
    }

    public void setCd_pagamento_gps_rateio(short cd_pagamento_gps_rateio) {
        this.cd_pagamento_gps_rateio = cd_pagamento_gps_rateio;
    }

    public short getCd_recolhimento_rateio() {
        return cd_recolhimento_rateio;
    }

    public void setCd_recolhimento_rateio(short cd_recolhimento_rateio) {
        this.cd_recolhimento_rateio = cd_recolhimento_rateio;
    }

    public String getImporta_lote_diferenca() {
        return importa_lote_diferenca;
    }

    public void setImporta_lote_diferenca(String importa_lote_diferenca) {
        this.importa_lote_diferenca = importa_lote_diferenca;
    }

    public String getUtiliza_controle_clifor() {
        return utiliza_controle_clifor;
    }

    public void setUtiliza_controle_clifor(String utiliza_controle_clifor) {
        this.utiliza_controle_clifor = utiliza_controle_clifor;
    }

    public int getCd_conta_juros_pagos() {
        return cd_conta_juros_pagos;
    }

    public void setCd_conta_juros_pagos(int cd_conta_juros_pagos) {
        this.cd_conta_juros_pagos = cd_conta_juros_pagos;
    }

    public int getCd_conta_juros_recebidos() {
        return cd_conta_juros_recebidos;
    }

    public void setCd_conta_juros_recebidos(int cd_conta_juros_recebidos) {
        this.cd_conta_juros_recebidos = cd_conta_juros_recebidos;
    }

    public int getCd_conta_descontos_obtidos() {
        return cd_conta_descontos_obtidos;
    }

    public void setCd_conta_descontos_obtidos(int cd_conta_descontos_obtidos) {
        this.cd_conta_descontos_obtidos = cd_conta_descontos_obtidos;
    }

    public int getCd_conta_descontos_concedidos() {
        return cd_conta_descontos_concedidos;
    }

    public void setCd_conta_descontos_concedidos(int cd_conta_descontos_concedidos) {
        this.cd_conta_descontos_concedidos = cd_conta_descontos_concedidos;
    }

    public int getOpcao_seguro_desemprego() {
        return opcao_seguro_desemprego;
    }

    public void setOpcao_seguro_desemprego(int opcao_seguro_desemprego) {
        this.opcao_seguro_desemprego = opcao_seguro_desemprego;
    }

    public String getDescontar_13_negativo() {
        return descontar_13_negativo;
    }

    public void setDescontar_13_negativo(String descontar_13_negativo) {
        this.descontar_13_negativo = descontar_13_negativo;
    }

    public short getOp_calculo_numero_dias() {
        return op_calculo_numero_dias;
    }

    public void setOp_calculo_numero_dias(short op_calculo_numero_dias) {
        this.op_calculo_numero_dias = op_calculo_numero_dias;
    }

    public String getEntSemFinsLucrativos() {
        return EntSemFinsLucrativos;
    }

    public void setEntSemFinsLucrativos(String EntSemFinsLucrativos) {
        this.EntSemFinsLucrativos = EntSemFinsLucrativos;
    }

    public String getRecolhimento_contr_sindical_centralizada() {
        return recolhimento_contr_sindical_centralizada;
    }

    public void setRecolhimento_contr_sindical_centralizada(String recolhimento_contr_sindical_centralizada) {
        this.recolhimento_contr_sindical_centralizada = recolhimento_contr_sindical_centralizada;
    }

    public int getCd_estabelecimento_centralizador_contr_sindical() {
        return cd_estabelecimento_centralizador_contr_sindical;
    }

    public void setCd_estabelecimento_centralizador_contr_sindical(int cd_estabelecimento_centralizador_contr_sindical) {
        this.cd_estabelecimento_centralizador_contr_sindical = cd_estabelecimento_centralizador_contr_sindical;
    }

    public String getPathAndFileLogo() {
        return PathAndFileLogo;
    }

    public void setPathAndFileLogo(String PathAndFileLogo) {
        this.PathAndFileLogo = PathAndFileLogo;
    }

    public int getCalc30_opcao_salario() {
        return calc30_opcao_salario;
    }

    public void setCalc30_opcao_salario(int calc30_opcao_salario) {
        this.calc30_opcao_salario = calc30_opcao_salario;
    }

    public String getCalc30_opcao_salario_fevereiro() {
        return calc30_opcao_salario_fevereiro;
    }

    public void setCalc30_opcao_salario_fevereiro(String calc30_opcao_salario_fevereiro) {
        this.calc30_opcao_salario_fevereiro = calc30_opcao_salario_fevereiro;
    }

    public int getCalc30_opcao_dias() {
        return calc30_opcao_dias;
    }

    public void setCalc30_opcao_dias(int calc30_opcao_dias) {
        this.calc30_opcao_dias = calc30_opcao_dias;
    }

    public int getCd_conta_despesas_bancarias_pgto() {
        return cd_conta_despesas_bancarias_pgto;
    }

    public void setCd_conta_despesas_bancarias_pgto(int cd_conta_despesas_bancarias_pgto) {
        this.cd_conta_despesas_bancarias_pgto = cd_conta_despesas_bancarias_pgto;
    }

    public int getCd_conta_despesas_bancarias_recebimento() {
        return cd_conta_despesas_bancarias_recebimento;
    }

    public void setCd_conta_despesas_bancarias_recebimento(int cd_conta_despesas_bancarias_recebimento) {
        this.cd_conta_despesas_bancarias_recebimento = cd_conta_despesas_bancarias_recebimento;
    }

    public String getDiretorio_grrf() {
        return diretorio_grrf;
    }

    public void setDiretorio_grrf(String diretorio_grrf) {
        this.diretorio_grrf = diretorio_grrf;
    }

    public String getOpcao_historico_contabil() {
        return opcao_historico_contabil;
    }

    public void setOpcao_historico_contabil(String opcao_historico_contabil) {
        this.opcao_historico_contabil = opcao_historico_contabil;
    }

    public String getOpcao_filtro_natureza_op() {
        return opcao_filtro_natureza_op;
    }

    public void setOpcao_filtro_natureza_op(String opcao_filtro_natureza_op) {
        this.opcao_filtro_natureza_op = opcao_filtro_natureza_op;
    }

    public String getOpcao_filtro_natureza_op_st() {
        return opcao_filtro_natureza_op_st;
    }

    public void setOpcao_filtro_natureza_op_st(String opcao_filtro_natureza_op_st) {
        this.opcao_filtro_natureza_op_st = opcao_filtro_natureza_op_st;
    }

    public String getOpcao_data_lancamento_contabil() {
        return opcao_data_lancamento_contabil;
    }

    public void setOpcao_data_lancamento_contabil(String opcao_data_lancamento_contabil) {
        this.opcao_data_lancamento_contabil = opcao_data_lancamento_contabil;
    }

    public String getImprimir_dados_destaque() {
        return imprimir_dados_destaque;
    }

    public void setImprimir_dados_destaque(String imprimir_dados_destaque) {
        this.imprimir_dados_destaque = imprimir_dados_destaque;
    }

    public String getRecibo_ferias_rateio() {
        return recibo_ferias_rateio;
    }

    public void setRecibo_ferias_rateio(String recibo_ferias_rateio) {
        this.recibo_ferias_rateio = recibo_ferias_rateio;
    }

    public String getRecibo_ferias_adiantamento() {
        return recibo_ferias_adiantamento;
    }

    public void setRecibo_ferias_adiantamento(String recibo_ferias_adiantamento) {
        this.recibo_ferias_adiantamento = recibo_ferias_adiantamento;
    }

    public String getRecibo_ferias_abono() {
        return recibo_ferias_abono;
    }

    public void setRecibo_ferias_abono(String recibo_ferias_abono) {
        this.recibo_ferias_abono = recibo_ferias_abono;
    }

    public String getDiretorio_sped() {
        return diretorio_sped;
    }

    public void setDiretorio_sped(String diretorio_sped) {
        this.diretorio_sped = diretorio_sped;
    }

    public String getEsp_estabelecimento() {
        return esp_estabelecimento;
    }

    public void setEsp_estabelecimento(String esp_estabelecimento) {
        this.esp_estabelecimento = esp_estabelecimento;
    }

    public String getOptante_liminar_aviso_previo() {
        return optante_liminar_aviso_previo;
    }

    public void setOptante_liminar_aviso_previo(String optante_liminar_aviso_previo) {
        this.optante_liminar_aviso_previo = optante_liminar_aviso_previo;
    }

    public String getMicro_emp_indiv_crh() {
        return micro_emp_indiv_crh;
    }

    public void setMicro_emp_indiv_crh(String micro_emp_indiv_crh) {
        this.micro_emp_indiv_crh = micro_emp_indiv_crh;
    }

    public String getEmpresa_cidada() {
        return empresa_cidada;
    }

    public void setEmpresa_cidada(String empresa_cidada) {
        this.empresa_cidada = empresa_cidada;
    }

    public String getEmail_rh() {
        return email_rh;
    }

    public void setEmail_rh(String email_rh) {
        this.email_rh = email_rh;
    }

    public String getDiretorio_dipj() {
        return diretorio_dipj;
    }

    public void setDiretorio_dipj(String diretorio_dipj) {
        this.diretorio_dipj = diretorio_dipj;
    }

    public int getUtiliza_controle_parcelas_auditoria() {
        return utiliza_controle_parcelas_auditoria;
    }

    public void setUtiliza_controle_parcelas_auditoria(int utiliza_controle_parcelas_auditoria) {
        this.utiliza_controle_parcelas_auditoria = utiliza_controle_parcelas_auditoria;
    }

    public String getHistorico_juros_pagos() {
        return historico_juros_pagos;
    }

    public void setHistorico_juros_pagos(String historico_juros_pagos) {
        this.historico_juros_pagos = historico_juros_pagos;
    }

    public String getHistorico_juros_recebidos() {
        return historico_juros_recebidos;
    }

    public void setHistorico_juros_recebidos(String historico_juros_recebidos) {
        this.historico_juros_recebidos = historico_juros_recebidos;
    }

    public String getHistorico_descontos_obtidos() {
        return historico_descontos_obtidos;
    }

    public void setHistorico_descontos_obtidos(String historico_descontos_obtidos) {
        this.historico_descontos_obtidos = historico_descontos_obtidos;
    }

    public String getHistorico_descontos_concedidos() {
        return historico_descontos_concedidos;
    }

    public void setHistorico_descontos_concedidos(String historico_descontos_concedidos) {
        this.historico_descontos_concedidos = historico_descontos_concedidos;
    }

    public String getHistorico_desp_bancaria_pagas() {
        return historico_desp_bancaria_pagas;
    }

    public void setHistorico_desp_bancaria_pagas(String historico_desp_bancaria_pagas) {
        this.historico_desp_bancaria_pagas = historico_desp_bancaria_pagas;
    }

    public String getHistorico_desp_bancaria_recebidas() {
        return historico_desp_bancaria_recebidas;
    }

    public void setHistorico_desp_bancaria_recebidas(String historico_desp_bancaria_recebidas) {
        this.historico_desp_bancaria_recebidas = historico_desp_bancaria_recebidas;
    }

    public String getHistorico_pagamentos() {
        return historico_pagamentos;
    }

    public void setHistorico_pagamentos(String historico_pagamentos) {
        this.historico_pagamentos = historico_pagamentos;
    }

    public String getHistorico_recebimentos() {
        return historico_recebimentos;
    }

    public void setHistorico_recebimentos(String historico_recebimentos) {
        this.historico_recebimentos = historico_recebimentos;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getDiretorio_tce_rs() {
        return diretorio_tce_rs;
    }

    public void setDiretorio_tce_rs(String diretorio_tce_rs) {
        this.diretorio_tce_rs = diretorio_tce_rs;
    }

    public int getCd_remessa_tce_rs() {
        return cd_remessa_tce_rs;
    }

    public void setCd_remessa_tce_rs(int cd_remessa_tce_rs) {
        this.cd_remessa_tce_rs = cd_remessa_tce_rs;
    }

    public String getEmitir_aviso_ferias() {
        return emitir_aviso_ferias;
    }

    public void setEmitir_aviso_ferias(String emitir_aviso_ferias) {
        this.emitir_aviso_ferias = emitir_aviso_ferias;
    }

    public String getDiretorio_seguro_desemprego() {
        return diretorio_seguro_desemprego;
    }

    public void setDiretorio_seguro_desemprego(String diretorio_seguro_desemprego) {
        this.diretorio_seguro_desemprego = diretorio_seguro_desemprego;
    }

    public int getCd_recolhimento_complemento_salarial() {
        return cd_recolhimento_complemento_salarial;
    }

    public void setCd_recolhimento_complemento_salarial(int cd_recolhimento_complemento_salarial) {
        this.cd_recolhimento_complemento_salarial = cd_recolhimento_complemento_salarial;
    }

    public int getCd_pagamento_gps_complemento_salarial() {
        return cd_pagamento_gps_complemento_salarial;
    }

    public void setCd_pagamento_gps_complemento_salarial(int cd_pagamento_gps_complemento_salarial) {
        this.cd_pagamento_gps_complemento_salarial = cd_pagamento_gps_complemento_salarial;
    }

    public String getOpcao_data_aviso_previo() {
        return opcao_data_aviso_previo;
    }

    public void setOpcao_data_aviso_previo(String opcao_data_aviso_previo) {
        this.opcao_data_aviso_previo = opcao_data_aviso_previo;
    }

    public int getForma_digitacao_caixa_banco_auditoria() {
        return forma_digitacao_caixa_banco_auditoria;
    }

    public void setForma_digitacao_caixa_banco_auditoria(int forma_digitacao_caixa_banco_auditoria) {
        this.forma_digitacao_caixa_banco_auditoria = forma_digitacao_caixa_banco_auditoria;
    }

    public String getHistorico_retencoes() {
        return historico_retencoes;
    }

    public void setHistorico_retencoes(String historico_retencoes) {
        this.historico_retencoes = historico_retencoes;
    }

    public int getGrau_padrao_relatorio() {
        return grau_padrao_relatorio;
    }

    public void setGrau_padrao_relatorio(int grau_padrao_relatorio) {
        this.grau_padrao_relatorio = grau_padrao_relatorio;
    }

    public int getEFD_atividade_preponderante() {
        return EFD_atividade_preponderante;
    }

    public void setEFD_atividade_preponderante(int EFD_atividade_preponderante) {
        this.EFD_atividade_preponderante = EFD_atividade_preponderante;
    }

    public int getEFD_natureza_pessoa_juridica() {
        return EFD_natureza_pessoa_juridica;
    }

    public void setEFD_natureza_pessoa_juridica(int EFD_natureza_pessoa_juridica) {
        this.EFD_natureza_pessoa_juridica = EFD_natureza_pessoa_juridica;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_empresa_escritorio() {
        return id_empresa_escritorio;
    }

    public void setId_empresa_escritorio(String id_empresa_escritorio) {
        this.id_empresa_escritorio = id_empresa_escritorio;
    }

    public Timestamp getData_hora_alteracao() {
        return data_hora_alteracao;
    }

    public void setData_hora_alteracao(Timestamp data_hora_alteracao) {
        this.data_hora_alteracao = data_hora_alteracao;
    }   
}
