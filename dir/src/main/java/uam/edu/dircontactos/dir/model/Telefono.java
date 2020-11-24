package uam.edu.dircontactos.dir.model;

public class Telefono 
{
	private int idContacto;
	
	private String numero;
	
	public Telefono()
	{
		
	}
	
	public Telefono(int idContacto, String numero)
	{
		this.idContacto = idContacto;
		this.numero = numero;
	}
	
	public void setIdContacto(int idContacto)
	{
		this.idContacto = idContacto;
	}
	
	public void setNumero(String numero)
	{
		this.numero = numero;
	}
	
	public int getIdContacto()
	{
		return this.idContacto;
	}
	
	public String getNumero()
	{
		return this.numero;
	}
}
