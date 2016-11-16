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
public class DadosFuncionaisNG extends DadosFuncionario {
    int idRegistro;
    String codigoRegistro;
    boolean inddemissao;
    Timestamp dataDemissao;
    Timestamp dataAdmissao;
    Timestamp dataOpcaoFgts;
    int idTipoAdmissao;
    int idTipoContratacao;
    String numeroMatricula;
    int numeroDiasContratoExperiencia;
    int numeroDiasProrrogacaoContratoExperiencia;
    int idTipoAdmissaoCaged;
    String numeroContaFgts;
    int numeroDvContaFgts;
    boolean indAlteracaoSalarial;
    boolean indAlvaraJudicial;
    boolean indDireitoReciproco;
    String numeroRegistro;
    int idReajusteTipo;
    double valorReajuste;
    int idCategoriaSefip;
    Timestamp dataUltimoExameMedico;
    int numeroMesesIntervaloExameMedico;
    int idVinculoEmpregaticio;
    Timestamp dataConcessaoBeneficio;
    double percentualAdiantamento;
    String observacaoRegistro;
    Timestamp dataInicioPeriodoAquisitivoFeriasPendente;
    Timestamp dataFimPeriodoAquisitivoFeriasPendente;
    Timestamp dataVigenciaAlteracaoSalarial;
    boolean indRecolheInss;
    boolean indRecebe13Salario;
    boolean indComissionista;
    boolean indRecolhefgts;
    boolean indRecolhePrevidencia;
    boolean indRecolheIrrf;
    boolean indConsolidarFolhas;
    int idTipoComissionista;
    boolean indInformarrais;
    boolean indCentralizaInss;
    Timestamp dataUltimaContribuicaoSindical;
    int idDadosPessoa;
    String descricaoComplementoSalario;
    boolean indSindicalizado;
    boolean indRegraApropriacao;
    int idTipoRegra;
    String fichario;
    int idTipoDiaTrabalho;
    boolean indConsiderarTambemMesesFeriasAfastamento;
    boolean indConsiderarTambemMesAdmissao;
    int ordemVinculado;
    int idRegistroOrigem;
    Timestamp dataTransferencia;
    int indTipoInclusao;
    int indMotivoContratacao;
    String justificativaContratacao;
    String justificativaProrrogacao;
    int indAposentado;
    boolean indConsederarDiasMesFolhaFerias;
    int idRegistroSubstituido;
    int idTipoTransferenciaEntreEmpresa;
    int idOnusCessao;
    String observacaoSucessao;
    boolean indSeguroDesemprego;
    boolean indStatusCagedAdmissional;
    String statuRecebimento;
    boolean indConsiderarDiasMesApropriacaoHoras;
    boolean indDescontarContribuicaoSindicalreferenteAnoAdmissao;
    boolean inVisualizadaOnline;
    byte [] senhaVisualizaDadosOnline;
    boolean indContribuicaoSindical;
    boolean inddescontarsestsenat;
    boolean indConsiderarFolhaRescisao;
    int idTipoPagamentoSalario;
    int idTipoSalario;
    Timestamp dataIncio;
    Timestamp dataFim;
    Double salario;
    String descricaoComplemento;
    Double percentualVariacao;
    String motivoMovimento;
    Timestamp datadissidio;
    boolean inddissidio;
    int iddadossindicato;
    int idcargo;

    public int getIddadossindicato() {
        return iddadossindicato;
    }

    public void setIddadossindicato(int iddadossindicato) {
        this.iddadossindicato = iddadossindicato;
    }   

    public int getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }
    
    

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }    
    
    public int getIdTipoPagamentoSalario() {
        return idTipoPagamentoSalario;
    }

    public void setIdTipoPagamentoSalario(int idTipoPagamentoSalario) {
        this.idTipoPagamentoSalario = idTipoPagamentoSalario;
    }

    public int getIdTipoSalario() {
        return idTipoSalario;
    }

    public void setIdTipoSalario(int idTipoSalario) {
        this.idTipoSalario = idTipoSalario;
    }

    public Timestamp getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(Timestamp dataIncio) {
        this.dataIncio = dataIncio;
    }

    public Timestamp getDataFim() {
        return dataFim;
    }

    public void setDataFim(Timestamp dataFim) {
        this.dataFim = dataFim;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getDescricaoComplemento() {
        return descricaoComplemento;
    }

    public void setDescricaoComplemento(String descricaoComplemento) {
        this.descricaoComplemento = descricaoComplemento;
    }

    public Double getPercentualVariacao() {
        return percentualVariacao;
    }

    public void setPercentualVariacao(Double percentualVariacao) {
        this.percentualVariacao = percentualVariacao;
    }

    public String getMotivoMovimento() {
        return motivoMovimento;
    }

    public void setMotivoMovimento(String motivoMovimento) {
        this.motivoMovimento = motivoMovimento;
    }

    public Timestamp getDatadissidio() {
        return datadissidio;
    }

    public void setDatadissidio(Timestamp datadissidio) {
        this.datadissidio = datadissidio;
    }

    public boolean isInddissidio() {
        return inddissidio;
    }

    public void setInddissidio(boolean inddissidio) {
        this.inddissidio = inddissidio;
    }   

    public boolean isInddescontarsestsenat() {
        return inddescontarsestsenat;
    }

    public void setInddescontarsestsenat(boolean inddescontarsestsenat) {
        this.inddescontarsestsenat = inddescontarsestsenat;
    }

    
    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public boolean isInddemissao() {
        return inddemissao;
    }

    public void setInddemissao(boolean inddemissao) {
        this.inddemissao = inddemissao;
    }

    public Timestamp getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Timestamp dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public Timestamp getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Timestamp dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Timestamp getDataOpcaoFgts() {
        return dataOpcaoFgts;
    }

    public void setDataOpcaoFgts(Timestamp dataOpcaoFgts) {
        this.dataOpcaoFgts = dataOpcaoFgts;
    }

    public int getIdTipoAdmissao() {
        return idTipoAdmissao;
    }

    public void setIdTipoAdmissao(int idTipoAdmissao) {
        this.idTipoAdmissao = idTipoAdmissao;
    }

    public int getIdTipoContratacao() {
        return idTipoContratacao;
    }

    public void setIdTipoContratacao(int idTipoContratacao) {
        this.idTipoContratacao = idTipoContratacao;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public int getNumeroDiasContratoExperiencia() {
        return numeroDiasContratoExperiencia;
    }

    public void setNumeroDiasContratoExperiencia(int numeroDiasContratoExperiencia) {
        this.numeroDiasContratoExperiencia = numeroDiasContratoExperiencia;
    }

    public int getNumeroDiasProrrogacaoContratoExperiencia() {
        return numeroDiasProrrogacaoContratoExperiencia;
    }

    public void setNumeroDiasProrrogacaoContratoExperiencia(int numeroDiasProrrogacaoContratoExperiencia) {
        this.numeroDiasProrrogacaoContratoExperiencia = numeroDiasProrrogacaoContratoExperiencia;
    }

    public int getIdTipoAdmissaoCaged() {
        return idTipoAdmissaoCaged;
    }

    public void setIdTipoAdmissaoCaged(int idTipoAdmissaoCaged) {
        this.idTipoAdmissaoCaged = idTipoAdmissaoCaged;
    }

    public String getNumeroContaFgts() {
        return numeroContaFgts;
    }

    public void setNumeroContaFgts(String numeroContaFgts) {
        this.numeroContaFgts = numeroContaFgts;
    }

    public int getNumeroDvContaFgts() {
        return numeroDvContaFgts;
    }

    public void setNumeroDvContaFgts(int numeroDvContaFgts) {
        this.numeroDvContaFgts = numeroDvContaFgts;
    }

    public boolean isIndAlteracaoSalarial() {
        return indAlteracaoSalarial;
    }

    public void setIndAlteracaoSalarial(boolean indAlteracaoSalarial) {
        this.indAlteracaoSalarial = indAlteracaoSalarial;
    }

    public boolean isIndAlvaraJudicial() {
        return indAlvaraJudicial;
    }

    public void setIndAlvaraJudicial(boolean indAlvaraJudicial) {
        this.indAlvaraJudicial = indAlvaraJudicial;
    }

    public boolean isIndDireitoReciproco() {
        return indDireitoReciproco;
    }

    public void setIndDireitoReciproco(boolean indDireitoReciproco) {
        this.indDireitoReciproco = indDireitoReciproco;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public int getIdReajusteTipo() {
        return idReajusteTipo;
    }

    public void setIdReajusteTipo(int idReajusteTipo) {
        this.idReajusteTipo = idReajusteTipo;
    }

    public double getValorReajuste() {
        return valorReajuste;
    }

    public void setValorReajuste(double valorReajuste) {
        this.valorReajuste = valorReajuste;
    }

    public int getIdCategoriaSefip() {
        return idCategoriaSefip;
    }

    public void setIdCategoriaSefip(int idCategoriaSefip) {
        this.idCategoriaSefip = idCategoriaSefip;
    }

    public Timestamp getDataUltimoExameMedico() {
        return dataUltimoExameMedico;
    }

    public void setDataUltimoExameMedico(Timestamp dataUltimoExameMedico) {
        this.dataUltimoExameMedico = dataUltimoExameMedico;
    }

    public int getNumeroMesesIntervaloExameMedico() {
        return numeroMesesIntervaloExameMedico;
    }

    public void setNumeroMesesIntervaloExameMedico(int numeroMesesIntervaloExameMedico) {
        this.numeroMesesIntervaloExameMedico = numeroMesesIntervaloExameMedico;
    }

    public int getIdVinculoEmpregaticio() {
        return idVinculoEmpregaticio;
    }

    public void setIdVinculoEmpregaticio(int idVinculoEmpregaticio) {
        this.idVinculoEmpregaticio = idVinculoEmpregaticio;
    }

    public Timestamp getDataConcessaoBeneficio() {
        return dataConcessaoBeneficio;
    }

    public void setDataConcessaoBeneficio(Timestamp dataConcessaoBeneficio) {
        this.dataConcessaoBeneficio = dataConcessaoBeneficio;
    }

    public double getPercentualAdiantamento() {
        return percentualAdiantamento;
    }

    public void setPercentualAdiantamento(double percentualAdiantamento) {
        this.percentualAdiantamento = percentualAdiantamento;
    }

    public String getObservacaoRegistro() {
        return observacaoRegistro;
    }

    public void setObservacaoRegistro(String observacaoRegistro) {
        this.observacaoRegistro = observacaoRegistro;
    }

    public Timestamp getDataInicioPeriodoAquisitivoFeriasPendente() {
        return dataInicioPeriodoAquisitivoFeriasPendente;
    }

    public void setDataInicioPeriodoAquisitivoFeriasPendente(Timestamp dataInicioPeriodoAquisitivoFeriasPendente) {
        this.dataInicioPeriodoAquisitivoFeriasPendente = dataInicioPeriodoAquisitivoFeriasPendente;
    }

    public Timestamp getDataFimPeriodoAquisitivoFeriasPendente() {
        return dataFimPeriodoAquisitivoFeriasPendente;
    }

    public void setDataFimPeriodoAquisitivoFeriasPendente(Timestamp dataFimPeriodoAquisitivoFeriasPendente) {
        this.dataFimPeriodoAquisitivoFeriasPendente = dataFimPeriodoAquisitivoFeriasPendente;
    }

    public Timestamp getDataVigenciaAlteracaoSalarial() {
        return dataVigenciaAlteracaoSalarial;
    }

    public void setDataVigenciaAlteracaoSalarial(Timestamp dataVigenciaAlteracaoSalarial) {
        this.dataVigenciaAlteracaoSalarial = dataVigenciaAlteracaoSalarial;
    }

    public boolean isIndRecolheInss() {
        return indRecolheInss;
    }

    public void setIndRecolheInss(boolean indRecolheInss) {
        this.indRecolheInss = indRecolheInss;
    }

    public boolean isIndRecebe13Salario() {
        return indRecebe13Salario;
    }

    public void setIndRecebe13Salario(boolean indRecebe13Salario) {
        this.indRecebe13Salario = indRecebe13Salario;
    }

    public boolean isIndComissionista() {
        return indComissionista;
    }

    public void setIndComissionista(boolean indComissionista) {
        this.indComissionista = indComissionista;
    }

    public boolean isIndRecolhefgts() {
        return indRecolhefgts;
    }

    public void setIndRecolhefgts(boolean indRecolhefgts) {
        this.indRecolhefgts = indRecolhefgts;
    }

    public boolean isIndRecolhePrevidencia() {
        return indRecolhePrevidencia;
    }

    public void setIndRecolhePrevidencia(boolean indRecolhePrevidencia) {
        this.indRecolhePrevidencia = indRecolhePrevidencia;
    }

    public boolean isIndRecolheIrrf() {
        return indRecolheIrrf;
    }

    public void setIndRecolheIrrf(boolean indRecolheIrrf) {
        this.indRecolheIrrf = indRecolheIrrf;
    }

    public boolean isIndConsolidarFolhas() {
        return indConsolidarFolhas;
    }

    public void setIndConsolidarFolhas(boolean indConsolidarFolhas) {
        this.indConsolidarFolhas = indConsolidarFolhas;
    }

    public int getIdTipoComissionista() {
        return idTipoComissionista;
    }

    public void setIdTipoComissionista(int idTipoComissionista) {
        this.idTipoComissionista = idTipoComissionista;
    }

    public boolean isIndInformarrais() {
        return indInformarrais;
    }

    public void setIndInformarrais(boolean indInformarrais) {
        this.indInformarrais = indInformarrais;
    }

    public boolean isIndCentralizaInss() {
        return indCentralizaInss;
    }

    public void setIndCentralizaInss(boolean indCentralizaInss) {
        this.indCentralizaInss = indCentralizaInss;
    }

    public Timestamp getDataUltimaContribuicaoSindical() {
        return dataUltimaContribuicaoSindical;
    }

    public void setDataUltimaContribuicaoSindical(Timestamp dataUltimaContribuicaoSindical) {
        this.dataUltimaContribuicaoSindical = dataUltimaContribuicaoSindical;
    }

    public int getIdDadosPessoa() {
        return idDadosPessoa;
    }

    public void setIdDadosPessoa(int idDadosPessoa) {
        this.idDadosPessoa = idDadosPessoa;
    }

    public String getDescricaoComplementoSalario() {
        return descricaoComplementoSalario;
    }

    public void setDescricaoComplementoSalario(String descricaoComplementoSalario) {
        this.descricaoComplementoSalario = descricaoComplementoSalario;
    }

    public boolean isIndSindicalizado() {
        return indSindicalizado;
    }

    public void setIndSindicalizado(boolean indSindicalizado) {
        this.indSindicalizado = indSindicalizado;
    }

    public boolean isIndRegraApropriacao() {
        return indRegraApropriacao;
    }

    public void setIndRegraApropriacao(boolean indRegraApropriacao) {
        this.indRegraApropriacao = indRegraApropriacao;
    }

    public int getIdTipoRegra() {
        return idTipoRegra;
    }

    public void setIdTipoRegra(int idTipoRegra) {
        this.idTipoRegra = idTipoRegra;
    }

    public String getFichario() {
        return fichario;
    }

    public void setFichario(String fichario) {
        this.fichario = fichario;
    }

    public int getIdTipoDiaTrabalho() {
        return idTipoDiaTrabalho;
    }

    public void setIdTipoDiaTrabalho(int idTipoDiaTrabalho) {
        this.idTipoDiaTrabalho = idTipoDiaTrabalho;
    }

    public boolean isIndConsiderarTambemMesesFeriasAfastamento() {
        return indConsiderarTambemMesesFeriasAfastamento;
    }

    public void setIndConsiderarTambemMesesFeriasAfastamento(boolean indConsiderarTambemMesesFeriasAfastamento) {
        this.indConsiderarTambemMesesFeriasAfastamento = indConsiderarTambemMesesFeriasAfastamento;
    }

    public boolean isIndConsiderarTambemMesAdmissao() {
        return indConsiderarTambemMesAdmissao;
    }

    public void setIndConsiderarTambemMesAdmissao(boolean indConsiderarTambemMesAdmissao) {
        this.indConsiderarTambemMesAdmissao = indConsiderarTambemMesAdmissao;
    }

    public int getOrdemVinculado() {
        return ordemVinculado;
    }

    public void setOrdemVinculado(int ordemVinculado) {
        this.ordemVinculado = ordemVinculado;
    }

    public int getIdRegistroOrigem() {
        return idRegistroOrigem;
    }

    public void setIdRegistroOrigem(int idRegistroOrigem) {
        this.idRegistroOrigem = idRegistroOrigem;
    }

    public Timestamp getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(Timestamp dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public int getIndTipoInclusao() {
        return indTipoInclusao;
    }

    public void setIndTipoInclusao(int indTipoInclusao) {
        this.indTipoInclusao = indTipoInclusao;
    }

    public int getIndMotivoContratacao() {
        return indMotivoContratacao;
    }

    public void setIndMotivoContratacao(int indMotivoContratacao) {
        this.indMotivoContratacao = indMotivoContratacao;
    }

    public String getJustificativaContratacao() {
        return justificativaContratacao;
    }

    public void setJustificativaContratacao(String justificativaContratacao) {
        this.justificativaContratacao = justificativaContratacao;
    }

    public String getJustificativaProrrogacao() {
        return justificativaProrrogacao;
    }

    public void setJustificativaProrrogacao(String justificativaProrrogacao) {
        this.justificativaProrrogacao = justificativaProrrogacao;
    }

    public int getIndAposentado() {
        return indAposentado;
    }

    public void setIndAposentado(int indAposentado) {
        this.indAposentado = indAposentado;
    }

    public boolean isIndConsederarDiasMesFolhaFerias() {
        return indConsederarDiasMesFolhaFerias;
    }

    public void setIndConsederarDiasMesFolhaFerias(boolean indConsederarDiasMesFolhaFerias) {
        this.indConsederarDiasMesFolhaFerias = indConsederarDiasMesFolhaFerias;
    }

    public int getIdRegistroSubstituido() {
        return idRegistroSubstituido;
    }

    public void setIdRegistroSubstituido(int idRegistroSubstituido) {
        this.idRegistroSubstituido = idRegistroSubstituido;
    }

    public int getIdTipoTransferenciaEntreEmpresa() {
        return idTipoTransferenciaEntreEmpresa;
    }

    public void setIdTipoTransferenciaEntreEmpresa(int idTipoTransferenciaEntreEmpresa) {
        this.idTipoTransferenciaEntreEmpresa = idTipoTransferenciaEntreEmpresa;
    }

    public int getIdOnusCessao() {
        return idOnusCessao;
    }

    public void setIdOnusCessao(int idOnusCessao) {
        this.idOnusCessao = idOnusCessao;
    }

    public String getObservacaoSucessao() {
        return observacaoSucessao;
    }

    public void setObservacaoSucessao(String observacaoSucessao) {
        this.observacaoSucessao = observacaoSucessao;
    }

    public boolean isIndSeguroDesemprego() {
        return indSeguroDesemprego;
    }

    public void setIndSeguroDesemprego(boolean indSeguroDesemprego) {
        this.indSeguroDesemprego = indSeguroDesemprego;
    }

    public boolean isIndStatusCagedAdmissional() {
        return indStatusCagedAdmissional;
    }

    public void setIndStatusCagedAdmissional(boolean indStatusCagedAdmissional) {
        this.indStatusCagedAdmissional = indStatusCagedAdmissional;
    }

    public String getStatuRecebimento() {
        return statuRecebimento;
    }

    public void setStatuRecebimento(String statuRecebimento) {
        this.statuRecebimento = statuRecebimento;
    }

    public boolean isIndConsiderarDiasMesApropriacaoHoras() {
        return indConsiderarDiasMesApropriacaoHoras;
    }

    public void setIndConsiderarDiasMesApropriacaoHoras(boolean indConsiderarDiasMesApropriacaoHoras) {
        this.indConsiderarDiasMesApropriacaoHoras = indConsiderarDiasMesApropriacaoHoras;
    }

    public boolean isIndDescontarContribuicaoSindicalreferenteAnoAdmissao() {
        return indDescontarContribuicaoSindicalreferenteAnoAdmissao;
    }

    public void setIndDescontarContribuicaoSindicalreferenteAnoAdmissao(boolean indDescontarContribuicaoSindicalreferenteAnoAdmissao) {
        this.indDescontarContribuicaoSindicalreferenteAnoAdmissao = indDescontarContribuicaoSindicalreferenteAnoAdmissao;
    }

    public boolean isInVisualizadaOnline() {
        return inVisualizadaOnline;
    }

    public void setInVisualizadaOnline(boolean inVisualizadaOnline) {
        this.inVisualizadaOnline = inVisualizadaOnline;
    }

    public byte[] getSenhaVisualizaDadosOnline() {
        return senhaVisualizaDadosOnline;
    }

    public void setSenhaVisualizaDadosOnline(byte[] senhaVisualizaDadosOnline) {
        this.senhaVisualizaDadosOnline = senhaVisualizaDadosOnline;
    }

    public boolean isIndContribuicaoSindical() {
        return indContribuicaoSindical;
    }

    public void setIndContribuicaoSindical(boolean indContribuicaoSindical) {
        this.indContribuicaoSindical = indContribuicaoSindical;
    }

    public boolean isIndConsiderarFolhaRescisao() {
        return indConsiderarFolhaRescisao;
    }

    public void setIndConsiderarFolhaRescisao(boolean indConsiderarFolhaRescisao) {
        this.indConsiderarFolhaRescisao = indConsiderarFolhaRescisao;
    }    
}
