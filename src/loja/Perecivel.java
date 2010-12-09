package loja;

import utilities.Data;

/***********************************************************
 * @author FBA 2009/05/10
 * 
 ***********************************************************/
public class Perecivel extends Produto
{
	private Data	validade;

	/***********************************************************
	 * @param codigo
	 * @param preco
	 * @param nome
	 * @param validade
	 ***********************************************************/
	public Perecivel(int codigo, double preco, String nome, String unidade, CodigoBarras cb, Data validade)
	{
		super(codigo, preco, nome, unidade, cb);
		this.validade = validade;
	}

	/***********************************************************
	 * @param outro
	 ***********************************************************/
	public Perecivel(Perecivel outro)
	{
		super(outro);
		this.validade = new Data(outro.validade);
	}

	/***********************************************************
	 * @return the validade
	 ***********************************************************/
	public Data getValidade()
	{
		return validade;
	}

	/***********************************************************
	 * @param validade
	 *           the validade to set
	 ***********************************************************/
	public void setValidade(Data validade)
	{
		this.validade = validade;
	}

	@Override
	public String valores()
	{
		return "(P) " + super.valores() + String.format(" %10s %15s", validade, getCodigoBarras());
	}

	@Override
	protected Object clone()
	{
		return new Perecivel(this);
	}

}
