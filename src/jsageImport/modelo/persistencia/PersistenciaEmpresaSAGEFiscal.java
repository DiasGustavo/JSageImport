/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaFiscalNG;

/**
 *
 * @author Gustavo
 */
public class PersistenciaEmpresaSAGEFiscal {
    PropertiesJdbc jdbc = new PropertiesJdbc();
    TratamentoDados trataDados = new TratamentoDados();
    
     /*Strings de url*/
    private final String urlNG = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGFOLHA = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_folha;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    private final String urlNGDOMINIO = "jdbc:sqlserver://"+jdbc.lerServidor("NG")+":"+jdbc.lerPorta("NG")+";databaseName=ng_dominio;user="+jdbc.lerUsuario("NG")+";password="+jdbc.lerSenha("NG")+";"; 
    
    /*Strings SQL*/
    
    private static final String SQL_UPDATE_CRDEmpresa = "UPDATE CRDEmpresa SET mascara_plano_contas = ?,num_niveis_plano_contas = ?,num_digitos_plano_contas = ?,comprimento_plano_contas = ?" +
                                                                              ",seq_conta = ?,simples_crh = ?,microempresa_crh = ?,cd_recolhimento = ?,cd_pagamento_gps= ?,op_calculo_numero_dias = ?" +
                                                                              ",EntSemFinsLucrativos = ?,calc30_opcao_salario = ?,calc30_opcao_dias = ?,imprimir_dados_destaque = ?,recibo_ferias_rateio = ?" +
                                                                              ",recibo_ferias_adiantamento = ?,recibo_ferias_abono = ? WHERE cd_empresa = ?";
    
    public void atualizarEmpresa (EmpresaFiscalNG empNG) throws JsageImportException{
        if (empNG == null) {
            String mensagem = "Não foi informada a Empresa para importar";
            throw new JsageImportException(mensagem);
        }
        jdbc.lerPropriedades("SAGE");
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = GerenciadorConexao.getConnection(jdbc.lerPropriedades("SAGE"));
            stmt = con.prepareStatement(SQL_UPDATE_CRDEmpresa);
            stmt.setString(1, "X-X-XX-XX-XXXX");
            stmt.setInt(2, 5);
            stmt.setString(3, "1");
            stmt.setString(4, "1.1.01.01.0001");
            stmt.setInt(5, 0);
            stmt.setString(6, "S");
            stmt.setString(7, "S");
            stmt.setInt(8, (short)115);
            stmt.setInt(9, (short)2003);
            stmt.setInt(10, (short) 2);
            stmt.setInt(11, (short) 2);
            stmt.setInt(12, (short) 2);
            stmt.setString(13, "N");
            stmt.setString(14, "N");
            stmt.setString(15, "N");
            
            stmt.executeUpdate();
        }catch (SQLException exc){
            StringBuffer msg = new StringBuffer("Não foi possível atualizar o cadastro da empresa.");
            msg.append("\nMotivo: " + exc.getMessage());
            throw new JsageImportException(msg.toString());            
        } finally {
            GerenciadorConexao.closeConexao(con, stmt);
        }
    }
    
    
}
