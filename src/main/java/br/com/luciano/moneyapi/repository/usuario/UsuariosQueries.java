package br.com.luciano.moneyapi.repository.usuario;

import br.com.luciano.moneyapi.model.Usuario;

import java.util.List;

public interface UsuariosQueries {

    public List<String> permissoes(Usuario usuario);

}
