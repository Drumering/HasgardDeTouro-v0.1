package br.com.opet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.opet.conexao.Conexao;
import br.com.opet.model.Categoria;
import br.com.opet.model.Produto;
import br.com.opet.sql.ProdutoQuery;

public class ProdutoDAO {
	
	public void cadastrar(Produto produto) throws Exception {
		String generatedID[] = { "proID" };
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(ProdutoQuery.INSERT, generatedID);

		stmt.setInt(1, produto.getCategoriaId());
		stmt.setString(2, produto.getNome());
		stmt.setDouble(3, produto.getAltura());
		stmt.setDouble(4, produto.getLargura());
		stmt.setDouble(5, produto.getComprimento());
		stmt.setDouble(6, produto.getPreco());
		stmt.setInt(7, produto.getQuantidade());

		int rowAffected = stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();

		while (rs.next()) {
			int produtoID = rs.getInt(1);
			produto.setId(produtoID);
		}

		if (rowAffected == 0) {
			conn.rollback();
			return;
		}
		conn.commit();
		stmt.close();
		conn.close();
	}

	public Produto consultar(int id) throws Exception {
		Produto produto = null;

		Connection conn = Conexao.getConexao();

		PreparedStatement stmt = conn.prepareStatement(ProdutoQuery.SELECT_ONE);
		stmt.setInt(1, id);

		conn.setAutoCommit(false);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			produto = extrair(rs);
		}
		rs.close();
		stmt.close();
		conn.close();
		return produto;
	}

	public ArrayList<Produto> listar() throws SQLException {

		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(ProdutoQuery.SELECT_ALL);
		conn.setAutoCommit(false);
		ResultSet rs = stmt.executeQuery();

		ArrayList<Produto> produtos = new ArrayList<>();
		while (rs.next()) {
			Produto produto = extrair(rs);
			produtos.add(produto);
		}

		rs.close();
		stmt.close();
		conn.close();
		return produtos;
	}

	private Produto extrair(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria(rs.getInt("idcate"), rs.getString("categoria"), rs.getString("descricao"));
		Produto produto = new Produto(rs.getInt("id"), categoria, rs.getString("nome"), rs.getDouble("altura"),
				rs.getDouble("largura"), rs.getDouble("comprimento"), rs.getDouble("preco"), rs.getInt("quantidade"));
		return produto;
	}

	public void excluir(int id) throws Exception {
		Connection conn = Conexao.getConexao();

		PreparedStatement stmt = conn.prepareStatement(ProdutoQuery.DELETE);

		stmt.setInt(1, id);

		int rowAffected = stmt.executeUpdate();

		if (rowAffected == 0) {
			conn.rollback();
			return;
		}
		conn.commit();
		stmt.close();
		conn.close();
	}

	public void alterar(Produto produto) throws Exception {		
		Connection conn = Conexao.getConexao();

		PreparedStatement stmt = conn.prepareStatement(ProdutoQuery.UPDATE);
		
		setarValores(produto, stmt);

		conn.setAutoCommit(false);

		int rowAffected = stmt.executeUpdate();

		if (rowAffected == 0) {
			conn.rollback();
			return;
		}
		
		conn.commit();
		stmt.close();
	}

	private void setarValores(Produto produto, PreparedStatement stmt) throws SQLException {
		stmt.setInt(1, produto.getCategoriaId());
		stmt.setString(2, produto.getNome());
		stmt.setDouble(3, produto.getAltura());
		stmt.setDouble(4, produto.getLargura());
		stmt.setDouble(5, produto.getComprimento());
		stmt.setDouble(6, produto.getPreco());
		stmt.setInt(6, produto.getQuantidade());
	}
}
