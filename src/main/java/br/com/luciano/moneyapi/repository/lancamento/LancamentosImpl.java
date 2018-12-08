package br.com.luciano.moneyapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.luciano.moneyapi.model.Lancamento;
import br.com.luciano.moneyapi.repository.filter.LancamentoFilter;
import br.com.luciano.moneyapi.repository.projection.ResumoLancamento;

public class LancamentosImpl implements LancamentosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Lancamento> findLancamentoById(Integer id) {
		String query = "select l from Lancamento l inner join fetch l.pessoa p inner join fetch l.categoria c where l.id = :id";

		return this.manager.createQuery(query, Lancamento.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findAny();
	}

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = this.manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filtro));
	}
	
	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		criteria.select(builder.construct(ResumoLancamento.class, 
				root.get("id"),
				root.get("descricao"),
				root.get("dataPagamento"),
				root.get("dataVencimento"),
				root.get("valor"),
				root.get("tipo"),
				root.get("categoria").get("nome"),
				root.get("pessoa").get("nome")));
		
		Predicate predicates[] = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoLancamento> query = this.manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
			
		return new PageImpl<>(query.getResultList(), pageable, total(filtro));
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<? extends Object> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalResgistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalResgistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalResgistroPorPagina);
	}

	private Long total(LancamentoFilter filtro) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(LancamentoFilter filtro, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(filtro.getDescricao() != null) {
			predicates.add(builder.like(builder.lower(root.get("descricao")), "%" + filtro.getDescricao() + "%"));
		}
		
		if(filtro.getDataVencimentoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), filtro.getDataVencimentoDe()));
		}
		
		if(filtro.getDataVencimentoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), filtro.getDataVencimentoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
