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
public class MovimentacaoNG extends FolhaRegistroNG {
    int idregistro;
    int competenciames;
    int competenciaano;
    int idtipofolha;
    Timestamp datainiciofolha;
    Timestamp datafimfolha;
    Timestamp datainicioapuracaofatogerador;
    Timestamp datafimapuracaofatogerador;
    Timestamp datainicioapuracaodsr;
    Timestamp datafimapuracaodsr;
    String descricaofolha;
    Timestamp datairrf;
    boolean indtravada;
    int numerodiastrabalhados;
    boolean inddissidio;
    int idferias;
    int idrescisaocontratual;
    int idrescisaocomplementar;
    boolean inddemonstrativoonlinevisualizado;
    boolean indusuario;    
    int idpay;
    int idverba;
    double valorreferenciainformada;
    double qtdereferenciainformada;
    double qtdereferenciacalculada;
    double valorinformado;
    double valorcalculado;
    int indprovdesc;
    boolean indsistema;
    boolean indexcluido;
    String memoriacalculo;
  
    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public int getCompetenciames() {
        return competenciames;
    }

    public void setCompetenciames(int competenciames) {
        this.competenciames = competenciames;
    }

    public int getCompetenciaano() {
        return competenciaano;
    }

    public void setCompetenciaano(int competenciaano) {
        this.competenciaano = competenciaano;
    }

    public int getIdtipofolha() {
        return idtipofolha;
    }

    public void setIdtipofolha(int idtipofolha) {
        this.idtipofolha = idtipofolha;
    }

    public Timestamp getDatainiciofolha() {
        return datainiciofolha;
    }

    public void setDatainiciofolha(Timestamp datainiciofolha) {
        this.datainiciofolha = datainiciofolha;
    }

    public Timestamp getDatafimfolha() {
        return datafimfolha;
    }

    public void setDatafimfolha(Timestamp datafimfolha) {
        this.datafimfolha = datafimfolha;
    }

    public Timestamp getDatainicioapuracaofatogerador() {
        return datainicioapuracaofatogerador;
    }

    public void setDatainicioapuracaofatogerador(Timestamp datainicioapuracaofatogerador) {
        this.datainicioapuracaofatogerador = datainicioapuracaofatogerador;
    }

    public Timestamp getDatafimapuracaofatogerador() {
        return datafimapuracaofatogerador;
    }

    public void setDatafimapuracaofatogerador(Timestamp datafimapuracaofatogerador) {
        this.datafimapuracaofatogerador = datafimapuracaofatogerador;
    }

    public Timestamp getDatainicioapuracaodsr() {
        return datainicioapuracaodsr;
    }

    public void setDatainicioapuracaodsr(Timestamp datainicioapuracaodsr) {
        this.datainicioapuracaodsr = datainicioapuracaodsr;
    }

    public Timestamp getDatafimapuracaodsr() {
        return datafimapuracaodsr;
    }

    public void setDatafimapuracaodsr(Timestamp datafimapuracaodsr) {
        this.datafimapuracaodsr = datafimapuracaodsr;
    }

    public String getDescricaofolha() {
        return descricaofolha;
    }

    public void setDescricaofolha(String descricaofolha) {
        this.descricaofolha = descricaofolha;
    }

    public Timestamp getDatairrf() {
        return datairrf;
    }

    public void setDatairrf(Timestamp datairrf) {
        this.datairrf = datairrf;
    }

    public boolean isIndtravada() {
        return indtravada;
    }

    public void setIndtravada(boolean indtravada) {
        this.indtravada = indtravada;
    }

    public int getNumerodiastrabalhados() {
        return numerodiastrabalhados;
    }

    public void setNumerodiastrabalhados(int numerodiastrabalhados) {
        this.numerodiastrabalhados = numerodiastrabalhados;
    }

    public boolean isInddissidio() {
        return inddissidio;
    }

    public void setInddissidio(boolean inddissidio) {
        this.inddissidio = inddissidio;
    }

    public int getIdferias() {
        return idferias;
    }

    public void setIdferias(int idferias) {
        this.idferias = idferias;
    }

    public int getIdrescisaocontratual() {
        return idrescisaocontratual;
    }

    public void setIdrescisaocontratual(int idrescisaocontratual) {
        this.idrescisaocontratual = idrescisaocontratual;
    }

    public int getIdrescisaocomplementar() {
        return idrescisaocomplementar;
    }

    public void setIdrescisaocomplementar(int idrescisaocomplementar) {
        this.idrescisaocomplementar = idrescisaocomplementar;
    }

    public boolean isInddemonstrativoonlinevisualizado() {
        return inddemonstrativoonlinevisualizado;
    }

    public void setInddemonstrativoonlinevisualizado(boolean inddemonstrativoonlinevisualizado) {
        this.inddemonstrativoonlinevisualizado = inddemonstrativoonlinevisualizado;
    }

    public boolean isIndusuario() {
        return indusuario;
    }

    public void setIndusuario(boolean indusuario) {
        this.indusuario = indusuario;
    }

    public int getIdpay() {
        return idpay;
    }

    public void setIdpay(int idpay) {
        this.idpay = idpay;
    }

    public int getIdverba() {
        return idverba;
    }

    public void setIdverba(int idverba) {
        this.idverba = idverba;
    }

    public double getValorreferenciainformada() {
        return valorreferenciainformada;
    }

    public void setValorreferenciainformada(double valorreferenciainformada) {
        this.valorreferenciainformada = valorreferenciainformada;
    }

    public double getQtdereferenciainformada() {
        return qtdereferenciainformada;
    }

    public void setQtdereferenciainformada(double qtdereferenciainformada) {
        this.qtdereferenciainformada = qtdereferenciainformada;
    }

    public double getQtdereferenciacalculada() {
        return qtdereferenciacalculada;
    }

    public void setQtdereferenciacalculada(double qtdereferenciacalculada) {
        this.qtdereferenciacalculada = qtdereferenciacalculada;
    }

    public double getValorinformado() {
        return valorinformado;
    }

    public void setValorinformado(double valorinformado) {
        this.valorinformado = valorinformado;
    }

    public double getValorcalculado() {
        return valorcalculado;
    }

    public void setValorcalculado(double valorcalculado) {
        this.valorcalculado = valorcalculado;
    }

    public int getIndprovdesc() {
        return indprovdesc;
    }

    public void setIndprovdesc(int indprovdesc) {
        this.indprovdesc = indprovdesc;
    }

    public boolean isIndsistema() {
        return indsistema;
    }

    public void setIndsistema(boolean indsistema) {
        this.indsistema = indsistema;
    }

    public String getMemoriacalculo() {
        return memoriacalculo;
    }

    public void setMemoriacalculo(String memoriacalculo) {
        this.memoriacalculo = memoriacalculo;
    }
    
    
}
