/*
 * Controller com as funções possíveis a serem utilizadas em relação aos dados do SAGE
 */
package jsageImport.controler;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionaisNG;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaTributacao;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaSAGE;
import jsageImport.persistencia.GerenciadorPersistenciaSAGE;
import jsageImport.persistencia.IGerenciadorPersistenciaSAGE;

/**
 * @author Gustavo Dias
 * Criação: 07/06/2016
 * Última modificação: 12/10/2016
 */
public class ControlerFuncionarioSAGE {
 
    public List pesquisarCNPJ (String id) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaCNPJ(id);
        
        return listaEmp;
    }
    
    public List pesquisarFuncionario (int idFuncionario, int cdEmpresa, String cpf) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaFuncionario(idFuncionario, cdEmpresa, cpf);
        
        return listaEmp;
    }
    
    public List pesquisarFuncionarioNome (int cdEmpresa, String cpf) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaFuncionarioNome( cdEmpresa, cpf);
        
        return listaEmp;
    }
    
    public List pesquisarIdDependente (int cdFuncionario, int idDependente) throws JsageImportException{
        List listaDep;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PFuncionario = gerenteP.getPersistenciaFuncionario();
        listaDep = PFuncionario.pesquisaIdDependente(cdFuncionario, idDependente);
        
        return listaDep;
    }
    
      
    public void gravarFuncionario (int cdEmpresa, DadosFuncionario pf, DadosFuncionaisNG fun) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarFuncionario(cdEmpresa, pf, fun);
        
    }
    
    public void gravarDocumentos (int cdFuncionario, int cdEmpresa, DadosFuncionario pf) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDocumentos(cdFuncionario, cdEmpresa, pf);
        
    }
    
    public void gravarDadosLotacao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG fun) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarLotacao(cdFuncionario, cdEmpresa, fun);
        
    }
    
    public void gravarColaborador ( int cdEmpresa, int cdFuncionario, DadosFuncionario pf) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarColaborador(cdEmpresa, cdFuncionario, pf);
        
    }   
    
    public void gravarFuncao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG fun ) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarFuncao(cdFuncionario, cdEmpresa, fun);
        
    }
    
    public void gravarSalario (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG fun) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarSalario(cdFuncionario, cdEmpresa, fun);
        
    }
    
    public void gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDependentes(cdFuncionario, cdEmpresa, dep);
        
    }
    
    public void gravarDadosFuncionais (int cdEmpresa, int cdFuncionario, DadosFuncionaisNG fun, DadosFuncionario pf ) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDadosFuncionais(cdEmpresa, cdFuncionario, fun, pf);
        
    }
    
    public boolean testarConexao (String server, String bd, String port, String user, String password) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PFuncionario = gerenteP.getPersistenciaFuncionario();
        boolean resultado = false;
        if (PFuncionario.testaConexaoSAGE(server, bd, port, user, password)){
            resultado = true;
        }
        return resultado;
    }
    
     public void gravarEmpresa (PessoaJuridica pj) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarEmpresa(pj);     
        
    }
    
    public void gravarEstabelecimento (PessoaJuridica pj, EmpresaTributacao empTrib, EmpresaTributacao empCnae, EmpresaFolha empFolha) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarEstabelecimento(pj, empTrib, empCnae, empFolha);
    }
    
    
    
    public void gravarEmpresaMatrizContabilizacao (int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarEmpresaMatrizContabilizacao(cd_empresa);
    
    }
    public void gravarEstabelecimentoParametro(PessoaJuridica pj) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarEstabelecimentoParametro(pj);
    }
    
    public void gravarTomador (int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarTomador(cd_empresa);
    }
    
    public void gravarCSCDFCEquivalenteCaixa(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarCSCDFCEquivalenteCaixa(cd_empresa);
    }
    
    public void gravarCRDPermissaoGrupoEstabelecimento(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarCRDPermissaoGrupoEstabelecimento(cd_empresa);
    }
    
    public void gravarCSCDFCEPlano(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarCSCDFCEPlano(cd_empresa);
    }
    
     public void gravarCSCDRAPlano(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarCSCDRAPlano(cd_empresa);
    }
    
    public void gravarCRDSCPRODEC(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarCRDSCPRODEC(cd_empresa);
    }
    
    public void gravarTituloDRE(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarTituloDRE(cd_empresa);
    }
    
    public void gravarTipoDRE(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarTipoDRE(cd_empresa);
    }
    
    public void gravarCapaLote(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarCapaLote(cd_empresa);
    }
    
    
    
    
    
    
    
}
