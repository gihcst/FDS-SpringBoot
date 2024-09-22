package com.bcopstein.endpointsdemo1;

import java.util.List;

public interface IRepository {
    List<Livro> getAll();
    List<String> getTitulos();
    List<String> getAutores();
    List<Livro> getLivrosDoAutor(String autor);
    Livro getLivroTitulo(String titulo);
    boolean cadastraLivroNovo(Livro livro);
    boolean removeLivro(int id);
    boolean atualizarLivro(int id, Livro livro);
}
