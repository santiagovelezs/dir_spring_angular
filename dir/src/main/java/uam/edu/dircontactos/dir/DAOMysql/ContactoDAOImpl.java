package uam.edu.dircontactos.dir.DAOMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uam.edu.dircontactos.dir.iDAO.DAO;
import uam.edu.dircontactos.dir.model.Contacto;

public class ContactoDAOImpl implements DAO<Contacto>
{
	private final Connection connection;

	
    public ContactoDAOImpl(Connection connection) 
    {
        this.connection = connection;
    }

	@Override
	public List<Contacto> getAll() throws Exception
	{
		List<Contacto> elements = new ArrayList<>();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacto");
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next())
        {
        	Contacto contacto = createContacto(resultSet);
        	statement = connection.prepareStatement("SELECT * FROM telefono WHERE id_contacto = ?");
            statement.setInt(1, contacto.getId());
            elements.add(contacto);
        }
        
        return elements;
	}

	@Override
	public Contacto getBy(int id) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacto WHERE id = ?");

		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) 
		{
			return createContacto(resultSet);
		} 
		else 
		{
			return null;
		}
	}

	@Override
	public int save(Contacto t) throws Exception 
	{
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO contacto(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, t.getName());		

		int affectedRows = statement.executeUpdate();
		if(affectedRows == 1)
		{
			ResultSet generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();
			t.setId(generatedKeys.getInt(1));
		}		

		return t.getId();
	}

	@Override
	public boolean update(Contacto t) throws Exception 
	{
		PreparedStatement statement = connection
				.prepareStatement("UPDATE contacto SET name=? WHERE id=?");

		statement.setString(1, t.getName());		
		statement.setInt(4, t.getId());

		return statement.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Contacto t) throws Exception
	{
		PreparedStatement statement = connection.prepareStatement("DELETE FROM contacto WHERE id = ?");

		statement.setInt(1, t.getId());

		return statement.executeUpdate() > 0;
	}
	
	private Contacto createContacto(ResultSet resultSet) throws SQLException
    {
		Contacto contacto = new Contacto(
                resultSet.getInt("id"),
                resultSet.getString("name")                               
        );

        return contacto;
    }
		
}
