/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gdeba
 */
public class Vendedor {
    private int id;
    private String nome;
    private String cargo;
    private String cpf;
    private String rg;

    public Vendedor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Vendedor(String nome, String cargo, String cpf, String rg) {
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
        this.rg = rg;
    }

    public Vendedor(int id) {
        this.id = id;
    }
    
    
    
    
}
