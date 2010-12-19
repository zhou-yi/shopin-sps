package loja;

import java.util.LinkedList;

import utilities.Data;

public class Encomenda 
{
	private int			id;
	private Data		data;
	private boolean		finalizada	= false;
	private BaseDados bd= new BaseDados();

	private LinkedList<LinhaEncomenda> linhasEncomenda = new LinkedList<LinhaEncomenda>();

	public Encomenda()
	{
		id = getUltimo()+1;
		setUltimo();
		data = Data.hoje();
	}
	
	public Encomenda(int id, Data data, boolean finalizada)
	{
		this.id = id;
		this.data = data;
		this.finalizada = finalizada;
	}
	
	public int getId()
	{
		return id;
	}
	
	public Data getData()
	{
		return data;
	}
	
	public boolean finalizada()
	{
		return finalizada;
	}
	
	public LinkedList<LinhaEncomenda> getLinhaEncomenda()
	{
		return linhasEncomenda;
	}
	
	public void encomendaFinalizada()
	{
		this.finalizada = true;
	}
	
	private void setUltimo()
	{
		id++;
	}
	
	private int getUltimo()
	{
		return bd.encomendas().getLast().getId();
	}
	
	public void adiciona(LinhaEncomenda linha)
	{
		linhasEncomenda.add(linha);
	}
	
	public void clear()
	{
		linhasEncomenda.clear();
	}
	
	public String toString()
	{
		return id + " " + data.getAno() + " " + data.getMes() + " " + data.getDia() + " " + finalizada;
	}
	
}
