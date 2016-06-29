/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.persistencia;

import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioNG;
import jsageImport.modelo.persistencia.PersistenciaFuncionarioNG;

/**
 *
 * @author Gustavo
 */
public class GerenciadorPersistenciaNG implements IGerenciadorPersistenciaNG {

    @Override
    public IPersistenciaFuncionarioNG getPersistenciaFuncionario() {
        return new PersistenciaFuncionarioNG();
    }
    
}
