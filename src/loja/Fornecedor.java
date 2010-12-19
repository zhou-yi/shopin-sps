package loja;

public class Fornecedor 
{
	private String name;
	private char productKind;
	private int id;
	private String contact;
	private String address;
	
	public Fornecedor(String name, char productKind, int id, String contact, String address)
	{
		this.name = name;
		this.productKind = productKind;
		this.id = id;
		this.contact = contact;
		this.address = address;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public char getProductKind() 
	{
		return productKind;
	}

	public void setProductKind(char productKind) 
	{
		this.productKind = productKind;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getContact() 
	{
		return contact;
	}

	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String toString()
	{
		return id + " " + productKind + "\n" +  name + "\n" + address + "\n" + contact;  
	}
	public void removeSupplier(String name)
	{
		if(name != null)
			throw new NullPointerException();
		
		if(BaseDados.fornecedores().contains(name))
			BaseDados.fornecedores().remove(BaseDados.fornecedores().contains(name));
	}
}
