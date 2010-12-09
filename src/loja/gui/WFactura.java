package loja.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import loja.BaseDados;
import loja.Factura;
import loja.LinhaFactura;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import loja.LinhaFactura.Criteria;
import loja.LinhaFactura.Order;

import utilities.Data;

/***********************************************************
 * @author FBA 2009/05/11
 * 
 ***********************************************************/
public class WFactura extends InterfaceFactura
{
	private static final int	FRAME_WIDTH		= 700;
	private static final int	FRAME_HEIGHT	= 550;
	private static final int	SHIFT_X			= 20;
	private static final int	SHIFT_Y			= 60;

	private static final int	CODIGO_SIZE		= 6;
	private static final int	QUANTO_SIZE		= 6;
	private static final int	SUBTOTAL_SIZE	= 6;
	private static final int	VALOR_SIZE		= 6;

	private static final int	ANO_SIZE			= 4;
	private static final int	MES_SIZE			= 2;
	private static final int	DIA_SIZE			= 2;

	private JFrame					janelaFactura	= this;

	private final JComboBox		cb_produtos		= new JComboBox();

	private final JTextField	f_codigo			= new JTextField(CODIGO_SIZE);
	private final JTextField	f_dia				= new JTextField(DIA_SIZE);
	private final JTextField	f_mes				= new JTextField(MES_SIZE);
	private final JTextField	f_ano				= new JTextField(ANO_SIZE);

	private final JTextField	f_subtotal		= new JTextField(SUBTOTAL_SIZE);
	private final JTextField	f_quanto			= new JTextField(QUANTO_SIZE);
	private final JTextField	f_valor			= new JTextField(VALOR_SIZE);

	private final JTextArea		texto				= new JTextArea();

	/***********************************************************
	 * @param bd
	 * @param contentor
	 * @param visivel
	 ***********************************************************/
	public WFactura(BaseDados bd, JFrame contentor, boolean visivel)
	{
		super(bd, contentor, visivel);

		this.add(painelNorte(), BorderLayout.NORTH);
		this.add(painelOeste(), BorderLayout.WEST);
		this.add(painelCentral(), BorderLayout.CENTER);
		this.add(painelSul(), BorderLayout.SOUTH);

		this.setAlwaysOnTop(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	/***********************************************************
	 * @param f
	 ***********************************************************/
	protected void escreve(Factura f)
	{
		texto.setText("");
		if (f == null)
		{
			f_codigo.setText("");
			f_ano.setText("");
			f_mes.setText("");
			f_dia.setText("");
		}
		else
		{
			f_codigo.setText(f.getNumero() + "");
			Data d = f.getData();
			f_ano.setText(d.getAno() + "");
			f_mes.setText(d.getMes() + "");
			f_dia.setText(d.getDia() + "");
			texto.append(f + "\n");
		}
	}

	/***********************************************************
	 * @param estado
	 ***********************************************************/
	public void visivel(boolean estado)
	{
		this.setLocation(chamadora.getX() + SHIFT_X, chamadora.getY() + SHIFT_Y);
		super.visivel(estado);
	}

	/***********************************************************
	 * 
	 ***********************************************************/
	private void inicializaValores()
	{
		Object p = cb_produtos.getSelectedItem();
		if (p != null)
		{
			f_quanto.setText("1");
			f_valor.setText(((Produto) p).getPreco() + "");
			f_subtotal.setText(f_valor.getText());
		}
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelNorte()
	{
		class L_CodigoFactura implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int numero = Integer.parseInt(f_codigo.getText());
				setCorrente(getBDados().procuraFactura(numero));
				if (factura_corrente == null) factura_corrente = new Factura(numero, Data.hoje(), false);
				escreve(factura_corrente);
			}
		}

		JLabel l_codigo = new JLabel("Código");
		JLabel l_dia = new JLabel("Dia");
		JLabel l_mes = new JLabel("Mês");
		JLabel l_ano = new JLabel("Ano");

		ActionListener list_factura = new L_CodigoFactura();
		f_codigo.addActionListener(list_factura);

		f_codigo.setText(Factura.getUltimoNumero() + "");

		JPanel p_north = new JPanel(new GridLayout(1, 4));

		p_north.add(l_codigo);
		p_north.add(f_codigo);
		p_north.add(l_dia);
		p_north.add(f_dia);
		p_north.add(l_mes);
		p_north.add(f_mes);
		p_north.add(l_ano);
		p_north.add(f_ano);

		return p_north;
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelCentral()
	{
		JPanel p_center = new JPanel(new GridLayout(2, 1));
		p_center.setBorder(new TitledBorder(new EtchedBorder(), "Linhas da Factura"));
		texto.setEditable(false);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setFont(new Font("Courier", 40, 12));
		JScrollPane textoComScroll = new JScrollPane(texto);
		p_center.add(textoComScroll, BorderLayout.CENTER);

		p_center.add(painelOrdenacao());

		return p_center;
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelOrdenacao()
	{
		final JRadioButton rb_crescente = new JRadioButton("Crescente");
		final JRadioButton rb_decrescente = new JRadioButton("Decrescente");
		final JRadioButton rb_ordena_nome = new JRadioButton("por produto");
		final JRadioButton rb_ordena_valor = new JRadioButton("por valor");

		class L_Ordenacao implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				LinhaFactura.Order order = null;
				LinhaFactura.Criteria criteria = null;
				if (rb_crescente.isSelected()) order = Order.ASCENDING;
				if (rb_decrescente.isSelected()) order = Order.DESCENDING;
				if (rb_ordena_valor.isSelected()) criteria = Criteria.BYVALUE;
				if (rb_ordena_nome.isSelected()) criteria = Criteria.BYNAME;

				LinhaFactura.setSort(order, criteria);
				escreve(factura_corrente);
			}
		}

		ActionListener list_ordena = new L_Ordenacao();
		rb_crescente.addActionListener(list_ordena);
		rb_decrescente.addActionListener(list_ordena);
		rb_ordena_valor.addActionListener(list_ordena);
		rb_ordena_nome.addActionListener(list_ordena);

		ButtonGroup grupo_sentido = new ButtonGroup();
		grupo_sentido.add(rb_crescente);
		grupo_sentido.add(rb_decrescente);
		rb_crescente.setSelected(true);

		JPanel p_sentido = new JPanel(new GridLayout(2, 1));
		p_sentido.setBorder(new TitledBorder(new EtchedBorder(), "Sentido da Ordenação"));
		p_sentido.add(rb_crescente);
		p_sentido.add(rb_decrescente);

		ButtonGroup grupo_campo = new ButtonGroup();
		grupo_campo.add(rb_ordena_valor);
		grupo_campo.add(rb_ordena_nome);
		rb_ordena_valor.setSelected(true);

		JPanel p_campo = new JPanel(new GridLayout(2, 1));
		p_campo.setBorder(new TitledBorder(new EtchedBorder(), "Tipo da Ordenação"));
		p_campo.add(rb_ordena_valor);
		p_campo.add(rb_ordena_nome);

		JPanel p_ordena = new JPanel(new GridLayout(1, 2));
		p_ordena.add(p_sentido);
		p_ordena.add(p_campo);

		return p_ordena;
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelOeste()
	{
		JPanel p_west = new JPanel(new GridLayout(4, 1));

		p_west.add(painelTipoProduto());
		p_west.add(cb_produtos);
		p_west.add(painelDados());
		p_west.add(painelConfirmacao());

		return p_west;
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelTipoProduto()
	{
		final JRadioButton rb_perecivel = new JRadioButton("Perecível");
		final JRadioButton rb_nperecivel = new JRadioButton("Não Perecível");

		class L_TipoProduto implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				cb_produtos.removeAllItems();

				for (Produto p : getBDados().produtos())
				{
					if (rb_perecivel.isSelected() && p instanceof Perecivel) cb_produtos.addItem(p);
					if (rb_nperecivel.isSelected() && p instanceof NaoPerecivel) cb_produtos.addItem(p);
				}
				inicializaValores();
			}
		}

		ButtonGroup grupo_perecivel = new ButtonGroup();
		grupo_perecivel.add(rb_perecivel);
		grupo_perecivel.add(rb_nperecivel);

		ActionListener list_escolha = new L_TipoProduto();
		rb_perecivel.addActionListener(list_escolha);
		rb_nperecivel.addActionListener(list_escolha);

		JPanel p_escolha = new JPanel(new GridLayout(2, 1));
		p_escolha.setBorder(new TitledBorder(new EtchedBorder(), "Tipo de Produto"));
		p_escolha.add(rb_perecivel);
		p_escolha.add(rb_nperecivel);

		// for initialization purposes
		rb_perecivel.setSelected(true);
		rb_nperecivel.setSelected(false);
		list_escolha.actionPerformed(null);
		// cb_produtos.setSelectedIndex(0);

		return p_escolha;
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelDados()
	{
		class L_Subtotal implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				f_subtotal.setText(Double.parseDouble(f_quanto.getText()) * Double.parseDouble(f_valor.getText()) + "");
			}
		}

		class L_ComboProdutos implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				inicializaValores();
			}
		}

		JPanel p_dados = new JPanel(new GridLayout(3, 2));
		p_dados.setBorder(new TitledBorder(new EtchedBorder(), "Factura"));

		JLabel l_quanto = new JLabel("Quantidade");
		JLabel l_valor = new JLabel("Valor unitário");
		JLabel l_subtotal = new JLabel("Subtotal");

		ActionListener list_subtotal = new L_Subtotal();
		f_quanto.addActionListener(list_subtotal);
		f_valor.addActionListener(list_subtotal);

		ActionListener list_produtos = new L_ComboProdutos();
		cb_produtos.addActionListener(list_produtos);

		p_dados.add(l_quanto);
		p_dados.add(f_quanto);
		p_dados.add(l_valor);
		p_dados.add(f_valor);
		p_dados.add(l_subtotal);
		p_dados.add(f_subtotal);

		return p_dados;
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelConfirmacao()
	{
		class L_AdicionaLinha implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if (factura_corrente.isAnulada())
					JOptionPane.showMessageDialog(janelaFactura, "Não se pode modificar uma factura anulada!", "Erro!",
							JOptionPane.ERROR_MESSAGE);
				else
				{
					factura_corrente.adiciona((Produto) cb_produtos.getSelectedItem(), Double.parseDouble(f_quanto.getText()));
					escreve(factura_corrente);
				}
			}
		}

		class L_RetiraLinha implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if (factura_corrente.isAnulada())
					JOptionPane.showMessageDialog(janelaFactura, "Não se pode modificar uma factura anulada!", "Erro!",
							JOptionPane.ERROR_MESSAGE);
				else
				{
					factura_corrente.retira((Produto) cb_produtos.getSelectedItem(), Double.parseDouble(f_quanto.getText()));
					escreve(factura_corrente);
				}
			}
		}

		JButton b_adiciona = new JButton("Adiciona");
		JButton b_retira = new JButton("Retira");

		ActionListener list_adiciona = new L_AdicionaLinha();
		b_adiciona.addActionListener(list_adiciona);

		ActionListener list_retira = new L_RetiraLinha();
		b_retira.addActionListener(list_retira);

		JPanel p_confirmacao = new JPanel(new GridLayout(1, 2));
		p_confirmacao.add(b_adiciona);
		p_confirmacao.add(b_retira);

		return p_confirmacao;
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private JPanel painelSul()
	{
		class L_CancelaFactura implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
			}
		}

		class L_ApagaFactura implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if (JOptionPane.showConfirmDialog(janelaFactura, "Apagar factura " + factura_corrente + " da base de dados?",
						"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) getBDados().retira(factura_corrente);
				factura_corrente = new Factura();
				escreve(factura_corrente);
			}
		}

		class L_AnulaFactura implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if (JOptionPane.showConfirmDialog(janelaFactura, "Anular factura " + factura_corrente + "?", "Confirmar",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0)
				{
					factura_corrente.setAnulada();
					escreve(factura_corrente);
				}
			}
		}

		class L_FechaFactura implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				getBDados().adiciona(factura_corrente);
				JOptionPane.showMessageDialog(janelaFactura, "Inserida factura " + factura_corrente + " na base de dados!", "Sucesso",
						JOptionPane.PLAIN_MESSAGE);
			}
		}

		JPanel p_south = new JPanel();

		JButton b_cancela = new JButton("Cancela");
		JButton b_apaga = new JButton("Apaga");
		JButton b_anula = new JButton("Anula");
		JButton b_fecha = new JButton("Fecha");

		ActionListener list_cancela = new L_CancelaFactura();
		b_cancela.addActionListener(list_cancela);

		ActionListener list_apaga = new L_ApagaFactura();
		b_apaga.addActionListener(list_apaga);

		ActionListener list_anula = new L_AnulaFactura();
		b_anula.addActionListener(list_anula);

		ActionListener list_fecha = new L_FechaFactura();
		b_fecha.addActionListener(list_fecha);

		p_south.add(b_cancela);
		p_south.add(b_apaga);
		p_south.add(b_anula);
		p_south.add(b_fecha);

		return p_south;
	}
}
