package uam.edu.dircontactos.dir;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
