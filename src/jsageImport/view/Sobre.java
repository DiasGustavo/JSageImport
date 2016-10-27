/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.view;

import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class Sobre extends JOptionPane{
    
    String mensagem = "Sistema SAGEImport \n versão 1.2   \n Copyright © GoldenSoft. Todos os direitos Reservados. \n Contato: gustavouepb@gmail.com";
    String titulo = "Sobre";
    
    public Sobre(){
        JOptionPane.showMessageDialog(null,mensagem, titulo , JOptionPane.INFORMATION_MESSAGE);
    }
}
