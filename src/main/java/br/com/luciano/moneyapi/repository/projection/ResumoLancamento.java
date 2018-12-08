package br.com.luciano.moneyapi.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.luciano.moneyapi.model.TipoLancamento;

public class ResumoLancamento {

	private Integer id;
	private String descricao;
	private LocalDate dataPagamento;
	private LocalDate dataVencimento;
	private BigDecimal valor;
	private TipoLancamento tipo;
	private String categoria;
	private String pessoa;

	public ResumoLancamento(Integer id, String descricao, LocalDate dataPagamento, LocalDate dataVencimento,
			BigDecimal valor, TipoLancamento tipo, String categoria, String pessoa) {
		this.id = id;
		this.descricao = descricao;
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.tipo = tipo;
		this.categoria = categoria;
		this.pessoa = pessoa;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getPessoa() {
		return pessoa;
	}

}
