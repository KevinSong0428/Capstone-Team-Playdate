package learn.lostandfound.data;

import learn.lostandfound.models.Animal;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AnimalRepository {
    List<Animal> findAll() throws DataAccessException;
    Animal findById(int id) throws DataAccessException;
    Animal add(Animal animal) throws DataAccessException;
    boolean update(Animal animal) throws DataAccessException;
    boolean deleteById(int id) throws DataAccessException;
}


