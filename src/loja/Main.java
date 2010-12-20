package loja;

import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import loja.gui.ILoja;
import loja.thinlet.InterfaceXML;

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

		dados = new BaseDados(
				"data/produtos.txt", "data/facturas.txt", "data/linhas_factura.txt", "data/fornecedores.txt", "data/encomenda.txt", "data/linhas_encomenda.txt");
		try
		{
			dados.carrega();
			System.out.println("Carregou " + dados.produtos().size() + " produtos.");
			System.out.println("Carregou " + dados.facturas().size() + " facturas.");

			//		 Escolha aqui qual das interfaces pretende
			//		iface = new InterfaceJanelas(dados);
			//			iface = new InterfaceTextual(dados);

			//			try {
			//				new FrameLauncher("Loja", new InterfaceXML(dados), 400, 300);
			//			} catch (Exception e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
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
			//			Icon icon = new ImageIcon("images/pos.gif");
			//			JOptionPane.showMessageDialog(null, "Você esta a utilizar o software 'POS' mais Poderoso do mundo !\nCopyright Software Power Solutions", "Software Power Solutions - POS",
			//					JOptionPane.PLAIN_MESSAGE, icon);
			//		boolean passa_login = false;
			//		Authentication auth = new Authentication(null);
			//		do{
			//		passa_login = auth.login();
			//		}while(!passa_login || (!auth.getLogin().equals("paulo gil") || !auth.getPassword().equals("password")));


			try {
				InterfaceXML int_xml = new InterfaceXML(dados);
				int_xml.Mostra();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}



	}

}
