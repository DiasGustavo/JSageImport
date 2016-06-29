/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.controler;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.PessoaFisica;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaSAGE;
import jsageImport.persistencia.GerenciadorPersistenciaSAGE;
import jsageImport.persistencia.IGerenciadorPersistenciaSAGE;

/**
 *
 * @author Gustavo
 */
public class ControlerFuncionarioSAGE {
 
    public List pesquisarId (String id) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaId(id);
        
        return listaEmp;
    }
    
    public List pesquisarFuncionario (int idFuncionario) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaIdFuncionario(idFuncionario);
        
        return listaEmp;
    }
    
    public List pesquisarIdDependente (int cdFuncionario, int idDependente) throws JsageImportException{
        List listaDep;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PFuncionario = gerenteP.getPersistenciaFuncionario();
        listaDep = PFuncionario.pesquisaIdDependente(cdFuncionario, idDependente);
        
        return listaDep;
    }
    
    public void gravarEmpresa (PessoaJuridica pj) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarEmpresa(pj);     
        
    }
    
    public void gravarFuncionario (int cdEmpresa, PessoaFisica pf) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarFuncionario(cdEmpresa, pf);
        
    }
    
    public void gravarDocumentos (int cdFuncionario, int cdEmpresa, DadosFuncionario pf) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDocumentos(cdFuncionario, cdEmpresa, pf);
        
    }
    
    public void gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaSAGE PEmpresa = gerenteP.getPersistenciaFuncionario();
        PEmpresa.gravarDependentes(cdFuncionario, cdEmpresa, dep);
        
    }
}
