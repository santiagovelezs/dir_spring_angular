package uam.edu.dircontactos.dir.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uam.edu.dircontactos.dir.DAOMysql.ContactoDAOImpl;
import uam.edu.dircontactos.dir.DAOMysql.TelefonoDAOImpl;
import uam.edu.dircontactos.dir.db.DB;
import uam.edu.dircontactos.dir.iDAO.DAO;
import uam.edu.dircontactos.dir.iDAO.DAO_1ToMany;
import uam.edu.dircontactos.dir.model.Contacto;
import uam.edu.dircontactos.dir.model.Telefono;

@RestController
public class ContactoController 
{
	private DAO<Contacto> contactoDAO;
	private DAO_1ToMany<Telefono> telefonoDAO;	
	
	public ContactoController()
	{
		contactoDAO = new ContactoDAOImpl(DB.getConnection());
		telefonoDAO = new TelefonoDAOImpl(DB.getConnection());		
	}
			
	@PostMapping(path="/contactos")
	public boolean addContact(@RequestBody Contacto received) throws Exception
	{
		
		int id = contactoDAO.save(received);
		ArrayList<Telefono> telefonos = received.getTelefonos();
		int affectedRows = 0;
		if(id>0)
		{
			for(Telefono telefono: telefonos)
			{
				telefono.setIdContacto(id);
				affectedRows += telefonoDAO.save(telefono);
			}
				
		}
		return affectedRows == telefonos.size();
	}  
	
	@GetMapping(path="/contactos")
	public List<Contacto> getContatos() throws Exception
	{
		List<Contacto> contactos = contactoDAO.getAll();
		for(Contacto contacto:contactos)
		{
			List<Telefono> telefonos = telefonoDAO.getAllById(contacto.getId());
			for(Telefono telefono:telefonos)
			{
				contacto.addTelefono(telefono.getNumero());
			}			
		}
		return contactos;
	}
	
	@GetMapping("/")		
	public String index()
	{		
		return "Dirrrrrrrrrrrrrr";
	}
	
}

