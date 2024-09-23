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
    private AcervoJDBC acervo;

    // Construtor que recebe uma instância de Acervo injetada
    public DemoController(AcervoJDBC acervo) {
        this.acervo = acervo;
    }

    /**
     * Mensagem de boas-vindas à biblioteca.
     * 
     * @return Mensagem de boas-vindas.
     */
    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemInicial() {
        return "Bem vindo a biblioteca central!";
    }

    /**
     * Retorna todos os livros cadastrados na biblioteca.
     * 
     * @return Lista de objetos Livro.
     */
    @GetMapping("/livros")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivros() {  
        return acervo.getAll();
    }

    /**
     * Retorna os títulos de todos os livros cadastrados.
     * 
     * @return Lista de objetos Livro.
     */
    @GetMapping("/titulos")
    @CrossOrigin(origins= "*")
    public List<Livro> getTitulos() {
        return acervo.getAll();
    }

    /**
     * Retorna todos os autores distintos cadastrados.
     * 
     * @return Lista de strings contendo os nomes dos autores.
     */
    @GetMapping("/autores")
    @CrossOrigin(origins= "*")
    public List<String> getAutores() {
        return acervo.getAuthors();
    }

    /**
     * Retorna a média de livros por autor.
     * 
     * @return A média de livros por autor.
     */
    @GetMapping("/mediaDeLivros")
    @CrossOrigin(origins= "*")
    public Double averageBooks() {
        return acervo.averageBooksFromAuthors();
    }

    /****************Query String*******************/

    /**
     * Retorna todos os livros de um autor específico utilizando query parameter.
     * 
     * @param autor O nome do autor.
     * @return Lista de objetos Livro escritos pelo autor.
     */
    @GetMapping("/livrosAutor")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivrosDoAutor(@RequestParam(value = "autor") String autor) {
        return acervo.getAuthorsbooks(autor);    
    }
    
    /**
     * Retorna todos os livros publicados em um determinado ano utilizando query parameter.
     * 
     * @param ano O ano de publicação.
     * @return Lista de objetos Livro publicados no ano especificado.
     */
    @GetMapping("/livroPorAno")
    @CrossOrigin(origins= "*")
    public List<Livro> getLivroByAno(@RequestParam(value = "ano") Integer ano) {
        return acervo.getBookbyYear(ano); 
    }

    /**
     * Retorna a quantidade de livros publicados após um determinado ano.
     * 
     * @param ano O ano de referência.
     * @return Número de livros publicados após o ano especificado.
     */
    @GetMapping("/getRecentes")
    @CrossOrigin(origins= "*")
    public int getRecentCount(@RequestParam(value = "ano") int ano) {
        return acervo.getRecent(ano);
    }

    /****************Path parameters*******************/

    /**
     * Retorna todos os livros de um autor específico em um determinado ano utilizando path parameters.
     * 
     * @param autor O nome do autor.
     * @param ano O ano de publicação.
     * @return Lista de objetos Livro.
     */
    @GetMapping("/livrosautor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoAutor(@PathVariable(value = "autor") String autor, @PathVariable(value = "ano") int ano) {
        return acervo.getBooksFromAuthorByYear(autor, ano); 
    }

    /**
     * Retorna todos os livros publicados antes de um determinado ano utilizando path parameter.
     * 
     * @param ano O ano limite.
     * @return Lista de objetos Livro publicados antes do ano especificado.
     */
    @GetMapping("/desatualizados/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getOutdated(@PathVariable(value = "ano") int ano) {
        return acervo.getOutdated(ano);
    }

    /**
     * Retorna o número de obras de um autor específico utilizando path parameter.
     * 
     * @param autor O nome do autor.
     * @return Número de livros escritos pelo autor.
     */
    @GetMapping("/nroDeObras/autor/{autor}")
    @CrossOrigin(origins = "*")
    public int getNroDeObras(@PathVariable(value = "autor") String autor) {
        return acervo.getNumberOfBook(autor);
    }

    /****************Request Body*******************/

    /**
     * Adiciona um novo livro à biblioteca.
     * 
     * @param livro Objeto Livro a ser adicionado.
     * @return Resposta com status de criação.
     */
    @PostMapping("/novolivro")
    @CrossOrigin(origins= "*")
    public ResponseEntity<Boolean> adcLivroNovo(@RequestBody final Livro livro) {
        boolean adicionado = acervo.registerNewBook(livro);
        if (adicionado) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    /****************Response Entity*******************/

    /**
     * Retorna um livro baseado no seu título.
     * 
     * @param titulo O título do livro.
     * @return Resposta com o livro ou status 404 se não encontrado.
     */
    @GetMapping("/getLivroPorTitulo/{titulo}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getBookTitle(@PathVariable("titulo") String titulo) {
        Livro resp = acervo.getBookTitle(titulo);
        if (resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    /**
     * Atualiza as informações de um livro existente pelo ID.
     * 
     * @param id O ID do livro a ser atualizado.
     * @param livroSetado Objeto Livro com os novos dados.
     * @return Resposta com status de sucesso ou 404 se não encontrado.
     */
    @PostMapping("/setLivro/{id}")
    @CrossOrigin(origins= "*")
    public ResponseEntity<Boolean> setLivro(@PathVariable("id") int id, @RequestBody Livro livroSetado) {
        boolean att = acervo.updateBook(id, livroSetado);
        if (att) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
