# Biblioteca Central API

Este projeto é um programa que gerencia uma biblioteca, utilizando Spring Boot e JDBC. O sistema permite operações como adicionar, remover e atualizar livros, além de consultar informações sobre autores e obras.

## Estrutura do Projeto

### Classes e Interfaces

1. **`Livro`**
   - Representa um livro com atributos como ID, título, autor e ano de publicação.
   - Métodos:
     - `getId()`: Retorna o ID do livro.
     - `getTitulo()`: Retorna o título do livro.
     - `getAutor()`: Retorna o autor do livro.
     - `getAno()`: Retorna o ano de publicação.
     - Métodos setter para modificar os atributos.
     - `toString()`: Retorna uma representação formatada do livro.

2. **`IRepository`**
   - Interface que define métodos para operações de acesso a dados relacionados a livros.
   - Métodos principais incluem:
     - `getAll()`: Retorna todos os livros.
     - `registerNewBook(Livro livro)`: Adiciona um novo livro.
     - `removeBook(int id)`: Remove um livro pelo ID.
     - `getAuthorsbooks(String autor)`: Retorna livros de um autor específico.

3. **`Acervo`**
   - Implementação da interface `IRepository`, utilizando `JdbcTemplate` para interagir com o banco de dados.
   - Métodos incluem:
     - `getAll()`: Consulta todos os livros no banco de dados.
     - `averageBooksFromAuthors()`: Calcula a média de livros por autor.
     - `getBookTitle(String titulo)`: Busca um livro pelo título.
     - Outros métodos para obter livros por ano, autor, ou verificar disponibilidade.

4. **`DemoController`**
   - Controlador REST que expõe endpoints para interagir com a API.
   - Endpoints principais:
     - `GET /biblioteca/livros`: Retorna todos os livros.
     - `POST /biblioteca/novolivro`: Adiciona um novo livro.
     - `GET /biblioteca/livrosAutor?autor=NomeDoAutor`: Retorna livros de um autor específico.
     - `GET /biblioteca/getLivroPorTitulo/{titulo}`: Retorna um livro pelo título.
     - `GET /biblioteca/livrosEmprestados`: Retorna todos os livros emprestados.
     - `GET /biblioteca/livrosDisponiveis`: Retorna todos os livros disponíveis.

5. **`Usuario`**
   - Representa um usuário com atributos como nome, código e ano de nascimento.
   - Métodos:
     - `getNome()`: Retorna o nome do usuário.
     - `getCodigo()`: Retorna o código do usuário.
     - `getAnoDeNascimento()`: Retorna o ano de nascimento do usuário.
     - Métodos setter para modificar os atributos.
     - `toString()`: Retorna uma representação formatada do usuário.

6. **`IRepositoryUsuario`**
   - Interface que define métodos para operações de acesso a dados relacionadas a usuários.
   - Métodos principais incluem:
     - `getUser(int codigo)`: Retorna um usuário pelo código.
     - `registerNewUser(Usuario usuario)`: Adiciona um novo usuário.
     - `removeUser(int codigo)`: Remove um usuário pelo código.
     - `getAllUsers()`: Retorna todos os usuários.

7. **`UsuarioJDBC`**
   - Implementação da interface `IRepositoryUsuario`, utilizando `JdbcTemplate` para interagir com o banco de dados.
   - Métodos incluem:
     - `getUser(int codigo)`: Busca um usuário pelo código.
     - `registerNewUser(Usuario usuario)`: Adiciona um novo usuário ao banco de dados.
     - `removeUser(int codigo)`: Remove um usuário pelo código.
     - `getAllUsers()`: Retorna todos os usuários cadastrados.

8. **`Endpointsdemo1Application`**
   - Classe principal que inicia a aplicação Spring Boot.

## Dependências

- **Spring Boot**: Framework utilizado para construir a API.
- **JDBC**: Para interação com o banco de dados.

## Como Executar

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute o comando:
   ```bash
   mvn spring-boot:run
4. A API estará disponível em http://localhost:8080.