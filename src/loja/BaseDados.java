package loja;

import utilities.Data;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections; //import java.util.LinkedList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner; //import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane; //import java.util.Vector;
import javax.swing.filechooser.FileFilter;

import loja.Produto.CodigoBarras;

/***********************************************************
 * @author FBA 2009/05/09
 * 
 ***********************************************************/
public final class BaseDados
{
	private static String	ficheiroProdutos;
	private static String	ficheiroFacturas;
	private static String	ficheiroLinhasFactura;

	// The only requirement for data structures to be used in this
	// database is that for each type T it provides the List<T> interface

	private List<Produto>	produtos	= new ArrayList<Produto>();
	private List<Factura>	facturas	= new ArrayList<Factura>();

	// private List<Produto> produtos = new Vector<Produto>();
	// private List<Factura> facturas = new Vector<Factura>();

	// private List<Produto> produtos = new Stack<Produto>();
	// private List<Factura> facturas = new Stack<Factura>();

	// private List<Produto> produtos = new LinkedList<Produto>();
	// private List<Factura> facturas = new LinkedList<Factura>();

	/***********************************************************
     * 
     ***********************************************************/
	public BaseDados(String fProdutos, String fFacturas, String fLinhasFactura)
	{
		ficheiroProdutos = fProdutos;
		ficheiroFacturas = fFacturas;
		ficheiroLinhasFactura = fLinhasFactura;
	}

	/***********************************************************
	 * @return the produtos
	 ***********************************************************/
	public List<Produto> produtos()
	{
		return produtos;
	}

	/***********************************************************
	 * @return the facturas
	 ***********************************************************/
	public List<Factura> facturas()
	{
		return facturas;
	}

	/***********************************************************
	 * @param p
	 * @return
	 ***********************************************************/
	public boolean existe(Produto p)
	{
		return produtos.contains(p);
	}

	/***********************************************************
	 * @param f
	 * @return
	 ***********************************************************/
	public boolean existe(Factura f)
	{
		return facturas.contains(f);
	}

	/***********************************************************
	 * @param codigo
	 * @return
	 ***********************************************************/
	public Produto procuraProduto(int codigo)
	{
		int posicao = produtos.indexOf(new Perecivel(codigo, 0, "", "", null, null));
		// Notice that two products are considered equal if they have the same
		// code

		return posicao == -1 ? null : produtos.get(posicao);
	}

	/***********************************************************
	 * @param codigo
	 * @return
	 ***********************************************************/
	public Factura procuraFactura(int codigo)
	{
		int posicao = facturas.indexOf(new Factura(codigo, null, false));
		return posicao == -1 ? null : facturas.get(posicao);
	}

	/***********************************************************
	 * @param p
	 ***********************************************************/
	public void adiciona(Produto p)
	{
		if (existe(p)) retira(p);
		produtos.add(p);
	}

	/***********************************************************
	 * @param f
	 ***********************************************************/
	public void adiciona(Factura f)
	{
		if (existe(f)) retira(f);
		facturas.add(f);
	}

	/***********************************************************
	 * @param p
	 ***********************************************************/
	public void retira(Produto p)
	{
		assert existe(p);
		produtos.remove(p);
	}

	/***********************************************************
	 * @param f
	 ***********************************************************/
	public void retira(Factura f)
	{
		assert existe(f);
		facturas.remove(f);
	}

	/***********************************************************
	 * @throws IOException
	 * @throws UserCancelException 
	 * @throws UserErrorException 
	 * 
	 ***********************************************************/
	public void carrega() throws UserCancelException, UserErrorException, InputMismatchException
	{
		carregaProdutos(inicializaFicheiro(ficheiroProdutos));
		carregaFacturas(inicializaFicheiro(ficheiroFacturas), inicializaFicheiro(ficheiroLinhasFactura));
	}

	/***********************************************************
	 * @throws UserCancelException
	 * @throws UserErrorException 
	 * @throws IOException
	 * 
	 ***********************************************************/
	public Scanner inicializaFicheiro(String filename) throws UserCancelException, UserErrorException
	{
		FileReader reader = null;
		
		do
		{
			try
			{
				reader = new FileReader(filename);
			}
			catch (FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de leitura!", JOptionPane.ERROR_MESSAGE);

				System.err.println("Erro na abertura do ficheiro: " + filename);
				
				// Create a file chooser
				JFileChooser fc = new JFileChooser();

			    //Add a custom file filter to select "txt" files only
				fc.setFileFilter(new MyFilter());
				
				fc.setSelectedFile(new File(filename));
				fc.setCurrentDirectory(new File("data/"));
				
				switch (fc.showOpenDialog(null))
				{
					case JFileChooser.APPROVE_OPTION:
						filename = fc.getSelectedFile().getPath();
						break;
						
					case JFileChooser.CANCEL_OPTION:
						throw new UserCancelException("Utilizador cancelou abertura de ficheiro!");
						
					case JFileChooser.ERROR_OPTION:
						throw new UserErrorException("Ocorreu um erro no diálogo de abertura de ficheiro!");
				}
			}
		}
		while (reader == null);
		return new Scanner(reader);
	}

	/***********************************************************
	 * @return the produtos
	 ***********************************************************/
	public void salva()
	{
		try
		{
			guardaProdutos(new PrintWriter(new File(ficheiroProdutos)));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}

		try
		{
			guardaFacturas(new PrintWriter(new File(ficheiroFacturas)), new PrintWriter(new File(ficheiroLinhasFactura)));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}

		// try
		// {
		// guardaFornecedores(new PrintWriter(new File(ficheiroFornecedores)));
		// } catch (FileNotFoundException e)
		// {
		// e.printStackTrace();
		// return;
		// }
	}

	/***********************************************************
	 * @param f_facturas
	 ***********************************************************/
	private void carregaFacturas(Scanner f_facturas, Scanner f_linhas_factura) throws InputMismatchException
	{
		while (f_facturas.hasNext())
		{
			int numero = f_facturas.nextInt();
			int ano = f_facturas.nextInt();
			int mes = f_facturas.nextInt();
			int dia = f_facturas.nextInt();
			boolean anulada = f_facturas.nextBoolean();
			facturas.add(new Factura(numero, new Data(dia, mes, ano), anulada));
		}

		while (f_linhas_factura.hasNext())
		{
			String line = f_linhas_factura.nextLine();
			String[] fields = line.split(" ");

			int codigo_factura = Integer.parseInt(fields[0]);
			int codigo_produto = Integer.parseInt(fields[1]);
			double quantidade = Double.parseDouble(fields[2]);
			double valor = Double.parseDouble(fields[3]);

			Produto p = procuraProduto(codigo_produto);
			if (p != null)
			{
				Factura f = procuraFactura(codigo_factura);
				if (f != null)
					f.adiciona(new LinhaFactura(f, p, quantidade, valor));
				else
					System.out.println("Não existe uma factura com o codigo " + codigo_factura + " a que se possam adicionar linhas!");
			}
			else
				System.out.println("Não existe o produto com o codigo " + codigo_produto + ", que se possa facturar!");
		}
		// for (int i=0; i< facturas.size(); i++)
		// System.out.println(facturas.get(i));
	}

	/***********************************************************
	 * @param out
	 ***********************************************************/
	private void guardaFacturas(PrintWriter fac, PrintWriter lin_fac)
	{
		Collections.sort(facturas);
		for (Factura f : facturas)
		{
			fac.println(f.getNumero() + " " + f.getData().getAno() + " " + f.getData().getMes() + " " + f.getData().getDia() + " "
					+ f.isAnulada());

			for (LinhaFactura linha : f.getLinhasFactura())
				lin_fac.println(f.getNumero() + " " + linha.getProduto().getCodigo() + " " + linha.getQuantidade() + " "
						+ linha.getValor_unitario());
		}
		fac.close();
		lin_fac.close();
	}

	/***********************************************************
	 * @param in
	 ***********************************************************/
	private void carregaProdutos(Scanner in) throws InputMismatchException
	{
		while (in.hasNext())
		{
			String line = in.nextLine();
			String[] fields = line.split("\t");

			char tipo = fields[0].charAt(0);
			int codigo = Integer.parseInt(fields[1]);
			double preco_venda = Double.parseDouble(fields[2]);
			String nome = fields[3];
			String unidade = fields[4];
			int ano = Integer.parseInt(fields[5]);
			int mes = Integer.parseInt(fields[6]);
			int dia = Integer.parseInt(fields[7]);
			String barras = fields[8];

			switch (tipo)
			{
				case 'p':
				case 'P':
					produtos.add(new Perecivel(codigo, preco_venda, nome, unidade, new CodigoBarras(barras), new Data(dia, mes, ano)));
					break;
				case 'n':
				case 'N':
					produtos.add(new NaoPerecivel(codigo, preco_venda, nome, unidade, new CodigoBarras(barras), new Data(dia, mes, ano)));
					break;
				default:
					System.out.println("Tipo de produto inválido");
					break;
			}
			// System.out.println(produtos.get(produtos.size()-1));
		}
		// for (int i=0; i< produtos.size(); i++)
		// System.out.println(produtos.get(i));
	}

	/***********************************************************
	 * @param out
	 ***********************************************************/
	private void guardaProdutos(PrintWriter out)
	{
		Data dia = null;
		char tipo = ' ';
		Collections.sort(produtos);
		for (Produto p : produtos)
		{
			if (p instanceof Perecivel)
			{
				dia = ((Perecivel) p).getValidade();
				tipo = 'P';
			}
			if (p instanceof NaoPerecivel)
			{
				dia = ((NaoPerecivel) p).getFabricado_em();
				tipo = 'N';
			}
			out.println(tipo + "\t" + p.getCodigo() + "\t" + p.getPreco() + "\t" + p.getNome() + "\t" + p.getUnidade() + "\t"
					+ dia.getAno() + "\t" + dia.getMes() + "\t" + dia.getDia() + "\t" + p.getCodigoBarras());
		}
		out.close();
	}

	class MyFilter extends FileFilter
	{
		@Override
		public boolean accept(File f)
		{
			if (f.isDirectory())
				return true;

			String extension = getExtension(f);
			if (extension != null)
				return extension.equals("txt");
			else
				return false;
		}

		@Override
		public String getDescription()
		{
			return "Ficheiros de dados";
		}

		/*
		 * Get the extension of a file.
		 */
		public String getExtension(File f)
		{
			String ext = null;
			String s = f.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 && i < s.length() - 1)
			{
				ext = s.substring(i + 1).toLowerCase();
			}
			return ext;
		}

	}
}
