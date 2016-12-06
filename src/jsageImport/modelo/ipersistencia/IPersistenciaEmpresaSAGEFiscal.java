/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.ipersistencia;

import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaFiscalNG;

/**
 *
 * @author Gustavo
 */
public interface IPersistenciaEmpresaSAGEFiscal {
    
    public abstract void atualizarEmpresa (EmpresaFiscalNG empNG) throws JsageImportException;
    
}
