package loja;

public class LinhaEncomenda 
{
	private int		 	idEncomenda;
	private String 		nomeFornecedor;
	private String	 	nomeProduto;
	private double 		quantidade;
	private double 		valor_unitario;

	public LinhaEncomenda(int idEncomenda, String nomeFornecedor, String nomeProduto, double quantidade, double valor_unitario)
	{
		this.idEncomenda 		= idEncomenda;
		this.nomeFornecedor 	= nomeFornecedor;
		this.nomeProduto 		= nomeProduto;
		this.quantidade 		= quantidade;
		this.valor_unitario		= valor_unitario;
	}

	public LinhaEncomenda(LinhaEncomenda linhaEncomenda)
	{
		this(linhaEncomenda.idEncomenda, 
			 linhaEncomenda.nomeFornecedor,
			 linhaEncomenda.nomeProduto, 
			 linhaEncomenda.quantidade,
			 linhaEncomenda.valor_unitario);
	}

	public int getIdEncomenda() 
	{
		return idEncomenda;
	}

	public void setIdEncomenda(int idEncomenda) 
	{
		this.idEncomenda = idEncomenda;
	}

	public String getNomeFornecedor() 
	{
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) 
	{
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNomeProduto() 
	{
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) 
	{
		this.nomeProduto = nomeProduto;
	}

	public double getQuantidade() 
	{
		return quantidade;
	}

	public void setQuantidade(double quantidade) 
	{
		this.quantidade = quantidade;
	}

	public double getValor_unitario() 
	{
		return valor_unitario;
	}

	public void setValor_unitario(double valor_unitario) 
	{
		this.valor_unitario = valor_unitario;
	}

	public String toString()
	{
		return idEncomenda + " " + nomeFornecedor + " " + nomeProduto + " " + quantidade + " " + valor_unitario;
	}
	
}