package br.com.luciano.moneyapi.repository;

import br.com.luciano.moneyapi.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Grupos extends JpaRepository<Grupo, Integer> {
}
