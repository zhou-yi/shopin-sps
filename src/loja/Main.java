package loja;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import loja.gui.InterfaceJanelas;
import loja.gui.ILoja;
import loja.gui.InterfaceTextual;

/***********************************************************
 * @author FBA 2009/03/10
 * 
 ***********************************************************/
public class Main
{
	private static BaseDados		dados;
	private static ILoja	iface;

	/***********************************************************
	 * @param args
	 * @throws IOException
	 * @throws IOException
	 ***********************************************************/
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// Guarantees the native look and feel (e.g. Windows, Linux, Mac)
		}
		catch (Exception e)
		{
			System.err.println("Falling back to the plain Java look and feel ...");
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de configuração!", JOptionPane.WARNING_MESSAGE);
		}

		dados = new BaseDados("data/produtos.txt", "data/facturas.txt", "data/linhas_factura.txt");
		try
		{
			dados.carrega();
			System.out.println("Carregou " + dados.produtos().size() + " produtos.");
			System.out.println("Carregou " + dados.facturas().size() + " facturas.");
			
			// Escolha aqui qual das interfaces pretende
			iface = new InterfaceJanelas(dados);
//			iface = new InterfaceTextual(dados);
		}
		catch (UserCancelException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Programa terminado!", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (UserErrorException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Programa terminado!", JOptionPane.ERROR_MESSAGE);
		}
		catch (InputMismatchException e)
		{
			JOptionPane.showMessageDialog(null, "Ficheiro de dados incorrecto ou corrompido!", "Programa terminado!",
					JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			Icon icon = new ImageIcon("images/pos.gif");
			JOptionPane.showMessageDialog(null, "Obrigado por ter escolhido o QUASAR POS!\nCopyright FBA, 2010", "Point Of Sale",
					JOptionPane.PLAIN_MESSAGE, icon);
		}
	}

}
