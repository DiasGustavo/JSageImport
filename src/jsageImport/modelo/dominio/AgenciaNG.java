/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.dominio;

/**
 *
 * @author Jeff-Info
 */
public class AgenciaNG extends BancoNG{
    
    int iddadosbanco;
    String codigoagencia;
    String numdvagencia;
    String descricao;
    String codcompensacao;
    boolean indativo;

    public int getIddadosbanco() {
        return iddadosbanco;
    }

    public void setIddadosbanco(int iddadosbanco) {
        this.iddadosbanco = iddadosbanco;
    }

    public String getCodigoagencia() {
        return codigoagencia;
    }

    public void setCodigoagencia(String codigoagencia) {
        this.codigoagencia = codigoagencia;
    }

    public String getNumdvagencia() {
        return numdvagencia;
    }

    public void setNumdvagencia(String numdvagencia) {
        this.numdvagencia = numdvagencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodcompensacao() {
        return codcompensacao;
    }

    public void setCodcompensacao(String codcompensacao) {
        this.codcompensacao = codcompensacao;
    }

    public boolean isIndativo() {
        return indativo;
    }

    public void setIndativo(boolean indativo) {
        this.indativo = indativo;
    }
    
}
