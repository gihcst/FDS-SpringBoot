package com.bcopstein.endpointsdemo1;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Classe que implementa a interface IRepositoryUsuario 
 * para gerenciar operações relacionadas a usuários no banco de dados.
 */
@Repository
public class UsuarioJDBC implements IRepositoryUsuario {
    private JdbcTemplate jdbcTemplate;

    /**
     * Construtor que recebe um JdbcTemplate injetado.
     * 
     * @param jdbcTemplate O JdbcTemplate usado para acessar o banco de dados.
     */
    public UsuarioJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retorna um usuário com base no seu código.
     * 
     * @param codigo O código do usuário.
     * @return O objeto Usuario correspondente ao código fornecido.
     */
    @Override
    public Usuario getUser(int codigo) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM usuario WHERE codigo = ?",
            (rs, rowId) -> new Usuario(rs.getString("nome"), rs.getInt("codigo"), rs.getInt("anodenascimento")), codigo);
    }

    /**
     * Cadastra um novo usuário no banco de dados.
     * 
     * @param usuario O objeto Usuario contendo os dados do novo usuário.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean registerNewUser(Usuario usuario) {
        return this.jdbcTemplate.update("INSERT INTO usuario(codigo, nome, anoDeNascimento) VALUES (?,?,?)",
            usuario.getCodigo(), usuario.getNome(), usuario.getAnoDeNascimento()) > 0;
    }

    /**
     * Remove um usuário do banco de dados pelo código.
     * 
     * @param codigo O código do usuário a ser removido.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean removeUser(int codigo) {
        return this.jdbcTemplate.update(
            "DELETE FROM usuario WHERE codigo = ?", codigo) > 0;
    }

    /**
     * Retorna todos os usuários cadastrados no banco de dados.
     * 
     * @return Lista de objetos Usuario representando todos os registros.
     */
    @Override
    public List<Usuario> getAllUsers() {
        return this.jdbcTemplate.query("SELECT * FROM usuario", 
            (rs, rowNum) -> new Usuario(rs.getString("nome"), rs.getInt("codigo"), rs.getInt("anoDeNascimento")));
    }
}
