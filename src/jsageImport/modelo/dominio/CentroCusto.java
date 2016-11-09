/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.dominio;

/**
 *
 * @author Jeff-Info
 */
public class CentroCusto {
    
    int idcentrocusto;
    
    int idowner;
    
    String codigocentrocusto;
    String codigoestruturado;
    String descricaocentrocusto;
    boolean indativo;

    public int getIdcentrocusto() {
        return idcentrocusto;
    }

    public void setIdcentrocusto(int idcentrocusto) {
        this.idcentrocusto = idcentrocusto;
    }

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

       public String getCodigocentrocusto() {
        return codigocentrocusto;
    }

    public void setCodigocentrocusto(String codigocentrocusto) {
        this.codigocentrocusto = codigocentrocusto;
    }

    public String getCodigoestruturado() {
        return codigoestruturado;
    }

    public void setCodigoestruturado(String codigoestruturado) {
        this.codigoestruturado = codigoestruturado;
    }

    public String getDescricaocentrocusto() {
        return descricaocentrocusto;
    }

    public void setDescricaocentrocusto(String descricaocentrocusto) {
        this.descricaocentrocusto = descricaocentrocusto;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }
    
}
