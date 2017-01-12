/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.teste;

import jsageImport.modelo.persistencia.TratamentoDados;

/**
 *
 * @author Gustavo
 */
public class TesteFormatarCnpj {
    
    public static void main (String [] args){
        TratamentoDados trataDados = new TratamentoDados();
        System.out.println(trataDados.formatarCampo("##.###.###/####-##","01685053000156"));
       
    }
    
    
}
