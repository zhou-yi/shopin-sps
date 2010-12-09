package loja;

import utilities.Data;

/***********************************************************
 * @author FBA 2009/03/10
 * 
 ***********************************************************/
public class NaoPerecivel extends Produto
{
	private Data	fabricado_em;

	/***********************************************************
	 * @param codigo
	 * @param preco
	 * @param nome
	 * @param validade
	 ***********************************************************/
	public NaoPerecivel(int codigo, double preco, String nome, String unidade, CodigoBarras cb, Data fabricado_em)
	{
		super(codigo, preco, nome, unidade, cb);
		this.fabricado_em = fabricado_em;
	}

	/***********************************************************
	 * @param outro
	 ***********************************************************/
	public NaoPerecivel(NaoPerecivel outro)
	{
		super(outro);
		this.fabricado_em = new Data(outro.fabricado_em);
	}

	/***********************************************************
	 * @return the validade
	 ***********************************************************/
	public Data getFabricado_em()
	{
		return fabricado_em;
	}

	/***********************************************************
	 * @param validade
	 *           the validade to set
	 ***********************************************************/
	public void setFabricado_em(Data fabricado_em)
	{
		this.fabricado_em = fabricado_em;
	}

	@Override
	public String valores()
	{
		return "(N) " + super.valores() + String.format(" %10s %15s", fabricado_em, getCodigoBarras());
	}

}
