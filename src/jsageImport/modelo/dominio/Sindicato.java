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
public class Sindicato extends PessoaJuridica{
    
    int iddadossindicato;
    //int idpessoa;
    int idtiposindicato;
    String codigoentidade;
    int mescontribuicao;
    int mesdissidio;
    boolean inddescontarmesadmissao;
    double fatoradicionalferias;
    boolean indativo;
    int diaslicencamaternidade;
    double percentualminimohoraextra;
    int numerodiasavisoprevio;
    boolean indconsideraracrescimoavisoprevio;
    int numerodiasantecedenciapgtoferias;
    int desconsiderardiasferiascoletivas;

    public int getIddadossindicato() {
        return iddadossindicato;
    }

    public void setIddadossindicato(int iddadossindicato) {
        this.iddadossindicato = iddadossindicato;
    }

    /*public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }*/

    public int getIdtiposindicato() {
        return idtiposindicato;
    }

    public void setIdtiposindicato(int idtiposindicato) {
        this.idtiposindicato = idtiposindicato;
    }

    public String getCodigoentidade() {
        return codigoentidade;
    }

    public void setCodigoentidade(String codigoentidade) {
        this.codigoentidade = codigoentidade;
    }

    public int getMescontribuicao() {
        return mescontribuicao;
    }

    public void setMescontribuicao(int mescontribuicao) {
        this.mescontribuicao = mescontribuicao;
    }

    public int getMesdissidio() {
        return mesdissidio;
    }

    public void setMesdissidio(int mesdissidio) {
        this.mesdissidio = mesdissidio;
    }

    public boolean isInddescontarmesadmissao() {
        return inddescontarmesadmissao;
    }

    public void setInddescontarmesadmissao(boolean inddescontarmesadmissao) {
        this.inddescontarmesadmissao = inddescontarmesadmissao;
    }

    public double getFatoradicionalferias() {
        return fatoradicionalferias;
    }

    public void setFatoradicionalferias(double fatoradicionalferias) {
        this.fatoradicionalferias = fatoradicionalferias;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }

    public int getDiaslicencamaternidade() {
        return diaslicencamaternidade;
    }

    public void setDiaslicencamaternidade(int diaslicencamaternidade) {
        this.diaslicencamaternidade = diaslicencamaternidade;
    }

    public double getPercentualminimohoraextra() {
        return percentualminimohoraextra;
    }

    public void setPercentualminimohoraextra(double percentualminimohoraextra) {
        this.percentualminimohoraextra = percentualminimohoraextra;
    }

    public int getNumerodiasavisoprevio() {
        return numerodiasavisoprevio;
    }

    public void setNumerodiasavisoprevio(int numerodiasavisoprevio) {
        this.numerodiasavisoprevio = numerodiasavisoprevio;
    }

    public boolean isIndconsideraracrescimoavisoprevio() {
        return indconsideraracrescimoavisoprevio;
    }

    public void setIndconsideraracrescimoavisoprevio(boolean indconsideraracrescimoavisoprevio) {
        this.indconsideraracrescimoavisoprevio = indconsideraracrescimoavisoprevio;
    }

    public int getNumerodiasantecedenciapgtoferias() {
        return numerodiasantecedenciapgtoferias;
    }

    public void setNumerodiasantecedenciapgtoferias(int numerodiasantecedenciapgtoferias) {
        this.numerodiasantecedenciapgtoferias = numerodiasantecedenciapgtoferias;
    }

    public int getDesconsiderardiasferiascoletivas() {
        return desconsiderardiasferiascoletivas;
    }

    public void setDesconsiderardiasferiascoletivas(int desconsiderardiasferiascoletivas) {
        this.desconsiderardiasferiascoletivas = desconsiderardiasferiascoletivas;
    }
      
     
    
}
