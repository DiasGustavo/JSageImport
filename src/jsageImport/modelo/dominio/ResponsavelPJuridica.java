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
public class ResponsavelPJuridica extends PessoaJuridica{
    int iddadosresponsavelfolhaempresa;
    int iddadosresponsavelfolha;
    int idpessoaempresa;
    boolean indsefip;
    boolean indcaged;
    boolean indrecisao;
    boolean indativo;
    boolean inddirf;
    boolean indrais;
    boolean indmanad;
    boolean inddcn;
    boolean indesocial;
    String nomeresponsavel;
    String autorizacaocaged;

    public String getAutorizacaocaged() {
        return autorizacaocaged;
    }

    public void setAutorizacaocaged(String autorizacaocaged) {
        this.autorizacaocaged = autorizacaocaged;
    }

    public String getNomeresponsavel() {
        return nomeresponsavel;
    }

    public void setNomeresponsavel(String nomeresponsavel) {
        this.nomeresponsavel = nomeresponsavel;
    }

    public int getIddadosresponsavelfolhaempresa() {
        return iddadosresponsavelfolhaempresa;
    }

    public void setIddadosresponsavelfolhaempresa(int iddadosresponsavelfolhaempresa) {
        this.iddadosresponsavelfolhaempresa = iddadosresponsavelfolhaempresa;
    }

    public int getIddadosresponsavelfolha() {
        return iddadosresponsavelfolha;
    }

    public void setIddadosresponsavelfolha(int iddadosresponsavelfolha) {
        this.iddadosresponsavelfolha = iddadosresponsavelfolha;
    }

    public int getIdpessoaempresa() {
        return idpessoaempresa;
    }

    public void setIdpessoaempresa(int idpessoaempresa) {
        this.idpessoaempresa = idpessoaempresa;
    }

    public boolean isIndsefip() {
        return indsefip;
    }

    public void setIndsefip(boolean indsefip) {
        this.indsefip = indsefip;
    }

    public boolean isIndcaged() {
        return indcaged;
    }

    public void setIndcaged(boolean indcaged) {
        this.indcaged = indcaged;
    }

    public boolean isIndrecisao() {
        return indrecisao;
    }

    public void setIndrecisao(boolean indrecisao) {
        this.indrecisao = indrecisao;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }

    public boolean isInddirf() {
        return inddirf;
    }

    public void setInddirf(boolean inddirf) {
        this.inddirf = inddirf;
    }

    public boolean isIndrais() {
        return indrais;
    }

    public void setIndrais(boolean indrais) {
        this.indrais = indrais;
    }

    public boolean isIndmanad() {
        return indmanad;
    }

    public void setIndmanad(boolean indmanad) {
        this.indmanad = indmanad;
    }

    public boolean isInddcn() {
        return inddcn;
    }

    public void setInddcn(boolean inddcn) {
        this.inddcn = inddcn;
    }

    public boolean isIndesocial() {
        return indesocial;
    }

    public void setIndesocial(boolean indesocial) {
        this.indesocial = indesocial;
    }
    
    
}
