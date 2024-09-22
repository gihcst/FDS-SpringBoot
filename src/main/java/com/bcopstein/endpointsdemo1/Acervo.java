package com.bcopstein.endpointsdemo1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class Acervo implements IRepository{
    private List<Livro> livros;
    
    public Acervo(){
        livros = new LinkedList<>();
        livros.add(new Livro(1, "Crepúsculo", "Stephenie Meyer", 2005));
        livros.add(new Livro(2, "Cidades de Papel", "John Green", 2015));
        livros.add(new Livro(3, "Rainha Vermelha", "Victoria Aveyard", 2015));
        livros.add(new Livro(4, "A História Secreta", "Elizabeth Kostova", 2005));
        livros.add(new Livro(5, "Pax", "Sara Pennypacker", 2016));
    }

    public boolean cadastraLivroNovo(Livro livro) {
        return livros.add(livro);
    }

    public List<Livro> getAll(){
        return livros;
    }

    public List<String> getTitulos(){
        return livros.stream().map(livro->livro.getTitulo()).toList();
    }

    public List<String> getAutores() {
    Set<String> autores = new HashSet<>();
    for (Livro l : livros) {
        autores.add(l.getAutor()); 
    }
    return new LinkedList<>(autores); 
}

    public List<Livro> getLivrosDoAutor(String autor){
        return livros.stream().filter(livro->livro.getAutor().equals(autor)).toList();
    }

    public List<Livro> getLivroByAno(Integer ano){
        return livros.stream().filter(livro->livro.getAno().equals(ano)).toList();
    }

    public List<Livro> getLivrosFromAutorByAno(String autor, int ano){
        return livros.stream().filter(livro -> livro.getAutor().equals(autor)).filter(livro -> livro.getAno() == ano).toList();
    }

    public List<Livro> getOutdated(int ano){
        return livros.stream().filter(livro -> livro.getAno() < ano).toList();
    }

    public int getNumberOfBook(String autor){
        return getLivrosDoAutor(autor).size();
    }

    public int getRecent(int ano){
        return (int) livros.stream().filter(livro -> livro.getAno() > ano).count();
    }

    public double averageBooksFromAutor() {
        long nroAutores = livros.stream().map(Livro::getAutor).distinct().count();
        if (nroAutores == 0) {
            return 0.0;  
        }
        return (double) livros.size() / nroAutores;
    }

    public boolean removeLivro(int id) {
        return livros.removeIf(livro -> livro.getId() == id);
    }
    
    public Livro getLivroTitulo(String titulo) {
        return livros.stream().filter(livro -> livro.getTitulo().equals(titulo)).findFirst().orElse(null); 
    }

    public boolean atualizarLivro(int id, Livro livroSetado) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                l.setTitulo(livroSetado.getTitulo());
                l.setAutor(livroSetado.getAutor());
                l.setAno(livroSetado.getAno());
                return true; 
            }
        }
        return false;
    }
}