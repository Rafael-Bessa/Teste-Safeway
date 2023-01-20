package sistema;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

public final class RegrasNegocio {

	public static void executar(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Entre com seu usuário e senha:");
		System.out.print("Usuário: ");
		String username = sc.next();
		System.out.print("Senha: ");
		String senha = sc.next();
	
		
		List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
				.collect(Collectors.toList());
		
		if (usuariosSearch.size() > 0) {
			Usuario usuarioLogado = usuariosSearch.get(0);
			if ((usuarioLogado.getSenha().equals(senha))) {

				System.out.println("Escolha uma opção para iniciar");
// Parte ajustada
				
				if (usuarioLogado.IsEmpresa()) {
					System.out.println("1 - Listar vendas");
					System.out.println("2 - Ver produtos");
					System.out.println("0 - Deslogar");
					Integer escolha = sc.nextInt();

					switch (escolha) {
					case 1: {
						System.out.println();
						System.out.println("************************************************************");
						System.out.println("VENDAS EFETUADAS");
						vendas.stream().forEach(venda -> {
							if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
								System.out.println("************************************************************");
								System.out.println("Venda de código: " + venda.getCódigo() + " no CPF "
										+ venda.getCliente().getCpf() + ": ");
								venda.getItens().stream().forEach(x -> {
									System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
								});
								System.out.println("Total Venda: R$ " + venda.getValor());
								System.out.println("Total Taxa a ser paga: R$ "
										+ venda.getComissaoSistema().setScale(2, RoundingMode.HALF_EVEN));
								System.out.println("Total Líquido  para empresa: R$ " + (venda.getValor()
										.subtract(venda.getComissaoSistema()).setScale(2, RoundingMode.HALF_DOWN)));
								System.out.println("************************************************************");
															
							}

						});
// Parte ajustada									
						BigDecimal taxa = new BigDecimal(1).subtract(usuarioLogado.getEmpresa().getTaxa());
						
						System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo().multiply(taxa).setScale(2, RoundingMode.HALF_DOWN));
						System.out.println("************************************************************");

						executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
					}
					case 2: {
						System.out.println();
						System.out.println("************************************************************");
						System.out.println("MEUS PRODUTOS");
						produtos.stream().forEach(produto -> {
							if (produto.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
								System.out.println("************************************************************");
								System.out.println("Código: " + produto.getId());
								System.out.println("Produto: " + produto.getNome());
								System.out.println("Quantidade em estoque: " + produto.getQuantidade());
								System.out.println("Valor: R$" + produto.getPreco());
								System.out.println("************************************************************");
							}

						});
						System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
						System.out.println("************************************************************");

						executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
					}
					case 0: {
						executar(usuarios, clientes, empresas, produtos, carrinho, vendas);

					}
					}
					
				} 
				
				
				if(usuarioLogado.IsAdmin()) {
					
					System.out.println("Empresas e seus dados");
					System.out.println("************************************************************");
					for (Empresa empresa : empresas) {					
						System.out.println("Nome da Empresa: " + empresa.getNome());
						System.out.println("Saldo da Empresa sem taxa: " + empresa.getSaldo().setScale(2, RoundingMode.HALF_DOWN));
						System.out.println("CNPJ da Empresa: " + empresa.getCnpj());
						System.out.println("Taxa da Empresa: " + empresa.getTaxa().setScale(2, RoundingMode.HALF_DOWN));
						System.out.println("************************************************************");
					}
					
					for(Usuario usuario : usuarios) {
						System.out.println("username: " + usuario.getUsername());
						System.out.println("senha: " + usuario.getSenha());
						System.out.println("************************************************************");
					}
									
					executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
				}
				
				
				
				else {
					System.out.println("1 - Realizar Compras");
					System.out.println("2 - Ver Compras");
					System.out.println("0 - Deslogar");
					Integer escolha = sc.nextInt();
					switch (escolha) {
					case 1: {
						System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar:");
						empresas.stream().forEach(x -> {
							System.out.println(x.getId() + " - " + x.getNome());
						});
						
						Long escolhaEmpresa = sc.nextLong();
						Integer escolhaProduto = -1;
						
						do {
							System.out.println("Escolha os seus produtos: ");
							produtos.stream().forEach(x -> {
								if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
									System.out.println(x.getId() + " - " + x.getNome());
								}
							});
							System.out.println("0 - Finalizar compra");
							
							
							escolhaProduto = sc.nextInt();
// Parte ajustada para regra de negócio						
								
							for (Produto produtoSearch : produtos) {		
								if (produtoSearch.getId().equals(escolhaProduto) && produtoSearch.getEmpresa().getId().equals(escolhaEmpresa)
										&& produtoSearch.getQuantidade() > 0) {
									carrinho.add(produtoSearch);
									produtoSearch.setQuantidade(produtoSearch.getQuantidade() - 1);
								}
							}
													
						} while (escolhaProduto != 0);
						System.out.println("************************************************************");
						System.out.println("Resumo da compra: ");
						carrinho.stream().forEach(x -> {
							if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
								System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
							}
						});
						Empresa empresaEscolhida = empresas.stream().filter(x -> x.getId().equals(escolhaEmpresa))
								.collect(Collectors.toList()).get(0);
						Cliente clienteLogado = clientes.stream()
								.filter(x -> x.getUsername().equals(usuarioLogado.getUsername()))
								.collect(Collectors.toList()).get(0);
						Venda venda = criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
						System.out.println("Total: R$" + venda.getValor());
						System.out.println("************************************************************");
						carrinho.clear();
						executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
					}
					case 2: {
						System.out.println();
						System.out.println("************************************************************");
						System.out.println("COMPRAS EFETUADAS");
						vendas.stream().forEach(venda -> {
							if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
								System.out.println("************************************************************");
								System.out.println("Compra de código: " + venda.getCódigo() + " na empresa "
										+ venda.getEmpresa().getNome() + ": ");
								venda.getItens().stream().forEach(x -> {
									System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
								});
								System.out.println("Total: R$" + venda.getValor());
								System.out.println("************************************************************");
							}

						});

						executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
					}
					case 0: {
						executar(usuarios, clientes, empresas, produtos, carrinho, vendas);

					}

					}
				}

			} else
				System.out.println("Senha incorreta");
				executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
		} else {
			System.out.println("Usuário não encontrado");
			executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
		}
		sc.close();
	}
	
	
	public static Venda criarVenda(List<Produto> carrinho, Empresa empresa, Cliente cliente, List<Venda> vendas) {
		
		BigDecimal total = carrinho.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		
		BigDecimal comissaoSistema = total.multiply(empresa.getTaxa());
		
		Long idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCódigo() + 1;
		
		Venda venda = new Venda(idVenda, carrinho.stream().toList(), total, comissaoSistema, empresa, cliente);
		
		empresa.setSaldo(empresa.getSaldo().add(total));
		
		vendas.add(venda);
		
		return venda;
	}
	
	
}
