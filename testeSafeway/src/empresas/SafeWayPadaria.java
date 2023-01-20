package empresas;

import java.math.BigDecimal;

import model.Empresa;
import model.Taxa;

public class SafeWayPadaria extends Empresa implements Taxa{
	
	private Long id;
	private String nome = "SafeWay Padaria";
	private String cnpj = "30021423000159";
	private BigDecimal taxa;
	private BigDecimal saldo = new BigDecimal(0);
	
	public SafeWayPadaria(Long id) {
		this.defineTaxaParaEmpresa();
		this.id = id;
	}
	
	@Override
	public void defineTaxaParaEmpresa() {
		setTaxa(new BigDecimal(0.15));
	}
				
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	

	
	
	
	
}
