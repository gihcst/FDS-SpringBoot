package com.bcopstein.endpointsdemo1;

import java.util.List;

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
    private Acervo acervo;

    public DemoController(Acervo acervo) {
        this.acervo = acervo;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemInicial(){
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("/livros")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivros(){   //livros impressos com a biblio JACKSON que serializa os objetos em JSON. (ignora o toString())
        return acervo.getAll();
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
    public List<Livro> getTitulos() {
        return acervo.getAll();
    }

    @GetMapping("/autores")
    @CrossOrigin(origins= "*")
    public String getAutores() {
        return acervo.getAutores();
    }

    /****************Query String*******************/
    //usamos a decoração @RequestParam
    @GetMapping("/livrosautor")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivrosDoAutor(@RequestParam(value = "autor") String autor){
        return acervo.getLivrosFromAutor(autor);    
    }
    
    @GetMapping("/livroByAno")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivroByAno(@RequestParam(value = "ano") Integer ano){
        return acervo.getLivroByAno(ano); 
    }

    /****************Path parameters*******************/
    @GetMapping("/livrosautor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoAutor(@PathVariable(value="autor") String autor, @PathVariable(value="ano") int ano){
        return acervo.getLivrosFromAutorByAno(autor, ano); 
    }

    @GetMapping("/outdated/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getOutdated(@PathVariable(value="ano") int ano){
        return acervo.getOutdated(ano);
    }

    /****************Request Body*******************/
    @PostMapping("/novolivro")
    @CrossOrigin(origins= "*")
    public ResponseEntity<Boolean> adcLivroNovo(@RequestBody final Livro livro){
        boolean adicionado = acervo.adcLivro(livro);
        if (adicionado) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    /****************Response Entity*******************/
    @GetMapping("/getLivroByTitulo/{titulo}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroTitulo(@PathVariable("titulo") String titulo){
        Livro resp = acervo.getAll().stream().filter(livro -> livro.getTitulo().equals(titulo)).findFirst().orElse(null);
        if (resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    
    @PostMapping("/setLivro/{id}")
    @CrossOrigin(origins= "*")
    public ResponseEntity<Boolean> setLivro(@PathVariable("id") int id, @RequestBody Livro livroSetado){
        for (Livro l : acervo.getAll()) {
            if (l.getId() == id) {
                l.setTitulo(livroSetado.getTitulo());
                l.setAutor(livroSetado.getAutor());
                l.setAno(livroSetado.getAno());
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
    }
}