

package loja.thinlet;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import loja.BaseDados;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import loja.Produto.CodigoBarras;
import thinlet.*;
import utilities.Data;


public class WEncomendaXML extends Thinlet{

	private BaseDados b_dados = null;
	private LinkedList<Produto> lista_produtos = null;
	public WEncomendaXML(BaseDados b_dados) throws IOException {

		add(parse("janelaencomenda.xml"));
		this.b_dados=b_dados;
	}
	
	
	public void iniciaComboBox(Object combo_box) {

		for(Produto p : lista_produtos){
			//			setInteger(combo_box, "selected", lista_produtos.indexOf(p));
			System.out.println(p.getNome()+"");
			Object choice = create("choice");
			setString(choice,"text", p.getNome());
			add(combo_box, choice);

		}

	}
	
}