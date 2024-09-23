package com.bcopstein.endpointsdemo1;

import java.util.List;

// Interface que define os métodos para operações de acesso a dados relacionados a usúarios
public interface IRepositoryUsuario {
    Usuario getUser(int codigo);
    boolean registerNewUser(Usuario usuario);
    boolean removeUser(int codigo);
    List<Usuario> getAllUsers();
}
