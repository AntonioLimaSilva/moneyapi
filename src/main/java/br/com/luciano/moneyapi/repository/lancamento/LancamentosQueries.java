package br.com.luciano.moneyapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.moneyapi.model.Lancamento;
import br.com.luciano.moneyapi.repository.filter.LancamentoFilter;
import br.com.luciano.moneyapi.repository.projection.ResumoLancamento;

import java.util.Optional;

public interface LancamentosQueries {

	Optional<Lancamento> findLancamentoById(Integer id);
	
	Page<Lancamento> filtrar(LancamentoFilter filtro, Pageable pageable);
	
	Page<ResumoLancamento> resumir(LancamentoFilter filtro, Pageable pageable);

}
