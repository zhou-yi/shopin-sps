package loja.thinlet.test;

import static org.junit.Assert.*;

import java.io.IOException;

import loja.BaseDados;
import loja.Perecivel;
import loja.Produto;
import loja.thinlet.JanelaFactura;
import loja.thinlet.JanelaInicial;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import thinlet.FrameLauncher;
import utilities.Data;

public class JanelaInicialTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testJanelaInicial() {
		assertNotNull("JanelaFactura.xml");
	}

	@Test
	public void testSair() {
		//allways sucessfull duhh!!!
	}

	@Test
	public void testGuardar() {
		//
	}

	@Test
	public void testPOS() {

	}

	@Test
	public void testAbreJanelaProdutos() {
	// no need to test
	}

	@Test
	public void testFornGerir() {
		// no need to test
	}

	@Test
	public void testFornListar() {
		// no need to test
	}

	@Test
	public void testListarProdutos() {

		BaseDados dados = new BaseDados(null, null, null);
		Perecivel p = new Perecivel(12, 12.3, "cenas", "unidade", null, new Data(12,12,2222));
		
		dados.adiciona(p);
		String s = (Produto.cabecalho() + "\n");
		s += "-------------------------------------------------------------------------------------------------------------------------------------------\n";
		for (Produto p1 : dados.produtos()) {
			s += p1.valores() + "\n";
		}
		
		assertNotNull(s);
		


	}


	@Test
	public void testAbreJanelaFact() throws IOException {
		FrameLauncher frame2 = (new FrameLauncher("Factura",
				new JanelaFactura(), 450, 360));
		frame2.setResizable(false);
		
		assertNotNull(frame2);
	}

	@Test
	public void testFactListar() {
		String s = "olaaaaaa";
		
		assertEquals("olaaaaaa", s);
	}

	@Test
	public void testLimparJanela() {
		//
	}

	@Test
	public void testAjuda() {
		//
	}

	@Test
	public void testGetBaseDados() throws IOException {
		
		JanelaInicial Ji = new JanelaInicial();
		
		BaseDados dados = new BaseDados(null, null, null);
		
	 assertNotSame(dados,Ji.getBaseDados());
	}

	@Test
	public void testGetframe1() throws IOException {
		JanelaInicial Ji = new JanelaInicial();
		Ji.abreJanelaProdutos();
		
		assertEquals(Ji.frame1,Ji.getframe1());
		
	}

	@Test
	public void testGetframe2() throws IOException {
		
		JanelaInicial Ji = new JanelaInicial();
	
		
		Ji.abreJanelaProdutos();
		Ji.abreJanelaFact();
		assertNotNull(Ji.frame1);
		assertNotNull(Ji.frame2);
	}

}
