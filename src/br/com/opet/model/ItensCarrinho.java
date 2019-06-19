package br.com.opet.model;

public class ItensCarrinho {

	private int idCarrinho;
	private int idProduto;
	private int quantidade;

	public ItensCarrinho() {
	}

	public ItensCarrinho(int idCarrinho, int idProduto, int quantidade) {
		this.idCarrinho = idCarrinho;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public int getIdCarrinho() {
		return idCarrinho;
	}

	public void setIdCarrinho(int idCarrinho) {
		this.idCarrinho = idCarrinho;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
