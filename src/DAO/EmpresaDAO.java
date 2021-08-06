/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Empresa;
import Model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gdeba
 */
public class EmpresaDAO {
    
    private Connection conexao;

    public EmpresaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    


    public void insert(Empresa empresa) throws SQLException{
        
        String sql = "insert into empresa(nome,endereco,complemento,bairro,numero,cidade,estado,cnpj,inscricao_estadual,telefone,email) values('"+ empresa.getNome() +"','"+ empresa.getEndereco()+"','"+ empresa.getComplemento()+"','"+empresa.getBairro()+"','"+empresa.getNumero()+"'"
                + ",'"+empresa.getCidade()+"','"+empresa.getEstado()+"','"+empresa.getCnpj()+"','"+empresa.getInscricao_estadual()+"','"+empresa.getTelefone()+"','"+empresa.getEmail()+"');";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
        
    }
    public void deleteAll() throws SQLException{
        String sql = "delete from empresa";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
    }
    public void deleteOne(Empresa empresa) throws SQLException{
        String sql = "delete from empresa where id="+empresa.getId()+"";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        conexao.close();
        
    }

    public boolean autenticarEmpresaporID(Empresa empresa) throws SQLException {
        String sql = "select * from empresa where id = '"+empresa.getId()+"'";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        return resultSet.next();
    }
    
    
    
}
