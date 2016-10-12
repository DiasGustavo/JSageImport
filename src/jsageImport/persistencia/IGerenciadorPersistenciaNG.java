/*
 * Interface para o uso da persistencia
 */
package jsageImport.persistencia;

import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaNG;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioNG;

/**
 * @author Gustavo Dias
 * Criação: 07/06/2016
 * Última modificação: 12/10/2016
 */
public interface IGerenciadorPersistenciaNG {
    
    public IPersistenciaFuncionarioNG getPersistenciaFuncionario();
    
    public IPersistenciaEmpresaNG getPersistenciaEmpresa();
    
}
