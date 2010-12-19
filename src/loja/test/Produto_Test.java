/**
 * 
 */
package loja.test;

import static org.junit.Assert.*;

import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import loja.*;
import loja.Produto.CodigoBarras;
import loja.gui.InterfaceJanelas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.Data;

/**
 * @author HibridO
 *
 */
public class Produto_Test {
	private static Produto p1, p2, p3;
	private static BaseDados dados;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p2 = new NaoPerecivel(11, 0.99, "Quitoso Champo", "Litro", new CodigoBarras("5601234527111"), new Data(10, 3, 2009));
		p1 = new Perecivel(777, 0.45, "Café", "cl", new CodigoBarras("5601234527712"), new Data(10, 3, 2009));
		p3 = new NaoPerecivel(20,	0.55,	"Chá",	"bule",new CodigoBarras("5601232257544"), new Data(11, 3, 2009));
		
		dados = new BaseDados("data/produtos.txt", "data/facturas.txt", "data/linhas_factura.txt", "data/fornecedores.txt", "data/encomenda.txt", "data/linhas_encomenda.txt");
		try
		{
			dados.carrega();
			System.out.println("Carregou " + dados.produtos().size() + " produtos.");
			System.out.println("Carregou " + dados.facturas().size() + " facturas.");

		}
		catch (InputMismatchException e)
		{
			JOptionPane.showMessageDialog(null, "Ficheiro de dados incorrecto ou corrompido!", "Programa terminado!",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
        System.out.println("setUp()");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	       System.out.println("tearDown()");
	}

	/**
	 * Test method for {@link loja.Produto#Produto(int, double, java.lang.String, java.lang.String, loja.Produto.CodigoBarras)}.
	 */
	@Test
	public void testProdutoIntDoubleStringStringCodigoBarras() {
		Produto p = new NaoPerecivel(11, 0.99, "Quitoso Champo", "Litro", new CodigoBarras("5601234527111"), new Data(10, 3, 2009));
		assertNotNull(p);
		assertTrue(p.getNome()=="Quitoso Champo");
	}

	/**
	 * Test method for {@link loja.Produto#Produto(loja.Produto)}.
	 */
	@Test
	public void testProdutoProduto() {
		
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link loja.Produto#setPreco_venda(double)}.
	 */
	@Test
	public void testSetPreco_venda() {
		p1.setPreco_venda(1.01);
		assertEquals(1.01,p1.getPreco(),0);
	}

	/**
	 * Test method for {@link loja.Produto#setUnidade(java.lang.String)}.
	 */
	@Test
	public void testSetUnidade() {
		p1.setUnidade("50ml");
		assertEquals(p1.getUnidade(),"50ml");
	}

	/**
	 * Test method for {@link loja.Produto#setCodigo_barras(loja.Produto.CodigoBarras)}.
	 */
	@Test
	public void testSetCodigo_barras() {
		CodigoBarras cb1 = new CodigoBarras("5601543567712");
		p1.setCodigo_barras(cb1);		
		assertEquals(p1.getCodigoBarras(),cb1);
	}

	/**
	 * Test method for {@link loja.Produto#setCodigo(int)}.
	 */
	@Test
	public void testSetCodigo() {
		p1.setCodigo(12);
		assertEquals(p1.getCodigo(), 12);
	}

	/**
	 * Test method for {@link loja.Produto#setPreco(float)}.
	 */
	@Test
	public void testSetPreco() {
		p1.setPreco(1);
		assertEquals(1,p1.getPreco(),0);
	}

	/**
	 * Test method for {@link loja.Produto#setNome(java.lang.String)}.
	 */
	@Test
	public void testSetNome() {
		//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link loja.Produto#cabecalho()}.
	 */
	@Test
	public void testCabecalho() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link loja.Produto#valores()}.
	 */
	@Test
	public void testValores() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link loja.Produto#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals(p1.toString(),"CAFÉ (50ml)");
	}

	/**
	 * Test method for {@link loja.Produto#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		assertTrue(p3.equals(dados.procuraProduto(20)));
	}

	/**
	 * Test method for {@link loja.Produto#compareTo(loja.Produto)}.
	 */
	@Test
	public void testCompareTo() {
		assertEquals(p3.compareTo(dados.procuraProduto(20)),0);
	}

}
