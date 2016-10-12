/*
 * Controller com as funções possíveis a serem utilizadas em relação ao funcionário NG
 */
package jsageImport.controler;

import java.util.List;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioNG;
import jsageImport.persistencia.GerenciadorPersistenciaNG;
import jsageImport.persistencia.IGerenciadorPersistenciaNG;

/**
 * @author Gustavo Dias
 * Criação: 07/06/2016
 * Última modificação: 12/10/2016
 */
public class ControlerFuncionarioNG {
    
    public ControlerFuncionarioNG(){
        
    }
    
    public boolean testarConexao (String server, String bd, String port, String user, String password) throws JsageImportException{
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionario = gerenteP.getPersistenciaFuncionario();
        boolean resultado = false;
        if (PFuncionario.TestaConexao(server, bd, port, user, password)){
            resultado = true;
        }
        return resultado;
    }
    
    public List pesquisarTodos () throws JsageImportException{
        List listaFun;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionario = gerenteP.getPersistenciaFuncionario();
        listaFun = PFuncionario.pesquisarTodos();
        
        return listaFun;
    }
    
    public List pesquisarEmpresas () throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PEmpresas = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresas.capturarInfoEmpresasComFun();
        
        return listaEmp;
    }
    
    public List pesquisarId (int id) throws JsageImportException{
        List listaEmp;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PEmpresa = gerenteP.getPersistenciaFuncionario();
        listaEmp = PEmpresa.pesquisaId(id);
        
        return listaEmp;
    }
    
    public List listarFuncionarios (int idpessoa) throws JsageImportException{
        List listaFun;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionarios = gerenteP.getPersistenciaFuncionario();
        listaFun = PFuncionarios.capturarInfoFuncionariosNG(idpessoa);
        
        return listaFun;
    }
    
    public List listarDependentes (int idpessoa) throws JsageImportException{
        List listaFun;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionarios = gerenteP.getPersistenciaFuncionario();
        listaFun = PFuncionarios.capturarInfoDependente(idpessoa);
        
        return listaFun;
    }
    
    public List listarPai (int idpessoa) throws JsageImportException{
        List listaFun;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionarios = gerenteP.getPersistenciaFuncionario();
        listaFun = PFuncionarios.capturarInfoPai(idpessoa);
        
        return listaFun;
    }
    
    public List listarMae (int idpessoa) throws JsageImportException{
        List listaFun;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionarios = gerenteP.getPersistenciaFuncionario();
        listaFun = PFuncionarios.capturarInfoMae(idpessoa);
        
        return listaFun;
    }
    
    public List listarDadosFuncionais (int idpessoa) throws JsageImportException{
        List listaFun;
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionarios = gerenteP.getPersistenciaFuncionario();
        listaFun = PFuncionarios.recuperarDadosFuncionais(idpessoa);
        
        return listaFun;
    }
    
    public String ImportarFuncionarios(int idEmpresa, int idPessoa, String cpf)throws JsageImportException{
        String funcionario = "";
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionario = gerenteP.getPersistenciaFuncionario();
        funcionario = PFuncionario.importaFuncionarios(idPessoa, idEmpresa, cpf);
        return funcionario;
    }
    
    public void ImportarEmpresa(String cnpj)throws JsageImportException{
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionario = gerenteP.getPersistenciaFuncionario();
        PFuncionario.ImportaEmpresas(cnpj);
    }
    
    public int sizeArrayImport ()throws JsageImportException{
        IGerenciadorPersistenciaNG gerenteP = new GerenciadorPersistenciaNG();
        IPersistenciaFuncionarioNG PFuncionario = gerenteP.getPersistenciaFuncionario();
        return PFuncionario.SizeImport();
    }
    
}
