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
public class PessoaFisica extends PessoaNG {
    
    int idCategoriaDocumentoMilitar;
    int idMunicipioNaturalidade;
    int idRaca;
    int idGrauInstrucao;
    int idEStadoCivil;
    String cpf;
    String numeroDocumentoIdentidade;
    Timestamp dataExpedicaoDocumentoIdentidade;
    String inscricaoInss;
    String orgaoExpedidorDocumentoIdentidade;
    Timestamp dataNascimento;
    String apelido;
    String indSexo;
    String tituloEleitor;
    String zonaEleitoral;
    String secaoEleitoral;
    String numeroDocumentoMilitar;
    String numeroRegistroProfissional;
    String orgaoExpedidorRegistroProfissional;
    String habilitacaoProfissional;
    Timestamp dataEmissaocnh;
    int idufcnh;
    String numeroCnh;
    Timestamp vencimentoCnh;
    int idcategoriaHabilitacaoCnh;
    Timestamp dataChegada;
    boolean indConjugeBrasileiro;
    boolean indNaturalizado;
    String numeroDecreto;
    String tipoVisto;
    int numeroFilhosBrasileiros;
    String registroGeralEstrangeiro;
    Timestamp validadeIdentidade;
    Timestamp validadeCtps;
    int idPaisNacionalidadeEstrangeiro;
    String nomeUfNacionalidadeEstrangeiro;
    String nomeMunicipioNaciolidadeEstrangeiro;
    int idTipoDeficiencia;
    int idTipoInscricao;
    String numeroInscricao;
    Timestamp dataInscricao;
    String tipoCerdidaoCivil;
    Timestamp dataEmissaoCertidaoCivil;
    String termoMatriculaCertidaoCivil;
    String livroCertidaoCivil;
    String folhaCertidaoCivil;
    String cartorioCertidaoCivil;
    int idUfCertidaoCivil;
    int idMunicipioCertidaoCivil;
    String numeroPassaporte;
    String orgaoEmissorPassaporte;
    int idUfPassaporte;
    Timestamp dataEmissaoPassaporte;
    Timestamp dataValidadePassaporte;
    int idPaisPassaporte;
    String numeroric;
    int idUfRic;
    String orgaoEmissorRic;
    int idMunicipioRic;
    Timestamp dataExpedicaoRic;
    String cpfFormatado;
    String orgaoEmissorCnh;
    Timestamp dataExpedicaoRegistroProfissional;
    Timestamp dataValidadeRegistroProfissional;
    Timestamp dataNaturalizacao;
    String orgaoEmissorRegistroNacionalEstrangeiro;
    String numeroRegistroNacionalEstrangeiro;
    Timestamp dataExpedicaoREgistroNacionalEstrangeiro;
    int idPaisNacionalidade;
    String listaIdTipoDeficienciaEsocial;
    String obervacaoDeficiencia;
    String numeroPortariaNaturalizacao;
    int idIndicadorInscricaoEstadualNfe;
    Timestamp dataPrimeiraHabilitacao;
    int idClassificacaoTrabalhadorEstrangeiro;
    
    public Timestamp getDataExpedicaoREgistroNacionalEstrangeiro() {
        return dataExpedicaoREgistroNacionalEstrangeiro;
    }

    public void setDataExpedicaoREgistroNacionalEstrangeiro(Timestamp dataExpedicaoREgistroNacionalEstrangeiro) {
        this.dataExpedicaoREgistroNacionalEstrangeiro = dataExpedicaoREgistroNacionalEstrangeiro;
    }    
    
    public String getOrgaoEmissorRegistroNacionalEstrangeiro() {
        return orgaoEmissorRegistroNacionalEstrangeiro;
    }

    public void setOrgaoEmissorRegistroNacionalEstrangeiro(String orgaoEmissorRegistroNacionalEstrangeiro) {
        this.orgaoEmissorRegistroNacionalEstrangeiro = orgaoEmissorRegistroNacionalEstrangeiro;
    }
    
    public Timestamp getDataNaturalizacao() {
        return dataNaturalizacao;
    }

    public void setDataNaturalizacao(Timestamp dataNaturalizacao) {
        this.dataNaturalizacao = dataNaturalizacao;
    }
    
    public int getIdCategoriaDocumentoMilitar() {
        return idCategoriaDocumentoMilitar;
    }

    public void setIdCategoriaDocumentoMilitar(int idCategoriaDocumentoMilitar) {
        this.idCategoriaDocumentoMilitar = idCategoriaDocumentoMilitar;
    }

    public int getIdMunicipioNaturalidade() {
        return idMunicipioNaturalidade;
    }

    public void setIdMunicipioNaturalidade(int idMunicipioNaturalidade) {
        this.idMunicipioNaturalidade = idMunicipioNaturalidade;
    }

    public int getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(int idRaca) {
        this.idRaca = idRaca;
    }

    public int getIdGrauInstrucao() {
        return idGrauInstrucao;
    }

    public void setIdGrauInstrucao(int idGrauInstrucao) {
        this.idGrauInstrucao = idGrauInstrucao;
    }

    public int getIdEStadoCivil() {
        return idEStadoCivil;
    }

    public void setIdEStadoCivil(int idEStadoCivil) {
        this.idEStadoCivil = idEStadoCivil;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroDocumentoIdentidade() {
        return numeroDocumentoIdentidade;
    }

    public void setNumeroDocumentoIdentidade(String numeroDocumentoIdentidade) {
        this.numeroDocumentoIdentidade = numeroDocumentoIdentidade;
    }

    public Timestamp getDataExpedicaoDocumentoIdentidade() {
        return dataExpedicaoDocumentoIdentidade;
    }

    public void setDataExpedicaoDocumentoIdentidade(Timestamp dataExpedicaoDocumentoIdentidade) {
        this.dataExpedicaoDocumentoIdentidade = dataExpedicaoDocumentoIdentidade;
    }

    public String getInscricaoInss() {
        return inscricaoInss;
    }

    public void setInscricaoInss(String inscricaoInss) {
        this.inscricaoInss = inscricaoInss;
    }

    public String getOrgaoExpedidorDocumentoIdentidade() {
        return orgaoExpedidorDocumentoIdentidade;
    }

    public void setOrgaoExpedidorDocumentoIdentidade(String orgaoExpedidorDocumentoIdentidade) {
        this.orgaoExpedidorDocumentoIdentidade = orgaoExpedidorDocumentoIdentidade;
    }

    public Timestamp getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Timestamp dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getIndSexo() {
        return indSexo;
    }

    public void setIndSexo(String indSexo) {
        this.indSexo = indSexo;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public String getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(String zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public String getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public void setSecaoEleitoral(String secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }

    public String getNumeroDocumentoMilitar() {
        return numeroDocumentoMilitar;
    }

    public void setNumeroDocumentoMilitar(String numeroDocumentoMilitar) {
        this.numeroDocumentoMilitar = numeroDocumentoMilitar;
    }

    public String getNumeroRegistroProfissional() {
        return numeroRegistroProfissional;
    }

    public void setNumeroRegistroProfissional(String numeroRegistroProfissional) {
        this.numeroRegistroProfissional = numeroRegistroProfissional;
    }

    public String getOrgaoExpedidorRegistroProfissional() {
        return orgaoExpedidorRegistroProfissional;
    }

    public void setOrgaoExpedidorRegistroProfissional(String orgaoExpedidorRegistroProfissional) {
        this.orgaoExpedidorRegistroProfissional = orgaoExpedidorRegistroProfissional;
    }

    public String getHabilitacaoProfissional() {
        return habilitacaoProfissional;
    }

    public void setHabilitacaoProfissional(String habilitacaoProfissional) {
        this.habilitacaoProfissional = habilitacaoProfissional;
    }

    public Timestamp getDataEmissaocnh() {
        return dataEmissaocnh;
    }

    public void setDataEmissaocnh(Timestamp dataEmissaocnh) {
        this.dataEmissaocnh = dataEmissaocnh;
    }

    public int getIdufcnh() {
        return idufcnh;
    }

    public void setIdufcnh(int idufcnh) {
        this.idufcnh = idufcnh;
    }

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public Timestamp getVencimentoCnh() {
        return vencimentoCnh;
    }

    public void setVencimentoCnh(Timestamp vencimentoCnh) {
        this.vencimentoCnh = vencimentoCnh;
    }

    public int getIdcategoriaHabilitacaoCnh() {
        return idcategoriaHabilitacaoCnh;
    }

    public void setIdcategoriaHabilitacaoCnh(int idcategoriaHabilitacaoCnh) {
        this.idcategoriaHabilitacaoCnh = idcategoriaHabilitacaoCnh;
    }

    public Timestamp getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Timestamp dataChegada) {
        this.dataChegada = dataChegada;
    }

    public boolean isIndConjugeBrasileiro() {
        return indConjugeBrasileiro;
    }

    public void setIndConjugeBrasileiro(boolean indConjugeBrasileiro) {
        this.indConjugeBrasileiro = indConjugeBrasileiro;
    }

    public boolean isIndNaturalizado() {
        return indNaturalizado;
    }

    public void setIndNaturalizado(boolean indNaturalizado) {
        this.indNaturalizado = indNaturalizado;
    }

    public String getNumeroDecreto() {
        return numeroDecreto;
    }

    public void setNumeroDecreto(String numeroDecreto) {
        this.numeroDecreto = numeroDecreto;
    }

    public String getTipoVisto() {
        return tipoVisto;
    }

    public void setTipoVisto(String tipoVisto) {
        this.tipoVisto = tipoVisto;
    }

    public int getNumeroFilhosBrasileiros() {
        return numeroFilhosBrasileiros;
    }

    public void setNumeroFilhosBrasileiros(int numeroFilhosBrasileiros) {
        this.numeroFilhosBrasileiros = numeroFilhosBrasileiros;
    }

    public String getRegistroGeralEstrangeiro() {
        return registroGeralEstrangeiro;
    }

    public void setRegistroGeralEstrangeiro(String registroGeralEstrangeiro) {
        this.registroGeralEstrangeiro = registroGeralEstrangeiro;
    }

    public Timestamp getValidadeIdentidade() {
        return validadeIdentidade;
    }

    public void setValidadeIdentidade(Timestamp validadeIdentidade) {
        this.validadeIdentidade = validadeIdentidade;
    }

    public Timestamp getValidadeCtps() {
        return validadeCtps;
    }

    public void setValidadeCtps(Timestamp validadeCtps) {
        this.validadeCtps = validadeCtps;
    }

    public int getIdPaisNacionalidadeEstrangeiro() {
        return idPaisNacionalidadeEstrangeiro;
    }

    public void setIdPaisNacionalidadeEstrangeiro(int idPaisNacionalidadeEstrangeiro) {
        this.idPaisNacionalidadeEstrangeiro = idPaisNacionalidadeEstrangeiro;
    }

    public String getNomeUfNacionalidadeEstrangeiro() {
        return nomeUfNacionalidadeEstrangeiro;
    }

    public void setNomeUfNacionalidadeEstrangeiro(String nomeUfNacionalidadeEstrangeiro) {
        this.nomeUfNacionalidadeEstrangeiro = nomeUfNacionalidadeEstrangeiro;
    }

    public String getNomeMunicipioNaciolidadeEstrangeiro() {
        return nomeMunicipioNaciolidadeEstrangeiro;
    }

    public void setNomeMunicipioNaciolidadeEstrangeiro(String nomeMunicipioNaciolidadeEstrangeiro) {
        this.nomeMunicipioNaciolidadeEstrangeiro = nomeMunicipioNaciolidadeEstrangeiro;
    }

    public int getIdTipoDeficiencia() {
        return idTipoDeficiencia;
    }

    public void setIdTipoDeficiencia(int idTipoDeficiencia) {
        this.idTipoDeficiencia = idTipoDeficiencia;
    }

    public int getIdTipoInscricao() {
        return idTipoInscricao;
    }

    public void setIdTipoInscricao(int idTipoInscricao) {
        this.idTipoInscricao = idTipoInscricao;
    }

    public String getNumeroInscricao() {
        return numeroInscricao;
    }

    public void setNumeroInscricao(String numeroInscricao) {
        this.numeroInscricao = numeroInscricao;
    }

    public Timestamp getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Timestamp dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getTipoCerdidaoCivil() {
        return tipoCerdidaoCivil;
    }

    public void setTipoCerdidaoCivil(String tipoCerdidaoCivil) {
        this.tipoCerdidaoCivil = tipoCerdidaoCivil;
    }

    public Timestamp getDataEmissaoCertidaoCivil() {
        return dataEmissaoCertidaoCivil;
    }

    public void setDataEmissaoCertidaoCivil(Timestamp dataEmissaoCertidaoCivil) {
        this.dataEmissaoCertidaoCivil = dataEmissaoCertidaoCivil;
    }

    public String getTermoMatriculaCertidaoCivil() {
        return termoMatriculaCertidaoCivil;
    }

    public void setTermoMatriculaCertidaoCivil(String termoMatriculaCertidaoCivil) {
        this.termoMatriculaCertidaoCivil = termoMatriculaCertidaoCivil;
    }

    public String getLivroCertidaoCivil() {
        return livroCertidaoCivil;
    }

    public void setLivroCertidaoCivil(String livroCertidaoCivil) {
        this.livroCertidaoCivil = livroCertidaoCivil;
    }

    public String getFolhaCertidaoCivil() {
        return folhaCertidaoCivil;
    }

    public void setFolhaCertidaoCivil(String folhaCertidaoCivil) {
        this.folhaCertidaoCivil = folhaCertidaoCivil;
    }

    public String getCartorioCertidaoCivil() {
        return cartorioCertidaoCivil;
    }

    public void setCartorioCertidaoCivil(String cartorioCertidaoCivil) {
        this.cartorioCertidaoCivil = cartorioCertidaoCivil;
    }

    public int getIdUfCertidaoCivil() {
        return idUfCertidaoCivil;
    }

    public void setIdUfCertidaoCivil(int idUfCertidaoCivil) {
        this.idUfCertidaoCivil = idUfCertidaoCivil;
    }

    public int getIdMunicipioCertidaoCivil() {
        return idMunicipioCertidaoCivil;
    }

    public void setIdMunicipioCertidaoCivil(int idMunicipioCertidaoCivil) {
        this.idMunicipioCertidaoCivil = idMunicipioCertidaoCivil;
    }

    public String getNumeroPassaporte() {
        return numeroPassaporte;
    }

    public void setNumeroPassaporte(String numeroPassaporte) {
        this.numeroPassaporte = numeroPassaporte;
    }

    public String getOrgaoEmissorPassaporte() {
        return orgaoEmissorPassaporte;
    }

    public void setOrgaoEmissorPassaporte(String orgaoEmissorPassaporte) {
        this.orgaoEmissorPassaporte = orgaoEmissorPassaporte;
    }

    public int getIdUfPassaporte() {
        return idUfPassaporte;
    }

    public void setIdUfPassaporte(int idUfPassaporte) {
        this.idUfPassaporte = idUfPassaporte;
    }

    public Timestamp getDataEmissaoPassaporte() {
        return dataEmissaoPassaporte;
    }

    public void setDataEmissaoPassaporte(Timestamp dataEmissaoPassaporte) {
        this.dataEmissaoPassaporte = dataEmissaoPassaporte;
    }

    public Timestamp getDataValidadePassaporte() {
        return dataValidadePassaporte;
    }

    public void setDataValidadePassaporte(Timestamp dataValidadePassaporte) {
        this.dataValidadePassaporte = dataValidadePassaporte;
    }

    public int getIdPaisPassaporte() {
        return idPaisPassaporte;
    }

    public void setIdPaisPassaporte(int idPaisPassaporte) {
        this.idPaisPassaporte = idPaisPassaporte;
    }

    public String getNumeroric() {
        return numeroric;
    }

    public void setNumeroric(String numeroric) {
        this.numeroric = numeroric;
    }

    public int getIdUfRic() {
        return idUfRic;
    }

    public void setIdUfRic(int idUfRic) {
        this.idUfRic = idUfRic;
    }

    public String getOrgaoEmissorRic() {
        return orgaoEmissorRic;
    }

    public void setOrgaoEmissorRic(String orgaoEmissorRic) {
        this.orgaoEmissorRic = orgaoEmissorRic;
    }

    public int getIdMunicipioRic() {
        return idMunicipioRic;
    }

    public void setIdMunicipioRic(int idMunicipioRic) {
        this.idMunicipioRic = idMunicipioRic;
    }

    public Timestamp getDataExpedicaoRic() {
        return dataExpedicaoRic;
    }

    public void setDataExpedicaoRic(Timestamp dataExpedicaoRic) {
        this.dataExpedicaoRic = dataExpedicaoRic;
    }

    public String getCpfFormatado() {
        return cpfFormatado;
    }

    public void setCpfFormatado(String cpfFormatado) {
        this.cpfFormatado = cpfFormatado;
    }

    public String getOrgaoEmissorCnh() {
        return orgaoEmissorCnh;
    }

    public void setOrgaoEmissorCnh(String orgaoEmissorCnh) {
        this.orgaoEmissorCnh = orgaoEmissorCnh;
    }

    public Timestamp getDataExpedicaoRegistroProfissional() {
        return dataExpedicaoRegistroProfissional;
    }

    public void setDataExpedicaoRegistroProfissional(Timestamp dataExpedicaoRegistroProfissional) {
        this.dataExpedicaoRegistroProfissional = dataExpedicaoRegistroProfissional;
    }

    public Timestamp getDataValidadeRegistroProfissional() {
        return dataValidadeRegistroProfissional;
    }

    public void setDataValidadeRegistroProfissional(Timestamp dataValidadeRegistroProfissional) {
        this.dataValidadeRegistroProfissional = dataValidadeRegistroProfissional;
    }

    public String getNumeroRegistroNacionalEstrangeiro() {
        return numeroRegistroNacionalEstrangeiro;
    }

    public void setNumeroRegistroNacionalEstrangeiro(String numeroRegistroNacionalEstrangeiro) {
        this.numeroRegistroNacionalEstrangeiro = numeroRegistroNacionalEstrangeiro;
    }

    public int getIdPaisNacionalidade() {
        return idPaisNacionalidade;
    }

    public void setIdPaisNacionalidade(int idPaisNacionalidade) {
        this.idPaisNacionalidade = idPaisNacionalidade;
    }

    public String getListaIdTipoDeficienciaEsocial() {
        return listaIdTipoDeficienciaEsocial;
    }

    public void setListaIdTipoDeficienciaEsocial(String listaIdTipoDeficienciaEsocial) {
        this.listaIdTipoDeficienciaEsocial = listaIdTipoDeficienciaEsocial;
    }

    public String getObervacaoDeficiencia() {
        return obervacaoDeficiencia;
    }

    public void setObervacaoDeficiencia(String obervacaoDeficiencia) {
        this.obervacaoDeficiencia = obervacaoDeficiencia;
    }

    public String getNumeroPortariaNaturalizacao() {
        return numeroPortariaNaturalizacao;
    }

    public void setNumeroPortariaNaturalizacao(String numeroPortariaNaturalizacao) {
        this.numeroPortariaNaturalizacao = numeroPortariaNaturalizacao;
    }

    public int getIdIndicadorInscricaoEstadualNfe() {
        return idIndicadorInscricaoEstadualNfe;
    }

    public void setIdIndicadorInscricaoEstadualNfe(int idIndicadorInscricaoEstadualNfe) {
        this.idIndicadorInscricaoEstadualNfe = idIndicadorInscricaoEstadualNfe;
    }

    public Timestamp getDataPrimeiraHabilitacao() {
        return dataPrimeiraHabilitacao;
    }

    public void setDataPrimeiraHabilitacao(Timestamp dataPrimeiraHabilitacao) {
        this.dataPrimeiraHabilitacao = dataPrimeiraHabilitacao;
    }

    public int getIdClassificacaoTrabalhadorEstrangeiro() {
        return idClassificacaoTrabalhadorEstrangeiro;
    }

    public void setIdClassificacaoTrabalhadorEstrangeiro(int idClassificacaoTrabalhadorEstrangeiro) {
        this.idClassificacaoTrabalhadorEstrangeiro = idClassificacaoTrabalhadorEstrangeiro;
    }  
    
}