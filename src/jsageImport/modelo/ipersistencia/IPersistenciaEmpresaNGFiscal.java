/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.ipersistencia;

import java.util.List;
import jsageImport.exception.JsageImportException;

/**
 *
 * @author Gustavo
 */
public interface IPersistenciaEmpresaNGFiscal {
    public abstract void importarEmpresaFiscalNG(int idEmpresa, String cnpj)throws JsageImportException;
    public abstract List pesquisarEmpresa (int idEmpresa) throws JsageImportException;
    public abstract List capturarInfoEmpresas() throws JsageImportException;
}
