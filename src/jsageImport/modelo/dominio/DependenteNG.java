/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.dominio;

import java.sql.Timestamp;

/**
 *
 * @author Gustavo
 */
public class DependenteNG extends PessoaFisica {
    
    boolean indDependenteIrrf;
    boolean indDependenteSalarioFamilia;
    boolean indDependentePensaoAlimenticia;
    boolean indIncapacitadoTrabalho;
    boolean indAtivo;
    int idowner;
    boolean indDependentePlanoSaude;
    int idRelacaoDependenciaPlanoSaude;
    int iddadosdependente;
    int idtipodependenciairrf;
    int idtiposituacaosalario;
    Timestamp datafim;
    boolean indsuspenso;

    public int getIddadosdependente() {
        return iddadosdependente;
    }

    public void setIddadosdependente(int iddadosdependente) {
        this.iddadosdependente = iddadosdependente;
    }

    public int getIdtipodependenciairrf() {
        return idtipodependenciairrf;
    }

    public void setIdtipodependenciairrf(int idtipodependenciairrf) {
        this.idtipodependenciairrf = idtipodependenciairrf;
    }

    public int getIdtiposituacaosalario() {
        return idtiposituacaosalario;
    }

    public void setIdtiposituacaosalario(int idtiposituacaosalario) {
        this.idtiposituacaosalario = idtiposituacaosalario;
    }

    public Timestamp getDatafim() {
        return datafim;
    }

    public void setDatafim(Timestamp datafim) {
        this.datafim = datafim;
    }

    public boolean isIndsuspenso() {
        return indsuspenso;
    }

    public void setIndsuspenso(boolean indsuspenso) {
        this.indsuspenso = indsuspenso;
    }
           
    

    public boolean isIndDependenteIrrf() {
        return indDependenteIrrf;
    }

    public void setIndDependenteIrrf(boolean indDependenteIrrf) {
        this.indDependenteIrrf = indDependenteIrrf;
    }

    public boolean isIndDependenteSalarioFamilia() {
        return indDependenteSalarioFamilia;
    }

    public void setIndDependenteSalarioFamilia(boolean indDependenteSalarioFamilia) {
        this.indDependenteSalarioFamilia = indDependenteSalarioFamilia;
    }

    public boolean isIndDependentePensaoAlimenticia() {
        return indDependentePensaoAlimenticia;
    }

    public void setIndDependentePensaoAlimenticia(boolean indDependentePensaoAlimenticia) {
        this.indDependentePensaoAlimenticia = indDependentePensaoAlimenticia;
    }

    public boolean isIndIncapacitadoTrabalho() {
        return indIncapacitadoTrabalho;
    }

    public void setIndIncapacitadoTrabalho(boolean indIncapacitadoTrabalho) {
        this.indIncapacitadoTrabalho = indIncapacitadoTrabalho;
    }

    public boolean isIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(boolean indAtivo) {
        this.indAtivo = indAtivo;
    }

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

    public boolean isIndDependentePlanoSaude() {
        return indDependentePlanoSaude;
    }

    public void setIndDependentePlanoSaude(boolean indDependentePlanoSaude) {
        this.indDependentePlanoSaude = indDependentePlanoSaude;
    }

    public int getIdRelacaoDependenciaPlanoSaude() {
        return idRelacaoDependenciaPlanoSaude;
    }

    public void setIdRelacaoDependenciaPlanoSaude(int idRelacaoDependenciaPlanoSaude) {
        this.idRelacaoDependenciaPlanoSaude = idRelacaoDependenciaPlanoSaude;
    }
    
    
}
