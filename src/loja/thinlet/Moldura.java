package loja.thinlet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import thinlet.*;

public class Moldura extends FrameLauncher implements WindowListener{

	
	public Moldura(String nome, Thinlet componente, int width, int hight, boolean visivel) {
		super(nome, componente, width, hight);
		this.setVisible(visivel);
		

	}

	public void windowActivated(WindowEvent arg0) {
					
	}
	
	public void windowClosing(WindowEvent we){
      
        this.setVisible(false);
      }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

		
			
	
	


}
