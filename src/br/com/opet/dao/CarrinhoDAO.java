package br.com.opet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.opet.conexao.Conexao;
import br.com.opet.model.Carrinho;
import br.com.opet.model.Categoria;
import br.com.opet.model.ItensCarrinho;
import br.com.opet.model.Produto;
import br.com.opet.sql.CarrinhoQuery;

public class CarrinhoDAO {

	public void cadastrar(Carrinho carrinho) throws SQLException {
		Connection conn = Conexao.getConexao();
		conn.setAutoCommit(false);
		PreparedStatement stmt = null;

		if (carrinho.getId() == 0) {
			carrinho.setIdSession();
			stmt = conn.prepareStatement(CarrinhoQuery.INSERT);
			stmt.setInt(1, carrinho.getId());

			int rowAffected = stmt.executeUpdate();

			if (rowAffected == 0) {
				conn.rollback();
				return;
			}
			conn.commit();
			stmt.close();
		}
		cadastrarItemCarrinho(carrinho, conn);
	}

	public void cadastrarItemCarrinho(Carrinho carrinho, Connection conn) throws SQLException {
		PreparedStatement stmtItens = conn.prepareStatement(CarrinhoQuery.INSERT_ITENS);
		stmtItens.setInt(1, carrinho.getId());
		stmtItens.setInt(2, carrinho.getProdutoId());
		stmtItens.setInt(3, carrinho.getQuantidade());

		int rowAffected = stmtItens.executeUpdate();

		if (rowAffected == 0) {
			conn.rollback();
			return;
		}
		conn.commit();
		conn.close();
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
		while (rs.next()) {
			carrinhos.add(extract(rs));
		}
		rs.close();
		stmt.close();
		conn.close();

		return carrinhos;
	}

	public ArrayList<ItensCarrinho> consultar(int id) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CarrinhoQuery.SELECT_ONE_ITEM);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		ArrayList<ItensCarrinho> itensCarrinhos = new ArrayList<>();
		ItensCarrinho item = null;

		while (rs.next()) {
			item = new ItensCarrinho(rs.getInt("idcarrinho"), rs.getInt("proid"), rs.getInt("quantidade"));
			itensCarrinhos.add(item);
		}

		rs.close();
		stmt.close();
		conn.close();
		return itensCarrinhos;
	}

	private Carrinho extract(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria(rs.getInt("idcate"), rs.getString("nomecate"), rs.getString("slugcate"));
		Produto produto = new Produto(rs.getInt("proID"), categoria, rs.getString("nome"), rs.getDouble("proaltura"),
				rs.getDouble("prolargura"), rs.getDouble("procompr"), rs.getDouble("preco"), rs.getInt("proqntd"));
		Carrinho carrinho = new Carrinho(rs.getInt("idcarrinho"), produto, rs.getInt("quantidade"));
		return carrinho;
	}

	public void setIdSession(Carrinho carrinho) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CarrinhoQuery.LOCALIZAR_ID);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			carrinho.setId(rs.getInt("NEXTVAL"));
		}
		rs.close();
		stmt.close();
		conn.close();
	}
}
