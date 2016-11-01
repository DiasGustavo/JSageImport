/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.controler;

import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaTributacao;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaSAGE;
import jsageImport.modelo.ipersistencia.IPersistenciaSAGE;
import jsageImport.persistencia.GerenciadorPersistenciaSAGE;
import jsageImport.persistencia.IGerenciadorPersistenciaSAGE;

/**
 *
 * @author Jeff-Info
 */
public class ControlerEmpresaSAGE {
    
        public void gravarEmpresa (PessoaJuridica pj) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEmpresa(pj);     
        
    }
    
    public void gravarEstabelecimento (PessoaJuridica pj, EmpresaTributacao empTrib, EmpresaTributacao empCnae, EmpresaFolha empFolha) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEstabelecimento(pj, empTrib, empCnae, empFolha);
    }
    
    
    
    public void gravarEmpresaMatrizContabilizacao (int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEmpresaMatrizContabilizacao(cd_empresa);
    
    }
    public void gravarEstabelecimentoParametro(PessoaJuridica pj) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEstabelecimentoParametro(pj);
    }
    
    public void gravarTomador (int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarTomador(cd_empresa);
    }
    
    public void gravarCSCDFCEquivalenteCaixa(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDFCEquivalenteCaixa(cd_empresa);
    }
    
    public void gravarCRDPermissaoGrupoEstabelecimento(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCRDPermissaoGrupoEstabelecimento(cd_empresa);
    }
    
    public void gravarCSCDFCEPlano(int cd_empresa)throws JsageImportException{
       IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDFCEPlano(cd_empresa);
    }
    
     public void gravarCSCDRAPlano(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDRAPlano(cd_empresa);
    }
    
    public void gravarCRDSCPRODEC(int cd_empresa)throws JsageImportException{
       IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCRDSCPRODEC(cd_empresa);
    }
    
    public void gravarTituloDRE(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarTituloDRE(cd_empresa);
    }
    
    public void gravarTipoDRE(int cd_empresa)throws JsageImportException{
       IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarTipoDRE(cd_empresa);
    }
    
    public void gravarCapaLote(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCapaLote(cd_empresa);
    }
    
    public void gravarCSCDMPLPLANO (int cd_empresa) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDMPLPLANO(cd_empresa);
    }
    
    public void gravarEmpresaParametro (PessoaJuridica pj) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEmpresaParametro(pj);
    }
    
}