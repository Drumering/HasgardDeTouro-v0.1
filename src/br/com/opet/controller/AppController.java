package br.com.opet.controller;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.opet.model.Categoria;
import br.com.opet.model.Produto;
import br.com.opet.util.SpiderReader;

@ManagedBean
public class AppController {
	public String cadastrarProduto(Produto produto) {
		produto.cadastrar();
		return "/index.xhtml";
	}
	
	public ArrayList<Produto> listarProduto() {
		Produto produto = new Produto();
		return produto.listar();
	}

	public String ClickCadastrarCarrinho(Produto p, int quantidade) {
		return "/carrinho.xhtml";
	}
	
	public String cadastrarCategoria(Categoria categoria) {
		categoria.cadastrar();
		return "/index.xhtml";
	}
	
	public ArrayList<Categoria> listarCategorias() {
		Categoria categoria = new Categoria();
		return categoria.listar();
	}
	
	public ArrayList<SpiderReader> listarPesquisaConcorrentes() {
		SpiderReader listarConcorrentes = new SpiderReader();
//		return listarConcorrentes.lerArquivoSpider("C:/Users/admin/eclipse-workspace/HasgardDeTouro-v0.1/dadosWebCrawler.txt");
		return listarConcorrentes.lerArquivoSpider("dadosWebCrawler.txt");
	}
	
	public String listarAction() {
		return "/index.xhtml";
	}
	
//	public ArrayList<String> listarPesquisaConcorrentes() {
//		ArrayList<String> linhasLidas = new ArrayList<String>();
//		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\admin\\eclipse-workspace\\HasgardDeTouro-v0.1\\dadosWebCrawler.txt"))) {
//			String novaLinha = "";
//			while ((novaLinha = reader.readLine()) != null) {
//				linhasLidas.add(novaLinha);
//			}
//			reader.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return linhasLidas;
//	}
}
