package loja;

import java.util.ArrayList;
import java.util.Collections;

import utilities.Data;

/***********************************************************
 * @author FBA 2009/03/18
 * 
 ***********************************************************/
public class Factura implements Comparable<Factura>
{
	public static final int		ANO		= 2009;

	private static int		ultimo_numero	= 1000;

	private int			numero;
	private Data			data;
	private boolean			anulada		= false;

	private ArrayList<LinhaFactura>	linhasFactura	= new ArrayList<LinhaFactura>();

	/***********************************************************
	 * @param numero
	 * @param dia
	 ***********************************************************/
	public Factura()
	{
		Factura.ultimo_numero++;
		this.numero = ultimo_numero;
		this.data = Data.hoje();
	}

	/***********************************************************
	 * @param numero
	 * @param dia
	 ***********************************************************/
	public Factura(int numero, Data data, boolean anulada)
	{
		this.numero = numero;
		this.data = data;
		this.anulada = anulada;
		Factura.ultimo_numero = numero > Factura.ultimo_numero ? numero : Factura.ultimo_numero;
	}

	/***********************************************************
	 * @param factura
	 ***********************************************************/
	public Factura(Factura factura)
	{
		this.numero = factura.numero;
		this.data = new Data(factura.data);
		this.anulada = factura.anulada;
		this.setLinhasFactura(factura.getLinhasFactura());
		Factura.ultimo_numero = numero > Factura.ultimo_numero ? numero : Factura.ultimo_numero;
	}

	/***********************************************************
	 * @return the ultimo_numero
	 ***********************************************************/
	public static int getUltimoNumero()
	{
		return ultimo_numero;
	}

	/***********************************************************
	 * @return the numero
	 ***********************************************************/
	public int getNumero()
	{
		return numero;
	}

	/***********************************************************
	 * @return the dia
	 ***********************************************************/
	public Data getData()
	{
		return data;
	}

	/***********************************************************
	 * @return the anulada
	 ***********************************************************/
	public boolean isAnulada()
	{
		return anulada;
	}

	/***********************************************************
	 * @return the linhasFactura
	 ***********************************************************/
	public Iterable<LinhaFactura> getLinhasFactura()
	{
		return linhasFactura;
	}

	/***********************************************************
	 * @param anulada
	 *           the anulada to set
	 ***********************************************************/
	public void setAnulada()
	{
		this.anulada = true;
	}

	/***********************************************************
	 * @param linhasFactura
	 *           the linhasFactura to set
	 ***********************************************************/
	public void setLinhasFactura(Iterable<LinhaFactura> linhasFactura)
	{

		this.linhasFactura = new ArrayList<LinhaFactura>();
		for (LinhaFactura linha : linhasFactura)
			this.linhasFactura.add((LinhaFactura) linha.clone());
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	private boolean linhaValida(int numero)
	{
		return numero >= 0 && numero < this.size();
	}

	/***********************************************************
	 * @param e
	 * @return true if a new line was added to the bill
	 * @see java.util.ArrayList#add(java.lang.Object)
	 ***********************************************************/
	public boolean adiciona(LinhaFactura linha)
	{
		return linhasFactura.add(linha);
	}

	/***********************************************************
	 * @param e
	 * @return true if a new line was added to the bill
	 * @see java.util.ArrayList#add(java.lang.Object)
	 ***********************************************************/
	public boolean adiciona(Produto p, double quanto)
	{
		boolean result = false;
		int position = indexOf(p);
		if (position != -1)
		{
			LinhaFactura linha = linhasFactura.get(position);
			linha.setQuantidade(linha.getQuantidade() + quanto);
		}
		else
			result = linhasFactura.add(new LinhaFactura(this, p, quanto, p.getPreco()));
		return result;
	}

	/***********************************************************
	 * @param e
	 * @return true if a new line was added to the bill
	 * @see java.util.ArrayList#add(java.lang.Object)
	 ***********************************************************/
	public boolean retira(Produto p, double quanto)
	{
		boolean result = false;
		int position = indexOf(p);
		if (position != -1)
		{
			LinhaFactura linha = linhasFactura.get(position);
			if (linha.getQuantidade() > quanto)
				linha.setQuantidade(linha.getQuantidade() - quanto);
			else
				linhasFactura.remove(position);
		}
		return result;
	}

	/***********************************************************
	 * 
	 * @see java.util.ArrayList#clear()
	 ***********************************************************/
	public void clear()
	{
		linhasFactura.clear();
	}

	/***********************************************************
	 * @param o
	 * @return
	 * @see java.util.ArrayList#contains(java.lang.Object)
	 ***********************************************************/
	public boolean contains(Produto p)
	{
		boolean result = false;
		for (LinhaFactura linha : linhasFactura)
			if (linha.getProduto().equals(p)) result = true;
		return result;
	}

	/***********************************************************
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 ***********************************************************/
	public LinhaFactura get(int index)
	{
		assert linhaValida(index);
		return linhasFactura.get(index);
	}

	/***********************************************************
	 * @param o
	 * @return
	 * @see java.util.ArrayList#indexOf(java.lang.Object)
	 ***********************************************************/
	public int indexOf(Produto p)
	{
		int position = -1;
		for (int i = 0; i < linhasFactura.size(); i++)
			if (linhasFactura.get(i).getProduto().equals(p)) position = i;
		return position;
	}

	/***********************************************************
	 * @param index
	 * @return
	 * @see java.util.ArrayList#remove(int)
	 ***********************************************************/
	public LinhaFactura remove(int index)
	{
		assert linhaValida(index);
		return linhasFactura.remove(index);
	}

	/***********************************************************
	 * @param o
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 ***********************************************************/
	public boolean remove(LinhaFactura o)
	{
		return linhasFactura.remove(o);
	}

	/***********************************************************
	 * @return
	 * @see java.util.ArrayList#size()
	 ***********************************************************/
	public int size()
	{
		return linhasFactura.size();
	}

	/***********************************************************
	 * @return
	 ***********************************************************/
	public double totalFactura()
	{
		double total = 0;
		for (LinhaFactura linha : linhasFactura)
			total += linha.totalLinha();
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Factura other = (Factura) obj;
		if (numero != other.numero) return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone(java.lang.Object)
	 */
	@Override
	public Object clone()
	{
		return new Factura(this);
	}

	public String cabecalho()
	{
		String result = "Nº Factura = " + numero + " | Data = " + data;
		return result + (anulada ? " (anulada)" : "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		Collections.sort(linhasFactura);
		String result = cabecalho() + "\n";
		result += "-----------------------------------------------\n";
		for (LinhaFactura linha : linhasFactura)
			result += linha + "\n";
		result += "-----------------------------------------------\n";
		result += String.format("%36s %6.2f€", "Total =", totalFactura());
		return result + "\n";
	}

	@Override
	public int compareTo(Factura outra)
	{
		return ((Integer) this.numero).compareTo(outra.numero);
	}
}
