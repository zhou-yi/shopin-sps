

package loja.thinlet;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import loja.BaseDados;
import loja.Encomenda;
import loja.Factura;
import loja.LinhaEncomenda;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import loja.Produto.CodigoBarras;
import thinlet.*;
import utilities.Data;


public class WEncomendaXML extends Thinlet{

	private BaseDados b_dados = null;
	private LinkedList<Produto> lista_produtos = null;
	private Encomenda encomenda_corrente = null;
	private LinhaEncomenda linha = null;
	private TextArea text_area = new TextArea();;

	public WEncomendaXML(BaseDados b_dados) throws IOException {

		this.b_dados=b_dados;
		lista_produtos = new LinkedList<Produto>();
		encomenda_corrente=new Encomenda();
		for (Produto p : b_dados.produtos())
		{
			lista_produtos.add(p);
		}
		add(parse("janelaencomenda.xml"));

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
	public void adiciona(Object combo_box, Object quantidade,  Object valorunitario, Object text_area)
	{

		int index =getSelectedIndex(combo_box);
		Object item = getItem(index);
		Produto p_aux = (Produto)item;

		linha = new LinhaEncomenda(1, "Shopin LDA", p_aux.getNome() , Double.parseDouble(getString(quantidade, "text")), Double.parseDouble(getString(quantidade, "text")));

		encomenda_corrente.adiciona(linha);
		escreve(text_area);
	}

	protected void escreve(Object text_area_xml)
	{
	
		text_area.append("----------------------------------------------------------------------------------------------\n");
		for (LinhaEncomenda L : encomenda_corrente.getLinhaEncomenda())
			text_area.append(L.toString() + "\n");
		
		text_area.repaint();
		setString(text_area_xml, "text", text_area.getText());
		

		
		
		
		
		
	}
	public void fecha()
	{
		b_dados.adiciona(encomenda_corrente);
		JOptionPane.showMessageDialog(this, "Inserida encomenda " + encomenda_corrente + " na base de dados!", "Sucesso",
				JOptionPane.PLAIN_MESSAGE);
	}




private Object getItem(int index) {
	if(lista_produtos.size()>index&&index>0)                                                                                                 
		return lista_produtos.get(index);
	return null;
}

public void iniciavalores(Object combo_box, Object quantidade,Object valorunitario,Object subtotal){


	int index =getSelectedIndex(combo_box);
	Object item = getItem(index);
	if (item != null)
	{
		setString(quantidade, "text", "1");
		String valor = ((Produto) item).getPreco()+"";
		setString(valorunitario, "text",valor);
		setString(subtotal, "text", valor);

	}
}



}