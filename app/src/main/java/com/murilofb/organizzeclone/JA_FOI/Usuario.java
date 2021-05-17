package com.murilofb.organizzeclone.JA_FOI;

public class Usuario {
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
