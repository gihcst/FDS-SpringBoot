package com.bcopstein.endpointsdemo1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca")
public class DemoController {
    private List<Livro> livros;

    public DemoController() {
        livros = new ArrayList<>();
        livros.add(new Livro(1, "Crepúsculo", "Stephenie Meyer", 2005));
        livros.add(new Livro(2, "Cidades de Papel", "John Green", 2015));
        livros.add(new Livro(3, "Rainha Vermelha", "Victoria Aveyard", 2015));
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemInicial(){
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("/livros")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivros(){   //livros impressos com a biblio JACKSON que serializa os objetos em JSON. (ignora o toString())
        return livros;
    }

    /*public List<String> getLivros() {
        List<String> livrosFormatados = new ArrayList<>(); //livros impressos de acordo com o toString()
        for (Livro livro : livros) {
            livrosFormatados.add(livro.toString());
        }
        return livrosFormatados;
    }*/

    @GetMapping("/titulos")
    @CrossOrigin(origins= "*")
    public String getTitulos() {
        List<String> titulos = new ArrayList<String>();
        for (Livro l : livros){
            titulos.add(l.getTitulo());
        }
        return titulos.toString();
    }

    @GetMapping("/autores")
    @CrossOrigin(origins= "*")
    public String getAutores() {
        Set<String> autores = new HashSet<>(); //p nao ir repetidos usa-se um conjunto
        for (Livro l : livros) {
            autores.add(l.getAutor());
        }
        return autores.toString();
    }

    /****************Query String*******************/
    //usamos a decoração @RequestParam
    @GetMapping("/livrosautor")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivrosDoAutor(@RequestParam(value = "autor") String autor){
        return livros.stream().filter(livro->livro.getAutor().equals(autor)).toList();
    }

    /****************Path parameters*******************/
    @GetMapping("/livrosautor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoAutor(@PathVariable(value="autor") String autor, @PathVariable(value="ano") int ano){
        return livros.stream()
                     .filter(livro -> livro.getAutor().equals(autor))
                     .filter(livro -> livro.getAno() == ano)
                     .toList();
    }

    /****************Request Body*******************/
    @PostMapping("/novolivro")
    @CrossOrigin(origins= "*")
    public boolean adcLivroNovo(@RequestBody final Livro livro){
        livros.add(livro);
        return true;
    }

    /****************Response Entity*******************/
    @GetMapping("/getLivroByTitulo/{titulo}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroTitulo(@PathVariable("titulo") String titulo){
        Livro resp = livros.stream().filter(livro -> livro.getTitulo().equals(titulo)).findFirst().orElse(null);
        if (resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
