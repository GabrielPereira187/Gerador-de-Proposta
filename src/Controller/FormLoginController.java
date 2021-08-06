/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import DAO.UsuarioDAO;
import DAO.conexao;
import View.Login;
import View.Menu;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gdeba
 */
public class FormLoginController {
    private final Login view;

    public FormLoginController(Login view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        String user = view.getjUser().getText().trim();
        String senha = view.getjPass().getText().trim();
        Login l = new Login();
        boolean root = false;
        if(user.equals("") || senha.equals("")){
            JOptionPane.showMessageDialog(null, "Coloque o usuario e a senha.");
        }
        if(user.equalsIgnoreCase("fernando") && senha.equals("230968a")){
            root = true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
            return;
        }
        if(root){
            
            Menu menu = new Menu();
            menu.setVisible(true);
            l.dispose();
            
        }
        else{

            Usuario usuario = new Usuario(user,senha);






            Connection Conexao = new conexao().getConnection();
            UsuarioDAO usuariodao = new UsuarioDAO(Conexao);

            boolean existe = usuariodao.autenticarUsuarioporUsuarioeSenha(usuario);

            if(existe){
                Menu menu = new Menu();
                menu.setVisible(true); 
                l.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
            }

        }
    }
    
    
    
    
}
