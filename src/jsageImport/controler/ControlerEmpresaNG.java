/*
 * Controller com as funções possíveis a serem utilizadas em relação a dados das
 * Empresas no NG.
 */
package jsageImport.controler;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaNG;
import jsageImport.persistencia.GerenciadorPersistenciaNG;
import jsageImport.persistencia.IGerenciadorPersistenciaNG;

/**
 * @author Gustavo Dias
 * Criação: 12/10/2016
 * Última modificação: 22/10/2016
 */
public class ControlerEmpresaNG {
    
    public List pesquisarEmpresas () throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaEmpresaNG PEmpresas = gerenteP.getPersistenciaEmpresa();
        listaEmp = PEmpresas.capturarInfoEmpresasComFun();
        
        return listaEmp;
    }
}