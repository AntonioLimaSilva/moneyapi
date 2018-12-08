package br.com.luciano.moneyapi.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luciano.moneyapi.exceptionhandler.MoneyResponseEntityExceptionHandler.Erro;
import br.com.luciano.moneyapi.model.Lancamento;
import br.com.luciano.moneyapi.repository.Lancamentos;
import br.com.luciano.moneyapi.repository.filter.LancamentoFilter;
import br.com.luciano.moneyapi.repository.projection.ResumoLancamento;
import br.com.luciano.moneyapi.service.LancamentoService;
import br.com.luciano.moneyapi.service.exception.PessoaNaoExistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	public MessageSource messageSource;
	
	@Autowired
	private Lancamentos lancamentos;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Lancamento> pesquisar(LancamentoFilter filtro, @PageableDefault(size = 3) Pageable pageable) {
		
		return this.lancamentos.filtrar(filtro, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter filtro, @PageableDefault(size = 3) Pageable pageable) {
		
		return this.lancamentos.resumir(filtro, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Lancamento> buscarPorId(@PathVariable Integer id) {
		Optional<Lancamento> lancamentoOptional = this.lancamentos.findLancamentoById(id);
		
		return lancamentoOptional.isPresent() ? ResponseEntity.ok(lancamentoOptional.get()) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Lancamento> inserir(@Valid @RequestBody Lancamento lancamento) {
		Lancamento lancamentoSalvo = this.lancamentoService.salvar(lancamento);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(lancamentoSalvo.getId()).toUri()).body(lancamentoSalvo);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Lancamento> editar(@PathVariable Integer id, @RequestBody @Valid Lancamento lancamento) {
		Lancamento lancamentoSalvo = this.lancamentoService.editar(id, lancamento);
		
		return ResponseEntity.ok(lancamentoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	public void excluir(@PathVariable Integer id) {
		
		this.lancamentos.deleteById(id);
	}
	
	@ExceptionHandler({ PessoaNaoExistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaNaoExistenteOuInativaException(PessoaNaoExistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("recurso.pessoa-nao-existente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
				
		return ResponseEntity.badRequest().body(erros);
	}
	
}
