/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.ipersistencia;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionaisNG;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaTributacao;
import jsageImport.modelo.dominio.FeriasNG;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.dominio.Sindicato;

/**
 *
 * @author Gustavo
 */
public interface IPersistenciaFuncionarioSAGE {
    
    public abstract List pesquisaCNPJ(String cnpj) throws JsageImportException;
    public abstract List pesquisaIdDependente (int cdFuncionario, int idDependente) throws JsageImportException;
    public abstract List pesquisaFuncionario(int idPessoa, int cdEmpresa, String cpf) throws JsageImportException;
    public abstract List pesquisaFuncionarioNome( int cdEmpresa, String cpf) throws JsageImportException;
    
    public abstract void gravarFuncionario (int cdEmpresa, DadosFuncionario pf, DadosFuncionaisNG fun) throws JsageImportException;
    public abstract void gravarDocumentos (int cdFuncionario, int cdEmpresa, DadosFuncionario pf) throws JsageImportException;
    public abstract void gravarLotacao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG df) throws JsageImportException;
    public abstract void gravarColaborador (int cdEmpresa, int cdFuncionario, DadosFuncionario df) throws JsageImportException;
    public abstract void gravarFuncao (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG df) throws JsageImportException;
    public abstract void gravarSalario (int cdFuncionario, int cdEmpresa, DadosFuncionaisNG df) throws JsageImportException;
    public abstract void gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep) throws JsageImportException;
    public abstract void gravarDadosFuncionais (int cdEmpresa, int cdFuncionario, DadosFuncionaisNG df, DadosFuncionario fun) throws JsageImportException;
    public abstract void gravarFerias (int cdFuncionario,int cdEmpresa, FeriasNG ferias) throws JsageImportException;
    public abstract void gravarControleESocial (int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public void gravarControleCamposESocial (int cdEmpresa, int cdFuncionario) throws JsageImportException;
    
    
    public boolean testaConexaoSAGE (String server, String bd, String port, String user, String password) throws JsageImportException;
}
