package learn.lostandfound.data;

import learn.lostandfound.models.Animal;
import learn.lostandfound.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AnimalJdbcTemplateRepositoryTest {
    @Autowired
    AnimalJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Animal> animals = repository.findAll();
        assertNotNull(animals);

        assertTrue(animals.size() >= 6 && animals.size() <= 8);
    }

    @Test
    void shouldFindById() throws DataAccessException {
        Animal buddy = new Animal(1, "Buddy", "Friendly, loves children", "Dog", "Golden Retriever");
        Animal whiskers = new Animal(2, "Whiskers", "Shy, has a missing tail", "Cat", "Siamese");

        Animal actual = repository.findById(1);
        assertEquals(actual, buddy);

        actual = repository.findById(2);
        assertEquals(actual, whiskers);

        actual = repository.findById(9);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Animal animal = new Animal( "Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        Animal actual = repository.add(animal);
        assertNotNull(actual);
        assertEquals(8, actual.getAnimal());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Animal animal = new Animal( "Test Updating Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setAnimalId(1);
        animal.setName("Test Updated Name");
        assertTrue(repository.update(animal));
    }

    @Test
    void shouldDelete() throws DataAccessException {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(10));
    }
}