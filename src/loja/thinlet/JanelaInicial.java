package loja.thinlet;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import thinlet.FrameLauncher;
import thinlet.Thinlet;

import loja.BaseDados;
import loja.Factura;
import loja.Produto;
import loja.UserCancelException;
import loja.UserErrorException;
import loja.gui.InterfaceJanelas;
import loja.gui.ILoja;
import loja.gui.InterfaceTextual;

/***********************************************************
 * @author FBA 2009/03/10
 * 
 ***********************************************************/

public class JanelaInicial extends Thinlet {

	public static BaseDados dados;

	public static FrameLauncher frame1;
	public static FrameLauncher frame2;

	public JanelaInicial() throws IOException {
		add(parse("JanelaInicial.xml"));

	}

	public static void main(String[] args) throws Exception {
		FrameLauncher frame = (new FrameLauncher("Quasar P.O.S",
				new JanelaInicial(), 450, 500));
		frame.setResizable(false);

		dados = new BaseDados("data/produtos.txt", "data/facturas.txt",
				"data/linhas_factura.txt");

		try {
			dados.carrega();
			System.out.println("Carregou " + dados.produtos().size()
					+ " produtos.");
			System.out.println("Carregou " + dados.facturas().size()
					+ " facturas.");

		} catch (UserCancelException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Programa terminado!", JOptionPane.INFORMATION_MESSAGE);
		} catch (UserErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Programa terminado!", JOptionPane.ERROR_MESSAGE);
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(null,
					"Ficheiro de dados incorrecto ou corrompido!",
					"Programa terminado!", JOptionPane.ERROR_MESSAGE);
		} finally {
			Icon icon = new ImageIcon("images/pos.gif");
			JOptionPane
					.showMessageDialog(
							null,
							"Obrigado por ter escolhido o QUASAR POS!\nCopyright FBA, 2010",
							"Point Of Sale", JOptionPane.PLAIN_MESSAGE, icon);
		}
	}

	public void sair() {
		System.exit(1);

	}

	public void guardar() {

		dados.salva();

	}

	public void POS() {

		JOptionPane.showMessageDialog(null,
				"Aqui irá aparecer o écran do Ponto de Venda ...",
				"Opção não implementada!", JOptionPane.WARNING_MESSAGE);
		// jPOS.visivel(true);
	}

	public void abreJanelaProdutos() throws IOException {
		frame1 = (new FrameLauncher("Produtos", new JanelaProdutos(), 450, 340));
		frame1.setResizable(false);

	}

	public void FornGerir() {
		// jFornecedor.visivel(true);
		JOptionPane.showMessageDialog(null,
				"Aqui irá aparecer o écran de gestão de fornecedores ...",
				"Opção não implementada!", JOptionPane.WARNING_MESSAGE);
	}

	public void FornListar() {
		JOptionPane.showMessageDialog(null,
				"Aqui irá aparecer a listagem de fornecedores ...",
				"Opção não implementada!", JOptionPane.WARNING_MESSAGE);
	}

	public void listarProdutos(Object texto) {
		String s = (Produto.cabecalho() + "\n");
		s += "-------------------------------------------------------------------------------------------------------------------------------------------\n";
		for (Produto p : dados.produtos()) {
			s += p.valores() + "\n";
		}
		setString(texto, "text", s);

	}

	public void abreJanelaFact() throws IOException {
		frame2 = (new FrameLauncher("Factura",
				new JanelaFactura(), 450, 360));
		frame2.setResizable(false);
		

	}

	public void FactListar(Object texto) {
		String s = "";

		for (Factura f : dados.facturas()) {
			s += f + "\n";
		}
		setString(texto, "text", s);

	}

	public void limparJanela(Object texto) {

		setString(texto, "text", "");

	}

	public void ajuda() {
		Icon icon = new ImageIcon("images/pos.gif");
		JOptionPane
				.showMessageDialog(
						null,
						"Obrigado por ter escolhido o QUASAR POS!\nCopyright FBA, 2010",
						"QUASAR P.O.S.", JOptionPane.PLAIN_MESSAGE, icon);

	}

	public BaseDados getBaseDados() {
		return dados;
	}

	public Frame getframe1() {
		return frame1;
	}

	public Frame getframe2() {
		return frame2;
	}

}