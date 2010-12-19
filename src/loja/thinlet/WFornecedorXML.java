package loja.thinlet;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import thinlet.Thinlet;
import loja.BaseDados;
import loja.Factura;
import loja.Fornecedor;
import loja.Produto;
import loja.UserCancelException;
import loja.UserErrorException;

public class WFornecedorXML extends Thinlet {

	private BaseDados b_dados = null;
	protected Fornecedor				fornecedor_corrente;
	LinkedList<Fornecedor> lista_fornecedores = null;


	public WFornecedorXML(BaseDados b_dados) throws IOException, InputMismatchException, UserCancelException, UserErrorException {


		this.b_dados=b_dados;
		lista_fornecedores = new LinkedList<Fornecedor>();
		fornecedor_corrente=new Fornecedor();
		for (Fornecedor f : b_dados.fornecedores())
		{
			lista_fornecedores.add(f);
		}
		

		add(parse("janelafornecedor.xml"));

	}


	public void iniciaComboBox(Object combo_box) {

		for(Fornecedor f : lista_fornecedores){
			//			setInteger(combo_box, "selected", lista_produtos.indexOf(p));
			System.out.println(f.getName()+"");
			Object choice = create("choice");
			setString(choice,"text", f.getName());
			add(combo_box, choice);

		}

	}
	public void retira(Object combo_box) throws InputMismatchException, UserCancelException, UserErrorException
	{
		int index =getSelectedIndex(combo_box);
		Object item = getItem(index);
		fornecedor_corrente = (Fornecedor)item;

		b_dados.retira(fornecedor_corrente);
		b_dados.salva();
	
	
		

	}

	private Object getItem(int index) {
		if(b_dados.fornecedores.size()>index&&index>0)
			return b_dados.fornecedores.get(index);
		return null;
	}

	public void adiciona(String name, String contact, String adress) throws InputMismatchException, UserCancelException, UserErrorException
	{

		Fornecedor fornecedor_aux = new Fornecedor(name, 'A', 0, contact, adress);
		b_dados.adiciona(fornecedor_aux);
		b_dados.salva();
	
		
		
	}
}




