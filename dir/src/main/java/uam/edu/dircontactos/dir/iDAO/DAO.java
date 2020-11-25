package uam.edu.dircontactos.dir.iDAO;

import java.util.List;

public interface DAO<T> 
{
    List<T> getAll() throws Exception;

    T getBy(int id) throws Exception;
    
    //List<T> getAllById(int id) throws Exception;

    int save(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(T t) throws Exception;
}
