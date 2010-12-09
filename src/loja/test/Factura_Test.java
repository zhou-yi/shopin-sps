/***********************************************************
* Filename: Factura_Test.java
* Created:  2009/03/17
***********************************************************/
package loja.test;

import loja.Factura;
import loja.LinhaFactura;
import loja.NaoPerecivel;
import loja.Perecivel;
import loja.Produto;
import utilities.Data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/***********************************************************
 * @author FBA
 * 2009/03/17
 *
 ***********************************************************/
public class Factura_Test {

	private static Factura f2;
	private static Produto p1, p2, p3, p4;
	
	private Data d = Data.hoje();
	
	/***********************************************************
	 * Corre automaticamente no início da bateria de testes.
	 * Serve para realizar inicialização de variáveis de teste.
	 ***********************************************************/
	@BeforeClass
	public static void setUpBeforeClass()
	{      
		f2 = new Factura();
		
		p1 = new Perecivel(1024, 0.6, "Imperial", "cl", null, new Data(10, 3, 2009));
		p2 = new NaoPerecivel(855, 1.1, "Coca-Cola", "cl", null, new Data(25, 12, 2009));
		p3 = new Perecivel(777, 0.45, "Café", "cl", null, new Data(10, 3, 2009));
		p4 = new NaoPerecivel(890, 2.5, "Cuba Libre", "cl", null, new Data(25, 12, 2009));
		
		f2.adiciona(p1, 5);
		f2.adiciona(p2, 4);
		f2.adiciona(p3, 6);
	}


	/***********************************************************
	 * Corre automaticamente no fim da bateria de testes.
	 * Pode ser usada para realizar operações de "limpeza".
	***********************************************************/
	@AfterClass
	public static void tearDownAfterClass()
	{
		f2.clear();
	}

	/***********************************************************
	 * Corre antes de qualquer teste
	 ***********************************************************/
	@Before
	public void setUp()
	{
        System.out.println("setUp()");
	}

	/***********************************************************
	 * Corre depois de qualquer teste
	 ***********************************************************/
	@After
	public void tearDown()
	{
       System.out.println("tearDown()");
	}

	/**
	 * Test method for {@link loja.Factura#Factura(utilities.Data)}.
	 */
	@Test
	public final void testFactura()
	{
		Factura f = new Factura();
		assertNotNull(f);		
		assertTrue(Factura.ANO == 2009);
		assertEquals(d, f.getData());
		
		Factura f1 = new Factura(f);
		assertEquals(f, f1);
	}

	/**
	 * Test method for {@link loja.Factura#getNumero()}.
	 */
	@Test
	public final void testGetNumero()
	{
		Factura f = new Factura(), 
		f1 = new Factura(),
		f2 = new Factura();
		
		assertEquals(f1.getNumero()-f.getNumero(), 1);
		assertEquals(f2.getNumero()-f.getNumero(), 2);
	}

	/**
	 * Test method for {@link loja.Factura#getDia()}.
	 */
	@Test
	public final void testGetDia() {
		assertEquals(d, new Factura().getData());
	}

	/**
	 * Test method for {@link loja.Factura#isAnulada()}.
	 */
	@Test
	public final void testIsAnulada() {
		assertFalse(new Factura().isAnulada());
	}

	/**
	 * Test method for {@link loja.Factura#getLinhasFactura()}.
	 */
	@Test
	public final void testGetLinhasFactura()
	{
	    int i = 0;
	    for (LinhaFactura l : f2.getLinhasFactura()) i++;
	    	assertEquals(i, 3);
	}

	/**
	 * Test method for {@link loja.Factura#setAnulada()}.
	 */
	@Test
	public final void testSetAnulada()
	{
		Factura f = new Factura();
		f.setAnulada();
		assertTrue(f.isAnulada());
	}

	/**
	 * Test method for {@link loja.Factura#setLinhasFactura(java.util.ArrayList)}.
	 */
	@Test
	public final void testSetLinhasFactura() {
		Factura f = new Factura();
		f.setLinhasFactura(f2.getLinhasFactura());
		assertEquals(f.getLinhasFactura(), f2.getLinhasFactura());
	}

	/**
	 * Test method for {@link loja.Factura#add(loja.Produto, float)}.
	 */
	@Test
	public final void testAdd() {
		NaoPerecivel pnovo = new NaoPerecivel(999, .90, "Ice Tea", "cl", null, new Data(2, 3, 2010));
		f2.adiciona(pnovo, 5);
		assertEquals(f2.get(f2.size()-1).getProduto(), pnovo);
		assertTrue(f2.contains(pnovo));
		f2.remove(f2.size()-1);
		assertFalse(f2.contains(pnovo));
	}

	/**
	 * Test method for {@link loja.Factura#clear()}.
	 */
	@Test
	public final void testClear()
	{
		assertEquals(f2.size(), 3);
		f2.clear();
		assertEquals(f2.size(), 0);
		
		setUpBeforeClass();	// to recover the initial status
	}

	/**
	 * Test method for {@link loja.Factura#contains(loja.Produto)}.
	 */
	@Test
	public final void testContains()
	{
		assertFalse(f2.contains(p4));
		assertTrue(f2.contains(p3));
		assertTrue(f2.contains(p2));
		assertTrue(f2.contains(p1));
	}

	/**
	 * Test method for {@link loja.Factura#get(int)}.
	 */
	@Test
	public final void testGet()
	{
		assertEquals(f2.get(2).getProduto(), p3);
		assertEquals(f2.get(1).getProduto(), p2);
		assertEquals(f2.get(0).getProduto(), p1);
	}

	/**
	 * Test method for {@link loja.Factura#indexOf(loja.Produto)}.
	 */
	@Test
	public final void testIndexOf()
	{
		assertEquals(f2.indexOf(p3), 2);
		assertEquals(f2.indexOf(p2), 1);
		assertEquals(f2.indexOf(p1), 0);
	}

	/**
	 * Test method for {@link loja.Factura#remove(int)}.
	 */
	@Test
	public final void testRemoveInt()
	{
		// p1, p2, p3
		assertFalse(f2.contains(p4));
		f2.adiciona(p4, 4);
		// p1, p2, p3, p4
		assertTrue(f2.contains(p4));
		f2.remove(3);
		// p1, p2, p3
		assertFalse(f2.contains(p4));
		f2.remove(0);
		// p2, p3
		assertFalse(f2.contains(p1));
		f2.remove(1);
		// p2
		assertTrue(f2.contains(p2));
		assertFalse(f2.contains(p1));
		f2.remove(0);
		// <empty bill>
		assertEquals(f2.size(), 0);
		
		setUpBeforeClass();	// to recover the initial status
	}

	/**
	 * Test method for {@link loja.Factura#remove(loja.LinhaFactura)}.
	 */
	@Test
	public final void testRemoveLinhaFactura()
	{
		// p1, p2, p3
		assertTrue(f2.contains(p2));
		f2.remove(f2.get(1));
		// p1, p3
		assertFalse(f2.contains(p2));
		assertTrue(f2.contains(p3));
		f2.remove(f2.get(f2.size()-1));
		// p1
		assertFalse(f2.contains(p3));
		assertTrue(f2.contains(p1));
		f2.remove(f2.get(0));
		// <empty>
		assertFalse(f2.contains(p1));

		setUpBeforeClass();	// to recover the initial status
	}

	/**
	 * Test method for {@link loja.Factura#size()}.
	 */
	@Test
	public final void testSize()
	{
		// p1, p2, p3
		assertEquals(f2.size(), 3);
		f2.remove(f2.get(1));
		// p1, p3
		assertEquals(f2.size(), 2);
		f2.adiciona(p4, 22);
		// p1, p3, p4
		assertEquals(f2.size(), 3);
		f2.adiciona(p4, 11);
		// p1, p3, p4
		assertEquals(f2.size(), 3);
		f2.clear();
		// <empty>
		assertEquals(f2.size(), 0);

		setUpBeforeClass();	// to recover the initial status
	}
	

	/**
	 * Test method for {@link loja.Factura#totalFactura()}.
	 */
	@Test
	public final void testTotalFactura()
	{
		Factura f = new Factura();
		assertEquals(f.totalFactura(), 0, 0.000001);
		
		f.adiciona(p1, 5);
		assertEquals(f.totalFactura(), 5 * p1.getPreco(), 0.000001);
		assertEquals(f.totalFactura(), f.get(0).totalLinha(), 0.000001);

		f.adiciona(p2, 7);
		assertEquals(f.totalFactura(), 5 * p1.getPreco() 
				                     + 7 * p2.getPreco(), 0.000001);
		assertEquals(f.totalFactura(),
										f.get(0).totalLinha()
									  + f.get(1).totalLinha(), 0.000001);
		
		f.adiciona(p3, 2.3);			
		assertEquals(f.totalFactura(), 5 * p1.getPreco() 
				                     + 7 * p2.getPreco()
				                     + 2.3 * p3.getPreco(), 0.000001);
		assertEquals(f.totalFactura(), 
										f.get(0).totalLinha()
									  + f.get(1).totalLinha()
									  + f.get(2).totalLinha(), 0.000001);
	}
	
	
	/**
	 * Test method for {@link loja.Factura#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject()
	{
		Factura f1 = new Factura();
		assertEquals(f1, f1);
		assertEquals(f2, f2);
		
		f1 = f2;
		assertEquals(f1, f2);
		assertEquals(f2, f1);
		assertTrue(f1.equals(f2) && f2.equals(f1)); 
		// in practice this latter test does exactly the same as the two previous ones 

		Factura f3 = new Factura(f2);
		assertEquals(f2, f3);
		assertEquals(f1, f3);
}

	/**
	 * Test method for {@link loja.Factura#clone()}.
	 */
	@Test
	public final void testClone()
	{
		Factura f1 = new Factura(f2);
		assertEquals(f1, f2);
		assertNotSame(f1, f2);
		assertFalse(f1==f2);	// The objects are stored in different positions
		
		Factura f3 = (Factura) f1.clone();
		assertEquals(f1, f3);
		assertNotSame(f1, f3);
		assertFalse(f1==f3);	// The objects are stored in different positions
		
		Factura f4 = (Factura) f2.clone();
		assertEquals(f2, f4);
		assertNotSame(f4, f2);
		assertFalse(f4==f2);	// The objects are stored in different positions
		
		f2=f4;
		assertSame(f2, f4);
		assertTrue(f4==f2);		// Now both f2 and f4 point to the same object
	}

}
