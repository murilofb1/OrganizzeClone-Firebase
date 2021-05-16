package com.murilofb.organizzeclone.models;

public class Movimentacao {

    String data;
    String descricao;
    String tipo;
    String categoria;
    double valor;
    String key;

    public Movimentacao() {
    }

    public Movimentacao(String data, String descricao, String tipo, String categoria, double valor) {
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
