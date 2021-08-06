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
public class Produto {
    private int item;
    private String descricao;
    private String unidade;
    private double preco_unitario;

    public Produto(int item, String descricao, String unidade, double preco_unitario) {
        this.item = item;
        this.descricao = descricao;
        this.unidade = unidade;
        this.preco_unitario = preco_unitario;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public Produto(int item) {
        this.item = item;
    }
    
    
    
}
