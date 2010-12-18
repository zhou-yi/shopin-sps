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


public class WProdutoXML extends Thinlet{

	private BaseDados b_dados = null;

	public WProdutoXML(BaseDados b_dados) throws IOException {

		add(parse("janelaproduto.xml"));
		this.b_dados=b_dados;
	}


	public void adiciona(Object check_box_perecivel, Object codigo, Object pvenda,Object nome, Object unidade, Object cbarras,Object dia, Object mes, Object ano){
		
		try
		{
			Produto p = le(check_box_perecivel, codigo, pvenda, nome, unidade, cbarras,dia, mes, ano);
			b_dados.adiciona(p);
			JOptionPane.showMessageDialog(this, "Inserido produto " + p + " na base de dados!", "Sucesso",
					JOptionPane.PLAIN_MESSAGE);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Formato numérico incorrecto!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean remove(Object check_box_perecivel, Object codigo, Object pvenda,Object nome, Object unidade, Object cbarras,Object dia, Object mes, Object ano)
	{
		Produto p = le(check_box_perecivel, codigo, pvenda,nome, unidade, cbarras,dia, mes, ano);

		if (JOptionPane.showConfirmDialog(this, "Remover produto " + p + " da base de dados?", "Confirmar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) 
		{
			b_dados.retira(p);
			return true;
		}
		return false;
	}
	
	public void cancelar(){
		//tenho algumas soluções rebuscadas.....
		InterfaceXML.m_produto.setVisible(false);
		
	}
	
	public void setvalidade(Object panel1)
	{
		setString(panel1, "text", "Data de Validade");
	}
	
	public void setfabrico(Object panel1)
	{
		setString(panel1, "text", "Data de Fabrico");
	}
	
	public Produto le(Object check_box_perecivel, Object codigo, Object pvenda,Object nome, Object unidade, Object cbarras,Object dia, Object mes, Object ano) throws NumberFormatException
	{	
		char tipo='N';
		boolean perecivel = getBoolean(check_box_perecivel, "selected");
		if(perecivel)
		tipo = 'P';
		int cod = Integer.parseInt(getString(codigo,"text"));
		double preco_venda = Double.parseDouble(getString(pvenda,"text"));
		String nom = getString(nome,"text");
		String uni = getString(unidade,"text");
		int a = Integer.parseInt(getString(ano,"text"));
		int m = Integer.parseInt(getString(mes,"text"));
		int d = Integer.parseInt(getString(dia,"text"));
		String barras = getString(cbarras,"text");
		System.out.println(cod+"\n"+tipo+"\n"+a+m+d+uni+nom+preco_venda);
		Produto p = null;
		switch (tipo)
		{
			case 'p':
			case 'P':
				p = new Perecivel(cod, preco_venda, nom, uni, new CodigoBarras(barras), new Data(d, m, a));
				break;
			case 'n':
			case 'N':
				p = new NaoPerecivel(cod, preco_venda, nom, uni, new CodigoBarras(barras), new Data(d, m, a));
				break;
			default:
				System.out.println("Tipo de produto inválido");
				break;
		}
		return p;
	
	}

}
