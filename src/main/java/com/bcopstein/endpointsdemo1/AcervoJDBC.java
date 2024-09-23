package com.bcopstein.endpointsdemo1;

import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AcervoJDBC implements IRepositoryAcervo {
    private JdbcTemplate jdbcTemplate;
    private int linhas = 0;

    // Construtor que recebe um JdbcTemplate injetado
    public AcervoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Cadastra um novo livro no banco de dados.
     * 
     * @param livro Objeto Livro contendo os dados do livro a ser inserido.
     * @return true se a inserção for bem-sucedida.
     */
    public boolean registerNewBook(Livro livro) {
        this.jdbcTemplate.update("INSERT INTO livros(id_livro, titulo, autor, ano) VALUES (?,?,?,?)", 
            livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno());
        return true;
    }

    /**
     * Retorna todos os livros cadastrados no banco de dados.
     * 
     * @return Lista de objetos Livro representando todos os registros.
     */
    public List<Livro> getAll() {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros", 
            (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")));
        return resp;
    }

    /**
     * Retorna os títulos de todos os livros cadastrados.
     * 
     * @return Lista de strings contendo os títulos.
     */
    public List<String> getTitles() {
        return this.jdbcTemplate.queryForList("SELECT titulo FROM livros", String.class);
    }

    /**
     * Retorna todos os autores distintos cadastrados no banco de dados.
     * 
     * @return Lista de strings contendo os autores.
     */
    public List<String> getAuthors() {
        return this.jdbcTemplate.queryForList("SELECT DISTINCT autor FROM livros", String.class);
    }

    /**
     * Retorna todos os livros de um determinado autor.
     * 
     * @param autor O nome do autor.
     * @return Lista de objetos Livro escritos pelo autor.
     */
    public List<Livro> getAuthorsbooks(String autor) {
        return this.jdbcTemplate.query("SELECT * FROM livros WHERE autor = ?", 
            (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")), 
            autor);
    }

    /**
     * Retorna todos os livros publicados em um determinado ano.
     * 
     * @param ano O ano de publicação.
     * @return Lista de objetos Livro.
     */
    public List<Livro> getBookbyYear(Integer ano) {
        return this.jdbcTemplate.query("SELECT * FROM livros WHERE ano = ?", 
            (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")), 
            ano);
    }

    /**
     * Retorna todos os livros de um autor específico em um determinado ano.
     * 
     * @param autor O nome do autor.
     * @param ano O ano de publicação.
     * @return Lista de objetos Livro.
     */
    public List<Livro> getBooksFromAuthorByYear(String autor, int ano) {
        return this.jdbcTemplate.query("SELECT * FROM livros WHERE autor = ? AND ano = ?", 
            (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")), 
            autor, ano);
    }

    /**
     * Retorna todos os livros publicados antes de um determinado ano.
     * 
     * @param ano O ano limite.
     * @return Lista de objetos Livro publicados antes do ano especificado.
     */
    public List<Livro> getOutdated(int ano) {
        return this.jdbcTemplate.query("SELECT * FROM livros WHERE ano < ?", 
            (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")), 
            ano);
    }

    /**
     * Retorna o número de livros de um determinado autor.
     * 
     * @param autor O nome do autor.
     * @return O número de livros escritos pelo autor.
     */
    public int getNumberOfBook(String autor) {
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM livros WHERE autor = ?", Integer.class, autor);
    }

    /**
     * Retorna o número de livros publicados após um determinado ano.
     * 
     * @param ano O ano de referência.
     * @return O número de livros publicados após o ano especificado.
     */
    public int getRecent(int ano) {
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM livros WHERE ano > ?", Integer.class, ano);
    }

    /**
     * Calcula a média de livros por autor.
     * 
     * @return A média de livros por autor, ou 0.0 se não houver autores.
     */
    @SuppressWarnings("null")
    public Double averageBooksFromAuthors() {
        Integer totalLivros = this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM livros", Integer.class);
        Integer nroAutores = this.jdbcTemplate.queryForObject("SELECT COUNT(DISTINCT autor) FROM livros", Integer.class);

        if (nroAutores == 0) {
            return 0.0;
        }
        return (double) totalLivros / nroAutores;
    }

    /**
     * Remove um livro do banco de dados pelo ID.
     * 
     * @param id O ID do livro a ser removido.
     * @return true se a remoção for bem-sucedida.
     */
    public boolean removeBook(int id) {
        this.jdbcTemplate.update("DELETE FROM livros WHERE id = ?", id);
        return true;
    }

    /**
     * Retorna um livro com base no seu título.
     * 
     * @param titulo O título do livro.
     * @return O objeto Livro correspondente ao título.
     */
    public Livro getBookTitle(String titulo) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM livros WHERE titulo = ?", 
        (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")), titulo);
    }

    /**
     * Atualiza as informações de um livro no banco de dados.
     * 
     * @param id O ID do livro a ser atualizado.
     * @param livroSetado O objeto Livro com os novos dados.
     * @return true se a atualização for bem-sucedida.
     */
    public boolean updateBook(int id, Livro livroSetado) {
        this.jdbcTemplate.update("UPDATE livros SET titulo = ?, autor = ?, ano = ? WHERE id_livro = ?", livroSetado.getTitulo(), livroSetado.getAutor(), livroSetado.getAno(), id);
        return true;
    }

    /**
     * Realiza o empréstimo de um livro para um usuário.
     * 
     * @param id_livro O ID do livro a ser emprestado.
     * @param codigoUser O código do usuário que está emprestando o livro.
     * @return true se o empréstimo for bem-sucedido.
     */
    public boolean lendBook(int id_livro, int codigoUser) {
        linhas = this.jdbcTemplate.update("UPDATE livros SET codigoUser = ? WHERE id_livro = ?",
            codigoUser, id_livro);
        return linhas > 0;
    }

    /**
     * Realiza a devolução de um livro.
     * 
     * @param id O ID do livro a ser devolvido.
     * @return true se a devolução for bem-sucedida.
     */
    public boolean returnBook(int id) {
        linhas = this.jdbcTemplate.update("UPDATE livros SET codigoUser = -1 WHERE id_livro = ?", id);
        return linhas > 0;
    }

    /**
     * Lista todos os livros que estão atualmente emprestados.
     * 
     * @return Lista de objetos Livro que estão emprestados.
     */
    public List<Livro> listLendBooks(int codigoUser) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * FROM livros WHERE codigoUser = ?", 
            (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")), codigoUser);
        return resp;
    }

    /**
     * Lista todos os livros que estão disponíveis na biblioteca.
     * 
     * @return Lista de objetos Livro que estão livres.
     */
    public List<Livro> listFreeBooks() {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * FROM livros WHERE codigoUser = -1", 
            (rs, rowNum) -> new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getInt("codigoUser")));
        return resp;
    }
}
