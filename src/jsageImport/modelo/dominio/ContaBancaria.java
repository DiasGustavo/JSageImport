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
public class ContaBancaria extends AgenciaNG{
    
       int iddadosagencia;
       int idtipocontabancaria;     
       String numeroconta;
       String digitoverificador;
       String descricao;
       Timestamp dataabertura;
       Timestamp datasaldoinicial;
       double saldoinicial;
       boolean indchequeespecial;
       double limitechequeespecial;
       boolean indcontaprincipal;
       boolean indcontainativa;
       boolean indentrasaldoanteriorfluxocaixa;
       boolean indemiteboleto;
       String responsavel;       
       int idpessoaempresa;
       boolean indativo;    
       int idowner;      
       boolean indsomavalorescontabilizacao;

    public int getIddadosagencia() {
        return iddadosagencia;
    }

    public void setIddadosagencia(int iddadosagencia) {
        this.iddadosagencia = iddadosagencia;
    }

    public int getIdtipocontabancaria() {
        return idtipocontabancaria;
    }

    public void setIdtipocontabancaria(int idtipocontabancaria) {
        this.idtipocontabancaria = idtipocontabancaria;
    }

    public String getNumeroconta() {
        return numeroconta;
    }

    public void setNumeroconta(String numeroconta) {
        this.numeroconta = numeroconta;
    }

    public String getDigitoverificador() {
        return digitoverificador;
    }

    public void setDigitoverificador(String digitoverificador) {
        this.digitoverificador = digitoverificador;
    }

    public Timestamp getDataabertura() {
        return dataabertura;
    }

    public void setDataabertura(Timestamp dataabertura) {
        this.dataabertura = dataabertura;
    }

    public Timestamp getDatasaldoinicial() {
        return datasaldoinicial;
    }

    public void setDatasaldoinicial(Timestamp datasaldoinicial) {
        this.datasaldoinicial = datasaldoinicial;
    }

    public double getSaldoinicial() {
        return saldoinicial;
    }

    public void setSaldoinicial(double saldoinicial) {
        this.saldoinicial = saldoinicial;
    }

    public boolean isIndchequeespecial() {
        return indchequeespecial;
    }

    public void setIndchequeespecial(boolean indchequeespecial) {
        this.indchequeespecial = indchequeespecial;
    }

    public double getLimitechequeespecial() {
        return limitechequeespecial;
    }

    public void setLimitechequeespecial(double limitechequeespecial) {
        this.limitechequeespecial = limitechequeespecial;
    }

    public boolean isIndcontaprincipal() {
        return indcontaprincipal;
    }

    public void setIndcontaprincipal(boolean indcontaprincipal) {
        this.indcontaprincipal = indcontaprincipal;
    }

    public boolean isIndcontainativa() {
        return indcontainativa;
    }

    public void setIndcontainativa(boolean indcontainativa) {
        this.indcontainativa = indcontainativa;
    }

    public boolean isIndentrasaldoanteriorfluxocaixa() {
        return indentrasaldoanteriorfluxocaixa;
    }

    public void setIndentrasaldoanteriorfluxocaixa(boolean indentrasaldoanteriorfluxocaixa) {
        this.indentrasaldoanteriorfluxocaixa = indentrasaldoanteriorfluxocaixa;
    }

    public boolean isIndemiteboleto() {
        return indemiteboleto;
    }

    public void setIndemiteboleto(boolean indemiteboleto) {
        this.indemiteboleto = indemiteboleto;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public int getIdpessoaempresa() {
        return idpessoaempresa;
    }

    public void setIdpessoaempresa(int idpessoaempresa) {
        this.idpessoaempresa = idpessoaempresa;
    }

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

    public boolean isIndsomavalorescontabilizacao() {
        return indsomavalorescontabilizacao;
    }

    public void setIndsomavalorescontabilizacao(boolean indsomavalorescontabilizacao) {
        this.indsomavalorescontabilizacao = indsomavalorescontabilizacao;
    }
       
       
}
