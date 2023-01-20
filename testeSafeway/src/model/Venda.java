package model;

import java.math.BigDecimal;
import java.util.List;

public class Venda {
	private Long código;
	private List<Produto> itens;
	private BigDecimal valor;
	private BigDecimal comissaoSistema;
	private Empresa empresa;
	private Cliente cliente;

	public Venda(Long código, List<Produto> itens, BigDecimal valor, BigDecimal comissaoSistema, Empresa empresa, Cliente cliente) {
		this.código = código;
		this.itens = itens;
		this.valor = valor;
		this.comissaoSistema = comissaoSistema;
		this.empresa = empresa;
		this.cliente = cliente;
	}

	public Venda() {
		super();
	}

	public Long getCódigo() {
		return código;
	}
	
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCódigo(Long código) {
		this.código = código;
	}

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getComissaoSistema() {
		return comissaoSistema;
	}

	public void setComissaoSistema(BigDecimal comissaoSistema) {
		this.comissaoSistema = comissaoSistema;
	}

}
