/***********************************************************
 * Filename: InterfaceTextual.java
 * Created:  2009/11/09
 ***********************************************************/
package loja.gui;

import java.util.Scanner;

import javax.swing.JFrame;

import loja.BaseDados;

/***********************************************************
 * @author Utilizador 2009/11/09
 * 
 ***********************************************************/
public class InterfaceTextual extends JFrame implements ILoja
{
	private InterfaceProduto	jProduto;
	private InterfaceFactura	jFactura;
	private BaseDados	bDados;

	/***********************************************************
	 * @param base
	 *           - a base de dados
	 ***********************************************************/
	public InterfaceTextual(BaseDados base)
	{
		bDados = base;

		jProduto = new CProduto(base, null, false);
		jFactura = new CFactura(base, null, false);

		menuGeral();
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	public void menuGeral()
	{
		Scanner linha = new Scanner(System.in);
		char escolha = ' ';
		do
		{
			System.out.println();
			System.out.println("******************************************");
			System.out.println("*        Menu do QUASAR P.O.S.            ");
			System.out.println("******************************************");
			System.out.println("\tP - Produtos");
			System.out.println("\tF - Facturas");
			System.out.println("\tA - Acerca de");
			System.out.println("\tS - Sair");
			System.out.println();
			System.out.print("Opção> ");

			escolha = linha.nextLine().toUpperCase().charAt(0);
			switch (escolha)
			{
				case 'P':
					((CProduto) jProduto).menuProdutos();
					break;
				case 'F':
					((CFactura) jFactura).menuFacturas();
					break;
				case 'A':
					System.out.println("QUASAR P.O.S., Copyright FBA, 2010");
					break;
				case 'S':
					break;
				default:
					System.out.println("Comando Inválido!");
			}
//			System.out.println(">" + escolha + "<");
		}
		while (escolha != 'S');
		System.exit(0);
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	@Override
	public InterfaceFactura criaJanelaFactura(BaseDados bd, JFrame contentor, boolean visivel)
	{
		return new CFactura(bd, null, visivel);
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	@Override
	public InterfaceProduto criaJanelaProduto(BaseDados bd, JFrame contentor, boolean visivel)
	{
		return new CProduto(bd, null, visivel);
	}

}
