package br.com.luciano.moneyapi.service;

import br.com.luciano.moneyapi.model.Usuario;
import br.com.luciano.moneyapi.repository.Usuarios;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario) {
        if(usuario.isNovo()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return this.usuarios.save(usuario);
    }

    public Usuario editar(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioOptional = this.usuarios.findById(id);

        if(!usuarioOptional.isPresent())
            throw new EmptyResultDataAccessException(1);

        Usuario usuarioSalvo = usuarioOptional.get();

        BeanUtils.copyProperties(usuario, usuarioSalvo, "id");

        return usuarios.save(usuarioSalvo);
    }

}
