/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Vendedor;
import Controller.FormVendedorController;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import DAO.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author gdeba
 */
public class CadastroVendedor extends javax.swing.JFrame {
    private final FormVendedorController controller;
    
    MaskFormatter mfrg;
    MaskFormatter mfcpf;
    Connection Conexao = new conexao().getConnection();
    PreparedStatement statement = null;
    ResultSet rs = null;
    
    public CadastroVendedor() throws SQLException {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jVendedores.getModel();
        jVendedores.setRowSorter(new TableRowSorter(modelo));
        readJtable();
        controller = new FormVendedorController(this);
        
    }

    public void readJtable() throws SQLException{
        try {
            DefaultTableModel modelo = (DefaultTableModel) jVendedores.getModel();
            modelo.setNumRows(0);
            String sql = "select * from vendedor";
            statement = Conexao.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String nome = rs.getString("nome");
                String crg = rs.getString("cargo");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                
                Object[] obj = {id,nome,crg,cpf,rg};
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
        jLabel4 = new javax.swing.JLabel();
        jtxtCPF = new javax.swing.JTextField();
        jtxtRG = new javax.swing.JTextField();
        ;
        jtxtCargo = new javax.swing.JTextField();
        jtxtID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jVendedores = new javax.swing.JTable();
        jbtninsere = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jtxtNome = new javax.swing.JTextField();
        jtxtNome2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jbtnexcluitudo = new javax.swing.JButton();
        jbtnexclui1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTextField2.setMinimumSize(new java.awt.Dimension(6, 65));
        jTextField2.setPreferredSize(new java.awt.Dimension(6, 21));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel2.setText("Cargo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel3.setText("RG");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, -1, -1));

        jLabel4.setText("CPF");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));

        jtxtCPF.setMinimumSize(new java.awt.Dimension(6, 30));
        jtxtCPF.setPreferredSize(new java.awt.Dimension(6, 21));
        getContentPane().add(jtxtCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 130, 31));
        getContentPane().add(jtxtRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 130, 31));
        getContentPane().add(jtxtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 130, 31));
        getContentPane().add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 130, 31));

        jVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "CARGO", "RG", "CPF"
            }
        ));
        jScrollPane1.setViewportView(jVendedores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 594, 321));

        jbtninsere.setBackground(new java.awt.Color(102, 255, 102));
        jbtninsere.setText("Inserir");
        jbtninsere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtninsereActionPerformed(evt);
            }
        });
        getContentPane().add(jbtninsere, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 107, -1));

        jLabel5.setText("ID");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        getContentPane().add(jtxtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 130, 31));
        getContentPane().add(jtxtNome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 130, 30));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 110, -1));

        jbtnexcluitudo.setBackground(new java.awt.Color(255, 0, 0));
        jbtnexcluitudo.setText("Excluir tudo");
        jbtnexcluitudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnexcluitudoActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnexcluitudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 107, -1));

        jbtnexclui1.setBackground(new java.awt.Color(255, 51, 51));
        jbtnexclui1.setText("Excluir Vendedor");
        jbtnexclui1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnexclui1ActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnexclui1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 130, -1));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 430, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtninsereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtninsereActionPerformed
        controller.salvaVendedor();
        jtxtNome.setText("");
        jtxtCargo.setText("");
        jtxtCPF.setText("");
        jtxtRG.setText("");
        try {
            readJtable();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jbtninsereActionPerformed

    private void jbtnexcluitudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnexcluitudoActionPerformed
        controller.excluitudo();
        try {
            readJtable();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnexcluitudoActionPerformed

    private void jbtnexclui1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnexclui1ActionPerformed
        controller.excluiUM();
        jtxtID.setText("");
        try {
            readJtable();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroVendedor.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(CadastroVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroVendedor().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroVendedor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public JTextField getJtxtCPF() {
        return jtxtCPF;
    }

    public void setJtxtCPF(JTextField jtxtCPF) {
        this.jtxtCPF = jtxtCPF;
    }

    public JTextField getJtxtCargo() {
        return jtxtCargo;
    }

    public void setJtxtCargo(JTextField jtxtCargo) {
        this.jtxtCargo = jtxtCargo;
    }

    public JTextField getJtxtID() {
        return jtxtID;
    }

    public void setJtxtID(JTextField jtxtID) {
        this.jtxtID = jtxtID;
    }

    public JTextField getJtxtNome() {
        return jtxtNome;
    }

    public void setJtxtNome(JTextField jtxtNome) {
        this.jtxtNome = jtxtNome;
    }

    public JTextField getJtxtRG() {
        return jtxtRG;
    }

    public void setJtxtRG(JTextField jtxtRG) {
        this.jtxtRG = jtxtRG;
    }

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable jVendedores;
    private javax.swing.JButton jbtnexclui1;
    private javax.swing.JButton jbtnexcluitudo;
    private javax.swing.JButton jbtninsere;
    private javax.swing.JTextField jtxtCPF;
    private javax.swing.JTextField jtxtCargo;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtNome;
    private javax.swing.JTextField jtxtNome2;
    private javax.swing.JTextField jtxtRG;
    // End of variables declaration//GEN-END:variables
}
