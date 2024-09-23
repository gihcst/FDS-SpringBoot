package com.bcopstein.endpointsdemo1;

import java.util.List;

public interface IRepositoryUsuario {
    Usuario getUser(int codigo);
    boolean registerNewUser(Usuario usuario);
    boolean removeUser(int codigo);
    List<Usuario> getAllUsers();
}
