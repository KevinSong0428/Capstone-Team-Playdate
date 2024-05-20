package learn.lostandfound.data;

import learn.lostandfound.data.mappers.AnimalMapper;
import learn.lostandfound.data.mappers.UserMapper;
import learn.lostandfound.models.Animal;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AnimalJdbcTemplateRepository implements AnimalRepository{
    private final JdbcTemplate jdbcTemplate;
    public AnimalJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Animal> findAll() throws DataAccessException {
        final String sql = "select animal.animal_id, " +
                "animal.name as animal_name, " +
                "animal.characteristics, " +
                "animal.animal, " +
                "animal.breed from animal;";
        return jdbcTemplate.query(sql, new AnimalMapper());
    }

    @Override
    public Animal findById(int id) throws DataAccessException {
        final String sql = "select animal.animal_id, " +
                "animal.name as animal_name, " +
                "animal.characteristics, " +
                "animal.animal, " +
                "animal.breed from animal " +
                "WHERE animal_id = ?;";
        return jdbcTemplate.query(sql, new AnimalMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Animal add(Animal animal) throws DataAccessException {
        final String sql = "INSERT INTO animal (`name`, characteristics, animal, breed) " +
                "VALUES (?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, animal.getName());
            ps.setString(2, animal.getCharacteristic());
            ps.setString(3, animal.getAnimal());
            ps.setString(4, animal.getBreed());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        animal.setAnimalId(keyHolder.getKey().intValue());
        return animal;
    }

    @Override
    public boolean update(Animal animal) throws DataAccessException {
        final String sql = "UPDATE animal SET " +
                "`name` = ?, characteristics = ?, animal = ?, breed = ? " +
                "WHERE animal_id = ?;";

        return jdbcTemplate.update(sql, animal.getName(), animal.getCharacteristic(), animal.getAnimal(), animal.getBreed(), animal.getAnimalId()) > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        jdbcTemplate.update("DELETE FROM post WHERE animal_id = ?;", id);
        return jdbcTemplate.update("DELETE FROM animal WHERE animal_id = ?;", id) > 0;    }
}
