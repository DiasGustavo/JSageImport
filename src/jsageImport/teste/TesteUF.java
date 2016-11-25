/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.teste;

import jsageImport.exception.JsageImportException;
import jsageImport.modelo.persistencia.TratamentoDados;

/**
 *
 * @author Gustavo
 */
public class TesteUF {
       
    public static void main(String args []) throws JsageImportException{
        TratamentoDados trataDados = new TratamentoDados();
        String orgaoRG = "SSP/PB";
        String orgaoRG_1 = "SSPPB";
        String orgaoRG_2 = "SSP PB";
        String orgaoRG_3 = "SSPPB'";
        System.out.println("Rg com /: " + trataDados.tratarUFRG(orgaoRG));
        System.out.println("Rg sem /: " + trataDados.tratarUFRG(orgaoRG_1));
        System.out.println("Rg com espaço : " + trataDados.tratarUFRG(orgaoRG_2));
        System.out.println("Rg com ': " + trataDados.tratarUFRG(orgaoRG_3));
    }
    
}
