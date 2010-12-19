package loja.thinlet;
import java.awt.TextArea;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;

//import javax.swing.WindowConstants;

import loja.BaseDados;
import loja.Factura;
import loja.Produto;
import thinlet.*;


public class InterfaceXML extends Thinlet{

	private BaseDados b_dados=null;
	private WProdutoXML window_produto = null;
	private WFacturaXML window_factura = null;
	private WEncomendaXML window_encomenda = null;
	public static Moldura m_produto = null;
	public static Moldura m_factura = null;
	public static Moldura m_encomenda = null;
	private TextArea text_area = new TextArea();;
	private static final String	separador			= "______________________________________________________________________________________________\n";


	public InterfaceXML(BaseDados dados) throws Exception{
		add(parse("janelainicial.xml"));
		
		this.b_dados=dados;
		this.window_produto = new WProdutoXML(b_dados);
		this.window_factura = new WFacturaXML(b_dados);
		this.window_encomenda= new WEncomendaXML(b_dados);
		this.m_produto = new Moldura("Produto", window_produto, 450, 350, false);
		this.m_factura = new Moldura("Factura", window_factura, 800, 600, false);
		this.m_encomenda = new Moldura("Encomenda", window_encomenda, 800, 600, false);
	

	}


	public void gerirProduto(){

		m_produto.setVisible(true);

	}
	public void listarProduto(Object text_area_xml){
		
		text_area.append(Produto.cabecalho() + "\n");
		text_area.append("----------------------------------------------------------------------------------------------\n");
		for (Produto p : b_dados.produtos())
			text_area.append(p.valores() + "\n");
		text_area.append(separador);
		text_area.repaint();
		setString(text_area_xml, "text", text_area.getText());
		

	
	}

	public void gerirFactura(){
		m_factura.setVisible(true);
	}
	
	public void gerirEncomenda(){
		m_encomenda.setVisible(true);
	}
	
	public void listarFactura(Object text_area_xml){

		for (Factura f : b_dados.facturas())
			text_area.append(f + "\n");
		text_area.append(separador);
		setString(text_area_xml, "text", text_area.getText());
	}

	public void limparJanela(Object text_area_xml){
		text_area.setText("");
		setString(text_area_xml, "text", text_area.getText());
	}

	public void sair()
	{
		System.exit(0);
	}
	
	public void guardar()
	{
		b_dados.salva();
	}
	
	public void Mostra() {

		FrameLauncher f = new FrameLauncher("Loja", this, 800, 600);
	}

	public static void main(String[] args) throws Exception{
//		FrameLauncher fl = new FrameLauncher("Loja", new InterfaceXML(), 400, 300);

	}
	public void acercaPrograma(){
		
	
		Icon icon = new ImageIcon("images/help.gif");
		
		JOptionPane
		.showMessageDialog(
				null,
				" Programa profissional de Ponto de Venda da Software Power Solutions. \n Gestão integrada de: \n Produtos; \n Facturas; \n Fornecedores; \n Clientes.",
				"Janela de informação",
				JOptionPane.INFORMATION_MESSAGE,icon);
	}
}
