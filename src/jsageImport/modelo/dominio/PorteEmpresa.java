/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.dominio;

import java.sql.Timestamp;

/**
 * @author Gustavo Dias
 * Criação: 24/10/2016
 * Última modificação: 24/10/2016
 * Modificado por: Gustavo Dais
 */
public class PorteEmpresa {
    
    int idporteempresa;
    int idpessoa;
    int ano;
    Timestamp datainiciovigencia;
    Timestamp datafimvigencia;

    public int getIdporteempresa() {
        return idporteempresa;
    }

    public void setIdporteempresa(int idporteempresa) {
        this.idporteempresa = idporteempresa;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Timestamp getDatainiciovigencia() {
        return datainiciovigencia;
    }

    public void setDatainiciovigencia(Timestamp datainiciovigencia) {
        this.datainiciovigencia = datainiciovigencia;
    }

    public Timestamp getDatafimvigencia() {
        return datafimvigencia;
    }

    public void setDatafimvigencia(Timestamp datafimvigencia) {
        this.datafimvigencia = datafimvigencia;
    }

    
}
