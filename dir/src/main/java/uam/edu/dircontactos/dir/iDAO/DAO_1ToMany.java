package uam.edu.dircontactos.dir.iDAO;

import java.util.List;

public interface DAO_1ToMany<T> 
{
    List<T> getAllById(int id) throws Exception;
    
}
