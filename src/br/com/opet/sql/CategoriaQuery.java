package br.com.opet.sql;

public final class CategoriaQuery {
	public static final String SELECT_ALL = "SELECT * FROM categoria";
	
	public static final String SELECT_ONE = "SELECT * FROM categoria WHERE idCate = ?";
	
	public static final String INSERT = "INSERT INTO categoria (idCate,nomeCate,slugCate) "
			+ "VALUES (CateSEQ.nextval,?,?)";
	
	public static final String DELETE = "DELETE FROM categoria WHERE idCate = ?";
	
	public static final String UPDATE = "UPDATE categoria SET NOMECATE = ?, SLUGCATE = ? "
			+ "WHERE IDCATE = ?";
}
