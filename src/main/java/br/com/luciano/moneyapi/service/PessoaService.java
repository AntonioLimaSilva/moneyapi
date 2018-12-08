package br.com.luciano.moneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.luciano.moneyapi.model.Pessoa;
import br.com.luciano.moneyapi.repository.Pessoas;

import java.util.Optional;

@Service
public class PessoaService {
	
	@Autowired
	private Pessoas pessoas;
	
	public Pessoa editar(Integer id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPorId(id);
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		
		return this.pessoas.save(pessoaSalva);
	}


	public void editarPropriedadeAtivo(Integer id, Boolean ativo) {
		Pessoa pessoaSalva = buscarPorId(id);
		
		pessoaSalva.setAtivo(ativo);
		
		this.pessoas.save(pessoaSalva);
	}
	
	private Pessoa buscarPorId(Integer id) {
		Optional<Pessoa> pessoaOptional = this.pessoas.findById(id);
		
		if(!pessoaOptional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return pessoaOptional.get();
	}

}
