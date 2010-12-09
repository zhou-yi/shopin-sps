/***********************************************************
 * Filename: CFactura.java
 * Created:  2009/11/09
 ***********************************************************/
package loja.gui;

import java.util.Scanner;

import javax.swing.JFrame;

import utilities.Data;

import loja.BaseDados;
import loja.Factura;

/***********************************************************
 * @author FBA 2009/11/09
 * 
 ***********************************************************/
public class CFactura extends InterfaceFactura
{

	/***********************************************************
	 * @param bd
	 * @param contentor
	 * @param visivel
	 ***********************************************************/
	public CFactura(BaseDados bd, JFrame contentor, boolean visivel)
	{
		super(bd, contentor, visivel);
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	public void menuFacturas()
	{
		Scanner linha = new Scanner(System.in);
		char escolha = ' ';
		do
		{
			System.out.println();
			System.out.println("******** Menu de Facturas ***********");
			System.out.println("\tL - Listar");
			System.out.println("\tE - Escolher");
			System.out.println("\tD - Detalhe");
//			System.out.println("\tN - Nova");
//			System.out.println("\tM - Modificar");
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
//				case 'N':
//					nova();
//					break;
				case 'E':
					escolhe();
					break;
				case 'D':
					detalhe();
					break;
//				case 'M':
//					modifica();
//					break;
				case 'A':
					apaga();
					break;
				case 'S':
					break;
				default:
					System.out.println("Comando Inválido!");
			}
//			System.out.println(">" + escolha + "<");
		}
		while (escolha != 'S');
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	private void apaga()
	{
		if (this.factura_corrente == null)
			System.out.println("ERRO: Factura não seleccionada!");
		else
		{
			this.getBDados().retira(this.factura_corrente);
			this.factura_corrente = null;
			this.lista();
		}		
	}

	/***********************************************************
	 * 
	 ***********************************************************/
//	private void modifica()
//	{
//		// TODO Auto-generated method stub
//	}

	/***********************************************************
	 * 
	 ***********************************************************/
	private void escolhe()
	{
		Scanner input = new Scanner(System.in);
		Integer numero;
		do
		{
			System.out.print("Nº factura [" + getBDados().facturas().get(0).getNumero() + "-"
					+ getBDados().facturas().get(getBDados().facturas().size() - 1).getNumero() + "]>");
			numero = Integer.parseInt(input.nextLine());
		}
		while (getBDados().procuraFactura(numero) == null);
		setCorrente(getBDados().procuraFactura(numero));
		lista();
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	private void detalhe()
	{
		if (this.factura_corrente == null)
			System.out.println("ERRO: Factura não seleccionada!");
		else
		{
			System.out.println(this.factura_corrente);
		}	
	}
	
	/***********************************************************
	 * 
	 ***********************************************************/	
//	private void nova()
//	{
//		// TODO Auto-generated method stub
//	}

	/***********************************************************
	 * 
	 ***********************************************************/
	private void lista()
	{
		for (Factura f : getBDados().facturas())
			System.out.println((f == factura_corrente ? "* " : "  ") + f.cabecalho());
	}

	@Override
	protected void escreve(Factura f)
	{
			System.out.println(f);
	}

}
