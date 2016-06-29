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
public class DadosFuncionario extends PessoaFisica{
    
    int idDadosAgencia;
    int idDadosBanco;
    String numeroCtps;
    String serieCtps;
    Timestamp dataCtps;
    int idUfCtps;
    String numContaBancaria;
    String numDvContaBancaria;
    int idFormaPagamento;
    int idCodigoDirf;
    int idTipoFuncionario;
    boolean indAtivo;

    public int getIdDadosAgencia() {
        return idDadosAgencia;
    }

    public void setIdDadosAgencia(int idDadosAgencia) {
        this.idDadosAgencia = idDadosAgencia;
    }

    public int getIdDadosBanco() {
        return idDadosBanco;
    }

    public void setIdDadosBanco(int idDadosBanco) {
        this.idDadosBanco = idDadosBanco;
    }

    public String getNumeroCtps() {
        return numeroCtps;
    }

    public void setNumeroCtps(String numeroCtps) {
        this.numeroCtps = numeroCtps;
    }

    public String getSerieCtps() {
        return serieCtps;
    }

    public void setSerieCtps(String serieCtps) {
        this.serieCtps = serieCtps;
    }

    public Timestamp getDataCtps() {
        return dataCtps;
    }

    public void setDataCtps(Timestamp dataCtps) {
        this.dataCtps = dataCtps;
    }

    public int getIdUfCtps() {
        return idUfCtps;
    }

    public void setIdUfCtps(int idUfCtps) {
        this.idUfCtps = idUfCtps;
    }

    public String getNumContaBancaria() {
        return numContaBancaria;
    }

    public void setNumContaBancaria(String numContaBancaria) {
        this.numContaBancaria = numContaBancaria;
    }

    public String getNumDvContaBancaria() {
        return numDvContaBancaria;
    }

    public void setNumDvContaBancaria(String numDvContaBancaria) {
        this.numDvContaBancaria = numDvContaBancaria;
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public int getIdCodigoDirf() {
        return idCodigoDirf;
    }

    public void setIdCodigoDirf(int idCodigoDirf) {
        this.idCodigoDirf = idCodigoDirf;
    }

    public int getIdTipoFuncionario() {
        return idTipoFuncionario;
    }

    public void setIdTipoFuncionario(int idTipoFuncionario) {
        this.idTipoFuncionario = idTipoFuncionario;
    }

    public boolean isIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(boolean indAtivo) {
        this.indAtivo = indAtivo;
    }
    
    
    
}
