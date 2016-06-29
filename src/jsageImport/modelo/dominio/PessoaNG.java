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
public class PessoaNG {
    
    int idPessoa;
    String codigoPessoa;
    String nomePessoa;
    boolean indFabrica;
    String observacao;
    boolean inddadosConvertidos;
    boolean indDesativada;
    Timestamp dataCadastramento;
    String fotoPessoa;
    byte [] biometria;
    String numeroCei;
    String codigoExternoEmpresa;
    String codigoExternoFilial;
    String documentoEstrangeiro;
    String cno;
    String caepf;
    int idtipocaepf;
    /*
    * Dados referente ao endereço
    */
    int idTipoBairro;
    int idTipoLogradouro;
    int idTitulogradouro;
    int idTipoEndereco;
    String logradouro;
    String numeroEndereco;
    String complemento;
    String bairro;
    int idmunicipio;
    String cep;
    String caixaPostal;
    byte [] indPrincipal;
    int idPaisEstrangeiro;
    String nomeUfEstrangeiro;
    String nomeMunicipioEstrangeiro;

    public int getIdTipoBairro() {
        return idTipoBairro;
    }

    public void setIdTipoBairro(int idTipoBairro) {
        this.idTipoBairro = idTipoBairro;
    }

    public int getIdTipoLogradouro() {
        return idTipoLogradouro;
    }

    public void setIdTipoLogradouro(int idTipoLogradouro) {
        this.idTipoLogradouro = idTipoLogradouro;
    }

    public int getIdTitulogradouro() {
        return idTitulogradouro;
    }

    public void setIdTitulogradouro(int idTitulogradouro) {
        this.idTitulogradouro = idTitulogradouro;
    }

    public int getIdTipoEndereco() {
        return idTipoEndereco;
    }

    public void setIdTipoEndereco(int idTipoEndereco) {
        this.idTipoEndereco = idTipoEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(int idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCaixaPostal() {
        return caixaPostal;
    }

    public void setCaixaPostal(String caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    public byte[] getIndPrincipal() {
        return indPrincipal;
    }

    public void setIndPrincipal(byte[] indPrincipal) {
        this.indPrincipal = indPrincipal;
    }

    public int getIdPaisEstrangeiro() {
        return idPaisEstrangeiro;
    }

    public void setIdPaisEstrangeiro(int idPaisEstrangeiro) {
        this.idPaisEstrangeiro = idPaisEstrangeiro;
    }

    public String getNomeUfEstrangeiro() {
        return nomeUfEstrangeiro;
    }

    public void setNomeUfEstrangeiro(String nomeUfEstrangeiro) {
        this.nomeUfEstrangeiro = nomeUfEstrangeiro;
    }

    public String getNomeMunicipioEstrangeiro() {
        return nomeMunicipioEstrangeiro;
    }

    public void setNomeMunicipioEstrangeiro(String nomeMunicipioEstrangeiro) {
        this.nomeMunicipioEstrangeiro = nomeMunicipioEstrangeiro;
    }
         
    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(String codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public boolean isIndFabrica() {
        return indFabrica;
    }

    public void setIndFabrica(boolean indFabrica) {
        this.indFabrica = indFabrica;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isInddadosConvertidos() {
        return inddadosConvertidos;
    }

    public void setInddadosConvertidos(boolean inddadosConvertidos) {
        this.inddadosConvertidos = inddadosConvertidos;
    }

    public boolean isIndDesativada() {
        return indDesativada;
    }

    public void setIndDesativada(boolean indDesativada) {
        this.indDesativada = indDesativada;
    }

    public Timestamp getDataCadastramento() {
        return dataCadastramento;
    }

    public void setDataCadastramento(Timestamp dataCadastramento) {
        this.dataCadastramento = dataCadastramento;
    }

    public String getFotoPessoa() {
        return fotoPessoa;
    }

    public void setFotoPessoa(String fotoPessoa) {
        this.fotoPessoa = fotoPessoa;
    }

    public byte[] getBiometria() {
        return biometria;
    }

    public void setBiometria(byte[] biometria) {
        this.biometria = biometria;
    }

    public String getNumeroCei() {
        return numeroCei;
    }

    public void setNumeroCei(String numeroCei) {
        this.numeroCei = numeroCei;
    }

    public String getCodigoExternoEmpresa() {
        return codigoExternoEmpresa;
    }

    public void setCodigoExternoEmpresa(String codigoExternoEmpresa) {
        this.codigoExternoEmpresa = codigoExternoEmpresa;
    }

    public String getCodigoExternoFilial() {
        return codigoExternoFilial;
    }

    public void setCodigoExternoFilial(String codigoExternoFilial) {
        this.codigoExternoFilial = codigoExternoFilial;
    }

    public String getDocumentoEstrangeiro() {
        return documentoEstrangeiro;
    }

    public void setDocumentoEstrangeiro(String documentoEstrangeiro) {
        this.documentoEstrangeiro = documentoEstrangeiro;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCaepf() {
        return caepf;
    }

    public void setCaepf(String caepf) {
        this.caepf = caepf;
    }

    public int getIdtipocaepf() {
        return idtipocaepf;
    }

    public void setIdtipocaepf(int idtipocaepf) {
        this.idtipocaepf = idtipocaepf;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    
    
    
}
