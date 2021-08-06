/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import DAO.UsuarioDAO;
import DAO.conexao;
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
public class FormUsuarioController {
    
    private CadastroUsuario view;

    public FormUsuarioController(CadastroUsuario view) {
        this.view = view;
    }
    
    
    public void salvaUsuario(){
    
    String user = view.getjUser().getText().trim();
    String senha = view.getjPass().getText().trim();
    
    if(user.equals("") || senha.equals("")){
        JOptionPane.showMessageDialog(null, "Os campos de senha e usuario devem estar preenchidos");
    }
    else{   
        
        
    Usuario usuario = new Usuario(user,senha);
        
        
        
        try {
            Connection Conexao = new conexao().getConnection();
            UsuarioDAO usuariodao = new UsuarioDAO(Conexao);
            usuariodao.insert(usuario);
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado no sistema!!!");
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
    
    }
    }
    
}
