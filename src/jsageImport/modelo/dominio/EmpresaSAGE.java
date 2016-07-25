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
    String mascara_plano_contas;
    int num_niveis_plano_contas;
    String num_digitos_plano_contas;
    String comprimento_plano_contas;
    int seq_conta;
    int opcao_caixa;
    String libera_lote_aberto;
    String lanca_lote_lib;
    String opcao_conta_cliente;
    String opcao_conta_fornecedor;
    int op_calculo_horista;
    String possui_tomador;
    String pagamento_mes;
    String prolabore_mes;
    String cargos_salarios;
    String produtor_rural;
    String naooptante_liminar;
    String optante_liminar;
    String utiliza_conta_clifor;
    String status;
    String integracao_csc;
    String simples_crh;
    String faturamento_simples_crh;
    String microempresa_crh;
    String epp_crh;
    String importa_lote_diferenca;
    String utiliza_controle_clifor;
    int opcao_seguro_desemprego;
    String descontar_13_negativo;
    short op_calculo_numero_dias;
    String EntSemFinsLucrativos;
    String recolhimento_contr_sindical_centralizada;
    int calc30_opcao_salario;
    String calc30_opcao_salario_fevereiro;
    int calc30_opcao_dias;
    String opcao_historico_contabil;
    String opcao_data_lancamento_contabil;
    String optante_liminar_aviso_previo;
    String micro_emp_indiv_crh;
    String empresa_cidada;
    int utiliza_controle_parcelas_auditoria;
    String emitir_aviso_ferias;
    String opcao_data_aviso_previo;
    int forma_digitacao_caixa_banco_auditoria;
    String historico_retencoes;    

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

    public String getOpcao_conta_cliente() {
        return opcao_conta_cliente;
    }

    public void setOpcao_conta_cliente(String opcao_conta_cliente) {
        this.opcao_conta_cliente = opcao_conta_cliente;
    }

    public String getOpcao_conta_fornecedor() {
        return opcao_conta_fornecedor;
    }

    public void setOpcao_conta_fornecedor(String opcao_conta_fornecedor) {
        this.opcao_conta_fornecedor = opcao_conta_fornecedor;
    }

    public int getOp_calculo_horista() {
        return op_calculo_horista;
    }

    public void setOp_calculo_horista(int op_calculo_horista) {
        this.op_calculo_horista = op_calculo_horista;
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

    public String getUtiliza_conta_clifor() {
        return utiliza_conta_clifor;
    }

    public void setUtiliza_conta_clifor(String utiliza_conta_clifor) {
        this.utiliza_conta_clifor = utiliza_conta_clifor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getEpp_crh() {
        return epp_crh;
    }

    public void setEpp_crh(String epp_crh) {
        this.epp_crh = epp_crh;
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

    public String getOpcao_historico_contabil() {
        return opcao_historico_contabil;
    }

    public void setOpcao_historico_contabil(String opcao_historico_contabil) {
        this.opcao_historico_contabil = opcao_historico_contabil;
    }

    public String getOpcao_data_lancamento_contabil() {
        return opcao_data_lancamento_contabil;
    }

    public void setOpcao_data_lancamento_contabil(String opcao_data_lancamento_contabil) {
        this.opcao_data_lancamento_contabil = opcao_data_lancamento_contabil;
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

    public int getUtiliza_controle_parcelas_auditoria() {
        return utiliza_controle_parcelas_auditoria;
    }

    public void setUtiliza_controle_parcelas_auditoria(int utiliza_controle_parcelas_auditoria) {
        this.utiliza_controle_parcelas_auditoria = utiliza_controle_parcelas_auditoria;
    }

    public String getEmitir_aviso_ferias() {
        return emitir_aviso_ferias;
    }

    public void setEmitir_aviso_ferias(String emitir_aviso_ferias) {
        this.emitir_aviso_ferias = emitir_aviso_ferias;
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
}