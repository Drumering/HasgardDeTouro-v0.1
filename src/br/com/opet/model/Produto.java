package br.com.opet.model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.opet.dao.ProdutoDAO;

@ManagedBean
@RequestScoped
public class Produto extends ProdutoDAO {

	private int id;
	private Categoria categoria;
	private String nome;
	private double altura;
	private double largura;
	private double comprimento;
	private double preco;
	private int quantidade;

	public Produto() {
		this.categoria = new Categoria();
	}

	public Produto(Categoria categoria, String nome, double altura, double largura, double comprimento, double preco,
			int quantidade) {
		this.categoria = categoria;
		this.nome = nome;
		this.altura = altura;
		this.largura = largura;
		this.comprimento = comprimento;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public Produto(int id, Categoria categoria, String nome, double altura, double largura, double comprimento, double preco,
			int quantidade) {
		this(categoria, nome, altura, largura, comprimento, preco, quantidade);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoriaId() {
		return categoria.getId();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void cadastrar() {
		try {
			super.cadastrar(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Produto consultar() throws Exception {
		return super.consultar(this.id);
	}

	public ArrayList<Produto> listar() {
		try {
			return super.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void excluir() {
		try {
			super.excluir(this.id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar() {
		try {
			super.alterar(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
