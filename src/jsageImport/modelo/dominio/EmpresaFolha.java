/*
 * Pojo para os dados da folha da empresa
 */
package jsageImport.modelo.dominio;

import java.sql.Timestamp;

/**
 * @author Gustavo Dias
 * Criação: 21/10/2016
 * Última modificação: 22/10/2016
 * Modificado por: Gustavo Dias
 */
public class EmpresaFolha {
    //tabela bpm_dadosempresafolha
    int iddadosempresafolha;
    int idpessoa;
    int idfpas;
    int idcodigosterceiros;
    int idtipoarredondamento;
    String contafgts;
    int idcodigogps;
    Double percentualrat;
    boolean indcalcularpis;
    boolean indencargosgpsporcc;
    boolean indparticipapat;
    Double percentualterceiro;
    int idoptantesimples;
    Double percentualinss;
    boolean indativo;
    boolean indfiliadasindicato;
    boolean indapropriacaohorasfatogerador;
    boolean indsuprimirapropriacaofatogerador;
    String idregimetributario;
    boolean indexcluido;
    int idtipoempresalei12546;
    boolean indefetuarecolhimentopatronallei12546;
    int idtipovigencialei12546;
    boolean indeduzirdiasdescontodsrcalculodiasuteis;
    boolean indconsideraradiantamento13provisao;
    boolean indcopafifa;
    int idaliquotalei12546;
    boolean indinssempresaintegralfolhaeprovisaoferias;
    boolean indvisualizadadosonline;
    boolean inddarfindividual;
    boolean indconsolidardarfporcodigo;
    //bpm_dadosempresafolhaparametro13salario    
    int idtipocontagemmesadiantamento;
    int idtipocalculomediaadiantamento;
    boolean indconsiderarmescorrenteadiantamento;
    boolean inddeduzirvaloroutroadiantamento;
    boolean indconsiderarmetadereferenciaeventoadiantamento;
    Double percentualcalculoadiantamento;
    int idtipocontagemmes13salario;
    int idtipocalculomedia13salario;
    boolean indconsiderarmescorrente13salario;
    boolean indpagarproporcionalacidentetrabalho;
    int idtipocalculomediacomplemento;
    boolean indconsiderarmescorrentecomplemento;
    boolean indbaixapagto13salario;
    boolean indativoSalario;
    //bpm_dadosempresafolhaparametroesocial
    int idclassificacaotributaria;
    int idcooperativa;
    int idconstrutora;
    boolean indprocessorat;
    int idtipoprocessorat;
    String numeroprocessorat;
    boolean indprocessofap;
    int idtipoprocessofap;
    String numeroprocessofap;
    boolean inddadosisencao;
    int idcertificador;
    Timestamp dataemissaocertificado;
    Timestamp datarenovacaocertificado;
    Timestamp datavencimentocertificado;
    Timestamp datapublicacaodoucertificado;
    String numerocertificado;
    String numeroprotocolorenovacaocertificado;
    String numeropaginadoucertificado;
    boolean inddadosinternacionalizacao;
    int idacordointernacionalisencaomulta;
    boolean indsocioostensivo;
    int idsituacaoespecial;
    int iddesoneracaofolha;
    int idregistroeletronicoempregados;
    boolean indinformacoescomplementares;
    boolean indprocessosjudiciaisrelativooutrasentidadesfundos;
    String numerosiafi;
    int idsituacaopf;
    boolean indativoEsocial;
    //bpm_dadosempresafolhaparametroferias
    boolean inddescontoproporcionalinss;
    boolean inddesconsiderarfalta;
    boolean inddescontarcontribuicaosindical;
    boolean indmostrardataretornorecibo;
    boolean inddataretornodianaoutil;
    boolean indemitiralertavencimentoavisoprevio;
    boolean indconsiderarabonopecuniario;
    int quantidadeanosdireitolicensapremio;
    boolean indagruparvinculado;
    boolean indpagarsalariofamiliaabonofamilia;
    boolean indpagarlicencaremuneradafuncionariomaisumano;
    boolean indmudarperiodoaquisitivo;
    boolean indpagarlicencaremunerada;
    boolean indpagaradiantamento13salario;
    boolean indativoFerias;      
    boolean indconsiderardiasfaltasperiodofatosgeradores;
    boolean indpagarmediaferiasverbaseparada;
    boolean indconsiderarvalorinssferiasverbaseparada;
    boolean indconsiderarbaixaproporcionalprovisaoferias;
    boolean indconsiderarbaixaprovisaoferiasconformevaloresferias;
    //bpm_dadosempresafolhaparametrogeral
    boolean inddescontarinss;
    boolean indgeralmostrarverbaferias;
    boolean indutilizarcasadecimal;
    boolean indcontroleautomaticosaldo;
    boolean indhorascalculosalariofamilia;
    boolean indabonarirmenor;
    int idtipodiatrabalhado;
    int idtipoarredondamentoGeral;
    Double valorarredondamento;
    int idtipocalculomedia;
    boolean indmescorrentecalculomedia;
    boolean indbasecalculomediadsr;
    boolean inddeduzirfaltadsr;
    boolean indcalculocomissionistadsr;
    int diainicioapuracaodsr;
    int diafimapuracaodsr;
    boolean indparalisacaoafastamentodoenca;
    boolean indutilizareventoocorridomesrecisaocalculomedia;
    boolean indparalisacaocontagemcontrato;
    boolean indpagardescansoindenizadodemissao;
    boolean indbeneficiotransporteabaterdiasferias;
    boolean indbeneficiotransporteabaterdiasafastamento;
    boolean indbeneficiotransporteproporcionaljornadareduzida;
    boolean indbeneficioticketabaterdiasferias;
    boolean indbeneficioticketabaterdiasafastamento;
    boolean indbeneficioticketproporcionaljornadareduzida;
    boolean indlancarverbadescontodsrmensalistaquinzenalista;
    boolean indlancarverbadescontodsrhoristasemanalistadiarista;
    boolean indmensalista;
    boolean indsemanalista;
    boolean indhorista;
    boolean indquinzenalista;
    boolean inddiarista;
    boolean indtarefeiro;
    boolean indconsiderartambemmesesferiasafastamento;
    int idtipoadiantamentoadmissao;
    int numerominimodiatrabalhoadiantamentoadmissao;
    int idpagamentofolhamensal;
    int diainicioapuracaofatogerador;
    int diafimapuracaofatogerador;
    boolean indativoGeral;
    boolean indconsiderararredondamentofolhaferias;
    int idconsiderarparacalculodsr;
    boolean indconsiderartambemmesadmissao;
    boolean indcontrolarmotivocalculosalariofamilia;
    boolean indconsiderarverbasrescisaomedia;
    boolean indbeneficiotransporteabaterfaltas;
    boolean indconsiderardiasapuracaodsrintegral;
    boolean indverbaprogramadaperiodoafastamento;
    boolean indsepararumavoferiasindenizado;
    boolean indmediacalculoferiasindenizado;
    int idtipoadiantamentoferias;
    int idtipoadiantamentoafastamento;
    int numerominimodiatrabalhoadiantamentoferias;
    int numerominimodiatrabalhoadiantamentoafastamento;
    boolean indpagarfaltasjustificadasseparadassalario;
    boolean indtrabalhotemporario;
    boolean indconsiderarsalfamcalculocontroleautomaticosaldo;
    boolean indconsiderararredondamentoparaautonomostransportadores;
    boolean indconsiderararredondamentoparasocios;
    boolean indlimitarcontagemdiastrabalhadosdatair;
    boolean indcalculardsrsobreferiadosverba;
    boolean indconsiderardiasmesfolhaferias;
    boolean indgerarfluxocaixa;
    boolean indclientedireto;
    boolean indconsiderardiasmesapropriacaohoras;
    Timestamp datainicioservico;
    boolean indpreencherdatairrfautomaticamentefolhamensal;
    int diapagamentoirrffolhamensal;
    boolean indtipodiairrffolhamensal;
    boolean indantecipadiautildatairrf;
    boolean indconsiderasabadodiautil;
    boolean indconsiderarlimitemaximoavisopreviotrabalhado;
    boolean indoperacaoparalelo;
    boolean indconsiderar15diaspagopelaempresareferenteafastamentos;
    Double percentualadiantamento;
    boolean indcalcularadiantamentodiasmesesdiferente30empregadoshoristas;
    boolean indconsiderarfolharescisao;
    boolean indgerarferiasproporcionaisrescisoesjustacausa;
    boolean indbeneficioticketabaterfaltas;
    Timestamp datafimservico;
    Double percentualdescontoBeneficioFolha;
    //bpm_dadosempresafolhasindicato
    int iddadossindicato;
    int ano;
    Double valorcontribuicaosindical;
    Double valorcontribuicaoassociativa;
    Double valorcontribuicaoassistencial;
    Double valorcontribuicaoconfederativa;
    boolean indativoSindicato;
    boolean indprincipal;

    public int getIddadosempresafolha() {
        return iddadosempresafolha;
    }

    public void setIddadosempresafolha(int iddadosempresafolha) {
        this.iddadosempresafolha = iddadosempresafolha;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getIdfpas() {
        return idfpas;
    }

    public void setIdfpas(int idfpas) {
        this.idfpas = idfpas;
    }

    public int getIdcodigosterceiros() {
        return idcodigosterceiros;
    }

    public void setIdcodigosterceiros(int idcodigosterceiros) {
        this.idcodigosterceiros = idcodigosterceiros;
    }

    public int getIdtipoarredondamento() {
        return idtipoarredondamento;
    }

    public void setIdtipoarredondamento(int idtipoarredondamento) {
        this.idtipoarredondamento = idtipoarredondamento;
    }

    public String getContafgts() {
        return contafgts;
    }

    public void setContafgts(String contafgts) {
        this.contafgts = contafgts;
    }

    public int getIdcodigogps() {
        return idcodigogps;
    }

    public void setIdcodigogps(int idcodigogps) {
        this.idcodigogps = idcodigogps;
    }

    public Double getPercentualrat() {
        return percentualrat;
    }

    public void setPercentualrat(Double percentualrat) {
        this.percentualrat = percentualrat;
    }

    public boolean isIndcalcularpis() {
        return indcalcularpis;
    }

    public void setIndcalcularpis(boolean indcalcularpis) {
        this.indcalcularpis = indcalcularpis;
    }

    public boolean isIndencargosgpsporcc() {
        return indencargosgpsporcc;
    }

    public void setIndencargosgpsporcc(boolean indencargosgpsporcc) {
        this.indencargosgpsporcc = indencargosgpsporcc;
    }

    public boolean isIndparticipapat() {
        return indparticipapat;
    }

    public void setIndparticipapat(boolean indparticipapat) {
        this.indparticipapat = indparticipapat;
    }

    public Double getPercentualterceiro() {
        return percentualterceiro;
    }

    public void setPercentualterceiro(Double percentualterceiro) {
        this.percentualterceiro = percentualterceiro;
    }

    public int getIdoptantesimples() {
        return idoptantesimples;
    }

    public void setIdoptantesimples(int idoptantesimples) {
        this.idoptantesimples = idoptantesimples;
    }

    public Double getPercentualinss() {
        return percentualinss;
    }

    public void setPercentualinss(Double percentualinss) {
        this.percentualinss = percentualinss;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }

    public boolean isIndfiliadasindicato() {
        return indfiliadasindicato;
    }

    public void setIndfiliadasindicato(boolean indfiliadasindicato) {
        this.indfiliadasindicato = indfiliadasindicato;
    }

    public boolean isIndapropriacaohorasfatogerador() {
        return indapropriacaohorasfatogerador;
    }

    public void setIndapropriacaohorasfatogerador(boolean indapropriacaohorasfatogerador) {
        this.indapropriacaohorasfatogerador = indapropriacaohorasfatogerador;
    }

    public boolean isIndsuprimirapropriacaofatogerador() {
        return indsuprimirapropriacaofatogerador;
    }

    public void setIndsuprimirapropriacaofatogerador(boolean indsuprimirapropriacaofatogerador) {
        this.indsuprimirapropriacaofatogerador = indsuprimirapropriacaofatogerador;
    }

    public String getIdregimetributario() {
        return idregimetributario;
    }

    public void setIdregimetributario(String idregimetributario) {
        this.idregimetributario = idregimetributario;
    }

    public boolean isIndexcluido() {
        return indexcluido;
    }

    public void setIndexcluido(boolean indexcluido) {
        this.indexcluido = indexcluido;
    }

    public int getIdtipoempresalei12546() {
        return idtipoempresalei12546;
    }

    public void setIdtipoempresalei12546(int idtipoempresalei12546) {
        this.idtipoempresalei12546 = idtipoempresalei12546;
    }

    public boolean isIndefetuarecolhimentopatronallei12546() {
        return indefetuarecolhimentopatronallei12546;
    }

    public void setIndefetuarecolhimentopatronallei12546(boolean indefetuarecolhimentopatronallei12546) {
        this.indefetuarecolhimentopatronallei12546 = indefetuarecolhimentopatronallei12546;
    }

    public int getIdtipovigencialei12546() {
        return idtipovigencialei12546;
    }

    public void setIdtipovigencialei12546(int idtipovigencialei12546) {
        this.idtipovigencialei12546 = idtipovigencialei12546;
    }

    public boolean isIndeduzirdiasdescontodsrcalculodiasuteis() {
        return indeduzirdiasdescontodsrcalculodiasuteis;
    }

    public void setIndeduzirdiasdescontodsrcalculodiasuteis(boolean indeduzirdiasdescontodsrcalculodiasuteis) {
        this.indeduzirdiasdescontodsrcalculodiasuteis = indeduzirdiasdescontodsrcalculodiasuteis;
    }

    public boolean isIndconsideraradiantamento13provisao() {
        return indconsideraradiantamento13provisao;
    }

    public void setIndconsideraradiantamento13provisao(boolean indconsideraradiantamento13provisao) {
        this.indconsideraradiantamento13provisao = indconsideraradiantamento13provisao;
    }

    public boolean isIndcopafifa() {
        return indcopafifa;
    }

    public void setIndcopafifa(boolean indcopafifa) {
        this.indcopafifa = indcopafifa;
    }

    public int getIdaliquotalei12546() {
        return idaliquotalei12546;
    }

    public void setIdaliquotalei12546(int idaliquotalei12546) {
        this.idaliquotalei12546 = idaliquotalei12546;
    }

    public boolean isIndinssempresaintegralfolhaeprovisaoferias() {
        return indinssempresaintegralfolhaeprovisaoferias;
    }

    public void setIndinssempresaintegralfolhaeprovisaoferias(boolean indinssempresaintegralfolhaeprovisaoferias) {
        this.indinssempresaintegralfolhaeprovisaoferias = indinssempresaintegralfolhaeprovisaoferias;
    }

    public boolean isIndvisualizadadosonline() {
        return indvisualizadadosonline;
    }

    public void setIndvisualizadadosonline(boolean indvisualizadadosonline) {
        this.indvisualizadadosonline = indvisualizadadosonline;
    }

    public boolean isInddarfindividual() {
        return inddarfindividual;
    }

    public void setInddarfindividual(boolean inddarfindividual) {
        this.inddarfindividual = inddarfindividual;
    }

    public boolean isIndconsolidardarfporcodigo() {
        return indconsolidardarfporcodigo;
    }

    public void setIndconsolidardarfporcodigo(boolean indconsolidardarfporcodigo) {
        this.indconsolidardarfporcodigo = indconsolidardarfporcodigo;
    }

    public int getIdtipocontagemmesadiantamento() {
        return idtipocontagemmesadiantamento;
    }

    public void setIdtipocontagemmesadiantamento(int idtipocontagemmesadiantamento) {
        this.idtipocontagemmesadiantamento = idtipocontagemmesadiantamento;
    }

    public int getIdtipocalculomediaadiantamento() {
        return idtipocalculomediaadiantamento;
    }

    public void setIdtipocalculomediaadiantamento(int idtipocalculomediaadiantamento) {
        this.idtipocalculomediaadiantamento = idtipocalculomediaadiantamento;
    }

    public boolean isIndconsiderarmescorrenteadiantamento() {
        return indconsiderarmescorrenteadiantamento;
    }

    public void setIndconsiderarmescorrenteadiantamento(boolean indconsiderarmescorrenteadiantamento) {
        this.indconsiderarmescorrenteadiantamento = indconsiderarmescorrenteadiantamento;
    }

    public boolean isInddeduzirvaloroutroadiantamento() {
        return inddeduzirvaloroutroadiantamento;
    }

    public void setInddeduzirvaloroutroadiantamento(boolean inddeduzirvaloroutroadiantamento) {
        this.inddeduzirvaloroutroadiantamento = inddeduzirvaloroutroadiantamento;
    }

    public boolean isIndconsiderarmetadereferenciaeventoadiantamento() {
        return indconsiderarmetadereferenciaeventoadiantamento;
    }

    public void setIndconsiderarmetadereferenciaeventoadiantamento(boolean indconsiderarmetadereferenciaeventoadiantamento) {
        this.indconsiderarmetadereferenciaeventoadiantamento = indconsiderarmetadereferenciaeventoadiantamento;
    }

    public Double getPercentualcalculoadiantamento() {
        return percentualcalculoadiantamento;
    }

    public void setPercentualcalculoadiantamento(Double percentualcalculoadiantamento) {
        this.percentualcalculoadiantamento = percentualcalculoadiantamento;
    }

    public int getIdtipocontagemmes13salario() {
        return idtipocontagemmes13salario;
    }

    public void setIdtipocontagemmes13salario(int idtipocontagemmes13salario) {
        this.idtipocontagemmes13salario = idtipocontagemmes13salario;
    }

    public int getIdtipocalculomedia13salario() {
        return idtipocalculomedia13salario;
    }

    public void setIdtipocalculomedia13salario(int idtipocalculomedia13salario) {
        this.idtipocalculomedia13salario = idtipocalculomedia13salario;
    }

    public boolean isIndconsiderarmescorrente13salario() {
        return indconsiderarmescorrente13salario;
    }

    public void setIndconsiderarmescorrente13salario(boolean indconsiderarmescorrente13salario) {
        this.indconsiderarmescorrente13salario = indconsiderarmescorrente13salario;
    }

    public boolean isIndpagarproporcionalacidentetrabalho() {
        return indpagarproporcionalacidentetrabalho;
    }

    public void setIndpagarproporcionalacidentetrabalho(boolean indpagarproporcionalacidentetrabalho) {
        this.indpagarproporcionalacidentetrabalho = indpagarproporcionalacidentetrabalho;
    }

    public int getIdtipocalculomediacomplemento() {
        return idtipocalculomediacomplemento;
    }

    public void setIdtipocalculomediacomplemento(int idtipocalculomediacomplemento) {
        this.idtipocalculomediacomplemento = idtipocalculomediacomplemento;
    }

    public boolean isIndconsiderarmescorrentecomplemento() {
        return indconsiderarmescorrentecomplemento;
    }

    public void setIndconsiderarmescorrentecomplemento(boolean indconsiderarmescorrentecomplemento) {
        this.indconsiderarmescorrentecomplemento = indconsiderarmescorrentecomplemento;
    }

    public boolean isIndbaixapagto13salario() {
        return indbaixapagto13salario;
    }

    public void setIndbaixapagto13salario(boolean indbaixapagto13salario) {
        this.indbaixapagto13salario = indbaixapagto13salario;
    }

    public boolean isIndativoSalario() {
        return indativoSalario;
    }

    public void setIndativoSalario(boolean indativoSalario) {
        this.indativoSalario = indativoSalario;
    }

    public int getIdclassificacaotributaria() {
        return idclassificacaotributaria;
    }

    public void setIdclassificacaotributaria(int idclassificacaotributaria) {
        this.idclassificacaotributaria = idclassificacaotributaria;
    }

    public int getIdcooperativa() {
        return idcooperativa;
    }

    public void setIdcooperativa(int idcooperativa) {
        this.idcooperativa = idcooperativa;
    }

    public int getIdconstrutora() {
        return idconstrutora;
    }

    public void setIdconstrutora(int idconstrutora) {
        this.idconstrutora = idconstrutora;
    }

    public boolean isIndprocessorat() {
        return indprocessorat;
    }

    public void setIndprocessorat(boolean indprocessorat) {
        this.indprocessorat = indprocessorat;
    }

    public int getIdtipoprocessorat() {
        return idtipoprocessorat;
    }

    public void setIdtipoprocessorat(int idtipoprocessorat) {
        this.idtipoprocessorat = idtipoprocessorat;
    }

    public String getNumeroprocessorat() {
        return numeroprocessorat;
    }

    public void setNumeroprocessorat(String numeroprocessorat) {
        this.numeroprocessorat = numeroprocessorat;
    }

    public boolean isIndprocessofap() {
        return indprocessofap;
    }

    public void setIndprocessofap(boolean indprocessofap) {
        this.indprocessofap = indprocessofap;
    }

    public int getIdtipoprocessofap() {
        return idtipoprocessofap;
    }

    public void setIdtipoprocessofap(int idtipoprocessofap) {
        this.idtipoprocessofap = idtipoprocessofap;
    }

    public String getNumeroprocessofap() {
        return numeroprocessofap;
    }

    public void setNumeroprocessofap(String numeroprocessofap) {
        this.numeroprocessofap = numeroprocessofap;
    }

    public boolean isInddadosisencao() {
        return inddadosisencao;
    }

    public void setInddadosisencao(boolean inddadosisencao) {
        this.inddadosisencao = inddadosisencao;
    }

    public int getIdcertificador() {
        return idcertificador;
    }

    public void setIdcertificador(int idcertificador) {
        this.idcertificador = idcertificador;
    }

    public Timestamp getDataemissaocertificado() {
        return dataemissaocertificado;
    }

    public void setDataemissaocertificado(Timestamp dataemissaocertificado) {
        this.dataemissaocertificado = dataemissaocertificado;
    }

    public Timestamp getDatarenovacaocertificado() {
        return datarenovacaocertificado;
    }

    public void setDatarenovacaocertificado(Timestamp datarenovacaocertificado) {
        this.datarenovacaocertificado = datarenovacaocertificado;
    }

    public Timestamp getDatavencimentocertificado() {
        return datavencimentocertificado;
    }

    public void setDatavencimentocertificado(Timestamp datavencimentocertificado) {
        this.datavencimentocertificado = datavencimentocertificado;
    }

    public Timestamp getDatapublicacaodoucertificado() {
        return datapublicacaodoucertificado;
    }

    public void setDatapublicacaodoucertificado(Timestamp datapublicacaodoucertificado) {
        this.datapublicacaodoucertificado = datapublicacaodoucertificado;
    }

    public String getNumerocertificado() {
        return numerocertificado;
    }

    public void setNumerocertificado(String numerocertificado) {
        this.numerocertificado = numerocertificado;
    }

    public String getNumeroprotocolorenovacaocertificado() {
        return numeroprotocolorenovacaocertificado;
    }

    public void setNumeroprotocolorenovacaocertificado(String numeroprotocolorenovacaocertificado) {
        this.numeroprotocolorenovacaocertificado = numeroprotocolorenovacaocertificado;
    }

    public String getNumeropaginadoucertificado() {
        return numeropaginadoucertificado;
    }

    public void setNumeropaginadoucertificado(String numeropaginadoucertificado) {
        this.numeropaginadoucertificado = numeropaginadoucertificado;
    }

    public boolean isInddadosinternacionalizacao() {
        return inddadosinternacionalizacao;
    }

    public void setInddadosinternacionalizacao(boolean inddadosinternacionalizacao) {
        this.inddadosinternacionalizacao = inddadosinternacionalizacao;
    }

    public int getIdacordointernacionalisencaomulta() {
        return idacordointernacionalisencaomulta;
    }

    public void setIdacordointernacionalisencaomulta(int idacordointernacionalisencaomulta) {
        this.idacordointernacionalisencaomulta = idacordointernacionalisencaomulta;
    }

    public boolean isIndsocioostensivo() {
        return indsocioostensivo;
    }

    public void setIndsocioostensivo(boolean indsocioostensivo) {
        this.indsocioostensivo = indsocioostensivo;
    }

    public int getIdsituacaoespecial() {
        return idsituacaoespecial;
    }

    public void setIdsituacaoespecial(int idsituacaoespecial) {
        this.idsituacaoespecial = idsituacaoespecial;
    }

    public int getIddesoneracaofolha() {
        return iddesoneracaofolha;
    }

    public void setIddesoneracaofolha(int iddesoneracaofolha) {
        this.iddesoneracaofolha = iddesoneracaofolha;
    }

    public int getIdregistroeletronicoempregados() {
        return idregistroeletronicoempregados;
    }

    public void setIdregistroeletronicoempregados(int idregistroeletronicoempregados) {
        this.idregistroeletronicoempregados = idregistroeletronicoempregados;
    }

    public boolean isIndinformacoescomplementares() {
        return indinformacoescomplementares;
    }

    public void setIndinformacoescomplementares(boolean indinformacoescomplementares) {
        this.indinformacoescomplementares = indinformacoescomplementares;
    }

    public boolean isIndprocessosjudiciaisrelativooutrasentidadesfundos() {
        return indprocessosjudiciaisrelativooutrasentidadesfundos;
    }

    public void setIndprocessosjudiciaisrelativooutrasentidadesfundos(boolean indprocessosjudiciaisrelativooutrasentidadesfundos) {
        this.indprocessosjudiciaisrelativooutrasentidadesfundos = indprocessosjudiciaisrelativooutrasentidadesfundos;
    }

    public String getNumerosiafi() {
        return numerosiafi;
    }

    public void setNumerosiafi(String numerosiafi) {
        this.numerosiafi = numerosiafi;
    }

    public int getIdsituacaopf() {
        return idsituacaopf;
    }

    public void setIdsituacaopf(int idsituacaopf) {
        this.idsituacaopf = idsituacaopf;
    }

    public boolean isIndativoEsocial() {
        return indativoEsocial;
    }

    public void setIndativoEsocial(boolean indativoEsocial) {
        this.indativoEsocial = indativoEsocial;
    }

    public boolean isInddescontoproporcionalinss() {
        return inddescontoproporcionalinss;
    }

    public void setInddescontoproporcionalinss(boolean inddescontoproporcionalinss) {
        this.inddescontoproporcionalinss = inddescontoproporcionalinss;
    }

    public boolean isInddesconsiderarfalta() {
        return inddesconsiderarfalta;
    }

    public void setInddesconsiderarfalta(boolean inddesconsiderarfalta) {
        this.inddesconsiderarfalta = inddesconsiderarfalta;
    }

    public boolean isInddescontarcontribuicaosindical() {
        return inddescontarcontribuicaosindical;
    }

    public void setInddescontarcontribuicaosindical(boolean inddescontarcontribuicaosindical) {
        this.inddescontarcontribuicaosindical = inddescontarcontribuicaosindical;
    }

    public boolean isIndmostrardataretornorecibo() {
        return indmostrardataretornorecibo;
    }

    public void setIndmostrardataretornorecibo(boolean indmostrardataretornorecibo) {
        this.indmostrardataretornorecibo = indmostrardataretornorecibo;
    }

    public boolean isInddataretornodianaoutil() {
        return inddataretornodianaoutil;
    }

    public void setInddataretornodianaoutil(boolean inddataretornodianaoutil) {
        this.inddataretornodianaoutil = inddataretornodianaoutil;
    }

    public boolean isIndemitiralertavencimentoavisoprevio() {
        return indemitiralertavencimentoavisoprevio;
    }

    public void setIndemitiralertavencimentoavisoprevio(boolean indemitiralertavencimentoavisoprevio) {
        this.indemitiralertavencimentoavisoprevio = indemitiralertavencimentoavisoprevio;
    }

    public boolean isIndconsiderarabonopecuniario() {
        return indconsiderarabonopecuniario;
    }

    public void setIndconsiderarabonopecuniario(boolean indconsiderarabonopecuniario) {
        this.indconsiderarabonopecuniario = indconsiderarabonopecuniario;
    }

    public int getQuantidadeanosdireitolicensapremio() {
        return quantidadeanosdireitolicensapremio;
    }

    public void setQuantidadeanosdireitolicensapremio(int quantidadeanosdireitolicensapremio) {
        this.quantidadeanosdireitolicensapremio = quantidadeanosdireitolicensapremio;
    }

    public boolean isIndagruparvinculado() {
        return indagruparvinculado;
    }

    public void setIndagruparvinculado(boolean indagruparvinculado) {
        this.indagruparvinculado = indagruparvinculado;
    }

    public boolean isIndpagarsalariofamiliaabonofamilia() {
        return indpagarsalariofamiliaabonofamilia;
    }

    public void setIndpagarsalariofamiliaabonofamilia(boolean indpagarsalariofamiliaabonofamilia) {
        this.indpagarsalariofamiliaabonofamilia = indpagarsalariofamiliaabonofamilia;
    }

    public boolean isIndpagarlicencaremuneradafuncionariomaisumano() {
        return indpagarlicencaremuneradafuncionariomaisumano;
    }

    public void setIndpagarlicencaremuneradafuncionariomaisumano(boolean indpagarlicencaremuneradafuncionariomaisumano) {
        this.indpagarlicencaremuneradafuncionariomaisumano = indpagarlicencaremuneradafuncionariomaisumano;
    }

    public boolean isIndmudarperiodoaquisitivo() {
        return indmudarperiodoaquisitivo;
    }

    public void setIndmudarperiodoaquisitivo(boolean indmudarperiodoaquisitivo) {
        this.indmudarperiodoaquisitivo = indmudarperiodoaquisitivo;
    }

    public boolean isIndpagarlicencaremunerada() {
        return indpagarlicencaremunerada;
    }

    public void setIndpagarlicencaremunerada(boolean indpagarlicencaremunerada) {
        this.indpagarlicencaremunerada = indpagarlicencaremunerada;
    }

    public boolean isIndpagaradiantamento13salario() {
        return indpagaradiantamento13salario;
    }

    public void setIndpagaradiantamento13salario(boolean indpagaradiantamento13salario) {
        this.indpagaradiantamento13salario = indpagaradiantamento13salario;
    }

    public boolean isIndativoFerias() {
        return indativoFerias;
    }

    public void setIndativoFerias(boolean indativoFerias) {
        this.indativoFerias = indativoFerias;
    }

    public boolean isIndconsiderardiasfaltasperiodofatosgeradores() {
        return indconsiderardiasfaltasperiodofatosgeradores;
    }

    public void setIndconsiderardiasfaltasperiodofatosgeradores(boolean indconsiderardiasfaltasperiodofatosgeradores) {
        this.indconsiderardiasfaltasperiodofatosgeradores = indconsiderardiasfaltasperiodofatosgeradores;
    }

    public boolean isIndpagarmediaferiasverbaseparada() {
        return indpagarmediaferiasverbaseparada;
    }

    public void setIndpagarmediaferiasverbaseparada(boolean indpagarmediaferiasverbaseparada) {
        this.indpagarmediaferiasverbaseparada = indpagarmediaferiasverbaseparada;
    }

    public boolean isIndconsiderarvalorinssferiasverbaseparada() {
        return indconsiderarvalorinssferiasverbaseparada;
    }

    public void setIndconsiderarvalorinssferiasverbaseparada(boolean indconsiderarvalorinssferiasverbaseparada) {
        this.indconsiderarvalorinssferiasverbaseparada = indconsiderarvalorinssferiasverbaseparada;
    }

    public boolean isIndconsiderarbaixaproporcionalprovisaoferias() {
        return indconsiderarbaixaproporcionalprovisaoferias;
    }

    public void setIndconsiderarbaixaproporcionalprovisaoferias(boolean indconsiderarbaixaproporcionalprovisaoferias) {
        this.indconsiderarbaixaproporcionalprovisaoferias = indconsiderarbaixaproporcionalprovisaoferias;
    }

    public boolean isIndconsiderarbaixaprovisaoferiasconformevaloresferias() {
        return indconsiderarbaixaprovisaoferiasconformevaloresferias;
    }

    public void setIndconsiderarbaixaprovisaoferiasconformevaloresferias(boolean indconsiderarbaixaprovisaoferiasconformevaloresferias) {
        this.indconsiderarbaixaprovisaoferiasconformevaloresferias = indconsiderarbaixaprovisaoferiasconformevaloresferias;
    }

    public boolean isInddescontarinss() {
        return inddescontarinss;
    }

    public void setInddescontarinss(boolean inddescontarinss) {
        this.inddescontarinss = inddescontarinss;
    }

    public boolean isIndgeralmostrarverbaferias() {
        return indgeralmostrarverbaferias;
    }

    public void setIndgeralmostrarverbaferias(boolean indgeralmostrarverbaferias) {
        this.indgeralmostrarverbaferias = indgeralmostrarverbaferias;
    }

    public boolean isIndutilizarcasadecimal() {
        return indutilizarcasadecimal;
    }

    public void setIndutilizarcasadecimal(boolean indutilizarcasadecimal) {
        this.indutilizarcasadecimal = indutilizarcasadecimal;
    }

    public boolean isIndcontroleautomaticosaldo() {
        return indcontroleautomaticosaldo;
    }

    public void setIndcontroleautomaticosaldo(boolean indcontroleautomaticosaldo) {
        this.indcontroleautomaticosaldo = indcontroleautomaticosaldo;
    }

    public boolean isIndhorascalculosalariofamilia() {
        return indhorascalculosalariofamilia;
    }

    public void setIndhorascalculosalariofamilia(boolean indhorascalculosalariofamilia) {
        this.indhorascalculosalariofamilia = indhorascalculosalariofamilia;
    }

    public boolean isIndabonarirmenor() {
        return indabonarirmenor;
    }

    public void setIndabonarirmenor(boolean indabonarirmenor) {
        this.indabonarirmenor = indabonarirmenor;
    }

    public int getIdtipodiatrabalhado() {
        return idtipodiatrabalhado;
    }

    public void setIdtipodiatrabalhado(int idtipodiatrabalhado) {
        this.idtipodiatrabalhado = idtipodiatrabalhado;
    }

    public int getIdtipoarredondamentoGeral() {
        return idtipoarredondamentoGeral;
    }

    public void setIdtipoarredondamentoGeral(int idtipoarredondamentoGeral) {
        this.idtipoarredondamentoGeral = idtipoarredondamentoGeral;
    }

    public Double getValorarredondamento() {
        return valorarredondamento;
    }

    public void setValorarredondamento(Double valorarredondamento) {
        this.valorarredondamento = valorarredondamento;
    }

    public int getIdtipocalculomedia() {
        return idtipocalculomedia;
    }

    public void setIdtipocalculomedia(int idtipocalculomedia) {
        this.idtipocalculomedia = idtipocalculomedia;
    }

    public boolean isIndmescorrentecalculomedia() {
        return indmescorrentecalculomedia;
    }

    public void setIndmescorrentecalculomedia(boolean indmescorrentecalculomedia) {
        this.indmescorrentecalculomedia = indmescorrentecalculomedia;
    }

    public boolean isIndbasecalculomediadsr() {
        return indbasecalculomediadsr;
    }

    public void setIndbasecalculomediadsr(boolean indbasecalculomediadsr) {
        this.indbasecalculomediadsr = indbasecalculomediadsr;
    }

    public boolean isInddeduzirfaltadsr() {
        return inddeduzirfaltadsr;
    }

    public void setInddeduzirfaltadsr(boolean inddeduzirfaltadsr) {
        this.inddeduzirfaltadsr = inddeduzirfaltadsr;
    }

    public boolean isIndcalculocomissionistadsr() {
        return indcalculocomissionistadsr;
    }

    public void setIndcalculocomissionistadsr(boolean indcalculocomissionistadsr) {
        this.indcalculocomissionistadsr = indcalculocomissionistadsr;
    }

    public int getDiainicioapuracaodsr() {
        return diainicioapuracaodsr;
    }

    public void setDiainicioapuracaodsr(int diainicioapuracaodsr) {
        this.diainicioapuracaodsr = diainicioapuracaodsr;
    }

    public int getDiafimapuracaodsr() {
        return diafimapuracaodsr;
    }

    public void setDiafimapuracaodsr(int diafimapuracaodsr) {
        this.diafimapuracaodsr = diafimapuracaodsr;
    }

    public boolean isIndparalisacaoafastamentodoenca() {
        return indparalisacaoafastamentodoenca;
    }

    public void setIndparalisacaoafastamentodoenca(boolean indparalisacaoafastamentodoenca) {
        this.indparalisacaoafastamentodoenca = indparalisacaoafastamentodoenca;
    }

    public boolean isIndutilizareventoocorridomesrecisaocalculomedia() {
        return indutilizareventoocorridomesrecisaocalculomedia;
    }

    public void setIndutilizareventoocorridomesrecisaocalculomedia(boolean indutilizareventoocorridomesrecisaocalculomedia) {
        this.indutilizareventoocorridomesrecisaocalculomedia = indutilizareventoocorridomesrecisaocalculomedia;
    }

    public boolean isIndparalisacaocontagemcontrato() {
        return indparalisacaocontagemcontrato;
    }

    public void setIndparalisacaocontagemcontrato(boolean indparalisacaocontagemcontrato) {
        this.indparalisacaocontagemcontrato = indparalisacaocontagemcontrato;
    }

    public boolean isIndpagardescansoindenizadodemissao() {
        return indpagardescansoindenizadodemissao;
    }

    public void setIndpagardescansoindenizadodemissao(boolean indpagardescansoindenizadodemissao) {
        this.indpagardescansoindenizadodemissao = indpagardescansoindenizadodemissao;
    }

    public boolean isIndbeneficiotransporteabaterdiasferias() {
        return indbeneficiotransporteabaterdiasferias;
    }

    public void setIndbeneficiotransporteabaterdiasferias(boolean indbeneficiotransporteabaterdiasferias) {
        this.indbeneficiotransporteabaterdiasferias = indbeneficiotransporteabaterdiasferias;
    }

    public boolean isIndbeneficiotransporteabaterdiasafastamento() {
        return indbeneficiotransporteabaterdiasafastamento;
    }

    public void setIndbeneficiotransporteabaterdiasafastamento(boolean indbeneficiotransporteabaterdiasafastamento) {
        this.indbeneficiotransporteabaterdiasafastamento = indbeneficiotransporteabaterdiasafastamento;
    }

    public boolean isIndbeneficiotransporteproporcionaljornadareduzida() {
        return indbeneficiotransporteproporcionaljornadareduzida;
    }

    public void setIndbeneficiotransporteproporcionaljornadareduzida(boolean indbeneficiotransporteproporcionaljornadareduzida) {
        this.indbeneficiotransporteproporcionaljornadareduzida = indbeneficiotransporteproporcionaljornadareduzida;
    }

    public boolean isIndbeneficioticketabaterdiasferias() {
        return indbeneficioticketabaterdiasferias;
    }

    public void setIndbeneficioticketabaterdiasferias(boolean indbeneficioticketabaterdiasferias) {
        this.indbeneficioticketabaterdiasferias = indbeneficioticketabaterdiasferias;
    }

    public boolean isIndbeneficioticketabaterdiasafastamento() {
        return indbeneficioticketabaterdiasafastamento;
    }

    public void setIndbeneficioticketabaterdiasafastamento(boolean indbeneficioticketabaterdiasafastamento) {
        this.indbeneficioticketabaterdiasafastamento = indbeneficioticketabaterdiasafastamento;
    }

    public boolean isIndbeneficioticketproporcionaljornadareduzida() {
        return indbeneficioticketproporcionaljornadareduzida;
    }

    public void setIndbeneficioticketproporcionaljornadareduzida(boolean indbeneficioticketproporcionaljornadareduzida) {
        this.indbeneficioticketproporcionaljornadareduzida = indbeneficioticketproporcionaljornadareduzida;
    }

    public boolean isIndlancarverbadescontodsrmensalistaquinzenalista() {
        return indlancarverbadescontodsrmensalistaquinzenalista;
    }

    public void setIndlancarverbadescontodsrmensalistaquinzenalista(boolean indlancarverbadescontodsrmensalistaquinzenalista) {
        this.indlancarverbadescontodsrmensalistaquinzenalista = indlancarverbadescontodsrmensalistaquinzenalista;
    }

    public boolean isIndlancarverbadescontodsrhoristasemanalistadiarista() {
        return indlancarverbadescontodsrhoristasemanalistadiarista;
    }

    public void setIndlancarverbadescontodsrhoristasemanalistadiarista(boolean indlancarverbadescontodsrhoristasemanalistadiarista) {
        this.indlancarverbadescontodsrhoristasemanalistadiarista = indlancarverbadescontodsrhoristasemanalistadiarista;
    }

    public boolean isIndmensalista() {
        return indmensalista;
    }

    public void setIndmensalista(boolean indmensalista) {
        this.indmensalista = indmensalista;
    }

    public boolean isIndsemanalista() {
        return indsemanalista;
    }

    public void setIndsemanalista(boolean indsemanalista) {
        this.indsemanalista = indsemanalista;
    }

    public boolean isIndhorista() {
        return indhorista;
    }

    public void setIndhorista(boolean indhorista) {
        this.indhorista = indhorista;
    }

    public boolean isIndquinzenalista() {
        return indquinzenalista;
    }

    public void setIndquinzenalista(boolean indquinzenalista) {
        this.indquinzenalista = indquinzenalista;
    }

    public boolean isInddiarista() {
        return inddiarista;
    }

    public void setInddiarista(boolean inddiarista) {
        this.inddiarista = inddiarista;
    }

    public boolean isIndtarefeiro() {
        return indtarefeiro;
    }

    public void setIndtarefeiro(boolean indtarefeiro) {
        this.indtarefeiro = indtarefeiro;
    }

    public boolean isIndconsiderartambemmesesferiasafastamento() {
        return indconsiderartambemmesesferiasafastamento;
    }

    public void setIndconsiderartambemmesesferiasafastamento(boolean indconsiderartambemmesesferiasafastamento) {
        this.indconsiderartambemmesesferiasafastamento = indconsiderartambemmesesferiasafastamento;
    }

    public int getIdtipoadiantamentoadmissao() {
        return idtipoadiantamentoadmissao;
    }

    public void setIdtipoadiantamentoadmissao(int idtipoadiantamentoadmissao) {
        this.idtipoadiantamentoadmissao = idtipoadiantamentoadmissao;
    }

    public int getNumerominimodiatrabalhoadiantamentoadmissao() {
        return numerominimodiatrabalhoadiantamentoadmissao;
    }

    public void setNumerominimodiatrabalhoadiantamentoadmissao(int numerominimodiatrabalhoadiantamentoadmissao) {
        this.numerominimodiatrabalhoadiantamentoadmissao = numerominimodiatrabalhoadiantamentoadmissao;
    }

    public int getIdpagamentofolhamensal() {
        return idpagamentofolhamensal;
    }

    public void setIdpagamentofolhamensal(int idpagamentofolhamensal) {
        this.idpagamentofolhamensal = idpagamentofolhamensal;
    }

    public int getDiainicioapuracaofatogerador() {
        return diainicioapuracaofatogerador;
    }

    public void setDiainicioapuracaofatogerador(int diainicioapuracaofatogerador) {
        this.diainicioapuracaofatogerador = diainicioapuracaofatogerador;
    }

    public int getDiafimapuracaofatogerador() {
        return diafimapuracaofatogerador;
    }

    public void setDiafimapuracaofatogerador(int diafimapuracaofatogerador) {
        this.diafimapuracaofatogerador = diafimapuracaofatogerador;
    }

    public boolean isIndativoGeral() {
        return indativoGeral;
    }

    public void setIndativoGeral(boolean indativoGeral) {
        this.indativoGeral = indativoGeral;
    }

    public boolean isIndconsiderararredondamentofolhaferias() {
        return indconsiderararredondamentofolhaferias;
    }

    public void setIndconsiderararredondamentofolhaferias(boolean indconsiderararredondamentofolhaferias) {
        this.indconsiderararredondamentofolhaferias = indconsiderararredondamentofolhaferias;
    }

    public int getIdconsiderarparacalculodsr() {
        return idconsiderarparacalculodsr;
    }

    public void setIdconsiderarparacalculodsr(int idconsiderarparacalculodsr) {
        this.idconsiderarparacalculodsr = idconsiderarparacalculodsr;
    }

    public boolean isIndconsiderartambemmesadmissao() {
        return indconsiderartambemmesadmissao;
    }

    public void setIndconsiderartambemmesadmissao(boolean indconsiderartambemmesadmissao) {
        this.indconsiderartambemmesadmissao = indconsiderartambemmesadmissao;
    }

    public boolean isIndcontrolarmotivocalculosalariofamilia() {
        return indcontrolarmotivocalculosalariofamilia;
    }

    public void setIndcontrolarmotivocalculosalariofamilia(boolean indcontrolarmotivocalculosalariofamilia) {
        this.indcontrolarmotivocalculosalariofamilia = indcontrolarmotivocalculosalariofamilia;
    }

    public boolean isIndconsiderarverbasrescisaomedia() {
        return indconsiderarverbasrescisaomedia;
    }

    public void setIndconsiderarverbasrescisaomedia(boolean indconsiderarverbasrescisaomedia) {
        this.indconsiderarverbasrescisaomedia = indconsiderarverbasrescisaomedia;
    }

    public boolean isIndbeneficiotransporteabaterfaltas() {
        return indbeneficiotransporteabaterfaltas;
    }

    public void setIndbeneficiotransporteabaterfaltas(boolean indbeneficiotransporteabaterfaltas) {
        this.indbeneficiotransporteabaterfaltas = indbeneficiotransporteabaterfaltas;
    }

    public boolean isIndconsiderardiasapuracaodsrintegral() {
        return indconsiderardiasapuracaodsrintegral;
    }

    public void setIndconsiderardiasapuracaodsrintegral(boolean indconsiderardiasapuracaodsrintegral) {
        this.indconsiderardiasapuracaodsrintegral = indconsiderardiasapuracaodsrintegral;
    }

    public boolean isIndverbaprogramadaperiodoafastamento() {
        return indverbaprogramadaperiodoafastamento;
    }

    public void setIndverbaprogramadaperiodoafastamento(boolean indverbaprogramadaperiodoafastamento) {
        this.indverbaprogramadaperiodoafastamento = indverbaprogramadaperiodoafastamento;
    }

    public boolean isIndsepararumavoferiasindenizado() {
        return indsepararumavoferiasindenizado;
    }

    public void setIndsepararumavoferiasindenizado(boolean indsepararumavoferiasindenizado) {
        this.indsepararumavoferiasindenizado = indsepararumavoferiasindenizado;
    }

    public boolean isIndmediacalculoferiasindenizado() {
        return indmediacalculoferiasindenizado;
    }

    public void setIndmediacalculoferiasindenizado(boolean indmediacalculoferiasindenizado) {
        this.indmediacalculoferiasindenizado = indmediacalculoferiasindenizado;
    }

    public int getIdtipoadiantamentoferias() {
        return idtipoadiantamentoferias;
    }

    public void setIdtipoadiantamentoferias(int idtipoadiantamentoferias) {
        this.idtipoadiantamentoferias = idtipoadiantamentoferias;
    }

    public int getIdtipoadiantamentoafastamento() {
        return idtipoadiantamentoafastamento;
    }

    public void setIdtipoadiantamentoafastamento(int idtipoadiantamentoafastamento) {
        this.idtipoadiantamentoafastamento = idtipoadiantamentoafastamento;
    }

    public int getNumerominimodiatrabalhoadiantamentoferias() {
        return numerominimodiatrabalhoadiantamentoferias;
    }

    public void setNumerominimodiatrabalhoadiantamentoferias(int numerominimodiatrabalhoadiantamentoferias) {
        this.numerominimodiatrabalhoadiantamentoferias = numerominimodiatrabalhoadiantamentoferias;
    }

    public int getNumerominimodiatrabalhoadiantamentoafastamento() {
        return numerominimodiatrabalhoadiantamentoafastamento;
    }

    public void setNumerominimodiatrabalhoadiantamentoafastamento(int numerominimodiatrabalhoadiantamentoafastamento) {
        this.numerominimodiatrabalhoadiantamentoafastamento = numerominimodiatrabalhoadiantamentoafastamento;
    }

    public boolean isIndpagarfaltasjustificadasseparadassalario() {
        return indpagarfaltasjustificadasseparadassalario;
    }

    public void setIndpagarfaltasjustificadasseparadassalario(boolean indpagarfaltasjustificadasseparadassalario) {
        this.indpagarfaltasjustificadasseparadassalario = indpagarfaltasjustificadasseparadassalario;
    }

    public boolean isIndtrabalhotemporario() {
        return indtrabalhotemporario;
    }

    public void setIndtrabalhotemporario(boolean indtrabalhotemporario) {
        this.indtrabalhotemporario = indtrabalhotemporario;
    }

    public boolean isIndconsiderarsalfamcalculocontroleautomaticosaldo() {
        return indconsiderarsalfamcalculocontroleautomaticosaldo;
    }

    public void setIndconsiderarsalfamcalculocontroleautomaticosaldo(boolean indconsiderarsalfamcalculocontroleautomaticosaldo) {
        this.indconsiderarsalfamcalculocontroleautomaticosaldo = indconsiderarsalfamcalculocontroleautomaticosaldo;
    }

    public boolean isIndconsiderararredondamentoparaautonomostransportadores() {
        return indconsiderararredondamentoparaautonomostransportadores;
    }

    public void setIndconsiderararredondamentoparaautonomostransportadores(boolean indconsiderararredondamentoparaautonomostransportadores) {
        this.indconsiderararredondamentoparaautonomostransportadores = indconsiderararredondamentoparaautonomostransportadores;
    }

    public boolean isIndconsiderararredondamentoparasocios() {
        return indconsiderararredondamentoparasocios;
    }

    public void setIndconsiderararredondamentoparasocios(boolean indconsiderararredondamentoparasocios) {
        this.indconsiderararredondamentoparasocios = indconsiderararredondamentoparasocios;
    }

    public boolean isIndlimitarcontagemdiastrabalhadosdatair() {
        return indlimitarcontagemdiastrabalhadosdatair;
    }

    public void setIndlimitarcontagemdiastrabalhadosdatair(boolean indlimitarcontagemdiastrabalhadosdatair) {
        this.indlimitarcontagemdiastrabalhadosdatair = indlimitarcontagemdiastrabalhadosdatair;
    }

    public boolean isIndcalculardsrsobreferiadosverba() {
        return indcalculardsrsobreferiadosverba;
    }

    public void setIndcalculardsrsobreferiadosverba(boolean indcalculardsrsobreferiadosverba) {
        this.indcalculardsrsobreferiadosverba = indcalculardsrsobreferiadosverba;
    }

    public boolean isIndconsiderardiasmesfolhaferias() {
        return indconsiderardiasmesfolhaferias;
    }

    public void setIndconsiderardiasmesfolhaferias(boolean indconsiderardiasmesfolhaferias) {
        this.indconsiderardiasmesfolhaferias = indconsiderardiasmesfolhaferias;
    }

    public boolean isIndgerarfluxocaixa() {
        return indgerarfluxocaixa;
    }

    public void setIndgerarfluxocaixa(boolean indgerarfluxocaixa) {
        this.indgerarfluxocaixa = indgerarfluxocaixa;
    }

    public boolean isIndclientedireto() {
        return indclientedireto;
    }

    public void setIndclientedireto(boolean indclientedireto) {
        this.indclientedireto = indclientedireto;
    }

    public boolean isIndconsiderardiasmesapropriacaohoras() {
        return indconsiderardiasmesapropriacaohoras;
    }

    public void setIndconsiderardiasmesapropriacaohoras(boolean indconsiderardiasmesapropriacaohoras) {
        this.indconsiderardiasmesapropriacaohoras = indconsiderardiasmesapropriacaohoras;
    }

    public Timestamp getDatainicioservico() {
        return datainicioservico;
    }

    public void setDatainicioservico(Timestamp datainicioservico) {
        this.datainicioservico = datainicioservico;
    }

    public boolean isIndpreencherdatairrfautomaticamentefolhamensal() {
        return indpreencherdatairrfautomaticamentefolhamensal;
    }

    public void setIndpreencherdatairrfautomaticamentefolhamensal(boolean indpreencherdatairrfautomaticamentefolhamensal) {
        this.indpreencherdatairrfautomaticamentefolhamensal = indpreencherdatairrfautomaticamentefolhamensal;
    }

    public int getDiapagamentoirrffolhamensal() {
        return diapagamentoirrffolhamensal;
    }

    public void setDiapagamentoirrffolhamensal(int diapagamentoirrffolhamensal) {
        this.diapagamentoirrffolhamensal = diapagamentoirrffolhamensal;
    }

    public boolean isIndtipodiairrffolhamensal() {
        return indtipodiairrffolhamensal;
    }

    public void setIndtipodiairrffolhamensal(boolean indtipodiairrffolhamensal) {
        this.indtipodiairrffolhamensal = indtipodiairrffolhamensal;
    }

    public boolean isIndantecipadiautildatairrf() {
        return indantecipadiautildatairrf;
    }

    public void setIndantecipadiautildatairrf(boolean indantecipadiautildatairrf) {
        this.indantecipadiautildatairrf = indantecipadiautildatairrf;
    }

    public boolean isIndconsiderasabadodiautil() {
        return indconsiderasabadodiautil;
    }

    public void setIndconsiderasabadodiautil(boolean indconsiderasabadodiautil) {
        this.indconsiderasabadodiautil = indconsiderasabadodiautil;
    }

    public boolean isIndconsiderarlimitemaximoavisopreviotrabalhado() {
        return indconsiderarlimitemaximoavisopreviotrabalhado;
    }

    public void setIndconsiderarlimitemaximoavisopreviotrabalhado(boolean indconsiderarlimitemaximoavisopreviotrabalhado) {
        this.indconsiderarlimitemaximoavisopreviotrabalhado = indconsiderarlimitemaximoavisopreviotrabalhado;
    }

    public boolean isIndoperacaoparalelo() {
        return indoperacaoparalelo;
    }

    public void setIndoperacaoparalelo(boolean indoperacaoparalelo) {
        this.indoperacaoparalelo = indoperacaoparalelo;
    }

    public boolean isIndconsiderar15diaspagopelaempresareferenteafastamentos() {
        return indconsiderar15diaspagopelaempresareferenteafastamentos;
    }

    public void setIndconsiderar15diaspagopelaempresareferenteafastamentos(boolean indconsiderar15diaspagopelaempresareferenteafastamentos) {
        this.indconsiderar15diaspagopelaempresareferenteafastamentos = indconsiderar15diaspagopelaempresareferenteafastamentos;
    }

    public Double getPercentualadiantamento() {
        return percentualadiantamento;
    }

    public void setPercentualadiantamento(Double percentualadiantamento) {
        this.percentualadiantamento = percentualadiantamento;
    }

    public boolean isIndcalcularadiantamentodiasmesesdiferente30empregadoshoristas() {
        return indcalcularadiantamentodiasmesesdiferente30empregadoshoristas;
    }

    public void setIndcalcularadiantamentodiasmesesdiferente30empregadoshoristas(boolean indcalcularadiantamentodiasmesesdiferente30empregadoshoristas) {
        this.indcalcularadiantamentodiasmesesdiferente30empregadoshoristas = indcalcularadiantamentodiasmesesdiferente30empregadoshoristas;
    }

    public boolean isIndconsiderarfolharescisao() {
        return indconsiderarfolharescisao;
    }

    public void setIndconsiderarfolharescisao(boolean indconsiderarfolharescisao) {
        this.indconsiderarfolharescisao = indconsiderarfolharescisao;
    }

    public boolean isIndgerarferiasproporcionaisrescisoesjustacausa() {
        return indgerarferiasproporcionaisrescisoesjustacausa;
    }

    public void setIndgerarferiasproporcionaisrescisoesjustacausa(boolean indgerarferiasproporcionaisrescisoesjustacausa) {
        this.indgerarferiasproporcionaisrescisoesjustacausa = indgerarferiasproporcionaisrescisoesjustacausa;
    }

    public boolean isIndbeneficioticketabaterfaltas() {
        return indbeneficioticketabaterfaltas;
    }

    public void setIndbeneficioticketabaterfaltas(boolean indbeneficioticketabaterfaltas) {
        this.indbeneficioticketabaterfaltas = indbeneficioticketabaterfaltas;
    }

    public Timestamp getDatafimservico() {
        return datafimservico;
    }

    public void setDatafimservico(Timestamp datafimservico) {
        this.datafimservico = datafimservico;
    }

    public Double getPercentualdescontoBeneficioFolha() {
        return percentualdescontoBeneficioFolha;
    }

    public void setPercentualdescontoBeneficioFolha(Double percentualdescontoBeneficioFolha) {
        this.percentualdescontoBeneficioFolha = percentualdescontoBeneficioFolha;
    }

    public int getIddadossindicato() {
        return iddadossindicato;
    }

    public void setIddadossindicato(int iddadossindicato) {
        this.iddadossindicato = iddadossindicato;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Double getValorcontribuicaosindical() {
        return valorcontribuicaosindical;
    }

    public void setValorcontribuicaosindical(Double valorcontribuicaosindical) {
        this.valorcontribuicaosindical = valorcontribuicaosindical;
    }

    public Double getValorcontribuicaoassociativa() {
        return valorcontribuicaoassociativa;
    }

    public void setValorcontribuicaoassociativa(Double valorcontribuicaoassociativa) {
        this.valorcontribuicaoassociativa = valorcontribuicaoassociativa;
    }

    public Double getValorcontribuicaoassistencial() {
        return valorcontribuicaoassistencial;
    }

    public void setValorcontribuicaoassistencial(Double valorcontribuicaoassistencial) {
        this.valorcontribuicaoassistencial = valorcontribuicaoassistencial;
    }

    public Double getValorcontribuicaoconfederativa() {
        return valorcontribuicaoconfederativa;
    }

    public void setValorcontribuicaoconfederativa(Double valorcontribuicaoconfederativa) {
        this.valorcontribuicaoconfederativa = valorcontribuicaoconfederativa;
    }

    public boolean isIndativoSindicato() {
        return indativoSindicato;
    }

    public void setIndativoSindicato(boolean indativoSindicato) {
        this.indativoSindicato = indativoSindicato;
    }

    public boolean isIndprincipal() {
        return indprincipal;
    }

    public void setIndprincipal(boolean indprincipal) {
        this.indprincipal = indprincipal;
    }
           
    

}
