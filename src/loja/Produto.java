package loja;

/***********************************************************
 * @author FBA 2009/03/10
 * 
 ***********************************************************/
public abstract class Produto implements Comparable<Produto>
{
	private int				codigo;
	private double			preco_venda;
	private String			nome;
	private String			unidade;
	private CodigoBarras	codigo_barras;

	private enum Tipo
	{
		EAN, UPC
	}

	public static class CodigoBarras
	{
		static private Tipo	tipo		= Tipo.EAN;
		static private int	digitos	= 13;

		private String			codigo;

		public CodigoBarras(String codigo)
		{
			this.codigo = codigo;
		}

		public String pais()
		{
			if (codigo.substring(0, 2) == "560")
				return "Portugal";
			else
				return "???";
		}

		public int checkDigit()
		{
			return 0;
		}

		@Override
		public String toString()
		{
			return this.codigo;
		}
	}

	/***********************************************************
	 * @param codigo
	 * @param preco
	 * @param nome
	 ***********************************************************/
	public Produto(int codigo, double preco, String nome, String unidade, CodigoBarras codigo_barras)
	{
		this.codigo = codigo;
		this.preco_venda = preco;
		this.nome = nome;
		this.unidade = unidade;
		this.codigo_barras = codigo_barras;
	}

	/***********************************************************
	 *
	 ***********************************************************/
	public Produto(Produto outro)
	{
		this(outro.codigo, outro.preco_venda, outro.nome, outro.unidade, outro.codigo_barras);
	}

	/***********************************************************
	 * @return the codigo
	 ***********************************************************/
	public final int getCodigo()
	{
		return codigo;
	}

	/***********************************************************
	 * @return the preco
	 ***********************************************************/
	public double getPreco()
	{
		return preco_venda;
	}

	/***********************************************************
	 * @return the nome
	 ***********************************************************/
	public String getNome()
	{
		return nome;
	}

	/***********************************************************
	 * @return the unidade
	 ***********************************************************/
	public String getUnidade()
	{
		return unidade;
	}

	/***********************************************************
	 * @return the codigo_barras
	 ***********************************************************/
	public CodigoBarras getCodigoBarras()
	{
		return codigo_barras;
	}

	/***********************************************************
	 * @param preco_venda
	 *           the preco_venda to set
	 ***********************************************************/
	public void setPreco_venda(double preco_venda)
	{
		this.preco_venda = preco_venda;
	}

	/***********************************************************
	 * @param unidade
	 *           the unidade to set
	 ***********************************************************/
	public void setUnidade(String unidade)
	{
		this.unidade = unidade;
	}

	/***********************************************************
	 * @param codigo_barras
	 *           the codigo_barras to set
	 ***********************************************************/
	public void setCodigo_barras(CodigoBarras codigo_barras)
	{
		this.codigo_barras = codigo_barras;
	}

	/***********************************************************
	 * @param codigo
	 *           the codigo to set
	 ***********************************************************/
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	/***********************************************************
	 * @param preco
	 *           the preco to set
	 ***********************************************************/
	public void setPreco(float preco)
	{
		this.preco_venda = preco;
	}

	/***********************************************************
	 * @param nome
	 *           the nome to set
	 ***********************************************************/
	protected void setNome(String nome)
	{
		this.nome = nome;
	}

	public static String cabecalho()
	{
		return "Tipo  Código Nome                            Preço Unidade           Data       Codigo Barras";
	}

	public String valores()
	{
		return String.format("%8d %-30s %6.2f %-15s", codigo, nome, preco_venda, unidade);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return nome.toUpperCase() + " (" + unidade + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		// if (getClass() != obj.getClass())
		// return false;
		return codigo == ((Produto) obj).codigo;
	}

	@Override
	public int compareTo(Produto outro)
	{
		return ((Integer) this.codigo).compareTo(outro.codigo);
	}

}
