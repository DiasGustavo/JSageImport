/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.ipersistencia;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionario;
import jsageImport.modelo.dominio.DependenteNG;
import jsageImport.modelo.dominio.PessoaFisica;
import jsageImport.modelo.dominio.PessoaJuridica;

/**
 *
 * @author Gustavo
 */
public interface IPersistenciaSAGE {
    
    public abstract List pesquisaId(String cnpj) throws JsageImportException;
    public abstract List pesquisaIdDependente (int cdFuncionario, int idDependente) throws JsageImportException;
    public abstract void gravarEmpresa (PessoaJuridica pj) throws JsageImportException;
    public abstract void gravarFuncionario (int cdEmpresa, PessoaFisica pf) throws JsageImportException;
    public abstract List pesquisaIdFuncionario(int idPessoa) throws JsageImportException;
    public abstract void gravarDocumentos (int cdFuncionario, int cdEmpresa, DadosFuncionario pf) throws JsageImportException;
    public abstract void gravarDependentes (int cdFuncionario, int cdEmpresa, DependenteNG dep) throws JsageImportException;
}
