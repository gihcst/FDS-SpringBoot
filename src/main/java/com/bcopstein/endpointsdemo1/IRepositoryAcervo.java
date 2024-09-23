package com.bcopstein.endpointsdemo1;

import java.util.List;

// Interface que define os métodos para operações de acesso a dados relacionados a livros
public interface IRepositoryAcervo {
    List<Livro> getAll();
    List<String> getTitles();
    List<String> getAuthors();
    List<Livro> getAuthorsbooks(String autor);
    Livro getBookTitle(String titulo);
    boolean registerNewBook(Livro livro);
    boolean removeBook(int id);
    boolean updateBook(int id, Livro livro);
    boolean lendBook(String titulo, int id);
    boolean returnBook(int id);
    Double averageBooksFromAuthors();
    List<Livro> getBookbyYear(Integer ano);
    int getRecent(int ano);
    List<Livro> getBooksFromAuthorByYear(String autor, int ano);
    List<Livro> getOutdated(int ano);
    int getNumberOfBook(String autor);
}