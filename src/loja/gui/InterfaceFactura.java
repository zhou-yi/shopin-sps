package loja.gui;

import javax.swing.JFrame;

import loja.BaseDados;
import loja.Factura;

public abstract class InterfaceFactura extends JFrame
{
	private static final long	serialVersionUID	= 1L;

	private final BaseDados		bDados;

	protected JFrame				chamadora;
	protected Factura				factura_corrente;

	/***********************************************************
	 * @param bd
	 * @param contentor
	 * @param visivel
	 ***********************************************************/
	public InterfaceFactura(BaseDados bd, JFrame contentor, boolean visivel)
	{
		this.bDados = bd;
		this.chamadora = contentor;
		this.setVisible(visivel);
		this.factura_corrente = new Factura();
	}

	/***********************************************************
	 * @param f
	 ***********************************************************/
	protected void setCorrente(Factura f)
	{
		factura_corrente = f;
	}

	/***********************************************************
	 * @return the bDados
	 ***********************************************************/
	protected BaseDados getBDados()
	{
		return bDados;
	}

	/***********************************************************
	 * @param estado
	 ***********************************************************/
	public void visivel(boolean estado)
	{
		this.setVisible(estado);
	}

	/***********************************************************
	 * @param f
	 ***********************************************************/
	abstract protected void escreve(Factura f);

}
