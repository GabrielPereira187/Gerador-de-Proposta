/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.EmpresaDAO;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import DAO.conexao;
import Model.Empresa;
import Model.Produto;
import Model.Vendedor;
import View.CadastroEmpresa;
import View.CadastroUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gdeba
 */
public class FormEmpresaController {
    private final CadastroEmpresa view;

    public FormEmpresaController(CadastroEmpresa view) {
        this.view = view;
    }
    
    public void salvaVendedor(){
        String nome = view.getJtxtnome().getText().trim();
        String endereco = view.getJtxtend().getText().trim();
        String comp = view.getJtxtcomp().getText().trim();
        String bairro = view.getJtxtbairro().getText().trim();
        String cnpj = view.getJcnpj().getText().trim();
        String numero = view.getJnum().getText().trim();
        String cid = view.getJcidade().getText().trim();
        String email = view.getJemail().getText().trim();
        String insc = view.getJinscricao().getText().trim();
        String tel = view.getJtelefone().getText().trim();
        String uf = (String) view.getJestados().getSelectedItem();
        if(nome.equals("") || endereco.equals("") || bairro.equals("") || cnpj.equals("") || numero.equals("") || cid.equals("") || email.equals("") || insc.equals("") ||
                tel.equals("")){
            JOptionPane.showMessageDialog(null, "Para a ação de inserção somente o campo de complemento pode ser vazio");
        }
        
        else{
        Empresa empresa = new Empresa(nome, endereco, comp, bairro, numero, cid, uf, cnpj, insc, tel, email);
        
        try{
            Connection Conexao = new conexao().getConnection();
            EmpresaDAO edao = new EmpresaDAO(Conexao);
            edao.insert(empresa);
            
            JOptionPane.showMessageDialog(null, "Empresa cadastrada no sistema!!!");
            
            
        
        }
        catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    }
    public void excluitudo(){
        try{
            Connection Conexao = new conexao().getConnection();
            EmpresaDAO edao = new EmpresaDAO(Conexao);
            edao.deleteAll();
            
            JOptionPane.showMessageDialog(null, "Todos as empresas excluidas!!!");
            
            
        
        }
        catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Não existem vendedores para serem excluidos!!!!");
        }
    }
    public void excluiUM(){
      
        String ID = view.getJtxtid().getText().trim();
        if(ID.equals("")){
            JOptionPane.showMessageDialog(null, "Coloque um id para excluir");
        }
        else{
                int id = Integer.parseInt(ID);

                Empresa empresa = new Empresa(id);

                try{
                    Connection Conexao = new conexao().getConnection();
                    EmpresaDAO edao = new EmpresaDAO(Conexao);
                    boolean existe = edao.autenticarEmpresaporID(empresa);


                    
                    if(existe){
                        edao.deleteOne(empresa);
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
