package loja.thinlet;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import thinlet.Thinlet;
import utilities.Data;
import loja.BaseDados;
import loja.Factura;
import loja.LinhaFactura;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import loja.LinhaFactura.Criteria;
import loja.LinhaFactura.Order;

public class WFacturaXML extends Thinlet{

	private BaseDados b_dados = null;
	protected Factura				factura_corrente;
	private LinkedList<Produto> lista_produtos = null;


	public WFacturaXML(BaseDados b_dados) throws IOException {
		this.b_dados=b_dados;
		lista_produtos = new LinkedList<Produto>();
		factura_corrente=new Factura();
		for (Produto p : b_dados.produtos())
		{
			lista_produtos.add(p);
		}
		add(parse("janelafactura.xml"));


	}

	public void setCrescente(Object por_valor, Object text_area, Object codigo, Object dia, Object mes,Object ano)
	{
		LinhaFactura.Order order = null;
		LinhaFactura.Criteria criteria = null;
		order = Order.ASCENDING;
		if(getBoolean(por_valor, "selected"))
			criteria = Criteria.BYVALUE;
		else
			criteria = Criteria.BYNAME;
		LinhaFactura.setSort(order, criteria);
		escreve(text_area, codigo, dia, mes, ano);
	}
	public void setDecrescente(Object por_valor, Object text_area, Object codigo, Object dia, Object mes,Object ano)
	{
		LinhaFactura.Order order = null;
		LinhaFactura.Criteria criteria = null;
		order = Order.DESCENDING;
		if(getBoolean(por_valor, "selected"))
			criteria = Criteria.BYVALUE;
		else
			criteria = Criteria.BYNAME;
		LinhaFactura.setSort(order, criteria);
		escreve(text_area, codigo, dia, mes, ano);
	}
	public void setPorProduto(Object crescente, Object text_area, Object codigo, Object dia, Object mes,Object ano)
	{
		LinhaFactura.Order order = null;
		LinhaFactura.Criteria criteria = null;
		criteria = Criteria.BYNAME;
		if(getBoolean(crescente, "selected"))
			order = Order.ASCENDING;
		else
			order = Order.DESCENDING;
		LinhaFactura.setSort(order, criteria);
		escreve(text_area, codigo, dia, mes, ano);
	}
	public void setPorValor(Object crescente, Object text_area, Object codigo, Object dia, Object mes,Object ano)
	{
		LinhaFactura.Order order = null;
		LinhaFactura.Criteria criteria = null;
		criteria = Criteria.BYVALUE;
		if(getBoolean(crescente, "selected"))
			order = Order.ASCENDING;
		else
			order = Order.DESCENDING;
		LinhaFactura.setSort(order, criteria);
		escreve(text_area, codigo, dia, mes, ano);
	}

	public void apaga( Object quantidade,  Object text_area,Object codigo, Object dia ,Object mes, Object ano)
	{

		if (JOptionPane.showConfirmDialog(this, "Apagar factura " + factura_corrente + " da base de dados?",
				"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) b_dados.retira(factura_corrente);
		factura_corrente = new Factura();
		escreve(text_area, codigo, dia, mes, ano);
	}

	public void anula(Object quantidade,  Object text_area,Object codigo, Object dia ,Object mes, Object ano)
	{
		if (JOptionPane.showConfirmDialog(this, "Anular factura " + factura_corrente + "?", "Confirmar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0)
		{
			factura_corrente.setAnulada();
			escreve(text_area, codigo, dia, mes, ano);
		}
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

	private void escreve(){

	}
	protected void escreve(Object text_area, Object codigo, Object dia, Object mes, Object ano)
	{
		setString(text_area,"text", "");
		if (factura_corrente == null)
		{
			setString(codigo,"text", "");
			setString(dia,"text", "");
			setString(mes,"text", "");
			setString(ano,"text", "");

		}
		else
		{
			Data d = factura_corrente.getData();
			setString(codigo,"text", factura_corrente.getNumero()+"");
			setString(dia,"text", d.getDia()+"");
			setString(mes,"text", d.getMes()+"");
			setString(ano,"text",d.getAno()+"");

			setString(text_area, "text", factura_corrente+"\n");
		}
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


	public void adiciona(Object combo_box, Object quantidade,  Object text_area,Object codigo, Object dia ,Object mes, Object ano)
	{

		int index =getSelectedIndex(combo_box);
		Object item = getItem(index);

		if(item!=null){

			if (factura_corrente.isAnulada())
				JOptionPane.showMessageDialog(this, "Não se pode modificar uma factura anulada!", "Erro!",
						JOptionPane.ERROR_MESSAGE);
			else
			{
				factura_corrente.adiciona((Produto) item, Double.parseDouble(getString(quantidade, "text")));
				escreve(text_area, codigo, dia, mes, ano);
			}
		}

	}
	private Object getItem(int index) {
		if(lista_produtos.size()>index&&index>0)
			return lista_produtos.get(index);
		return null;
	}

	public void retira(Object combo_box, Object quantidade,  Object text_area,Object codigo, Object dia ,Object mes, Object ano)
	{
		int index =getSelectedIndex(combo_box);
		Object item = getItem(index);

		if (factura_corrente.isAnulada())
			JOptionPane.showMessageDialog(this, "Não se pode modificar uma factura anulada!", "Erro!",
					JOptionPane.ERROR_MESSAGE);
		else
		{
			factura_corrente.retira((Produto) item, Double.parseDouble(getString(quantidade, "text")));
			escreve(text_area, codigo, dia, mes, ano);
		}

	}

	public void procuraPorCodigo(Object codigo, Object text_area, Object dia ,Object mes, Object ano)
	{
		int numero = Integer.parseInt(getString(codigo, "text"));
		factura_corrente=(b_dados.procuraFactura(numero));
		if (factura_corrente == null) factura_corrente = new Factura(numero, Data.hoje(), false);
		escreve(text_area, codigo, dia, mes, ano);
	}

	public void fecha()
	{
		b_dados.adiciona(factura_corrente);
		JOptionPane.showMessageDialog(this, "Inserida factura " + factura_corrente + " na base de dados!", "Sucesso",
				JOptionPane.PLAIN_MESSAGE);
	}

	public void cancela(){
		InterfaceXML.m_factura.setVisible(false);
	}

	public Factura getFactura() {
		return factura_corrente;		
	}


}
