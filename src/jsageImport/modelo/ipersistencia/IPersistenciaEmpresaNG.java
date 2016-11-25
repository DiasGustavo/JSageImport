/*
 * Interface que apresenta as funções que são implementadas pela classe PeristenciaEmpresaNG
 */
package jsageImport.modelo.ipersistencia;

import java.util.List;
import jsageImport.exception.JsageImportException;

/**
 * @author Gustavo Dias
 * Criação: 12/10/2016
 * Última modificação: 22/10/2016
 */
public interface IPersistenciaEmpresaNG {
    public abstract List pesquisarTodos() throws JsageImportException;
    public abstract List recuperarEmpresas() throws JsageImportException;
    public abstract List capturarInfoEmpresasComFun () throws JsageImportException;
    public abstract void ImportaEmpresas (int idEmpresa, String cnpj) throws JsageImportException;
    public abstract void ImportaTodasEmpresas(int idEmpresa, String cnpj) throws JsageImportException;
    public abstract List recuperarAgenciaNG () throws JsageImportException;
    public abstract List pesquisarCnpj(int idEmpresa, String cnpj) throws JsageImportException;
    public abstract List pesquisarEmpresaPorID(int idEmpresa) throws JsageImportException;
}
