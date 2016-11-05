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
public class BancoNG extends PessoaNG{
   
    String codigobanco;
    String pracacompensacao;
    boolean indativo;

    public String getCodigobanco() {
        return codigobanco;
    }

    public void setCodigobanco(String codigobanco) {
        this.codigobanco = codigobanco;
    }

    public String getPracacompensacao() {
        return pracacompensacao;
    }

    public void setPracacompensacao(String pracacompensacao) {
        this.pracacompensacao = pracacompensacao;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }
   
}
