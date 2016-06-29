/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.exception;

import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class JsageImportException extends Exception {

     public JsageImportException (){
         super("Causa de erro desconhecido!");
     }
     
     public JsageImportException (String message){
         super(message);
         JOptionPane.showMessageDialog(null,message, "Alerta", JOptionPane.ERROR_MESSAGE);
     }
}
