/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.view;

import java.awt.Dimension;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import jsageImport.controler.ControlerFuncionarioNG;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.DadosFuncionaisNG;

/**
 *
 * @author Gustavo
 */
public class FrMostraDependentes extends javax.swing.JInternalFrame {

    private List funcionarios;
    private int idpj;
    private String nomeEmpresa;
    /**
     * Creates new form PesquisarFrame
     */
    public FrMostraDependentes() throws JsageImportException {
        initComponents();
        exibirFuncionarios();
    }
    
    public void setIdPj (int id){
        this.idpj = id;
    }
    
    public void setNomeEmpresa (String nome){
        this.nomeEmpresa = nome;
    }
    
    public void exibirFuncionarios () throws JsageImportException{
        ControlerFuncionarioNG control = new ControlerFuncionarioNG();
        // O metodo pesquisarTodos retorna um list
        this.funcionarios = control.listarDadosFuncionais(3413);
        //jlEmpresa.setText("Empresa: " + this.nomeEmpresa);
        
        DefaultTableModel model = (DefaultTableModel)tFuncionarios.getModel();
        this.removerLinhasDaTabela(model);
        
        Iterator resultado = funcionarios.iterator();
        
        while (resultado.hasNext()){
            DadosFuncionaisNG pf = (DadosFuncionaisNG) resultado.next();
            String id = pf.getCodigoRegistro();
            Timestamp nome = pf.getDataAdmissao();
            int cpf = pf.getIdTipoAdmissao();
            String dataNascimento = pf.getNumeroMatricula();
            
            Object[] linha = {id, nome, cpf, dataNascimento };
            model.addRow(linha);
        }
        int total = funcionarios.size();
        jlQRegistros.setText("Quantidade de Registros: "+ total);
    }
    
    public void removerLinhasDaTabela (DefaultTableModel model){
        while (model.getRowCount() > 0){
            int ultimaLinha = model.getRowCount() -1;
            model.removeRow(ultimaLinha);
        }
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation(15 + (d.width - this.getSize().width) / 5, 15 + (d.height -
        this.getSize().height) / 5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tFuncionarios = new javax.swing.JTable();
        jlQRegistros = new javax.swing.JLabel();
        jbImportar = new javax.swing.JButton();
        jlEmpresa = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Funcionários");

        tFuncionarios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "Data de Nascimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tFuncionarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tFuncionarios);

        jlQRegistros.setText("Quantidade de Registros:");

        jbImportar.setText("Importar");
        jbImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImportarActionPerformed(evt);
            }
        });

        jlEmpresa.setText("Empresa:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlEmpresa)
                    .addComponent(jbImportar)
                    .addComponent(jlQRegistros)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jlEmpresa)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlQRegistros)
                .addGap(40, 40, 40)
                .addComponent(jbImportar)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbImportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbImportar;
    private javax.swing.JLabel jlEmpresa;
    private javax.swing.JLabel jlQRegistros;
    private javax.swing.JTable tFuncionarios;
    // End of variables declaration//GEN-END:variables
}
