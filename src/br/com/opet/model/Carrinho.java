package br.com.opet.model;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.opet.dao.CarrinhoDAO;

public class Carrinho extends CarrinhoDAO {
	private int id;
	private Produto produto;
	private int quantidade;

	public Carrinho() {

	}

	public Carrinho(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Carrinho(int id, Produto produto, int quantidade) {
		this(produto, quantidade);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public int getProdutoId() {
		return produto.getId();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Carrinho> listarCarrinhos() {
		try {
			return super.listarCarrinhos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void excluir() {
		try {
			super.excluir(this.id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Carrinho consultar() {
		try {
			return super.consultar(this.id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
