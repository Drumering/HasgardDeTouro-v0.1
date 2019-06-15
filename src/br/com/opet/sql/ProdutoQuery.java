package br.com.opet.sql;

public final class ProdutoQuery {
	public static final String INSERT = "INSERT INTO produto(proID,idCate,proNome,proAltura,proLargura,"
			+ "proCompr,proPreco,proQntd) VALUES(proSEQ.nextval,?,?,?,?,?,?,?)";
	
	public static final String SELECT_ONE = "SELECT p.PROID id, p.IDCATE idcate, "
			+ "p.PRONOME nome, p.PROALTURA altura, p.PROLARGURA largura, p.PROCOMPR "
			+ "comprimento, p.PROPRECO preco, p.PROQNTD quantidade, c.NOMECATE "
			+ "categoria,c.SLUGCATE descricao FROM produto p INNER JOIN categoria "
			+ "c ON p.idCate = c.idCate WHERE p.proid = ?";
	
	public static final String SELECT_ALL = "SELECT p.PROID id, p.IDCATE idcate, "
			+ "p.PRONOME nome, p.PROALTURA altura, p.PROLARGURA largura, p.PROCOMPR "
			+ "comprimento, p.PROPRECO preco, p.PROQNTD quantidade, c.NOMECATE "
			+ "categoria,c.SLUGCATE descricao FROM produto p INNER JOIN categoria " + "c ON p.idCate = c.idCate";

	public static final String UPDATE = "UPDATE produto SET IDCATE = ?,PRONOME = ?, PROALTURA = ?, PROLARGURA = ?, "
			+ "PROCOMPR = ?, PROPRECO = ?, PROQNTD = ? WHERE proID = ?";

	public static final String DELETE = "DELETE FROM produto WHERE proID = ?";
}
