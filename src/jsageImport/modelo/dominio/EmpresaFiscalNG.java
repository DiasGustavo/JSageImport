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
public class EmpresaFiscalNG extends PessoaJuridica{
    
    int idtranscricaomapaecfsaida;
    int idcfpadraoentrada;
    int idmodelolivroregistroentrada;
    int idmodelolivroregistrosaida;
    int iddadosguiaicmsst;
    int iddadosguiaicmsnormal;
    int idcfpadraosaida;
    int idmovimentocompensasaoretencao;
    int iddadosfornecedorregistroentrada;
    int iddestaquestregistroentrada;
    int idmodeloresumoapuracaoipi;
    int idseriepadraosaida;
    String seriepadraoentrada;
    String subseriepadraoentrada;
    String especiepadraoentrada;
    int iddadosguiaaliquota;
    int iddadosguiaiss;
    boolean indrecolhimentosimplificadoicms;
    boolean indconvenio10696;
    boolean indprodutorrural;
    boolean indcontroleunidadesvs;
    boolean indescrituracaocentralizada;
    boolean indativo;
    boolean indeditarmaterial;
    boolean indeditarfatura;
    boolean indmudardiaautomaticosaida;
    boolean indcontrolatributomovimentocfop;
    boolean indexibiroperacaoprestacaointerestadual;
    boolean indexibirobservacaoincidenciaimposto;
    double valorretencaopiscofinscsll;
    double valornaoconsiderarirrf;
    double valornaoconsiderarinss;
    boolean indgerarfaturacondicaooutros;
    int idtipoperiodicidadelancamento;
    int idcontacontabillancamentoavista;
    boolean indexibircontabilizacaodadosfiscal;
    boolean indexibirobservacaogt;
    boolean indcontabilizacaosubcodigo;
    int idtipoopcaoimpressaorelatorio;
    int idcontacontabilbaixafatura;
    String codigoatividadepadrao;
    int idmodelolivrociap;
    boolean indpossuialiquotadifpiscofinscontribuinte;
    boolean indpossuialiquotadifpiscofinssubstituto;
    boolean indincluiautomaticocontacontabilclientefornecedor;
    int idcontacontabilcliente;
    int idcontacontabilfornecedor;
    boolean indobrigasubcodigomovimento;
    int idcfparametropadraoentrada;
    int idcfparametropadraosaida;
    boolean indvalidarsequencianotaemissao;
    boolean indgerarocorrenciadifaliquota;
    int idtipoocorrencia;
    boolean indabaterdebitoipiimpostosfederais;
    boolean indcontabilizarsituacaotributaria91;
    boolean indcriarlinha91semipi;
    boolean indmensagemsequencianotas;
    boolean indcontacontabilunicaclientefornecedor;
    int idcontacontabilclienteunica;
    int idcontacontabilfornecedorunica;
    boolean indutilizaretencaooutrosparametros;
    String indtiporetencaosaidas;
    String indtiporetencaoentradas;
    boolean indabatervalorfretefaturamento;
    int idtipocontribuintepiscofins;
    boolean indutilizacodigoexternomercserv;
    boolean indutilizaletrasmercservarquivos;
    boolean indpermiteclientefornecedorsemcontacontabil;
    boolean indcontrolamercadoriaservicoporfilial;
    boolean indeditarservico;
    String numeroregistroanp;
    boolean indgerarfaturacondicaopagamentooutrascontabilizacao;
    boolean indbloqueartransferenciaavulsa;
    boolean indgerarcreditocstoutros;
    boolean indagruparcontascontabeisiguais;
    boolean indvalidaraliquotapresuncao;
    boolean indcontacontabilunicafornecedor;
    boolean indempresasegmentocombustivel;
    boolean indpossuiconvenio11503;
    String codigoperfilapresentacaoempresa;
    String codigonaturezapessoajuridica;
    String codigoapuracaocontribuicoescreditos;
    int idcontacontabilh010;
    int idcontacontabilcredora;
    int idcontacontabildevedora;
    int idtipoatividadepredominante;
    boolean indcontribuinteipi;
    boolean indcalculaestoquemovimento;
    int idcodigoregimetributariocsosn;
    Timestamp datainiciovigenciacodigoregimetributariocsosn;
    int idcontacontabilcredoraservico;
    int idcontacontabildevedoraservico;
    int idcentrocustoservico;
    int codigoapuracaopiscofins;
    boolean indregimeespecialpiscofins;
    String idregimetributario;
    boolean indincluirautomaticocontacontabilfornecedor;
    boolean indconsiderarlancamentoisslivrosfiscais;
    boolean indexcluido;
    int idapropriacaopiscofins;
    boolean indescrituracaonfeconsolidada;
    int idtipoatividadeempresablocop;
    int idcfnotacancelada;
    int idcfparametronotacancelada;
    int iddatacontabilizacaodocextemporaneo;
    boolean indutilizamasterfiscal;
    boolean indutilizarcodigocparquivos;
    boolean indsalvarlancamentosinconsistentes;
    boolean indsalvarnotavaloresnaofecham;
    int idtipocalculomovimentoimportacaodanfe;
    boolean INDGERAROCORRENCIADEVOLUCAOPISCOFINS;
    boolean indgeraregistrom220m620spedpiscof;
    double percentualaproximadotributosincidentes;
    double idtipocampocalculotributoaproximado;
    boolean indeditarcontabilizacaodocumentosfiscais;
    boolean indintegrarlancamentocontabilautomatico;
    boolean indexibirestoquesap;
    int idtipoitemconvenio106;
    boolean indobrigargeracaofaturamento;
    boolean indvalidacaoobrigatoriageracaosped;
    boolean indcalcularretencaoregimecaixa;
    boolean indconfigbasecalculopiscof;
    int idcontacontabiltransferencia;
    boolean indrequerclientefornecedorconta;
    boolean indatualizardescricaocontacontabil;
    boolean indconsiderarstretidoobservacao;
    boolean indconsiderarstantecipadoobservacao;
    boolean indefetuarecolhimentopatronal;
    boolean inddefinircomposicaofaturamentosimples;

    public int getIdtranscricaomapaecfsaida() {
        return idtranscricaomapaecfsaida;
    }

    public void setIdtranscricaomapaecfsaida(int idtranscricaomapaecfsaida) {
        this.idtranscricaomapaecfsaida = idtranscricaomapaecfsaida;
    }

    public int getIdcfpadraoentrada() {
        return idcfpadraoentrada;
    }

    public void setIdcfpadraoentrada(int idcfpadraoentrada) {
        this.idcfpadraoentrada = idcfpadraoentrada;
    }

    public int getIdmodelolivroregistroentrada() {
        return idmodelolivroregistroentrada;
    }

    public void setIdmodelolivroregistroentrada(int idmodelolivroregistroentrada) {
        this.idmodelolivroregistroentrada = idmodelolivroregistroentrada;
    }

    public int getIdmodelolivroregistrosaida() {
        return idmodelolivroregistrosaida;
    }

    public void setIdmodelolivroregistrosaida(int idmodelolivroregistrosaida) {
        this.idmodelolivroregistrosaida = idmodelolivroregistrosaida;
    }

    public int getIddadosguiaicmsst() {
        return iddadosguiaicmsst;
    }

    public void setIddadosguiaicmsst(int iddadosguiaicmsst) {
        this.iddadosguiaicmsst = iddadosguiaicmsst;
    }

    public int getIddadosguiaicmsnormal() {
        return iddadosguiaicmsnormal;
    }

    public void setIddadosguiaicmsnormal(int iddadosguiaicmsnormal) {
        this.iddadosguiaicmsnormal = iddadosguiaicmsnormal;
    }

    public int getIdcfpadraosaida() {
        return idcfpadraosaida;
    }

    public void setIdcfpadraosaida(int idcfpadraosaida) {
        this.idcfpadraosaida = idcfpadraosaida;
    }

    public int getIdmovimentocompensasaoretencao() {
        return idmovimentocompensasaoretencao;
    }

    public void setIdmovimentocompensasaoretencao(int idmovimentocompensasaoretencao) {
        this.idmovimentocompensasaoretencao = idmovimentocompensasaoretencao;
    }

    public int getIddadosfornecedorregistroentrada() {
        return iddadosfornecedorregistroentrada;
    }

    public void setIddadosfornecedorregistroentrada(int iddadosfornecedorregistroentrada) {
        this.iddadosfornecedorregistroentrada = iddadosfornecedorregistroentrada;
    }

    public int getIddestaquestregistroentrada() {
        return iddestaquestregistroentrada;
    }

    public void setIddestaquestregistroentrada(int iddestaquestregistroentrada) {
        this.iddestaquestregistroentrada = iddestaquestregistroentrada;
    }

    public int getIdmodeloresumoapuracaoipi() {
        return idmodeloresumoapuracaoipi;
    }

    public void setIdmodeloresumoapuracaoipi(int idmodeloresumoapuracaoipi) {
        this.idmodeloresumoapuracaoipi = idmodeloresumoapuracaoipi;
    }

    public int getIdseriepadraosaida() {
        return idseriepadraosaida;
    }

    public void setIdseriepadraosaida(int idseriepadraosaida) {
        this.idseriepadraosaida = idseriepadraosaida;
    }

    public String getSeriepadraoentrada() {
        return seriepadraoentrada;
    }

    public void setSeriepadraoentrada(String seriepadraoentrada) {
        this.seriepadraoentrada = seriepadraoentrada;
    }

    public String getSubseriepadraoentrada() {
        return subseriepadraoentrada;
    }

    public void setSubseriepadraoentrada(String subseriepadraoentrada) {
        this.subseriepadraoentrada = subseriepadraoentrada;
    }

    public String getEspeciepadraoentrada() {
        return especiepadraoentrada;
    }

    public void setEspeciepadraoentrada(String especiepadraoentrada) {
        this.especiepadraoentrada = especiepadraoentrada;
    }

    public int getIddadosguiaaliquota() {
        return iddadosguiaaliquota;
    }

    public void setIddadosguiaaliquota(int iddadosguiaaliquota) {
        this.iddadosguiaaliquota = iddadosguiaaliquota;
    }

    public int getIddadosguiaiss() {
        return iddadosguiaiss;
    }

    public void setIddadosguiaiss(int iddadosguiaiss) {
        this.iddadosguiaiss = iddadosguiaiss;
    }

    public boolean isIndrecolhimentosimplificadoicms() {
        return indrecolhimentosimplificadoicms;
    }

    public void setIndrecolhimentosimplificadoicms(boolean indrecolhimentosimplificadoicms) {
        this.indrecolhimentosimplificadoicms = indrecolhimentosimplificadoicms;
    }

    public boolean isIndconvenio10696() {
        return indconvenio10696;
    }

    public void setIndconvenio10696(boolean indconvenio10696) {
        this.indconvenio10696 = indconvenio10696;
    }

    public boolean isIndprodutorrural() {
        return indprodutorrural;
    }

    public void setIndprodutorrural(boolean indprodutorrural) {
        this.indprodutorrural = indprodutorrural;
    }

    public boolean isIndcontroleunidadesvs() {
        return indcontroleunidadesvs;
    }

    public void setIndcontroleunidadesvs(boolean indcontroleunidadesvs) {
        this.indcontroleunidadesvs = indcontroleunidadesvs;
    }

    public boolean isIndescrituracaocentralizada() {
        return indescrituracaocentralizada;
    }

    public void setIndescrituracaocentralizada(boolean indescrituracaocentralizada) {
        this.indescrituracaocentralizada = indescrituracaocentralizada;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }

    public boolean isIndeditarmaterial() {
        return indeditarmaterial;
    }

    public void setIndeditarmaterial(boolean indeditarmaterial) {
        this.indeditarmaterial = indeditarmaterial;
    }

    public boolean isIndeditarfatura() {
        return indeditarfatura;
    }

    public void setIndeditarfatura(boolean indeditarfatura) {
        this.indeditarfatura = indeditarfatura;
    }

    public boolean isIndmudardiaautomaticosaida() {
        return indmudardiaautomaticosaida;
    }

    public void setIndmudardiaautomaticosaida(boolean indmudardiaautomaticosaida) {
        this.indmudardiaautomaticosaida = indmudardiaautomaticosaida;
    }

    public boolean isIndcontrolatributomovimentocfop() {
        return indcontrolatributomovimentocfop;
    }

    public void setIndcontrolatributomovimentocfop(boolean indcontrolatributomovimentocfop) {
        this.indcontrolatributomovimentocfop = indcontrolatributomovimentocfop;
    }

    public boolean isIndexibiroperacaoprestacaointerestadual() {
        return indexibiroperacaoprestacaointerestadual;
    }

    public void setIndexibiroperacaoprestacaointerestadual(boolean indexibiroperacaoprestacaointerestadual) {
        this.indexibiroperacaoprestacaointerestadual = indexibiroperacaoprestacaointerestadual;
    }

    public boolean isIndexibirobservacaoincidenciaimposto() {
        return indexibirobservacaoincidenciaimposto;
    }

    public void setIndexibirobservacaoincidenciaimposto(boolean indexibirobservacaoincidenciaimposto) {
        this.indexibirobservacaoincidenciaimposto = indexibirobservacaoincidenciaimposto;
    }

    public double getValorretencaopiscofinscsll() {
        return valorretencaopiscofinscsll;
    }

    public void setValorretencaopiscofinscsll(double valorretencaopiscofinscsll) {
        this.valorretencaopiscofinscsll = valorretencaopiscofinscsll;
    }

    public double getValornaoconsiderarirrf() {
        return valornaoconsiderarirrf;
    }

    public void setValornaoconsiderarirrf(double valornaoconsiderarirrf) {
        this.valornaoconsiderarirrf = valornaoconsiderarirrf;
    }

    public double getValornaoconsiderarinss() {
        return valornaoconsiderarinss;
    }

    public void setValornaoconsiderarinss(double valornaoconsiderarinss) {
        this.valornaoconsiderarinss = valornaoconsiderarinss;
    }

    public boolean isIndgerarfaturacondicaooutros() {
        return indgerarfaturacondicaooutros;
    }

    public void setIndgerarfaturacondicaooutros(boolean indgerarfaturacondicaooutros) {
        this.indgerarfaturacondicaooutros = indgerarfaturacondicaooutros;
    }

    public int getIdtipoperiodicidadelancamento() {
        return idtipoperiodicidadelancamento;
    }

    public void setIdtipoperiodicidadelancamento(int idtipoperiodicidadelancamento) {
        this.idtipoperiodicidadelancamento = idtipoperiodicidadelancamento;
    }

    public int getIdcontacontabillancamentoavista() {
        return idcontacontabillancamentoavista;
    }

    public void setIdcontacontabillancamentoavista(int idcontacontabillancamentoavista) {
        this.idcontacontabillancamentoavista = idcontacontabillancamentoavista;
    }

    public boolean isIndexibircontabilizacaodadosfiscal() {
        return indexibircontabilizacaodadosfiscal;
    }

    public void setIndexibircontabilizacaodadosfiscal(boolean indexibircontabilizacaodadosfiscal) {
        this.indexibircontabilizacaodadosfiscal = indexibircontabilizacaodadosfiscal;
    }

    public boolean isIndexibirobservacaogt() {
        return indexibirobservacaogt;
    }

    public void setIndexibirobservacaogt(boolean indexibirobservacaogt) {
        this.indexibirobservacaogt = indexibirobservacaogt;
    }

    public boolean isIndcontabilizacaosubcodigo() {
        return indcontabilizacaosubcodigo;
    }

    public void setIndcontabilizacaosubcodigo(boolean indcontabilizacaosubcodigo) {
        this.indcontabilizacaosubcodigo = indcontabilizacaosubcodigo;
    }

    public int getIdtipoopcaoimpressaorelatorio() {
        return idtipoopcaoimpressaorelatorio;
    }

    public void setIdtipoopcaoimpressaorelatorio(int idtipoopcaoimpressaorelatorio) {
        this.idtipoopcaoimpressaorelatorio = idtipoopcaoimpressaorelatorio;
    }

    public int getIdcontacontabilbaixafatura() {
        return idcontacontabilbaixafatura;
    }

    public void setIdcontacontabilbaixafatura(int idcontacontabilbaixafatura) {
        this.idcontacontabilbaixafatura = idcontacontabilbaixafatura;
    }

    public String getCodigoatividadepadrao() {
        return codigoatividadepadrao;
    }

    public void setCodigoatividadepadrao(String codigoatividadepadrao) {
        this.codigoatividadepadrao = codigoatividadepadrao;
    }

    public int getIdmodelolivrociap() {
        return idmodelolivrociap;
    }

    public void setIdmodelolivrociap(int idmodelolivrociap) {
        this.idmodelolivrociap = idmodelolivrociap;
    }

    public boolean isIndpossuialiquotadifpiscofinscontribuinte() {
        return indpossuialiquotadifpiscofinscontribuinte;
    }

    public void setIndpossuialiquotadifpiscofinscontribuinte(boolean indpossuialiquotadifpiscofinscontribuinte) {
        this.indpossuialiquotadifpiscofinscontribuinte = indpossuialiquotadifpiscofinscontribuinte;
    }

    public boolean isIndpossuialiquotadifpiscofinssubstituto() {
        return indpossuialiquotadifpiscofinssubstituto;
    }

    public void setIndpossuialiquotadifpiscofinssubstituto(boolean indpossuialiquotadifpiscofinssubstituto) {
        this.indpossuialiquotadifpiscofinssubstituto = indpossuialiquotadifpiscofinssubstituto;
    }

    public boolean isIndincluiautomaticocontacontabilclientefornecedor() {
        return indincluiautomaticocontacontabilclientefornecedor;
    }

    public void setIndincluiautomaticocontacontabilclientefornecedor(boolean indincluiautomaticocontacontabilclientefornecedor) {
        this.indincluiautomaticocontacontabilclientefornecedor = indincluiautomaticocontacontabilclientefornecedor;
    }

    public int getIdcontacontabilcliente() {
        return idcontacontabilcliente;
    }

    public void setIdcontacontabilcliente(int idcontacontabilcliente) {
        this.idcontacontabilcliente = idcontacontabilcliente;
    }

    public int getIdcontacontabilfornecedor() {
        return idcontacontabilfornecedor;
    }

    public void setIdcontacontabilfornecedor(int idcontacontabilfornecedor) {
        this.idcontacontabilfornecedor = idcontacontabilfornecedor;
    }

    public boolean isIndobrigasubcodigomovimento() {
        return indobrigasubcodigomovimento;
    }

    public void setIndobrigasubcodigomovimento(boolean indobrigasubcodigomovimento) {
        this.indobrigasubcodigomovimento = indobrigasubcodigomovimento;
    }

    public int getIdcfparametropadraoentrada() {
        return idcfparametropadraoentrada;
    }

    public void setIdcfparametropadraoentrada(int idcfparametropadraoentrada) {
        this.idcfparametropadraoentrada = idcfparametropadraoentrada;
    }

    public int getIdcfparametropadraosaida() {
        return idcfparametropadraosaida;
    }

    public void setIdcfparametropadraosaida(int idcfparametropadraosaida) {
        this.idcfparametropadraosaida = idcfparametropadraosaida;
    }

    public boolean isIndvalidarsequencianotaemissao() {
        return indvalidarsequencianotaemissao;
    }

    public void setIndvalidarsequencianotaemissao(boolean indvalidarsequencianotaemissao) {
        this.indvalidarsequencianotaemissao = indvalidarsequencianotaemissao;
    }

    public boolean isIndgerarocorrenciadifaliquota() {
        return indgerarocorrenciadifaliquota;
    }

    public void setIndgerarocorrenciadifaliquota(boolean indgerarocorrenciadifaliquota) {
        this.indgerarocorrenciadifaliquota = indgerarocorrenciadifaliquota;
    }

    public int getIdtipoocorrencia() {
        return idtipoocorrencia;
    }

    public void setIdtipoocorrencia(int idtipoocorrencia) {
        this.idtipoocorrencia = idtipoocorrencia;
    }

    public boolean isIndabaterdebitoipiimpostosfederais() {
        return indabaterdebitoipiimpostosfederais;
    }

    public void setIndabaterdebitoipiimpostosfederais(boolean indabaterdebitoipiimpostosfederais) {
        this.indabaterdebitoipiimpostosfederais = indabaterdebitoipiimpostosfederais;
    }

    public boolean isIndcontabilizarsituacaotributaria91() {
        return indcontabilizarsituacaotributaria91;
    }

    public void setIndcontabilizarsituacaotributaria91(boolean indcontabilizarsituacaotributaria91) {
        this.indcontabilizarsituacaotributaria91 = indcontabilizarsituacaotributaria91;
    }

    public boolean isIndcriarlinha91semipi() {
        return indcriarlinha91semipi;
    }

    public void setIndcriarlinha91semipi(boolean indcriarlinha91semipi) {
        this.indcriarlinha91semipi = indcriarlinha91semipi;
    }

    public boolean isIndmensagemsequencianotas() {
        return indmensagemsequencianotas;
    }

    public void setIndmensagemsequencianotas(boolean indmensagemsequencianotas) {
        this.indmensagemsequencianotas = indmensagemsequencianotas;
    }

    public boolean isIndcontacontabilunicaclientefornecedor() {
        return indcontacontabilunicaclientefornecedor;
    }

    public void setIndcontacontabilunicaclientefornecedor(boolean indcontacontabilunicaclientefornecedor) {
        this.indcontacontabilunicaclientefornecedor = indcontacontabilunicaclientefornecedor;
    }

    public int getIdcontacontabilclienteunica() {
        return idcontacontabilclienteunica;
    }

    public void setIdcontacontabilclienteunica(int idcontacontabilclienteunica) {
        this.idcontacontabilclienteunica = idcontacontabilclienteunica;
    }

    public int getIdcontacontabilfornecedorunica() {
        return idcontacontabilfornecedorunica;
    }

    public void setIdcontacontabilfornecedorunica(int idcontacontabilfornecedorunica) {
        this.idcontacontabilfornecedorunica = idcontacontabilfornecedorunica;
    }

    public boolean isIndutilizaretencaooutrosparametros() {
        return indutilizaretencaooutrosparametros;
    }

    public void setIndutilizaretencaooutrosparametros(boolean indutilizaretencaooutrosparametros) {
        this.indutilizaretencaooutrosparametros = indutilizaretencaooutrosparametros;
    }

    public String getIndtiporetencaosaidas() {
        return indtiporetencaosaidas;
    }

    public void setIndtiporetencaosaidas(String indtiporetencaosaidas) {
        this.indtiporetencaosaidas = indtiporetencaosaidas;
    }

    public String getIndtiporetencaoentradas() {
        return indtiporetencaoentradas;
    }

    public void setIndtiporetencaoentradas(String indtiporetencaoentradas) {
        this.indtiporetencaoentradas = indtiporetencaoentradas;
    }

    public boolean isIndabatervalorfretefaturamento() {
        return indabatervalorfretefaturamento;
    }

    public void setIndabatervalorfretefaturamento(boolean indabatervalorfretefaturamento) {
        this.indabatervalorfretefaturamento = indabatervalorfretefaturamento;
    }

    public int getIdtipocontribuintepiscofins() {
        return idtipocontribuintepiscofins;
    }

    public void setIdtipocontribuintepiscofins(int idtipocontribuintepiscofins) {
        this.idtipocontribuintepiscofins = idtipocontribuintepiscofins;
    }

    public boolean isIndutilizacodigoexternomercserv() {
        return indutilizacodigoexternomercserv;
    }

    public void setIndutilizacodigoexternomercserv(boolean indutilizacodigoexternomercserv) {
        this.indutilizacodigoexternomercserv = indutilizacodigoexternomercserv;
    }

    public boolean isIndutilizaletrasmercservarquivos() {
        return indutilizaletrasmercservarquivos;
    }

    public void setIndutilizaletrasmercservarquivos(boolean indutilizaletrasmercservarquivos) {
        this.indutilizaletrasmercservarquivos = indutilizaletrasmercservarquivos;
    }

    public boolean isIndpermiteclientefornecedorsemcontacontabil() {
        return indpermiteclientefornecedorsemcontacontabil;
    }

    public void setIndpermiteclientefornecedorsemcontacontabil(boolean indpermiteclientefornecedorsemcontacontabil) {
        this.indpermiteclientefornecedorsemcontacontabil = indpermiteclientefornecedorsemcontacontabil;
    }

    public boolean isIndcontrolamercadoriaservicoporfilial() {
        return indcontrolamercadoriaservicoporfilial;
    }

    public void setIndcontrolamercadoriaservicoporfilial(boolean indcontrolamercadoriaservicoporfilial) {
        this.indcontrolamercadoriaservicoporfilial = indcontrolamercadoriaservicoporfilial;
    }

    public boolean isIndeditarservico() {
        return indeditarservico;
    }

    public void setIndeditarservico(boolean indeditarservico) {
        this.indeditarservico = indeditarservico;
    }

    public String getNumeroregistroanp() {
        return numeroregistroanp;
    }

    public void setNumeroregistroanp(String numeroregistroanp) {
        this.numeroregistroanp = numeroregistroanp;
    }

    public boolean isIndgerarfaturacondicaopagamentooutrascontabilizacao() {
        return indgerarfaturacondicaopagamentooutrascontabilizacao;
    }

    public void setIndgerarfaturacondicaopagamentooutrascontabilizacao(boolean indgerarfaturacondicaopagamentooutrascontabilizacao) {
        this.indgerarfaturacondicaopagamentooutrascontabilizacao = indgerarfaturacondicaopagamentooutrascontabilizacao;
    }

    public boolean isIndbloqueartransferenciaavulsa() {
        return indbloqueartransferenciaavulsa;
    }

    public void setIndbloqueartransferenciaavulsa(boolean indbloqueartransferenciaavulsa) {
        this.indbloqueartransferenciaavulsa = indbloqueartransferenciaavulsa;
    }

    public boolean isIndgerarcreditocstoutros() {
        return indgerarcreditocstoutros;
    }

    public void setIndgerarcreditocstoutros(boolean indgerarcreditocstoutros) {
        this.indgerarcreditocstoutros = indgerarcreditocstoutros;
    }

    public boolean isIndagruparcontascontabeisiguais() {
        return indagruparcontascontabeisiguais;
    }

    public void setIndagruparcontascontabeisiguais(boolean indagruparcontascontabeisiguais) {
        this.indagruparcontascontabeisiguais = indagruparcontascontabeisiguais;
    }

    public boolean isIndvalidaraliquotapresuncao() {
        return indvalidaraliquotapresuncao;
    }

    public void setIndvalidaraliquotapresuncao(boolean indvalidaraliquotapresuncao) {
        this.indvalidaraliquotapresuncao = indvalidaraliquotapresuncao;
    }

    public boolean isIndcontacontabilunicafornecedor() {
        return indcontacontabilunicafornecedor;
    }

    public void setIndcontacontabilunicafornecedor(boolean indcontacontabilunicafornecedor) {
        this.indcontacontabilunicafornecedor = indcontacontabilunicafornecedor;
    }

    public boolean isIndempresasegmentocombustivel() {
        return indempresasegmentocombustivel;
    }

    public void setIndempresasegmentocombustivel(boolean indempresasegmentocombustivel) {
        this.indempresasegmentocombustivel = indempresasegmentocombustivel;
    }

    public boolean isIndpossuiconvenio11503() {
        return indpossuiconvenio11503;
    }

    public void setIndpossuiconvenio11503(boolean indpossuiconvenio11503) {
        this.indpossuiconvenio11503 = indpossuiconvenio11503;
    }

    public String getCodigoperfilapresentacaoempresa() {
        return codigoperfilapresentacaoempresa;
    }

    public void setCodigoperfilapresentacaoempresa(String codigoperfilapresentacaoempresa) {
        this.codigoperfilapresentacaoempresa = codigoperfilapresentacaoempresa;
    }

    public String getCodigonaturezapessoajuridica() {
        return codigonaturezapessoajuridica;
    }

    public void setCodigonaturezapessoajuridica(String codigonaturezapessoajuridica) {
        this.codigonaturezapessoajuridica = codigonaturezapessoajuridica;
    }

    public String getCodigoapuracaocontribuicoescreditos() {
        return codigoapuracaocontribuicoescreditos;
    }

    public void setCodigoapuracaocontribuicoescreditos(String codigoapuracaocontribuicoescreditos) {
        this.codigoapuracaocontribuicoescreditos = codigoapuracaocontribuicoescreditos;
    }

    public int getIdcontacontabilh010() {
        return idcontacontabilh010;
    }

    public void setIdcontacontabilh010(int idcontacontabilh010) {
        this.idcontacontabilh010 = idcontacontabilh010;
    }

    public int getIdcontacontabilcredora() {
        return idcontacontabilcredora;
    }

    public void setIdcontacontabilcredora(int idcontacontabilcredora) {
        this.idcontacontabilcredora = idcontacontabilcredora;
    }

    public int getIdcontacontabildevedora() {
        return idcontacontabildevedora;
    }

    public void setIdcontacontabildevedora(int idcontacontabildevedora) {
        this.idcontacontabildevedora = idcontacontabildevedora;
    }

    public int getIdtipoatividadepredominante() {
        return idtipoatividadepredominante;
    }

    public void setIdtipoatividadepredominante(int idtipoatividadepredominante) {
        this.idtipoatividadepredominante = idtipoatividadepredominante;
    }

    public boolean isIndcontribuinteipi() {
        return indcontribuinteipi;
    }

    public void setIndcontribuinteipi(boolean indcontribuinteipi) {
        this.indcontribuinteipi = indcontribuinteipi;
    }

    public boolean isIndcalculaestoquemovimento() {
        return indcalculaestoquemovimento;
    }

    public void setIndcalculaestoquemovimento(boolean indcalculaestoquemovimento) {
        this.indcalculaestoquemovimento = indcalculaestoquemovimento;
    }

    public int getIdcodigoregimetributariocsosn() {
        return idcodigoregimetributariocsosn;
    }

    public void setIdcodigoregimetributariocsosn(int idcodigoregimetributariocsosn) {
        this.idcodigoregimetributariocsosn = idcodigoregimetributariocsosn;
    }

    public Timestamp getDatainiciovigenciacodigoregimetributariocsosn() {
        return datainiciovigenciacodigoregimetributariocsosn;
    }

    public void setDatainiciovigenciacodigoregimetributariocsosn(Timestamp datainiciovigenciacodigoregimetributariocsosn) {
        this.datainiciovigenciacodigoregimetributariocsosn = datainiciovigenciacodigoregimetributariocsosn;
    }

    public int getIdcontacontabilcredoraservico() {
        return idcontacontabilcredoraservico;
    }

    public void setIdcontacontabilcredoraservico(int idcontacontabilcredoraservico) {
        this.idcontacontabilcredoraservico = idcontacontabilcredoraservico;
    }

    public int getIdcontacontabildevedoraservico() {
        return idcontacontabildevedoraservico;
    }

    public void setIdcontacontabildevedoraservico(int idcontacontabildevedoraservico) {
        this.idcontacontabildevedoraservico = idcontacontabildevedoraservico;
    }

    public int getIdcentrocustoservico() {
        return idcentrocustoservico;
    }

    public void setIdcentrocustoservico(int idcentrocustoservico) {
        this.idcentrocustoservico = idcentrocustoservico;
    }

    public int getCodigoapuracaopiscofins() {
        return codigoapuracaopiscofins;
    }

    public void setCodigoapuracaopiscofins(int codigoapuracaopiscofins) {
        this.codigoapuracaopiscofins = codigoapuracaopiscofins;
    }

    public boolean isIndregimeespecialpiscofins() {
        return indregimeespecialpiscofins;
    }

    public void setIndregimeespecialpiscofins(boolean indregimeespecialpiscofins) {
        this.indregimeespecialpiscofins = indregimeespecialpiscofins;
    }

    public String getIdregimetributario() {
        return idregimetributario;
    }

    public void setIdregimetributario(String idregimetributario) {
        this.idregimetributario = idregimetributario;
    }

    public boolean isIndincluirautomaticocontacontabilfornecedor() {
        return indincluirautomaticocontacontabilfornecedor;
    }

    public void setIndincluirautomaticocontacontabilfornecedor(boolean indincluirautomaticocontacontabilfornecedor) {
        this.indincluirautomaticocontacontabilfornecedor = indincluirautomaticocontacontabilfornecedor;
    }

    public boolean isIndconsiderarlancamentoisslivrosfiscais() {
        return indconsiderarlancamentoisslivrosfiscais;
    }

    public void setIndconsiderarlancamentoisslivrosfiscais(boolean indconsiderarlancamentoisslivrosfiscais) {
        this.indconsiderarlancamentoisslivrosfiscais = indconsiderarlancamentoisslivrosfiscais;
    }

    public boolean isIndexcluido() {
        return indexcluido;
    }

    public void setIndexcluido(boolean indexcluido) {
        this.indexcluido = indexcluido;
    }

    public int getIdapropriacaopiscofins() {
        return idapropriacaopiscofins;
    }

    public void setIdapropriacaopiscofins(int idapropriacaopiscofins) {
        this.idapropriacaopiscofins = idapropriacaopiscofins;
    }

    public boolean isIndescrituracaonfeconsolidada() {
        return indescrituracaonfeconsolidada;
    }

    public void setIndescrituracaonfeconsolidada(boolean indescrituracaonfeconsolidada) {
        this.indescrituracaonfeconsolidada = indescrituracaonfeconsolidada;
    }

    public int getIdtipoatividadeempresablocop() {
        return idtipoatividadeempresablocop;
    }

    public void setIdtipoatividadeempresablocop(int idtipoatividadeempresablocop) {
        this.idtipoatividadeempresablocop = idtipoatividadeempresablocop;
    }

    public int getIdcfnotacancelada() {
        return idcfnotacancelada;
    }

    public void setIdcfnotacancelada(int idcfnotacancelada) {
        this.idcfnotacancelada = idcfnotacancelada;
    }

    public int getIdcfparametronotacancelada() {
        return idcfparametronotacancelada;
    }

    public void setIdcfparametronotacancelada(int idcfparametronotacancelada) {
        this.idcfparametronotacancelada = idcfparametronotacancelada;
    }

    public int getIddatacontabilizacaodocextemporaneo() {
        return iddatacontabilizacaodocextemporaneo;
    }

    public void setIddatacontabilizacaodocextemporaneo(int iddatacontabilizacaodocextemporaneo) {
        this.iddatacontabilizacaodocextemporaneo = iddatacontabilizacaodocextemporaneo;
    }

    public boolean isIndutilizamasterfiscal() {
        return indutilizamasterfiscal;
    }

    public void setIndutilizamasterfiscal(boolean indutilizamasterfiscal) {
        this.indutilizamasterfiscal = indutilizamasterfiscal;
    }

    public boolean isIndutilizarcodigocparquivos() {
        return indutilizarcodigocparquivos;
    }

    public void setIndutilizarcodigocparquivos(boolean indutilizarcodigocparquivos) {
        this.indutilizarcodigocparquivos = indutilizarcodigocparquivos;
    }

    public boolean isIndsalvarlancamentosinconsistentes() {
        return indsalvarlancamentosinconsistentes;
    }

    public void setIndsalvarlancamentosinconsistentes(boolean indsalvarlancamentosinconsistentes) {
        this.indsalvarlancamentosinconsistentes = indsalvarlancamentosinconsistentes;
    }

    public boolean isIndsalvarnotavaloresnaofecham() {
        return indsalvarnotavaloresnaofecham;
    }

    public void setIndsalvarnotavaloresnaofecham(boolean indsalvarnotavaloresnaofecham) {
        this.indsalvarnotavaloresnaofecham = indsalvarnotavaloresnaofecham;
    }

    public int getIdtipocalculomovimentoimportacaodanfe() {
        return idtipocalculomovimentoimportacaodanfe;
    }

    public void setIdtipocalculomovimentoimportacaodanfe(int idtipocalculomovimentoimportacaodanfe) {
        this.idtipocalculomovimentoimportacaodanfe = idtipocalculomovimentoimportacaodanfe;
    }

    public boolean isINDGERAROCORRENCIADEVOLUCAOPISCOFINS() {
        return INDGERAROCORRENCIADEVOLUCAOPISCOFINS;
    }

    public void setINDGERAROCORRENCIADEVOLUCAOPISCOFINS(boolean INDGERAROCORRENCIADEVOLUCAOPISCOFINS) {
        this.INDGERAROCORRENCIADEVOLUCAOPISCOFINS = INDGERAROCORRENCIADEVOLUCAOPISCOFINS;
    }

    public boolean isIndgeraregistrom220m620spedpiscof() {
        return indgeraregistrom220m620spedpiscof;
    }

    public void setIndgeraregistrom220m620spedpiscof(boolean indgeraregistrom220m620spedpiscof) {
        this.indgeraregistrom220m620spedpiscof = indgeraregistrom220m620spedpiscof;
    }

    public double getPercentualaproximadotributosincidentes() {
        return percentualaproximadotributosincidentes;
    }

    public void setPercentualaproximadotributosincidentes(double percentualaproximadotributosincidentes) {
        this.percentualaproximadotributosincidentes = percentualaproximadotributosincidentes;
    }

    public double getIdtipocampocalculotributoaproximado() {
        return idtipocampocalculotributoaproximado;
    }

    public void setIdtipocampocalculotributoaproximado(double idtipocampocalculotributoaproximado) {
        this.idtipocampocalculotributoaproximado = idtipocampocalculotributoaproximado;
    }

    public boolean isIndeditarcontabilizacaodocumentosfiscais() {
        return indeditarcontabilizacaodocumentosfiscais;
    }

    public void setIndeditarcontabilizacaodocumentosfiscais(boolean indeditarcontabilizacaodocumentosfiscais) {
        this.indeditarcontabilizacaodocumentosfiscais = indeditarcontabilizacaodocumentosfiscais;
    }

    public boolean isIndintegrarlancamentocontabilautomatico() {
        return indintegrarlancamentocontabilautomatico;
    }

    public void setIndintegrarlancamentocontabilautomatico(boolean indintegrarlancamentocontabilautomatico) {
        this.indintegrarlancamentocontabilautomatico = indintegrarlancamentocontabilautomatico;
    }

    public boolean isIndexibirestoquesap() {
        return indexibirestoquesap;
    }

    public void setIndexibirestoquesap(boolean indexibirestoquesap) {
        this.indexibirestoquesap = indexibirestoquesap;
    }

    public int getIdtipoitemconvenio106() {
        return idtipoitemconvenio106;
    }

    public void setIdtipoitemconvenio106(int idtipoitemconvenio106) {
        this.idtipoitemconvenio106 = idtipoitemconvenio106;
    }

    public boolean isIndobrigargeracaofaturamento() {
        return indobrigargeracaofaturamento;
    }

    public void setIndobrigargeracaofaturamento(boolean indobrigargeracaofaturamento) {
        this.indobrigargeracaofaturamento = indobrigargeracaofaturamento;
    }

    public boolean isIndvalidacaoobrigatoriageracaosped() {
        return indvalidacaoobrigatoriageracaosped;
    }

    public void setIndvalidacaoobrigatoriageracaosped(boolean indvalidacaoobrigatoriageracaosped) {
        this.indvalidacaoobrigatoriageracaosped = indvalidacaoobrigatoriageracaosped;
    }

    public boolean isIndcalcularretencaoregimecaixa() {
        return indcalcularretencaoregimecaixa;
    }

    public void setIndcalcularretencaoregimecaixa(boolean indcalcularretencaoregimecaixa) {
        this.indcalcularretencaoregimecaixa = indcalcularretencaoregimecaixa;
    }

    public boolean isIndconfigbasecalculopiscof() {
        return indconfigbasecalculopiscof;
    }

    public void setIndconfigbasecalculopiscof(boolean indconfigbasecalculopiscof) {
        this.indconfigbasecalculopiscof = indconfigbasecalculopiscof;
    }

    public int getIdcontacontabiltransferencia() {
        return idcontacontabiltransferencia;
    }

    public void setIdcontacontabiltransferencia(int idcontacontabiltransferencia) {
        this.idcontacontabiltransferencia = idcontacontabiltransferencia;
    }

    public boolean isIndrequerclientefornecedorconta() {
        return indrequerclientefornecedorconta;
    }

    public void setIndrequerclientefornecedorconta(boolean indrequerclientefornecedorconta) {
        this.indrequerclientefornecedorconta = indrequerclientefornecedorconta;
    }

    public boolean isIndatualizardescricaocontacontabil() {
        return indatualizardescricaocontacontabil;
    }

    public void setIndatualizardescricaocontacontabil(boolean indatualizardescricaocontacontabil) {
        this.indatualizardescricaocontacontabil = indatualizardescricaocontacontabil;
    }

    public boolean isIndconsiderarstretidoobservacao() {
        return indconsiderarstretidoobservacao;
    }

    public void setIndconsiderarstretidoobservacao(boolean indconsiderarstretidoobservacao) {
        this.indconsiderarstretidoobservacao = indconsiderarstretidoobservacao;
    }

    public boolean isIndconsiderarstantecipadoobservacao() {
        return indconsiderarstantecipadoobservacao;
    }

    public void setIndconsiderarstantecipadoobservacao(boolean indconsiderarstantecipadoobservacao) {
        this.indconsiderarstantecipadoobservacao = indconsiderarstantecipadoobservacao;
    }

    public boolean isIndefetuarecolhimentopatronal() {
        return indefetuarecolhimentopatronal;
    }

    public void setIndefetuarecolhimentopatronal(boolean indefetuarecolhimentopatronal) {
        this.indefetuarecolhimentopatronal = indefetuarecolhimentopatronal;
    }

    public boolean isInddefinircomposicaofaturamentosimples() {
        return inddefinircomposicaofaturamentosimples;
    }

    public void setInddefinircomposicaofaturamentosimples(boolean inddefinircomposicaofaturamentosimples) {
        this.inddefinircomposicaofaturamentosimples = inddefinircomposicaofaturamentosimples;
    }
    
    
}
