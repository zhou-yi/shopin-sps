package loja;


/***********************************************************
* @author FBA
* 2009/05/14
*
***********************************************************/
public class LinhaFactura implements Comparable<LinhaFactura>
{
    private Factura factura;
    private Produto produto;
    private double quantidade;
    private double valor_unitario;

    public enum Order
    {
	ASCENDING, DESCENDING
    }

    public enum Criteria
    {
	BYNAME, BYVALUE, BYINSERT
    }

    private static Order order = Order.ASCENDING;
    private static Criteria criteria = Criteria.BYVALUE;

    /***********************************************************
     * @param quantidade
     * @param valor_unitario
     * @param factura
     * @param produto
     ***********************************************************/
    public LinhaFactura(Factura factura, Produto produto, double quantidade, double valor)
    {
	this.factura = factura;
	this.produto = produto;
	this.quantidade = quantidade;
	this.valor_unitario = valor;
    }

    public LinhaFactura(LinhaFactura linhaFactura)
    {
	this(linhaFactura.factura, 
	     linhaFactura.produto, 
	     linhaFactura.quantidade, 
	     linhaFactura.valor_unitario);
    }

    /***********************************************************
     * @return the quantidade
     ***********************************************************/
    public double getQuantidade()
    {
	return quantidade;
    }

    /***********************************************************
     * @return the valor_unitario
     ***********************************************************/
    public double getValor_unitario()
    {
	return valor_unitario;
    }

    /***********************************************************
     * @return the factura
     ***********************************************************/
    public Factura getFactura()
    {
	return factura;
    }

    /***********************************************************
     * @return the produto
     ***********************************************************/
    public Produto getProduto()
    {
	return produto;
    }

    /***********************************************************
     * @param quantidade
     *            the quantidade to set
     ***********************************************************/
    public void setQuantidade(double quantidade)
    {
	this.quantidade = quantidade;
    }

    /***********************************************************
     * @param valor_unitario
     *            the valor_unitario to set
     ***********************************************************/
    public void setValor_unitario(double valor_unitario)
    {
	this.valor_unitario = valor_unitario;
    }

    /***********************************************************
     * @param factura
     *            the factura to set
     ***********************************************************/
    public void setFactura(Factura factura)
    {
	this.factura = factura;
    }

    /***********************************************************
     * @param produto
     *            the produto to set
     ***********************************************************/
    public void setProduto(Produto produto)
    {
	this.produto = produto;
    }

    /***********************************************************
     * @param produto
     *            the produto to set
     ***********************************************************/
    public static void setSort(Order o, Criteria c)
    {
	order = o;
	criteria = c;
    }

    /***********************************************************
     * @return
     ***********************************************************/
    public double totalLinha()
    {
	return quantidade * valor_unitario;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
	return String.format("%4.1f %-25s %4.2f€ %6.2f€", 
		quantidade, produto.getNome(), valor_unitario, totalLinha());
    }

    /***********************************************************
     * @param arg0
     * @return
     * @see loja.Factura#equals(java.lang.Object)
     ***********************************************************/
    @Override
    public boolean equals(Object arg0)
    {
	return produto.equals(((LinhaFactura) arg0).getProduto());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone(java.lang.Object)
     */

    @Override
    protected Object clone()
    {
	return new LinhaFactura(this);
    }

    @Override
    public int compareTo(LinhaFactura other)
    {
	int result = 1;
	switch (criteria)
	{
	case BYINSERT:
	    result = 0;
	    break;

	case BYVALUE:
	    result = this.totalLinha() > other.totalLinha() ? 1
		    : this.totalLinha() == other.totalLinha() ? 0
			    : -1;
	    break;

	case BYNAME:
	    result = this.produto.getNome().compareTo(
		    other.produto.getNome());
	    break;

	}
	return order == Order.ASCENDING ? result : -result;
    }

}
