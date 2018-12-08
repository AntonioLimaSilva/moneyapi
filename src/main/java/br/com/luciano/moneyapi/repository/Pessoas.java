package br.com.luciano.moneyapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.moneyapi.model.Pessoa;

@Repository
public interface Pessoas extends JpaRepository<Pessoa, Integer> {

	Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);

}
