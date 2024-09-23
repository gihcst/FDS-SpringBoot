package com.bcopstein.endpointsdemo1;

/**
 * Classe que representa um usuário do sistema.
 */
public class Usuario {
    private String nome;
    private int codigo;
    private int anoDeNascimento;

    /**
     * Construtor que inicializa um novo objeto Usuario.
     * 
     * @param nome O nome do usuário.
     * @param codigo O código único do usuário.
     * @param anoDeNascimento O ano de nascimento do usuário.
     */
    public Usuario(String nome, int codigo, int anoDeNascimento) {
        this.nome = nome;
        this.codigo = codigo;
        this.anoDeNascimento = anoDeNascimento;
    }

    /**
     * Retorna o nome do usuário.
     * 
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o código do usuário.
     * 
     * @return O código do usuário.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna o ano de nascimento do usuário.
     * 
     * @return O ano de nascimento do usuário.
     */
    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    /**
     * Atualiza o código do usuário.
     * 
     * @param codigo O novo código do usuário.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Atualiza o nome do usuário.
     * 
     * @param nome O novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Atualiza o ano de nascimento do usuário.
     * 
     * @param anoDeNascimento O novo ano de nascimento do usuário.
     */
    public void setAnoDeNascimento(int anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    /**
     * Retorna uma representação em string do objeto Usuario.
     * 
     * @return A string que representa o usuário.
     */
    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", codigo=" + codigo + ", anoDeNascimento=" + anoDeNascimento + "]";
    }
}
