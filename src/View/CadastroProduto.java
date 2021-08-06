/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.FormProdutoController;
import Controller.FormVendedorController;
import DAO.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author gdeba
 */
public class CadastroProduto extends javax.swing.JFrame {
   
    
    MaskFormatter mfpunit;
    private final FormProdutoController controller;
    Connection Conexao = new conexao().getConnection();
    PreparedStatement statement = null;
    ResultSet rs = null;
    
    
    public CadastroProduto() throws SQLException{
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jprodutos.getModel();
        jprodutos.setRowSorter(new TableRowSorter(modelo));
        readJtable();
        controller = new FormProdutoController(this);
    }
    public void readJtable() throws SQLException{
        try {
            DefaultTableModel modelo = (DefaultTableModel) jprodutos.getModel();
            modelo.setNumRows(0);
            String sql = "select * from produto";
            statement = Conexao.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                String item = rs.getString("item");
                String desc = rs.getString("descricao");
                String un = rs.getString("unidade");
                String preco = rs.getString("preco_unit");
                
                Object[] obj = {item,desc,un,preco};
                modelo.addRow(obj);
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "judcjdsb");
        }
 
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jprodutos = new javax.swing.JTable();
        jbtninsere = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jbtnexcluitudo = new javax.swing.JButton();
        jbtnexclui1 = new javax.swing.JButton();
        jUnidade = new javax.swing.JComboBox<>();
        jTextFieldPreco = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextDesc = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        jTextField2.setMinimumSize(new java.awt.Dimension(6, 65));
        jTextField2.setPreferredSize(new java.awt.Dimension(6, 21));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Descrição");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel2.setText("Unidade");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        jLabel3.setText("Preço Unitario");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));
        getContentPane().add(jtxtItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 130, 31));

        jprodutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item", "Descrição", "Unidade", "Preco_Unit"
            }
        ));
        jScrollPane1.setViewportView(jprodutos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 660, 321));

        jbtninsere.setBackground(new java.awt.Color(102, 255, 102));
        jbtninsere.setText("Inserir Produto");
        jbtninsere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtninsereActionPerformed(evt);
            }
        });
        getContentPane().add(jbtninsere, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 107, -1));

        jLabel5.setText("Item");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 110, -1));

        jbtnexcluitudo.setBackground(new java.awt.Color(255, 0, 0));
        jbtnexcluitudo.setText("Excluir tudo");
        jbtnexcluitudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnexcluitudoActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnexcluitudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 107, -1));

        jbtnexclui1.setBackground(new java.awt.Color(255, 51, 51));
        jbtnexclui1.setText("Excluir Produto");
        jbtnexclui1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnexclui1ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnexclui1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a unidade", "KG", "G", " " }));
        getContentPane().add(jUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 140, 30));
        getContentPane().add(jTextFieldPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 130, 30));

        jTextDesc.setColumns(20);
        jTextDesc.setRows(5);
        jScrollPane2.setViewportView(jTextDesc);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 320, 70));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 110, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtninsereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtninsereActionPerformed
        try {
            controller.salvaProduto();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        jtxtItem.setText("");
        jTextDesc.setText("");
        jTextFieldPreco.setText("");
        try {
            readJtable();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtninsereActionPerformed

    private void jbtnexcluitudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnexcluitudoActionPerformed
        controller.excluitudo();
        jtxtItem.setText("");
        jTextDesc.setText("");
        jTextFieldPreco.setText("");
        try {
            readJtable();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnexcluitudoActionPerformed

    private void jbtnexclui1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnexclui1ActionPerformed
        controller.excluiUM();
        jtxtItem.setText("");
        jTextDesc.setText("");
        jTextFieldPreco.setText("");
        try {
            readJtable();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnexclui1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroProduto().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    

    public JComboBox<String> getjUnidade() {
        return jUnidade;
    }

    public void setjUnidade(JComboBox<String> jUnidade) {
        this.jUnidade = jUnidade;
    }

    

    public JTextField getJtxtItem() {
        return jtxtItem;
    }

    public void setJtxtItem(JTextField jtxtItem) {
        this.jtxtItem = jtxtItem;
    }

    public JTextField getjTextFieldPreco() {
        return jTextFieldPreco;
    }

    public void setjTextFieldPreco(JTextField jTextFieldPreco) {
        this.jTextFieldPreco = jTextFieldPreco;
    }

    public JTextArea getjTextDesc() {
        return jTextDesc;
    }

    public void setjTextDesc(JTextArea jTextDesc) {
        this.jTextDesc = jTextDesc;
    }
    
    
    
    

    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextDesc;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldPreco;
    private javax.swing.JComboBox<String> jUnidade;
    private javax.swing.JButton jbtnexclui1;
    private javax.swing.JButton jbtnexcluitudo;
    private javax.swing.JButton jbtninsere;
    private javax.swing.JTable jprodutos;
    private javax.swing.JTextField jtxtItem;
    // End of variables declaration//GEN-END:variables
}