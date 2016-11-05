/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTesteSage;

import java.sql.Timestamp;

/**
 *
 * @author Jeff-Info
 */
public class Teste {
    
   public static void main (String args []){
       
        /*Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = new SimpleDateFormat("dd/MM/yyyy").format(timestamp.getTime());
	System.out.println(date);*/
        String data = "2006-02-21 08:35:20";
        Timestamp tempo = Timestamp.valueOf(data);
        System.out.println("Data gerada: "+ tempo);
       
       
      
   }
    
}
