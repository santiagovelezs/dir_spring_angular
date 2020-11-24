package uam.edu.dircontactos.dir.DAOMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uam.edu.dircontactos.dir.iDAO.DAO;
import uam.edu.dircontactos.dir.iDAO.DAO_1ToMany;
import uam.edu.dircontactos.dir.model.Telefono;

public class TelefonoDAOImpl implements DAO<Telefono>, DAO_1ToMany<Telefono>
{
	private final Connection connection;

	
    public TelefonoDAOImpl(Connection connection) 
    {
        this.connection = connection;
    }

    @Override
	public List<Telefono> getAll() throws Exception
	{
		List<Telefono> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM telefono");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Telefono telefono = createTelefono(resultSet);
            
            elements.add(telefono);
        }
        
        return elements;
	}
    
    @Override
	public List<Telefono> getAllById(int id) throws Exception 
    {
		List<Telefono> elements = new ArrayList<>();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM telefono WHERE id_contacto = ?");
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) 
		{
			Telefono telefono = createTelefono(resultSet);

			elements.add(telefono);
		}

		return elements;
	}

	@Override
	public Telefono getBy(int id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM telefono WHERE id_contacto = ?");

		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createTelefono(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Telefono t) throws SQLException 
	{
		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO telefono(id_contacto, telefono) VALUES (?, ?)");

		statement.setInt(1, t.getIdContacto());
		statement.setString(2, t.getNumero());

		int affectedRows = statement.executeUpdate();

		return affectedRows;
	}

	@Override
	public boolean update(Telefono t) throws Exception 
	{
		PreparedStatement statement = connection
				.prepareStatement("UPDATE telefono SET telefono=? WHERE id_contacto=?");

		statement.setString(1, t.getNumero());		
		statement.setInt(4, t.getIdContacto());

		return statement.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Telefono t) throws Exception
	{
		PreparedStatement statement = connection
				.prepareStatement("DELETE FROM telefono WHERE id_contacto = ? AND telefono = ?");

		statement.setInt(1, t.getIdContacto());
		statement.setString(2, t.getNumero());

		return statement.executeUpdate() > 0;
	}
	
	private Telefono createTelefono(ResultSet resultSet) throws SQLException
    {
		Telefono telefono = new Telefono(
                resultSet.getInt("id_contacto"),
                resultSet.getString("telefono")                               
        );

        return telefono;
    }
	
}
