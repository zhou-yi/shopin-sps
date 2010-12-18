package loja.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import loja.BaseDados;
import loja.Factura;
import loja.Produto;

/***********************************************************
 * @author FBA 2009/04/29
 * 
 ***********************************************************/
public class InterfaceJanelas extends JFrame implements ILoja
{
	private static final long		serialVersionUID	= 1L;

	private static int		FRAME_WIDTH			= 800;
	private static int		FRAME_HEIGHT		= 600;
	private static final String	separador			= "______________________________________________________________________________________________\n";

	private InterfaceJanelas		iface					= this;
	private JMenuBar					menu					= new JMenuBar();
	private JTextArea					texto;

	private InterfaceProduto			jProduto;
	private InterfaceFactura			jFactura;

	// private WFornecedor jFornecedor = new WFornecedor(false);
	// private WPOS jPOS = new WPOS(false);

	private BaseDados					bDados;

	/***********************************************************
	 * @param base
	 *           - a base de dados
	 ***********************************************************/
	public InterfaceJanelas(BaseDados base)
	{
		super("QUASAR P.O.S.");

		bDados = base;

		jProduto = new WProduto(base, this, false);
		jFactura = new WFactura(base, this, false);

		this.setJMenuBar(menu);

		menu.add(createFileMenu());
		menu.add(createOperationsMenu());
		menu.add(createManagementMenu());
		menu.add(createHelpMenu());

		final ImageIcon imageIcon = new ImageIcon("images/glasses_background.jpg");
		texto = new JTextArea(){
			Image	image	= imageIcon.getImage();
			
			{
				setOpaque(false);
			} // instance initializer

			public void paintComponent(Graphics g)
			{
				Point p = this.getLocation();
				g.drawImage(image, this.getWidth()-imageIcon.getIconWidth(), -p.y, this);
				super.paintComponent(g);
			}
		};

		texto.setEditable(false);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setFont(new Font("Courier", 40, 14));
//		texto.setBackground(Color.YELLOW);
//		texto.setForeground(Color.BLUE);

		JScrollPane textoComScroll = new JScrollPane(texto);

		this.add(textoComScroll, BorderLayout.CENTER);

		FRAME_WIDTH = imageIcon.getIconWidth();
		FRAME_HEIGHT = imageIcon.getIconHeight();
		
		// Get the size of the default screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation((dim.width - FRAME_WIDTH) / 2, (dim.height - FRAME_HEIGHT) / 2);		
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Creates the File menu.
	 * 
	 * @return the menu
	 */
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("Base");
		menu.add(createFileSaveItem());
		menu.add(createFileExitItem());
		return menu;
	}

	/**
	 * Creates the File->Exit menu item and sets its action listener.
	 * 
	 * @return the menu item
	 */
	public JMenuItem createFileSaveItem()
	{
		JMenuItem item = new JMenuItem("Guardar");

		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				bDados.salva();
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * Creates the File->Exit menu item and sets its action listener.
	 * 
	 * @return the menu item
	 */
	public JMenuItem createFileExitItem()
	{
		JMenuItem item = new JMenuItem("Sair");

		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * Creates the Operations menu.
	 * 
	 * @return the menu
	 */
	public JMenu createOperationsMenu()
	{
		JMenu menu = new JMenu("Operações");
		menu.add(createPointOfSaleItem());
		return menu;
	}

	/**
	 * Creates the File->Exit menu item and sets its action listener.
	 * 
	 * @return the menu item
	 */
	public JMenuItem createPointOfSaleItem()
	{
		JMenuItem item = new JMenuItem("POS");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				JOptionPane.showMessageDialog(iface, "Aqui irá aparecer o écran do Ponto de Venda ...", "Opção não implementada!",
						JOptionPane.WARNING_MESSAGE);
				// jPOS.visivel(true);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * Creates the Management menu.
	 * 
	 * @return the menu
	 */
	public JMenu createManagementMenu()
	{
		JMenu menu = new JMenu("Gestão");
		menu.add(createProdutosMenu());
		menu.add(createFornecedoresMenu());
		menu.add(createFacturasMenu());
		menu.add(createLimparItem("Limpar janela"));
		return menu;
	}

	/**
	 * Creates the Produtos submenu.
	 * 
	 * @return the menu
	 */
	public JMenu createProdutosMenu()
	{
		JMenu menu = new JMenu("Produtos");
		menu.add(createGereProdutosItem("Gerir"));
		menu.add(createListaProdutosItem("Listar"));
		return menu;
	}

	/**
	 * Creates the Fornecedores submenu.
	 * 
	 * @return the menu
	 */
	public JMenu createFornecedoresMenu()
	{
		JMenu menu = new JMenu("Fornecedores");
		menu.add(createGereFornecedoresItem("Gerir"));
		menu.add(createListaFornecedoresItem("Listar"));
		return menu;
	}

	/**
	 * Creates the Facturas submenu.
	 * 
	 * @return the menu
	 */
	public JMenu createFacturasMenu()
	{
		JMenu menu = new JMenu("Facturas");
		menu.add(createGereFacturasItem("Gerir"));
		menu.add(createListaFacturasItem("Listar"));
		return menu;
	}

	/**
	 * Creates a menu item to change the font face and set its action listener.
	 * 
	 * @return the menu item
	 */
	public JMenuItem createGereProdutosItem(final String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				jProduto.visivel(true);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * @param name
	 *           - the name of the menu item
	 * @return the menu item
	 */
	public JMenuItem createListaProdutosItem(String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				texto.append(Produto.cabecalho() + "\n");
				texto.append("----------------------------------------------------------------------------------------------\n");
				for (Produto p : bDados.produtos())
					texto.append(p.valores() + "\n");
				texto.append(separador);
				texto.repaint();
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * @param name
	 *           - the name of the menu item
	 * @return the menu item
	 */
	public JMenuItem createGereFornecedoresItem(final String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				// jFornecedor.visivel(true);
				JOptionPane.showMessageDialog(iface, "Aqui irá aparecer o écran de gestão de fornecedores ...",
						"Opção não implementada!", JOptionPane.WARNING_MESSAGE);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * @param name
	 *           - the name of the menu item
	 * @return the menu item
	 */
	public JMenuItem createListaFornecedoresItem(String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				// for (Fornecedor f: bDados.getFornecedores())
				// texto.append(f + "\n");
				// texto.append(separador);
				JOptionPane.showMessageDialog(iface, "Aqui irá aparecer a listagem de fornecedores ...", "Opção não implementada!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * @param name
	 *           - the name of the menu item
	 * @return the menu item
	 */
	public JMenuItem createGereFacturasItem(String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				jFactura.visivel(true);
				// JOptionPane.showMessageDialog(iface,
				// "Aqui irá aparecer o écran das facturas ...",
				// "Opção não implementada!", JOptionPane.WARNING_MESSAGE );
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * @param name
	 *           - the name of the menu item
	 * @return the menu item
	 */
	public JMenuItem createListaFacturasItem(String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				for (Factura f : bDados.facturas())
					texto.append(f + "\n");
				texto.append(separador);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * @param name
	 *           - the name of the menu item
	 * @return the menu item
	 */
	public JMenuItem createLimparItem(String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				texto.setText("");
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * Creates the Management menu.
	 * 
	 * @return the menu
	 */
	public JMenu createHelpMenu()
	{
		JMenu menu = new JMenu("Ajuda");
		menu.add(createAcercaItem("Acerca deste programa"));
		return menu;
	}

	/***********************************************************
	 * @param name
	 *           the name of the menu item
	 * @return the menu item
	 ***********************************************************/
	public JMenuItem createAcercaItem(String name)
	{
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				Icon icon = new ImageIcon("images/pos.gif");
				JOptionPane.showMessageDialog(null, "Obrigado por ter escolhido o QUASAR POS!\nCopyright FBA, 2010", "QUASAR P.O.S.",
						JOptionPane.PLAIN_MESSAGE, icon);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	@Override
	public InterfaceFactura criaJanelaFactura(BaseDados bd, JFrame contentor, boolean visivel)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceProduto criaJanelaProduto(BaseDados bd, JFrame contentor, boolean visivel)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
