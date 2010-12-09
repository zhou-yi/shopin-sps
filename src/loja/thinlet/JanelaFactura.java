package loja.thinlet;

import java.io.IOException;

import javax.swing.JOptionPane;

import loja.Factura;
import loja.Produto;

import thinlet.FrameLauncher;
import thinlet.Thinlet;
import utilities.Data;

public class JanelaFactura extends Thinlet {

	protected Factura factura_corrente;

	public JanelaFactura() throws IOException {
		add(parse("JanelaFactura.xml"));

	}

	public void fecha() {

		JanelaInicial.dados.adiciona(factura_corrente);
		JOptionPane.showMessageDialog(null, "Inserida factura "
				+ factura_corrente + " na base de dados!", "Sucesso",
				JOptionPane.PLAIN_MESSAGE);

	}

	public void anula() {

		if (JOptionPane.showConfirmDialog(null, "Anular factura "
				+ factura_corrente + "?", "Confirmar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
			factura_corrente.setAnulada();
		}

	}

	public Factura getFactura_corrente() {
		return factura_corrente;
	}

	public void setFactura_corrente(Factura facturaCorrente) {
		factura_corrente = facturaCorrente;
	}

	public void apaga() {
		if (JOptionPane.showConfirmDialog(null, "Apagar factura "
				+ factura_corrente + " da base de dados?", "Confirmar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0)
			JanelaInicial.dados.retira(factura_corrente);
		factura_corrente = new Factura();

	}

	public void cancela() {
JanelaInicial.frame2.setVisible(false);
	}

	public void adiciona(int i){

//		if (factura_corrente.isAnulada())
//			JOptionPane.showMessageDialog(null,
//					"Não se pode modificar uma factura anulada!", "Erro!",
//					JOptionPane.ERROR_MESSAGE);
//		else {
//
//			factura_corrente.adiciona((Produto) cb_produtos.getSelectedItem(),
//					Double.parseDouble(f_quanto.getText()));
//			escreve(factura_corrente);
//		}

	}

	public void retira() {

	}
	
	public void alterar(Object cb1,Object cb2,Object cb3,Object cb4,Object cb5){
		
		
			setString(cb1, "text", "cafe" );
			setString(cb2, "text", "bolo");
			setString(cb3, "text", "descafeinado" );
			setString(cb4, "text", "tosta mista" );
			setString(cb5, "text", "cha" );
			
		
	}
	
	public void alterar2(Object cb1,Object cb2,Object cb3,Object cb4,Object cb5){
	
		setString(cb1, "text", "Ice tea" );
		setString(cb2, "text", "Coca-cola");
		setString(cb3, "text", "Sumol" );
		setString(cb4, "text", "7 up" );
		setString(cb5, "text", "Agua" );
	}

}
