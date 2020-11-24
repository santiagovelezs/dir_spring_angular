package uam.edu.dircontactos.dir;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import uam.edu.dircontactos.dir.db.DB;

@SpringBootApplication
public class DirApplication 
{
	@Bean
	public Connection jdbcConnection() 
	{
	    return DB.getConnection();
	}

	public static void main(String[] args) 
	{
		SpringApplication.run(DirApplication.class, args);
	}

}
