package com.bcopstein.endpointsdemo1;

public class Usuario {
    private String nome;
    private int codigo;
    private int anoDeNascimento;

    public Usuario(String nome, int codigo, int anoDeNascimento) {
        this.nome = nome;
        this.codigo = codigo;
        this.anoDeNascimento = anoDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeNascimento(int anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", codigo=" + codigo + ", anoDeNascimento=" + anoDeNascimento + "]";
    }
}
