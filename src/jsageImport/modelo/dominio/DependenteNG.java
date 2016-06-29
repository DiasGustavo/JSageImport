/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.dominio;

/**
 *
 * @author Gustavo
 */
public class DependenteNG extends PessoaNG {
    
    boolean indDependenteIrrf;
    boolean indDependenteSalarioFamilia;
    boolean indDependentePensaoAlimenticia;
    boolean indIncapacitadoTrabalho;
    boolean indAtivo;
    int idowner;
    boolean indDependentePlanoSaude;
    int idRelacaoDependenciaPlanoSaude;

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
