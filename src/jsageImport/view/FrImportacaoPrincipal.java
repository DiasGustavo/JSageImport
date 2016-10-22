/*
 * Form responsavel pela listagem das empresas com folha
 */
package jsageImport.view;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jsageImport.controler.ControlerEmpresaNG;
import jsageImport.exception.JsageImportException;
import jsageImport.modelo.dominio.PessoaJuridica;

/**
 * @author Gustavo Dias
 * Criação: 21/10/2016
 * Última modificação: 22/10/2016
 */
public class FrImportacaoPrincipal extends javax.swing.JInternalFrame {

    private List empresas;
    private String nomeEmpresa;
    private String cnpj;
    /**
     * Creates new form PesquisarFrame
     */
    public FrImportacaoPrincipal() {
        initComponents();
        
    }
    
    public void exibirEmpresas () throws JsageImportException{
        ControlerEmpresaNG control = new ControlerEmpresaNG();
        // O metodo pesquisarTodos retorna um list
        this.empresas = control.pesquisarEmpresas();
        
        DefaultTableModel model = (DefaultTableModel)tFuncionarios.getModel();
        this.removerLinhasDaTabela(model);
        
        Iterator resultado = empresas.iterator();
        
        while (resultado.hasNext()){
            PessoaJuridica pj = (PessoaJuridica) resultado.next();
            int id = pj.getIdPessoa();
            String nomeFantasia = pj.getNomeFantasia();
            String cnpj = pj.getCnpjFormatado();
            String nomeAbreviado = pj.getNomeAbreviado();
            
            Object[] linha = {id, nomeFantasia, cnpj, nomeAbreviado };
            model.addRow(linha);
        }
        int total = empresas.size();
        jlQRegistros.setText("Quantidade de Registros: "+ total);
    }
    
    public void removerLinhasDaTabela (DefaultTableModel model){
        while (model.getRowCount() > 0){
            int ultimaLinha = model.getRowCount() -1;
            model.removeRow(ultimaLinha);
        }
    }
    
    public PessoaJuridica getEmpresa() throws JsageImportException {
        PessoaJuridica pj = null;
        int linhaSelecionada = tFuncionarios.getSelectedRow();
        if (linhaSelecionada < 0) {
            throw new JsageImportException("Não foi selecionado nenhuma Empresa");
        }
        pj = (PessoaJuridica) this.empresas.get(linhaSelecionada);
        this.nomeEmpresa = pj.getNomeAbreviado();
        this.cnpj = pj.getCnpjFormatado();
        return pj;
    }
    
    public int pedirConfirmacao(String mensagem, String titulo, int tipo){
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, tipo);
        return resposta;
    }
    
    public void exibirMensagem(String mensagem, String titulo, boolean isErro) {
        int tipo;
        if (isErro) {
            tipo = JOptionPane.ERROR_MESSAGE;
        } else {
            tipo = JOptionPane.INFORMATION_MESSAGE;
        }
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }
    public void importarDados () throws JsageImportException{
        ControlerEmpresaNG control = new ControlerEmpresaNG();
        control.ImportarEmpresa(this.cnpj);
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
        jbPesquisar = new javax.swing.JButton();
        jlQRegistros = new javax.swing.JLabel();
        jbImportar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Consulta Empresas no Banco NG");

        tFuncionarios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome Fantasia", "CNPJ", "Nome Abreviado"
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

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jlQRegistros.setText("Quantidade de Registros:");

        jbImportar.setText("Listar Funcionários");
        jbImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlQRegistros)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbPesquisar)
                        .addGap(34, 34, 34)
                        .addComponent(jbImportar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlQRegistros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPesquisar)
                    .addComponent(jbImportar))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        try {
            exibirEmpresas();
        } catch (JsageImportException ex) {
            StringBuffer mensagem = new StringBuffer("Não foi possível realizar a consulta.");
            mensagem.append("\nMotivo: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, mensagem);
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jbImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImportarActionPerformed
        PessoaJuridica pj = null;
        FrImportacaoFuncionarioProgresso pjFun = new FrImportacaoFuncionarioProgresso();
        try {
            pj = this.getEmpresa();
            pjFun.setIdPj(pj.getIdPessoa());
            pjFun.setNomeEmpresa(this.nomeEmpresa);
            pjFun.setCnpj(this.cnpj);
            this.importarDados();
            
            pjFun.exibirFuncionarios();
            this.getParent().add(pjFun);
            pjFun.setVisible(true);
            
        }catch (JsageImportException ex){
            //this.exibirMensagem(ex.getMessage(), "Mensagem de Erro", true);
        } 
    }//GEN-LAST:event_jbImportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbImportar;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JLabel jlQRegistros;
    private javax.swing.JTable tFuncionarios;
    // End of variables declaration//GEN-END:variables
}
