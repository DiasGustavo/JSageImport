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
public class Contador extends PessoaFisica{
    
    String crc;
    int ufcrc;
    boolean inddefinitivo;
    boolean indresponsavel;
    String cargocontador;
    boolean indativo;
    int idowner;
    Timestamp datavalidadecrc;
    String numerosequencialcrc;

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public int getUfcrc() {
        return ufcrc;
    }

    public void setUfcrc(int ufcrc) {
        this.ufcrc = ufcrc;
    }

    public boolean isInddefinitivo() {
        return inddefinitivo;
    }

    public void setInddefinitivo(boolean inddefinitivo) {
        this.inddefinitivo = inddefinitivo;
    }

    public boolean isIndresponsavel() {
        return indresponsavel;
    }

    public void setIndresponsavel(boolean indresponsavel) {
        this.indresponsavel = indresponsavel;
    }

    public String getCargocontador() {
        return cargocontador;
    }

    public void setCargocontador(String cargocontador) {
        this.cargocontador = cargocontador;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

    public Timestamp getDatavalidadecrc() {
        return datavalidadecrc;
    }

    public void setDatavalidadecrc(Timestamp datavalidadecrc) {
        this.datavalidadecrc = datavalidadecrc;
    }

    public String getNumerosequencialcrc() {
        return numerosequencialcrc;
    }

    public void setNumerosequencialcrc(String numerosequencialcrc) {
        this.numerosequencialcrc = numerosequencialcrc;
    }
    
    
    
}
