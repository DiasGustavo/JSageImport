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
public class DependenteSAGE {
    int cdEmpresa;
    int cdFuncionario;
    int cdDependente;
    Timestamp dtInclusao;
    String nome;
    int tipoParentesco;
    String descricaoParentesco;
    Timestamp dtNascimento;
    String suspendeSf;
    String observacao;
    String cpfDependente;
    int esocialTipoParentesco;
    String esocialPensionista;
    int esocialEventoPensao;
    double esocialPercentualPensao;

    public int getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(int cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public int getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(int cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public int getCdDependente() {
        return cdDependente;
    }

    public void setCdDependente(int cdDependente) {
        this.cdDependente = cdDependente;
    }

    public Timestamp getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Timestamp dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipoParentesco() {
        return tipoParentesco;
    }

    public void setTipoParentesco(int tipoParentesco) {
        this.tipoParentesco = tipoParentesco;
    }

    public String getDescricaoParentesco() {
        return descricaoParentesco;
    }

    public void setDescricaoParentesco(String descricaoParentesco) {
        this.descricaoParentesco = descricaoParentesco;
    }

    public Timestamp getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Timestamp dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getSuspendeSf() {
        return suspendeSf;
    }

    public void setSuspendeSf(String suspendeSf) {
        this.suspendeSf = suspendeSf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCpfDependente() {
        return cpfDependente;
    }

    public void setCpfDependente(String cpfDependente) {
        this.cpfDependente = cpfDependente;
    }

    public int getEsocialTipoParentesco() {
        return esocialTipoParentesco;
    }

    public void setEsocialTipoParentesco(int esocialTipoParentesco) {
        this.esocialTipoParentesco = esocialTipoParentesco;
    }

    public String getEsocialPensionista() {
        return esocialPensionista;
    }

    public void setEsocialPensionista(String esocialPensionista) {
        this.esocialPensionista = esocialPensionista;
    }

    public int getEsocialEventoPensao() {
        return esocialEventoPensao;
    }

    public void setEsocialEventoPensao(int esocialEventoPensao) {
        this.esocialEventoPensao = esocialEventoPensao;
    }

    public double getEsocialPercentualPensao() {
        return esocialPercentualPensao;
    }

    public void setEsocialPercentualPensao(double esocialPercentualPensao) {
        this.esocialPercentualPensao = esocialPercentualPensao;
    }
    
    
}
