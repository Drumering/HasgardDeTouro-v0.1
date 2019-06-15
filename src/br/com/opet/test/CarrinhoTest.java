package br.com.opet.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.opet.model.Carrinho;
import br.com.opet.model.Categoria;
import br.com.opet.model.Produto;

class CarrinhoTest {
	private Categoria categoria;
	private Produto produto;
	private Carrinho carrinho;

	@BeforeEach
	public void setUp() {
		categoria = new Categoria(1, "casal", "cholchao-de-casal");
		produto = new Produto(1, categoria, "Cholchao Dona Joana", 
				1.1, 2.2, 3.3, 1250.99, 150);
		carrinho = new Carrinho(produto, 3);
		carrinho.setId(5);
	}
	
	@Test
	@Disabled
	public void cadastrarCarrinhoTest() {
		assertAll("cadastrar", () -> carrinho.cadastrar());
		assertAll("cadastrar", () -> carrinho.cadastrar());
		assertAll("cadastrar", () -> carrinho.cadastrar());
	}
	
	@Test
	public void listarCarrinhoTest() {
		ArrayList<Carrinho> carrinhos = carrinho.listarCarrinhos();
		assertEquals(3, carrinhos.size());
	}
	
	@Test
	public void consultarCarrinhoTest() {
		assertEquals(5, carrinho.consultar().getId());
	}
	
	@Test
	@Disabled
	public void excluirCarrinhoTest() {
		assertAll("excluir", () -> carrinho.excluir());
	}
}
