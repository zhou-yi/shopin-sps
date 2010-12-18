package loja.thinlet.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import loja.BaseDados;
import loja.Perecivel;
import loja.Produto;
import loja.thinlet.WProdutoXML;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import thinlet.Thinlet;
import utilities.Data;

public class WProdutoXML_Test {
	private static BaseDados dados;
	private static WProdutoXML t;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dados = new BaseDados("data/produtos.txt", "data/facturas.txt", "data/linhas_factura.txt");
		try
		{
			dados.carrega();
			System.out.println("Carregou " + dados.produtos().size() + " produtos.");
			System.out.println("Carregou " + dados.facturas().size() + " facturas.");
			t= new WProdutoXML(dados);

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
	public void testAdiciona() throws IOException {
		
		Object check_box_perecivel = t.create("checkbox");
		t.setBoolean(check_box_perecivel,"selected", true);
		
		Object text_field_codigo = t.create("textfield");
		t.setString(text_field_codigo,"text", "15");
		
		Object text_field_pvenda = t.create("textfield");
		t.setString(text_field_pvenda,"text", "1");
		
		Object text_field_nome = t.create("textfield");
		t.setString(text_field_nome,"text", "PENIS");
		
		Object text_field_unidade = t.create("textfield");
		t.setString(text_field_unidade,"text", "cm");
		
		Object text_field_cbarras = t.create("textfield");
		t.setString(text_field_cbarras,"text", "5546512345");
		
		Object text_field_dia = t.create("textfield");
		t.setString(text_field_dia,"text", "12");
		
		Object text_field_mes = t.create("textfield");
		t.setString(text_field_mes,"text", "12");
		
		Object text_field_ano = t.create("textfield");
		t.setString(text_field_ano,"text", "2000");
		
		
		Produto p = new Perecivel(Integer.parseInt(t.getString(text_field_codigo,"text")),Double.parseDouble(t.getString(text_field_pvenda,"text")),t.getString(text_field_nome,"text"),t.getString(text_field_unidade,"text"),new Produto.CodigoBarras(t.getString(text_field_cbarras,"text")),new Data(Integer.parseInt(t.getString(text_field_dia,"text")),Integer.parseInt(t.getString(text_field_mes,"text")),Integer.parseInt(t.getString(text_field_ano,"text"))));
		t.adiciona(check_box_perecivel,text_field_codigo,text_field_pvenda,text_field_nome,text_field_unidade,text_field_cbarras,text_field_dia,text_field_mes,text_field_ano);
		
		assertTrue(dados.existe(p));
	}

	@Test
	public void testRemoveObjectObjectObjectObjectObjectObjectObjectObjectObject() {
	
		
		Object check_box_perecivel = t.create("checkbox");
		t.setBoolean(check_box_perecivel,"selected", true);
		
		Object text_field_codigo = t.create("textfield");
		t.setString(text_field_codigo,"text", "15");
		
		Object text_field_pvenda = t.create("textfield");
		t.setString(text_field_pvenda,"text", "1");
		
		Object text_field_nome = t.create("textfield");
		t.setString(text_field_nome,"text", "PENIS");
		
		Object text_field_unidade = t.create("textfield");
		t.setString(text_field_unidade,"text", "cm");
		
		Object text_field_cbarras = t.create("textfield");
		t.setString(text_field_cbarras,"text", "5546512345");
		
		Object text_field_dia = t.create("textfield");
		t.setString(text_field_dia,"text", "12");
		
		Object text_field_mes = t.create("textfield");
		t.setString(text_field_mes,"text", "12");
		
		Object text_field_ano = t.create("textfield");
		t.setString(text_field_ano,"text", "2000");
		
		
		Produto p = new Perecivel(Integer.parseInt(t.getString(text_field_codigo,"text")),Double.parseDouble(t.getString(text_field_pvenda,"text")),t.getString(text_field_nome,"text"),t.getString(text_field_unidade,"text"),new Produto.CodigoBarras(t.getString(text_field_cbarras,"text")),new Data(Integer.parseInt(t.getString(text_field_dia,"text")),Integer.parseInt(t.getString(text_field_mes,"text")),Integer.parseInt(t.getString(text_field_ano,"text"))));
		if(t.remove(check_box_perecivel,text_field_codigo,text_field_pvenda,text_field_nome,text_field_unidade,text_field_cbarras,text_field_dia,text_field_mes,text_field_ano))
		
		assertFalse(dados.existe(p));
	
	
	}

	@Test
	public void testSetValidade() throws IOException {
		Object panel = t.create("panel");
		t.setvalidade(panel);
		assertTrue(!t.getString(panel, "text").equals(null));

}

}