package learn.lostandfound.data;

import learn.lostandfound.models.Animal;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AnimalRepository {
    List<Animal> findAll() throws org.springframework.dao.DataAccessException;
    Animal findById(int id) throws org.springframework.dao.DataAccessException;
    Animal add(Animal animal) throws org.springframework.dao.DataAccessException;
    boolean update(Animal animal) throws org.springframework.dao.DataAccessException;
    boolean deleteById(int id) throws DataAccessException;
}


