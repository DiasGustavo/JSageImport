/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.dominio;

import java.sql.Timestamp;

/**
 *
 * @author Jeff-Info
 */
public class EmpresaTributacao {
    
    int idformatributacao;
    int idpessoa; 
    int idCNAE;
    boolean indPrincipal;
    int ano;
    Timestamp datainiciovigenciaTributo;
    Timestamp datafimvigenciaTributo;
    Timestamp datainiciovigenciaCNAE;
    Timestamp datafimvigenciaCNAE;
    int iddadosempresaformatributacao;
    int idcodigogps;
    int idcodigosterceiros;
    int idoptantesimples;
    double percentualrat;
    double percentualterceiro;
    double percentualinss;
    double fap;

    public int getIdformatributacao() {
        return idformatributacao;
    }

    public void setIdformatributacao(int idformatributacao) {
        this.idformatributacao = idformatributacao;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getIdCNAE() {
        return idCNAE;
    }

    public void setIdCNAE(int idCNAE) {
        this.idCNAE = idCNAE;
    }

    public boolean isIndPrincipal() {
        return indPrincipal;
    }

    public void setIndPrincipal(boolean indPrincipal) {
        this.indPrincipal = indPrincipal;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Timestamp getDatainiciovigenciaTributo() {
        return datainiciovigenciaTributo;
    }

    public void setDatainiciovigenciaTributo(Timestamp datainiciovigenciaTributo) {
        this.datainiciovigenciaTributo = datainiciovigenciaTributo;
    }

    public Timestamp getDatafimvigenciaTributo() {
        return datafimvigenciaTributo;
    }

    public void setDatafimvigenciaTributo(Timestamp datafimvigenciaTributo) {
        this.datafimvigenciaTributo = datafimvigenciaTributo;
    }

    public Timestamp getDatainiciovigenciaCNAE() {
        return datainiciovigenciaCNAE;
    }

    public void setDatainiciovigenciaCNAE(Timestamp datainiciovigenciaCNAE) {
        this.datainiciovigenciaCNAE = datainiciovigenciaCNAE;
    }

    public Timestamp getDatafimvigenciaCNAE() {
        return datafimvigenciaCNAE;
    }

    public void setDatafimvigenciaCNAE(Timestamp datafimvigenciaCNAE) {
        this.datafimvigenciaCNAE = datafimvigenciaCNAE;
    }

    public int getIddadosempresaformatributacao() {
        return iddadosempresaformatributacao;
    }

    public void setIddadosempresaformatributacao(int iddadosempresaformatributacao) {
        this.iddadosempresaformatributacao = iddadosempresaformatributacao;
    }

    public int getIdcodigogps() {
        return idcodigogps;
    }

    public void setIdcodigogps(int idcodigogps) {
        this.idcodigogps = idcodigogps;
    }

    public int getIdcodigosterceiros() {
        return idcodigosterceiros;
    }

    public void setIdcodigosterceiros(int idcodigosterceiros) {
        this.idcodigosterceiros = idcodigosterceiros;
    }

    public int getIdoptantesimples() {
        return idoptantesimples;
    }

    public void setIdoptantesimples(int idoptantesimples) {
        this.idoptantesimples = idoptantesimples;
    }

    public double getPercentualrat() {
        return percentualrat;
    }

    public void setPercentualrat(double percentualrat) {
        this.percentualrat = percentualrat;
    }

    public double getPercentualterceiro() {
        return percentualterceiro;
    }

    public void setPercentualterceiro(double percentualterceiro) {
        this.percentualterceiro = percentualterceiro;
    }

    public double getPercentualinss() {
        return percentualinss;
    }

    public void setPercentualinss(double percentualinss) {
        this.percentualinss = percentualinss;
    }

    public double getFap() {
        return fap;
    }

    public void setFap(double fap) {
        this.fap = fap;
    }
    

}