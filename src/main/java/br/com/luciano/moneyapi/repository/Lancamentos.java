package br.com.luciano.moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.moneyapi.model.Lancamento;
import br.com.luciano.moneyapi.repository.lancamento.LancamentosQueries;

@Repository
public interface Lancamentos extends JpaRepository<Lancamento, Integer>, LancamentosQueries {	



}
