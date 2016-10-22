/*
 * Interface dos metodos implementados pela classe PersistenciaFuncionarioNG
 */
package jsageImport.modelo.ipersistencia;

import java.util.List;
import jsageImport.exception.JsageImportException;

/**
 * @author Gustavo Dias
 * Criação: 06/06/2016
 * Última modificação: 06/06/2016
 */
public interface IPersistenciaFuncionarioNG {
    
    public abstract List pesquisarTodos() throws JsageImportException;
    public abstract List pesquisaId (int id) throws JsageImportException;
    public abstract List capturarInfoFuncionariosNG (int idEmpresa) throws JsageImportException;
    public abstract List capturarInfoDependente (int idPessoa) throws JsageImportException;
    public abstract List capturarInfoPai (int idPessoa) throws JsageImportException;
    public abstract List capturarInfoMae (int idPessoa) throws JsageImportException;
    public abstract List recuperarDadosFuncionais(int id) throws JsageImportException;
    public abstract boolean TestaConexao (String server, String bd, String port, String user, String password) throws JsageImportException;
    public abstract String importaFuncionarios (int idFuncionario, int cdEmpresa, String cpf) throws JsageImportException;
    public abstract int SizeImport() throws JsageImportException;
       
    
}
