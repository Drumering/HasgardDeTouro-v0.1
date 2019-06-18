package br.com.opet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.opet.conexao.Conexao;
import br.com.opet.model.Carrinho;
import br.com.opet.model.Categoria;
import br.com.opet.model.Produto;
import br.com.opet.sql.CarrinhoQuery;

public class CarrinhoDAO {
	
	public void cadastrar(Carrinho carrinho) throws SQLException {
		String generatedID[] = {"idCarrinho"};
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CarrinhoQuery.INSERT, generatedID);
		
		conn.setAutoCommit(false);

		int rowAffected = stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		
		while(rs.next()) {
			int carrinhoID = rs.getInt(1);
			carrinho.setId(carrinhoID);
		}
		rs.close();

		if (rowAffected == 0) {
			conn.rollback();
			return;
		}
		cadastrarItemCarrinho(carrinho, conn, stmt);
	}

	private void cadastrarItemCarrinho(Carrinho carrinho, Connection conn, PreparedStatement stmt) throws SQLException {
		int rowAffected;
		PreparedStatement stmtItens = conn.prepareStatement(CarrinhoQuery.INSERT_ITENS);
		stmtItens.setInt(1, carrinho.getId());
		stmtItens.setInt(2, carrinho.getProdutoId());
		stmtItens.setInt(3, carrinho.getQuantidade());
		
		rowAffected = stmtItens.executeUpdate();
		
		if (rowAffected == 0) {
			conn.rollback();
			return;
		}

		conn.commit();
		conn.close();
		stmt.close();
		stmtItens.close();
	}
	
	public void excluir(int id) throws SQLException {
		Connection conn = Conexao.getConexao();

		PreparedStatement stmt = conn.prepareStatement(CarrinhoQuery.DELETE);

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

	public ArrayList<Carrinho> listarCarrinhos() throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CarrinhoQuery.SELECT_ALL);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Carrinho> carrinhos = new ArrayList<>();
		while(rs.next()) {
			carrinhos.add(extract(rs));
		}
		rs.close();
		stmt.close();
		conn.close();
		
		return carrinhos;
	}

	public Carrinho consultar(int id) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CarrinhoQuery.SELECT_ONE);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Carrinho carrinho = null;

		while (rs.next()) {
			carrinho = extract(rs);
		}

		rs.close();
		stmt.close();
		conn.close();
		return carrinho;
	}
	
	private Carrinho extract(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria(rs.getInt("idcate"), rs.getString("nomecate"), 
				rs.getString("slugcate"));
		Produto produto = new Produto(rs.getInt("proID"), categoria, rs.getString("nome"), 
				rs.getDouble("proaltura"), rs.getDouble("prolargura"), rs.getDouble("procompr"),
				rs.getDouble("preco"), rs.getInt("proqntd"));
		Carrinho carrinho = new Carrinho(rs.getInt("idcarrinho"), produto, rs.getInt("quantidade"));
		return carrinho;
	}
}
