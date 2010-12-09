package loja.thinlet.test;

import static org.junit.Assert.*;

import java.io.IOException;

import loja.BaseDados;
import loja.Perecivel;
import loja.Produto.CodigoBarras;
import loja.thinlet.JanelaInicial;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.Data;

public class JanelaProdutosTest {

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
	public void testJanelaProdutos() {
		//
	}

	@Test
	public void testCancela() throws IOException {
		JanelaInicial j = new JanelaInicial();
		j.setVisible(false);
		
	}

	@Test
	public void testRemoveStringStringStringStringStringStringStringStringBoolean() throws IOException {
		
		
		
		Perecivel p = new Perecivel(12, 12.3, "cenas", "unidade", new CodigoBarras("ola"), new Data(12,12,2222));
		BaseDados dados = new BaseDados("data/produtos.txt", "data/facturas.txt",
		"data/linhas_factura.txt");
		
		BaseDados dados_aux = new BaseDados("data/produtos.txt", "data/facturas.txt",
		"data/linhas_factura.txt");

		dados.adiciona(p);
		
		dados.retira(p);
	
	assertFalse(dados.existe(p));
		
		
		
	}

	@Test
	public void testAdiciona() throws IOException {
		Perecivel p = new Perecivel(12, 12.3, "cenas", "unidade", new CodigoBarras("ola"), new Data(12,12,2222));
		BaseDados dados = new BaseDados("data/produtos.txt", "data/facturas.txt",
		"data/linhas_factura.txt");
		
		BaseDados dados_aux = new BaseDados("data/produtos.txt", "data/facturas.txt",
		"data/linhas_factura.txt");

		dados.adiciona(p);
		assertTrue(dados.existe(p));
		
		
	}

}
