/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
import Model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gdeba
 */
public class ProdutoDAO {
    private final Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public ProdutoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void insert(Produto produto) throws SQLException{
        String sql = "insert into produto values('"+produto.getItem()+"','"+ produto.getDescricao() 
                +"','"+produto.getUnidade()+"','"+produto.getPreco_unitario()+"');";
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.execute();
        statement.close();
        
    }
    public void deleteAll() throws SQLException{
        String sql = "delete from produto";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
    }
    
    public void deleteOne(Produto produto) throws SQLException{
        String sql = "delete from produto where item="+produto.getItem()+"";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
    }

    public boolean autenticarProdutoporItem(Produto produto) throws SQLException {
        String sql = "select * from produto where item = '"+produto.getItem()+"'";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        return resultSet.next();
    }
    
    
}
