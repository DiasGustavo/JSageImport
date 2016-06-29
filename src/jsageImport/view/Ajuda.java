/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.view;

import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author Gustavo
 */
public class Ajuda {
    
    public void abrirManual(){
        try {
 
                File pdfFile = new File(".\\Manual.pdf");
                if (pdfFile.exists()) {
 
                        if (Desktop.isDesktopSupported()) {
                                Desktop.getDesktop().open(pdfFile);
                        } else {
                                System.out.println("Awt Desktop não disponível");
                        }
 
                } else {
                        System.out.println("Ficheiro inexistente");
                }
 
                System.out.println("Done");
 
          } catch (Exception ex) {
                ex.printStackTrace();
          }
    } 
    
}
