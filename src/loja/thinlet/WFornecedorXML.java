package loja.thinlet;

import java.io.IOException;

import thinlet.Thinlet;
import loja.BaseDados;

public class WFornecedorXML extends Thinlet {

	private BaseDados b_dados = null;

		public WFornecedorXML(BaseDados b_dados) throws IOException {

			add(parse("janelafornecedores.xml"));
			this.b_dados=b_dados;
		}


}
