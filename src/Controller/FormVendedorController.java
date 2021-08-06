/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import Model.Vendedor;
import DAO.UsuarioDAO;
import DAO.VendedorDAO;
import DAO.conexao;
import View.CadastroUsuario;
import View.CadastroVendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gdeba
 */
public class FormVendedorController {
    private final CadastroVendedor view;

    public FormVendedorController(CadastroVendedor view) {
        this.view = view;
    }
    
    public void salvaVendedor(){
        String nome = view.getJtxtNome().getText().trim();
        String cargo = view.getJtxtCargo().getText().trim();
        String rg = view.getJtxtRG().getText().trim();
        String cpf = view.getJtxtCPF().getText().trim();
        if(nome.equals("") || cargo.equals("") || rg.equals("") || cpf.equals("")){
            JOptionPane.showMessageDialog(null, "Os campos de nome,cargo,rg e cpf devem estar preenchidos");
        }
        else{
        Vendedor vendedor = new Vendedor(nome,cargo,cpf,rg);
        
        try{
            Connection Conexao = new conexao().getConnection();
            VendedorDAO vendedordao = new VendedorDAO(Conexao);
            vendedordao.insert(vendedor);
            
            JOptionPane.showMessageDialog(null, "Vendedor cadastrado no sistema!!!");
            
            
        
        }
        catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    }
    public void excluitudo(){
        try{
            Connection Conexao = new conexao().getConnection();
            VendedorDAO vendedordao = new VendedorDAO(Conexao);
            vendedordao.deleteAll();
            
            JOptionPane.showMessageDialog(null, "Todos os vendedores excluidos!!!");
            
            
        
        }
        catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Não existem vendedores para serem excluidos!!!!");
        }
    }
    public void excluiUM(){
      
        String ID = view.getJtxtID().getText();
        if(ID.equals("")){
            JOptionPane.showMessageDialog(null, "Coloque um ID para excluir");
        }
        else{
                int id = Integer.parseInt(ID);

                Vendedor vendedor = new Vendedor(id);

                try{
                    Connection Conexao = new conexao().getConnection();
                    VendedorDAO vendedordao = new VendedorDAO(Conexao);
                    boolean existe = vendedordao.autenticarVendedorporID(vendedor);


                    
                    if(existe){
                        vendedordao.deleteOne(vendedor);
                        JOptionPane.showMessageDialog(null, "Vendedor excluído com sucesso!!!!!!!!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Não Existe vendedor com esse ID");
                    }

                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Coloque um ID para excluir");
                }

                }
        
    }
    
        
   
    
    
    
}
