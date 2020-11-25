package uam.edu.dircontactos.dir.controllers;

import java.sql.Connection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uam.edu.dircontactos.dir.DAOMysql.TelefonoDAOImpl;
import uam.edu.dircontactos.dir.db.DB;
import uam.edu.dircontactos.dir.iDAO.DAO;
import uam.edu.dircontactos.dir.model.Telefono;

@RestController
public class TelefonoController 
{
	private DAO<Telefono> telefonoDAO;
	
	public TelefonoController(Connection conection)
	{
		telefonoDAO = new TelefonoDAOImpl(DB.getConnection());
	}
	
	@PostMapping(path="/telefono")
	public boolean addTelefono(@RequestBody Telefono received) throws Exception
	{		
		int affectedRows = telefonoDAO.save(received);
		
		return affectedRows == 1;
	}  
}
