package com.bcopstein.endpointsdemo1;

public class Livro {
    private Integer id; // Identificador único do livro
    private String titulo; // Título do livro
    private String autor; // Autor do livro
    private Integer ano; // Ano de publicação do livro

    /**
     * Construtor da classe Livro.
     * 
     * @param id Identificador do livro.
     * @param titulo Título do livro.
     * @param autor Autor do livro.
     * @param ano Ano de publicação.
     */
    public Livro(Integer id, String titulo, String autor, Integer ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    // Métodos getter para acessar os atributos privados
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

    // Métodos setter para modificar os atributos privados
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    /**
     * Retorna uma representação em string do livro.
     * 
     * @return String formatada com informações do livro.
     */
    @Override
    public String toString() {
        return "Livro " + id + ": " + titulo + ", " + autor + ", de " + ano;
    }    
}
