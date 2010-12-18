package loja.gui;

import utilities.Data;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.InputMismatchException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import loja.BaseDados;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import loja.Produto.CodigoBarras;

/***********************************************************
 * @author FBA 2009/05/14
 * 
 ***********************************************************/
public class WProduto extends InterfaceProduto
{
	private static final long	serialVersionUID	= 1L;

	private static final int	FRAME_WIDTH			= 400;
	private static final int	FRAME_HEIGHT		= 300;
	private static final int	SHIFT_X				= 20;
	private static final int	SHIFT_Y				= 60;

	private static final int	CODIGO_SIZE			= 6;
	private static final int	PRECO_SIZE			= 6;
	private static final int	NOME_SIZE			= 30;
	private static final int	UNIDADE_SIZE		= 15;
	private static final int	BARRAS_SIZE			= 12;
	private static final int	ANO_SIZE				= 4;
	private static final int	MES_SIZE				= 2;
	private static final int	DIA_SIZE				= 2;

	// private JFrame chamadora;
	private JFrame					janelaProduto		= this;

	private JPanel					escolha				= new JPanel(new GridLayout(2, 1));
	private JPanel					painel				= new JPanel(new GridLayout(5, 2));
	private JPanel					botoes				= new JPanel(new GridLayout(1, 3));
	private JPanel					data					= new JPanel();

	private ButtonGroup			grupo					= new ButtonGroup();
	private JRadioButton			perecivel			= new JRadioButton("Perecível");
	private JRadioButton			nperecivel			= new JRadioButton("Não Perecível");

	private JButton				cancela				= new JButton("Cancela");
	private JButton				remove				= new JButton("Remove");
	private JButton				adiciona				= new JButton("Adiciona");

	private JLabel					l_codigo				= new JLabel("Código");
	private JLabel					l_preco_venda		= new JLabel("Preço de venda");
	private JLabel					l_nome				= new JLabel("Nome");
	private JLabel					l_unidade			= new JLabel("Unidade");
	private JLabel					l_codigo_barras	= new JLabel("Código de Barras");
	private JLabel					l_dia					= new JLabel("Dia");
	private JLabel					l_mes					= new JLabel("Mês");
	private JLabel					l_ano					= new JLabel("Ano");

	private final JTextField	f_codigo				= new JTextField(CODIGO_SIZE);
	private final JTextField	f_preco_venda		= new JTextField(PRECO_SIZE);
	private final JTextField	f_nome				= new JTextField(NOME_SIZE);
	private final JTextField	f_unidade			= new JTextField(UNIDADE_SIZE);
	private final JTextField	f_codigo_barras	= new JTextField(BARRAS_SIZE);
	private final JTextField	f_dia					= new JTextField(DIA_SIZE);
	private final JTextField	f_mes					= new JTextField(MES_SIZE);
	private final JTextField	f_ano					= new JTextField(ANO_SIZE);

	class AdicionaProduto implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				Produto p = le();
				getBDados().adiciona(p);
				JOptionPane.showMessageDialog(janelaProduto, "Inserido produto " + p + " na base de dados!", "Sucesso",
						JOptionPane.PLAIN_MESSAGE);
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Formato numérico incorrecto!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class RemoveProduto implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Produto p = le();

			if (JOptionPane.showConfirmDialog(janelaProduto, "Remover produto " + p + " da base de dados?", "Confirmar",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) 
			{
				getBDados().retira(p);
				
			}
		}
	}

	class CancelaProduto implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
		}
	}

	class CodigoProduto implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Produto p = getBDados().procuraProduto(Integer.parseInt(f_codigo.getText()));
			if (p != null) escreve(p);
		}
	}

	class LegendaData implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (perecivel.isSelected())
				data.setBorder(new TitledBorder(new EtchedBorder(), "Data de Validade"));
			else
				data.setBorder(new TitledBorder(new EtchedBorder(), "Data de Fabrico"));
		}
	}

	/***********************************************************
	 * @param bd
	 * @param contentor
	 * @param visivel
	 ***********************************************************/
	public WProduto(BaseDados bd, JFrame contentor, boolean visivel)
	{
		super(bd, contentor, visivel);

		ActionListener list_adiciona = new AdicionaProduto();
		adiciona.addActionListener(list_adiciona);

		ActionListener list_remove = new RemoveProduto();
		remove.addActionListener(list_remove);

		ActionListener list_cancela = new CancelaProduto();
		cancela.addActionListener(list_cancela);

		ActionListener list_legenda = new LegendaData();
		perecivel.addActionListener(list_legenda);
		nperecivel.addActionListener(list_legenda);
		nperecivel.setSelected(true);

		list_legenda.actionPerformed(null);
		// to guarantee that the date legend is set on boot

		escolha.setBorder(new TitledBorder(new EtchedBorder(), " Tipo de Produto"));
		grupo.add(perecivel);
		grupo.add(nperecivel);
		escolha.add(perecivel);
		escolha.add(nperecivel);

		painel.setBorder(new TitledBorder(new EtchedBorder(), "Produto"));
		painel.add(l_codigo);
		painel.add(f_codigo);

		ActionListener le_produto = new CodigoProduto();
		f_codigo.addActionListener(le_produto);

		painel.add(l_preco_venda);
		painel.add(f_preco_venda);
		painel.add(l_nome);
		painel.add(f_nome);
		painel.add(l_unidade);
		painel.add(f_unidade);
		painel.add(l_codigo_barras);
		painel.add(f_codigo_barras);

		data.add(l_dia);
		data.add(f_dia);
		data.add(l_mes);
		data.add(f_mes);
		data.add(l_ano);
		data.add(f_ano);

		botoes.add(cancela);
		botoes.add(remove);
		botoes.add(adiciona);

		this.add(botoes, BorderLayout.NORTH);
		this.add(escolha, BorderLayout.WEST);
		this.add(painel, BorderLayout.CENTER);
		this.add(data, BorderLayout.SOUTH);

		this.chamadora = contentor;
		this.setVisible(visivel);
		this.setAlwaysOnTop(true);

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private Produto le() throws NumberFormatException
	{
		char tipo = perecivel.isSelected() ? 'P' : 'N';
		int codigo = Integer.parseInt(f_codigo.getText());
		double preco_venda = Double.parseDouble(f_preco_venda.getText());
		String nome = f_nome.getText();
		String unidade = f_unidade.getText();
		int ano = Integer.parseInt(f_ano.getText());
		int mes = Integer.parseInt(f_mes.getText());
		int dia = Integer.parseInt(f_dia.getText());
		String barras = f_codigo_barras.getText();

		Produto p = null;
		switch (tipo)
		{
			case 'p':
			case 'P':
				p = new Perecivel(codigo, preco_venda, nome, unidade, new CodigoBarras(barras), new Data(dia, mes, ano));
				break;
			case 'n':
			case 'N':
				p = new NaoPerecivel(codigo, preco_venda, nome, unidade, new CodigoBarras(barras), new Data(dia, mes, ano));
				break;
			default:
				System.out.println("Tipo de produto inválido");
				break;
		}
		return p;
	}

	/***********************************************************
	 * @param p
	 ***********************************************************/
	protected void escreve(Produto p)
	{
		perecivel.setSelected(p instanceof Perecivel);
		f_codigo.setText(p.getCodigo() + "");
		f_preco_venda.setText(p.getPreco() + "");
		f_nome.setText(p.getNome());
		f_unidade.setText(p.getUnidade());
		Data d = p instanceof Perecivel ? ((Perecivel) p).getValidade() : ((NaoPerecivel) p).getFabricado_em();
		f_ano.setText(d.getAno() + "");
		f_mes.setText(d.getMes() + "");
		f_dia.setText(d.getDia() + "");

		f_codigo_barras.setText(p.getCodigoBarras() + "");
	}

	/***********************************************************
	 * @param estado
	 ***********************************************************/
	public void visivel(boolean estado)
	{
		this.setLocation(chamadora.getX() + SHIFT_X, chamadora.getY() + SHIFT_Y);
		this.setVisible(estado);
	}

}
