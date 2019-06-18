package br.com.opet.sql;

public final class CarrinhoQuery {
	
	public static final String SELECT_ALL = "SELECT car.IDCARRINHO, car.PROID, p.PRONOME NOME, "
			+ "car.quantidade QUANTIDADE, p.PROPRECO*quantidade PRECO," + 
			"p.PROALTURA, p.PROCOMPR, p.PROLARGURA, p.PROQNTD, c.IDCATE, c.NOMECATE, "
			+ "c.SLUGCATE FROM carrinho_itens car join produto p on car.proID = p.proID " + 
			"join categoria c on p.IDCATE = c.IDCATE";
	
	public static final String SELECT_ONE = "SELECT car.IDCARRINHO, car.PROID, p.PRONOME NOME, car.quantidade QUANTIDADE, "
			+ "p.PROPRECO*quantidade PRECO, p.PROALTURA, p.PROCOMPR, p.PROLARGURA, p.PROQNTD, c.IDCATE, c.NOMECATE, "
			+ "c.SLUGCATE FROM carrinho car join produto p on car.proID = p.proID join categoria c on p.IDCATE = c.IDCATE "
			+ "WHERE car.IDCARRINHO = ?";
	
	public static final String INSERT = "INSERT INTO carrinho (idCarrinho) "
			+ "VALUES (carrinhoSEQ.nextval)";
	
	public static final String INSERT_ITENS = "INSERT INTO carrinho_itens (idCarrinho,proId,quantidade) "
			+ "VALUES (?,?,?)";
	
	public static final String DELETE = "DELETE FROM carrinho WHERE idCarrinho = ?";

}
