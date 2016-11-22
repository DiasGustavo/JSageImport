/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.teste;

import java.sql.Timestamp;

/**
 *
 * @author Gustavo
 */
public class TesteDATA {
    
    public static void main (String args []){
        
        Timestamp dataAnt = Timestamp.valueOf("2100-12-31 08:00:00");
        Timestamp dataProx = Timestamp.valueOf("2200-12-31 08:00:00");
        
        if(dataAnt.before(dataProx)){
            System.out.println("data proxima é mais recente");
        }else{
            System.out.println("data proxima é mais antiga");
        }
        
    }
    
}
