package uam.edu.dircontactos.dir.model;

import java.util.ArrayList;

public class Contacto 
{
	private int id;
	
	private String name;
	
	private ArrayList<Telefono> telefonos;
	
	public Contacto()
	{
		this.telefonos = new ArrayList<>();
	}
	
	public Contacto(int id, String name)
	{
		setId(id);
		setName(name);
		this.telefonos = new ArrayList<>();
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setTelefonos(ArrayList<Telefono> telefonos)
	{
		this.telefonos = telefonos;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void addTelefono(String numero)
	{
		Telefono tel = new Telefono(this.id, numero);
		this.telefonos.add(tel);
	}
	
	public ArrayList<Telefono> getTelefonos()
	{
		return this.telefonos;
	}
}
