package com.bcopstein.endpointsdemo1;

public class Livro {
    private Integer id;
    private String titulo;
    private String autor;
    private Integer ano;

    public Livro(Integer id, String titulo, String autor, Integer ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return "Livro " + id + ": " + titulo + ", " + autor + ", de " + ano;
    }

    
}
