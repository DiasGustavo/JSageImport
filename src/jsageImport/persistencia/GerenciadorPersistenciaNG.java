/*
 * Gerenciador da persistencia dos dados do NG
 */
package jsageImport.persistencia;

import jsageImport.modelo.ipersistencia.IPersistenciaEmpresaNG;
import jsageImport.modelo.ipersistencia.IPersistenciaFuncionarioNG;
import jsageImport.modelo.persistencia.PersistenciaEmpresaNG;
import jsageImport.modelo.persistencia.PersistenciaFuncionarioNG;

/**
 * @author Gustavo Dias
 * Criação: 07/06/2016
 * Última modificação: 12/10/2016
 */
public class GerenciadorPersistenciaNG implements IGerenciadorPersistenciaNG {

    @Override
    public IPersistenciaFuncionarioNG getPersistenciaFuncionario() {
        return new PersistenciaFuncionarioNG();
    }

    @Override
    public IPersistenciaEmpresaNG getPersistenciaEmpresa() {
        return new PersistenciaEmpresaNG();
    }
    
}
