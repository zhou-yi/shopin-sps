package loja.thinlet;

import java.io.IOException;

import javax.swing.JOptionPane;

import loja.BaseDados;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import loja.Produto.CodigoBarras;

import thinlet.FrameLauncher;
import thinlet.Thinlet;
import utilities.Data;

public class JanelaProdutos extends Thinlet {

	private Produto p;

	public JanelaProdutos() throws IOException {
		add(parse("JanelaProdutos.xml"));

	}

	public void cancela() {
		JanelaInicial.frame1.setVisible(false);


	}

	public void remove(String codigo, String preco_venda, String nome,
			String unidade, String ano, String mes, String dia, String barras,
			boolean check) {
		if (preco_venda.equals("") || nome.equals("") || unidade.equals("")
				|| ano.equals("") || mes.equals("") || dia.equals("")
				|| barras.equals("")) {
			JOptionPane
					.showMessageDialog(
							null,
							"Deixou campos por preencher .\n Por Favor preencha todos os campos.",
							"Janela de informação",
							JOptionPane.INFORMATION_MESSAGE);
		} else {

			int c = Integer.parseInt(codigo);
			double pv = Double.parseDouble(preco_venda);

			int a = Integer.parseInt(ano);
			int m = Integer.parseInt(dia);
			int d = Integer.parseInt(ano);
			if (JOptionPane.showConfirmDialog(null, "Remover produto " + p
					+ " da base de dados?", "Confirmar",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				JanelaInicial.dados.retira(p);

			}
		}
	}

	public void adiciona(String codigo, String preco_venda, String nome,
			String unidade, String ano, String mes, String dia, String barras,
			boolean check) {

		if (preco_venda.equals("") || nome.equals("") || unidade.equals("")
				|| ano.equals("") || mes.equals("") || dia.equals("")
				|| barras.equals("")) {
			JOptionPane
					.showMessageDialog(
							null,
							"Deixou campos por preencher .\n Por Favor preencha todos os campos.",
							"Janela de informação",
							JOptionPane.INFORMATION_MESSAGE);
		} else {

			int c = Integer.parseInt(codigo);
			double pv = Double.parseDouble(preco_venda);

			int a = Integer.parseInt(ano);
			int m = Integer.parseInt(dia);
			int d = Integer.parseInt(ano);

			if (check == true) {

				p = new Perecivel(c, pv, nome, unidade,
						new CodigoBarras(barras), new Data(d, m, a));

			}

			else {

				p = new NaoPerecivel(c, pv, nome, unidade, new CodigoBarras(
						barras), new Data(d, m, a));
			}

			JanelaInicial.dados.adiciona(p);

			JOptionPane.showMessageDialog(null, "Inserido produto " + p
					+ " na base de dados!", "Sucesso",
					JOptionPane.PLAIN_MESSAGE);

		}

	}

}
