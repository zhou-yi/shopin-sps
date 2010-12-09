/**
 * 
 */
package loja.test;

import static org.junit.Assert.*;
import loja.Factura;
import loja.NaoPerecivel;
import loja.Produto;
import loja.Produto.CodigoBarras;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.Data;

/**
 * @author GiGi
 *
 */
public class Produto_Test {

	private static Produto p1;
	private static Produto p2;
	@BeforeClass
public static void setUpBeforeClass() throws Exception {
		
		
	}

	


	/**
	 * Test method for {@link loja.Produto#Produto(loja.Produto)}.
	 */
	@Test
	public final void testProduto()
	{
		p1 = new NaoPerecivel(122222,12.5,"packIorgurtes","dezoito",new CodigoBarras("1231312314"),new Data(12,12,2010));
		p2 = new NaoPerecivel(12312312,11.5,"packPresuntos","vinte",new CodigoBarras("213123123"), new Data(11,11,2010));		
	
		assertNotNull(p1);		
		assertTrue(p1.getCodigo()>0);
		
		
		
		//assertEquals(p1, p2); keria fazer notequals
}
	public void testGetCodigo() {
	p1.setCodigo(10);
	assertEquals(p1.getCodigo(),10);
		
	}

	/**
	 * Test method for {@link loja.Produto#getPreco()}.
	 */
	@Test
	public void testGetPreco() {
		p1.setPreco((float) 10.5);
	//	assertEquals(p1.getPreco(),(float)10.5);
			
	}

	/**
	 * Test method for {@link loja.Produto#getNome()}.
	 */
	@Test
	public void testGetNome() {
		assertEquals(p1.getNome(), "packIorgurtes");
	}

	/**
	 * Test method for {@link loja.Produto#getUnidade()}.
	 */
	@Test
	public void testGetUnidade() {
		p1.setUnidade("12");
		assertEquals(p1.getUnidade(),"12");
	}

	/**
	 * Test method for {@link loja.Produto#getCodigoBarras()}.
	 */
	@Test
	public void testGetCodigoBarras() {
		CodigoBarras cb = new CodigoBarras("11112");
		p1.setCodigo_barras(new CodigoBarras("435345345"));
		assertNotSame(p1.getCodigoBarras(), cb);
	}


	/**
	 * Test method for {@link java.lang.Object#clone()}.
	 */
	@Test
	public void testClone() {
		p1 = new NaoPerecivel(122222,12.5,"packIorgurtes","dezoito",new CodigoBarras("1231312314"), new Data(11,11,2009)); 
		
		p2= new NaoPerecivel((NaoPerecivel) p1);
		
		assertNotSame(p1, p2);
		p1 = p2;
		
		assertSame(p1, p2);

	
	}

	public void setP2(Produto p2) {
		this.p2 = p2;
	}

	public Produto getP2() {
		return p2;
	}

}
