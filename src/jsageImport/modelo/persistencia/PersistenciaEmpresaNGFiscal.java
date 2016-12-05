/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaFiscalNG;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaNGFiscal;

/**
 *
 * @author Gustavo
 */
public class PersistenciaEmpresaNGFiscal implements IPersistenciaEmpresaNGFiscal{
    private PropertiesJdbc jdbc = new PropertiesJdbc();
    private TratamentoDados trataDados = new TratamentoDados();
   /*
    * String SQL para consultas no banco NG  
    * Strings SQL para informações das empreas no NG
    */
    
    private static final String SQL_SELECT_CRD_EMPRESA_NG = "SELECT * FROM (bpm_dadosempresafiscal as fiscal " +
                                                                     "LEFT JOIN bpm_dadosempresafiscalatividadeprevidenciaria as fiscalprev on fiscal.iddadosempresafiscal = fiscalprev.iddadosempresafiscal " +
                                                                     "LEFT JOIN bpm_dadosempresafiscalcampofiscal as fiscalcampo on fiscal.iddadosempresafiscal = fiscalcampo.iddadosempresafiscal" +
                                                                     "LEFT JOIN bpm_dadosempresafiscaldmed as fiscalmed on fiscal.iddadosempresafiscal = fiscalmed.iddadosempresafiscal " +
                                                                     "LEFT JOIN bpm_dadosempresafiscalrateiopiscofins as fiscalpisconfis on fiscal.iddadosempresafiscal = fiscalpisconfis.iddadosempresafiscal " +
                                                                     "LEFT JOIN bpm_dadosempresafiscalst as fiscalst on fiscal.iddadosempresafiscal = fiscalst.iddadosempresafiscal " +
                                                                     "LEFT JOIN bpm_dadosempresafiscaltributo as fiscaltributo on fiscal.iddadosempresafiscal = fiscaltributo.iddadosempresafiscal " +
                                                                     "LEFT JOIN bpm_pessoa as pessoa on fiscal.idpessoa = pessoa.idpessoa " +
                                                                     "LEFT JOIN bpm_dadospessoajuridica as pjuridica on fiscal.idpessoa = pjuridica.idpessoa) " +
                                                                " WHERE fiscal.idpessoa = ?";
    
    private static final String SQL_IDS_EMPRESAS_NG = "SELECT DISTINCT idpessoa FROM bpm_dadosempresafiscal";
    private static final String SQL_INFO_EMPRESAS_NG = "SELECT * FROM (bpm_dadospessoajuridica AS pj INNER JOIN bpm_pessoa AS p ON p.idpessoa = pj.idpessoa" 
                                                            + " INNER JOIN bpm_pessoaendereco AS pe ON p.idpessoa = pe.idpessoa) " +
                                                              " WHERE (P.idpessoa = ?);";
    
    /*url para conexao com o banco do ng*/    
    //jdbc:sqlserver://servidor:porta;databaseName=banco;user=usuario;password=senha;"
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";";
    
    @Override
    public List pesquisarEmpresa (int idEmpresa) throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_SELECT_CRD_EMPRESA_NG);
            stmt.setInt(1, idEmpresa);
            rs = stmt.executeQuery();
            List listaEmpresas = new ArrayList();
            while (rs.next()) {
                EmpresaFiscalNG fun = criarEmpresaFiscalNG(rs);
                listaEmpresas.add(fun);
            }
            return listaEmpresas;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta dos dados da empresa no módulo fiscal.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    private List pesquisarIdsEmpresas () throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = GerenciadorConexao.getConnection(urlNG);
            stmt = con.prepareStatement(SQL_IDS_EMPRESAS_NG);
            
            rs = stmt.executeQuery();
            List listaIdsEmpresas = new ArrayList();
            
            while (rs.next()) {
                listaIdsEmpresas.add(rs.getInt("idpessoa"));
            }
            return listaIdsEmpresas;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta das ids das empresas.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    @Override
    public List capturarInfoEmpresas() throws JsageImportException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List listaEmpresas = new ArrayList();
            List listaConsulta = pesquisarIdsEmpresas();
            if (listaConsulta.size() > 0){
                con = GerenciadorConexao.getConnection(urlNG);
                stmt = con.prepareStatement(SQL_INFO_EMPRESAS_NG);
            
                for (int i = 0; i < listaConsulta.size(); i++ ){
                    int idpessoa = (int)listaConsulta.get(i);
                    stmt.setInt(1,idpessoa);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        PessoaJuridica pj = criarEmpresaNG(rs);
                        listaEmpresas.add(pj);
                    }
                } 
            }else{
                throw new JsageImportException("Não foi encontrado Empreasas!");
            }
                        
            
            return listaEmpresas;
        } catch (SQLException exc) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta das informações das Empresas.");
            mensagem.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(mensagem.toString());
        } finally {
            GerenciadorConexao.closeConexao(con, stmt, rs);
        }
    }
    
    @Override
    public void importarEmpresaFiscalNG()throws JsageImportException{
        
    }

    private EmpresaFiscalNG criarEmpresaFiscalNG(ResultSet rs)throws JsageImportException {
        EmpresaFiscalNG empresaNG = new EmpresaFiscalNG();
        try{
            empresaNG.setIdPessoa(rs.getInt("idpessoa"));
            empresaNG.setNomeFantasia(rs.getString("nomefantasia"));
            empresaNG.setNomeAbreviado(rs.getString("nomeabreviado"));
            empresaNG.setCnpjFormatado(rs.getString("cnpjformatado"));
            empresaNG.setCnpj(rs.getString("cnpj"));
            empresaNG.setIdNaturezaJuridica(rs.getInt("idnaturezajuridica"));
            empresaNG.setIdQualificacaoEmpresa(rs.getInt("idqualificacaoempresa"));
            empresaNG.setCapitalSocialInicial(rs.getDouble("capitalsocialinicial"));
            empresaNG.setDataFundacao(rs.getTimestamp("datafundacao"));
            empresaNG.setNumeroProprietarios(rs.getInt("numeroproprietarios"));
            empresaNG.setNirc(rs.getString("nirc"));
            empresaNG.setIdTipoEntidade(rs.getInt("idtipoentidade"));
            empresaNG.setInscricaoEstadual(rs.getString("inscricaoestadual"));
            empresaNG.setInscricaoMunicipal(rs.getString("inscricaomunicipal"));
            empresaNG.setObjetoSocial(rs.getString("objetosocial"));
            empresaNG.setNumeroRegistoJunta(rs.getString("numeroregistrojunta"));
            empresaNG.setDataRegistroJunta(rs.getTimestamp("dataregistrojunta"));
            empresaNG.setNomeJunta(rs.getString("nomejunta"));
            empresaNG.setDataInicioAtividade(rs.getTimestamp("datainicioatividade"));
            empresaNG.setNumeroCei(rs.getString("numerocei"));
            empresaNG.setIdtranscricaomapaecfsaida(rs.getInt("idtranscricaomapaecfsaida"));
            empresaNG.setIdcfpadraoentrada(rs.getInt("idcfpadraoentrada"));
            empresaNG.setIdmodelolivroregistroentrada(rs.getInt("idmodelolivroregistroentrada"));
            empresaNG.setIdmodelolivroregistrosaida(rs.getInt("idmodelolivroregistrosaida"));
            empresaNG.setIddadosguiaicmsst(rs.getInt("iddadosguiaicmsst"));
            empresaNG.setIddadosguiaicmsnormal(rs.getInt("iddadosguiaicmsnormal"));
            empresaNG.setIdcfpadraosaida(rs.getInt("idcfpadraosaida"));
            empresaNG.setIdmovimentocompensasaoretencao(rs.getInt("idmovimentocompensacaoretencao"));
            empresaNG.setIddadosfornecedorregistroentrada(rs.getInt("iddadosfornecedorregistroentrada"));
            empresaNG.setIddestaquestregistroentrada(rs.getInt("iddestaquestregistroentrada"));
            empresaNG.setIdmodeloresumoapuracaoipi(rs.getInt("idmodeloresumoapuracaoipi"));
            empresaNG.setIdseriepadraosaida(rs.getInt("idseriepadraosaida"));
            empresaNG.setSeriepadraoentrada(rs.getString("seriepadraoentrada"));
            empresaNG.setSubseriepadraoentrada(rs.getString("subseriepadraoentrada"));
            empresaNG.setEspeciepadraoentrada(rs.getString("especiepadraoentrada"));
            empresaNG.setIddadosguiaaliquota(rs.getInt("iddadosguiaaliquota"));
            empresaNG.setIddadosguiaiss(rs.getInt("iddadosguiaiss"));
            empresaNG.setIndrecolhimentosimplificadoicms(rs.getBoolean("indrecolhimentosimplificadoicms"));
            empresaNG.setIndconvenio10696(rs.getBoolean("indconvenio10696"));
            empresaNG.setIndprodutorrural(rs.getBoolean("indprodutorrural"));
            empresaNG.setIndcontroleunidadesvs(rs.getBoolean("indcontroleunidadesvs"));
            empresaNG.setIndescrituracaocentralizada(rs.getBoolean("indescrituracaocentralizada"));
            empresaNG.setIndeditarmaterial(rs.getBoolean("indeditarmaterial"));
            empresaNG.setIndeditarfatura(rs.getBoolean("indeditarfatura"));
            empresaNG.setIndmudardiaautomaticosaida(rs.getBoolean("indmudardiaautomaticosaida"));
            empresaNG.setIndcontrolatributomovimentocfop(rs.getBoolean("indcontrolatributomovimentocfop"));
            empresaNG.setIndexibiroperacaoprestacaointerestadual(rs.getBoolean("indexibiroperacaoprestacaointerestadual"));
            empresaNG.setIndexibirobservacaoincidenciaimposto(rs.getBoolean("indexibirobservacaoincidenciaimposto"));
            empresaNG.setValorretencaopiscofinscsll(rs.getDouble("valorretencaopisconfinscsll"));
            empresaNG.setValornaoconsiderarirrf(rs.getDouble("valornaoconsiderarirrf"));
            empresaNG.setValornaoconsiderarinss(rs.getDouble("valornaoconsiderarinss"));
            empresaNG.setIndgerarfaturacondicaooutros(rs.getBoolean("indgerarfaturacondicaooutros"));
            empresaNG.setIdtipoperiodicidadelancamento(rs.getInt("idtipoperiodicidadelancamento"));
            empresaNG.setIdcontacontabillancamentoavista(rs.getInt("idcontacontabillancamentoavista"));
            empresaNG.setIndexibircontabilizacaodadosfiscal(rs.getBoolean("indexibircontabilizacaodadosfiscal"));
            empresaNG.setIndexibirobservacaogt(rs.getBoolean("indexibirobservacaogt"));
            empresaNG.setIndcontabilizacaosubcodigo(rs.getBoolean("indcontabilizacaosubcodigo"));
            empresaNG.setIdtipoopcaoimpressaorelatorio(rs.getInt("idtipoopcaoimpressaorelatorio"));
            empresaNG.setIdcontacontabilbaixafatura(rs.getInt("idcontacontabilbaixafatura"));
            empresaNG.setCodigoatividadepadrao(rs.getString("codigoatividadepadrao"));
            empresaNG.setIdmodelolivrociap(rs.getInt("idmodelolivrociap"));
            empresaNG.setIndpossuialiquotadifpiscofinscontribuinte(rs.getBoolean("indpossuialiquotadifpiscofinscontribuinte"));
            empresaNG.setIndpossuialiquotadifpiscofinssubstituto(rs.getBoolean("indpossuialiquotadifpiscofinssubstituto"));
            empresaNG.setIndincluiautomaticocontacontabilclientefornecedor(rs.getBoolean("indincluiautomaticocontacontabilclientefornecedor"));
            empresaNG.setIdcontacontabilcliente(rs.getInt("idcontacontabilcliente"));
            empresaNG.setIdcontacontabilfornecedor(rs.getInt("idcontacontabilfornecedor"));
            empresaNG.setIndobrigasubcodigomovimento(rs.getBoolean("indobrigasubcodigomovimento"));
            empresaNG.setIdcfparametropadraoentrada(rs.getInt("idcfparametropadraoentrada"));
            empresaNG.setIdcfpadraosaida(rs.getInt("idcfparametropadraosaida"));
            empresaNG.setIndvalidarsequencianotaemissao(rs.getBoolean("indvalidarsequencianotaemissao"));
            empresaNG.setIndgerarocorrenciadifaliquota(rs.getBoolean("indgerarocorrenciadifaliquota"));
            empresaNG.setIdtipoocorrencia(rs.getInt("idtipoocorrencia"));
            empresaNG.setIndabaterdebitoipiimpostosfederais(rs.getBoolean("indabaterdebitoipiimpostosfederais"));
            empresaNG.setIndcontabilizarsituacaotributaria91(rs.getBoolean("indcontabilizarsituacaotributaria91"));
            empresaNG.setIndcriarlinha91semipi(rs.getBoolean("indcriarlinha91semipi"));
            empresaNG.setIndmensagemsequencianotas(rs.getBoolean("indmensagemsequencianotas"));
            empresaNG.setIndcontacontabilunicaclientefornecedor(rs.getBoolean("indcontacontabilunicaclientefornecedor"));
            empresaNG.setIdcontacontabilclienteunica(rs.getInt("idcontacontabilclienteunica"));
            empresaNG.setIdcontacontabilfornecedor(rs.getInt("idcontacontabilfornecedor"));
            empresaNG.setIndutilizaretencaooutrosparametros(rs.getBoolean("indutilizarrentencaooutroparametros"));
            empresaNG.setIndtiporetencaosaidas(rs.getString("indtiporentencaosaidas"));
            empresaNG.setIndtiporetencaoentradas(rs.getString("indtiporentencaoentradas"));
            empresaNG.setIndabatervalorfretefaturamento(rs.getBoolean("indabatervalorferetefaturamento"));
            empresaNG.setIdtipocontribuintepiscofins(rs.getInt("idtipocontribuintepiscofins"));
            empresaNG.setIndutilizacodigoexternomercserv(rs.getBoolean("indutilizacodigoexternomercserv"));
            empresaNG.setIndutilizaletrasmercservarquivos(rs.getBoolean("indutilizaletrasmercservarquivos"));
            empresaNG.setIndpermiteclientefornecedorsemcontacontabil(rs.getBoolean("indpermiteclientefornecedorsemcontacontabil"));
            empresaNG.setIndcontrolamercadoriaservicoporfilial(rs.getBoolean("indcontrolamercadoriaservicoporfilial"));
            empresaNG.setIndeditarservico(rs.getBoolean("indeditarservico"));
            empresaNG.setNumeroregistroanp(rs.getString("numeroregistroanp"));
            empresaNG.setIndgerarfaturacondicaopagamentooutrascontabilizacao(rs.getBoolean("indgerarfaturacondicaopagamentooutrascontabilizacao"));
            empresaNG.setIndbloqueartransferenciaavulsa(rs.getBoolean("indbloqueartransferenciaavulsa"));
            empresaNG.setIndgerarcreditocstoutros(rs.getBoolean("indgerarcreditocstoutros"));
            empresaNG.setIndagruparcontascontabeisiguais(rs.getBoolean("indagruparcontascontabeisiguais"));
            empresaNG.setIndvalidaraliquotapresuncao(rs.getBoolean("indvalidaraliquotapersuncao"));
            empresaNG.setIndcontacontabilunicafornecedor(rs.getBoolean("indcontacontabilunicafornecedor"));
            empresaNG.setIndempresasegmentocombustivel(rs.getBoolean("indemprsasegmentocombustivel"));
            empresaNG.setIndpossuiconvenio11503(rs.getBoolean("indpossuiconvenio11503"));
            empresaNG.setCodigoperfilapresentacaoempresa(rs.getString("codigoperfilapresentacaoempresa"));
            empresaNG.setCodigonaturezapessoajuridica(rs.getString("codigonaturecapessoajuridica"));
            empresaNG.setCodigoapuracaocontribuicoescreditos(rs.getString("codigoapuracaocontribuicoescreditos"));
            empresaNG.setIdcontacontabilh010(rs.getInt("idcontacontabilh010"));
            empresaNG.setIdcontacontabilcredora(rs.getInt("idcontacontabilcredora"));
            empresaNG.setIdcontacontabildevedora(rs.getInt("idcontacontabildevedora"));
            empresaNG.setIdtipoatividadepredominante(rs.getInt("idtipoatividadepredominante"));
            empresaNG.setIndcontribuinteipi(rs.getBoolean("indcontribuinteipi"));
            empresaNG.setIndcalculaestoquemovimento(rs.getBoolean("indcalculaestoquemovimento"));
            empresaNG.setIdcodigoregimetributariocsosn(rs.getInt("idcodigoregimetributariocsosn"));
            empresaNG.setDatainiciovigenciacodigoregimetributariocsosn(rs.getTimestamp("datainiciovigenciacodigoregimetributariocsosn"));
            empresaNG.setIdcontacontabilcredoraservico(rs.getInt("idcontacontabilcredotraservico"));
            empresaNG.setIdcontacontabildevedoraservico(rs.getInt("dicontacontabildevedoraservico"));
            empresaNG.setIdcentrocustoservico(rs.getInt("idcentrocustoservico"));
            empresaNG.setCodigoapuracaopiscofins(rs.getInt("codigoapuracaopiscofins"));
            empresaNG.setIndregimeespecialpiscofins(rs.getBoolean("indregimeespecialpiscofins"));
            empresaNG.setIdregimetributario(rs.getString("idregimetributario"));
            empresaNG.setIndincluirautomaticocontacontabilfornecedor(rs.getBoolean("indincluirautomaticocontacontabilfornecedor"));
            empresaNG.setIndconsiderarlancamentoisslivrosfiscais(rs.getBoolean("indconsiderarlancamentoissolivrosfiscais"));
            empresaNG.setIndexcluido(rs.getBoolean("indexcluido"));
            empresaNG.setIdapropriacaopiscofins(rs.getInt("idapropriacaopiscofins"));
            empresaNG.setIndescrituracaonfeconsolidada(rs.getBoolean("indescrituracaonfeconsolidada"));
            empresaNG.setIdtipoatividadeempresablocop(rs.getInt("idtipoatividadeempresablocop"));
            empresaNG.setIdcfnotacancelada(rs.getInt("idcfnotacancelada"));
            empresaNG.setIdcfparametronotacancelada(rs.getInt("idcfparametronotacancelada"));
            empresaNG.setIddatacontabilizacaodocextemporaneo(rs.getInt("iddatacontabilizacaodocextemporaneio"));
            empresaNG.setIndutilizamasterfiscal(rs.getBoolean("indutilizamasterfiscal"));
            empresaNG.setIndutilizarcodigocparquivos(rs.getBoolean("indutilizarcodigocparquivos"));
            empresaNG.setIndsalvarlancamentosinconsistentes(rs.getBoolean("indsalavarlancamentosinconsistentes"));
            empresaNG.setIndsalvarnotavaloresnaofecham(rs.getBoolean("indsalvarnotavaloresnaofecham"));
            empresaNG.setIdtipocalculomovimentoimportacaodanfe(rs.getInt("idtipocalculomovimentoimportacaodanfe"));
            empresaNG.setINDGERAROCORRENCIADEVOLUCAOPISCOFINS(rs.getBoolean("INDGERAROCORRENCIADEVOLUCAOPISCOFINS"));
            empresaNG.setIndgeraregistrom220m620spedpiscof(rs.getBoolean("indgerarregistrom220m620spedpiscof"));
            empresaNG.setPercentualaproximadotributosincidentes(rs.getDouble("percentualaproximadotributosincidentes"));
            empresaNG.setIdtipocampocalculotributoaproximado(rs.getInt("idtipocampocalculotributoaproximado"));
            empresaNG.setIndeditarcontabilizacaodocumentosfiscais(rs.getBoolean("indeditarcontabilizacaodocumentosfiscais"));
            empresaNG.setIndintegrarlancamentocontabilautomatico(rs.getBoolean("indintegrarlancamentocontabilautomatico"));
            empresaNG.setIndexibirestoquesap(rs.getBoolean("indexibirestoquesap"));
            empresaNG.setIdtipoitemconvenio106(rs.getInt("idtipoitemconvenio106"));
            empresaNG.setIndobrigargeracaofaturamento(rs.getBoolean("indobrigargeracaofaturamento"));
            empresaNG.setIndvalidacaoobrigatoriageracaosped(rs.getBoolean("indvalidacaoobrigaotirageracaosped"));
            empresaNG.setIndcalcularretencaoregimecaixa(rs.getBoolean("indcalcularretencaoregimecaixa"));
            empresaNG.setIndconfigbasecalculopiscof(rs.getBoolean("indconfigbasecalculopiscof"));
            empresaNG.setIdcontacontabiltransferencia(rs.getInt("idcontacontabiltransferencia"));
            empresaNG.setIndrequerclientefornecedorconta(rs.getBoolean("indrequerclientefornecedorconta"));
            empresaNG.setIndatualizardescricaocontacontabil(rs.getBoolean("indatualixardescricaocontacontabil"));
            empresaNG.setIndconsiderarstretidoobservacao(rs.getBoolean("indconsiderarstretidoobservacao"));
            empresaNG.setIndconsiderarstantecipadoobservacao(rs.getBoolean("indconsiderarstantecipadoobservacao"));
            empresaNG.setIndefetuarecolhimentopatronal(rs.getBoolean("indefetuarecolhimentopatronal"));
            empresaNG.setInddefinircomposicaofaturamentosimples(rs.getBoolean("inddefinircomposicaofaturamentosimples"));
            
            return empresaNG;
        }catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível criar o objeto empresaNGFiscal.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        
    }
    
    private PessoaJuridica criarEmpresaNG(ResultSet rs) throws JsageImportException{
        PessoaJuridica pj = new PessoaJuridica();
        try{
            /* Dados de uma pessoa comum*/
            pj.setIdPessoa(rs.getInt("idpessoa"));
            pj.setCodigoPessoa(rs.getString("codigopessoa"));
            pj.setNomePessoa(rs.getString("nomepessoa"));
            pj.setIndFabrica(rs.getBoolean("indfabrica"));
            pj.setObservacao(rs.getString("observacao"));
            pj.setInddadosConvertidos(rs.getBoolean("inddadosconvertidos"));
            pj.setIndDesativada(rs.getBoolean("inddesativada"));
            pj.setDataCadastramento(rs.getTimestamp("datacadastramento"));
            pj.setFotoPessoa(rs.getString("fotopessoa"));
            pj.setBiometria(rs.getBytes("biometria"));
            pj.setNumeroCei(rs.getString("numerocei"));
            pj.setCodigoExternoEmpresa(rs.getString("codigoexternoempresa"));
            pj.setCodigoExternoFilial(rs.getString("codigoexternofilial"));
            pj.setDocumentoEstrangeiro(rs.getString("documentoestrangeiro"));
            pj.setCno(rs.getString("cno"));
            pj.setCaepf(rs.getString("caepf"));
            pj.setIdtipocaepf(rs.getInt("idtipocaepf"));
            /*Dados endereço*/
            pj.setLogradouro(rs.getString("logradouro"));
            pj.setNumeroEndereco(rs.getString("numeroendereco"));
            pj.setComplemento(rs.getString("complemento"));
            pj.setBairro(rs.getString("bairro"));
            pj.setIdmunicipio(rs.getInt("idmunicipio"));
            pj.setCep(rs.getString("cep"));
            pj.setIdmunicipio(rs.getInt("idmunicipio"));
            
            /*Dados da pessoa juridica*/
            pj.setNomeFantasia(rs.getString("nomefantasia"));
            pj.setCnpj(rs.getString("cnpj"));
            pj.setInscricaoEstadual(rs.getString("inscricaoestadual"));
            pj.setInscricaoMunicipal(rs.getString("inscricaomunicipal"));
            pj.setObjetoSocial(rs.getString("objetosocial"));
            pj.setNumeroRegistoJunta(rs.getString("numeroregistrojunta"));
            pj.setNomeJunta(rs.getString("nomejunta"));
            pj.setDataInicioAtividade(rs.getTimestamp("datainicioatividade"));
            pj.setDataTerminoSociedade(rs.getTimestamp("dataterminosociedade"));
            pj.setCapitalSocialInicial(rs.getDouble("capitalsocialInicial"));
            pj.setDataFundacao(rs.getTimestamp("datafundacao"));
            pj.setDataFimAtividade(rs.getTimestamp("datafimatividade"));
            pj.setNumeroProprietarios(rs.getInt("numeroproprietarios"));
            pj.setNirc(rs.getString("nirc"));
            pj.setIdNaturezaJuridica(rs.getInt("idnaturezajuridica"));
            pj.setNomeAbreviado(rs.getString("nomeabreviado"));
            pj.setIdQualificacaoEmpresa(rs.getInt("idqualificacaoempresa"));
            pj.setCodigoUfRama(rs.getString("codigosuframa"));
            pj.setIdTipoOrgaoPublico(rs.getInt("idtipoorgaopublico"));
            pj.setIdTipoEntidade(rs.getInt("idtipoentidade"));
            pj.setDataInicioInscricaoEstadual(rs.getTimestamp("datainicioinscricaoestadual"));
            pj.setCnpjFormatado(rs.getString("cnpjformatado"));
            pj.setIdIdentificadorInscricaoEstadualNfe(rs.getInt("idindicadorinscricaoestadualnfe"));
            pj.setIndEmpresaExterna(rs.getBoolean("indempresaexterna"));
            
            
        } catch (SQLException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível obter os dados da Pessoa Jurídica.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            throw new JsageImportException(mensagem.toString());
        }
        return pj;
    }
}
