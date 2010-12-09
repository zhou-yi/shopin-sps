/***********************************************************
 * Filename: InterfaceLoja.java
 * Created:  2009/11/09
 ***********************************************************/
package loja.gui;

import javax.swing.JFrame;

import loja.BaseDados;

/***********************************************************
 * @author FBA 2009/11/09
 * 
 ***********************************************************/
public interface ILoja
{
	public InterfaceFactura criaJanelaFactura(BaseDados bd, JFrame contentor, boolean visivel);
	public InterfaceProduto criaJanelaProduto(BaseDados bd, JFrame contentor, boolean visivel);
}
