package br.com.luciano.moneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luciano.moneyapi.model.Lancamento;
import br.com.luciano.moneyapi.model.Pessoa;
import br.com.luciano.moneyapi.repository.Lancamentos;
import br.com.luciano.moneyapi.repository.Pessoas;
import br.com.luciano.moneyapi.service.exception.PessoaNaoExistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	public Lancamentos lancamentos;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoaExistente = this.pessoas.getOne(lancamento.getPessoa().getId());
		
		if(pessoaExistente == null || pessoaExistente.isInativa()) {
			throw new PessoaNaoExistenteOuInativaException();
		}
		
		return this.lancamentos.save(lancamento);
	}

	public Lancamento editar(Integer id, Lancamento lancamento) {
		Lancamento lancamentoSalvo = this.lancamentos.getOne(id);
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
		
		return salvar(lancamentoSalvo);
	}

}
