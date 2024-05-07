package learn.lostandfound.data.mappers;

import learn.lostandfound.models.Animal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalMapper implements RowMapper<Animal> {
    @Override
    public Animal mapRow(ResultSet resultSet, int i) throws SQLException {
        Animal animal = new Animal();
        animal.setAnimalId(resultSet.getInt("animal_id"));
        animal.setName(resultSet.getString("name"));
        animal.setCharacteristic(resultSet.getString("characteristics"));
        animal.setAnimal(resultSet.getString("animal"));
        animal.setBreed(resultSet.getString("breed"));
        return animal;
    }
}
