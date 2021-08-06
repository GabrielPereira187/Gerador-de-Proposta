/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Produto;
import Model.Vendedor;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import DAO.conexao;
import View.CadastroProduto;
import View.CadastroUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gdeba
 */
public class FormProdutoController {
    private final CadastroProduto view;

    public FormProdutoController(CadastroProduto view) {
        this.view = view;
    }

    public void salvaProduto() throws SQLException{
        String item = view.getJtxtItem().getText().trim();
        String desc = view.getjTextDesc().getText().trim();
        String unidade = (String) view.getjUnidade().getSelectedItem();
        String preco = view.getjTextFieldPreco().getText().trim();
        
        int it = Integer.parseInt(item);
        
        double p = Double.parseDouble(preco);
        if(item.equals("") || desc.equals("") || preco.equals("")){
            JOptionPane.showMessageDialog(null, "Os campos de preço,de item e de descrição devem estar preenchidos");
        }
        else{
        
        Produto produto = new Produto(it,desc,unidade,p);
        
        try{
             Connection Conexao = new conexao().getConnection();
             ProdutoDAO produtodao = new ProdutoDAO(Conexao);
             boolean existe = produtodao.autenticarProdutoporItem(produto);
             if(existe){
                JOptionPane.showMessageDialog(null, "Item ja existente no sistema!!!");
             }
             else{
             produtodao.insert(produto);
             JOptionPane.showMessageDialog(null, "Produto cadastrado no sistema!!!");
             }
             
            
            
        
        }
        catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
                }
    }
    
    public void excluitudo(){
        try{
            Connection Conexao = new conexao().getConnection();
            ProdutoDAO produtodao = new ProdutoDAO(Conexao);
            produtodao.deleteAll();
            
            JOptionPane.showMessageDialog(null, "Todos os produtos excluidos!!!");
            
            
        
        }
        catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Não existem vendedores para serem excluidos!!!!");
        }
    }
    public void excluiUM(){
      
        String ID = view.getJtxtItem().getText().trim();
        if(ID.equals("")){
            JOptionPane.showMessageDialog(null, "Coloque um Item para excluir");
        }
        else{
                int id = Integer.parseInt(ID);

                Produto produto = new Produto(id);

                try{
                    Connection Conexao = new conexao().getConnection();
                    ProdutoDAO pdao = new ProdutoDAO(Conexao);
                    boolean existe = pdao.autenticarProdutoporItem(produto);


                    
                    if(existe){
                        pdao.deleteOne(produto);
                        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!!!!!!!!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Não Existe produto com esse Item");
                    }

                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Coloque um ID para excluir");
                }

                }
        
    }
    
    
    
    
}
