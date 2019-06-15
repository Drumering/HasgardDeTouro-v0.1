package br.com.opet.util;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SpiderReader {

	private String nome;
	private String dimensoes;
	private String preco;

	public SpiderReader() {
		
	}

	public SpiderReader(String nome, String dimensoes, String preco) {
		this.nome = nome;
		this.dimensoes = dimensoes;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(String dimensoes) {
		this.dimensoes = dimensoes;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public ArrayList<SpiderReader> lerArquivoSpider(String arquivo) {
		LeitorArquivo leitor = new LeitorArquivo();
		ArrayList<SpiderReader> spiders = leitor.formatarArquivoSpider(arquivo);
		return spiders;
	}
}
