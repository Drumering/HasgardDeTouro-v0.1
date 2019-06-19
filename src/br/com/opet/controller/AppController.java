package br.com.opet.controller;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.opet.model.Carrinho;
import br.com.opet.model.Categoria;
import br.com.opet.model.ItensCarrinho;
import br.com.opet.model.Produto;
import br.com.opet.util.SpiderReader;

@ManagedBean
@SessionScoped
public class AppController {
	Carrinho carrinho = new Carrinho();

	public String cadastrarProduto(Produto produto) {
		produto.cadastrar();
		return "../index.xhtml";
	}
	
	public ArrayList<Produto> listarProduto() {
		Produto produto = new Produto();
		return produto.listar();
	}
	
	public ArrayList<ItensCarrinho> consultarUnicoCarrinho() {
		ArrayList<ItensCarrinho> itens = null;
		try {
			itens = carrinho.consultar(carrinho.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itens;
	}

	public void cadastrarCarrinho(Produto produto, int quantidade) {
		carrinho.setProduto(produto);
		carrinho.setQuantidade(quantidade);
		carrinho.cadastrar();
	}
	
	public String cadastrarCategoria(Categoria categoria) {
		categoria.cadastrar();
		return "index.xhtml";
	}
	
	public ArrayList<Categoria> listarCategorias() {
		Categoria categoria = new Categoria();
		return categoria.listar();
	}
	
	public ArrayList<Carrinho> listarCarrinhos(){
		Carrinho carrinho = new Carrinho();
		return carrinho.listarCarrinhos();
	}
	
	public ArrayList<SpiderReader> listarPesquisaConcorrentes() {
		SpiderReader listarConcorrentes = new SpiderReader();
		return listarConcorrentes.lerArquivoSpider("dadosWebCrawler.txt");
	}
}
