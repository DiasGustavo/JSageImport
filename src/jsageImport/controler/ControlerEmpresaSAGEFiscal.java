/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.controler;

import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaFiscalNG;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaSAGEFiscal;
import jsageImport.persistencia.GerenciadorPersistenciaSAGE;
import jsageImport.persistencia.IGerenciadorPersistenciaSAGE;

/**
 *
 * @author Gustavo
 */
public class ControlerEmpresaSAGEFiscal {
    
    public void atualizarEmpresa (EmpresaFiscalNG empNG) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGEFiscal PEmpresa = gerenteP.getPersistenciaEmpresaSAGEFiscal();
        PEmpresa.atualizarEmpresa(empNG);        
    }
    
}
