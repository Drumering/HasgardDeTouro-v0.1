package br.com.opet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.opet.conexao.Conexao;
import br.com.opet.model.Categoria;
import br.com.opet.sql.CategoriaQuery;

public class CategoriaDAO {
	
	public void cadastrar(Categoria categoria) throws Exception {
		String generatedID[] = {"idCate"};
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CategoriaQuery.INSERT, generatedID);

		stmt.setString(1, categoria.getNome());
		stmt.setString(2, categoria.getDescricao());
		
		conn.setAutoCommit(false);

		int rowAffected = stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		
		while(rs.next()) {
			int categoriaID = rs.getInt(1);
			categoria.setId(categoriaID);
		}
		
		rs.close();

		if (rowAffected == 0) {
			conn.rollback();
			return;
		}

		conn.commit();
		conn.close();
		stmt.close();		
	}

	public Categoria consultar(int id) throws Exception {
		
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CategoriaQuery.SELECT_ONE);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Categoria categoria = null;

		while (rs.next()) {
			categoria = new Categoria(rs.getInt("idCate"), rs.getString("nomeCate"), rs.getString("slugCate"));
		}

		rs.close();
		stmt.close();
		conn.close();
		return categoria;
	}
	
	public ArrayList<Categoria> listar() throws SQLException {
		
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CategoriaQuery.SELECT_ALL);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Categoria> categorias = new ArrayList<>();
		while (rs.next()) {
			Categoria categoria = new Categoria(rs.getInt("idCate"), 
					rs.getString("nomeCate"), rs.getString("slugCate"));
			categorias.add(categoria);
		}

		rs.close();
		stmt.close();
		conn.close();
		return categorias;
	}
	
	public void excluir(Categoria categoria) throws Exception {
		
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CategoriaQuery.DELETE);

		stmt.setInt(1, categoria.getId());
		
		int rowAffected = stmt.executeUpdate();
		
		if(rowAffected == 0) {
			conn.rollback();
			return;
		}
		
		conn.commit();
		stmt.close();
		conn.close();
	}

	public void alterar(Categoria categoria) throws SQLException {
		Connection conn = Conexao.getConexao();
		PreparedStatement stmt = conn.prepareStatement(CategoriaQuery.UPDATE);

		stmt.setString(1, categoria.getNome());
		stmt.setString(2, categoria.getDescricao());
		stmt.setInt(3, categoria.getId());
		
		int rowAffected = stmt.executeUpdate();
		
		if(rowAffected == 0) {
			conn.rollback();
			return;
		}
		
		conn.commit();
		stmt.close();
		conn.close();
	}
}
