/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdeba
 */
public class VendedorDAO {
    private final Connection conexao;

    public VendedorDAO(Connection conexao) {
        this.conexao = conexao;
    }

    

    

    

    
    
    public void insert(Vendedor vendedor) throws SQLException{
        
        String sql = "insert into vendedor(nome,cargo,cpf,rg) values('"+ vendedor.getNome() +"','"+ vendedor.getCargo() +"','"+ vendedor.getCpf()+"','"+vendedor.getRg()+"');";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
        
    }
    public void deleteAll() throws SQLException{
        String sql = "delete from vendedor";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
    }
    public void deleteOne(Vendedor vendedor) throws SQLException{
        String sql = "delete from vendedor where id="+vendedor.getId()+"";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
    }

    public boolean autenticarVendedorporID(Vendedor vendedor) throws SQLException {
        String sql = "select * from vendedor where id = '"+vendedor.getId()+"'";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        return resultSet.next();
    }
    
    public ResultSet carregarCombo() throws SQLException{
        String sql = "select nome from vendedor order by nome asc";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        ResultSet rs = statement.executeQuery();
        
        return rs;
    }
    
    
    
    
    
}
