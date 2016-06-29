/*
 * POJO de um funcionário
 */
package jsageImport.modelo.dominio;

import java.sql.Timestamp;

/**
 * @author Gustavo Dias
 * Criação: 02/06/2016
 * Última modificação: 06/06/2016
 */
public class FuncionarioSAGE {
   
    String nome;
    String endreco;
    int nr_endereco;
    String compl_endereco;
    String bairro;
    String cidade;
    String estado;
    int cep;
    String pai;
    String mae;
    String sexo;
    short estado_civil;
    short nacionalidade;
    short ano_chegada;
    short grau_instrucao;
    Timestamp dt_nascimento;
    short ddd_fone;
    int telefone;
    String apelido;
    String chave_acesso;
    String senha_acesso;
    String raca;
    String deficiente;
    String cidade_nascimento;
    String estado_nascimento;
    short ddd_celular;
    int celular;
    String nomecompleto;
    String email;
    Timestamp data_chegada;
    String tipo_logradouro;
    int cd_municipio;
    int cd_municipio_nascimento;
    String funcionario_aposentado;
    Timestamp data_hora_alteracao;
    String id;
    String id_endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndreco() {
        return endreco;
    }

    public void setEndreco(String endreco) {
        this.endreco = endreco;
    }

    public int getNr_endereco() {
        return nr_endereco;
    }

    public void setNr_endereco(int nr_endereco) {
        this.nr_endereco = nr_endereco;
    }

    public String getCompl_endereco() {
        return compl_endereco;
    }

    public void setCompl_endereco(String compl_endereco) {
        this.compl_endereco = compl_endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public short getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(short estado_civil) {
        this.estado_civil = estado_civil;
    }

    public short getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(short nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public short getAno_chegada() {
        return ano_chegada;
    }

    public void setAno_chegada(short ano_chegada) {
        this.ano_chegada = ano_chegada;
    }

    public short getGrau_instrucao() {
        return grau_instrucao;
    }

    public void setGrau_instrucao(short grau_instrucao) {
        this.grau_instrucao = grau_instrucao;
    }

    public Timestamp getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(Timestamp dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public short getDdd_fone() {
        return ddd_fone;
    }

    public void setDdd_fone(short ddd_fone) {
        this.ddd_fone = ddd_fone;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getChave_acesso() {
        return chave_acesso;
    }

    public void setChave_acesso(String chave_acesso) {
        this.chave_acesso = chave_acesso;
    }

    public String getSenha_acesso() {
        return senha_acesso;
    }

    public void setSenha_acesso(String senha_acesso) {
        this.senha_acesso = senha_acesso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDeficiente() {
        return deficiente;
    }

    public void setDeficiente(String deficiente) {
        this.deficiente = deficiente;
    }

    public String getCidade_nascimento() {
        return cidade_nascimento;
    }

    public void setCidade_nascimento(String cidade_nascimento) {
        this.cidade_nascimento = cidade_nascimento;
    }

    public String getEstado_nascimento() {
        return estado_nascimento;
    }

    public void setEstado_nascimento(String estado_nascimento) {
        this.estado_nascimento = estado_nascimento;
    }

    public short getDdd_celular() {
        return ddd_celular;
    }

    public void setDdd_celular(short ddd_celular) {
        this.ddd_celular = ddd_celular;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getData_chegada() {
        return data_chegada;
    }

    public void setData_chegada(Timestamp data_chegada) {
        this.data_chegada = data_chegada;
    }

    public String getTipo_logradouro() {
        return tipo_logradouro;
    }

    public void setTipo_logradouro(String tipo_logradouro) {
        this.tipo_logradouro = tipo_logradouro;
    }

    public int getCd_municipio() {
        return cd_municipio;
    }

    public void setCd_municipio(int cd_municipio) {
        this.cd_municipio = cd_municipio;
    }

    public int getCd_municipio_nascimento() {
        return cd_municipio_nascimento;
    }

    public void setCd_municipio_nascimento(int cd_municipio_nascimento) {
        this.cd_municipio_nascimento = cd_municipio_nascimento;
    }

    public String getFuncionario_aposentado() {
        return funcionario_aposentado;
    }

    public void setFuncionario_aposentado(String funcionario_aposentado) {
        this.funcionario_aposentado = funcionario_aposentado;
    }

    public Timestamp getData_hora_alteracao() {
        return data_hora_alteracao;
    }

    public void setData_hora_alteracao(Timestamp data_hora_alteracao) {
        this.data_hora_alteracao = data_hora_alteracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(String id_endereco) {
        this.id_endereco = id_endereco;
    }
    
    
    
                    
}
