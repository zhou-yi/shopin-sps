

package loja.thinlet;

import java.awt.event.ActionEvent;
import java.io.IOException;

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

	public WEncomendaXML(BaseDados b_dados) throws IOException {

		add(parse("janelaencomenda.xml"));
		this.b_dados=b_dados;
	}
	
}