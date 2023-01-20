package main;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import empresas.LevelVarejo;
import empresas.SafeWayPadaria;
import empresas.SafeWayRestaurante;
import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;
import sistema.RegrasNegocio;

public class Main {

	public static void main(String[] args) {
		
		// SIMULANDO BANCO DE DADOS
		
		List<Produto> carrinho = new ArrayList<Produto>();
		List<Venda> vendas = new ArrayList<Venda>();

		Empresa empresa = new SafeWayPadaria(1l);
		Empresa empresa2 = new LevelVarejo(2l);
		Empresa empresa3 = new SafeWayRestaurante(3l);

		Produto produto = new Produto(1, "Pão Francês", 5, new BigDecimal(3.50), empresa);
		Produto produto2 = new Produto(2, "Sonho", 5, new BigDecimal(8.50), empresa);
		Produto produto3 = new Produto(3, "Croissant", 7, new BigDecimal(6.50), empresa);
		Produto produto4 = new Produto(4, "Chá Gelado", 4, new BigDecimal(5.50), empresa);		
		Produto produto5 = new Produto(5, "Coturno", 10, new BigDecimal(400.0), empresa2);
		Produto produto6 = new Produto(6, "Jaqueta Jeans", 15, new BigDecimal(150.0), empresa2);
		Produto produto7 = new Produto(7, "Calça Sarja", 15, new BigDecimal(150.0), empresa2);
		Produto produto8 = new Produto(8, "Prato feito - Frango", 10, new BigDecimal(25.0), empresa3);
		Produto produto9 = new Produto(9, "Prato feito - Carne", 10, new BigDecimal(25.0), empresa3);
		Produto produto10 = new Produto(10, "Suco Natural", 30, new BigDecimal(10.0), empresa3);


		Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
		Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);

		Usuario usuario1 = new Usuario("admin", "1234", null, null);
		Usuario usuario2 = new Usuario("empresa", "1234", null, empresa);
		Usuario usuario3 = new Usuario("cliente", "1234", cliente, null);
		Usuario usuario4 = new Usuario("cliente2", "1234", cliente2, null);
		Usuario usuario5 = new Usuario("empresa2", "1234", null, empresa2);
		Usuario usuario6 = new Usuario("empresa3", "1234", null, empresa3);


		List<Usuario> usuarios = Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6);
		List<Cliente> clientes = Arrays.asList(cliente, cliente2);
		List<Empresa> empresas = Arrays.asList(empresa, empresa2, empresa3);
		List<Produto> produtos = Arrays.asList(produto, produto2, produto3, produto4, produto5, produto6, produto7,
				produto8, produto9, produto10);
		
		
		RegrasNegocio.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
	}
}
