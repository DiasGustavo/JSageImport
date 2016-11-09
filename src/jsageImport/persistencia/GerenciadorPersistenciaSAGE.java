/*
 * Implementa a interface de gerenciamento da persistencia do SAGE 
 */
package jsageImport.persistencia;

import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaSAGE;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioSAGE;
import jsageImport.modelo.persistencia.PersistenciaEmpresaSAGE;
import jsageImport.modelo.persistencia.PersistenciaFuncionarioSAGE;

/**
 * @author Gustavo Dias
 * Criação: 12/10/2016
 * Última modificação: 12/10/2016
 */
 public class GerenciadorPersistenciaSAGE implements IGerenciadorPersistenciaSAGE {
    
    @Override
    public IPersistenciaFuncionarioSAGE getPersistenciaFuncionario() {
        return new PersistenciaFuncionarioSAGE();
    }

    @Override
    public IPersistenciaEmpresaSAGE getPersistenciaEmpresa() {
        return new PersistenciaEmpresaSAGE();
    }
    
}
