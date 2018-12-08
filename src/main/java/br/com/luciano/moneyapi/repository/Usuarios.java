package br.com.luciano.moneyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.moneyapi.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByEmail(String email);

}
