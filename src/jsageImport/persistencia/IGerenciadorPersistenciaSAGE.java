/*
 * Interface para o uso da persistencia dos dados do SAGE
 */
package jsageImport.persistencia;

import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaSAGE;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioSAGE;

/**
 *
 * @author Gustavo
 */
public interface IGerenciadorPersistenciaSAGE {
    public IPersistenciaFuncionarioSAGE getPersistenciaFuncionario();
    
    public IPersistenciaEmpresaSAGE getPersistenciaEmpresa ();
}
