/***********************************************************
 * Filename: CFactura.java
 * Created:  2009/11/09
 ***********************************************************/
package loja.gui;

import java.util.Scanner;
import javax.swing.JFrame;

import loja.BaseDados;
import loja.Factura;
import loja.Produto;

/***********************************************************
 * @author Utilizador 2009/11/09
 * 
 ***********************************************************/
public class CProduto extends InterfaceProduto
{
	private static final long	serialVersionUID	= 1L;

	/***********************************************************
	 * @param bd
	 * @param contentor
	 * @param visivel
	 ***********************************************************/
	public CProduto(BaseDados bd, JFrame contentor, boolean visivel)
	{
		super(bd, contentor, visivel);
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	public void menuProdutos()
	{
		Scanner linha = new Scanner(System.in);
		char escolha = ' ';
		do
		{
			System.out.println();
			System.out.println("******** Menu de Produtos ***********");
			System.out.println("\tL - Listar");
			System.out.println("\tE - Escolher");
			// System.out.println("\tN - Nova");
			// System.out.println("\tM - Modificar");
			System.out.println("\tA - Apagar");
			System.out.println("\tS - Sair");
			System.out.println();
			System.out.print("Opção> ");

			escolha = linha.nextLine().toUpperCase().charAt(0);
			switch (escolha)
			{
				case 'L':
					lista();
					break;
				// case 'N':
				// novo();
				// break;
				case 'E':
					escolhe();
					break;
				// case 'M':
				// modifica();
				// break;
				case 'A':
					apaga();
					break;
				case 'S':
					break;
				default:
					System.out.println("Comando Inválido!");
			}
			// System.out.println(">" + escolha + "<");
		}
		while (escolha != 'S');
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	private void apaga()
	{
		if (this.produto_corrente == null)
			System.out.println("ERRO: Produto não seleccionado!");
		else
		{
			this.getBDados().retira(this.produto_corrente);
			this.produto_corrente = null;
			this.lista();
		}
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	// private void modifica()
	// {
	// // TODO Auto-generated method stub
	//
	// }

	/***********************************************************
	 * 
	 ***********************************************************/
	private void escolhe()
	{
		Scanner input = new Scanner(System.in);
		Integer numero;
		do
		{
			System.out.print("Nº produto [" + getBDados().produtos().get(0).getCodigo() + "-"
					+ getBDados().produtos().get(getBDados().produtos().size() - 1).getCodigo() + "]>");
			numero = Integer.parseInt(input.nextLine());
		}
		while (getBDados().procuraProduto(numero) == null);
		this.setCorrente(getBDados().procuraProduto(numero));
		this.lista();
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	// private void novo()
	// {
	// // TODO Auto-generated method stub
	// }

	/***********************************************************
	 * 
	 ***********************************************************/
	private void lista()
	{
		System.out.println("  " + Produto.cabecalho());
		System.out.println("  ----------------------------------------------------------------------------------------------");
		for (Produto p : getBDados().produtos())
			System.out.println((p == this.produto_corrente ? "* " : "  ") + p.valores());
	}

	@Override
	protected void escreve(Produto p)
	{
		System.out.println(p);
	}

}
