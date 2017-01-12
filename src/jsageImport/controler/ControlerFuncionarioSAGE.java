/*
 * Controller com as funções possíveis a serem utilizadas em relação aos dados do SAGE
 */
package jsageImport.controler;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionaisNG;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.FeriasNG;
import jsageImport.modelo.dominio.MovimentacaoNG;
import jsageImport.modelo.dominio.PlanoSaudeNG;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioSAGE;
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
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaCNPJ(id);
        
        return listaEmp;
    }
    
    public List pesquisarIDCNPJ(int id, String cnpj) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaIDCNPJ(id, cnpj);
        
        return listaEmp;
    }
    
    public List pesquisarFuncionario (int idFuncionario, int cdEmpresa, String cpf) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaFuncionario(idFuncionario, cdEmpresa, cpf);
        
        return listaEmp;
    }
    
    public List pesquisarFuncionarioNome (int cdEmpresa, String cpf) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaFuncionarioNome( cdEmpresa, cpf);
        
        return listaEmp;
    }
    
    public List pesquisarIdDependente (int cdFuncionario, int idDependente) throws JsageImportException{
        List listaDep;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PFuncionario = gerenteP.getPersistenciaFuncionario();
        listaDep = PFuncionario.pesquisaIdDependente(cdFuncionario, idDependente);
        
        return listaDep;
    }
    
      
    public void gravarFuncionario (int cdEmpresa, DadosFuncionario pf, DadosFuncionaisNG fun) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarFuncionario(cdEmpresa, pf, fun);
        
    }
    
    public void gravarDocumentos (int cdFuncionario, int cdEmpresa, DadosFuncionario pf) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDocumentos(cdFuncionario, cdEmpresa, pf);
        
    }
    
    public void gravarDadosLotacao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG fun) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarLotacao(cdFuncionario, cdEmpresa, fun);
        
    }
    
    public void gravarColaborador ( int cdEmpresa, int cdFuncionario, DadosFuncionario pf) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarColaborador(cdEmpresa, cdFuncionario, pf);
        
    }   
    
    public void gravarFuncao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG fun ) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarFuncao(cdFuncionario, cdEmpresa, fun);
        
    }
    
    public void gravarSalario (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG fun) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarSalario(cdFuncionario, cdEmpresa, fun);
        
    }
    
    public void gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDependentes(cdFuncionario, cdEmpresa, dep);
        
    }
    
    public void gravarDadosFuncionais (int cdEmpresa, int cdFuncionario, DadosFuncionaisNG fun, DadosFuncionario pf ) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDadosFuncionais(cdEmpresa, cdFuncionario, fun, pf);
        
    }
    
    public void gravarFerias (int cdFuncionario,int cdEmpresa, FeriasNG ferias) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarFerias(cdFuncionario, cdEmpresa, ferias);
    }
    
    public void gravarControleESocial (int cdFuncionario,int cdEmpresa) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarControleESocial(cdFuncionario, cdEmpresa);
    }
    
    public void gravarControleCamposESocial (int cdEmpresa, int cdFuncionario) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarControleCamposESocial(cdEmpresa, cdFuncionario);
    }
    public void gravarFunEspecifico (int cdFuncionario, int cdEmpresa) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarFunEspecifico(cdFuncionario, cdEmpresa);
    }
    
    public void gravarProcImposto (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarProcImposto(movimentacao, cdEmpresa, cdFuncionario);
    }
    
    public void gravarMovEvento (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarMovEvento(movimentacao, cdEmpresa, cdFuncionario);
    }
    public void gravarProcBase (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario, DadosFuncionaisNG dadosFuncionais) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarProcBase(movimentacao, cdEmpresa, cdFuncionario, dadosFuncionais);
    }
    
    
    public void gravarProcEvento (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarProcEvento(movimentacao, cdEmpresa, cdFuncionario);
    }
    
    public void gravarPlanoSaude (PlanoSaudeNG plano) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarPlanoSaude(plano);
    }
    
    public void gravarPlanoSaudeGeral (PlanoSaudeNG plano, int cdEmpresa) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarPlanoSaudeGeral(plano, cdEmpresa);
    }
    
    public void gravarPlanoSaudeFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarPlanoSaudeFuncionario(movimento, cdEmpresa, cdFuncionario);
    }
    
    public void gravarPlanoSaudeMovFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarPlanoSaudeMovFuncionario(movimento, cdEmpresa, cdFuncionario);
    }
    public void gravarPlanoSaudeProcFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarPlanoSaudeProcFuncionario(movimento, cdEmpresa, cdFuncionario);
    }   
    public boolean testarConexao (String server, String bd, String port, String user, String password) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaFuncionarioSAGE PFuncionario = gerenteP.getPersistenciaFuncionario();
        boolean resultado = false;
        if (PFuncionario.testaConexaoSAGE(server, bd, port, user, password)){
            resultado = true;
        }
        return resultado;
    }
    

}
