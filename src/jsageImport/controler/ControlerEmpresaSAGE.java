/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.controler;

import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.CargoFun;
import jsageImport.modelo.dominio.CentroCusto;
import jsageImport.modelo.dominio.ContaBancaria;
import jsageImport.modelo.dominio.ContadorPFisica;
import jsageImport.modelo.dominio.ContadorPJuridica;
import jsageImport.modelo.dominio.EmpresaFolha;
import jsageImport.modelo.dominio.EmpresaTributacao;
import jsageImport.modelo.dominio.PessoaJuridica;
import jsageImport.modelo.dominio.ResponsavelPJuridica;
import jsageImport.modelo.dominio.Sindicato;
import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaSAGE;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioSAGE;
import jsageImport.persistencia.GerenciadorPersistenciaSAGE;
import jsageImport.persistencia.IGerenciadorPersistenciaSAGE;

/**
 *
 * @author Jeff-Info
 */
public class ControlerEmpresaSAGE {
    
        public void gravarEmpresa (PessoaJuridica pj) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEmpresa(pj);     
        
    }
    
    public void gravarEstabelecimento (int idResponsavelCaged, int idResponsavelSefip,PessoaJuridica pj, EmpresaTributacao empTrib, EmpresaTributacao empCnae, EmpresaFolha empFolha) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEstabelecimento(idResponsavelCaged, idResponsavelSefip, pj, empTrib, empCnae, empFolha);
    }  
    
    public void gravarEmpresaMatrizContabilizacao (int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEmpresaMatrizContabilizacao(cd_empresa);
    
    }
    public void gravarEstabelecimentoParametro(PessoaJuridica pj) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEstabelecimentoParametro(pj);
    }
    
    public void gravarTomador (int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarTomador(cd_empresa);
    }
    
    public void gravarCSCDFCEquivalenteCaixa(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDFCEquivalenteCaixa(cd_empresa);
    }
    
    public void gravarCRDPermissaoGrupoEstabelecimento(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCRDPermissaoGrupoEstabelecimento(cd_empresa);
    }
    
    public void gravarCSCDFCEPlano(int cd_empresa)throws JsageImportException{
       IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDFCEPlano(cd_empresa);
    }
    
     public void gravarCSCDRAPlano(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDRAPlano(cd_empresa);
    }
    
    public void gravarCRDSCPRODEC(int cd_empresa)throws JsageImportException{
       IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCRDSCPRODEC(cd_empresa);
    }
    
    public void gravarTituloDRE(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarTituloDRE(cd_empresa);
    }
    
    public void gravarTipoDRE(int cd_empresa)throws JsageImportException{
       IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarTipoDRE(cd_empresa);
    }
    
    public void gravarCapaLote(int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCapaLote(cd_empresa);
    }
    
    public void gravarCSCDMPLPLANO (int cd_empresa) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCSCDMPLPLANO(cd_empresa);
    }
    
    public void gravarEmpresaParametro (PessoaJuridica pj) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEmpresaParametro(pj);
    }
    public void gravarBanco (int id, ContaBancaria conta, int cd_empresa)throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarBanco(id, conta, cd_empresa);
    }
    public void gravarBancoGeral (int cd_empresa) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarBancoGeral(cd_empresa);
    }
    
    public void gravarCargo (CargoFun cargo, int cd_empresa ) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCargo(cargo, cd_empresa);        
    }
    public void gravarCentroCusto (int id, CentroCusto centroCusto, int cd_empresa) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarCentroCusto(id, centroCusto, cd_empresa);     
    }  
    public void gravarSindicato (Sindicato sindicato) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarSindicato(sindicato); 
    }
    
    public void gravarEstrutura (int cdEmpresa) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarEstrutura(cdEmpresa);
    }
    
    public void gravarContador (ContadorPFisica contador) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarContador(contador);
    }
    
    public void gravarContadorPJuridica (ContadorPJuridica contador) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarContadorPJuridica(contador);
    }
    
    public boolean pesquisarResponsavel (String nome) throws JsageImportException{
        boolean tag = false;
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        tag = PEmpresa.pesquisarResponsavel(nome);
        
        return tag;
    }
    
    public void gravarResponsavelPJuridica (ResponsavelPJuridica responsavel) throws JsageImportException{
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarResponsavelPJuridica(responsavel);
    }
    
    public void gravarParametroSindicato (int idSindicato) throws JsageImportException {
        IGerenciadorPersistenciaSAGE gerenteP = new GerenciadorPersistenciaSAGE();
        IPersistenciaEmpresaSAGE PEmpresa = gerenteP.getPersistenciaEmpresa();
        PEmpresa.gravarParametroSindicato(idSindicato);
    }
   
}
