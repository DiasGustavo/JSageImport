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
public class FeriasNG extends DadosFuncionaisNG{
    
    Timestamp datainicioferias;
    Timestamp datainicioperiodoaquisitivo;
    Timestamp datafimperiodoaquisitivo;
    Timestamp datainicioperiodogozo;
    Timestamp datafimperiodogozo;
    Timestamp datainicioabono;
    Timestamp datafimabono;
    int diasfalta;
    double diasferias;
    int diasdesconsiderar;
    boolean indconcederabonopecuniario;
    int diasabono;
    boolean indabonoinicioferias;
    boolean indpagar13salario;
    Timestamp dataretornoferias;
    Timestamp datalimitegozo;
    int indstatus;
    double salario;
    double salarioferias;
    double mediasalarial;
    Timestamp datairrf;
    double saldodias;
    boolean indoculto;
    boolean indferiascoletivas;
    double diaslicenca;
    double valorcomposicaosalarialferias;
    Timestamp datahoralog;

    public Timestamp getDatahoralog() {
        return datahoralog;
    }

    public void setDatahoralog(Timestamp datahoralog) {
        this.datahoralog = datahoralog;
    }

    public Timestamp getDatainicioferias() {
        return datainicioferias;
    }

    public void setDatainicioferias(Timestamp datainicioferias) {
        this.datainicioferias = datainicioferias;
    }

    public Timestamp getDatainicioperiodoaquisitivo() {
        return datainicioperiodoaquisitivo;
    }

    public void setDatainicioperiodoaquisitivo(Timestamp datainicioperiodoaquisitivo) {
        this.datainicioperiodoaquisitivo = datainicioperiodoaquisitivo;
    }

    public Timestamp getDatafimperiodoaquisitivo() {
        return datafimperiodoaquisitivo;
    }

    public void setDatafimperiodoaquisitivo(Timestamp datafimperiodoaquisitivo) {
        this.datafimperiodoaquisitivo = datafimperiodoaquisitivo;
    }

    public Timestamp getDatainicioperiodogozo() {
        return datainicioperiodogozo;
    }

    public void setDatainicioperiodogozo(Timestamp datainicioperiodogozo) {
        this.datainicioperiodogozo = datainicioperiodogozo;
    }

    public Timestamp getDatafimperiodogozo() {
        return datafimperiodogozo;
    }

    public void setDatafimperiodogozo(Timestamp datafimperiodogozo) {
        this.datafimperiodogozo = datafimperiodogozo;
    }

    public Timestamp getDatainicioabono() {
        return datainicioabono;
    }

    public void setDatainicioabono(Timestamp datainicioabono) {
        this.datainicioabono = datainicioabono;
    }

    public Timestamp getDatafimabono() {
        return datafimabono;
    }

    public void setDatafimabono(Timestamp datafimabono) {
        this.datafimabono = datafimabono;
    }

    public int getDiasfalta() {
        return diasfalta;
    }

    public void setDiasfalta(int diasfalta) {
        this.diasfalta = diasfalta;
    }

    public double getDiasferias() {
        return diasferias;
    }

    public void setDiasferias(double diasferias) {
        this.diasferias = diasferias;
    }

    public int getDiasdesconsiderar() {
        return diasdesconsiderar;
    }

    public void setDiasdesconsiderar(int diasdesconsiderar) {
        this.diasdesconsiderar = diasdesconsiderar;
    }

    public boolean isIndconcederabonopecuniario() {
        return indconcederabonopecuniario;
    }

    public void setIndconcederabonopecuniario(boolean indconcederabonopecuniario) {
        this.indconcederabonopecuniario = indconcederabonopecuniario;
    }

    public int getDiasabono() {
        return diasabono;
    }

    public void setDiasabono(int diasabono) {
        this.diasabono = diasabono;
    }

    public boolean isIndabonoinicioferias() {
        return indabonoinicioferias;
    }

    public void setIndabonoinicioferias(boolean indabonoinicioferias) {
        this.indabonoinicioferias = indabonoinicioferias;
    }

    public boolean isIndpagar13salario() {
        return indpagar13salario;
    }

    public void setIndpagar13salario(boolean indpagar13salario) {
        this.indpagar13salario = indpagar13salario;
    }

    public Timestamp getDataretornoferias() {
        return dataretornoferias;
    }

    public void setDataretornoferias(Timestamp dataretornoferias) {
        this.dataretornoferias = dataretornoferias;
    }

    public Timestamp getDatalimitegozo() {
        return datalimitegozo;
    }

    public void setDatalimitegozo(Timestamp datalimitegozo) {
        this.datalimitegozo = datalimitegozo;
    }

    public int getIndstatus() {
        return indstatus;
    }

    public void setIndstatus(int indstatus) {
        this.indstatus = indstatus;
    }

    @Override
    public Double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalarioferias() {
        return salarioferias;
    }

    public void setSalarioferias(double salarioferias) {
        this.salarioferias = salarioferias;
    }

    public double getMediasalarial() {
        return mediasalarial;
    }

    public void setMediasalarial(double mediasalarial) {
        this.mediasalarial = mediasalarial;
    }

    public Timestamp getDatairrf() {
        return datairrf;
    }

    public void setDatairrf(Timestamp datairrf) {
        this.datairrf = datairrf;
    }

    public double getSaldodias() {
        return saldodias;
    }

    public void setSaldodias(double saldodias) {
        this.saldodias = saldodias;
    }

    public boolean isIndoculto() {
        return indoculto;
    }

    public void setIndoculto(boolean indoculto) {
        this.indoculto = indoculto;
    }

    public boolean isIndferiascoletivas() {
        return indferiascoletivas;
    }

    public void setIndferiascoletivas(boolean indferiascoletivas) {
        this.indferiascoletivas = indferiascoletivas;
    }

    public double getDiaslicenca() {
        return diaslicenca;
    }

    public void setDiaslicenca(double diaslicenca) {
        this.diaslicenca = diaslicenca;
    }

    public double getValorcomposicaosalarialferias() {
        return valorcomposicaosalarialferias;
    }

    public void setValorcomposicaosalarialferias(double valorcomposicaosalarialferias) {
        this.valorcomposicaosalarialferias = valorcomposicaosalarialferias;
    }
          
    
}
