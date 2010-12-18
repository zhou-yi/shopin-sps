package loja;

import utilities.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import loja.LinhaFactura.Criteria;
import loja.LinhaFactura.Order;
import loja.Produto.CodigoBarras;

/***********************************************************
 * @author FBA 2009/05/14
 * 
 ***********************************************************/
public class Testes
{
	private static Factura	f2;
	private static Produto	p1, p2, p3;

	public static void testaNumeracao()
	{
		Factura f1 = null;
		for (int i = 1; i <= 10; i++)
		{
			f1 = new Factura();
			System.out.println(f1);
		}
	}

	public static void inicializaFactura()
	{
		f2 = new Factura();

		p1 = new Perecivel(1024, 0.6, "Imperial", "cl", new CodigoBarras(
				"5601234567712"), new Data(10, 3, 2009));
		p2 = new NaoPerecivel(855, 1.1, "Coca-Cola", "cl", new CodigoBarras(
				"5601232257712"), new Data(25, 12, 2009));
		p3 = new Perecivel(777, 0.45, "Café", "cl", new CodigoBarras(
				"5601234527712"), new Data(10, 3, 2009));

		f2.adiciona(p1, 5);
		f2.adiciona(p2, 4);
		f2.adiciona(p3, 6);
	}

	public static void testaReflexividade()
	{
		inicializaFactura();
		System.out.println("|" + f2.getClass() + "|");
		for (int i = 0; i < f2.size(); i++)
			System.out.println(f2.get(i)
					+ (f2.get(i).getProduto() instanceof Perecivel ? " (P)"
							: " (NP)"));
	}

	public static void testaCopiarAlterar()
	{
		inicializaFactura();
		Factura copia = (Factura) f2.clone();
		System.out.println(copia);

		Produto p4 = new Perecivel(373, 1.15, "Bolo", "g", null, new Data(10, 3,
				2009));

		copia.adiciona(p2, 6);
		System.out.println(copia);

		copia.adiciona(p4, 2);
		System.out.println(copia);
	}

	public static void testaOrdenar()
	{
		inicializaFactura();

		Produto p4 = new Perecivel(373, 1.15, "Bolo", "g", new CodigoBarras(
				"5601234511882"), new Data(10, 3, 2009));

		f2.adiciona(p2, 6);
		f2.adiciona(p4, 2);

		LinhaFactura.setSort(Order.ASCENDING, Criteria.BYINSERT);
		System.out.println(f2);

		LinhaFactura.setSort(Order.ASCENDING, Criteria.BYVALUE);
		System.out.println(f2);

		LinhaFactura.setSort(Order.DESCENDING, Criteria.BYVALUE);
		System.out.println(f2);

		LinhaFactura.setSort(Order.ASCENDING, Criteria.BYNAME);
		System.out.println(f2);

		LinhaFactura.setSort(Order.DESCENDING, Criteria.BYNAME);
		System.out.println(f2);
	}

	public static void testaTimer()
	{
		final int DELAY = 2000; // Milliseconds between timer ticks

		class MyTimerListener implements ActionListener
		{
			private Random	n	= new Random();

			public void actionPerformed(ActionEvent event)
			{
				int pos = n.nextInt(f2.size());
				f2.adiciona(f2.get(pos).getProduto(), n.nextDouble()
						* f2.get(pos).getQuantidade());
				System.out.println(f2);
			}
		}

		inicializaFactura();
		MyTimerListener listener = new MyTimerListener();

		Timer t = new Timer(DELAY, listener);
		t.start();

		// while (f2.totalFactura() < 200);

		JOptionPane.showMessageDialog(null, "Sair?");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException
	{
		testaNumeracao();
		testaReflexividade();
		testaCopiarAlterar();
		testaOrdenar();
		testaTimer();
	}

}
