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

    public static class Usuario {
        private String nomeCompleto;
        private String email;
        private String senha;

        private double receitaTotal = 0;
        private double despesaTotal = 0;


        public Usuario(String nomeCompleto, String email, String senha) {
            this.nomeCompleto = nomeCompleto;
            this.email = email;
            this.senha = senha;
        }

        public Usuario() {
        }

        public String getNomeCompleto() {
            return nomeCompleto;
        }

        public String getEmail() {
            return email;
        }

        public String getSenha() {
            return senha;
        }

        public void setNomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public void setReceitaTotal(double receitaTotal) {
            this.receitaTotal = receitaTotal;
        }

        public void setDespesaTotal(double despesaTotal) {
            this.despesaTotal = despesaTotal;
        }

        public double getReceitaTotal() {
            return receitaTotal;
        }



        public double getDespesaTotal() {
            return despesaTotal;
        }


        public String getFirstName() {
            String[] nomeSplitado = nomeCompleto.split(" ");
            String firtName = nomeSplitado[0];
            return firtName;
        }

    }
}
