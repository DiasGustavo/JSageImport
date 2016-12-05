/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.controler;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaNGFiscal;
import jsageImport.persistencia.GerenciadorPersistenciaNG;
import jsageImport.persistencia.IGerenciadorPersistenciaNG;

/**
 *
 * @author Gustavo
 */
public class ControlerEmpresaNGFiscal {
   public List capturarInfoEmpresas () throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaEmpresaNGFiscal PEmpresas = gerenteP.getPersistenciaEmpresaNGFiscal();
        listaEmp = PEmpresas.capturarInfoEmpresas();
        
        return listaEmp;
    }
}
