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
public class Estabelecimento {
    
    int cd_estabelecimento;
    String razao;
    String fantasia;
    String endereco;
    int numero;
    String bairro;
    String cidade;
    String uf;
    short ddd_telefone;
    int telefone;
    String natureza_juridica;
    short categoria;
    String cnpj_cpf;
    String local_registro;
    String nome_titular;
    String denom_titular;
    String cpf_titular;
    short cd_responsavel_estabelecimento;
    String salario_educacao;
    String denominacao_pagina_csc;
    String contribuinte_icms;
    String contribuinte_ipi;
    String contribuinte_iss;
    String opcao_ipi;
    int compensacao_tributos_retido;
    double perc_cs_venda;
    String denominacao_pagina_cef;
    String substituto_tributario;
    String utiliza_ecf;
    short tributacao;
    short qualificacao;
    Timestamp dt_inicio_atividade;
    String antecipar_irpj_csll;
    String calcular_excedente_antecipacao_irpj_csll;
    short parcelamento_irpj_csll;
    String tipo_estabelecimento;
    String instituicao_financeira;
    String status;
    String razao_completa;
    String estatuto_microempresa;
    short opcao_vencimento_darf;
    String beneficiario_prodepe;
    String difere_icms_rs;
    int opcao_super;
    double vl_super_icms_fixo;
    double vl_super_iss_fixo;
    String protocolos_baixa_guias;
    double vl_minimo_retencao_pis_cofins_csll;
    double vl_minimo_retencao_irf;
    int cd_classificacao;
    double aliquota_fecp;
    int natureza_juridica_ecf;
    int tipo_entidade_ecf;
    int tipo_plano_ecf;
    int coeficiente_ciap_opcao;

    public int getCd_estabelecimento() {
        return cd_estabelecimento;
    }

    public void setCd_estabelecimento(int cd_estabelecimento) {
        this.cd_estabelecimento = cd_estabelecimento;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public short getDdd_telefone() {
        return ddd_telefone;
    }

    public void setDdd_telefone(short ddd_telefone) {
        this.ddd_telefone = ddd_telefone;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getNatureza_juridica() {
        return natureza_juridica;
    }

    public void setNatureza_juridica(String natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
    }

    public short getCategoria() {
        return categoria;
    }

    public void setCategoria(short categoria) {
        this.categoria = categoria;
    }

    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

    public String getLocal_registro() {
        return local_registro;
    }

    public void setLocal_registro(String local_registro) {
        this.local_registro = local_registro;
    }

    public String getNome_titular() {
        return nome_titular;
    }

    public void setNome_titular(String nome_titular) {
        this.nome_titular = nome_titular;
    }

    public String getDenom_titular() {
        return denom_titular;
    }

    public void setDenom_titular(String denom_titular) {
        this.denom_titular = denom_titular;
    }

    public String getCpf_titular() {
        return cpf_titular;
    }

    public void setCpf_titular(String cpf_titular) {
        this.cpf_titular = cpf_titular;
    }

    public short getCd_responsavel_estabelecimento() {
        return cd_responsavel_estabelecimento;
    }

    public void setCd_responsavel_estabelecimento(short cd_responsavel_estabelecimento) {
        this.cd_responsavel_estabelecimento = cd_responsavel_estabelecimento;
    }

    public String getSalario_educacao() {
        return salario_educacao;
    }

    public void setSalario_educacao(String salario_educacao) {
        this.salario_educacao = salario_educacao;
    }

    public String getDenominacao_pagina_csc() {
        return denominacao_pagina_csc;
    }

    public void setDenominacao_pagina_csc(String denominacao_pagina_csc) {
        this.denominacao_pagina_csc = denominacao_pagina_csc;
    }

    public String getContribuinte_icms() {
        return contribuinte_icms;
    }

    public void setContribuinte_icms(String contribuinte_icms) {
        this.contribuinte_icms = contribuinte_icms;
    }

    public String getContribuinte_ipi() {
        return contribuinte_ipi;
    }

    public void setContribuinte_ipi(String contribuinte_ipi) {
        this.contribuinte_ipi = contribuinte_ipi;
    }

    public String getContribuinte_iss() {
        return contribuinte_iss;
    }

    public void setContribuinte_iss(String contribuinte_iss) {
        this.contribuinte_iss = contribuinte_iss;
    }

    public String getOpcao_ipi() {
        return opcao_ipi;
    }

    public void setOpcao_ipi(String opcao_ipi) {
        this.opcao_ipi = opcao_ipi;
    }

    public int getCompensacao_tributos_retido() {
        return compensacao_tributos_retido;
    }

    public void setCompensacao_tributos_retido(int compensacao_tributos_retido) {
        this.compensacao_tributos_retido = compensacao_tributos_retido;
    }

    public double getPerc_cs_venda() {
        return perc_cs_venda;
    }

    public void setPerc_cs_venda(double perc_cs_venda) {
        this.perc_cs_venda = perc_cs_venda;
    }

    public String getDenominacao_pagina_cef() {
        return denominacao_pagina_cef;
    }

    public void setDenominacao_pagina_cef(String denominacao_pagina_cef) {
        this.denominacao_pagina_cef = denominacao_pagina_cef;
    }

    public String getSubstituto_tributario() {
        return substituto_tributario;
    }

    public void setSubstituto_tributario(String substituto_tributario) {
        this.substituto_tributario = substituto_tributario;
    }

    public String getUtiliza_ecf() {
        return utiliza_ecf;
    }

    public void setUtiliza_ecf(String utiliza_ecf) {
        this.utiliza_ecf = utiliza_ecf;
    }

    public short getTributacao() {
        return tributacao;
    }

    public void setTributacao(short tributacao) {
        this.tributacao = tributacao;
    }

    public short getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(short qualificacao) {
        this.qualificacao = qualificacao;
    }

    public Timestamp getDt_inicio_atividade() {
        return dt_inicio_atividade;
    }

    public void setDt_inicio_atividade(Timestamp dt_inicio_atividade) {
        this.dt_inicio_atividade = dt_inicio_atividade;
    }

    public String getAntecipar_irpj_csll() {
        return antecipar_irpj_csll;
    }

    public void setAntecipar_irpj_csll(String antecipar_irpj_csll) {
        this.antecipar_irpj_csll = antecipar_irpj_csll;
    }

    public String getCalcular_excedente_antecipacao_irpj_csll() {
        return calcular_excedente_antecipacao_irpj_csll;
    }

    public void setCalcular_excedente_antecipacao_irpj_csll(String calcular_excedente_antecipacao_irpj_csll) {
        this.calcular_excedente_antecipacao_irpj_csll = calcular_excedente_antecipacao_irpj_csll;
    }

    public short getParcelamento_irpj_csll() {
        return parcelamento_irpj_csll;
    }

    public void setParcelamento_irpj_csll(short parcelamento_irpj_csll) {
        this.parcelamento_irpj_csll = parcelamento_irpj_csll;
    }

    public String getTipo_estabelecimento() {
        return tipo_estabelecimento;
    }

    public void setTipo_estabelecimento(String tipo_estabelecimento) {
        this.tipo_estabelecimento = tipo_estabelecimento;
    }

    public String getInstituicao_financeira() {
        return instituicao_financeira;
    }

    public void setInstituicao_financeira(String instituicao_financeira) {
        this.instituicao_financeira = instituicao_financeira;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRazao_completa() {
        return razao_completa;
    }

    public void setRazao_completa(String razao_completa) {
        this.razao_completa = razao_completa;
    }

    public String getEstatuto_microempresa() {
        return estatuto_microempresa;
    }

    public void setEstatuto_microempresa(String estatuto_microempresa) {
        this.estatuto_microempresa = estatuto_microempresa;
    }

    public short getOpcao_vencimento_darf() {
        return opcao_vencimento_darf;
    }

    public void setOpcao_vencimento_darf(short opcao_vencimento_darf) {
        this.opcao_vencimento_darf = opcao_vencimento_darf;
    }

    public String getBeneficiario_prodepe() {
        return beneficiario_prodepe;
    }

    public void setBeneficiario_prodepe(String beneficiario_prodepe) {
        this.beneficiario_prodepe = beneficiario_prodepe;
    }

    public String getDifere_icms_rs() {
        return difere_icms_rs;
    }

    public void setDifere_icms_rs(String difere_icms_rs) {
        this.difere_icms_rs = difere_icms_rs;
    }

    public int getOpcao_super() {
        return opcao_super;
    }

    public void setOpcao_super(int opcao_super) {
        this.opcao_super = opcao_super;
    }

    public double getVl_super_icms_fixo() {
        return vl_super_icms_fixo;
    }

    public void setVl_super_icms_fixo(double vl_super_icms_fixo) {
        this.vl_super_icms_fixo = vl_super_icms_fixo;
    }

    public double getVl_super_iss_fixo() {
        return vl_super_iss_fixo;
    }

    public void setVl_super_iss_fixo(double vl_super_iss_fixo) {
        this.vl_super_iss_fixo = vl_super_iss_fixo;
    }

    public String getProtocolos_baixa_guias() {
        return protocolos_baixa_guias;
    }

    public void setProtocolos_baixa_guias(String protocolos_baixa_guias) {
        this.protocolos_baixa_guias = protocolos_baixa_guias;
    }

    public double getVl_minimo_retencao_pis_cofins_csll() {
        return vl_minimo_retencao_pis_cofins_csll;
    }

    public void setVl_minimo_retencao_pis_cofins_csll(double vl_minimo_retencao_pis_cofins_csll) {
        this.vl_minimo_retencao_pis_cofins_csll = vl_minimo_retencao_pis_cofins_csll;
    }

    public double getVl_minimo_retencao_irf() {
        return vl_minimo_retencao_irf;
    }

    public void setVl_minimo_retencao_irf(double vl_minimo_retencao_irf) {
        this.vl_minimo_retencao_irf = vl_minimo_retencao_irf;
    }

    public int getCd_classificacao() {
        return cd_classificacao;
    }

    public void setCd_classificacao(int cd_classificacao) {
        this.cd_classificacao = cd_classificacao;
    }

    public double getAliquota_fecp() {
        return aliquota_fecp;
    }

    public void setAliquota_fecp(double aliquota_fecp) {
        this.aliquota_fecp = aliquota_fecp;
    }

    public int getNatureza_juridica_ecf() {
        return natureza_juridica_ecf;
    }

    public void setNatureza_juridica_ecf(int natureza_juridica_ecf) {
        this.natureza_juridica_ecf = natureza_juridica_ecf;
    }

    public int getTipo_entidade_ecf() {
        return tipo_entidade_ecf;
    }

    public void setTipo_entidade_ecf(int tipo_entidade_ecf) {
        this.tipo_entidade_ecf = tipo_entidade_ecf;
    }

    public int getTipo_plano_ecf() {
        return tipo_plano_ecf;
    }

    public void setTipo_plano_ecf(int tipo_plano_ecf) {
        this.tipo_plano_ecf = tipo_plano_ecf;
    }

    public int getCoeficiente_ciap_opcao() {
        return coeficiente_ciap_opcao;
    }

    public void setCoeficiente_ciap_opcao(int coeficiente_ciap_opcao) {
        this.coeficiente_ciap_opcao = coeficiente_ciap_opcao;
    }
    
    
    
}
