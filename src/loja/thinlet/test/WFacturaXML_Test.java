package loja.thinlet.test;

import static org.junit.Assert.*;

import java.util.InputMismatchException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import loja.BaseDados;
import loja.Factura;
import loja.Produto;
import loja.thinlet.WFacturaXML;
import loja.thinlet.WProdutoXML;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.Data;

public class WFacturaXML_Test {
	private static BaseDados dados;
	private static WFacturaXML t;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dados = new BaseDados("data/produtos.txt", "data/facturas.txt", "data/linhas_factura.txt", "data/fornecedores.txt", "data/encomenda.txt", "data/linhas_encomenda.txt");
		try
		{
			dados.carrega();
			System.out.println("Carregou " + dados.produtos().size() + " produtos.");
			System.out.println("Carregou " + dados.facturas().size() + " facturas.");
			t= new WFacturaXML(dados);

		}
		catch (InputMismatchException e)
		{
			JOptionPane.showMessageDialog(null, "Ficheiro de dados incorrecto ou corrompido!", "Programa terminado!",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApaga() {
		
		Object text_area = t.create("textarea");

		Object quantidade = t.create("textfield");
		

		Object codigo = t.create("textfield");
		

		Object dia = t.create("textfield");

		Object mes = t.create("textfield");

		Object ano = t.create("textfield");
		
		t.apaga(quantidade, text_area, codigo, dia, mes, ano);
		
		Factura f = t.getFactura();
		assertEquals(f.toString()+"\n",t.getString(text_area, "text"));
		assertEquals(String.valueOf(f.getNumero()),t.getString(codigo, "text"));
		
		int d = Integer.parseInt(t.getString(dia, "text"));
		int m = Integer.parseInt(t.getString(mes, "text"));
		int a = Integer.parseInt(t.getString(ano, "text"));
		Data data = new Data(d,m,a);
		assertEquals(f.getData(),data);
		
		assertEquals(f.toString()+"\n",t.getString(text_area, "text"));
//		fail("Not yet implemented");
	}

	@Test
	public void testIniciaComboBox() {
//		LinkedList<Produto> lista_produtos = new LinkedList<Produto>();
//
//		for (Produto p : dados.produtos())
//		{
//			lista_produtos.add(p);
//		}
//		
//		Object combo_box = t.create("combobox");
//				
//		t.iniciaComboBox(combo_box);
//		
//		for(int i = 0; i<lista_produtos.size();i++){
//			t.setInteger(combo_box, "selected", i);
//			String choice = t.getChoice(combo_box, String.valueOf(i));
//			
//			assertEquals(lista_produtos.get(i).getNome(),choice);
//			
//		}
//	//	fail("Not yet implemented");
	}

	@Test
	public void testIniciavalores() {
		LinkedList<Produto> lista_produtos = new LinkedList<Produto>();

		for (Produto p : dados.produtos())
		{
			lista_produtos.add(p);
		}
		
		Object combo_box = t.create("combobox");
		t.iniciaComboBox(combo_box);
		int selectedItem = 1;
		t.setInteger(combo_box, "selected", selectedItem);

		Object quantidade = t.create("textfield");

		Object valorunitario = t.create("textfield");
		
		Object subtotal = t.create("textfield");
		
		
		t.iniciavalores(combo_box, quantidade, valorunitario, subtotal);
		String preco_uni = String.valueOf(lista_produtos.get(selectedItem).getPreco());
		assertEquals(t.getString(quantidade,"text"),"1");
		assertEquals(t.getString(valorunitario,"text"),preco_uni);
		assertEquals(t.getString(subtotal,"text"),preco_uni);
		
	//	fail("Not yet implemented");
	}

	@Test
	public void testAdiciona() {
		//	Factura f = new Factura();
		LinkedList<Produto> lista_produtos = new LinkedList<Produto>();

		for (Produto p : dados.produtos())
		{
			lista_produtos.add(p);
		}
		Object combo_box = t.create("combobox");
		t.iniciaComboBox(combo_box);
		int selectedItem = 1;
		t.setInteger(combo_box, "selected", selectedItem);

		Object text_area = t.create("textarea");

		Object quantidade = t.create("textfield");
		t.setString(quantidade, "text", "1");

		Object codigo = t.create("textfield");
		t.setString(codigo, "text", "2000");

		Object dia = t.create("textfield");

		Object mes = t.create("textfield");

		Object ano = t.create("textfield");
		
		
		t.adiciona(combo_box, quantidade, text_area, codigo, dia, mes, ano);

		System.out.println("dia "+t.getString(text_area,"text"));
		Factura f = t.getFactura();
		assertTrue(f.contains(lista_produtos.get(selectedItem)));
		//neste caso tenho de acrescentar o parágrafo no final do f.toString porque sei que no metodos privado "escreve(..)" na WFacturaXML acrescenta-se \n no final
		assertEquals(t.getString(text_area,"text"),(f.toString()+"\n"));

		//		
		//		
		//		
		//		fail("Not yet implemented");
	}

	@Test
	public void testRetira() {

		LinkedList<Produto> lista_produtos = new LinkedList<Produto>();

		for (Produto p : dados.produtos())
		{
			lista_produtos.add(p);
		}
		Object combo_box = t.create("combobox");
		t.iniciaComboBox(combo_box);
		int selectedItem = 1;
		t.setInteger(combo_box, "selected", selectedItem);


		Object text_area = t.create("textarea");

		Object quantidade = t.create("textfield");
		t.setString(quantidade, "text", "1");

		Object codigo = t.create("textfield");
		t.setString(codigo, "text", "2000");

		Object dia = t.create("textfield");

		Object mes = t.create("textfield");

		Object ano = t.create("textfield");

		Factura f = t.getFactura();

		while(f.contains(lista_produtos.get(selectedItem)))
			t.retira(combo_box, quantidade, text_area, codigo, dia, mes, ano);

		t.adiciona(combo_box, quantidade, text_area, codigo, dia, mes, ano);
		t.retira(combo_box, quantidade, text_area, codigo, dia, mes, ano);

		assertFalse(f.contains(lista_produtos.get(selectedItem)));

		//	fail("Not yet implemented");
	}

	@Test
	public void testProcuraPorCodigo() {
		Factura f = new Factura();
		int numero = f.getNumero();

		Object codigo = t.create("textfield");
		t.setString(codigo, "text", String.valueOf(numero));
		
		Object text_area = t.create("textarea");
		
		Object dia = t.create("textfield");

		Object mes = t.create("textfield");

		Object ano = t.create("textfield");
		
		t.procuraPorCodigo(codigo, text_area, dia, mes, ano);
		
		assertEquals(f.toString()+"\n",t.getString(text_area,"text"));
		
		int d = Integer.parseInt(t.getString(dia, "text"));
		int m = Integer.parseInt(t.getString(mes, "text"));
		int a = Integer.parseInt(t.getString(ano, "text"));
		Data data = new Data(d,m,a);
		assertEquals(f.getData(),data);
		
		
	//	fail("Not yet implemented");
	}

	@Test
	public void testFecha() {
		t.fecha();
		assertTrue(dados.existe(t.getFactura()));
		//	fail("Not yet implemented");
	}

	@Test
		public void testAnula() {
	
			
			Object text_area = t.create("textarea");
	
			Object quantidade = t.create("textfield");
			
	
			Object codigo = t.create("textfield");
			
	
			Object dia = t.create("textfield");
	
			Object mes = t.create("textfield");
	
			Object ano = t.create("textfield");
			
			t.anula(quantidade, text_area, codigo, dia, mes, ano);
			
			Factura f = t.getFactura();
			assertEquals(f.toString()+"\n",t.getString(text_area, "text"));
			assertEquals(String.valueOf(f.getNumero()),t.getString(codigo, "text"));
			
			int d = Integer.parseInt(t.getString(dia, "text"));
			int m = Integer.parseInt(t.getString(mes, "text"));
			int a = Integer.parseInt(t.getString(ano, "text"));
			Data data = new Data(d,m,a);
			assertEquals(f.getData(),data);
			
			assertEquals(f.toString()+"\n",t.getString(text_area, "text"));
	//		fail("Not yet implemented");
			
			assertTrue(f.isAnulada());
		}


}
