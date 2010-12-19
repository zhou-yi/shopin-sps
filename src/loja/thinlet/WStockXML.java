package loja.thinlet;

import java.awt.TextArea;
import java.io.IOException;

import loja.BaseDados;
import loja.Factura;
import thinlet.*;


public class WStockXML extends Thinlet{

	private BaseDados b_dados = null;

	private TextArea text_area = new TextArea();

	public WStockXML(BaseDados b_dados) throws IOException {
		add(parse("janelastock.xml"));
		this.b_dados = b_dados;
	}

	public void listar(Object text_area_xml){
		//for (String s : stocks.listaInventario())
		//text_area.append(s);
		text_area.append("\n");
		setString(text_area_xml, "text", text_area.getText());
	}
	
	public void adiciona(){
		
	}

	public void remove(){
		
	}
	
}
