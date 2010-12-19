package loja.thinlet;

import java.io.IOException;
import java.util.LinkedList;

import loja.BaseDados;
import loja.Factura;
import loja.Produto;
import thinlet.Thinlet;

public class WEncomendaXML extends Thinlet{

	

	
	private BaseDados b_dados;
	private LinkedList<Produto> lista_produtos = null;
//	protected Encomenda				factura_corrente;

	public WEncomendaXML(BaseDados b_dados) throws IOException {
//		this.b_dados=b_dados;
//		lista_produtos = new LinkedList<Produto>();
//	//	factura_corrente=new Factura();
//		for (Produto p : b_dados.produtos())
//		{
//			lista_produtos.add(p);
//		}
		add(parse("janelaencomenda.xml"));


	}
}
