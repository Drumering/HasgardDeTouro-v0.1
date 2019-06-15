package br.com.opet.model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.opet.dao.CategoriaDAO;

@ManagedBean
@RequestScoped
public class Categoria extends CategoriaDAO{
	
	private int id;
	private String nome;
	private String descricao;
	
	public Categoria() {}
	
	public Categoria(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Categoria(int id, String nome, String descricao) {
		this(nome, descricao);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void cadastrar() {
		try {
			super.cadastrar(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			super.excluir(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Categoria consultar() {
		try {
			return super.consultar(this.id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Categoria> listar() {
		try {
			return super.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void alterar() {
		try {
			super.alterar(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}