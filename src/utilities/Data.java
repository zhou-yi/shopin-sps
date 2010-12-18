package utilities;

import java.util.Calendar;

import loja.Factura;


/***********************************************************
* @author Utilizador
* 2009/03/10
*
***********************************************************/
public class Data
{
	private int dia;
	private int mes;
	private int ano;
	
	/***********************************************************
	* @param dia o dia actual
	* @param mes
	* @param ano
	***********************************************************/
	public Data(int dia, int mes, int ano)
	{
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	/***********************************************************
	* @param dia o dia actual
	* @param mes
	* @param ano
	***********************************************************/
	public Data(Data outra)
	{
		this.dia = outra.dia;
		this.mes = outra.mes;
		this.ano = outra.ano;
	}
	
	public static Data hoje()
	{
        Calendar cal = Calendar.getInstance();		
		return new Data(cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
	}
	
	
	/***********************************************************
	 * @return the dia
	 ***********************************************************/
	public int getDia() {
		return dia;
	}

	/***********************************************************
	 * @return the mes
	 ***********************************************************/
	public int getMes() {
		return mes;
	}

	/***********************************************************
	 * @return the ano
	 ***********************************************************/
	public int getAno() {
		return ano;
	}

	/***********************************************************
	 * @param dia the dia to set
	 ***********************************************************/
	public void setDia(int dia) {
		this.dia = dia;
	}

	/***********************************************************
	 * @param mes the mes to set
	 ***********************************************************/
	public void setMes(int mes) {
		this.mes = mes;
	}

	/***********************************************************
	 * @param ano the ano to set
	 ***********************************************************/
	public void setAno(int ano) {
		this.ano = ano;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		Data d = (Data) obj;
		return dia == d.dia && mes == d.mes && ano == d.ano;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return dia + "/" + mes + "/" + ano;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone(java.lang.Object)
	 */
	@Override
	protected Object clone()
	{
		return new Data(this);
	}
	
}
