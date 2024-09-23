package com.bcopstein.endpointsdemo1;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJDBC implements IRepositoryUsuario {
    private JdbcTemplate jdbcTemplate;

    public UsuarioJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Usuario getUser(int codigo) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM usuario WHERE codigo = ?",
            (rs, rowId) ->
                new Usuario(rs.getString("name"),
                rs.getInt("codigo"),
                rs.getInt("anoDeNascimento")));
    }

    @Override
    public boolean registerNewUser(Usuario usuario) {
        return this.jdbcTemplate.update("INSERT INTO usuario(codigo, nome, anoDeNascimento) VALUES (?,?,?)",
            usuario.getCodigo(), usuario.getNome(), usuario.getAnoDeNascimento()) > 0;
    }

    @Override
    public boolean removeUser(int codigo) {
        return this.jdbcTemplate.update(
            "DELETE FROM usuario WHERE codigo = ?", 
            codigo
        ) > 0;
    }

    @Override
    public List<Usuario> getAllUsers() {
        return this.jdbcTemplate.query(
            "SELECT * FROM usuario",
            (rs, rowNum) -> new Usuario(
                rs.getString("nome"),
                rs.getInt("codigo"),
                rs.getInt("anoDeNascimento")
            )
        );
    }
}