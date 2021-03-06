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
import jsageImport.modelo.dominio.FeriasNG;
import jsageImport.modelo.dominio.MovimentacaoNG;
import jsageImport.modelo.dominio.PlanoSaudeNG;

/**
 *
 * @author Gustavo
 */
public interface IPersistenciaFuncionarioSAGE {
    
    public abstract List pesquisaCNPJ(String cnpj) throws JsageImportException;
    public abstract List pesquisaIDCNPJ(int id, String cnpj) throws JsageImportException;
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
    public abstract void gravarControleCamposESocial (int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public abstract void gravarFunEspecifico (int cdFuncionario, int cdEmpresa) throws JsageImportException;
    public void gravarMovEvento (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public void gravarProcBase (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario, DadosFuncionaisNG dadosFuncionais) throws JsageImportException;
    public void gravarProcEvento (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public void gravarProcImposto (MovimentacaoNG movimentacao, int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public abstract void gravarPlanoSaude (PlanoSaudeNG plano) throws JsageImportException;
    public abstract void gravarPlanoSaudeGeral (PlanoSaudeNG plano, int cdEmpresa) throws JsageImportException;
    public abstract void gravarPlanoSaudeFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public abstract void gravarPlanoSaudeMovFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public abstract void gravarPlanoSaudeProcFuncionario (MovimentacaoNG movimento, int cdEmpresa, int cdFuncionario) throws JsageImportException;
    public boolean testaConexaoSAGE (String server, String bd, String port, String user, String password) throws JsageImportException;
}
