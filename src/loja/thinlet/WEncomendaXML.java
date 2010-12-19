

package loja.thinlet;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import loja.BaseDados;
import loja.Encomenda;
import loja.Factura;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import loja.Produto.CodigoBarras;
import thinlet.*;
import utilities.Data;


public class WEncomendaXML extends Thinlet{

	private BaseDados b_dados = null;
	private LinkedList<Encomenda> lista_encomendas= null;
	private Encomenda encomenda_corrente = null;
	
	public WEncomendaXML(BaseDados b_dados) throws IOException {

		
		this.b_dados=b_dados;
		lista_encomendas = new LinkedList<Encomenda>();
		encomenda_corrente=new Encomenda();
		for (Encomenda e : b_dados.encomendas())
		{
			lista_encomendas.add(e);
		}
		add(parse("janelaencomenda.xml"));
		
	}
	
	
//	public void iniciaComboBox(Object combo_box) {
//
//		for(E : lista_encomendas){
//			//			setInteger(combo_box, "selected", lista_produtos.indexOf(p));
//			System.out.println(p.getNome()+"");
//			Object choice = create("choice");
//			setString(choice,"text", p.getNome());
//			add(combo_box, choice);
//
//		}
//
//	}
	
}