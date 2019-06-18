package br.com.opet.controller;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import br.com.opet.model.Carrinho;
import br.com.opet.model.Categoria;
import br.com.opet.model.Produto;
import br.com.opet.util.SpiderReader;

@ManagedBean
public class AppController {
	public String cadastrarProduto(Produto produto) {
		produto.cadastrar();
		return "../index.xhtml";
	}
	
	public ArrayList<Produto> listarProduto() {
		Produto produto = new Produto();
		return produto.listar();
	}

	public void cadastrarCarrinho(Produto produto, int quantidade) {
		Carrinho carrinho = new Carrinho(produto,quantidade);
		carrinho.cadastrar();
//		return "/carrinho.xhtml";
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
