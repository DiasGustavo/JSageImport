/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.ipersistencia;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.FuncionarioSAGE;

/**
 * @author Gustavo Dias
 * Criação: 06/06/2016
 * Última modificação: 06/06/2016
 */
public interface IPersistenciaFuncionarioNG {
    
    public abstract void gravarFuncionario (FuncionarioSAGE fun) throws JsageImportException;
    public abstract void excluirFuncionario (FuncionarioSAGE fun) throws JsageImportException;
    public abstract List pesquisarTodos() throws JsageImportException;
    public abstract List recuperarEmpresas() throws JsageImportException;
    public abstract List capturarInfoEmpresasComFun () throws JsageImportException;
    public abstract List pesquisaId (int id) throws JsageImportException;
    public abstract List capturarInfoFuncionariosNG (int idEmpresa) throws JsageImportException;
    public abstract List capturarInfoDependente (int idPessoa) throws JsageImportException;
    public abstract List capturarInfoPai (int idPessoa) throws JsageImportException;
    public abstract List capturarInfoMae (int idPessoa) throws JsageImportException;
    public abstract List recuperarDadosFuncionais(int id) throws JsageImportException;
    public abstract boolean TestaConexao (String server, String bd, String port, String user, String password) throws JsageImportException;
    public abstract void ImportaEmpresas (String cnpj) throws JsageImportException;
    public abstract String importaFuncionarios (int idFuncionario, int cdEmpresa, String cpf) throws JsageImportException;
    public abstract int SizeImport() throws JsageImportException;
       
    
}
