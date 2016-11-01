/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.ipersistencia;

import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaTributacao;
import jsageImport.modelo.dominio.PessoaJuridica;

/**
 *
 * @author Jeff-Info
 */
public interface IPersistenciaEmpresaSAGE {
    
    public void gravarEmpresaMatrizContabilizacao (int cd_empresa)throws JsageImportException;
    public void gravarEstabelecimentoParametro(PessoaJuridica pj) throws JsageImportException;
    public void gravarTomador (int cd_empresa) throws JsageImportException;
    public void gravarCSCDFCEquivalenteCaixa (int cd_empresa)throws JsageImportException;
    public void gravarCRDPermissaoGrupoEstabelecimento (int cd_empresa) throws JsageImportException;
    public void gravarCSCDRAPlano(int cd_empresa)throws JsageImportException;
    public void gravarCSCDFCEPlano (int cd_empresa)throws JsageImportException;
    public void gravarCRDSCPRODEC (int cd_empresa) throws JsageImportException;
    public void gravarTituloDRE (int cd_empresa)throws JsageImportException;
    public void gravarTipoDRE (int cd_empresa) throws JsageImportException;
    public void gravarCapaLote (int cd_empresa) throws JsageImportException;
    public void gravarCSCDMPLPLANO (int cd_empresa) throws JsageImportException;
    public void gravarEmpresaParametro (PessoaJuridica pj) throws JsageImportException;
    
    
    public abstract void gravarEstabelecimento (PessoaJuridica pj, EmpresaTributacao empTrib, EmpresaTributacao empCnae, EmpresaFolha empFolha) throws JsageImportException;
    public abstract void gravarEmpresa (PessoaJuridica pj) throws JsageImportException;
   
    
}