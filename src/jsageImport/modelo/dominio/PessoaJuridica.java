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
public class PessoaJuridica extends PessoaNG {
    
    String nomeFantasia;
    String cnpj;
    String inscricaoEstadual;
    String inscricaoMunicipal;
    String objetoSocial;
    String numeroRegistoJunta;
    Timestamp dataRegistroJunta;
    String nomeJunta;
    Timestamp dataInicioAtividade;
    Timestamp dataTerminoSociedade;
    Timestamp dataFimAtividade;
    double capitalSocialInicial;
    Timestamp dataFundacao;
    int numeroProprietarios;
    String nirc;
    int idNaturezaJuridica;
    String nomeAbreviado;
    int idQualificacaoEmpresa;
    String codigoUfRama;
    int idTipoOrgaoPublico;
    int idTipoEntidade;
    Timestamp dataInicioInscricaoEstadual;
    String cnpjFormatado;
    int idIdentificadorInscricaoEstadualNfe;
    boolean indEmpresaExterna;

    public Timestamp getDataTerminoSociedade() {
        return dataTerminoSociedade;
    }

    public void setDataTerminoSociedade(Timestamp dataTerminoSociedade) {
        this.dataTerminoSociedade = dataTerminoSociedade;
    }
    
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getObjetoSocial() {
        return objetoSocial;
    }

    public void setObjetoSocial(String objetoSocial) {
        this.objetoSocial = objetoSocial;
    }

    public String getNumeroRegistoJunta() {
        return numeroRegistoJunta;
    }

    public void setNumeroRegistoJunta(String numeroRegistoJunta) {
        this.numeroRegistoJunta = numeroRegistoJunta;
    }

    public Timestamp getDataRegistroJunta() {
        return dataRegistroJunta;
    }

    public void setDataRegistroJunta(Timestamp dataRegistroJunta) {
        this.dataRegistroJunta = dataRegistroJunta;
    }

    public String getNomeJunta() {
        return nomeJunta;
    }

    public void setNomeJunta(String nomeJunta) {
        this.nomeJunta = nomeJunta;
    }

    public Timestamp getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public void setDataInicioAtividade(Timestamp dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public Timestamp getDataFimAtividade() {
        return dataFimAtividade;
    }

    public void setDataFimAtividade(Timestamp dataFimAtividade) {
        this.dataFimAtividade = dataFimAtividade;
    }

    public double getCapitalSocialInicial() {
        return capitalSocialInicial;
    }

    public void setCapitalSocialInicial(double capitalSocialInicial) {
        this.capitalSocialInicial = capitalSocialInicial;
    }

    public Timestamp getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Timestamp dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getNumeroProprietarios() {
        return numeroProprietarios;
    }

    public void setNumeroProprietarios(int numeroProprietarios) {
        this.numeroProprietarios = numeroProprietarios;
    }

    public String getNirc() {
        return nirc;
    }

    public void setNirc(String nirc) {
        this.nirc = nirc;
    }

    public int getIdNaturezaJuridica() {
        return idNaturezaJuridica;
    }

    public void setIdNaturezaJuridica(int idNaturezaJuridica) {
        this.idNaturezaJuridica = idNaturezaJuridica;
    }

    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    public void setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
    }

    public int getIdQualificacaoEmpresa() {
        return idQualificacaoEmpresa;
    }

    public void setIdQualificacaoEmpresa(int idQualificacaoEmpresa) {
        this.idQualificacaoEmpresa = idQualificacaoEmpresa;
    }

    public String getCodigoUfRama() {
        return codigoUfRama;
    }

    public void setCodigoUfRama(String codigoUfRama) {
        this.codigoUfRama = codigoUfRama;
    }

    public int getIdTipoOrgaoPublico() {
        return idTipoOrgaoPublico;
    }

    public void setIdTipoOrgaoPublico(int idTipoOrgaoPublico) {
        this.idTipoOrgaoPublico = idTipoOrgaoPublico;
    }

    public int getIdTipoEntidade() {
        return idTipoEntidade;
    }

    public void setIdTipoEntidade(int idTipoEntidade) {
        this.idTipoEntidade = idTipoEntidade;
    }

    public Timestamp getDataInicioInscricaoEstadual() {
        return dataInicioInscricaoEstadual;
    }

    public void setDataInicioInscricaoEstadual(Timestamp dataInicioInscricaoEstadual) {
        this.dataInicioInscricaoEstadual = dataInicioInscricaoEstadual;
    }

    public String getCnpjFormatado() {
        return cnpjFormatado;
    }

    public void setCnpjFormatado(String cnpjFormatado) {
        this.cnpjFormatado = cnpjFormatado;
    }

    public int getIdIdentificadorInscricaoEstadualNfe() {
        return idIdentificadorInscricaoEstadualNfe;
    }

    public void setIdIdentificadorInscricaoEstadualNfe(int idIdentificadorInscricaoEstadualNfe) {
        this.idIdentificadorInscricaoEstadualNfe = idIdentificadorInscricaoEstadualNfe;
    }

    public boolean isIndEmpresaExterna() {
        return indEmpresaExterna;
    }

    public void setIndEmpresaExterna(boolean indEmpresaExterna) {
        this.indEmpresaExterna = indEmpresaExterna;
    } 
       
}
