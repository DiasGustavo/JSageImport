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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import jsageImport.controler.ControlerFuncionarioNG;
import jsageImport.controler.ControlerFuncionarioSAGE;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.EmpresaSAGE;
import jsageImport.modelo.dominio.PessoaFisica;

/**
 *
 * @author Gustavo
 */
public class FrImportacaoFuncionario extends javax.swing.JInternalFrame {

    private List funcionarios;
    private int idpj;
    private String nomeEmpresa;
    private String cnpj;
    

    
    /**
     * Creates new form PesquisarFrame
     * @param cnpj
     */
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public FrImportacaoFuncionario() {
        initComponents();
        pb.setVisible(false);
        
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
        this.funcionarios = control.listarFuncionarios(this.idpj);
        jlEmpresa.setText("Empresa: " + this.nomeEmpresa);
        
        DefaultTableModel model = (DefaultTableModel)tFuncionarios.getModel();
        this.removerLinhasDaTabela(model);
        
        Iterator resultado = funcionarios.iterator();
        
        while (resultado.hasNext()){
            PessoaFisica pf = (PessoaFisica) resultado.next();
            int id = pf.getIdPessoa();
            String nome = pf.getNomePessoa();
            String cpf = pf.getCpfFormatado();
            Timestamp dataNascimento = pf.getDataNascimento();
            
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
    
    public int pedirConfirmacao(String mensagem, String titulo, int tipo){
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, tipo);
        return resposta;
    }
    
    public PessoaFisica getFuncionario() throws JsageImportException {
        PessoaFisica pf = null;
        int linhaSelecionada = tFuncionarios.getSelectedRow();
        if (linhaSelecionada < 0) {
            throw new JsageImportException("Não foi selecionado nenhuma Empresa");
        }
        pf = (PessoaFisica) this.funcionarios.get(linhaSelecionada);
        
        return pf;
    }
    
    public void importarDados () throws JsageImportException{
            
            ControlerFuncionarioNG control = new ControlerFuncionarioNG();
            ControlerFuncionarioSAGE ctr = new ControlerFuncionarioSAGE();
            List empresas = ctr.pesquisarCNPJ(this.cnpj);
            //captura as inforamações da empresa para onde os funcionários serão inseridos
            EmpresaSAGE emp = (EmpresaSAGE)empresas.get(0);
            jlStatus.setText("Importando os Funcionários Aguarde ...");
            
            int reply = JOptionPane.showConfirmDialog(null, "Deseja realizar a importação dos Funcionários da Empresa de CNPJ: " + this.cnpj+"\n Para Empresa de CNPJ:" + emp.getCnpj_cpf()+" ?", "Aviso de importação", JOptionPane.YES_NO_OPTION);
            
            if(reply == JOptionPane.YES_OPTION){               
                int flag = 0;                
                for(int i= 0; i < this.funcionarios.size(); i++){
                    
                    PessoaFisica pfGravar =(PessoaFisica) this.funcionarios.get(i);                                    
                    
                    control.ImportarFuncionarios(pfGravar.getIdPessoa(), emp.getCd_empresa(),  pfGravar.getNomePessoa());
                    //System.out.println(pfGravar.getIdPessoa()+ "-- " + pfGravar.getCpfFormatado());              
                    if (flag == this.funcionarios.size()-1){
                        jlStatus.setText("Status: Concluído!");                       
                        JOptionPane.showMessageDialog(null, "Funcionarios Gravados com Sucesso!"); 
                        return;
                    }
                    flag++;                    
                }                              
            
            }else if (reply == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "A importação não foi realizada!"); 
            }   
        
    }
    
    public void progresso(int max) throws JsageImportException{
        
        final int numImages = max;
        SwingWorker worker = new SwingWorker() {
                            @Override
                            protected Object doInBackground() throws Exception {
                                pb.setMaximum(numImages);
                                for (int x = 0; x <= pb.getMaximum(); x++) {
                                    pb.setValue(x);
                                    Thread.sleep(900);
                                }
                                return null;
                            }
                            @Override
                            protected void done() {
                                //barra.setValue(0);  
                                System.out.println("Feito...");
                                JOptionPane.showMessageDialog(null, "Progresso concluído");
                            }
                        };
                        worker.execute();
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
        jlStatus = new javax.swing.JLabel();
        pb = new javax.swing.JProgressBar();

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

        jlStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlStatus.setText("Status:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlStatus)
                    .addComponent(jlEmpresa)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbImportar)
                        .addGap(105, 105, 105)
                        .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jbImportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addComponent(jlStatus)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImportarActionPerformed
        try {
            
            importarDados();
        } catch (JsageImportException ex) {
            Logger.getLogger(FrImportacaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbImportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbImportar;
    private javax.swing.JLabel jlEmpresa;
    private javax.swing.JLabel jlQRegistros;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JProgressBar pb;
    private javax.swing.JTable tFuncionarios;
    // End of variables declaration//GEN-END:variables
}