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
public class FolhaRegistroNG {
    int idpessoaregistro;
    String codigoregistro;
    boolean inddemissao;
    Timestamp datademissao;
    Timestamp dataadmissao;
    Timestamp dataopcaofgts;
    int idtipoadmissao;
    int idtipocontratacao;
    String numeromatricula;
    int numerodiascontratoexperiencia;
    int numerodiasprorrogacaocontratoexperiencia;
    int idtipoadmissaocaged;
    String numerocontafgts;
    int numerodvcontafgts;
    boolean indalteracaosalarial;
    boolean indalvarajudicial;
    boolean inddireitoreciproco;
    String numeroregistro;
    int idreajustetipo;
    Double valorreajuste;
    int idcategoriasefip;
    Timestamp dataultimoexamemedico;
    int numeromesesintervaloexamemedico;
    int idvinculoempregaticio;
    Timestamp dataconcessaobeneficio;
    Double percentualadiantamento;
    String observacaoregistro;
    int idowner;
    Timestamp datainicioperiodoaquisitivoferiaspendente;
    Timestamp datafimperiodoaquisitivoferiaspendente;
    Timestamp datavigenciaalteracaosalarial;
    boolean indrecolheinss;
    boolean indrecebe13salario;
    boolean indcomissionista;
    boolean indrecolhefgts;
    boolean indrecolheprevidencia;
    boolean indrecolheirrf;
    boolean indconsolidarfolhas;
    int idtipocomissionista;
    boolean indinformarrais;
    boolean indcentralizainss;
    Timestamp dataultimacontribuicaosindical;
    int iddadospessoa;
    String descricaocomplementosalario;
    boolean indsindicalizado;
    boolean indregraapropriacao;
    int idtiporegra;
    String fichario;
    int idtipodiatrabalhado;
    boolean indconsiderartambemmesesferiasafastamento;
    boolean indconsiderartambemmesadmissao;
    int ordemvinculado;
    int idregistroorigem;
    Timestamp datatransferencia;
    int indtipoinclusao;
    int indmotivocontratacao;
    String justificativacontratacao;
    String justificativaprorrogacao;
    int indaposentado;
    boolean indconsiderardiasmesfolhaferias;
    int idregistrosubstituido;
    int idtipotransferenciaentreempresa;
    int idonuscessao;
    String observacaosucessao;
    boolean indsegurodesemprego;
    boolean indstatuscagedadmissional;
    String statusrecebimento;
    boolean indconsiderardiasmesapropriacaohoras;
    boolean inddescontarcontribuicaosindicalreferenteanoadmissao;
    boolean indvisualizadadosonline;
    boolean indcontribuicaosindical;
    boolean inddescontarsestsenat;
    boolean indconsiderarfolharescisao;
    boolean indquebracaixa;

    public int getIdpessoaregistro() {
        return idpessoaregistro;
    }

    public void setIdpessoaregistro(int idpessoaregistro) {
        this.idpessoaregistro = idpessoaregistro;
    }

    public String getCodigoregistro() {
        return codigoregistro;
    }

    public void setCodigoregistro(String codigoregistro) {
        this.codigoregistro = codigoregistro;
    }

    public boolean isInddemissao() {
        return inddemissao;
    }

    public void setInddemissao(boolean inddemissao) {
        this.inddemissao = inddemissao;
    }

    public Timestamp getDatademissao() {
        return datademissao;
    }

    public void setDatademissao(Timestamp datademissao) {
        this.datademissao = datademissao;
    }

    public Timestamp getDataadmissao() {
        return dataadmissao;
    }

    public void setDataadmissao(Timestamp dataadmissao) {
        this.dataadmissao = dataadmissao;
    }

    public Timestamp getDataopcaofgts() {
        return dataopcaofgts;
    }

    public void setDataopcaofgts(Timestamp dataopcaofgts) {
        this.dataopcaofgts = dataopcaofgts;
    }

    public int getIdtipoadmissao() {
        return idtipoadmissao;
    }

    public void setIdtipoadmissao(int idtipoadmissao) {
        this.idtipoadmissao = idtipoadmissao;
    }

    public int getIdtipocontratacao() {
        return idtipocontratacao;
    }

    public void setIdtipocontratacao(int idtipocontratacao) {
        this.idtipocontratacao = idtipocontratacao;
    }

    public String getNumeromatricula() {
        return numeromatricula;
    }

    public void setNumeromatricula(String numeromatricula) {
        this.numeromatricula = numeromatricula;
    }

    public int getNumerodiascontratoexperiencia() {
        return numerodiascontratoexperiencia;
    }

    public void setNumerodiascontratoexperiencia(int numerodiascontratoexperiencia) {
        this.numerodiascontratoexperiencia = numerodiascontratoexperiencia;
    }

    public int getNumerodiasprorrogacaocontratoexperiencia() {
        return numerodiasprorrogacaocontratoexperiencia;
    }

    public void setNumerodiasprorrogacaocontratoexperiencia(int numerodiasprorrogacaocontratoexperiencia) {
        this.numerodiasprorrogacaocontratoexperiencia = numerodiasprorrogacaocontratoexperiencia;
    }

    public int getIdtipoadmissaocaged() {
        return idtipoadmissaocaged;
    }

    public void setIdtipoadmissaocaged(int idtipoadmissaocaged) {
        this.idtipoadmissaocaged = idtipoadmissaocaged;
    }

    public String getNumerocontafgts() {
        return numerocontafgts;
    }

    public void setNumerocontafgts(String numerocontafgts) {
        this.numerocontafgts = numerocontafgts;
    }

    public int getNumerodvcontafgts() {
        return numerodvcontafgts;
    }

    public void setNumerodvcontafgts(int numerodvcontafgts) {
        this.numerodvcontafgts = numerodvcontafgts;
    }

    public boolean isIndalteracaosalarial() {
        return indalteracaosalarial;
    }

    public void setIndalteracaosalarial(boolean indalteracaosalarial) {
        this.indalteracaosalarial = indalteracaosalarial;
    }

    public boolean isIndalvarajudicial() {
        return indalvarajudicial;
    }

    public void setIndalvarajudicial(boolean indalvarajudicial) {
        this.indalvarajudicial = indalvarajudicial;
    }

    public boolean isInddireitoreciproco() {
        return inddireitoreciproco;
    }

    public void setInddireitoreciproco(boolean inddireitoreciproco) {
        this.inddireitoreciproco = inddireitoreciproco;
    }

    public String getNumeroregistro() {
        return numeroregistro;
    }

    public void setNumeroregistro(String numeroregistro) {
        this.numeroregistro = numeroregistro;
    }

    public int getIdreajustetipo() {
        return idreajustetipo;
    }

    public void setIdreajustetipo(int idreajustetipo) {
        this.idreajustetipo = idreajustetipo;
    }

    public Double getValorreajuste() {
        return valorreajuste;
    }

    public void setValorreajuste(Double valorreajuste) {
        this.valorreajuste = valorreajuste;
    }

    public int getIdcategoriasefip() {
        return idcategoriasefip;
    }

    public void setIdcategoriasefip(int idcategoriasefip) {
        this.idcategoriasefip = idcategoriasefip;
    }

    public Timestamp getDataultimoexamemedico() {
        return dataultimoexamemedico;
    }

    public void setDataultimoexamemedico(Timestamp dataultimoexamemedico) {
        this.dataultimoexamemedico = dataultimoexamemedico;
    }

    public int getNumeromesesintervaloexamemedico() {
        return numeromesesintervaloexamemedico;
    }

    public void setNumeromesesintervaloexamemedico(int numeromesesintervaloexamemedico) {
        this.numeromesesintervaloexamemedico = numeromesesintervaloexamemedico;
    }

    public int getIdvinculoempregaticio() {
        return idvinculoempregaticio;
    }

    public void setIdvinculoempregaticio(int idvinculoempregaticio) {
        this.idvinculoempregaticio = idvinculoempregaticio;
    }

    public Timestamp getDataconcessaobeneficio() {
        return dataconcessaobeneficio;
    }

    public void setDataconcessaobeneficio(Timestamp dataconcessaobeneficio) {
        this.dataconcessaobeneficio = dataconcessaobeneficio;
    }

    public Double getPercentualadiantamento() {
        return percentualadiantamento;
    }

    public void setPercentualadiantamento(Double percentualadiantamento) {
        this.percentualadiantamento = percentualadiantamento;
    }

    public String getObservacaoregistro() {
        return observacaoregistro;
    }

    public void setObservacaoregistro(String observacaoregistro) {
        this.observacaoregistro = observacaoregistro;
    }

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

    public Timestamp getDatainicioperiodoaquisitivoferiaspendente() {
        return datainicioperiodoaquisitivoferiaspendente;
    }

    public void setDatainicioperiodoaquisitivoferiaspendente(Timestamp datainicioperiodoaquisitivoferiaspendente) {
        this.datainicioperiodoaquisitivoferiaspendente = datainicioperiodoaquisitivoferiaspendente;
    }

    public Timestamp getDatafimperiodoaquisitivoferiaspendente() {
        return datafimperiodoaquisitivoferiaspendente;
    }

    public void setDatafimperiodoaquisitivoferiaspendente(Timestamp datafimperiodoaquisitivoferiaspendente) {
        this.datafimperiodoaquisitivoferiaspendente = datafimperiodoaquisitivoferiaspendente;
    }

    public Timestamp getDatavigenciaalteracaosalarial() {
        return datavigenciaalteracaosalarial;
    }

    public void setDatavigenciaalteracaosalarial(Timestamp datavigenciaalteracaosalarial) {
        this.datavigenciaalteracaosalarial = datavigenciaalteracaosalarial;
    }

    public boolean isIndrecolheinss() {
        return indrecolheinss;
    }

    public void setIndrecolheinss(boolean indrecolheinss) {
        this.indrecolheinss = indrecolheinss;
    }

    public boolean isIndrecebe13salario() {
        return indrecebe13salario;
    }

    public void setIndrecebe13salario(boolean indrecebe13salario) {
        this.indrecebe13salario = indrecebe13salario;
    }

    public boolean isIndcomissionista() {
        return indcomissionista;
    }

    public void setIndcomissionista(boolean indcomissionista) {
        this.indcomissionista = indcomissionista;
    }

    public boolean isIndrecolhefgts() {
        return indrecolhefgts;
    }

    public void setIndrecolhefgts(boolean indrecolhefgts) {
        this.indrecolhefgts = indrecolhefgts;
    }

    public boolean isIndrecolheprevidencia() {
        return indrecolheprevidencia;
    }

    public void setIndrecolheprevidencia(boolean indrecolheprevidencia) {
        this.indrecolheprevidencia = indrecolheprevidencia;
    }

    public boolean isIndrecolheirrf() {
        return indrecolheirrf;
    }

    public void setIndrecolheirrf(boolean indrecolheirrf) {
        this.indrecolheirrf = indrecolheirrf;
    }

    public boolean isIndconsolidarfolhas() {
        return indconsolidarfolhas;
    }

    public void setIndconsolidarfolhas(boolean indconsolidarfolhas) {
        this.indconsolidarfolhas = indconsolidarfolhas;
    }

    public int getIdtipocomissionista() {
        return idtipocomissionista;
    }

    public void setIdtipocomissionista(int idtipocomissionista) {
        this.idtipocomissionista = idtipocomissionista;
    }

    public boolean isIndinformarrais() {
        return indinformarrais;
    }

    public void setIndinformarrais(boolean indinformarrais) {
        this.indinformarrais = indinformarrais;
    }

    public boolean isIndcentralizainss() {
        return indcentralizainss;
    }

    public void setIndcentralizainss(boolean indcentralizainss) {
        this.indcentralizainss = indcentralizainss;
    }

    public Timestamp getDataultimacontribuicaosindical() {
        return dataultimacontribuicaosindical;
    }

    public void setDataultimacontribuicaosindical(Timestamp dataultimacontribuicaosindical) {
        this.dataultimacontribuicaosindical = dataultimacontribuicaosindical;
    }

    public int getIddadospessoa() {
        return iddadospessoa;
    }

    public void setIddadospessoa(int iddadospessoa) {
        this.iddadospessoa = iddadospessoa;
    }

    public String getDescricaocomplementosalario() {
        return descricaocomplementosalario;
    }

    public void setDescricaocomplementosalario(String descricaocomplementosalario) {
        this.descricaocomplementosalario = descricaocomplementosalario;
    }

    public boolean isIndsindicalizado() {
        return indsindicalizado;
    }

    public void setIndsindicalizado(boolean indsindicalizado) {
        this.indsindicalizado = indsindicalizado;
    }

    public boolean isIndregraapropriacao() {
        return indregraapropriacao;
    }

    public void setIndregraapropriacao(boolean indregraapropriacao) {
        this.indregraapropriacao = indregraapropriacao;
    }

    public int getIdtiporegra() {
        return idtiporegra;
    }

    public void setIdtiporegra(int idtiporegra) {
        this.idtiporegra = idtiporegra;
    }

    public String getFichario() {
        return fichario;
    }

    public void setFichario(String fichario) {
        this.fichario = fichario;
    }

    public int getIdtipodiatrabalhado() {
        return idtipodiatrabalhado;
    }

    public void setIdtipodiatrabalhado(int idtipodiatrabalhado) {
        this.idtipodiatrabalhado = idtipodiatrabalhado;
    }

    public boolean isIndconsiderartambemmesesferiasafastamento() {
        return indconsiderartambemmesesferiasafastamento;
    }

    public void setIndconsiderartambemmesesferiasafastamento(boolean indconsiderartambemmesesferiasafastamento) {
        this.indconsiderartambemmesesferiasafastamento = indconsiderartambemmesesferiasafastamento;
    }

    public boolean isIndconsiderartambemmesadmissao() {
        return indconsiderartambemmesadmissao;
    }

    public void setIndconsiderartambemmesadmissao(boolean indconsiderartambemmesadmissao) {
        this.indconsiderartambemmesadmissao = indconsiderartambemmesadmissao;
    }

    public int getOrdemvinculado() {
        return ordemvinculado;
    }

    public void setOrdemvinculado(int ordemvinculado) {
        this.ordemvinculado = ordemvinculado;
    }

    public int getIdregistroorigem() {
        return idregistroorigem;
    }

    public void setIdregistroorigem(int idregistroorigem) {
        this.idregistroorigem = idregistroorigem;
    }

    public Timestamp getDatatransferencia() {
        return datatransferencia;
    }

    public void setDatatransferencia(Timestamp datatransferencia) {
        this.datatransferencia = datatransferencia;
    }

    public int getIndtipoinclusao() {
        return indtipoinclusao;
    }

    public void setIndtipoinclusao(int indtipoinclusao) {
        this.indtipoinclusao = indtipoinclusao;
    }

    public int getIndmotivocontratacao() {
        return indmotivocontratacao;
    }

    public void setIndmotivocontratacao(int indmotivocontratacao) {
        this.indmotivocontratacao = indmotivocontratacao;
    }

    public String getJustificativacontratacao() {
        return justificativacontratacao;
    }

    public void setJustificativacontratacao(String justificativacontratacao) {
        this.justificativacontratacao = justificativacontratacao;
    }

    public String getJustificativaprorrogacao() {
        return justificativaprorrogacao;
    }

    public void setJustificativaprorrogacao(String justificativaprorrogacao) {
        this.justificativaprorrogacao = justificativaprorrogacao;
    }

    public int getIndaposentado() {
        return indaposentado;
    }

    public void setIndaposentado(int indaposentado) {
        this.indaposentado = indaposentado;
    }

    public boolean isIndconsiderardiasmesfolhaferias() {
        return indconsiderardiasmesfolhaferias;
    }

    public void setIndconsiderardiasmesfolhaferias(boolean indconsiderardiasmesfolhaferias) {
        this.indconsiderardiasmesfolhaferias = indconsiderardiasmesfolhaferias;
    }

    public int getIdregistrosubstituido() {
        return idregistrosubstituido;
    }

    public void setIdregistrosubstituido(int idregistrosubstituido) {
        this.idregistrosubstituido = idregistrosubstituido;
    }

    public int getIdtipotransferenciaentreempresa() {
        return idtipotransferenciaentreempresa;
    }

    public void setIdtipotransferenciaentreempresa(int idtipotransferenciaentreempresa) {
        this.idtipotransferenciaentreempresa = idtipotransferenciaentreempresa;
    }

    public int getIdonuscessao() {
        return idonuscessao;
    }

    public void setIdonuscessao(int idonuscessao) {
        this.idonuscessao = idonuscessao;
    }

    public String getObservacaosucessao() {
        return observacaosucessao;
    }

    public void setObservacaosucessao(String observacaosucessao) {
        this.observacaosucessao = observacaosucessao;
    }

    public boolean isIndsegurodesemprego() {
        return indsegurodesemprego;
    }

    public void setIndsegurodesemprego(boolean indsegurodesemprego) {
        this.indsegurodesemprego = indsegurodesemprego;
    }

    public boolean isIndstatuscagedadmissional() {
        return indstatuscagedadmissional;
    }

    public void setIndstatuscagedadmissional(boolean indstatuscagedadmissional) {
        this.indstatuscagedadmissional = indstatuscagedadmissional;
    }

    public String getStatusrecebimento() {
        return statusrecebimento;
    }

    public void setStatusrecebimento(String statusrecebimento) {
        this.statusrecebimento = statusrecebimento;
    }

    public boolean isIndconsiderardiasmesapropriacaohoras() {
        return indconsiderardiasmesapropriacaohoras;
    }

    public void setIndconsiderardiasmesapropriacaohoras(boolean indconsiderardiasmesapropriacaohoras) {
        this.indconsiderardiasmesapropriacaohoras = indconsiderardiasmesapropriacaohoras;
    }

    public boolean isInddescontarcontribuicaosindicalreferenteanoadmissao() {
        return inddescontarcontribuicaosindicalreferenteanoadmissao;
    }

    public void setInddescontarcontribuicaosindicalreferenteanoadmissao(boolean inddescontarcontribuicaosindicalreferenteanoadmissao) {
        this.inddescontarcontribuicaosindicalreferenteanoadmissao = inddescontarcontribuicaosindicalreferenteanoadmissao;
    }

    public boolean isIndvisualizadadosonline() {
        return indvisualizadadosonline;
    }

    public void setIndvisualizadadosonline(boolean indvisualizadadosonline) {
        this.indvisualizadadosonline = indvisualizadadosonline;
    }

    public boolean isIndcontribuicaosindical() {
        return indcontribuicaosindical;
    }

    public void setIndcontribuicaosindical(boolean indcontribuicaosindical) {
        this.indcontribuicaosindical = indcontribuicaosindical;
    }

    public boolean isInddescontarsestsenat() {
        return inddescontarsestsenat;
    }

    public void setInddescontarsestsenat(boolean inddescontarsestsenat) {
        this.inddescontarsestsenat = inddescontarsestsenat;
    }

    public boolean isIndconsiderarfolharescisao() {
        return indconsiderarfolharescisao;
    }

    public void setIndconsiderarfolharescisao(boolean indconsiderarfolharescisao) {
        this.indconsiderarfolharescisao = indconsiderarfolharescisao;
    }

    public boolean isIndquebracaixa() {
        return indquebracaixa;
    }

    public void setIndquebracaixa(boolean indquebracaixa) {
        this.indquebracaixa = indquebracaixa;
    }
    
    
}
