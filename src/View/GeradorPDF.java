package View;


import DAO.VendedorDAO;
import DAO.conexao;
import Model.HeaderFooterPageEvent;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.security.auth.module.NTSystem;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gdeba
 */
public class GeradorPDF extends javax.swing.JFrame  {
    
    /**
     * Creates new form PdfFazendo
     */
    MaskFormatter mfdata;
    MaskFormatter mfhora;
   
    PreparedStatement statement = null;
    ResultSet rs = null;
    double total = 0;
    int i = 0;
    public GeradorPDF() throws SQLException {
        try {
            mfdata = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            mfhora = new MaskFormatter("##h##min");
        }
        catch(ParseException ex){
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        readComboboxV();
        readComboboxP();
        readComboboxE();
        
    }
    
    public void readComboboxV() throws SQLException{
        Connection Conexao = new conexao().getConnection();
        try {
            jVendedores.removeAllItems();
            String sql = "select nome from vendedor";
            statement = Conexao.prepareStatement(sql);
            jVendedores.addItem("");
            rs = statement.executeQuery();
            while(rs.next()){
                String id = rs.getString("nome").trim();
                jVendedores.addItem(id);
                
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL");
        }
 
    }
    public void preencheremp() throws SQLException{
         Connection Conexao = new conexao().getConnection();
         String nome_recebido = (String) jempresas.getSelectedItem();
         
        try {
            String sql = "select * from empresa where nome='"+nome_recebido+"'";
            statement = Conexao.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String end = rs.getString("endereco");
                String comp = rs.getString("complemento");
                String bairro = rs.getString("bairro");
                String cid = rs.getString("cidade");
                String est = rs.getString("estado");
                String cnpj = rs.getString("cnpj");
                String insc = rs.getString("inscricao_estadual");
                String tel = rs.getString("telefone");
                String num = rs.getString("numero");
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                jnum.setText(num);
                jcomp.setText(comp);
                jtxtend.setText(end);
                jbairro.setText(bairro);
                juf.setText(est);
                jcnpj.setText(cnpj);
                jemail.setText(email);
                jtel.setText(tel);
                jinsc.setText(insc);
                jcid.setText(cid);
                
                
                
                
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL");
        }
    }
    
    public void preencherVend() throws SQLException{
        Connection Conexao = new conexao().getConnection();
         String nome_recebido = (String) jVendedores.getSelectedItem();
         
        try {
            String sql = "select * from vendedor where nome='"+nome_recebido+"'";
            statement = Conexao.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String rg = rs.getString("rg");
                String cargo = rs.getString("cargo");
                String cpf = rs.getString("cpf");
                jTextFieldCPF.setText(cpf);
                jTextFieldRG.setText(rg);
                jTextFieldCargo.setText(cargo);
                
                
                
                
                
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL");
        }
    }
    
    
    public void readComboboxP() throws SQLException{
        Connection Conexao = new conexao().getConnection();
        try {
            jProdutos.removeAllItems();
            String sql = "select item from produto";
            statement = Conexao.prepareStatement(sql);
            jProdutos.addItem("");
            rs = statement.executeQuery();
            while(rs.next()){
                String id = rs.getString("item").trim();
                jProdutos.addItem(id);
                
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL");
        }
 
    }
    public void readComboboxE() throws SQLException{
        Connection Conexao = new conexao().getConnection();
        try {
            jempresas.removeAllItems();
            String sql = "select nome from empresa";
            statement = Conexao.prepareStatement(sql);
            jempresas.addItem("");
            rs = statement.executeQuery();
            while(rs.next()){
                String id = rs.getString("nome").trim();
                jempresas.addItem(id);
                
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL");
        }
 
    }
    
    public void preencherProd() throws SQLException{
         DecimalFormat df = new DecimalFormat("#,##0.00");
         Connection Conexao = new conexao().getConnection();
         String nome_recebido = (String) jProdutos.getSelectedItem();
         String qtde = jTextFieldqtd.getText().trim();
         DefaultTableModel modelo = (DefaultTableModel) jPedido.getModel();
         modelo.setRowCount(i);
         i++;
         int qtd;
         double prec,t = 0;
         qtd = Integer.parseInt(qtde);
        try {
            String sql = "select * from produto where item='"+nome_recebido+"'";
            statement = Conexao.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                String id = rs.getString("item");
                String desc = rs.getString("descricao");
                String unidade = rs.getString("unidade");
                String preco = rs.getString("preco_unit");
                prec = Double.parseDouble(preco);
                t = prec * qtd;
                Object[] dados = {id,desc,unidade,"R$"+df.format(prec).toString()
                        ,qtde,"R$"+df.format(t).toString()};
                modelo.addRow(dados);
                
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar o sql");
        }
        jTextFieldqtd.setText("");
        
        
    }
    public void removeProd(){
        DefaultTableModel modelo = (DefaultTableModel) jPedido.getModel();
        if (jPedido.getSelectedRow() >= 0){
            modelo.removeRow(jPedido.getSelectedRow());
            jPedido.setModel(modelo);
        }
        else{
            JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");
        }
        i--;
}
    
    public double calcularTotal(){
    double somaTotal=0;int x;
    for(x=0; x<jPedido.getRowCount();x++){
        Object obj = jPedido.getValueAt(x, 5);
        String s = String.valueOf(obj);
        String v = s.substring(2);
        v = v.replace(',', '.');
        somaTotal += Double.parseDouble(v);
    }
    return somaTotal;
    }
    
    public void addTabela(Document doc) throws DocumentException{
        DecimalFormat df = new DecimalFormat("#,##0.00");
        PdfPTable tbl = new PdfPTable(6);
        Font font2 = new Font(12);
        Font fon = new Font(11);
        
        Paragraph prop = new Paragraph("\nProposta Comercial\n\n",font2);
        prop.setAlignment(Element.ALIGN_CENTER);
        doc.add(prop);
        
        
        tbl.addCell("Item");
        tbl.addCell("Descrição");
        tbl.addCell("Unidade");
        tbl.addCell("Preço Unitário");
        tbl.addCell("Quantidade");
        tbl.addCell("Valor");
        
        for(int y = 0; y < jPedido.getRowCount();y++){
            String item = jPedido.getValueAt(y, 0).toString();
            String desc = jPedido.getValueAt(y, 1).toString();
            String uni = jPedido.getValueAt(y, 2).toString();
            String prec = jPedido.getValueAt(y, 3).toString();
            String qtd = jPedido.getValueAt(y, 4).toString();
            String val = jPedido.getValueAt(y, 5).toString();
            
            tbl.addCell(item);
            tbl.addCell(desc);
            tbl.addCell(uni);
            tbl.addCell(prec);
            tbl.addCell(qtd);
            tbl.addCell(val);
            
        }
        double resp = calcularTotal();
        Paragraph tot = new Paragraph("Valor Total = R$" + df.format(resp));
        tot.setAlignment(Element.ALIGN_RIGHT);
        doc.add(tbl);
        doc.add(tot);
        
        Paragraph p = new Paragraph("\nProcedência: nacional - Marca: Ceagesp\n" +
        "Nos preços propostos estão inclusos, além do lucro, todas as despesas e custos diretos ou indiretos relacionados ao fornecimento do objeto da presente licitação, tais como tributos, remunerações, despesas financeiras e quaisquer outras necessárias ao cumprimento do objeto desta licitação, inclusive gastos com transporte. \n" +
        "O preço ofertado permanecerá fixo e irreajustável. \n" +
        "Validade da proposta: 60 (sessenta) dias contados a partir da data de sua apresentação.\n" +
        "Declaramos expressamente que conhecemos e aceitamos todas as exigências do edital e das Leis que regem a espécie.",fon);
        doc.add(p);
        
        
        
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        JtextNmPent = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        JtextDoc1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTextSetor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTEXTDT = new javax.swing.JFormattedTextField(mfdata);
        LBLDATA = new javax.swing.JLabel();
        jTextHora = new javax.swing.JFormattedTextField(mfhora);
        jLabel4 = new javax.swing.JLabel();
        jPregao = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextPregao = new javax.swing.JTextField();
        jTextProcesso = new javax.swing.JTextField();
        jTextObjeto = new javax.swing.JTextField();
        jVendedores = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jTextFieldCargo = new javax.swing.JTextField();
        jTextFieldCPF = new javax.swing.JTextField();
        jTextFieldRG = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jProdutos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPedido = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jbtnadd = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextFieldqtd = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jempresas = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jemail = new javax.swing.JTextField();
        jtxtend = new javax.swing.JTextField();
        jcomp = new javax.swing.JTextField();
        jnum = new javax.swing.JTextField();
        jcid = new javax.swing.JTextField();
        juf = new javax.swing.JTextField();
        jcnpj = new javax.swing.JTextField();
        jinsc = new javax.swing.JTextField();
        jtel = new javax.swing.JTextField();
        jbairro = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setText("Gerar PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 530, 147, 50));

        JtextNmPent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextNmPentActionPerformed(evt);
            }
        });
        getContentPane().add(JtextNmPent, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 150, -1));

        jLabel1.setText("Nome da Penitenciária");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));

        lblData.setToolTipText("");
        getContentPane().add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        JtextDoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextDoc1ActionPerformed(evt);
            }
        });
        getContentPane().add(JtextDoc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 150, -1));

        jLabel2.setText("Nome do Documento");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        JTextSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextSetorActionPerformed(evt);
            }
        });
        getContentPane().add(JTextSetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 150, -1));

        jLabel3.setText("Setor");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));

        jTEXTDT.setName("jTextDATA"); // NOI18N
        jTEXTDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTEXTDTFocusLost(evt);
            }
        });
        jTEXTDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTEXTDTActionPerformed(evt);
            }
        });
        getContentPane().add(jTEXTDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 150, -1));

        LBLDATA.setText("Data");
        LBLDATA.setName("jLblData"); // NOI18N
        getContentPane().add(LBLDATA, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, -1, -1));

        jTextHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextHoraActionPerformed(evt);
            }
        });
        getContentPane().add(jTextHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 150, -1));

        jLabel4.setText("Hora");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, -1, -1));

        jPregao.setText("Pregão");
        getContentPane().add(jPregao, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        jLabel6.setText("Processo");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, -1, -1));

        jLabel7.setText("Objeto");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, -1, -1));
        getContentPane().add(jTextPregao, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 150, -1));
        getContentPane().add(jTextProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 150, -1));
        getContentPane().add(jTextObjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 150, -1));

        jVendedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVendedoresActionPerformed(evt);
            }
        });
        getContentPane().add(jVendedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 140, -1));

        jButton2.setBackground(new java.awt.Color(0, 51, 255));
        jButton2.setText("Buscar Vendedor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        jTextFieldCargo.setEditable(false);
        jTextFieldCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCargoActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 100, -1));

        jTextFieldCPF.setEditable(false);
        jTextFieldCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCPFActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 100, -1));

        jTextFieldRG.setEditable(false);
        getContentPane().add(jTextFieldRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 100, -1));

        jLabel5.setText("Cargo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel8.setText("CPF");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel9.setText("RG");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel10.setText("Vendedores");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jProdutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        getContentPane().add(jProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 110, -1));

        jPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Descrição", "Unidade", "Preço Unitário", "Quantidade", "Valor"
            }
        ));
        jScrollPane1.setViewportView(jPedido);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 590, 220));

        jLabel11.setText("Itens");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 20));

        jbtnadd.setBackground(new java.awt.Color(0, 255, 0));
        jbtnadd.setText("Adicionar Item");
        jbtnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaddActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 100, -1));

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setText("Retirar Item");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 560, 100, -1));
        getContentPane().add(jTextFieldqtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 80, -1));

        jLabel12.setText("Quantidade");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, 20));

        jempresas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jempresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jempresasActionPerformed(evt);
            }
        });
        getContentPane().add(jempresas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 140, -1));

        jLabel13.setText("Empresas");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jButton5.setBackground(new java.awt.Color(0, 51, 255));
        jButton5.setText("Buscar Empresa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jLabel14.setText("Endereço");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jLabel15.setText("Cidade");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        jLabel16.setText("Inscrição Estadual");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));

        jLabel17.setText("Complemento");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel18.setText("Estado");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        jLabel19.setText("Telefone");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, -1, -1));

        jLabel20.setText("Numero");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, -1));

        jLabel21.setText("CNPJ");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, -1));

        jLabel22.setText("Bairro");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        jLabel23.setText("Email");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jemail.setEditable(false);
        getContentPane().add(jemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 160, -1));

        jtxtend.setEditable(false);
        jtxtend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtendActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtend, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 160, -1));

        jcomp.setEditable(false);
        jcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcompActionPerformed(evt);
            }
        });
        getContentPane().add(jcomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 150, -1));

        jnum.setEditable(false);
        getContentPane().add(jnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 160, -1));

        jcid.setEditable(false);
        getContentPane().add(jcid, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 140, -1));

        juf.setEditable(false);
        juf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jufActionPerformed(evt);
            }
        });
        getContentPane().add(juf, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 140, -1));

        jcnpj.setEditable(false);
        jcnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcnpjActionPerformed(evt);
            }
        });
        getContentPane().add(jcnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 140, -1));

        jinsc.setEditable(false);
        getContentPane().add(jinsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 130, -1));

        jtel.setEditable(false);
        getContentPane().add(jtel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 130, -1));

        jbairro.setEditable(false);
        getContentPane().add(jbairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, 130, -1));

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 540, 80, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JtextNmPentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextNmPentActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JtextNmPentActionPerformed
    
    
    //definição de fontes

    
    
    
    
    
    
    
    
    
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        NTSystem infoSystem = new NTSystem();
        Document documentoPDF = new Document();
        String y,nomeDoc,dt,data,hora,obj,pregao,processo;
        Scanner in = new Scanner(System.in);
        String nomePenit,nomeSetor;
        
        dt = obterData();
        try{
            String sub_pasta = "C:\\Users\\"+infoSystem.getName()+"\\OneDrive\\Documents\\Pdfs Criadoss\\" + dt +"\\";
            
            
            File Subpasta = new File(sub_pasta);
            Subpasta.mkdirs();
            
            
            
            
            nomeDoc = JtextDoc1.getText();
            nomePenit = JtextNmPent.getText();
            nomeSetor = JTextSetor.getText();
            data = jTEXTDT.getText();
            hora = jTextHora.getText();
            pregao = jTextPregao.getText();
            processo = jTextProcesso.getText();
            obj = jTextObjeto.getText();
            String empresas = (String) jempresas.getSelectedItem();
            String vendedor = (String) jVendedores.getSelectedItem();
            if(nomeDoc.equals("") || nomePenit.equals("") || nomeSetor.equals("") || i == 0 || empresas.equals("") || vendedor.equals("")){
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos e a tabela não pode ser vazia");
            }
            else{
            PdfWriter writer = PdfWriter.getInstance(documentoPDF, new FileOutputStream(sub_pasta + nomeDoc + ".pdf"));
   
            HeaderFooterPageEvent event = new HeaderFooterPageEvent(this);
            writer.setPageEvent(event);
            documentoPDF.open();
            dados(documentoPDF,nomePenit,nomeSetor);
            pregao(documentoPDF,hora,data,pregao,processo,obj);
            declaracao(documentoPDF);
            gerarASS(documentoPDF);
            //inicio da segunda pagina
            documentoPDF.newPage();
            dados(documentoPDF,nomePenit,nomeSetor);
            pregao(documentoPDF,hora,data,pregao,processo,obj);
            declaracaomicro(documentoPDF);
            gerarASS(documentoPDF);
            documentoPDF.newPage();
            dados(documentoPDF,nomePenit,nomeSetor);
            pregao(documentoPDF,hora,data,pregao,processo,obj);
            declaracaoindependente(documentoPDF);
            gerarASS(documentoPDF);
            documentoPDF.newPage();
            dados(documentoPDF,nomePenit,nomeSetor);
            pregao(documentoPDF,hora,data,pregao,processo,obj);
            addTabela(documentoPDF);
            gerarASS(documentoPDF);
            documentoPDF.close();
            
            
        
            
            documentoPDF.setPageSize(PageSize.A4);
            
            JOptionPane.showMessageDialog(null, "PDF CRIADO COM SUCESSO");
            }
            
        }catch(DocumentException | IOException de){
        }finally{
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JtextDoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextDoc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtextDoc1ActionPerformed

    private void JTextSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextSetorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextSetorActionPerformed

    private void jTEXTDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTEXTDTFocusLost
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
             try{
                 Date date = sdf.parse(jTEXTDT.getText());
                 jTEXTDT.setValue(sdf.format(date));
                 
             }
             catch(ParseException e){
                 jTEXTDT.setFocusLostBehavior(JFormattedTextField.PERSIST);
                 jTEXTDT.setText("");
                 jTEXTDT.setValue(null);
             }
    }//GEN-LAST:event_jTEXTDTFocusLost

    private void jTextHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextHoraActionPerformed

    private void jTEXTDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTEXTDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTEXTDTActionPerformed

    private void jVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVendedoresActionPerformed
              
    }//GEN-LAST:event_jVendedoresActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            preencherVend();
        } catch (SQLException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCPFActionPerformed

    private void jTextFieldCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCargoActionPerformed

    private void jbtnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaddActionPerformed
        String qtde = getjTextFieldqtd().getText();
        
        if(qtde.equals("") || qtde.equals("") || qtde.equals("0") || !qtde.matches("[A-Z]*")){
            JOptionPane.showMessageDialog(null, "Valor de quantidade não pode nem ser vazio,deve ser numérico e um produto tem que estar selecionado na seleção");
        }
        else{
            try {
                preencherProd();

            } catch (SQLException ex) {
                Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnaddActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        removeProd();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jtxtendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtendActionPerformed

    private void jcnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcnpjActionPerformed

    private void jufActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jufActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jufActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            preencheremp();
        } catch (SQLException ex) {
            Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcompActionPerformed

    private void jempresasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jempresasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jempresasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    
    
    
    
    
    
    
    public static void cabecalho(Document docPDF) throws DocumentException{
        String traco = "______________________________________________________";
        Font font = new Font(Font.HELVETICA,20,Font.ITALIC,new Color(105,105,105));
        Paragraph cabecalhor = new Paragraph("COMERCIAL TAQUARUSSU LTDA – EPP",font);
        cabecalhor.setAlignment(Element.ALIGN_CENTER);
        docPDF.add(cabecalhor);
        docPDF.add(new Paragraph(traco));
    }
    
    public static void dados(Document docPDF,String nm,String nmS) throws DocumentException{
        Font font2 = new Font(12);
        Paragraph rep = new Paragraph("\nÀ\n" +
        "SECRETARIA DE ESTADO DA ADMINISTRAÇÃO PENITENCIÁRIA",font2);
        Paragraph d = new Paragraph(nmS + "\n" + nm,font2);
        docPDF.add(new Paragraph(rep));
        docPDF.add(new Paragraph(d));
        
    }
    public static void pregao(Document docPDF,String hora,String data,String pregao,String processo,String objeto)throws DocumentException{
        Font font2 = new Font(12);
        Paragraph rep = new Paragraph("PREGÃO ELETRÔNICO " + pregao + "\nPROCESSO " + processo + "\nDATA E HORA DA ABERTURA DA SESSÃO PÚBLICA: " + 
                data + "-às " + hora + "\n\n",font2);
        Paragraph obj = new Paragraph("OBJETO: " + objeto,font2);
        docPDF.add(new Paragraph(rep));
        docPDF.add(new Paragraph(obj));
    }
    public void declaracao(Document docPDF) throws DocumentException{
        Font font2 = new Font(12,Font.BOLD);
        Font font3 = new Font(12);
        String n = (String) jVendedores.getSelectedItem();
        String rg = jTextFieldRG.getText();
        String cpf = jTextFieldCPF.getText();
        String nomeE = (String) jempresas.getSelectedItem();
        String pregao = jTextPregao.getText();
        String processo = jTextProcesso.getText();
        Paragraph rep = new Paragraph("\nDECLARAÇÃO\n",font3);
        rep.setAlignment(Element.ALIGN_CENTER);
        Paragraph dec = new Paragraph("\n"+n.toUpperCase()+", RG nº "+rg+", CPF nº "+cpf+", DECLARO, sob as penas da Lei, que o licitante "+nomeE.toUpperCase()+", interessado em participar do PREGÃO ELETRÔNICO "+pregao+" - PROCESSO "+processo+":",font3);
        Paragraph buc = new Paragraph("a) está em situação regular perante o Ministério do Trabalho no que se refere a observância do disposto no inciso XXXIII do artigo 7.º da Constituição Federal, na forma do Decreto Estadual nº. 42.911/1998; \n" +
        "b) não possui impedimento legal para licitar ou contratar com a Administração;\n\n\n",font3);
        docPDF.add(rep);
        docPDF.add(new Paragraph(dec));
        docPDF.add(new Paragraph(buc));
        
        

    }
    public void declaracaomicro(Document docPDF) throws DocumentException{
        Font font2 = new Font(12,Font.BOLD);
        Font font3 = new Font(12);
        String n = (String) jVendedores.getSelectedItem();
        String rg = jTextFieldRG.getText();
        String cpf = jTextFieldCPF.getText();
        String nomeE = (String) jempresas.getSelectedItem();
        String pregao = jTextPregao.getText();
        String processo = jTextProcesso.getText();
        Paragraph rep = new Paragraph("\nDECLARAÇÃO DE ENQUADRAMENTO COMO MICROEMPRESA OU EMPRESA DE PEQUENO PORTE\n",font3);
        rep.setAlignment(Element.ALIGN_CENTER);
        Paragraph dec = new Paragraph("\nEu, "+n.toUpperCase()+", portador do RG nº "+rg+" e do CPF nº "+cpf+", representante legal do licitante "+nomeE.toUpperCase()+", interessado em participar do PREGÃO ELETRÔNICO "+pregao+" - PROCESSO "+processo+","
                + "DECLARO, sob as penas da Lei, o seu enquadramento na condição de Microempresa ou Empresa de Pequeno Porte, "
                + "nos critérios previstos no artigo 3º da Lei Complementar Federal n° 123/2006, "
                + "bem como sua não inclusão nas vedações previstas no mesmo diploma legal.\n\n\n ",font3);
        docPDF.add(rep);
        docPDF.add(new Paragraph(dec));
        

    }
    public void declaracaoindependente(Document docPDF) throws DocumentException{
        Font font2 = new Font(12,Font.BOLD);
        Font font3 = new Font(12);
        String n = (String) jVendedores.getSelectedItem();
        String rg = jTextFieldRG.getText();
        String cpf = jTextFieldCPF.getText();
        String nomeE = (String) jempresas.getSelectedItem();
        String pregao = jTextPregao.getText();
        String processo = jTextProcesso.getText();
        Paragraph rep = new Paragraph("\nDECLARAÇÃO DE ENQUADRAMENTO COMO MICROEMPRESA OU EMPRESA DE PEQUENO PORTE\n",font3);
        rep.setAlignment(Element.ALIGN_CENTER);
        Paragraph dec = new Paragraph("\nEu, "+n.toUpperCase()+", portador do RG nº "+rg+" e do CPF nº "+cpf+", representante legal do licitante "+nomeE.toUpperCase()+", interessado em participar do PREGÃO ELETRÔNICO "+pregao+" - PROCESSO "+processo+".",font3);
        Paragraph buc = new Paragraph("DECLARO, sob as penas da Lei, especialmente o artigo 299 do Código Penal Brasileiro, que:\n" +
"a) a proposta apresentada foi elaborada de maneira independente e o seu conteúdo não foi, no todo ou em parte, direta ou indiretamente, informado ou discutido com qualquer outro licitante ou interessado, em potencial ou de fato, no presente procedimento licitatório;\n" +
"b) a intenção de apresentar a proposta não foi informada ou discutida com qualquer outro licitante ou interessado, em potencial ou de fato, no presente procedimento licitatório;\n" +
"c) o licitante não tentou, por qualquer meio ou por qualquer pessoa, influir na decisão de qualquer outro licitante ou interessado, em potencial ou de fato, no presente procedimento licitatório;\n" +
"d) o conteúdo da proposta apresentada não será, no todo ou em parte, direta ou indiretamente, comunicado ou discutido com qualquer outro licitante ou interessado, em potencial ou de fato, no presente procedimento licitatório antes da adjudicação do objeto;\n" +
"e) o conteúdo da proposta apresentada não foi, no todo ou em parte, informado, discutido ou recebido de qualquer integrante relacionado, direta ou indiretamente, ao órgão licitante antes da abertura oficial das propostas; e\n" +
"f) o representante legal do licitante está plenamente ciente do teor e da extensão desta declaração e que detém plenos poderes e informações para firmá-la.\n" +
"DECLARO, ainda, que a pessoa jurídica que represento conduz seus negócios de forma a coibir fraudes, corrupção e a prática de quaisquer outros atos lesivos à Administração Pública, nacional ou estrangeira, em atendimento à Lei Federal nº 12.846/ 2013 e ao Decreto Estadual nº 60.106/2014, tais como:\n" +
"I – prometer, oferecer ou dar, direta ou indiretamente, vantagem indevida a agente público, ou a terceira pessoa a ele relacionada; \n" +
"II – comprovadamente, financiar, custear, patrocinar ou de qualquer modo subvencionar a prática dos atos ilícitos previstos em Lei; \n" +
"III – comprovadamente, utilizar-se de interposta pessoa física ou jurídica para ocultar ou dissimular seus reais interesses ou a identidade dos beneficiários dos atos praticados; \n" +
"IV – no tocante a licitações e contratos: \n" +
"a)frustrar  ou  fraudar,  mediante  ajuste,  combinação  ou  qualquer  outro  expediente,  o  caráter  competitivo  de procedimento licitatório público; \n" +
"b) impedir, perturbar ou fraudar a realização de qualquer ato de procedimento licitatório público; \n" +
"c) afastar ou procurar afastar licitante, por meio de fraude ou oferecimento de vantagem de qualquer tipo; \n" +
"d) fraudar licitação pública ou contrato dela decorrente; \n" +
"e) criar, de modo fraudulento ou irregular, pessoa jurídica para participar de licitação pública ou celebrar contrato administrativo; \n" +
"f)obter  vantagem  ou  benefício  indevido,  de  modo  fraudulento,  de  modificações  ou  prorrogações  de  contratos celebrados com a administração pública, sem autorização em lei, no ato convocatório da licitação pública ou nos respectivos instrumentos contratuais; ou \n" +
"g) manipular ou fraudar o equilíbrio econômico-financeiro dos contratos celebrados com a administração pública; \n" +
"V – dificultar atividade de investigação ou fiscalização de órgãos, entidades ou agentes públicos, ou intervir em sua atuação, inclusive no âmbito das agências reguladoras e dos órgãos de fiscalização do sistema financeiro nacional.\n\n\n\n",font3);
        docPDF.add(rep);
        docPDF.add(new Paragraph(dec));
        docPDF.add(new Paragraph(buc));
    
    
    }
    
    
    public void gerarASS(Document docPDF) throws DocumentException{
        Font font3 = new Font(12);
        String n = (String) jVendedores.getSelectedItem();
        String rg = jTextFieldRG.getText();
        String cpf = jTextFieldCPF.getText();
        String nomeE = (String) jempresas.getSelectedItem();
        String pregao = jTextPregao.getText();
        String processo = jTextProcesso.getText();
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        
        int dia = data.getDay();
        int mes = data.getMonth();
        int ano = c.get(Calendar.YEAR);
        String m = null;
        switch(mes){
            case 0:
                m = "janeiro";
                break;
            case 1:
                m = "fevereiro";
                break;
            case 2:
                m = "março";
                break;
            case 3:
                m = "abril";
                break;
            case 4:
                m = "maio";
                break;
            case 5:
                m = "junho";
                break;
            case 6:
                m = "julho";
                break;
            case 7:
                m = "agosto";
                break;
            case 8:
                m = "setembro";
                break;
            case 9:
                m = "outubro";
                break;
            case 10:
                m = "novembro";
                break;
            case 11:
                m = "dezembro";
                break;
        }
        String fMaiuscula = toTitledCase(nomeE);
        Paragraph dat = new Paragraph("Sorocaba,"+dia+" de "+m+" de "+ano+"\n\n\n\n",font3);
        String a = jTextFieldCargo.getText();
        Paragraph ass = new Paragraph("__________________________________________\n"
                + ""+fMaiuscula+"\n"
                + ""+n+"-"+a+"\n"
                + "RG. "+rg+" - SSP\n"
                + "CPF "+cpf+"\n",font3);
        docPDF.add(new Paragraph(dat));
        docPDF.add(new Paragraph(ass));
        
        
        
    }
    
    public static String toTitledCase(String str){
		String[] words = str.split("\\s");
		StringBuilder sb = new StringBuilder();
 
		for(int i = 0; i < words.length; i++){
			sb.append(words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase());
			sb.append(" ");
		}
 
		return sb.toString();
	}
    
    
    
    
    
    
    
    
    
    
    
    
   
     public static String obterData(){
        Calendar c = Calendar.getInstance();
        
        //obtenção da data atual
        Date data = c.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dt = sdf.format(data);
         
        return dt; 
     }
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
            java.util.logging.Logger.getLogger(GeradorPDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeradorPDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeradorPDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeradorPDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GeradorPDF().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GeradorPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }

    public JTextField getJbairro() {
        return jbairro;
    }

    public void setJbairro(JTextField jbairro) {
        this.jbairro = jbairro;
    }

    public JTextField getJcid() {
        return jcid;
    }

    public void setJcid(JTextField jcid) {
        this.jcid = jcid;
    }

    public JTextField getJcnpj() {
        return jcnpj;
    }

    public void setJcnpj(JTextField jcnpj) {
        this.jcnpj = jcnpj;
    }

    public JTextField getJcomp() {
        return jcomp;
    }

    public void setJcomp(JTextField jcomp) {
        this.jcomp = jcomp;
    }

    public JTextField getJemail() {
        return jemail;
    }

    public void setJemail(JTextField jemail) {
        this.jemail = jemail;
    }

    public JComboBox<String> getJempresas() {
        return jempresas;
    }

    public void setJempresas(JComboBox<String> jempresas) {
        this.jempresas = jempresas;
    }

    public JTextField getJinsc() {
        return jinsc;
    }

    public void setJinsc(JTextField jinsc) {
        this.jinsc = jinsc;
    }

    public JTextField getJnum() {
        return jnum;
    }

    public void setJnum(JTextField jnum) {
        this.jnum = jnum;
    }

    public JTextField getJtel() {
        return jtel;
    }

    public void setJtel(JTextField jtel) {
        this.jtel = jtel;
    }

    public JTextField getJtxtend() {
        return jtxtend;
    }

    public void setJtxtend(JTextField jtxtend) {
        this.jtxtend = jtxtend;
    }

    public JTextField getJuf() {
        return juf;
    }

    public void setJuf(JTextField juf) {
        this.juf = juf;
    }

    public JTextField getjTextFieldqtd() {
        return jTextFieldqtd;
    }

    public void setjTextFieldqtd(JTextField jTextFieldqtd) {
        this.jTextFieldqtd = jTextFieldqtd;
    }
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextSetor;
    private javax.swing.JTextField JtextDoc1;
    private javax.swing.JTextField JtextNmPent;
    private javax.swing.JLabel LBLDATA;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTable jPedido;
    private javax.swing.JLabel jPregao;
    private javax.swing.JComboBox<String> jProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField jTEXTDT;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldCargo;
    private javax.swing.JTextField jTextFieldRG;
    private javax.swing.JTextField jTextFieldqtd;
    private javax.swing.JFormattedTextField jTextHora;
    private javax.swing.JTextField jTextObjeto;
    private javax.swing.JTextField jTextPregao;
    private javax.swing.JTextField jTextProcesso;
    private javax.swing.JComboBox<String> jVendedores;
    private javax.swing.JTextField jbairro;
    private javax.swing.JButton jbtnadd;
    private javax.swing.JTextField jcid;
    private javax.swing.JTextField jcnpj;
    private javax.swing.JTextField jcomp;
    private javax.swing.JTextField jemail;
    private javax.swing.JComboBox<String> jempresas;
    private javax.swing.JTextField jinsc;
    private javax.swing.JTextField jnum;
    private javax.swing.JTextField jtel;
    private javax.swing.JTextField jtxtend;
    private javax.swing.JTextField juf;
    private javax.swing.JLabel lblData;
    // End of variables declaration//GEN-END:variables
}
